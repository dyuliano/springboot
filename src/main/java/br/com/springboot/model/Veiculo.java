package br.com.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

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
@Where(clause = "DELETED = 0")
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

    @Column(name = "DELETED")
    private Integer deleted = 0;

    public void setDeleted() {
        this.deleted = 1;
    }

    public Double QuantidadeCombustivelViagem(Double kmCidade, Double kmRodovia) {
        return ((kmCidade / this.consumoMedioCidade) + (kmRodovia / this.consumoMedioRodovias));
    }

    public Double ValorCombustivelViagem(Double precoCombustivel, Double kmCidade, Double kmRodovia) {
        return ((kmCidade / this.consumoMedioCidade * precoCombustivel)
                + (kmRodovia / this.consumoMedioRodovias * precoCombustivel));
    }

}
