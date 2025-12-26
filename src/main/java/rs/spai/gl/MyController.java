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
	
	


}
