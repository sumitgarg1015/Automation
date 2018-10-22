package utility;

import org.apache.log4j.Logger;


public class Log {
	public static Logger log = Logger.getLogger(Log.class.getClass());

	
	public static void startTestCase(String tc){
		
		log.info("********************************************************************");
		log.info("********************************************************************");
		log.info("%%%%%%%%%%%%%%Start "+tc+ " execution%%%%%%%%%%%%%");
		log.info("********************************************************************");
		log.info("********************************************************************");		
	}
	
	public static void endTestCase(String tc){
		
		log.info("********************************************************************");
		log.info("********************************************************************");
		log.info("%%%%%%%%%%%%%%%End "+tc+" execution%%%%%%%%%%%%%%%");
		log.info("********************************************************************");
		log.info("********************************************************************");		
	}
	
	public static void info(String message){
		log.info(message);
	}
	public static void warn(String message){
		log.warn(message);
	}
	public static void fatal(String message){
		log.fatal(message);
	}
	public static void debug(String message){
		log.debug(message);
	}
	public static void error(String message){
		log.error(message);
	}
	public static void trace(String message){
		log.trace(message);
	}
}
