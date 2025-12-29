package rs.spai.gl;

import java.util.List;

import jakarta.persistence.CascadeType;
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
    private int ida;
    private String name;
    private int age;
    private String nationality;
    
    // quand un auteur est supprimé -> tous ses livres sont supprimés automatiquement
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;


	public int getIdA() {
		return ida;
	}


	public void setIdA(int ida) {
		this.ida = ida;
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
