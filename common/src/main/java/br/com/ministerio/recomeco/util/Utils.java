package br.com.ministerio.recomeco.util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class Utils {
    public static boolean cpfIsValido(String cpf) {
        try {
            new CPFValidator().assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
}
