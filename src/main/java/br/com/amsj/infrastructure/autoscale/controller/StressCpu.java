package br.com.amsj.infrastructure.autoscale.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.amsj.infrastructure.autoscale.service.StressCpuService;

@RestController
@RequestMapping
public class StressCpu {

	@GetMapping("/stresscpu")
	public ResponseEntity<String> stressCpu(@RequestParam int seconds) throws InterruptedException {
		
		return stress(seconds);
	}
	
	private ResponseEntity<String> stress(int seconds) throws InterruptedException {
		
		long limitTime = 10 * 60; // max limit 10 minutes
		
		if(seconds > limitTime) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid parameter");
		}
		
		new StressCpuService().stress(seconds);
		
		
		return ResponseEntity.ok("Success");
	}

}
