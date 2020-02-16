package io.java.movie.catalogue.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId")String userId){
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<Rating> ratings = Arrays.asList(
			new Rating("1234","2"),
			new Rating("5678","4")
				);
		List<CatalogueItem> catItems = new ArrayList<CatalogueItem>();
		for(Rating rating : ratings) {
			Movie movie = restTemplate.getForObject("http://localhost:8282/movies/"+rating.getMovieId(), Movie.class);
			CatalogueItem ci = new CatalogueItem(movie.getName(), "test", rating.getRating());
			catItems.add(ci);
		}
	return catItems;
	}

}
