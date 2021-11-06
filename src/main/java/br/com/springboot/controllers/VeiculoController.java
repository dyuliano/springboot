package br.com.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.model.Veiculo;
import br.com.springboot.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("")
    public String index() {
        String msg = "API\nSpring Boot com Hibernate";
        return msg;
    }

    @GetMapping("/buscar/{id}")
    public Veiculo buscar(@PathVariable("id") Long id) {
        System.out.println(String.format("O id é: %s", id));
        Optional<Veiculo> veiculosFind = this.veiculoRepository.findById(id);

        if (veiculosFind.isPresent()) {
            return veiculosFind.get();
        }
        return null;
    }

    @PostMapping("incluir")
    public Veiculo veiculo(@RequestBody Veiculo veiculo) {
        return this.veiculoRepository.save(veiculo);
    }

    @GetMapping("/listar")
    public List<Veiculo> list() {
        return this.veiculoRepository.findAll();
    }

}
