package com.tuyo.produtodata.produtosdata;

import com.tuyo.produtodata.produtosdata.entities.Produto;
import com.tuyo.produtodata.produtosdata.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)            //Usando SpringRunner em vez de JUnit default.
@SpringBootTest
class ProdutosdataStoreApplicationTests {    // Junit testes

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
    public void testFindAllPaging() {                               // Criando Paging
        // FindAll methods só apareceram apra serem utilizado depois de ser implementado em ProdutoRepository: PagingAndSortingRepository
        Pageable pageable = PageRequest.of(0, 1);         // Usando Pageable de Spring Data e não do java.awt. Pageable é uma Interface e por isso não é possível criar uma instância (instanceOf)
        Iterable<Produto> results = repository.findAll(pageable);   // Tem que criar uma PageRequest: Um Objeto PageRequest é uma implementação do pageable do heat control.
        results.forEach(p -> System.out.println(p.getName()));      // Vamos utilizar o PageRequest com dois parâmetros: page e size.
        // page: é a página que eu quero acessar. Por exemplo, nós temos 5 gravações no database:
        // E podemos recuperar somente uma gravação por página. E nesse caso, só podemos acessar as páginas 0 ou 1 ou 2 ou 3.
        // size: é o tamanho de quantas gravações queremos por página.
        // page index: começa com 0 e não com 1. Então, "0", é a primeira página que queremos acesar.
        // size: vamos começar com "1", indicando que apenas uma gravação apareça por página se eu acessar a página "0"(primeira página).
        // Iterable<Produto> ... = estamos assinando um estado comportamental, sequencial, para uma nova local variável com a Interface Iterable.
        // Isso retorna uma página exclusiva de produtos onde poderemos interar ( listar em sequência ).
        // forEach: para usar uma expressão lambda (economia de código numa listagem).
        // forEach: eu pego os resultados passados em cada forEach e os uso na expressao lambda para cada produto.
        // Em Iterable, que funciona igual a uma lista ( para isso ocorrer, eu usei a expressão Lambda que mostra o nome da página.
        // Acessar Page: só mudar em page a que deseja mostrar: 0, 1, 2, 3 ou 4. (equivalente a 5 páginas)
        // Size: Quantidade de páginas: como temos apenas 5 páginas, podemos colocar no máximo 2 em size. Se tivéssemos 6, aí conseguiríamos e assim por diante.
    }

    @Test
    public void testFindAllSorting() {      // Criando Sorting
        // Quando se utiliza o Sorting, temos que pensar em qual tipo queremos utilizar:
        // 1. properties: colunas no database. Deve se usar apenas quando quiser que o construtor pegue um número de propriedades da nossa entidade.
        // ficaria algo do tipo passando a propriedade "name" que está na entidade Produto: new Sort("name").
        //E caso não especifique a ordem da listagem, ela aparecerá como ascendente.
        // 2. orders: direção
        repository.findAll(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"), new Sort.Order(null, "price"))) // Order: é uma Innerclasse de Sort Class (escolher Order() Anonymous Inner Type.
                // Sort está "sorteando" por nome de forma decrescente.
                .forEach(p -> System.out.println(p.getName()));

        // repository.findAll(Sort.by("name", "price")).forEach(p -> System.out.println(p.getName()));                  // Ao utilizarmos aqui o .forEach estamos eliminando esse pedaço de código: Iterable<Produto>findAll = ... porque ele elimina o Iterable.
        // Ele sorteia em ordem alfabética.
        // repository.findAll(new Sort(Direction.DESC, "name", "price")).forEach(p -> System.out.println(p.getName())); // Em ordem decrescente.
    }

    @Test
    public void testFindAllPagingAndSorting() {                                                         //Paging e Sorting sendo usados juntos
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.DESC, "name");
        repository.findAll(pageable).forEach(p -> System.out.println(p.getName()));

    }

    @Test
    public void testFindAllProdutos(){

        System.out.println(repository.findAllProdutos());

    }
}
