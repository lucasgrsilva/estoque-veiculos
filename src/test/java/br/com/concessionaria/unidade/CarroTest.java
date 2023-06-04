
package br.com.concessionaria.unidade;

//import org.junit.Test;
import br.com.concessionaria.domain.entity.Carro;
import br.com.concessionaria.domain.entity.MarcasCarro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

//ATENÇÃO
//Essa classe não tem a intenção de propor testes unitários "úteis",
//visto que são em sua maioria 'Gets' e 'Sets'
//Esta classe existe tão somente para demonstração da aplicação do CodeCov

public class CarroTest {
    private Carro carro;

    @BeforeEach
    public void setUp(){
        carro = new Carro(
                12, 12345, "ABC1234", "Carro Teste", 200, BigDecimal.valueOf(2.0),
                Boolean.TRUE, 16, MarcasCarro.Toyota,
                2023, LocalDate.now(), BigDecimal.valueOf(20000), BigDecimal.valueOf(18000)
        );
    }

    //Normalmente não escreveria testes para Get e Set, porém esta classe testa a corretude do construtor
    @Test
    public void testGetCavalosPotencia() {
        Assertions.assertEquals(200, carro.getCavalosPotencia());
    }

    @Test
    public void testGetCilindradaEmLitro() {
        Assertions.assertEquals(BigDecimal.valueOf(2.0), carro.getCilindradaEmLitro());
    }

    @Test
    public void testGetMarca() {
        Assertions.assertEquals(MarcasCarro.Toyota, carro.getMarca());
    }

    @Test
    public void testIsTurbo() {
        Assertions.assertEquals(Boolean.TRUE, carro.isTurbo());
    }

}