package com.tuyo.produtodata.produtosdata.repository;

import com.tuyo.produtodata.produtosdata.entities.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {           // É preciso prover genérica informação que este repositório CRUD espera.
                                                                                // <T, Id> = Tipo: Entidade Produto. E Id: Integer. Lá está como tipo primitivo "int", colocamos aqui com a classe " Integer ".
}
