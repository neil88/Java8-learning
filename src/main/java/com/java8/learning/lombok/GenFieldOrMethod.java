package com.java8.learning.lombok;

import lombok.extern.slf4j.Slf4j;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

@Slf4j
public class GenFieldOrMethod {


    public static void main(String args[]) throws IntrospectionException {
//        obtainMethods(Human.class);
//        System.out.println("--------------------------");
        Human h = new Human(null,null,null,null);
        System.out.println(h.getClass());
        System.out.println(Human.class);
        setValueIfFieldIsNull(h, Human.class);

    }

    public static <T> void obtainMethods(Class<T> className) {
        Method[] methods = className.getDeclaredMethods();

        for (Method method : methods) {
            StringBuilder sbuil = new StringBuilder("[toGenericString]:").append(method.toGenericString()).append(" | ")
                    .append("[getModifiers]").append(method.getModifiers()).append(" | ")
                    .append("[getReturnType]").append(method.getReturnType()).append(" | ")
                    .append("[getParameterTypes]").append(method.getParameterTypes()).append(" | ")
                    .append("[getGenericExceptionTypes]").append(method.getGenericExceptionTypes()).append(" | ")
                    .append("[getAnnotations]").append(method.getAnnotations()).append(System.lineSeparator());
            System.out.println(sbuil.toString());
        }
    }


    public static <T> void setValueIfFieldIsNull(T t, Class<T> clazz) {

        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field f : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
                System.out.println(pd.getName());

                Object value = pd.getReadMethod().invoke(t);
                if ( value == null ) {
                    if(f.getType().equals(String.class)){
                        System.out.println("-- String type. --");
                    }

                    if ( f.getType().getName().equals("java.lang.String") ) {
                        pd.getWriteMethod().invoke(t, "");
                    } else if ( f.getType().getName().equals("java.math.BigDecimal") ) {
                        pd.getWriteMethod().invoke(t, BigDecimal.ZERO);
                    }
                }

            }
        } catch (Exception e) {
            log.error("Get field failure.", e);
        }
    }

    public static String toLowerCaseFirstOne(String s) {
        if ( Character.isLowerCase(s.charAt(0)) ) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    public static <T> void getFieldStr(String startStr, Class<T> className,
                                       String endStr) {
        Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(startStr + field.getName() + endStr);
        }
    }



    public static <T> void obtainSetMethods(String startStr, Class<T> className) {
        Method[] methods = className.getDeclaredMethods();

        for (Method method : methods) {
            if ( method.getName().startsWith("set") ) {
                System.out.println(startStr + method.getName() + "();");
            }
        }
    }

    public static <T> void obtainGetMethods(String startStr, Class<T> className) {
        Method[] methods = className.getDeclaredMethods();
        for (Method method : methods) {
            if ( method.getName().startsWith("get") ) {
                System.out.println(startStr + method.getName() + "()");
            }
        }
    }

    public static <T> void getSetValueStr(String setObject, String getObject,
                                          Class<T> className) throws IntrospectionException {
        Field[] fields = className.getDeclaredFields();
        for (Field f : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),
                    className);
            Method wM = pd.getWriteMethod();
            Method rM = pd.getReadMethod();
            System.out.println(setObject + "." + wM.getName() + "(" + getObject
                    + "." + rM.getName() + "());");
        }
    }

    public static <T> void getSetValueIfNullSetStr(String setObject,
                                                   String getObject, Class<T> className) throws IntrospectionException {
        Field[] fields = className.getDeclaredFields();
        for (Field f : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),
                    className);
            Method wM = pd.getWriteMethod();
            Method rM = pd.getReadMethod();
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append(
                    "if(" + setObject + "." + rM.getName() + "()==null){\r");
            sBuilder.append("\t" + setObject + "." + wM.getName() + "();\r");
            sBuilder.append("}\r\n");
            System.out.println(sBuilder.toString());
        }
    }
}
