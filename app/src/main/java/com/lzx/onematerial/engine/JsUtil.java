package com.lzx.onematerial.engine;

/**
 * Created by lizhenxin on 17-11-15.
 */

public class JsUtil {
    public static final String REMOVE_ARTICLE = "javascript:function remove(){\n" +
            "    var a = document.querySelector(\"div.header\");\n" +
            "    a.parentNode.removeChild(a);\n" +
            "    var c = document.querySelector(\"div.footer\");\n" +
            "    c.parentNode.removeChild(c);\n" +
            "    var f = document.querySelector(\"img.one-image-exclude\");\n" +
            "    f.parentNode.removeChild(f);\n" +
            "}";
}
