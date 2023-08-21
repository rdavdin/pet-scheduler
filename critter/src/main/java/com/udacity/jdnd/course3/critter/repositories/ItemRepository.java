package com.udacity.jdnd.course3.critter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
}
