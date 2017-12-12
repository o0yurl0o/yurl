package com.common.logger;

import com.common.logger.helper.FormattingTuple;
import com.common.logger.helper.MessageFormatter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by yurl on 2017/12/12.
 */
public final class LoggerAdapter implements Serializable, BaseLogger {

    private static final long serialVersionUID = -3022819204749213789L;
    final transient Logger logger;
    private final ResourceBundle rb = ResourceBundle.getBundle("logger", Locale.getDefault());

    /**
     * Following the pattern discussed in pages 162 through 168 of "The complete
     * log4j manual".
     */
    final static String FQCN = LoggerAdapter.class.getName();

    // Does the log4j version in use recognize the TRACE level?
    // The trace level was introduced in log4j 1.2.12.
    final boolean traceCapable;

    // WARN: DirectLoggerAdapter constructor should have only package access so
    // that
    // only DirectLoggerFactory be able to create one.
    LoggerAdapter(Logger logger) {
        this.logger = logger;
        traceCapable = isTraceCapable();
    }

    private void log(String callerFQCN, Priority level, String message, Throwable t) {
        logger.log(callerFQCN, level, message, t);
    }

    private boolean isTraceCapable() {
        try {
            logger.isTraceEnabled();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        }
    }

    /**
     * Is this logger instance enabled for the TRACE level?
     *
     * @return True if this Logger is enabled for level TRACE, false otherwise.
     */
    public boolean isTraceEnabled() {
        if (traceCapable) {
            return logger.isTraceEnabled();
        } else {
            return logger.isDebugEnabled();
        }
    }

    /**
     * Log a message object at level TRACE.
     *
     * @param msg - the message object to be logged
     */
    public void trace(String msg) {
        log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, msg, null);
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * argument.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level TRACE.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void trace(String format, Object arg1, Object arg2) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level TRACE according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the TRACE level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void trace(String format, Object[] argArray) {
        if (isTraceEnabled()) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft
                    .getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at level TRACE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void trace(String msg, Throwable t) {
        log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, msg, t);
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Log a message object at level DEBUG.
     *
     * @param msg - the message object to be logged
     */
    public void debug(String msg) {
        if (rb.containsKey(msg)) {
            log(FQCN, Level.DEBUG, rb.getString(msg), null);
        } else {
            log(FQCN, Level.DEBUG, msg, null);
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * argument.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level DEBUG.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void debug(String format, Object arg) {
        if (logger.isDebugEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.DEBUG, new MessageFormat(rb.getString(format)).format(new Object[]{arg}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.DEBUG, new MessageFormat(rb.getString(format)).format(new Object[]{arg1, arg2}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void debug(String format, Object[] argArray) {
        if (logger.isDebugEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.DEBUG, new MessageFormat(rb.getString(format)).format(argArray), null);
            } else {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
                log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void debug(String msg, Throwable t) {
        log(FQCN, Level.DEBUG, msg, t);
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Log a message object at the INFO level.
     *
     * @param msg - the message object to be logged
     */
    public void info(String msg) {
        if (rb.containsKey(msg)) {
            log(FQCN, Level.INFO, rb.getString(msg), null);
        } else {
            log(FQCN, Level.INFO, msg, null);
        }
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void info(String format, Object arg) {
        if (logger.isInfoEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.INFO, new MessageFormat(rb.getString(format)).format(new Object[]{arg}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void info(String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.INFO, new MessageFormat(rb.getString(format)).format(new Object[]{arg1, arg2}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at level INFO according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void info(String format, Object[] argArray) {
        if (logger.isInfoEnabled()) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.INFO, new MessageFormat(rb.getString(format)).format(argArray), null);
            } else {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
                log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void info(String msg, Throwable t) {
        log(FQCN, Level.INFO, msg, t);
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false otherwise.
     */
    public boolean isWarnEnabled() {
        return logger.isEnabledFor(Level.WARN);
    }

    /**
     * Log a message object at the WARN level.
     *
     * @param msg - the message object to be logged
     */
    public void warn(String msg) {
        if (rb.containsKey(msg)) {
            log(FQCN, Level.WARN, rb.getString(msg), null);
        } else {
            log(FQCN, Level.WARN, msg, null);
        }
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void warn(String format, Object arg) {
        if (logger.isEnabledFor(Level.WARN)) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.WARN, new MessageFormat(rb.getString(format)).format(new Object[]{arg}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg);
                log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.WARN)) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.WARN, new MessageFormat(rb.getString(format)).format(new Object[]{arg1, arg2}), null);
            } else {
                FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
                log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log a message at level WARN according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void warn(String format, Object[] argArray) {
        if (logger.isEnabledFor(Level.WARN)) {
            if (rb.containsKey(format)) {
                log(FQCN, Level.WARN, new MessageFormat(rb.getString(format)).format(argArray), null);
            } else {
                FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
                log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
            }
        }
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void warn(String msg, Throwable t) {
        log(FQCN, Level.WARN, msg, t);
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isEnabledFor(Level.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     *
     * @param msg - the message object to be logged
     */
    public void error(String msg) {
        if (msg == null){
            log(FQCN, Level.ERROR, msg, null);
            return;
        }
        if (rb.containsKey(msg)) {
            log(FQCN, Level.ERROR, rb.getString(msg), null);
        } else {
            log(FQCN, Level.ERROR, msg, null);
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    public void error(String format, Object arg) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg);
            log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    public void error(String format, Object arg1, Object arg2) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
            log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log a message at level ERROR according to the specified format and
     * arguments.
     * <p>
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    public void error(String format, Object[] argArray) {
        if (logger.isEnabledFor(Level.ERROR)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    public void error(String msg, Throwable t) {
        if (msg == null) {
            log(FQCN, Level.ERROR, msg, t);
            return;
        }
        if (rb.containsKey(msg)) {
            log(FQCN, Level.ERROR, rb.getString(msg), t);
        } else {
            log(FQCN, Level.ERROR, msg, t);
        }
    }

}
