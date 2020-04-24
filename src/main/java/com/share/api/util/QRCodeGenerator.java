package com.share.api.util;

import cn.hutool.http.HttpStatus;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.share.api.exception.UnifiedException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static String getBarCode(String msg, int width, int height, String path) {
        try {
            path = path + System.currentTimeMillis() + ".png";
            File file = new File(path);
            OutputStream ous = new FileOutputStream(file);
            String format = "png";
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, String> map = new HashMap<>();
            //设置编码 EncodeHintType类中可以设置MAX_SIZE， ERROR_CORRECTION，CHARACTER_SET，DATA_MATRIX_SHAPE，AZTEC_LAYERS等参数
            map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            map.put(EncodeHintType.MARGIN, "2");
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE, width, height, map);
            MatrixToImageWriter.writeToStream(bitMatrix, format, ous);
        } catch (Exception e) {
            throw new UnifiedException("导出二维码错误", e);
        }
        return path;
    }

    public static void main(String[] args) {
        String msg = "pillar666";
        String path = "D:\\" + System.currentTimeMillis() + ".png";
        QRCodeGenerator.getBarCode(msg, 300, 300, path);
    }
}
