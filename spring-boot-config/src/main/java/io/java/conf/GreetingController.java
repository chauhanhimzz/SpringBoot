package io.java.conf;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	@Value("${my.greetings: defalut value}")
	private String greetingMsg;
	
	@Value("${app.description}")
	private String description ;
	
	@Value("${my.list.val}")
	private List<String> alp;
	
	@Value("#{${db.conn.val}}")
	public Map<String, String> mymap;
	
	@Autowired
	DBConfig conf;
	
	@GetMapping("/greeting")
	public String greeting() {
		return greetingMsg + description + alp + mymap + conf.getPort() +conf.getUser() + conf.getPwd();
	}

}
