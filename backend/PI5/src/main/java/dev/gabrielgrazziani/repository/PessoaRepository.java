package dev.gabrielgrazziani.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrielgrazziani.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Optional<Pessoa> findByEmail(String email);
}
