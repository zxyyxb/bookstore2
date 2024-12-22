package cn.edu.ecnu.stu.bookstore.utils;

import cn.edu.ecnu.stu.bookstore.component.AppException;
import cn.edu.ecnu.stu.bookstore.component.Constants;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;

public class ImageUtil {

    /**
     * 图片转Base64字符串
     * @param imageFileName
     * @return
     */
    public static String convertImageToBase64Str(String imageFileName) {
        ByteArrayOutputStream baos = null;
        try {
            //获取图片类型
            String suffix = imageFileName.substring(imageFileName.lastIndexOf(".") + 1);
            //构建文件
            File imageFile = new File("pictures/" + imageFileName);
            //通过ImageIO把文件读取成BufferedImage对象
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            //构建字节数组输出流
            baos = new ByteArrayOutputStream();
            //写入流
            ImageIO.write(bufferedImage, suffix, baos);
            //通过字节数组流获取字节数组
            byte[] bytes = baos.toByteArray();
            //获取JDK8里的编码器Base64.Encoder转为base64字符
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Base64字符串转图片
     * @param base64String
     */
    public static String convertBase64StrToImage(String base64String) {
        ByteArrayInputStream bais = null;
        try {
            //获取图片类型
            String suffix = "png";
            //获取JDK8里的解码器Base64.Decoder,将base64字符串转为字节数组
            byte[] bytes = Base64.getDecoder().decode(base64String);
            //构建字节数组输入流
            bais = new ByteArrayInputStream(bytes);
            //通过ImageIO把字节数组输入流转为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(bais);
            String fileName = UUID.randomUUID() + "." + suffix;
            String fullPath = "pictures/" + fileName;
            //构建文件
            File imageFile = new File(fullPath);
            //写入生成文件
            ImageIO.write(bufferedImage, suffix, imageFile);
            return fileName;
        } catch (Exception e) {
            throw new AppException(Constants.CLIENT_ERROR, Constants.PICTURE_ERROR);
        } finally {
            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

