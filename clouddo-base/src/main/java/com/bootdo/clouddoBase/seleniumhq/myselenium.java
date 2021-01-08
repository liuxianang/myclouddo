package com.bootdo.clouddoBase.seleniumhq;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class myselenium {
   public static  void 京东秒杀() throws InterruptedException, IOException {
       Thread.sleep(3000);
       System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
       WebDriver driver = new ChromeDriver();
       //System.out.println(">>>>>>>>>>>>>>>>>偏移位置"+getMoveDistance(driver));
      /*  System.setProperty("webdriver.gecko.driver", "c://geckodriver.exe");
        WebDriver driver = new FirefoxDriver();*/
//      driver.manage().window().maximize();
       driver.manage().window().setPosition(new Point(100, 50));
       driver.manage().deleteAllCookies();
       // 与浏览器同步非常重要，必须等待浏览器加载完毕
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       driver.get("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fmiaosha.jd.com%2F");

      /*  Thread.sleep(1000);

        WebElement qqLoginLink = driver
                .findElement(By.xpath("//div[class='login-btn']"));
        qqLoginLink.click();
        Thread.sleep(1000);

        // 获取当前页面句柄
        String handle = driver.getWindowHandle();
        // 获取所有页面的句柄，并循环判断不是当前的句柄 然后切换到子窗体
        for (String handles : driver.getWindowHandles()) {
            if (handles.equals(handle))
                continue;
            driver.switchTo().window(handles);
        }*/

       // 由于登录输入框在frame中，还需要先切换进入frame，否则，也找不到输入框的


       // 调试过程中，如果提示找不到元素，不知道是否切换成功了，可以把当前handler的source打印出来看看
       // System.out.println(driver.getPageSource());
       Thread.sleep(2000);
       driver.findElement(By.xpath("//a [contains(text(),'账户登录')]")).click();//进入回放课表
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@id='loginname']")).sendKeys("15070938483");
       driver.findElement(By.xpath("//*[@id='nloginpwd']")).sendKeys("291291");


       //由于我的账号没绑定手机，点登录后会有个提示，如果直接关闭，可能被判断为还没完成登录，没有会话，所以稍等片刻
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@class='login-btn']")).click();
       Thread.sleep(2000);
       String currentPageUrl = driver.getCurrentUrl();
       System.out.println(">>>>>>>>>>>>"+currentPageUrl);
       String img = driver.findElement(By.xpath("//div/div[@class='JDJRV-bigimg']/img")).getAttribute("src");
       byte[] imagedata = DatatypeConverter.parseBase64Binary(img.substring(img.indexOf(",") + 1));
       BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagedata));
       ImageIO.write(bufferedImage, "png", new File("D:\\testjpg\\test.png"));
       String top = driver.findElement(By.xpath("//div/div[@class='JDJRV-smallimg']")).getAttribute("style");
       System.out.println(">>>>>>>>>>>>"+top.substring(6,10));
       //关闭弹出的子窗体
       //driver.close();
       Thread.sleep(2000);
       Actions actions = new Actions(driver);
       new Actions(driver).clickAndHold( driver.findElement(By.xpath("//*[@class='JDJRV-slide-inner JDJRV-slide-btn']"))).perform();//click and hold the moveButton
       Thread.sleep(1000);//slow down
       actions.moveByOffset(67,0).perform();
       actions.release(driver.findElement(By.xpath("//*[@class='JDJRV-slide-inner JDJRV-slide-btn']"))).perform();

       Thread.sleep(1000);
       Actions action2 = new Actions(driver);
       new Actions(driver).clickAndHold( driver.findElement(By.xpath("//*[@class='JDJRV-slide-inner JDJRV-slide-btn']"))).perform();//click and hold the moveButton
       Thread.sleep(1000);//slow down
       action2.moveByOffset(67,0).perform();
       action2.release(driver.findElement(By.xpath("//*[@class='JDJRV-slide-inner JDJRV-slide-btn']"))).perform();
       //driver.navigate() 下有很多方法，比如后退，刷新等
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@class='my_course_item']")).click();//进入我的课程
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@class='btn primary']")).click();//进入学习界面
       Thread.sleep(2000);
       driver.findElement(By.xpath("//div [contains(text(),'回放课表')]")).click();//进入回放课表
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@class='btn']")).click();//进入回放课表
      /*  Thread.sleep(2000);
        List<WebElement> downlist = driver.findElements(By.xpath("//*[@class='downloadBtn']"));*/
   }
}
