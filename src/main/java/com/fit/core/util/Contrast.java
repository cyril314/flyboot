package com.fit.core.util;

import com.fit.core.common.constant.dictmap.base.AbstractDictMap;
import com.fit.core.common.constant.dictmap.factory.DictFieldWarpperFactory;
import com.fit.core.support.StrKit;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 对比两个对象的变化的工具类
 *
 * @author Aim
 * @Date 2017/3/31 10:36
 */
public class Contrast {

    //记录每个修改字段的分隔符
    public static final String separator = ";;;";

    /**
     * 比较两个对象,并返回不一致的信息
     *
     * @author Aim
     * @Date 2017/5/9 19:34
     */
    public static String contrastObj(Object pojo1, Object pojo2) {
        String str = "";
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = getMethod.invoke(pojo2);
                if (o1 == null || o2 == null) {
                    continue;
                }
                if (o1 instanceof Date) {
                    o1 = DateUtil.getDay((Date) o1);
                }
                if (!o1.toString().equals(o2.toString())) {
                    if (i != 1) {
                        str += separator;
                    }
                    str += "字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 比较两个对象pojo1和pojo2,并输出不一致信息
     *
     * @author Aim
     * @Date 2017/5/9 19:34
     */
    public static String contrastObj(Class dictClass, String key, Object pojo1, Map<String, String> pojo2) throws IllegalAccessException, InstantiationException {
        AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
        String str = parseMutiKey(dictMap, key, pojo2) + separator;
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = pojo2.get(StrKit.firstCharToLowerCase(getMethod.getName().substring(3)));
                if (o1 == null || o2 == null) {
                    continue;
                }
                str += changeParam(field, dictMap, str, i, o1, o2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 比较两个对象pojo1和pojo2,并输出不一致信息
     *
     * @author Aim
     * @Date 2017/5/9 19:34
     */
    public static String contrastObjByName(Class dictClass, String key, Object pojo1, Map<String, String> pojo2) throws IllegalAccessException, InstantiationException {
        AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
        String str = parseMutiKey(dictMap, key, pojo2) + separator;
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                String prefix = "get";
                int prefixLength = 3;
                if (field.getType().getName().equals("java.lang.Boolean")) {
                    prefix = "is";
                    prefixLength = 2;
                }
                Method getMethod = null;
                try {
                    getMethod = clazz.getDeclaredMethod(prefix + StrKit.firstCharToUpperCase(field.getName()));
                } catch (NoSuchMethodException e) {
                    System.err.println("this className:" + clazz.getName() + " is not methodName: " + e.getMessage());
                    continue;
                }
                Object o1 = getMethod.invoke(pojo1);
                Object o2 = pojo2.get(StrKit.firstCharToLowerCase(getMethod.getName().substring(prefixLength)));
                if (o1 == null || o2 == null) {
                    continue;
                }
                str += changeParam(field, dictMap, str, i, o1, o2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * @param field   声明字段
     * @param dictMap 字典映射
     * @param str     返回消息
     * @param i       循环次数
     * @param o1      旧对象
     * @param o2      新对象
     */
    private static String changeParam(Field field, AbstractDictMap dictMap, String str, int i, Object o1, Object o2) {
        if (o1 instanceof Date) {
            o1 = DateUtil.getDay((Date) o1);
        } else if (o1 instanceof Integer) {
            if (!StringUtils.isEmpty(o2)) {
                o2 = Integer.parseInt(o2.toString());
            }
        }
        if (!o1.toString().equals(o2.toString())) {
            if (i != 1) {
                str += separator;
            }
            String fieldName = dictMap.get(field.getName());
            String fieldWarpperMethodName = dictMap.getFieldWarpperMethodName(field.getName());
            if (fieldWarpperMethodName != null) {
                Object o1Warpper = DictFieldWarpperFactory.createFieldWarpper(o1, fieldWarpperMethodName);
                Object o2Warpper = "";
                if (!StringUtils.isEmpty(o2)) {
                    o2Warpper = DictFieldWarpperFactory.createFieldWarpper(o2, fieldWarpperMethodName);
                }
                str += "字段名称:" + fieldName + ",旧值:" + o1Warpper + ",新值:" + o2Warpper;
            } else {
                str += "字段名称:" + fieldName + ",旧值:" + o1 + ",新值:" + o2;
            }
            i++;
        }
        return str;
    }

    /**
     * 解析多个key(逗号隔开的)
     *
     * @author Aim
     * @Date 2017/5/16 22:19
     */
    public static String parseMutiKey(AbstractDictMap dictMap, String key, Map<String, String> requests) {
        StringBuilder sb = new StringBuilder();
        if (key.indexOf(",") != -1) {
            String[] keys = key.split(",");
            for (String item : keys) {
                String fieldWarpperMethodName = dictMap.getFieldWarpperMethodName(item);
                String value = requests.get(item);
                if (fieldWarpperMethodName != null) {
                    Object valueWarpper = DictFieldWarpperFactory.createFieldWarpper(value, fieldWarpperMethodName);
                    sb.append(dictMap.get(item) + "=" + valueWarpper + ",");
                } else {
                    sb.append(dictMap.get(item) + "=" + value + ",");
                }
            }
            return StrKit.removeSuffix(sb.toString(), ",");
        } else {
            String fieldWarpperMethodName = dictMap.getFieldWarpperMethodName(key);
            String value = requests.get(key);
            if (fieldWarpperMethodName != null) {
                Object valueWarpper = DictFieldWarpperFactory.createFieldWarpper(value, fieldWarpperMethodName);
                sb.append(dictMap.get(key) + "=" + valueWarpper);
            } else {
                sb.append(dictMap.get(key) + "=" + value);
            }
            return sb.toString();
        }
    }
}