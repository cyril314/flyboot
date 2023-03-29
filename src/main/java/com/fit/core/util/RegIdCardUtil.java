package com.fit.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Aim
 * @Des 正则截取身份证
 * @DATE 2018/5/14
 */
public class RegIdCardUtil {


    public static void main(String[] args) {

        String test = "5.6单号已废弃 4.10无货退款 3.20客户就要这个颜色愿意继续等 3.20库存有一个棕拼橙色，已经通知到客户是否换颜色 做单蜜蜂B sfz622126198810171649 3.8顾客不要口红红~还想继续想等 3.8姐夫通知有口红红问下换款";
        System.out.println(RegIdCardUtil.getAllValue(test));

        System.out.println(RegIdCardUtil.getAllValue("做单蜜蜂B sfz440583199402111661(温爱华)"));
        System.out.println(RegIdCardUtil.getAllValue("做单蜜蜂B sfz440583199402111661(温爱华)"));
        System.out.println(RegIdCardUtil.getNameValue(RegIdCardUtil.getAllValue("做单蜜蜂B sfz440583199402111661(温爱华)")));
        System.out.println(RegIdCardUtil.getCardValue(RegIdCardUtil.getAllValue("做单蜜蜂B sfz440583199402111661(温爱华)")));
        System.out.println(RegIdCardUtil.getAllValue("做单蜜蜂B sfz63010419790509054X"));
    }

    public static final String AllIdCard = "\\d{6}((19|20)\\d{2})((0[0-9])|(1[0-2]))((((0|1|2)[0-9])|(3[0,1]))\\d{3}[xX\\d](\\(.*?\\))|(((0|1|2)[0-9])|(3[0,1]))\\d{3}[xX\\d])";
    public static final String IdCard = "\\d{6}((19|20)\\d{2})((0[0-9])|(1[0-2]))(((0|1|2)[0-9])|(3[0,1]))\\d{3}[xX\\d]";
    public static final String CardName = "(?<=\\().*(?=\\))";

    /**
     * 正则获取完整IdCard信息
     *
     * @param values
     */
    public static String getAllValue(String values) {
        return getRegValue(values, RegIdCardUtil.AllIdCard);
    }

    /**
     * 正则获取完整IdCard名字信息
     *
     * @param values
     */
    public static String getNameValue(String values) {
        return getRegValue(values, RegIdCardUtil.CardName);
    }

    /**
     * 正则获取完整IdCard信息
     *
     * @param values
     */
    public static String getCardValue(String values) {
        return getRegValue(values, RegIdCardUtil.IdCard);
    }

    /**
     * 根据正则表达式获取内容信息
     *
     * @param values
     * @param reg
     */
    public static String getRegValue(String values, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(values);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }

}
