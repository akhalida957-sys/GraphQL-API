package rs.spai.gl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;



@Controller
public class MyController {
	
	
	
	private AuthorRepository autRepo ;
	private BookRepository bookRepo ;
	private CategoryRepository catRepo ;
	private final static int defaultPageSize =10;
	
	
	public MyController(AuthorRepository autRepo, BookRepository bookRepo, CategoryRepository catRepo) {
		super();
		this.autRepo = autRepo;
		this.bookRepo = bookRepo;
		this.catRepo = catRepo;
	}
	
	
	
	@QueryMapping
	public List<Book> listBooks( @Argument Integer publicationYear,@Argument String language,@Argument Integer categoryId,
	       @Argument Boolean recursive, // true = inclure sous-catégories
	        @Argument int page) {


	    List<Book> books = bookRepo.findAll();

	    // Filtre par publicationYear
	    if (publicationYear != null) {
	        books = books.stream()
	                .filter(b -> b.getPublicationYear() == publicationYear)
	                .toList();
	    }

	    // Filtre par language
	    if (language != null && !language.isEmpty()) {
	        books = books.stream()
	                .filter(b -> b.getLanguage().equalsIgnoreCase(language))
	                .toList();
	    }

	    // Filtre par catégorie
	    if (categoryId != null) {
	        if (Boolean.TRUE.equals(recursive)) {
	            // Inclure sous-catégories
	            Set<Integer> categoryIds = new HashSet<>();
	            collectCategoryIds(categoryId, categoryIds);
	            books = books.stream()
	                    .filter(b -> categoryIds.contains(b.getCategory().getIdC()))
	                    .toList();
	        } else {
	            // Juste la catégorie sélectionnée
	            books = books.stream()
	                    .filter(b ->b.getCategory().getIdC() == categoryId)
	                    .toList();
	        }
	    }

	    // Pagination
	    int start = (page - 1) * defaultPageSize;
	    int end = Math.min(start + defaultPageSize, books.size());

	    return books.subList(start, end);
	}

	// Méthode récursive pour récupérer toutes les sous-catégories
	private void collectCategoryIds(int parentId, Set<Integer> categoryIds) {
	    categoryIds.add(parentId);
	    List<Category> children = catRepo.findByParentCategory_IdC(parentId);
	    for (Category child : children) {
	        collectCategoryIds(child.getIdC(), categoryIds);
	    }
	}


}
