package com.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Entity.Category;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Integer>{

}
