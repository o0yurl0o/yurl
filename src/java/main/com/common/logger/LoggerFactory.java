package com.common.logger;

import com.common.utils.StringUtils;
import org.apache.log4j.Level;

import static com.common.logger.LoggerConstants.DEFUALT_LOGGER_NAME;
import static com.common.logger.LoggerRepositoryWrapper.*;

/**
 * Created by yurl on 2017/12/4.
 */
public class LoggerFactory {

    private static Level configLevel;

    public static BaseLogger getLogger() {
        return getLogger(DEFUALT_LOGGER_NAME);
    }

    public static BaseLogger getLogger(String name) {
        return DEFUALT_LOGGER.getLogger(name);
    }

    /**
     * 设置首选日志级别
     * @param level
     */
    public static void setLoggerLevel(String level) {
        if (!StringUtils.isEmpty(level)) {
            setLoggerLevel(Level.toLevel(level));
        }
    }

    public static void setLoggerLevel(Level level) {
        configLevel = level;
        for (LoggerRepositoryWrapper loggerRepositoryWarpper : LoggerRepositoryWrapper.values()) {
            loggerRepositoryWarpper.clear();
        }
    }

    public static Level getConfigLevel() {
        return configLevel != null ? configLevel : Level.DEBUG;
    }


    public static void clear() {
        for (LoggerRepositoryWrapper loggerRepositoryWarpper : LoggerRepositoryWrapper.values()) {
            loggerRepositoryWarpper.clear();
        }
    }


}
