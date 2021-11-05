package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

}
