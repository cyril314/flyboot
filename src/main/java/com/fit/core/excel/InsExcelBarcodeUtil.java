package com.fit.core.excel;

import org.springframework.util.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author Aim
 * @Des
 * @DATE 2018/5/17
 */
public class InsExcelBarcodeUtil {

    /**
     * 生成条码，并返回图片流字符串
     *
     * @param barcodeValue 要生成条形码的数据
     */
    public static byte[] makeBarcode(final String barcodeValue) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(barcodeValue, ous);
        return ous.toByteArray();
    }


    /**
     * 生成到数据流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
        Code39Bean bean = new Code39Bean();
        final int dpi = 1500;
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
        // 宽度
        bean.setWideFactor(28);
        // 高度
        bean.setHeight(8);
        // 条形码左右两边是否留空白，默认为true
        bean.doQuietZone(true);
        // 设置条码号字体的大小
        bean.setFontSize(3);
        // 设置条码号显示的位置
        bean.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);

        String format = "image/png";
        try {
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            // 生成条形码
            bean.generateBarcode(canvas, msg);
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 重新设置图片的大小
     * @Param: [wb, patriarch, rowIndex, data]
     * @Return: void
     */
    public static void drawPictureInfoExcel(Workbook wb, XSSFDrawing patriarch, int rowIndex, byte[] data) {

        try {
            // anchor主要用于设置图片的属性
            //  参数  说明
            //  dx1  第1个单元格中x轴的偏移量
            //  dy1  第1个单元格中y轴的偏移量
            //  dx2  第2个单元格中x轴的偏移量
            //  dy2  第2个单元格中y轴的偏移量
            //  col1 第1个单元格的列号
            //  row1 第1个单元格的行号
            //  col2 第2个单元格的列号
            //  row2 第2个单元格的行号
            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) 8, rowIndex, (short) 8, rowIndex);
            //Sets the anchor type （图片在单元格的位置）
            //0 = Move and size with Cells, 2 = Move but don't size with cells, 3 = Don't move or size with cells.
            anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_DO_RESIZE);
            XSSFPicture picture = patriarch.createPicture(anchor, wb.addPicture(data, Workbook.PICTURE_TYPE_JPEG));
            picture.resize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
