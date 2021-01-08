
package com.bootdo.clouddoadmin.controller;


import com.bootdo.clouddoadmin.domain.UserChatmsgDO;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.domain.msgDO;
import com.bootdo.clouddoadmin.service.UserChatmsgService;
import com.bootdo.clouddoadmin.service.UserService;
import com.bootdo.clouddoadmin.utils.WebSocketServer;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.*;


/**
 * WebSocketController
 * @author zhengkai.blog.csdn.net
 */

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private UserChatmsgService userChatmsgService;
    @Autowired
    UserService userService;
    @GetMapping("index")
    public ResponseEntity<String> index(){
        return ResponseEntity.ok("请求成功");
    }
    @GetMapping("chatlist")
    public R chatlist(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<UserChatmsgDO> userChatmsgList = userChatmsgService.top10list(params);
        for(int i=0;i<userChatmsgList.size();i++){
            if(userChatmsgList.get(i).getUserid().equals(params.get("chatid").toString())){
                userChatmsgList.get(i).setMine(true);
            }else{
                userChatmsgList.get(i).setMine(false);
            }
            msgDO msg=new msgDO();
            msg.setText(userChatmsgList.get(i).getMsg());//转换数据格式
            userChatmsgList.get(i).setText(msg);
        }
        return R.ok().put("data",userChatmsgList);
    }
    @GetMapping("chatmylist")
    public R chatmylist(@RequestParam Map<String, Object> params){
        //查询列表数据
        List<UserChatmsgDO> userChatmsgList = userChatmsgService.top10mylist(params);
        for(int i=0;i<userChatmsgList.size();i++){
            if(userChatmsgList.get(i).getUserid().equals(params.get("chatid").toString())){
                userChatmsgList.get(i).setMine(false);
            }else{
                userChatmsgList.get(i).setMine(true);
            }
            msgDO msg=new msgDO();
            msg.setText(userChatmsgList.get(i).getMsg());//转换数据格式
            userChatmsgList.get(i).setText(msg);
        }
        return R.ok().put("data",userChatmsgList);
    }
    @GetMapping("useronline")
    public R useronline(@RequestParam Map<String, Object> params){
        //查询列表数据
        ArrayList<String> Onlinekeylist = WebSocketServer.getOnlinekeylist();
        System.out.println(">>>>>>>>>"+Onlinekeylist);
        ArrayList<UserDO> userlist = new ArrayList<UserDO>();
        for(int i=0;i<Onlinekeylist.size();i++){
            UserDO muser = userService.get(Long.parseLong(Onlinekeylist.get(i)));
            userlist.add(muser);
        }
        System.out.println(">>>>>>>>>"+userlist);
        return R.ok().put("data",userlist);
    }
    @GetMapping("page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @GetMapping("/pushmsg")
    public ResponseEntity<String> pushToWeb(String message,@RequestParam Map<String, Object> params) throws IOException {
        System.out.println(">>>>>>>>>"+WebSocketServer.getOnlineuserids());
        System.out.println(">>>>>>>>>"+params.get("chatid").toString());
        String[] userids = params.get("chatid").toString().split(",");
        WebSocketServer.sendInfo(params.get("msg").toString(),userids[0],params.get("chatname").toString(),"true");
        WebSocketServer.sendInfo(params.get("msg").toString(),userids[1],params.get("chatname").toString(),"false");
        System.out.println(">>>>>>>>>"+WebSocketServer.getOnlineCount());
        UserChatmsgDO userChatmsgDO=new UserChatmsgDO();
        userChatmsgDO.setDate(new Date());
        userChatmsgDO.setName(params.get("chatname").toString());
        userChatmsgDO.setUserid(userids[0]);
        userChatmsgDO.setMsg(params.get("msg").toString());
        userChatmsgDO.setChatstate(1);//私聊消息持久化
        userChatmsgService.save(userChatmsgDO);//记录聊天记录
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
    @GetMapping("/pushall")
    public ResponseEntity<String> pushToWeb(@RequestParam Map<String, Object> params) throws IOException {
        System.out.println(">>>>>>>>>"+WebSocketServer.getOnlineuserids());
        System.out.println(">>>>>>>>>"+WebSocketServer.getOnlineCount());
        WebSocketServer.sendInfoall(params.get("msg").toString(),params.get("chatid").toString(),params.get("chatname").toString());
        UserChatmsgDO userChatmsgDO=new UserChatmsgDO();
        userChatmsgDO.setDate(new Date());
        userChatmsgDO.setName(params.get("chatname").toString());
        userChatmsgDO.setUserid(params.get("chatid").toString());
        userChatmsgDO.setMsg(params.get("msg").toString());
        userChatmsgDO.setChatstate(0);//群聊消息持久化
        userChatmsgService.save(userChatmsgDO);//记录聊天记录
        return ResponseEntity.ok("MSG SEND SUCCESS");
    }
}


