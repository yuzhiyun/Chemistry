package com.yuzhiyun.chemistry.model.util;

import com.yuzhiyun.chemistry.model.entity.bmobEntity.User;

import java.util.List;

/**
 * Created by yuzhiyun on 2016-07-21.
 */
public class CONSTANT {
//    章
    public static String[] array_data = {
            "第一章 电解质溶液",
            "第二章 缓冲溶液"
//            "第三章 酸碱滴定法",
//            "第四章 化学反应热及化学反应的方向及限度",
//            "第五章 化学反应速率",
//            "第六章 氧化还原及电极电位",
//            "第七章 原子结构与原子周期律",
    };
//    题型
    public static String[] type = {
            "选择题",
//            "名词解释题",
//            "简答题",
//            "计算题",
            "判断题"
    };
    //    表名
    public static String[] table={
            "choiceQuestion",
//            "nounExplanationQuestion",
//            "briefAnswerQuestion",
//            "calculateQuestion",
            "judgementQuestion"};
    public static String BMOB_APP_ID="a1a600ee66c4e8af2871fdbe17cf881b";
    public static List<User> userList;
    public static int chapter=0;

}
