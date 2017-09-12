package cn.edu.ntu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class BaseController<T> {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected <T> ResponseEntity<T> ok(T body) {
		return ResponseEntity.ok(body);
	}
}
