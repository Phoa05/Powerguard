package com.fiap.powerguard.repository;

import com.fiap.powerguard.models.QuedaEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuedaEnergiaRepository extends JpaRepository<QuedaEnergia, Long> {

    List<QuedaEnergia> findByCepAfetado(String cepAfetado);

    List<QuedaEnergia> findByUserId(Long userId);

    List<QuedaEnergia> findByStatus(String status);
}
