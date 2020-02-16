package io.java.movie.data.resource;

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

}
