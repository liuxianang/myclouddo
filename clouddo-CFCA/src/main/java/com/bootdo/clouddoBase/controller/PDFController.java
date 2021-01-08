package com.bootdo.clouddoBase.controller;

import com.bootdo.clouddoBase.util.MyConst;
import com.bootdo.clouddoBase.util.SealUtils;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import com.itextpdf.text.pdf.*;
import org.springframework.web.bind.annotation.*;


import java.io.*;

import static com.bootdo.clouddoBase.util.SealUtils.byte2File;

@RestController
public class PDFController {

    @GetMapping("/make")
    public String makePdf() {
        // 模板路径
        String templatePath = "D:/CFCA/test.pdf";
        // 生成的新文件路径
        String newPDFPath = "D:/CFCA/test1.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(bfChinese);
            form.setField("debentures", "2465465145648456");
            form.setField("debenturesAddress", "aaaa");
         /*   form.setField("debenturesLegalRepresentative","222");
            form.setField("debenturesAddress","aaaa");*/
            form.setField("obligor", "啊啊");
          /*  form.setField("obligorLegalRepresentative","啊啊");
            form.setField("obligorLegalRepresentativeAddress","啊啊");
            form.setField("signYear","啊啊");
            form.setField("signMonth","啊啊");
            form.setField("signDay","啊啊");
            form.setField("protocol","啊啊");
            form.setField("contractNO","啊啊");
            form.setField("byYear","啊啊");
            form.setField("byMonth","啊啊");
            form.setField("byDay","啊啊");
            form.setField("amountOfMoney","啊啊");
            form.setField("amountInWords","啊啊");
            form.setField("repaymentYear","啊啊");
            form.setField("repaymentMonth","啊啊");
            form.setField("repaymentDay","啊啊");*/

            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
            System.out.println("bbbbbb");
        } catch (IOException e) {
            System.out.println(e);
        } catch (DocumentException e) {
            System.out.println(e);
        }

        return "aaaaa";

    }

    @GetMapping("/make2")
    public String make2Pdf() throws IOException, DocumentException {
        String templatePath = "D:/CFCA/test1.pdf";
        // 生成的文件路径
        String targetPath = "D:/CFCA/test2.pdf";
        // 图片路径
        String imagePath = "D:/CFCA/test.jpg";

        // 读取模板文件
        InputStream input = new FileInputStream(new File(templatePath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
        // 提取pdf中的表单
        AcroFields form = stamper.getAcroFields();
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

        // 通过域名获取所在页和坐标，左下角为起点


        // 读图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(1);
        // 根据域的大小缩放图片
        image.scaleToFit(100, 100);
        // 添加图片
        image.setAbsolutePosition(100, 100);
        under.addImage(image);

        stamper.close();
        reader.close();
        return "bbbbb";
    }

    @GetMapping("/make3")
    public String make3Pdf() throws IOException, DocumentException {

        byte2File(SealUtils.genSealData("江西银行部股份有限公司赣江新区支行", "投标保函业务专用章", 300), "D:\\test\\", 222 + ".png");
        return "bbbbb" + MyConst.Const;
    }

}