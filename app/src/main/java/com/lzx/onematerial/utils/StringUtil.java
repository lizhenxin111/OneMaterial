package com.lzx.onematerial.utils;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lizhenxin on 17-11-19.
 */

public class StringUtil{
    public static String getJsFromFile(String fileName){
        String js = null;
        try {
            InputStream inputStream = MyApp.getContext().getAssets().open(fileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
            js = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return js;
    }

    public static boolean isNull(String content) {
        if (content == null) {
            return true;
        }
        return "".equals(content.trim());
    }
}
