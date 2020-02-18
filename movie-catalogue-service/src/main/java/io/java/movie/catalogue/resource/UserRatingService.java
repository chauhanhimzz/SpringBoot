package io.java.movie.catalogue.resource;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackUserRating")
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