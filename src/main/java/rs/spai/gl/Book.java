package rs.spai.gl;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private int idb;
    private String title;
    private int publicationYear;
    private String language;
    private int pages;
    
    @ManyToOne
    @JoinColumn(name = "author_id_ida")
    private Author author;
    
    @ManyToOne
    @JoinColumn(name = "category_id_idc")
    private Category category;
    


	public long getIdBook() {
		return idb;
	}





	public void setIdB(int idB) {
		this.idb = idB;
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





	public int getNbPages() {
		return pages;
	}





	public void setNbPages(int pages) {
		this.pages = pages;
	}





	public Author getAuthorId() {
		return author;
	}





	public void setAuthorId(Author authorId) {
		this.author = authorId;
	}





	public Category getCategory() {
		return category;
	}





	public void setCategory(Category categoryId) {
		this.category = categoryId;
	}
    
    
    
    
    
    
    

}
