package dev.gabrielgrazziani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrielgrazziani.model.ItemOrdemServico;

public interface ItemRepository extends JpaRepository<ItemOrdemServico, Long>{

}
