package io.java.movie.data.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getMovieData(@PathVariable("movieId") String movieId){
		return new Rating(movieId, "12");
	}

	@RequestMapping("users/{userId}")
	public UserRating getRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", "40"),
				new Rating("5678", "12")
				);
		UserRating ur = new UserRating();
		ur.setUserRating(ratings);
		return ur;
	}
}
