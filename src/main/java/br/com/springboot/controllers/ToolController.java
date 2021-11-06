package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.CustoViagem;
import br.com.springboot.model.Veiculo;
import br.com.springboot.repository.VeiculoRepository;

@RestController
@RequestMapping("/tools")
public class ToolController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping("")
    public String custoviagem(@RequestBody CustoViagem dadosViagem) {

        Veiculo v1 = new Veiculo();
        Optional<Veiculo> meuVeiculo = this.veiculoRepository.findById(dadosViagem.IdVeiculo);

        if (meuVeiculo.isPresent()) {
            v1 = meuVeiculo.get();
            System.out.println(
                    v1.ValorCombustivelViagem(dadosViagem.precoGasolina, dadosViagem.kmCidade, dadosViagem.kmRodovia));
            System.out.println(v1.QuantidadeCombustivelViagem(dadosViagem.kmCidade, dadosViagem.kmRodovia));

            return String.format(
                    "Você irá gastar %,.3f litros de combustivel com um custo de R$ %,.2f para percorrer %,.3f KM no veiculo de ID %s",
                    v1.QuantidadeCombustivelViagem(dadosViagem.kmCidade, dadosViagem.kmRodovia),
                    v1.ValorCombustivelViagem(dadosViagem.precoGasolina, dadosViagem.kmCidade, dadosViagem.kmRodovia),
                    (dadosViagem.kmCidade + dadosViagem.kmRodovia), dadosViagem.IdVeiculo);
        }

        return String.format("Custo da sua viagem é R$ %1$,.2f", dadosViagem.CustoViagem());
    }

    @PostMapping("/list")
    public List<Veiculo> list(@RequestBody CustoViagem dadosViagem) {
        return this.veiculoRepository.findAll();
    }

    @PostMapping("/list/menorcusto")
    public List<Veiculo> listmenor(@RequestBody CustoViagem dadosViagem) {
        return this.veiculoRepository.findMenorCusto(dadosViagem.getPrecoGasolina());
    }

    @PostMapping("/list/maiorcusto")
    public List<Veiculo> listmaior(@RequestBody CustoViagem dadosViagem) {
        return this.veiculoRepository.findMaiorCusto(dadosViagem.getPrecoGasolina());
    }

}
