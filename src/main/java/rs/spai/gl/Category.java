package rs.spai.gl;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int idC;
    private String C_name;
    
    
    
    @ManyToOne
    @JoinColumn(name = "parentCategory")  // --> colonne INT en SQL
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;
    
    @OneToMany(mappedBy = "category")
    private List<Book> books;
    


	public int getIdC() {
		return idC;
	}




	public void setIdC(int idC) {
		this.idC = idC;
	}




	public String getCategoryName() {
		return C_name;
	}




	public void setCategoryName(String c_name) {
		C_name = c_name;
	}




	public Category getParentCategory() {
		return parentCategory;
	}




	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
    
    
    
	
}
