package com.common.logger;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;

import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import static com.common.logger.LoggerConstants.*;

/**
 * Created by yurl on 2017/12/12.
 */
enum LoggerRepositoryWrapper {
    DEFUALT_LOGGER(DEFUALT_LOGGER_NAME, DEFUALT_LOG4J_RESOURCE_NAME);

    private String name;
    private String propPath;
    private LoggerRepository loggerRepository;
    private ConcurrentHashMap<String, BaseLogger> loggerMap = new ConcurrentHashMap<String, BaseLogger>();

    private LoggerRepositoryWrapper(String name, String propPath) {
        this.name = name;
        this.propPath = propPath;
    }

    private void initLoggerRepository() {
        if (loggerRepository == null) {
            Logger rootLogger = new RootLogger(Level.ALL);
            loggerRepository = new Hierarchy(rootLogger);
            URL resource = Thread.currentThread().getContextClassLoader().getResource(propPath);
            if (resource != null) {
                new PropertyConfigurator().doConfigure(resource, loggerRepository);
            } else {
                LogLog.error("missing log resource properties");
            }
        }
    }

    public String getName() {
        return name;
    }

    public BaseLogger getLogger() {
        return getLogger(name);
    }

    public BaseLogger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    public BaseLogger getLogger(String name) {
        initLoggerRepository();
        if (loggerMap.contains(name)) {
            return loggerMap.get(name);
        } else {
            synchronized (loggerMap) {
                if (!loggerMap.contains(name)) {
                    Logger logger = loggerRepository.getLogger(name);
                    logger.setLevel(LoggerFactory.getConfigLevel());
                    BaseLogger loggerAdapter = new LoggerAdapter(logger);
                    loggerMap.put(name, loggerAdapter);
                    return loggerAdapter;
                } else {
                    return loggerMap.get(name);
                }
            }
        }
    }

    public void clear() {
        loggerMap.clear();
    }


}
