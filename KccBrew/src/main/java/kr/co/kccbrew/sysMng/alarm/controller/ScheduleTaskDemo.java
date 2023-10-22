package kr.co.kccbrew.sysMng.alarm.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskDemo {
	
	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		//CarService carService = ctx.getBean("carService", CarService.class);
		//
        // while (!carService.isDone()) {
        //    logger.info("스케줄된 잡이 끝나기를 기다림 ...");
        //    Thread.sleep(250);
        // }
		
		System.in.read();
		ctx.close();
	}
}