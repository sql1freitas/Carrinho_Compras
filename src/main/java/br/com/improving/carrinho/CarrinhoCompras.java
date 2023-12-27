package br.com.improving.carrinho;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class CarrinhoCompras {

	private List<Item> itemList;

	/**
	 * @author Matheus
	 */
	public CarrinhoCompras(){
		this.itemList = new ArrayList<>();
	}



    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param produto
     * @param valorUnitario
     * @param quantidade
     */
    public void adicionarItem(Produto produto, BigDecimal valorUnitario, int quantidade) {
		if (quantidade <= 0 || valorUnitario.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Quantidade e valor unitário devem ser valores positivos.");
		}

		for (Item item : itemList) {
			if (item.getProduto().equals(produto)) {
				item.atualizarQuantidade(quantidade);
				item.atualizarValorUnitario(valorUnitario);
				return;
			}
		}

		itemList.add(new Item(produto, valorUnitario, quantidade));
	}



    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param produto
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(Produto produto) {

		List<Item> itensAtualizados = itemList.stream()
				.filter(item -> !item.getProduto().equals(produto))
				.collect(Collectors.toList());

		// Se a lista foi modificada, significa que o produto foi removido
		boolean produtoRemovido = itemList.size() != itensAtualizados.size();

		itemList = itensAtualizados; // Atualiza a lista de itens
		return produtoRemovido;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na 
     * coleção, em que zero representa o primeiro item.
     *
     * @param posicaoItem
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removerItem(int posicaoItem) {
		if (posicaoItem >= 0 && posicaoItem < itemList.size()) {
			itemList.remove(posicaoItem);
			return true;
		}
		return false;
	}

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getValorTotal() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Item item : itemList) {
			valorTotal = valorTotal.add(item.atualizarValorTotal());
		}
		return valorTotal;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return itens
     */
    public Collection<Item> getItens() {
		return new ArrayList<>(itemList);
    }
}