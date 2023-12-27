package br.com.improving.carrinho;

import java.math.BigDecimal;

/**
 * Classe que representa um item no carrinho de compras.
 */
public class Item {

    private Produto produto;
    private BigDecimal valorUnitario;
    private int quantidade;

    /**
     * Construtor da classe Item.
     * 
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public Item(Produto produto, BigDecimal valorUnitario, int quantidade) {
		this.produto = produto;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Produto getProduto() {
		return produto;
    }

    /**
     * Retorna o valor unitário do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorUnitario() {
		return valorUnitario;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantidade() {
		return quantidade;
    }
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	/**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal atualizarValorTotal() {
		// Atualiza o valor total sempre que o valor unitário ou a quantidade são alterados.
		return valorUnitario.multiply(BigDecimal.valueOf(quantidade));

    }
	/**
	 * Atualiza quantidade total de itens.
	 */

	public void atualizarQuantidade(int novaQuantidade) {
		if (novaQuantidade <= 0) {
			throw new IllegalArgumentException("A quantidade deve ser um valor positivo.");
		}
		this.quantidade = novaQuantidade;
		atualizarValorTotal();
	}

	public void atualizarValorUnitario(BigDecimal novoValorUnitario) {
		if (novoValorUnitario.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("O valor unitário deve ser um valor positivo.");
		}
		this.valorUnitario = novoValorUnitario;
		atualizarValorTotal();
	}
}
