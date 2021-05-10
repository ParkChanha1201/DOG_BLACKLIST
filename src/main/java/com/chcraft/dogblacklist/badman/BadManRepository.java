package com.chcraft.dogblacklist.badman;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BadManRepository extends JpaRepository<BadMan, Integer>{
	Optional<BadMan> findByName(String name);
}
