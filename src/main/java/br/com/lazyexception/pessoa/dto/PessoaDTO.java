package br.com.lazyexception.pessoa.dto;

import java.util.Date;
import java.util.List;

public class PessoaDTO {

    public PessoaDTO() {

    }

    public PessoaDTO(String uuid, String apelido, String nome, String nascimento, List<String> stack) {
        this.uuid = uuid;
        this.apelido = apelido;
        this.nome = nome;
        this.nascimento = nascimento;
        this.stack = stack;
    }

    private String uuid;

    private String apelido;

    private String nome;

    private String nascimento;

    private List<String> stack;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
