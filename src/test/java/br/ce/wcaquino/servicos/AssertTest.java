package br.ce.wcaquino.servicos;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
@Test
public void test() {
	assertTrue(true);
	assertFalse(false);
	
	assertEquals("Erro de comparacao",1, 2);
	assertEquals(0.51234, 0.512, 0.001);
	assertEquals(Math.PI, 3.14, 0.01);
	
	int i = 4;
	Integer i2 = 4;
	assertEquals(Integer.valueOf(i), i2.intValue(), 0.001);
	assertEquals(i, i2.intValue(), 0.01);
	
	assertEquals("bola", "bola");
	assertNotEquals("bola", "casa");
	assertTrue("bola".equalsIgnoreCase("Bola"));
	assertTrue("bola".startsWith("bo"));
	
	Usuario u1 = new Usuario("Usuario 1");
	Usuario u2 = new Usuario("Usuario 1");
	Usuario u3 = null;
	
	assertEquals(u1, u2);
	
	assertSame(u2, u2);
	assertNotSame(u1, u2);
	assertNull(u3);
	//assertNotNull(u3);
}
}
