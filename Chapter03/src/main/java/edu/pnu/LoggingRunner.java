package edu.pnu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoggingRunner implements ApplicationRunner{
	
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception{
//		Log.trace("TRACE 로그 메시지");
//		Log.debug("DEBUG 로그 메시지");
//		Log.info("INFO 로그 메시지");
//		Log.warn("warn 로그 메시지");
//		Log.error("ERROR 로그 메시지");
	}
	
}
