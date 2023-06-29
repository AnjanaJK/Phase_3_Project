package com.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Entity.Purchase;

@Repository
public interface IPurchaseRepo extends JpaRepository<Purchase, Integer>{

	

}
