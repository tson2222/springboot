package com.wk.autoverleihMVC;

import com.wk.autoverleihMVC.model.Booking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;

@SpringBootApplication
public class AutoMVCApplication {

//TEST
	public static void main(String... args) {
		SpringApplication.run(AutoMVCApplication.class, args);
		for (Field field : Booking.class.getDeclaredFields()) {
			if (field.isAnnotationPresent(org.springframework.format.annotation.DateTimeFormat.class)) {
				System.out.println("The field " + field + " has DateTimeFormat annotation.");
			}else System.out.println(field);
		}
	}
}
