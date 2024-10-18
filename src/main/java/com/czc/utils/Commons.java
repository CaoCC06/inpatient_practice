package com.czc.utils;

import com.czc.domain.Medicine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 页面数据展示封装类
 */
 @Component
public class Commons {
    /**
     * 网站链接
     *
     * @return
     */
    public static String site_url() {
        return site_url("/page/1");
    }
    /**
     * 返回网站链接下的全址
     *
     * @param sub 后面追加的地址
     * @return
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站配置项
     *
     * @param key
     * @return
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param key
     * @param defalutValue 默认值
     * @return
     */
    public static String site_option(String key, String defalutValue) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return defalutValue;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;
    }

    /**
     * 返回日期
     *
     * @return
     */
     public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
     }


    /**
     * 截取药品信息摘要
     *
     * @param medicine 药品信息
     * @param len   要截取文字的个数
     * @return
     */
    public static String intro(Medicine medicine, int len) {
        String value = medicine.getEfficacy();
        int pos = value.indexOf("<!--more-->");
        if (pos != -1) {
            String html = value.substring(0, pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 显示药品缩略图，顺序为：第一张图 -> 随机获取
     *
     * @return
     */
    public static String show_thumb(Medicine medicine) {

        if (StringUtils.isNotBlank(medicine.getPicture())){
            String path = "/images/"+medicine.getPicture();
            return path;
        }
        return "/client/img/1.jpg";
    }

}
