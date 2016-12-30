package com.caissa.operation.common.utils;

import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/*
  二维码的生成 带logo处理
  Created by Administrator on 2016/12/20
 */
public class MatrixToImageWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixToImageWriter.class);

    private static final int BLACK = 0xFF000000;//用于设置图案的颜色
    private static final int WHITE = 0xFFFFFFFF; //用于背景色

    private MatrixToImageWriter() {
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
//                image.setRGB(x, y,  (matrix.get(x, y) ? Color.YELLOW.getRGB() : Color.CYAN.getRGB()));
            }
        }
        return image;
    }

    /**
     * 带logo输出到文件
     * @param matrix
     * @param format
     * @param file
     * @param logUri
     * @throws IOException
     */
    public static void writeToFileWithLogo(BitMatrix matrix, String format, File file, String logUri) throws IOException {

        System.out.println("write to file");
        BufferedImage image = toBufferedImage(matrix);
        //设置logo图标
        QRCodeUtil logoConfig = new QRCodeUtil();
        image = logoConfig.setMatrixLogo(image, logUri);

        if (!ImageIO.write(image, format, file)) {
            LOGGER.error("write qr image error");
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }else{
            LOGGER.info("write qr image success");
        }
    }

    /**
     * 不带logo输出到文件
     * @param matrix
     * @param format
     * @param file
     * @throws IOException
     */
    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {

        System.out.println("write to file");
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            LOGGER.error("write qr image error");
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }else{
            LOGGER.info("write qr image success");
        }
    }

    /**
     * 带logo输出到流
     * @param matrix
     * @param format
     * @param stream
     * @param logUri
     * @throws IOException
     */
    public static void writeToStreamWithLogo(BitMatrix matrix, String format, OutputStream stream, String logUri) throws IOException {

        BufferedImage image = toBufferedImage(matrix);

        //设置logo图标
        QRCodeUtil logoConfig = new QRCodeUtil();
        image = logoConfig.setMatrixLogo(image, logUri);

        if (!ImageIO.write(image, format, stream)) {
            LOGGER.error("write qr image error");
            throw new IOException("Could not write an image of format " + format);
        }
    }

    /**
     * 不带logo输出到流
     * @param matrix
     * @param format
     * @param stream
     * @throws IOException
     */
    public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {

        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, stream)) {
            LOGGER.error("write qr image error");
            throw new IOException("Could not write an image of format " + format);
        }
    }
}
