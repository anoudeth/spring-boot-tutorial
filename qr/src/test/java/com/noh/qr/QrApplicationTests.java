package com.noh.qr;

import com.noh.qr.utils.QrCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QrApplicationTests {
	@Autowired
	private QrCode qrCode;
	@Test
	void contextLoads() {
	}
	@Test
	void genQr() {

	}

}
