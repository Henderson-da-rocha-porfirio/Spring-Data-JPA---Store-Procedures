package com.tuyo.produtodata.produtosdata;

import com.tuyo.produtodata.produtosdata.entities.*;
import com.tuyo.produtodata.produtosdata.repository.*;
import org.junit.jupiter.api.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testFindByDescContains() { // desc = description = descricao e não decrescente.
        List<Produto> produtos = repository.findByDescContains("Apple");
        produtos.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByPriceBetween() {
        List<Produto> produtos = repository.findByPriceBetween(500d, 2500d);
        produtos.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByDescLike() {
        List<Produto> produtos = repository.findByDescLike("%Garmin%");
        produtos.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindByIdsIn() {
        // Pageable pageable = new PageRequest(0, 2);
        Pageable pageable = PageRequest.of(0, 2);
        List<Produto> produtos = repository.findByIdIn(Arrays.asList(1, 2, 3), pageable);
        produtos.forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testFindAllPaging() {
        Pageable pageable = PageRequest.of(0, 2);
        Iterable<Produto> results = repository.findAll(pageable);
        results.forEach(p -> System.out.println(p.getName()));

    }

    @Test
    public void testFindAllSorting() {
        repository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"), new Sort.Order(null, "price")))
                .forEach(p -> System.out.println(p.getName()));

        // repository.findAll(Sort.by("name", "price")).forEach(p ->
        // System.out.println(p.getName()));

    }

    @Test
    public void testFindAllPagingAndSorting() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "name");
        repository.findAll(pageable).forEach(p -> System.out.println(p.getName()));

    }

/*    @Test
    @Transactional
    public void testCaching() {
        Session session = entityManager.unwrap(Session.class);
        Produto produto = repository.findById(1).get();

        repository.findById(1).get();

        session.evict(produto);

        repository.findById(1).get();
    }*/
}