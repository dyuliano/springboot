package br.com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {
    public Long IdVeiculo;
    public Double precoGasolina;
    public Double kmCidade;
    public Double kmRodovia;

    public Double CustoCidade() {
        return (this.kmCidade * this.precoGasolina);
    }

    public Double CustoRodovia() {
        return (this.kmRodovia * this.precoGasolina);
    }

    public Double CustoViagem() {
        return (this.CustoCidade() + this.CustoRodovia());
    }

}
