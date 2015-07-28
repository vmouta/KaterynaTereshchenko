package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class AppLogger implements org.slf4j.Logger
{
	final Logger logger;

	private AppLogger(String name) {
		  logger = LoggerFactory.getLogger(name);
	}
	
	private AppLogger(Class<?> clazz) {
		  logger = LoggerFactory.getLogger(clazz);
	}
	
	public static AppLogger getLogger(String name) {
		return new AppLogger(name);
	}
	
	public static AppLogger getLogger(Class<?> clazz) {
		return new AppLogger(clazz);
	}
	
	public static void	debugLog(java.lang.String message)  {
		play.Logger.debug(message);
	}
	
	public static void	debugLog(java.lang.String message, java.lang.Throwable error)  {
		play.Logger.debug(message, error);
	}
	
	public static void	errorLog(java.lang.String message)  {
		play.Logger.error(message);
	}
	
	public static void	errorLog(java.lang.String message, java.lang.Throwable error)  {
		play.Logger.error(message, error);
	}
	
	public static void	infoLog(java.lang.String message)  {
		play.Logger.info(message);
	}
	
	public static void	infoLog(java.lang.String message, java.lang.Throwable error)  {
		play.Logger.info(message, error);
	}

	public static boolean isDebugEnabledLog() {
		return play.Logger.isDebugEnabled();
	}
	
	public static boolean isErrorEnabledLog() {
		return play.Logger.isErrorEnabled();
	}
	
	
	public static boolean isInfoEnabledLog() {
		return play.Logger.isInfoEnabled();
	}
	
	public static boolean isTraceEnabledLog() {
	    return play.Logger.isTraceEnabled();
    }
	
	public static boolean isWarnEnabledLog() {
	    return play.Logger.isWarnEnabled();
	}
	    
	public static void	traceLog(java.lang.String message) {
		play.Logger.trace(message);
	}
	    
	public static void	traceLog(java.lang.String message, java.lang.Throwable error) {
	    play.Logger.trace(message, error);
	}

	public static void	warnLog(java.lang.String message) {
	    play.Logger.warn(message);
	}
	    
	public static void	warnLog(java.lang.String message, java.lang.Throwable error) {
		play.Logger.warn(message,error);
	}
	
	/* Public */
	
	@Override
	public void debug(String arg0) {
		logger.debug(arg0);
	}

	@Override
	public void debug(String arg0, Object arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void debug(String arg0, Object... arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void debug(String arg0, Throwable arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void debug(Marker arg0, String arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object... arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		logger.debug(arg0, arg1, arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.debug(arg0, arg1, arg2, arg3);
	}

	@Override
	public void error(String arg0) {
		logger.error(arg0);
	}

	@Override
	public void error(String arg0, Object arg1) {
		logger.error(arg0, arg1);
	}

	@Override
	public void error(String arg0, Object... arg1) {
		logger.error(arg0, arg1);
	}

	@Override
	public void error(String arg0, Throwable arg1) {
		logger.error(arg0, arg1);
	}

	@Override
	public void error(Marker arg0, String arg1) {
		logger.error(arg0, arg1);
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) {
		logger.error(arg0, arg1, arg2);
		
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2) {
		logger.error(arg0, arg1, arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Object... arg2) {
		logger.error(arg0, arg1, arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		logger.error(arg0, arg1, arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.error(arg0, arg1, arg2, arg3);
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public void info(String arg0) {
		logger.info(arg0);
	}

	@Override
	public void info(String arg0, Object arg1) {
		logger.info(arg0, arg1);
	}

	@Override
	public void info(String arg0, Object... arg1) {
		logger.info(arg0, arg1);
	}

	@Override
	public void info(String arg0, Throwable arg1) {
		logger.info(arg0, arg1);
	}

	@Override
	public void info(Marker arg0, String arg1) {
		logger.info(arg0, arg1);
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) {
		logger.info(arg0, arg1, arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) {
		logger.info(arg0, arg1, arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object... arg2) {
		logger.info(arg0, arg1, arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		logger.info(arg0, arg1, arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.info(arg0, arg1, arg2, arg3);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		return logger.isDebugEnabled(arg0);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		return logger.isErrorEnabled(arg0);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		return logger.isInfoEnabled(arg0);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		return isTraceEnabled(arg0);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		return logger.isWarnEnabled(arg0);
	}

	@Override
	public void trace(String arg0) {
		logger.trace(arg0);
	}

	@Override
	public void trace(String arg0, Object arg1) {
		logger.trace(arg0, arg1);
	}

	@Override
	public void trace(String arg0, Object... arg1) {
		logger.trace(arg0, arg1);
	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		logger.trace(arg0, arg1);
	}

	@Override
	public void trace(Marker arg0, String arg1) {
		logger.trace(arg0, arg1);
	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		logger.trace(arg0, arg1, arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.trace(arg0, arg1, arg2, arg3);
	}

	@Override
	public void warn(String arg0) {
		logger.warn(arg0);
	}

	@Override
	public void warn(String arg0, Object arg1) {
		logger.warn(arg0, arg1);
	}

	@Override
	public void warn(String arg0, Object... arg1) {
		logger.warn(arg0, arg1);
	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

	@Override
	public void warn(Marker arg0, String arg1) {
		logger.warn(arg0, arg1);
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		logger.warn(arg0, arg1, arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		logger.warn(arg0, arg1, arg2, arg3);
	}

}
