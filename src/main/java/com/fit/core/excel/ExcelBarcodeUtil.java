package com.fit.core.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.*;

public class ExcelBarcodeUtil {

    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;
    private FileInputStream input;
    private String[] excleTitle;

    public static void main(String[] args) throws FileNotFoundException {
        //生成条码
        String bcVal = ExcelBarcodeUtil.makeBarcodeValue();
        //生成条码图片
        String bcPath = ExcelBarcodeUtil.makeBarcode(bcVal);
        System.out.println(bcPath);
        //插入excel
        ExcelBarcodeUtil.insBarcodeInExcel(bcPath);
    }

    //生成条码值
    public static String makeBarcodeValue() {
        return "A" + System.currentTimeMillis();
    }

    //生成条码文件至临时目录，并返回生成图片的路径信息
    public static String makeBarcode(final String barcodeValue) throws FileNotFoundException {
        //存放条码图片的路径
        final String barcodePicPath = "classpath:barcode\\";
        try {
            //Create the barcode bean
            Code39Bean bean = new Code39Bean();

            final int dpi = 150;

            //Configure the barcode generator
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
            //width exactly one pixel
            bean.setWideFactor(3);
            bean.doQuietZone(false);

            //Open output file
            File outputFile = new File(barcodePicPath + barcodeValue + ".png");
            OutputStream out = new FileOutputStream(outputFile);
            try {
                //Set up the canvas provider for monochrome JPEG output
                BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                        out, "image/png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

                //Generate the barcode
                bean.generateBarcode(canvas, barcodeValue);

                //Signal end of generation
                canvas.finish();
            } finally {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return barcodePicPath + barcodeValue + ".png";
    }

    public static void insBarcodeInExcel(String barcodePic) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(new File("d:\\\\base.xls"));// excelPath,Excel
            // 文件 的绝对路径
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(1);
            HSSFRow row = sheet.getRow(0);// 得到标题的内容对象。
            System.out.println(row.getCell(15).toString());

            InputStream inputStream = new FileInputStream(barcodePic);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            inputStream.close();
            CreationHelper helper = wb.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(15);
            anchor.setRow1(0);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize();
            FileOutputStream fileOut = new FileOutputStream("d:\\\\base.xls");
            wb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
