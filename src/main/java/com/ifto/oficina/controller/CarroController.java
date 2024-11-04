package com.ifto.oficina.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifto.oficina.dao.CarroRepository;
import com.ifto.oficina.model.Carro;

@RestController
@RequestMapping("/oficina")
public class CarroController {
     @Autowired
    CarroRepository carroRepository;

    @GetMapping("/carro")
    List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    @GetMapping("/carro/{id}")
    Optional<Carro> getById(@PathVariable Integer id) { //mudar para placa
        return carroRepository.findById(id);
    }

    @PostMapping("/carro")
    Carro createNew(@RequestBody Carro novoCarro) {
        novoCarro.setId(null);
        return carroRepository.save(novoCarro);
    }

    @DeleteMapping("/carro/{id}")
    void delete(@PathVariable Integer id) {
        carroRepository.deleteById(id);
    }

    @PutMapping("/carro/{id}")
    Carro updateOrCreate(@RequestBody Carro novoCarro, @PathVariable Integer id) {

        return carroRepository.findById(id)
                .map(carro -> {
                    carro.setModelo(novoCarro.getModelo());
                    carro.setMarca(novoCarro.getMarca());
                    carro.setAno(novoCarro.getAno());
                    carro.setPlaca(novoCarro.getPlaca());
                    return carroRepository.save(carro);
                })
                .orElseGet(() -> {
                    novoCarro.setId(id);
                    return carroRepository.save(novoCarro);
                });
    }
    
}
