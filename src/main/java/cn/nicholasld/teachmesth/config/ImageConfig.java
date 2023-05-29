package cn.nicholasld.teachmesth.config;

import cn.nicholasld.teachmesth.TeachMeSth;

import java.io.InputStream;

/**
 * @author NicholasLD
 * @createTime 2023/5/29 21:08
 */
public class ImageConfig {
    public static String EMOJI_FILE_PATH = "/Users/mac/Code/MyProjects/TeachMeSth/Image/teachme.png";
    public static String FONT_FILE_PATH = "/Users/mac/Code/MyProjects/TeachMeSth/Image/font.otf";

    public static InputStream getImage(){
        try{
            return TeachMeSth.class.getClassLoader().getResourceAsStream("teachme.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getFont(){
        try{
            return TeachMeSth.class.getClassLoader().getResourceAsStream("font.otf");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
