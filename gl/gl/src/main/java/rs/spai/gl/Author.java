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
public class Author {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idA;
    private String name;
    private int age;
    private String nationality;
    
    @OneToMany(mappedBy = "authorId")
    private List<Book> books;


	public String getIdA() {
		return idA;
	}


	public void setIdA(String idA) {
		this.idA = idA;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
    
    
    
    
    
  

}
