//package com.olx.actuator;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
//import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
//import org.springframework.stereotype.Component;
//
//import com.olx.repository.UserRepository;
//
//@Component
//
//@Endpoint(id = "user")
//public class UserDetailsActuator {
//
//	private Map<String, Integer> map = new HashMap<String, Integer>();
//
//	@Autowired
//	UserRepository userRepository;
//
//	@PostConstruct
//	public void init() {
//		map.put("totalUser", userRepository.findAll().size());
//	}
//
//	@ReadOperation
//	public Map<String, Integer> getAllData() {
//		return map;
//	}
//}
