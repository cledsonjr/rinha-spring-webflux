package br.com.lazyexception.pessoa.validator;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PessoaNotNullValidator implements Validator {

    /**
    * This Validator validates just Person instances
    */
    public boolean supports(Class clazz) {
        return PessoaDTO.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        PessoaDTO pessoaDTO = (PessoaDTO) obj;
        if (isNull(pessoaDTO.getApelido()) || isNull(pessoaDTO.getNome()) || isNull(pessoaDTO.getNascimento())) {
            e.reject("451");
        }
    }

    private Boolean isNull(String valor) {
        return valor == null || valor.isBlank() || valor.isEmpty();
    }
}