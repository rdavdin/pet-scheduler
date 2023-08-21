package com.udacity.jdnd.course3.critter.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.entities.Bid;
import com.udacity.jdnd.course3.critter.entities.Item;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long>{
    Set<Bid> findByItem(Item item);
}
