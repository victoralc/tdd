package br.com.victor.tdd.testes;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.victor.tdd.builders.CriadorDePromocao;
import br.com.victor.tdd.model.Cliente;
import br.com.victor.tdd.model.Lance;
import br.com.victor.tdd.model.Promocao;

public class PromocaoTest {
	
	
	private Cliente maria;
	private Cliente victor;

	@Before
	public void setUp() {
		this.victor = new Cliente("Victor");
		this.maria = new Cliente("Maria");

	}

	@Test
	public void deveRegistrarUmLance(){
		Promocao promocao = new CriadorDePromocao().para("Macbook Pro")
				.comLance(victor, new BigDecimal("800.0")).cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(1, lances.size());
		assertEquals(new BigDecimal("800.0"), lances.get(0).getValor());
		
	}
	
	@Test
	public void deveRegistrarVariosLances(){
		Promocao promocao = new CriadorDePromocao().para("Macbook Pro")
				.comLance(victor, new BigDecimal("800.0"))
				.comLance(maria, new BigDecimal("700.0"))
				.cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(2, lances.size());
		assertEquals(new BigDecimal("800.0"), lances.get(0).getValor());
		assertEquals(new BigDecimal("700.0"), lances.get(1).getValor());
		
	}
	
	@Test
	public void naoDeveRegistrarDoisLancesSeguidosDoMesmoCliente(){
		Promocao promocao = new CriadorDePromocao().para("Ipad Plus")
				.comLance(victor, new BigDecimal("1200.0"))
				.comLance(victor, new BigDecimal("1700.0")) //deve ignorar
				.cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(1, lances.size());
		assertEquals(new BigDecimal("1200.0"), lances.get(0).getValor());
		
	}
	
	@Test
	public void naoDeveRegistrarMaisDoQueCincoLancesDoMesmoCliente(){
		Promocao promocao = new CriadorDePromocao().para("Iphone 7")
				.comLance(victor, new BigDecimal("200.0"))
				.comLance(maria, new BigDecimal("700.0"))
				.comLance(victor, new BigDecimal("300.0"))
				.comLance(maria, new BigDecimal("900.0"))
				.comLance(victor, new BigDecimal("600.0"))
				.comLance(maria, new BigDecimal("500.0"))
				.comLance(victor, new BigDecimal("700.0"))
				.comLance(maria, new BigDecimal("100.0"))
				.comLance(victor, new BigDecimal("300.0"))
				.comLance(maria, new BigDecimal("400.0"))
				.comLance(victor, new BigDecimal("800.0")) //ignora
				.comLance(maria, new BigDecimal("900.0")) //ignora
				.cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(10, lances.size());
		
	}
	
	
}
