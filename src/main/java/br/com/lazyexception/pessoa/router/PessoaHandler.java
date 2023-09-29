package br.com.lazyexception.pessoa.router;

import br.com.lazyexception.pessoa.exception.UnprocessableException;
import br.com.lazyexception.pessoa.PessoaService;
import br.com.lazyexception.pessoa.dto.PessoaDTO;
import br.com.lazyexception.pessoa.validator.PessoaDateValidator;
import br.com.lazyexception.pessoa.validator.PessoaIsNumericValidator;
import br.com.lazyexception.pessoa.validator.PessoaNotNullValidator;
import br.com.lazyexception.pessoa.validator.PessoaSizeValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class PessoaHandler {

    private final PessoaService pessoaService;

    public PessoaHandler(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    private final Validator pessoaSizeValidator = new PessoaSizeValidator();
    private final Validator pessoaNotNullValidator = new PessoaNotNullValidator();
    private final Validator pessoaIsNumericValidator = new PessoaIsNumericValidator();
    private final Validator pessoaDateValidator = new PessoaDateValidator();

    public Mono<ServerResponse> create(ServerRequest req) {
        Mono<PessoaDTO> person = req.bodyToMono(PessoaDTO.class)
                .doOnNext(this::notNulValidate)
                .doOnNext(this::stringValidade)
                .doOnNext(this::dateValidate)
                .doOnNext(this::sizeValidate)
                .doOnNext(this::pessoaExists);

        return person.flatMap(this.pessoaService::save)
                .flatMap(p -> ServerResponse.created(URI.create("/pessoas/" + p)).build());
    }

//    public Mono<ServerResponse> create(ServerRequest req) {
//        Mono<PessoaDTO> person = req.bodyToMono(PessoaDTO.class)
//                .doOnNext(this::notNulValidate)
//                .doOnNext(this::stringValidade)
//                .doOnNext(this::dateValidate)
//                .doOnNext(this::sizeValidate)
//                .doOnNext(this::pessoaExists);
//
//        return person.flatMap(post -> Mono.just("12345678"))
//                .flatMap(p -> ServerResponse.created(URI.create("/pessoas/12345678" )).build());
//    }

    public Mono<ServerResponse> get(ServerRequest req) {
        String uuid = req.pathVariable("id");
        return this.pessoaService.getById(uuid)
                .flatMap(pessoa -> ok().contentType(APPLICATION_JSON).bodyValue(pessoa))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> count(ServerRequest req) {
        return this.pessoaService.count()
                .flatMap(pessoa -> ok().contentType(APPLICATION_JSON).bodyValue(pessoa))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> filter(ServerRequest req) {
        Optional<String> t = req.queryParam("t");
        return t.map(s -> this.pessoaService.filter(s)
                .flatMap(pessoa -> ok().contentType(APPLICATION_JSON).bodyValue(pessoa))
                .switchIfEmpty(ServerResponse.ok().build()))
                .orElseGet(() -> ServerResponse.badRequest().build());

    }

    private void sizeValidate(PessoaDTO pessoaDTO) {
        Errors errors = new BeanPropertyBindingResult(pessoaDTO, "pessoaDTO");
        pessoaSizeValidator.validate(pessoaDTO, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    private void stringValidade(PessoaDTO pessoaDTO) {
        Errors errors = new BeanPropertyBindingResult(pessoaDTO, "pessoaDTO");
        pessoaIsNumericValidator.validate(pessoaDTO, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    private void dateValidate(PessoaDTO pessoaDTO) {
        Errors errors = new BeanPropertyBindingResult(pessoaDTO, "pessoaDTO");
        pessoaDateValidator.validate(pessoaDTO, errors);
        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    private void notNulValidate(PessoaDTO pessoaDTO) {
        Errors errors = new BeanPropertyBindingResult(pessoaDTO, "pessoaDTO");
        pessoaNotNullValidator.validate(pessoaDTO, errors);
        if (errors.hasErrors()) {
            throw new UnprocessableException();
        }
    }

    private void pessoaExists(PessoaDTO pessoaDTO) {
        Integer total = pessoaService.getByApelido(pessoaDTO.getApelido());
        if (total > 0) {
            throw new UnprocessableException();
        }
    }

}
