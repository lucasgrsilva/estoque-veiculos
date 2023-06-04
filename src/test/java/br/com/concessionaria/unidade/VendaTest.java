package br.com.concessionaria.unidade;

import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


//ATENÇÃO
//Essa classe não tem a intenção de propor testes unitários "úteis",
//visto que são em sua maioria 'Gets' e 'Sets'
//Esta classe existe tão somente para demonstração da aplicação do CodeCov

class VendaTest {
    private Venda venda;
    private Cliente clienteVenda, clienteVenda2;
    private int id;
    private LocalDate dataDaCompra;
    private BigDecimal precoDeVenda;
    private Carro carroVenda, carroVenda2;
    private MetodoDePagamento metodoPagamento;
    private int numParcelas;

    @BeforeEach
    void setUp(){
        id = 1;

        dataDaCompra = LocalDate.now();

        precoDeVenda = BigDecimal.valueOf(50000);

        carroVenda = new Carro(5, 555, "abc5555", "Fiesta", 150,
                BigDecimal.valueOf(1000), true, 12, MarcasCarro.Ford, 2021,
                LocalDate.parse("2021-01-01"), BigDecimal.valueOf(45000), BigDecimal.valueOf(55000));

        carroVenda2 = new Carro(6, 400, "abc5556", "Corolla", 200,
                BigDecimal.valueOf(1000), true, 14, MarcasCarro.Toyota, 2018,
                LocalDate.parse("2021-01-01"), BigDecimal.valueOf(60000), BigDecimal.valueOf(70000));

        metodoPagamento = MetodoDePagamento.CARTAO_DE_CREDITO;

        numParcelas = 12;

        Endereco endereco = new Endereco("Rua A", 10, "Federal", "12.345-678", "MG",
                "Contagem");

        clienteVenda = new Cliente(12,"Elon", "(31)40028922", endereco, "123.456.789-10");

        clienteVenda2 = new Cliente(13,"Musk", "(31)40028922", endereco, "000.456.789-10");

        venda = new Venda(id, dataDaCompra, precoDeVenda, carroVenda, clienteVenda,
                metodoPagamento, numParcelas);

    }
    @Test
        //Em teoria é uma má pratica testar vários metodos num só, porém, pela trivialidade dos Getters e Setters, achei mais viável fazer assim.
    void testGetters() {
        assertEquals(id, venda.getId());
        assertEquals(dataDaCompra, venda.getDataDaCompra());
        assertEquals(precoDeVenda, venda.getPrecoDeVenda());
        assertEquals(carroVenda, venda.getVeiculoVendido());
        assertEquals(clienteVenda, venda.getClienteVenda());
        assertEquals(metodoPagamento, venda.getMetodoPagamento());
        assertEquals(numParcelas, venda.getNumParcelas());
    }

    @Test
    void testSetId() {
        int id = 300;
        venda.setId(id);
        assertEquals(id, venda.getId());
    }

    @Test
    void testSetDataDaCompra() {
        LocalDate dataDaCompra = LocalDate.of(2020, 3, 18);
        venda.setDataDaCompra(dataDaCompra);
        assertEquals(dataDaCompra, venda.getDataDaCompra());
    }

    @Test
    void testSetPrecoDeVenda() {
        BigDecimal precoDeVenda = BigDecimal.valueOf(70000);
        venda.setPrecoDeVenda(precoDeVenda);
        assertEquals(precoDeVenda, venda.getPrecoDeVenda());
    }

    @Test
    void testSetVeiculoVendido() {
        venda.setVeiculoVendido(carroVenda2);
        assertEquals(carroVenda2, venda.getVeiculoVendido());
    }

    @Test
    void testSetClienteVenda() {
        venda.setClienteVenda(clienteVenda2);
        assertEquals(clienteVenda2, venda.getClienteVenda());
    }

    @Test
    void testSetMetodoPagamento() {
        metodoPagamento = MetodoDePagamento.CARTAO_DE_DEBITO;
        venda.setMetodoPagamento(metodoPagamento);
        assertEquals(metodoPagamento, venda.getMetodoPagamento());
    }

    @Test
    void testSetNumParcelas() {
        numParcelas = 6;
        venda.setNumParcelas(numParcelas);
        assertEquals(numParcelas, venda.getNumParcelas());
    }
}