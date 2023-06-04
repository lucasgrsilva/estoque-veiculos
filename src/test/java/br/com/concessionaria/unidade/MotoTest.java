
package br.com.concessionaria.unidade;
import br.com.concessionaria.domain.entity.Moto;
import br.com.concessionaria.domain.entity.MarcasMoto;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;

//ATENÇÃO
//Essa classe não tem a intenção de propor testes unitários "úteis",
//visto que são em sua maioria 'Gets' e 'Sets'
//Esta classe existe tão somente para demonstração da aplicação do CodeCov

public class MotoTest {
    Moto moto;
    @Before
    public void setUp(){
        moto = new Moto(
                12345,  545454, "ABC1234","R1",1000, 18, MarcasMoto.Yamaha,
                2023, LocalDate.now(), BigDecimal.valueOf(5000), BigDecimal.valueOf(4000)
        );
    }


    @Test
    public void testGetCilindradaEmCc() {
        assertEquals(1000, moto.getCilindradaEmCc());
    }

    @Test
    public void testGetAroDasRodas() {
        assertEquals(18, moto.getAroDasRodas());
    }

    @Test
    public void testGetMarca() {
        assertEquals(MarcasMoto.Yamaha, moto.getMarca());
    }
}