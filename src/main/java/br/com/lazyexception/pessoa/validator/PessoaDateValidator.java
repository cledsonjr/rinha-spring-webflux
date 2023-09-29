package br.com.lazyexception.pessoa.validator;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PessoaDateValidator implements Validator {
    
    /**
    * This Validator validates just Person instances
    */
    public boolean supports(Class clazz) {
        return PessoaDTO.class.equals(clazz);
    }
    
    public void validate(Object obj, Errors e) {
        PessoaDTO pessoaDTO = (PessoaDTO) obj;
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(pessoaDTO.getNascimento(), dateFormatter);
        } catch (DateTimeParseException er) {
            e.reject("452");
        }

    }

}