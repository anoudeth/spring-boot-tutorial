package com.noh.qr.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class QrCode {

    private static final int qrcolor = 0xff000000; // default is black
    private static final int bgwhite = 0xFFFFFFFF; // background color
    private static final int width = 300; // QR code width
    private static final int height = 300; // QR code height

    private static final Logger logger = LoggerFactory.getLogger(QrCode.class);
    private static final String rootPath = "./QRCodeImage";
    private static final String QR_CODE_IMAGE_PATH =  "/QR.png";

    public static void main(String[] args) throws Exception{
        QrCode qrCode = new QrCode();
//        String fileName = qrCode.genQRCode("a", "hello4", 200, 200);
//        System.out.println(fileName);
//        qrCode.qrWithLogo();
        qrCode.createCode("abc");
    }
    /**
     * Generate QR
     * @param text
     * @param width
     * @param height
     */
    public URI generateQRCodeImage(String text, int width, int height) {
        Path path = null;
        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            path = FileSystems.getDefault().getPath(rootPath + QR_CODE_IMAGE_PATH);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException e) {
            logger.warn("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            logger.error("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        return path.toUri();
    }

    /**
     *
     * @param text
     * @param width
     * @param height
     * @return
     */
    public String genQRCode(String newPath, String text, int width, int height) {

        Path path = null;
        String imageName = null;

        Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0); /* default = 4 */

        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);



//            imageName = QR_CODE_IMAGE_PATH;
            imageName = text + ".png";
            File file = new File(rootPath + "/" + newPath);
            file.mkdirs();
            String fileName = file.getPath() + "/" + imageName;
            logger.info("fileName : " + fileName);
//            path = FileSystems.getDefault().getPath( global.getQrCodePath() + imageName);
            path = FileSystems.getDefault().getPath(fileName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException e) {

            logger.warn("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {

            logger.error("Could not generate QR Code, IOException :: " + e.getMessage());
        }

        return imageName;
    }

    public void qrWithLogo() throws Exception {
        String content = "00001";
//        String pathToStore = "C:\\Tools\\QRCodeGenerated.jpg";
        String pathToStore = rootPath + "/test.jpg";

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 400, 400);
        MatrixToImageConfig imageConfig = new MatrixToImageConfig(MatrixToImageConfig.BLACK, MatrixToImageConfig.WHITE);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, imageConfig);
        // Getting logo image
//        BufferedImage logoImage = ImageIO.read( new File("C:\\Tools\\logo2.png"));
        BufferedImage logoImage = ImageIO.read( new File("./QRCodeImage/B/noh.jpg"));
        int finalImageHeight = qrImage.getHeight() - logoImage.getHeight();
        int finalImageWidth = qrImage.getWidth() - logoImage.getWidth();
        //Merging both images
        BufferedImage finalImage = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) finalImage.getGraphics();
        graphics.drawImage(qrImage, 0, 0, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics.drawImage(logoImage, (int) Math.round(finalImageWidth / 2), (int) Math.round(finalImageHeight / 2), null);

        ImageIO.write(finalImage, "png", new File(pathToStore));

        System.out.println("QR Code with Logo Generated Successfully");

    }

    /**
     * This method takes the text to be encoded, the width and height of the QR Code,
     * and returns the QR Code in the form of a byte array.
     * @param text
     * @param width
     * @param height
     * @return
     */
    public byte[] getQRCodeImage(String text, int width, int height) {

        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return pngData;

        } catch (WriterException e) {
            logger.warn("Could not generate QR Code, WriterException :: " + e.getMessage());
            return null;
        } catch (IOException e) {
            logger.error("Could not generate QR Code, IOException :: " + e.getMessage());
            return null;
        }
    }


    public void createCode(String  dirName) throws WriterException {
        File logoFile = new File("./QRCodeImage/noh.jpg");
        File QrCodeFile = new File(rootPath+ "/" + dirName);
        String url = "https://www.baidu.com/";
        String note = "visit Baidu connection";

        System.out.println(QrCodeFile.getPath());
        System.out.println(QrCodeFile.exists());
        if(!QrCodeFile.exists()) {
            QrCodeFile.mkdirs();
        }

        for (int i=0; i < 10; i++) {
            System.out.println("gen qr: "+ (i));
//            path = FileSystems.getDefault().getPath("/0000"+(i)+".png");
//            drawLogoQRCode(logoFile, new File(QrCodeFile + "/0000"+(i)+".png"), "0000"+i, "IFS0000"+i);
            drawLogoQRCode(logoFile, new File(rootPath+ "/" + dirName + "/0000"+(i)+".png"), "0000"+i, "0000"+i);
        }

        System.out.println(">>> gen qr finished");
        System.out.println("> start zipping");

    }

    //Used to set QR QR QR code parameters
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            put (EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // set the error correction level of QR QR code (H is the highest level). Specific level information
            put (EncodeHintType.CHARACTER_SET, "UTF-8"); // set the encoding method
            put(EncodeHintType.MARGIN, 0);
        }
    };

    //Generate QR code image with logo
    public static void drawLogoQRCode(File logoFile, File codeFile, String qrUrl, String note) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            //The order of parameters are: coding content, coding type, width of generated picture, height of generated picture, and parameter setting
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //Start to create bitmap image with QR code data, set black (0xFFFFFF) and white (0xff000000) as two colors respectively
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? qrcolor : bgwhite);
                }
            }

            int width = image.getWidth();
            int height = image.getHeight();
            if (Objects.nonNull(logoFile) && logoFile.exists()) {
                //Building drawing objects
                Graphics2D g = image.createGraphics();
                //Read logo picture
                BufferedImage logo = ImageIO.read(logoFile);
                //Start drawing logo picture
                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
                g.dispose();
                logo.flush();
            }

            //Custom text description
            if (StringUtils.isNotEmpty(note)) {
                //For new pictures, add text under the QR code with logo
                BufferedImage outImage = new BufferedImage(300, 335, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                //Draw QR code to new panel
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                //Draw text to new panel
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("TimesRoman", Font.BOLD, 30)); // font, font type, font size
                int strWidth = outg.getFontMetrics().stringWidth(note);
                if (strWidth > 299) {
                    //// if the length is too long, cut off the front part
                    //Wrap if the length is too long
                    String note1 = note.substring(0, note.length() / 2);
                    String note2 = note.substring(note.length() / 2, note.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(note1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(note2);
                    outg.drawString(note1, 150 - strWidth1 / 2, height + (outImage.getHeight() - height) / 2 + 10);
                    BufferedImage outImage2 = new BufferedImage(300, 375, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont (new Font ("TimesRoman", Font.BOLD, 30)); // font, font type, font size
                    outg2.drawString(note2, 150 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(note, 150 - strWidth / 2, height + (outImage. getHeight() - height) / 2 + 10); // draw text
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }

            image.flush();

            ImageIO.write(image, "png", codeFile);
            System.out.println("generation of QR code completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
