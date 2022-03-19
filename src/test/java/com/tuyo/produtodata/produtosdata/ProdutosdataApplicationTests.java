package com.tuyo.produtodata.produtosdata;

import com.tuyo.produtodata.produtosdata.entities.Produto;
import com.tuyo.produtodata.produtosdata.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)            //Usando SpringRunner em vez de JUnit default.
@SpringBootTest
class ProdutosdataApplicationTests {    // Junit testes

    @Autowired
    ProdutoRepository repository;

    @Test
        // Construtor marcado com a annotation Test.
    void contextLoads() {                // Esse teste ele vai correr e procurar por uma classe que tiver marcada, no classpath, com a @SprinBootApplication.
    }

    @Test
    void testCreate() {                // Método que insere dados no banco através do teste.
        Produto produto = new Produto();
        produto.setId(1);
        produto.setName("Iphone");
        produto.setDesc("Awesome");
        produto.setPrice(1000d);

        repository.save(produto);
    }

    @Test
    public void testRead() {
        Produto produto = repository.findById(1).get();
        assertNotNull(produto);
        assertEquals("Iphone", produto.getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + produto.getDesc());
    }

    @Test
    public void testUpdate() {
        Produto produto = repository.findById(1).get();
        produto.setPrice(1200d);
        repository.save(produto); // usar o mesmo método usado em testCreate
    }

	/*@Test
	public void testDelete() {
		repository.deleteById(1);
		}*/


    @Test
    public void testDelete() {
        if (repository.existsById(1)) {
            System.out.println("Deletando um produto");
            repository.deleteById(1);
        }
    }

    @Test
    public void testCount() {
        System.out.println("Total de Gravações===============>>>>>>>>>>>>>>>" + repository.count());
    }

    @Test
    public void testFindByName() { // permite testar para vermos os precos dos smartphones cadastrados através do console. Ou seja, achar métodos sem escrever nenhum código sql.
        List<Produto> produtos = repository.findByName("Smartphone");
        produtos.forEach(p -> System.out.println(p.getPrice()));            // realizando iteração (listagem) do produto usando java 8 syntazx
                                                                            // usando expressao lambda -> arrow. O "p" representa cada produto na lista de produto.
                                                                            // p.getPrice = imprimir o preço do produto.
        List<Produto> produtos1 = repository.findByName("Smartphone");
        produtos1.forEach(p -> System.out.println(p.getPrice()));
    }

    @Test
    public void testFindByNameAndDesc() {
        List<Produto> produtos = repository.findByNameAndDesc("Iphone", "Apple");
        produtos.forEach(p -> System.out.println(p.getPrice()));                        // Exemplo com uso de outra palavra-teste
    }

 /*   @Test
    public void testFindByPriceGreaterThan() { // Verifica por nome.
        List<Produto> produtos = repository.findByPriceGreaterThan(1000d);
        produtos.forEach(p -> System.out.println(p.getName()));
    }*/

    @Test
    public void testFindByPriceGreaterThan() { // Verificar por preço
        List<Produto> produtos = repository.findByPriceGreaterThan(1000d);
        produtos.forEach(p -> System.out.println(p.getPrice()));
    }
}