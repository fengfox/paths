package com.ftao.paths.repository;

import com.ftao.paths.domain.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderRepository extends JpaRepository<Leader,Integer> {
}
