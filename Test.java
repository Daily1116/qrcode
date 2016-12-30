package com.caissa.operation.web.action.activity;

import com.caissa.operation.common.utils.QRCodeUtil;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/12/30.
 */

public class Test {
    public static void main(String[] args) {
        String logoUri = "images/qr_logo.jpg";
        //二维码中的内容
        String content="QrCode";
        //二维码尺寸
        int[]  size=new int[]{280,280};
        //输出格式
        String format = "jpg";
        try {
            OutputStream out = new FileOutputStream(new File("d:\\qr_image.jpg"));
            QRCodeUtil.getInstance().CreatQrImageToStream(content,format,out,size);
//            QRCodeUtil.getInstance().CreatQrImageToStreamWithLogo(content,format,logoUri,out,size);
        } catch (IOException | WriterException e) {
            System.out.println();
        }
    }
}

