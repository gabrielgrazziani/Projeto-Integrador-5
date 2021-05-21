package dev.gabrielgrazziani.configurer;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import dev.gabrielgrazziani.model.ServicoProduto;
import dev.gabrielgrazziani.model.Tipo;
import dev.gabrielgrazziani.repository.ServicoProdutoRepository;

@Configuration
public class DadosTesteServicoProduto {

	@Autowired
	private ServicoProdutoRepository repository;
	
	@PostConstruct
	@Profile("dev")
	public void criarServicoProduto() {
		ServicoProduto sp1 = ServicoProduto.builder()
			.nome("Lapis")
			.tipo(Tipo.PRODUTO)
			.unidadeMedida("KG")
			.valorComercial(new BigDecimal("1"))
			.valorCusto(new BigDecimal("0.5"))
			.build();
		
		ServicoProduto sp2 = ServicoProduto.builder()
			.nome("Limpesa")
			.tipo(Tipo.SERVICO)
			.unidadeMedida("")
			.valorComercial(new BigDecimal("80"))
			.valorCusto(new BigDecimal("60"))
			.build();
		
		this.repository.save(sp1);
		this.repository.save(sp2);
	}
}
