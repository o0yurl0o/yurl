package com.common.logger.helper;

/**
 * Created by Yurl on 2017/11/6.
 */
public class LogInfoUtils {

    private static Long logLabel = 0L;
    private static final String CLASSNAME = LogInfoUtils.class.getName();

    /**
     * 获得一个新的标识符
     */
    public static Long getLabel() {
        synchronized (logLabel) {
            return logLabel++;
        }
    }

    /**
     * 获得线程方法栈中指定类之前的方法栈元素
     */
    public static StackTraceElement getPreCurrentStack(Thread t, Class clazz) {
        return getPreCurrentStack(t.getStackTrace(), clazz.getName(), 0);
    }

    /**
     * 获得线程方法栈指定深度的方法栈元素
     */
    public static StackTraceElement getPreCurrentStack(Thread t, int deep) {
        return getPreCurrentStack(t.getStackTrace(), CLASSNAME, deep);
    }

    /**
     * 获得异常方法栈中指定类之前的方法栈元素
     */
    public static StackTraceElement getPreCurrentStack(Exception e, Class clazz) {
        return getPreCurrentStack(e.getStackTrace(), clazz.getName(), 0);
    }

    /**
     * 获得异常方法栈中指定深度的方法栈元素
     */
    public static StackTraceElement getPreCurrentStack(Exception e, int deep) {
        StackTraceElement[] elements = e.getStackTrace();
        if (elements.length > deep) {
            return elements[deep];
        }
        return null;
    }

    private static StackTraceElement getPreCurrentStack(StackTraceElement[] elements, String clazz, int deep) {
        boolean flag = false;
        for(int i = 0; i < elements.length - deep; i++) {
            if(elements[i].getClassName().equals(clazz)) {
                flag = true;
            } else if (flag) {
                return elements[i + deep];
            }
        }
        return null;
    }

    /**
     * 获得线程方法栈信息
     */
    public static String stackTraceInfo(Thread thread) {
        StringBuilder sb = new StringBuilder();
        sb.append("name : " + thread.getName() + "\n");
        for (StackTraceElement e : thread.getStackTrace()) {
            sb.append(e.toString()).append('\n');
        }
        return sb.toString();
    }

    /**
     * 截取sql字符串
     */
    public static String substringSql(String sql) {
        if (sql.length() > 5000) {
            StringBuilder sb = new StringBuilder();
            sb.append(sql.substring(0, 4000)).append("\n...and ").append(sql.length() - 4000).append(" characters more");
            return sb.toString();
        }
        return sql;
    }

}
