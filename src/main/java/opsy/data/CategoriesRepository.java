package opsy.data;

import opsy.entities.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CategoriesRepository extends CrudRepository<Categories, Integer> {

    List<Categories> findAll();
    Categories findByCategory(String category);
}
