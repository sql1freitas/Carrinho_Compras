package br.com.improving.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import br.com.improving.carrinho.CarrinhoCompras;
import br.com.improving.carrinho.CarrinhoComprasFactory;
import br.com.improving.carrinho.Produto;



/**
 * @author Matheus
 * Testes unitários com a dependência Junit na classe CarrinhoComprasFactory
 */


public class CarrinhoComprasFactoryTest {

	@Test
	 public void criarCarrinhoNovo() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
		String identificacaoCliente = "cliente1";

		CarrinhoCompras carrinho = factory.criar(identificacaoCliente);

		assertNotNull(carrinho);
		assertTrue(factory.getCarrinhosMap().containsKey(identificacaoCliente));
	}

	@Test
	public void criarCarrinhoExistente() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
		String identificacaoCliente = "cliente1";

		CarrinhoCompras carrinho1 = factory.criar(identificacaoCliente);
		CarrinhoCompras carrinho2 = factory.criar(identificacaoCliente);

		assertNotNull(carrinho1);
		assertSame(carrinho1, carrinho2);
		assertTrue(factory.getCarrinhosMap().containsKey(identificacaoCliente));
	}

	@Test
	public void getValorTicketMedioSemCarrinhos() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();

		BigDecimal valorTicketMedio = factory.getValorTicketMedio();

		assertEquals(BigDecimal.ZERO, valorTicketMedio);
	}

	@Test
	public void getValorTicketMedioComCarrinhos() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
		CarrinhoCompras carrinho1 = factory.criar("cliente1");
		carrinho1.adicionarItem(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		CarrinhoCompras carrinho2 = factory.criar("cliente2");
		carrinho2.adicionarItem(new Produto(2L,"Produto2"), BigDecimal.valueOf(5), 3);

		BigDecimal valorTicketMedio = factory.getValorTicketMedio();

		assertEquals(BigDecimal.valueOf(35).divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP), valorTicketMedio);
	}
	@Test
	 public void invalidarCarrinhoExistente() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
		String identificacaoCliente = "cliente1";
		factory.criar(identificacaoCliente);

		boolean result = factory.invalidar(identificacaoCliente);

		assertTrue(result);
		assertFalse(factory.getCarrinhosMap().containsKey(identificacaoCliente));
	}

	@Test
	public void invalidarCarrinhoInexistente() {
		CarrinhoComprasFactory factory = new CarrinhoComprasFactory();
		String identificacaoCliente = "cliente1";

		boolean result = factory.invalidar(identificacaoCliente);

		assertFalse(result);
		assertFalse(factory.getCarrinhosMap().containsKey(identificacaoCliente));
	}
}


