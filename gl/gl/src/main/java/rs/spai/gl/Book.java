package rs.spai.gl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idB;
    private String title;
    private int publicationYear;
    private String language;
    private int pages;
    
    @ManyToOne
    private Author authorId;
    @ManyToOne
    private Category categoryId;
    


	public String getIdB() {
		return idB;
	}





	public void setIdB(String idB) {
		this.idB = idB;
	}





	public String getTitle() {
		return title;
	}





	public void setTitle(String title) {
		this.title = title;
	}





	public int getPublicationYear() {
		return publicationYear;
	}





	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}





	public String getLanguage() {
		return language;
	}





	public void setLanguage(String language) {
		this.language = language;
	}





	public int getPages() {
		return pages;
	}





	public void setPages(int pages) {
		this.pages = pages;
	}





	public Author getAuthorId() {
		return authorId;
	}





	public void setAuthorId(Author authorId) {
		this.authorId = authorId;
	}





	public Category getCategoryId() {
		return categoryId;
	}





	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
    
    
    
    
    
    
    

}
