package br.com.improving.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * @author Matheus
 */

@RunWith(Suite.class)
@SuiteClasses({CarrinhoComprasFactoryTest.class, CarrinhoComprasTest.class,
ItemTest.class, ProdutoTest.class})
public class AllTests {
}
