package com.ifto.oficina.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Carro> carros;
    private String nome;
    
    private String telefone;
    private String endereco;
}

