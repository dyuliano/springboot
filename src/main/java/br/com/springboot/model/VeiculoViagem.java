package br.com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoViagem {
    private String Nome;
    private String Marca;
    private String Modelo;
    private String Ano;
    private Double QuantidadeCombustivel;
    private Double ValorTotalCombustivel;
}
