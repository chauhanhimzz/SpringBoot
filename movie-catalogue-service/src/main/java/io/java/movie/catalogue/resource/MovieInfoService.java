package io.java.movie.catalogue.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfoService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackCatalogItem")
	public Movie getCatalogueItem(Rating rating) {
		//----using service discovery
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
				Movie.class);
		return movie;
	}

	public Movie getFallbackCatalogItem(Rating rating) {
		return new Movie("movienamenotfound", "0");
	}	

}
