package br.com.lazyexception.pessoa.validator;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PessoaIsNumericValidator implements Validator {
    
    /**
    * This Validator validates just Person instances
    */
    public boolean supports(Class clazz) {
        return PessoaDTO.class.equals(clazz);
    }
    
    public void validate(Object obj, Errors e) {
        PessoaDTO pessoaDTO = (PessoaDTO) obj;
        if (isNumeric(pessoaDTO.getApelido()) || isNumeric(pessoaDTO.getNome()) || isNumeric(pessoaDTO.getNascimento())) {
            e.reject("452");
        }

        if (pessoaDTO.getStack() != null) {
            pessoaDTO.getStack().forEach(stack -> {
                if (isNumeric(stack)) {
                    e.reject("452");
                }
            });
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}