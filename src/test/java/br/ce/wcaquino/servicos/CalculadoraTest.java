package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	private Calculadora calculadora;
	
	@Before
	public void setUp() {
		calculadora = new Calculadora();
	}
	
	@Test
	public void deveSomarDoisValores() {
		//cenario
		int a = 5;
		int b = 3;
		
		//a��o
		int resultado = calculadora.somar(a, b);
		
		
		//verifica��o
		Assert.assertEquals(8, resultado, 0.1);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		int a = 8;
		int b = 5;
		
		//a��o
		int resultado = calculadora.subtrair(a, b);
		
		//verifica��o
		Assert.assertEquals(3, resultado, 0.01);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		//cenario
		int a = 6;
		int b = 3;
		
		//a��o
		int resultado = calculadora.dividir(a, b);
		
		
		//verica��o
		Assert.assertEquals(2, resultado, 0.1);
	}
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		//cenario
		int a = 10;
		int b = 0;
		
		//a��o
		calculadora.dividir(a, b);

		
		//verifica��o
	}
}
