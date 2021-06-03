package dev.gabrielgrazziani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrielgrazziani.model.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long>{

}
