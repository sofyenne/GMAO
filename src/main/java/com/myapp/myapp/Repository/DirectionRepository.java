package com.myapp.myapp.Repository;

import com.myapp.myapp.Models.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DirectionRepository extends JpaRepository<Direction ,Long> {
}
