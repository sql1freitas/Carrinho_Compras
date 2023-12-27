package br.com.improving.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.improving.carrinho.Produto;


/**
 * @author Matheus
 * Testes unitários com a dependência Junit na classe Produto
 */

public class ProdutoTest {

	@Test
	public void equalsComObjetoIgual() {
		Produto produto1 = new Produto(1L, "Produto1");
		Produto produto2 = produto1;

		assertTrue(produto1.equals(produto2));
	}

	@Test
	public void equalsComObjetoNulo() {
		Produto produto1 = new Produto(1L, "Produto1");

		assertFalse(produto1.equals(null));
	}

	@Test
	public void equalsComClassesDiferentes() {
		Produto produto1 = new Produto(1L, "Produto1");
		Object objetoDiferente = new Object();

		assertFalse(produto1.equals(objetoDiferente));
	}

	@Test
	public void equalsComObjetosIguais() {
		Produto produto1 = new Produto(1L, "Produto1");
		Produto produto2 = new Produto(1L, "Produto1");

		assertTrue(produto1.equals(produto2));
	}

	@Test
	public void equalsComObjetosDiferentes() {
		Produto produto1 = new Produto(1L, "Produto1");
		Produto produto2 = new Produto(2L, "Produto2");

		assertFalse(produto1.equals(produto2));
	}

	@Test
	public void hashCodeComObjetosIguais() {
		Produto produto1 = new Produto(1L, "Produto1");
		Produto produto2 = new Produto(1L, "Produto1");

		assertEquals(produto1.hashCode(), produto2.hashCode());
	}

	@Test
	public void hashCodeComObjetosDiferentes() {
		Produto produto1 = new Produto(1L, "Produto1");
		Produto produto2 = new Produto(2L, "Produto2");

		assertNotEquals(produto1.hashCode(), produto2.hashCode());
	}
}