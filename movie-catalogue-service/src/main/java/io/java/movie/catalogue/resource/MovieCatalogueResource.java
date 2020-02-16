package io.java.movie.catalogue.resource;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogueResource {
	
	@RequestMapping("/{userId}")
	public List<CatalogueItem> getCatalogue(@PathVariable("userId")String userId){
		return Collections.singletonList(
				new CatalogueItem("transformers","test","2")
				);
	}

}
