package com.ifto.oficina.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifto.oficina.model.Carro;



@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer>{

    
} 