package rs.spai.gl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//C’est la classe qui communique avec la BD et exécuter automatiquement des requêtes SQL/JPA

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // JPA va générer automatiquement la requête pour récupérer les enfants
    List<Category> findByParentCategory_IdC(int parentId);
}
