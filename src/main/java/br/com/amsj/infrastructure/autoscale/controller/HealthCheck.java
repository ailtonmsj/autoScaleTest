package br.com.amsj.infrastructure.autoscale.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheck {

	static {
		try {
			LOCAL_HOST = InetAddress.getLocalHost().toString();
		} catch (Exception e) {
			e.printStackTrace();
			LOCAL_HOST = "Unknown host";
		}
	}
	
	private static String LOCAL_HOST;
	
	private static final ResponseEntity<String> HEALTH_CHECK_OK = ResponseEntity.ok("Up and Running - " + LOCAL_HOST);
	private static final ResponseEntity<String> HEALTH_CHECK_ERROR = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(LOCAL_HOST);
	
	private static ResponseEntity<String> healthCheckResult = HEALTH_CHECK_OK;
	
	@GetMapping(path = "/")
	public ResponseEntity<String> healthcheck(){
					
		return healthCheckResult;
	}
	
	@PutMapping(path = "/{healthcheckstatus}")
	public ResponseEntity<String> healthcheck(@PathVariable(name = "healthcheckstatus", required = true) boolean healthCheckStatus){
		
		if(healthCheckStatus) {
			healthCheckResult = HEALTH_CHECK_OK;
		}else {
			healthCheckResult = HEALTH_CHECK_ERROR;
		}
		
		return ResponseEntity.ok("Health check status will be " + healthCheckResult.getStatusCodeValue() + " now");
	}

}
