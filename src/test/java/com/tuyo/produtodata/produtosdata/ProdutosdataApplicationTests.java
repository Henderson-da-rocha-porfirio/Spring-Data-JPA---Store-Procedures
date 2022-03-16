package com.tuyo.produtodata.produtosdata;

import com.tuyo.produtodata.produtosdata.entities.Produto;
import com.tuyo.produtodata.produtosdata.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) 			//Usando SpringRunner em vez de JUnit default.
@SpringBootTest
class ProdutosdataApplicationTests { 	// Junit testes

	@Autowired
	ProdutoRepository repository;

	@Test 								// Construtor marcado com a annotation Test.
	void contextLoads() {				// Esse teste ele vai correr e procurar por uma classe que tiver marcada, no classpath, com a @SprinBootApplication.
	}

	@Test
	void testCreate() { 				// Método que insere dados no banco através do teste.
		Produto produto = new Produto();
		produto.setId(1);
		produto.setName("Iphone");
		produto.setDesc("Awesome");
		produto.setPrice(1000d);

		repository.save(produto);
	}

}
