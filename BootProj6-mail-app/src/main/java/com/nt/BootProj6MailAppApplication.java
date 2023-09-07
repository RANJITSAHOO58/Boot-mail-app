package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.service.IMassage;

@SpringBootApplication
public class BootProj6MailAppApplication {

	public static void main(String[] args) {
		//get ioc container
		ApplicationContext ctx=SpringApplication.run(BootProj6MailAppApplication.class, args);
	   //get service class object ref
		IMassage service=ctx.getBean("ImpMassageService",IMassage.class);
		//invoke method
		try {
			String msg=service.sendmassage(new String[] {"hi friend","happy janmastami","may god bless you"},new String[] {"sataikiit19@gmail.com"});
			System.out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//close container
		((ConfigurableApplicationContext)ctx).close();
	}

}
