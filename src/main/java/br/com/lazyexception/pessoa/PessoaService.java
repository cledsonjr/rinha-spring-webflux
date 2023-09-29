package br.com.lazyexception.pessoa;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Mono<String> save(PessoaDTO pessoa) {
        String uuid = UUID.randomUUID().toString();
        pessoa.setUuid(uuid);
        pessoaRepository.save(pessoa);
        return Mono.just(uuid);
    }

    public Mono<PessoaDTO> getById(String uuid) {
        return Mono.just(pessoaRepository.getById(uuid));
    }

    public Mono<List<PessoaDTO>> filter(String t) {
        return Mono.just(pessoaRepository.filter(t));
    }

    public Integer getByApelido(String apelido) {
        return pessoaRepository.getByApelido(apelido);
    }

    public Mono<Integer> count(){
        return Mono.just(pessoaRepository.count());
    }

}
