package io.java.conf.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.conf.cli.DBConfig;

@RestController
public class ClientConfigControoler {
	@Autowired
	DBConfig conf;
	
	@GetMapping("/getConfig")
	public String greeting() {
		return  conf.getPort() +conf.getUser() + conf.getPwd();
	}

}
