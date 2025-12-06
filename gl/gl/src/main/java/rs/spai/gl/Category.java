package rs.spai.gl;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String idC;
    private String C_name;
    private String parentCategoryId;
    
    @OneToMany(mappedBy = "categoryId")
    private List<Book> books;
    


	public String getIdC() {
		return idC;
	}




	public void setIdC(String idC) {
		this.idC = idC;
	}




	public String getC_name() {
		return C_name;
	}




	public void setC_name(String c_name) {
		C_name = c_name;
	}




	public String getParentCategoryId() {
		return parentCategoryId;
	}




	public void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
    
    
    
	
}
