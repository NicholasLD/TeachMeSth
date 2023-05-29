package cn.nicholasld.teachmesth.implement;

import cn.nicholasld.teachmesth.config.ImageConfig;
import net.mamoe.mirai.utils.ExternalResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author NicholasLD
 * @createTime 2023/5/29 20:42
 */
public class TeachMeImpl {
    public static ExternalResource getReplyImage(String teachContent) throws FileNotFoundException {
        BufferedImage emojiImage = null;
        try {
            if (ImageConfig.getImage() == null) {
                throw new FileNotFoundException("Image file not found");
            }
            emojiImage = ImageIO.read(ImageConfig.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (emojiImage != null) {
            Graphics2D graphics = emojiImage.createGraphics();
            Font font = null;
            try {
                if (ImageConfig.getFont() == null) {
                    throw new FileNotFoundException("Font file not found");
                }
                font = Font.createFont(Font.TRUETYPE_FONT, ImageConfig.getFont());
                font = font.deriveFont(32f); // 设置字体大小
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            if (font != null) {
                FontMetrics fm = graphics.getFontMetrics(font);
                int textWidth = fm.stringWidth("教我"+teachContent);
                if (textWidth > 150) {
                    font = font.deriveFont(32f * 150 / textWidth);
                }
                graphics.setFont(font);
                graphics.setColor(Color.BLACK);
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
                graphics.setClip(5, 100, 150, 50);
                if (font.getSize() == 32f) {
                    graphics.drawString("教我"+teachContent, (150 - textWidth) / 2, 136);
                }else{
                    graphics.drawString("教我"+teachContent, 5, 136);
                }
                //释放资源
                graphics.dispose();


                // 创建临时文件
                File tempFile = null;
                try {
                    tempFile = File.createTempFile("textemoji", ".png");
                    ImageIO.write(emojiImage, "png", tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (tempFile != null) {
                    return ExternalResource.create(tempFile);
                }
            }
        }
        return null;
    }
}
