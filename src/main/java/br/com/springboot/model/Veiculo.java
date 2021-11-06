package br.com.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nome;
    @NonNull
    private String marca;
    @NonNull
    private String modelo;
    @NonNull
    private String dataFabricação; // Ano/Modelo AAAA/AAAA
    private Double consumoMedioCidade;
    private Double consumoMedioRodovias;

    public Double QuantidadeCombustivelViagem(Double kmCidade, Double kmRodovia) {
        return ((kmCidade / this.consumoMedioCidade) + (kmRodovia / this.consumoMedioRodovias));
    }

    public Double ValorCombustivelViagem(Double precoCombustivel, Double kmCidade, Double kmRodovia) {
        return ((kmCidade / this.consumoMedioCidade * precoCombustivel)
                + (kmRodovia / this.consumoMedioRodovias * precoCombustivel));
    }

}
