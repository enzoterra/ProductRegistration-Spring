package br.edu.ifms.provalp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifms.provalp4.modelo.Informacoes;

public interface InformacoesRepository extends JpaRepository<Informacoes, Long> {

	@Query(value = "SELECT i.id FROM informacoes i WHERE i.idproduto=?1", nativeQuery=true)
	Informacoes findByProdutoId(long idProduto);
}
