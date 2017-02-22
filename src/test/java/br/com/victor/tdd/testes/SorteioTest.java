package br.com.victor.tdd.testes;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.victor.tdd.builders.CriadorDePromocao;
import br.com.victor.tdd.model.Cliente;
import br.com.victor.tdd.model.Lance;
import br.com.victor.tdd.model.Promocao;
import br.com.victor.tdd.service.Sorteio;

public class SorteioTest {

	private Sorteio sorteio;

	private Cliente victor;
	private Cliente rafael;
	private Cliente maria;
	private Cliente joao;
	private Cliente jose;
	private Cliente roberto;
	private Cliente karine;

	@Before
	public void setUp() {
		this.sorteio = new Sorteio();
		this.victor = new Cliente("Victor Alcantara");
		this.rafael = new Cliente("Rafael");
		this.maria = new Cliente("Maria");
		this.joao = new Cliente("Joao");
		this.jose = new Cliente("Jose");
		this.roberto = new Cliente("Roberto");
		this.karine = new Cliente("Karine");
	}

	@Test
	public void deveSortearEmOrdemCrescente() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Xbox One")
			.comLance(victor, new BigDecimal("400.0"))
			.comLance(rafael, new BigDecimal("200.0"))
			.comLance(joao, new BigDecimal("100.0"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		BigDecimal maiorValorEsperado = new BigDecimal("400.0");
		BigDecimal menorValorEsperado = new BigDecimal("100.0");

		// Validação
		assertEquals(maiorValorEsperado, sorteio.getMaiorLance());
		assertEquals(menorValorEsperado, sorteio.getMenorLance());
	}

	@Test
	public void deveSortearEmOrdemDecrescente() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Toyota Corolla")
			.comLance(maria, new BigDecimal("1050"))
			.comLance(victor, new BigDecimal("3700"))
			.comLance(rafael, new BigDecimal("840"))
			.comLance(joao, new BigDecimal("8000"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		BigDecimal maiorValorEsperado = new BigDecimal("8000");
		BigDecimal menorValorEsperado = new BigDecimal("840");

		// Validação
		assertEquals(maiorValorEsperado, sorteio.getMaiorLance());
		assertEquals(menorValorEsperado, sorteio.getMenorLance());
	}

	@Test
	public void deveSortearEmOrdemAleatoria() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Geladeira Consul")
			.comLance(victor, new BigDecimal("2500.0"))
			.comLance(maria, new BigDecimal("3200.0"))
			.comLance(rafael, new BigDecimal("1400.0"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		BigDecimal maiorValorEsperado = new BigDecimal("3200.0");
		BigDecimal menorValorEsperado = new BigDecimal("1400.0");

		// Validação
		assertEquals(maiorValorEsperado, sorteio.getMaiorLance());
		assertEquals(menorValorEsperado, sorteio.getMenorLance());
	}

	@Test
	public void deveSortearComApenasUmLance() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Playstation 4")
			.comLance(victor, new BigDecimal("400.0"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		BigDecimal maiorValorEsperado = new BigDecimal("400.0");
		BigDecimal menorValorEsperado = new BigDecimal("400.0");

		// Validação
		assertEquals(maiorValorEsperado, sorteio.getMaiorLance());
		assertEquals(menorValorEsperado, sorteio.getMenorLance());
	}

	@Test
	public void deveSortearTresMenoresLances() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Bicicleta Caloi Master")
			.comLance(victor, new BigDecimal("800"))
			.comLance(maria, new BigDecimal("500"))
			.comLance(rafael, new BigDecimal("900"))
			.comLance(joao, new BigDecimal("200"))
			.comLance(jose, new BigDecimal("100"))
			.comLance(roberto, new BigDecimal("700"))
			.comLance(karine, new BigDecimal("600"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		// Validação
		assertEquals(3, menores.size());
		assertEquals(new BigDecimal("100"), menores.get(0).getValor());
		assertEquals(new BigDecimal("200"), menores.get(1).getValor());
		assertEquals(new BigDecimal("500"), menores.get(2).getValor());
	}

	@Test
	public void deveSortearTresMenoresLancesComListaDeTamanhoMenorDoQue3() {
		// Cenário
		Promocao promocao = new CriadorDePromocao().para("Cafeteira Mallory")
			.comLance(maria, new BigDecimal("500"))
			.comLance(rafael, new BigDecimal("900"))
			.cria();

		// Ação
		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		// Validação
		assertEquals(2, menores.size());
		assertEquals(new BigDecimal("500"), menores.get(0).getValor());
		assertEquals(new BigDecimal("900"), menores.get(1).getValor());
	}

	@Test
	public void naoDeveSortearQuandoNaoHouverLances() {
		// Cenário
		Promocao promocao = new Promocao("Bicicleta Caloi Master");

		sorteio.sorteia(promocao);

		List<Lance> menores = sorteio.getTresMenoresLances();

		// Validação
		assertEquals(0, menores.size());
	}

}
