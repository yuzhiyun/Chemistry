package com.yuzhiyun.chemistry.model.util;

import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.Html;
import android.util.Log;

/**
 * Created by yuzhiyun on 2016-09-06.
 */
public class UtilHtml {

    /**把从数据库中提取出来的String 转换成html
     * 此处涉及很多字符串的处理，图片的文件名称用两个#包含，在第一个#后面加/以表示它是图片
     * 而不是文字
     * */
    public static StringBuilder createHtml(String question){
        //sd卡路径
        String IMG_PATH = Environment.getExternalStorageDirectory().getPath();

        //依据#将字符串分割
        String[] tokens = question.split("#", 0);
        StringBuilder html = new StringBuilder();
        for (int i = 0; i < tokens.length; i++)
            //约定图片的文件名以/开头，然后叠加到html中
            if (tokens[i].charAt(0) == '/')
                html.append("<img src=\"" +  IMG_PATH+ tokens[i] + "\"/>");
            else {
                html.append(tokens[i]);
            }
        return html ;
    }
    /***获取图片，
     * 以便使得html显示在textView中
     * */
    public static final Html.ImageGetter imageGetter = new Html.ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            drawable = Drawable.createFromPath(source);
            if (null != drawable)

                //由于显示的图片太小，所以我把它的长宽都变大3倍
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() * 3, drawable.getIntrinsicHeight() * 3);
            Log.e("getDrawable", source);
            return drawable;
        }
    };
}
