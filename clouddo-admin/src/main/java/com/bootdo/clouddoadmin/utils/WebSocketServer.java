package com.bootdo.clouddoadmin.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.clouddoadmin.domain.UserDO;
import com.bootdo.clouddoadmin.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * @author zhengkai.blog.csdn.net
 */
@ServerEndpoint("/imserver/{userId}")
@Component
public class WebSocketServer {


    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    @Autowired
    private UserService userService;
    private static WebSocketServer webSocketServer;
    /**接收userId*/
    private String userId="";

    /**
     * 连接建立成功调用的方法*/

    @PostConstruct
    public void init() {
        webSocketServer = this;
    }

    @OnOpen
    public void onOpen(Session session,@PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
            System.out.println("重新连接");
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
            UserDO userDO=new UserDO();
            userDO.setUserId(Long.parseLong(userId));
            userDO.setIsflag(1);
            webSocketServer.userService.update(userDO);
            System.out.println("新增连接");
        }



        try {
            System.out.println("连接成功");
            String msg = "{\"online\":true}";
            ArrayList<String> keylist = getMapKeys(webSocketMap);//获取key列表
            for(int i=0;i<webSocketMap.size();i++) {
                webSocketMap.get(keylist.get(i)).sendMessage(msg);//测试发送上线消息
                System.out.println("发送消息");
            }
        } catch (Exception e) {

        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            UserDO userDO=new UserDO();
            userDO.setUserId(Long.parseLong(userId));
            userDO.setIsflag(0);
            webSocketServer.userService.update(userDO);
            //从set中删除
            subOnlineCount();
        }

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {

        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromUserId",this.userId);
                String toUserId=jsonObject.getString("toUserId");
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toUserId)&&webSocketMap.containsKey(toUserId)){
                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
                }else{

                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId,String chatname,String mine) throws IOException {
        String msg = "{\"msg\":\""+message+"\",\"userid\":"+userId+",\"username\":\""+chatname+"\",\"isfalg\":true,\"mymine\":"+mine+"}";
        if(StringUtils.isNotBlank(userId)&&webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(msg);
            System.out.println("》》》》》》》》》发送私信"+msg);
        }else{
        }
    }
    /**
     * 发送自定义群发消息
     * */
    public static void sendInfoall(String message,String sendid,String chatname) throws IOException {
        ArrayList<String> keylist = getMapKeys(webSocketMap);//获取key列表
        String msg = "{\"msg\":\""+message+"\",\"userid\":"+sendid+",\"username\":\""+chatname+"\",\"isfalg\":false}";
        System.out.println(msg);
        for(int i=0;i<webSocketMap.size();i++) {
                webSocketMap.get(keylist.get(i)).sendMessage(msg);
                System.out.println("发送消息");
        }
    }
    public static ArrayList<String> getMapKeys(Map map){
        if(map==null || map.size()==0)
        return null;
                ArrayList<String> list = new ArrayList<String>();
        Iterator iter = map.keySet().iterator();
        while (iter.hasNext()) {
        String tmpkey = (String)iter.next();
        if(tmpkey==null || tmpkey.length()==0)
        continue;
        list.add(tmpkey);
        }
        return list;
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized ConcurrentHashMap<String, WebSocketServer> getOnlineuserids() {
        return webSocketMap;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    public static synchronized ArrayList<String> getOnlinekeylist() {
        return getMapKeys(webSocketMap);
    }
}
