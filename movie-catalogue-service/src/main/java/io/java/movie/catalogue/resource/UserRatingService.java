package io.java.movie.catalogue.resource;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableHystrix
public class UserRatingService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") }
			
			)
	
	public UserRating getUserRating(String userId) {
		//----using service discovery
		UserRating userRating = restTemplate.getForObject("http://movie-data-service/data/users/"+userId,
				UserRating.class);
		return userRating;
	}
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating urating = new UserRating();
		Rating rating = new Rating("0", "0");
		urating.setUserRating(Arrays.asList(rating));
		return urating;

	}
}
