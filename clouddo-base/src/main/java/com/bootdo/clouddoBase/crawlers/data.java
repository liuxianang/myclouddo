package com.bootdo.clouddoBase.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.bootdo.clouddoBase.Util.jdbcutil;
import org.seimicrawler.xpath.JXDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Crawler(name = "data")
public class data extends BaseSeimiCrawler {
    @Override
   /* public String[] startUrls() {
        return new String[]{"https://www.csdn.net/"};
    }*/
    public String[] startUrls() {
        return new String[]{"https://pindao.suning.com/city/bingxi.html"};
    }
    @Override
    public void start(Response response) {
        JXDocument doc = response.document();
        try {
           /* List lis = doc.sel("//ul/li[@class='bz-bb u-goods-item']/a/@href");*/
            List urls = doc.sel("//ul/li[@class='bz-bb u-goods-item']/a/@href");
            logger.info("{}", urls.size());
            for (Object s:urls){
                push(new Request("https:"+s.toString(),"getTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goRun(Response response) {

    }

    public void getTitle(Response response){
        JXDocument doc = response.document();
        List urls2 =doc.sel("//img/@src");
        Download(urls2);
        jdbcutil datesql=new jdbcutil();

        try {
          /*  logger.info("url:{} {}", response.getUrl(), doc.sel("//img/@src"));*/
            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@id='itemDisplayName']/text()"));
            datesql.connect(response.getUrl(),doc.sel("//h1[@id='itemDisplayName']/text()").toString());
            //do something
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载图片
    private void Download(List<String> listImgSrc) {
        try {
            //开始时间
            Date begindate = new Date();
            listImgSrc.removeAll(Collections.singleton(""));
            for (String url : listImgSrc) {
                //开始时间
                Date begindate2 = new Date();

                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());
                System.out.println(">>>>>>>>>>>>"+imageName);

                URL uri = new URL("http:"+url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File("D:/image/"+ System.currentTimeMillis()+".png"));//文件输出流
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                //关闭流
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");

                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            System.out.println("下载失败"+e);
        }
    }
  /*  @Scheduled(cron = "0/60 * * * * ?")
    public void callByCron(){
        logger.info("我是一个根据cron表达式执行的调度器，60秒一次");
        // 可定时发送一个Request
        CrawlerModel model = CrawlerCache.getCrawlerModel("data");
       if(model==null){
           Seimi s = new Seimi();
           s.goRun("data");
       }else{
           System.out.println( model.isUseUnrepeated());

           model.sendRequest(new Request("https://pindao.suning.com/city/bingxi.html",data::getTitle));

       }

        //push(Request.build(startUrls()[0],"getTitle"));
    }*/
}
