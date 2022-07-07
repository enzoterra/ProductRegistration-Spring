package br.edu.ifms.provalp4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifms.provalp4.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
