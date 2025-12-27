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
	
// --------------------   	 booksByAuthor ------------------
	
	@QueryMapping
	public List<Book> booksByAuthor(@Argument Long authorId) {
	    return bookRepo.findAll().stream()
	            .filter(b -> b.getAuthorId() != null &&
	                         b.getAuthorId().getIdA() == authorId).toList();
	}

	
	// --------------------   	 search   ------------------	
	
	@QueryMapping
	public List<Object> search( @Argument String keyword,  @Argument String type, @Argument int page) {
	    List<Object> result = new ArrayList<>();

	    if (type == null || type.equalsIgnoreCase("Book")) {
	        result.addAll(bookRepo.findAll().stream()
	            .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
	            .toList());
	    }
	    if (type == null || type.equalsIgnoreCase("Author")) {
	        result.addAll(autRepo.findAll().stream()
	            .filter(a -> a.getName().toLowerCase().contains(keyword.toLowerCase()))
	            .toList());
	    }
	    if (type == null || type.equalsIgnoreCase("Category")) {
	        result.addAll(catRepo.findAll().stream()
	            .filter(c -> c.getCategoryName().toLowerCase().contains(keyword.toLowerCase())) // Correction
	            .toList());
	    }

	    int start = (page - 1) * defaultPageSize;
	    int end = Math.min(start + defaultPageSize, result.size());

	    // Vérifiez si le début est supérieur à la taille de la liste
	    if (start < 0 || start >= result.size()) {
	        return Collections.emptyList();
	    }

	    return result.subList(start, end);
	}
	
	// ------------------------  Add a new Book  ---------------------	
		@MutationMapping
		public Book addBook(@Argument String title,@Argument int publicationYear,@Argument int nbrpages,@Argument String language,@Argument int authorId,@Argument int categoryId) {

		    Author author = autRepo.findById(authorId)
		            .orElseThrow(() -> new RuntimeException("Author not found"));

		    Category category = catRepo.findById(categoryId)
		            .orElseThrow(() -> new RuntimeException("Category not found"));

		    Book book = Book.builder()
		            .title(title)
		            .publicationYear(publicationYear)
		            .pages(nbrpages)
		            .language(language)
		            .author(author)
		            .category(category)
		            .build();

		    return bookRepo.save(book);
		}

	//  ----------------------  Delet an author --------
		
		@MutationMapping
		public Boolean deleteAuthor(@Argument int authorId) {
		    autRepo.deleteById(authorId);
		    return true;
		}
	


}
