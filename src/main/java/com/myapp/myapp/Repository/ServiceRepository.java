package com.myapp.myapp.Repository;


import com.myapp.myapp.Models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<List<Service>> findAllByDirection(long id);
}
