package com.san.spring.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SpringTaskScheduler {

	 private static final Logger LOGGER = LoggerFactory.getLogger(SpringTaskScheduler.class);
	 
	 public SpringTaskScheduler(){
		 
	 }
	 
	 public void doSomething(){
		 LOGGER.info("Hi ..I'm insdie SpringTaskScheduler:doSomething()....");
	}
	 
}
