package br.com.improving.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;


import br.com.improving.carrinho.CarrinhoCompras;
import br.com.improving.carrinho.Produto;


/**
 * @author Matheus
 * Testes unitários com a dependência Junit na classe CarrinhoCompras
 */

public class CarrinhoComprasTest {

	@Test
	 public void adicionarItem() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto = new Produto(1l, "Produto1");
		BigDecimal valorUnitario = BigDecimal.TEN;
		int quantidade = 2;

		carrinho.adicionarItem(produto, valorUnitario, quantidade);

		assertEquals(1, carrinho.getItens().size());
		assertEquals(valorUnitario.multiply(BigDecimal.valueOf(quantidade)), carrinho.getValorTotal());
	}

	@Test
	 public void adicionarItemComQuantidadeZero() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto = new Produto(1l, "Produto1");
		BigDecimal valorUnitario = BigDecimal.TEN;
		int quantidade = 0;

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> carrinho.adicionarItem(produto, valorUnitario, quantidade));

		assertEquals("Quantidade e valor unitário devem ser valores positivos.", exception.getMessage());
		assertTrue(carrinho.getItens().isEmpty());
	}

	@Test
	 public void removerItem() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto = new Produto(1l, "Produto1");
		BigDecimal valorUnitario = BigDecimal.TEN;
		int quantidade = 2;

		carrinho.adicionarItem(produto, valorUnitario, quantidade);

		assertTrue(carrinho.removerItem(produto));
		assertTrue(carrinho.getItens().isEmpty());
		assertEquals(BigDecimal.ZERO, carrinho.getValorTotal());
	}

	@Test
	 public void removerItemQueNaoExiste() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto1 = new Produto(1l, "Produto1");
		Produto produto2 = new Produto(2l, "Produto2");

		carrinho.adicionarItem(produto1, BigDecimal.TEN, 2);

		assertFalse(carrinho.removerItem(produto2));
		assertEquals(1, carrinho.getItens().size());
	}

	@Test
	 public void removerItemPorPosicao() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto1 = new Produto(1l, "Produto1");
		Produto produto2 = new Produto(2l, "Produto2");

		carrinho.adicionarItem(produto1, BigDecimal.TEN, 2);
		carrinho.adicionarItem(produto2, BigDecimal.valueOf(5), 3);

		assertTrue(carrinho.removerItem(0));
		assertEquals(1, carrinho.getItens().size());
		assertEquals(produto2, carrinho.getItens().iterator().next().getProduto());
	}

	@Test
	 public void removerItemPorPosicaoInvalida() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto = new Produto(1l, "Produto1");

		carrinho.adicionarItem(produto, BigDecimal.TEN, 2);

		assertFalse(carrinho.removerItem(1));
		assertEquals(1, carrinho.getItens().size());
	}

	@Test
	public void getValorTotal() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto1 = new Produto(1l, "Produto1");
		Produto produto2 = new Produto(2l, "Produto2");

		carrinho.adicionarItem(produto1, BigDecimal.valueOf(10), 2);
		carrinho.adicionarItem(produto2, BigDecimal.valueOf(5), 3);

		assertEquals(BigDecimal.valueOf(35), carrinho.getValorTotal());
	}

	@Test
	public void getItens() {
		CarrinhoCompras carrinho = new CarrinhoCompras();
		Produto produto1 = new Produto(1l, "Produto1");
		Produto produto2 = new Produto(2l, "Produto2");

		carrinho.adicionarItem(produto1, BigDecimal.TEN, 2);
		carrinho.adicionarItem(produto2, BigDecimal.valueOf(5), 3);

		assertEquals(2, carrinho.getItens().size());
	}
}



