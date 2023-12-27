package br.com.improving.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.improving.carrinho.Item;
import br.com.improving.carrinho.Produto;

/**
 * @author Matheus
 * Testes unitários com a dependência Junit na classe Item
 */

public class ItemTest {

	@Test
	 public void atualizarValorTotal() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		BigDecimal valorTotal = item.atualizarValorTotal();

		assertEquals(BigDecimal.valueOf(20), valorTotal);
	}

	@Test
	public void atualizarQuantidadeComQuantidadePositiva() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		item.atualizarQuantidade(3);

		assertEquals(3, item.getQuantidade());
		assertEquals(BigDecimal.valueOf(30), item.atualizarValorTotal());
	}

	@Test
	public void atualizarQuantidadeComQuantidadeZero() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> item.atualizarQuantidade(0));

		assertEquals("A quantidade deve ser um valor positivo.", exception.getMessage());
		assertEquals(2, item.getQuantidade()); // Garante que a quantidade não foi alterada
		assertEquals(BigDecimal.valueOf(20), item.atualizarValorTotal()); // Garante que o valor total não foi alterado
	}

	@Test
	public void atualizarQuantidadeComQuantidadeNegativa() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> item.atualizarQuantidade(-1));

		assertEquals("A quantidade deve ser um valor positivo.", exception.getMessage());
		assertEquals(2, item.getQuantidade()); // Garante que a quantidade não foi alterada
		assertEquals(BigDecimal.valueOf(20), item.atualizarValorTotal()); // Garante que o valor total não foi alterado
	}

	@Test
	public void atualizarValorUnitarioComValorPositivo() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		item.atualizarValorUnitario(BigDecimal.valueOf(15));

		assertEquals(BigDecimal.valueOf(15), item.getValorUnitario());
		assertEquals(BigDecimal.valueOf(30), item.atualizarValorTotal());
	}

	@Test
	public void atualizarValorUnitarioComValorZero() {
		Item item = new Item(new Produto(1L, "Produto1"), BigDecimal.TEN, 2);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> {
					item.atualizarQuantidade(0);
					item.atualizarValorTotal(); // Chama explicitamente após a atualização da quantidade
				});

		assertEquals("A quantidade deve ser um valor positivo.", exception.getMessage());
		assertEquals("Garante que a quantidade não foi alterada", 2, item.getQuantidade());
		assertEquals("Garante que o valor total não foi alterado", BigDecimal.valueOf(20), item.atualizarValorTotal());

	}

	@Test
	public void atualizarValorUnitarioComValorNegativo() {
		Item item = new Item(new Produto(1L,"Produto1"), BigDecimal.TEN, 2);

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> item.atualizarValorUnitario(BigDecimal.valueOf(-5)));

		assertEquals("O valor unitário deve ser um valor positivo.", exception.getMessage());
		assertEquals(BigDecimal.TEN, item.getValorUnitario()); // Garante que o valor unitário não foi alterado
		assertEquals(BigDecimal.valueOf(20), item.atualizarValorTotal()); // Garante que o valor total não foi alterado
	}
}

