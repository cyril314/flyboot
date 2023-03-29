package com.fit.core.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.*;

/**
 * @AUTO
 * @Author Aim
 * @DATE 2018/5/18
 */
public class ItextPDFUtil {

    public static void main(String[] args) throws FileNotFoundException {
        //方法一
        String[] str = {"123456789", "TOP__ONE", "男", "1991-01-01", "130222111133338888", "河北省保定市"};
        byte[] bytes = fillTemplate("", str);
        System.out.println(bytes);

        //方法二
        String creatPath = "";
        OutputStream os = new FileOutputStream(creatPath);
        createPDF(os);
    }

    /**
     * 利用模板生成pdf
     *
     * @param templatePath 模板路径
     */
    public static byte[] fillTemplate(String templatePath, String[] str) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        try {
            PdfReader reader = new PdfReader(templatePath);// 读取pdf模板
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            int i = 0;
            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next().toString();
                form.setField(name, str[i++]);
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, ous);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            System.out.println(1);
        } catch (Exception e) {
            System.out.println(2);
        }
        return ous.toByteArray();
    }

    /**
     * 生成pdf
     */
    public static void createPDF(OutputStream os) {
        com.lowagie.text.Document document = new com.lowagie.text.Document();
        try {
            com.lowagie.text.pdf.BaseFont bf = com.lowagie.text.pdf.BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//创建字体
            //自定义字体
            //bf = com.lowagie.text.pdf.BaseFont.createFont("font/华康少女文字W5(P).TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//华康少女文字
            com.lowagie.text.Font font = new com.lowagie.text.Font(bf, 12);//使用字体
            com.lowagie.text.pdf.PdfWriter.getInstance(document, os);
            document.open();
            document.add(new com.lowagie.text.Paragraph("hello word", font));
            document.close();
        } catch (Exception e) {
            System.out.println("file create exception");
        }
    }
}
