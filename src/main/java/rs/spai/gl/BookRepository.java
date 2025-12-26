package rs.spai.gl;

import org.springframework.data.jpa.repository.JpaRepository;


// C’est la classe qui communique avec la BD et exécuter automatiquement des requêtes SQL/JPA

public interface BookRepository extends JpaRepository<Book, Integer>{

}
