package com.noh.qr;

import com.noh.qr.utils.QrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QrApplication {



	public static void main(String[] args) {
		System.out.println("hello");

		SpringApplication.run(QrApplication.class, args);

	}

}
