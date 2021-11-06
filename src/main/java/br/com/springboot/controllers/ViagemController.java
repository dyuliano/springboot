package br.com.springboot.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Veiculo;
import br.com.springboot.model.VeiculoViagem;
import br.com.springboot.model.Viagem;
import br.com.springboot.repository.VeiculoRepository;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostMapping("")
    public String custoviagem(@RequestBody Viagem dadosViagem) {

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
    public List<VeiculoViagem> list(@RequestBody Viagem dadosViagem) {

        List veiculos = this.veiculoRepository.findAll();
        List<VeiculoViagem> listaVeiculos = new ArrayList<VeiculoViagem>();

        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo _veiculo = (Veiculo) veiculos.get(i);
            VeiculoViagem _veiculoViagem = new VeiculoViagem();

            _veiculoViagem.setNome(_veiculo.getNome());
            _veiculoViagem.setMarca(_veiculo.getMarca());
            _veiculoViagem.setModelo(_veiculo.getModelo());
            _veiculoViagem.setAno(_veiculo.getDataFabricação());
            _veiculoViagem.setQuantidadeCombustivel(
                    _veiculo.QuantidadeCombustivelViagem(dadosViagem.kmCidade, dadosViagem.kmRodovia));
            _veiculoViagem.setValorTotalCombustivel(_veiculo.ValorCombustivelViagem(dadosViagem.precoGasolina,
                    dadosViagem.kmCidade, dadosViagem.kmRodovia));

            listaVeiculos.add(_veiculoViagem);
        }

        Collections.sort(listaVeiculos, new CompararVeiculosViagem());

        return listaVeiculos;
    }

    @PostMapping("/list/menorcusto")
    public List<Veiculo> listmenor(@RequestBody Viagem dadosViagem) {
        return this.veiculoRepository.findMenorCusto(dadosViagem.getPrecoGasolina());
    }

    @PostMapping("/list/maiorcusto")
    public List<Veiculo> listmaior(@RequestBody Viagem dadosViagem) {
        return this.veiculoRepository.findMaiorCusto(dadosViagem.getPrecoGasolina());
    }

}

class CompararVeiculosViagem implements Comparator<VeiculoViagem> {
    public int compare(VeiculoViagem v1, VeiculoViagem v2) {
        if (v1.getValorTotalCombustivel() < v2.getValorTotalCombustivel())
            return -1;
        else if (v1.getValorTotalCombustivel() > v2.getValorTotalCombustivel())
            return +1;
        else
            return 0;
    }
}
