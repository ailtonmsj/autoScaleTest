package br.com.amsj.infrastructure.autoscale.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StressCpu {

	@GetMapping("/stresscpu")
	public ResponseEntity<String> stressCpu(@RequestParam long seconds) {
		
		return stress(seconds);
	}
	
	private ResponseEntity<String> stress(long plusSeconds) {
		
		long limitTime = 10 * 60; // max limit 10 minutes
		
		if(plusSeconds > limitTime) {
			return ResponseEntity.badRequest().build();
		}
		
		LocalDateTime limitDate = LocalDateTime.now().plusSeconds(plusSeconds);

		int count = 0;
		while(limitDate.isAfter(LocalDateTime.now())) {
			count++;
			try {
				stressCpuUsingCipher();
			} catch (Exception e) {
				System.err.println("Error on execution " + count);
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		return ResponseEntity.ok("Execution times " + count);
	}

	private void stressCpuUsingCipher() throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {

		byte[] keyBytes = new String("TEST STRESS TEST").getBytes("UTF-8");
		byte[] messageToCripto = new String("TESTE STRESS CPU MESSAGE").getBytes("UTF-8");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		cipher.update(messageToCripto);

	}
}
