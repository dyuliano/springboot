package br.com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.springboot.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v from Veiculo v order by (v.consumoMedioCidade * :combustivel + v.consumoMedioRodovias * :combustivel)")
    public List<Veiculo> findMaiorCusto(@Param("combustivel") Double combustivel);

    @Query("SELECT v from Veiculo v order by (v.consumoMedioCidade * :combustivel + v.consumoMedioRodovias * :combustivel) desc")
    public List<Veiculo> findMenorCusto(@Param("combustivel") Double combustivel);

}
