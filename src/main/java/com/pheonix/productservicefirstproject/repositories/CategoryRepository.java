package com.pheonix.productservicefirstproject.repositories;

import com.pheonix.productservicefirstproject.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

////It will contain all the CRUD operations related to Category Table
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category save(Category category);
}
