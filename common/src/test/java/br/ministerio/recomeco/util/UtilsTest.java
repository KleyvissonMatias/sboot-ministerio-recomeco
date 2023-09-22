package br.ministerio.recomeco.util;

import br.com.ministerio.recomeco.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

    @Test
    public void testeCpfValidoRetornoTrue() {
        boolean retorno = Utils.cpfIsValido("72134977078");
        Assertions.assertTrue(retorno);
    }

    @Test
    public void testeCpfValidoRetornoFalse() {
        boolean retorno = Utils.cpfIsValido("11111111");
        Assertions.assertFalse(retorno);
    }
}
