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

import com.ifto.oficina.dao.ClienteRepository;
import com.ifto.oficina.model.Cliente;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/oficina")


public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/cliente")
    List<Cliente> all() {
        return clienteRepository.findAll();
    }

    @GetMapping("/cliente/{cpf}")
    Optional<Cliente> getById(@PathVariable String cpf) { //trocar pelo cpf
        return clienteRepository.findById(cpf);
    }

    @PostMapping("/cliente")
    Cliente createNew(@Valid @RequestBody Cliente novoCliente) {
        novoCliente.setCpf(null);
        return clienteRepository.save(novoCliente);
    }

    @DeleteMapping("/cliente/{cpf}")
    void delete(@PathVariable String cpf) {
        clienteRepository.deleteById(cpf);
    }

    @PutMapping("/cliente/{cpf}")
    Cliente updateOrCreate(@RequestBody Cliente novoCliente, @PathVariable String cpf) {

        return clienteRepository.findById(cpf)
                .map(cliente -> {
                    cliente.setCpf(novoCliente.getCpf());
                    cliente.setNome(novoCliente.getNome());
                    cliente.setEndereco(novoCliente.getEndereco());
                    cliente.setTelefone(novoCliente.getTelefone());
                    cliente.setCarros(novoCliente.getCarros());
                    return clienteRepository.save(cliente);
                })
                .orElseGet(() -> {
                    novoCliente.setCpf(cpf);
                    return clienteRepository.save(novoCliente);
                });
    }
}
