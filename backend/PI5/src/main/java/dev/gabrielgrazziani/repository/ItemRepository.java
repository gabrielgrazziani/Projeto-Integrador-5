package dev.gabrielgrazziani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gabrielgrazziani.model.ItemOrdemServico;
import dev.gabrielgrazziani.model.OrdemServico;

public interface ItemRepository extends JpaRepository<ItemOrdemServico, Long>{

	List<ItemOrdemServico> findByOrdemServico(OrdemServico ordemServico);

}
