package top.ptcc9.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.lang.Nullable;
import top.ptcc9.exceptions.IllegalParameterException;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;


public class CommonUtil {
    private static String resourcePath = "";
    private static String mainPackagePath = "";

    static {
        try {
            resourcePath = new File("src/main/resources").getCanonicalPath();
            mainPackagePath = new File("src/main/java/top/ptcc9").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static String getResourcePath() {
        return resourcePath;
    }

    public static String getMainPackagePath() {
        return mainPackagePath;
    }

    public static long stringParseTimestamp(String time,String format) {
        return DateUtil.parse(time,format).getTime();
    }

    public static long stringParseTimestamp(String time) {
        return stringParseTimestamp(time,"yyyy-MM-dd HH:mm:ss");
    }


    public static long getEndOfDay(String time,String format) {
        return stringParseTimestamp(time, format) + ONE_DAY;
    }

    public static long getEndOfDay(String time) {
        return getEndOfDay(time, "yyyy-MM-dd HH:mm:ss");
    }



    public static long getSimpleDateTime() {
        return System.currentTimeMillis();
    }


    public static DateTime simpleDate2DateTime(long simpleDate) {
        return DateUtil.date(simpleDate);
    }


    public static String simpleDate2DateTimeAsString(long simpleDate) {
        return simpleDate2DateTime(simpleDate).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_DAY = 86400000L;
    public static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * 计算两个日期相差绝对值  实现 n分钟前  n小时前  n天前  n周前  n月前  n年前
     *
     * @param targetTime 目标时间 格式：20201207235959 非时间戳
     * @return
     */
    public static String getBetweenDescription(long targetTime) {
        DateTime targetTimeAsDateTime = simpleDate2DateTime(targetTime);
        long startTimestamp = targetTimeAsDateTime.getTime();
        long endTimestamp = DateTime.now().getTime();

        long delta = endTimestamp - startTimestamp;
        if (delta < ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        } else if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        } else if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        } else if (delta < 48L * ONE_HOUR) {
            return "昨天";
        } else if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        } else if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    /**
     * 0550a1e6904041afb79810ad9edc1ac0
     *
     * @return
     */
    public static String getSimpleUUID() {
        return IdUtil.simpleUUID();
    }


    public static <E> String list2Str(Collection<E> list) {
        if (isEmpty(list)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int index = 0;
        final int lastIndex = list.size() - 1;
        for (E e : list) {
            builder.append(e);
            if (index != lastIndex) {
                builder.append(",");
            }
            index++;
        }
        return String.valueOf(builder);
    }



    public static <E> boolean isNull(E e) {
        return e == null;
    }



    public static String trimEmpty2Null(String str) {
        if (isNull(str) || str.isEmpty()) {
            str = null;
        }
        return str;
    }

    public static boolean isEmpty(String str) {
        return isNull(str) || str.isEmpty();
    }

    public static <E> boolean isEmpty(Collection<E> list) {
        return isNull(list) || list.size() == 0;
    }



    public static boolean isEmptyMatchAny(String... str) {
        for (String item : str) {
            if (isEmpty(item)) {
                return true;
            }
        }
        return false;
    }



    public static String hidePhone(String phone) {
        return phone.substring(0,3) + "****" + phone.substring(7);
    }


    public static String hideBankCard(String bankCardNum) {
        return "**********" + bankCardNum.substring(bankCardNum.length() - 4);
    }


    public static String hideName(String name) {
        return name.substring(0,1) + "**";
    }


    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) throws UnknownHostException {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                inet = InetAddress.getLocalHost();
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static void verifyEmptyParam(String target,String message) {
        if (CommonUtil.isEmpty(target)) {
            throw new IllegalParameterException(message);
        }
    }

    public static <T extends RuntimeException> void checkTrue(boolean flag,Class<T> className,String message) {
        try {
            if (flag) {
                throw className.getConstructor(String.class).newInstance(message);
            }
        }catch (IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private static final DecimalFormat decimalFormat = new DecimalFormat("000");

    public static String integerFillWith0(int num) {
        return decimalFormat.format(num);
    }

    /**
     * 将对象转换为map
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public static <T> Map<String,Object> obj2Map(T t) {
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);
            try {
                Object o = declaredField.get(t);
                declaredField.setAccessible(false);
                if (o == null) continue;
                map.put(declaredField.getName(),o);
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String str2DatabaseType(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            boolean isUpper = false;
            char cur = str.charAt(i);
            if (cur >= 65 && cur <= 90) {
                cur += 32;
                isUpper = true;
            }
            if (isUpper && i != 0) {
                builder.append("_").append(cur);
            }else {
                builder.append(cur);
            }
        }
        return builder.toString();
    }
}
