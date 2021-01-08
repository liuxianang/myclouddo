package com.bootdo.clouddoBase.util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.imageio.ImageIO;

public class SealUtils {
    private static final String FONT_FAMILY = "宋体"; // 字体
    private static final String STAR = "★";

    /**
     * 根据companyName获得合同专用章
     *  
     *
     * @param companyName
     * @return
     */
    public static BufferedImage genSealImage(String companyName, String FOOT_TXT, int CANVAS_SIZE) {
        BufferedImage bi = new BufferedImage(CANVAS_SIZE, CANVAS_SIZE, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0));
        g2d.fillRect(0, 0, CANVAS_SIZE, CANVAS_SIZE);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));
        int circleRadius = CANVAS_SIZE / 2 - 10;
        drawCicle(g2d, circleRadius);
        drawStar(g2d, circleRadius, CANVAS_SIZE);
        drawFootText(g2d, circleRadius, FOOT_TXT, CANVAS_SIZE);
        drwaHeadText(companyName, g2d, circleRadius);
        g2d.dispose();
        return bi;
    }

    /**
     * 辅助方法，byte数组保存为本地文件
     *
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *      * 根据companyName获得合同专用章
     *      * 
     *      * @param companyName
     *      * @return
     *      * @throws IOException
     *      
     */
    public static byte[] genSealData(String companyName, String FOOT_TXT, int CANVAS_SIZE) throws IOException {
        BufferedImage image = genSealImage(companyName, FOOT_TXT, CANVAS_SIZE);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", outStream);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
            }
        }
        byte[] imageData = outStream.toByteArray();
        return imageData;
    }


    /**
     * 弧形绘制顶部名称
     *  
     *
     * @param companyName
     * @param g2d
     * @param circleRadius
     */
    private static void drwaHeadText(String companyName, Graphics2D g2d, int circleRadius) {
        int companyNameLen = companyName.length();
        int headFontSize;
        if (companyNameLen < 8) { // 字体根据画布大小微调出来的
            headFontSize = 47;
        } else {
            /*headFontSize = 43 - (companyNameLen - 8) * 2;*/
            headFontSize = 27;
            System.out.println(">>>>>>>>>>>"+headFontSize);
        }


        Font f;
        FontRenderContext context;
        Rectangle2D rectangle;
        f = new Font(FONT_FAMILY, Font.BOLD, headFontSize);
        System.out.println(">>>>>>>>>>>"+f);
        context = g2d.getFontRenderContext();
        rectangle = f.getStringBounds(companyName, context);


        double interval; // 文字之间间距
        if (companyNameLen == 1) {
            interval = 0;
        } else {
            interval = rectangle.getWidth() / (companyNameLen - 1) * 0.9;
        }


        double newRadius = circleRadius + rectangle.getY() - 10; // bounds.getY()是负数，这样可以将弧形文字固定在圆内了。-10目的是离圆环稍远一点
        double radianPerInterval = 2 * Math.asin(interval / (2 * newRadius)); // 每个间距对应的角度


        // 第一个元素的角度
        double firstAngle;
        if (companyNameLen % 2 == 1) {
            firstAngle = (companyNameLen - 1) * radianPerInterval / 2.0 + Math.PI / 2 + 0.18; // 0.18是浮点计算误差，如果文字不对称，可以微调
        } else {
            firstAngle = (companyNameLen / 2.0 - 1) * radianPerInterval + radianPerInterval / 2.0 + Math.PI / 2 + 0.18;
        }
        for (int i = 0; i < companyNameLen; i++) {
            double theta = firstAngle - i * radianPerInterval;
            double thetaX = newRadius * Math.sin(Math.PI / 2 - theta); // 小小的trick，将【0，pi】区间变换到[pi/2,-pi/2]区间
            double thetaY = newRadius * Math.cos(theta - Math.PI / 2); // 同上类似，这样处理就不必再考虑正负的问题了
            AffineTransform transform = AffineTransform.getRotateInstance(Math.PI / 2 - theta);
            Font f2 = f.deriveFont(transform);
            g2d.setFont(f2);
            g2d.drawString(companyName.substring(i, i + 1), (float) (circleRadius + thetaX + 10), (float) (circleRadius - thetaY + 10));
        }
    }


    /**
     * 画底部文字
     *  
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawFootText(Graphics2D g2d, int circleRadius, String FOOT_TXT, int CANVAS_SIZE) {
        Font f;
        FontRenderContext context;
        Rectangle2D rectangle;
        f = new Font(FONT_FAMILY, Font.BOLD, CANVAS_SIZE / 16);
        System.out.println(">>>>>>>>>>111"+f);
        context = g2d.getFontRenderContext();
        rectangle = f.getStringBounds(FOOT_TXT, context);
        int a = 10 * CANVAS_SIZE / 300; // 底部文字距中心的距离，这是调出来的
        g2d.setFont(f);
        g2d.drawString(FOOT_TXT, (float) (circleRadius + a - rectangle.getCenterX()), (float) (circleRadius * 1.5 + a - rectangle.getCenterY()));
    }


    /**
     * 画中心的 ★
     *  
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawStar(Graphics2D g2d, int circleRadius, int CANVAS_SIZE) {
        Font f = new Font(FONT_FAMILY, Font.PLAIN, CANVAS_SIZE / 3);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle = f.getStringBounds(STAR, context);
        g2d.setFont(f);
        g2d.drawString(STAR, (float) (circleRadius + 10 - rectangle.getCenterX()), (float) (circleRadius + 10 - rectangle.getCenterY()));
    }

    /**
     *  * 画外圈圆
     *  
     *
     * @param g2d
     * @param circleRadius
     */
    private static void drawCicle(Graphics2D g2d, int circleRadius) {
        g2d.setPaint(Color.red);
        int lineSize = circleRadius * 2 / (35); // 圆线条粗细是圆直径的1/35
        g2d.setStroke(new BasicStroke(lineSize));
        Shape circle = new Arc2D.Double(10, 10, circleRadius * 2, circleRadius * 2, 0, 360, Arc2D.OPEN);
        g2d.draw(circle);
    }
}
