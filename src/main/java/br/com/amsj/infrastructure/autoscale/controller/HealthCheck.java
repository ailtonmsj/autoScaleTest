package br.com.amsj.infrastructure.autoscale.controller;

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
	
	private boolean healthcheckstatus = true; 
	
	@GetMapping(path = "/")
	public ResponseEntity<String> healthcheck(){
		
		if(this.healthcheckstatus) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping(path = "/{healthcheckstatus}")
	public ResponseEntity<Void> healthcheck(@PathVariable(name = "healthcheckstatus", required = true) boolean healthcheckstatus){
		
		this.healthcheckstatus = healthcheckstatus;
		
		return ResponseEntity.ok().build();
	}

}
