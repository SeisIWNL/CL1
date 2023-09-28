package com.farmacia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.farmacia.modelo.Farmacia;

public interface IFarmaciaRepositorio extends JpaRepository<Farmacia, Integer>{

}
