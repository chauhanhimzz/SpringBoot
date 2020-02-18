package io.java.movie.catalogue.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder builder;
	
	@Autowired
	MovieInfoService movieInfo;
	
	@Autowired
	UserRatingService userRatingser;
	
	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod="getFallbackCatatlogue")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId")String userId){
		UserRating userRating = userRatingser.getUserRating(userId);
		List<CatalogueItem> catItems = new ArrayList<CatalogueItem>();
		
		for (Rating rating : userRating.getUserRating()) {
			Movie movie = movieInfo.getCatalogueItem(rating);
			CatalogueItem ci = new CatalogueItem(movie.getName(), "test", rating.getRating());
			catItems.add(ci);
		}
		
		//----using service discovery - ends
		
		//List<Rating> ratings = new ArrayList<Rating>();
		// ratings = Arrays.asList(
		//	new Rating("1234","3"),
		//	new Rating("5678","4")
		//		);
		
		//----------------code to use normal rest template and weblclient starts
		/*UserRating userRating = restTemplate.getForObject("http://localhost:8383/data/users/"+userId,
				UserRating.class);
		List<CatalogueItem> catItems = new ArrayList<CatalogueItem>();
		
		for (Rating rating : userRating.getUserRating()) {
			Movie movie = builder.build()
					             .get()
					             .uri("http://localhost:8282/movies/" + rating.getMovieId())
					             .retrieve()
					             .bodyToMono( Movie.class)
					             .block();
			CatalogueItem ci = new CatalogueItem(movie.getName(), "test", rating.getRating());
			catItems.add(ci);
		}*/
		//----------------code to use normal rest template and weblclient ends
		//use this code with restTemplate object
		//for(Rating rating : ratings) {
		//	Movie movie = restTemplate.getForObject("http://localhost:8282/movies/"+rating.getMovieId(), Movie.class);
		//	CatalogueItem ci = new CatalogueItem(movie.getName(), "test", rating.getRating());
		//	catItems.add(ci);
		//}
	return catItems;
	}
	
	List<CatalogueItem> getFallbackCatatlogue(@PathVariable("userId")String userId){
		return Arrays.asList(new CatalogueItem("nomovie", "", "0"));
	}
}
