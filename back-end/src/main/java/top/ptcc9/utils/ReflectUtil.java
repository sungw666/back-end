package top.ptcc9.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtil {

    public static List<Class<?>> getClassesByPackage(String packageName) throws ClassNotFoundException {
        String packagePath = "E:\\bus\\代码生成器\\back-end\\target\\classes\\" + packageName.replace(".", "\\") + "\\";
        File packageDirectory = new File(packagePath);
        File[] files = packageDirectory.listFiles();
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                Class<?> clazz = Class.forName(className);
                classes.add(clazz);
            }
        }
        return classes;
    }

    public static String getFieldType(Class<?> className,String fieldName) {
        Field[] declaredFields = className.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field.getType().getSimpleName();
            }
        }
        throw new RuntimeException("根据field判断类型时找不到匹配类型");
    }

    public static boolean isContain(Class<?> className,String fieldName) {
        Field[] declaredFields = className.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
