package com.ifto.oficina;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ifto.oficina.dao.CarroRepository;
import com.ifto.oficina.dao.ClienteRepository;
import com.ifto.oficina.model.Carro;
import com.ifto.oficina.model.Cliente;

/**
 * @autores : Ananda, Lais e Yago
 */

@SpringBootApplication
public class OficinaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OficinaApplication.class, args);
	}

	@Autowired
	CarroRepository carroRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public void run(String... args) throws Exception {
	List<Carro> carros = new ArrayList<>();

		carros.add(carroRepository.save(new Carro(null, "XXX-1234", "Fiat", "Fastback", 2024)));
		carros.add(carroRepository.save(new Carro(null, "yyy-3456", "Hyundai", "HB20", 2022)));

		Cliente cliente = new Cliente("123.456.789-22", carros, "Ananda Krishina", "(63)91234-5678","Palmas, Tocantins");
		

		clienteRepository.save(cliente);

		carros = new ArrayList<>();

		carros.add(carroRepository.save(new Carro(null, "rty-4657", "Fiat", "Argo", 2023)));

		clienteRepository.save(new Cliente("321.654.987-11", carros, "Lais Baltar", "(63)94321-8765","Porto Nacional, Tocantins"));

	}
}
