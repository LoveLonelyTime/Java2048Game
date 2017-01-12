package com.github.lovelonelytime.java2048game;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 语言资源加载器
 * 
 * @author LoveLonelyTime
 */
public class LanguageLoader {
    /**
     * 语言资源加载器
     */
    private static final ResourceBundle LANG_RESOURCE_BUNDLE = ResourceBundle.getBundle("langs/lang",
            Locale.getDefault());

    /**
     * 获取字符串
     * 
     * @param key
     *            键
     * @return 字符串
     */
    public static String getString(String key) {
        return LanguageLoader.LANG_RESOURCE_BUNDLE.getString(key);
    }
}
