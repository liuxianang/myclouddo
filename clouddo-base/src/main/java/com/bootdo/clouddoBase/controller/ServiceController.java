package com.bootdo.clouddoBase.controller;


import com.bootdo.clouddoBase.service.LogService;
import com.bootdo.clouddocommon.dto.LogDO;
import com.bootdo.clouddocommon.dto.ServiceDo;
import com.bootdo.clouddocommon.utils.PageUtils;
import com.bootdo.clouddocommon.utils.Query;
import com.bootdo.clouddocommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/service")
@RestController
public class ServiceController {
    @Autowired
    LogService logService;

    @GetMapping()
    R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List <ServiceDo>listserviceDo  = new ArrayList<ServiceDo>();
        int count=0;
        System.out.println("sssssssssssssssssssssssssssssss");
        try {
            String path = "http://localhost:8001/";// 要获得html页面内容的地址

            URL url = new URL(path);// 创建url对象

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();// 打开连接

            conn.setRequestProperty("contentType", "GBK"); // 设置url中文参数编码

            conn.setConnectTimeout(5 * 1000);// 请求的时间

            conn.setRequestMethod("GET");// 请求方式

            InputStream inStream = conn.getInputStream();
            // readLesoSysXML(inStream);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    inStream, "GBK"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            // 读取获取到内容的最后一行,写入
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String str = buffer.toString();

            String ipString1 = str.substring(str.indexOf("<tbody>"));

            // 获取你的IP是中间的[182.149.82.50]内容
            String ipsString2 = ipString1.substring(ipString1.indexOf("<tbody>") + 6,
                    ipString1.indexOf("</tbody>"));
            //获取当前IP地址所在地址
			/*	String ipsString3=ipString1.substring(ipString1.indexOf(": "),ipString1.lastIndexOf("</center>"));
				System.err.println(ipsString3);*/

            // 返回公网IP值
            ipsString2=ipsString2.replace(" ","");
            String[] aa = ipsString2.split("<tr>");
            count=aa.length-1;
            for(int i=1; i<aa.length;i++){

                ServiceDo serviceDo=new ServiceDo();
                String[] bb = aa[i].split("<td>");
                for(int j=1; j<bb.length;j++){
                   bb[j]=bb[j].replaceAll("<b>|</b>|</td>|<ahref=\"(.*)target=\"_blank\">|</a>|</tr>","");
                    System.out.println( bb[j]);
                }
                serviceDo.setApplication(bb[1]);
                serviceDo.setAMIs(bb[2]);
                serviceDo.setAvailabilityZones(bb[3]);
                serviceDo.setStatus(bb[4]);

                listserviceDo.add(serviceDo);
            }
        } catch (Exception e) {
            System.out.println("获取注册信息中心信息失败");
        }


        return R.page(new PageUtils(listserviceDo, count));
    }







    @PostMapping("/save")
    R save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @DeleteMapping()
    R remove(Long id) {
        if (logService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/batchRemove")
    R batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }
}
