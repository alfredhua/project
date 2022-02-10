package com.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static Logger innerGet() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return LoggerFactory.getLogger(stackTrace[3].getClassName());
    }

    public static void info( String format, Object... obj) {
        Logger logger = innerGet();
        if (logger.isInfoEnabled()) {
            innerGet().info(format, obj);
        }
    }

    public static void warn( String format, Object... obj) {
        Logger logger = innerGet();
        if (logger.isWarnEnabled()) {
            innerGet().warn(format, obj);
        }
    }

    public static void trace( String format, Object... obj) {
        Logger logger = innerGet();
        if (logger.isTraceEnabled()) {
            innerGet().trace(format, obj);
        }
    }

    public static void error( String format, Object... obj) {
        Logger logger = innerGet();
        if (logger.isErrorEnabled()) {
            innerGet().error(format, obj);
        }
    }

    public static void error(Throwable e, String format, Object... arguments) {
        Logger logger = innerGet();
        logger.error(format(format, arguments), e);
    }

    private static String format(String template, Object... values) {
        return String.format(template.replace("{}", "%s"), values);
    }
}

