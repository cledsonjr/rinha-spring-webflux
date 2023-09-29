package br.com.lazyexception.pessoa.validator;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PessoaSizeValidator implements Validator {
    
    /**
    * This Validator validates just Person instances
    */
    public boolean supports(Class clazz) {
        return PessoaDTO.class.equals(clazz);
    }
    
    public void validate(Object obj, Errors e) {
        PessoaDTO pessoaDTO = (PessoaDTO) obj;
        if (pessoaDTO.getApelido().length() > 32 || pessoaDTO.getNome().length() > 100 || pessoaDTO.getNascimento().length() > 10) {
            e.reject("450");
        }

        if (pessoaDTO.getStack() != null) {
            pessoaDTO.getStack().forEach(stack -> {
                if (stack == null || stack.isBlank() || stack.isEmpty() || stack.length() > 32) {
                    e.reject("450");
                }
            });
        }
    }
}