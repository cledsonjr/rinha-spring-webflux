package br.com.lazyexception.pessoa;

import br.com.lazyexception.pessoa.dto.PessoaDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PessoaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PessoaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(PessoaDTO pessoa) {
        String search = String.format("%s%s%s%s", pessoa.getApelido(), pessoa.getNome(),pessoa.getNascimento(), pessoa.getStack() != null ? String.join("", pessoa.getStack()) : "");
        jdbcTemplate.update("INSERT INTO rinha.pessoas  (uuid, apelido, nome, nascimento, stack, busca) VALUES(?,?,?,?,?,?)",
                pessoa.getUuid(), pessoa.getApelido(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getStack() != null ? pessoa.getStack().toArray(new String[pessoa.getStack().size()]) : new String[]{}, search.toLowerCase());
    }

    public PessoaDTO getById(String uuid) {
        String sql = "SELECT * FROM rinha.pessoas WHERE uuid = ?";
        return (PessoaDTO) jdbcTemplate.queryForObject(
                sql,
                new Object[]{uuid},
                (rs, rowNum) ->
                        new PessoaDTO(
                                rs.getString("uuid"),
                                rs.getString("apelido"),
                                rs.getString("nome"),
                                rs.getString("nascimento"),
                                Arrays.stream((String[]) rs.getArray("stack").getArray()).collect(Collectors.toList())
                        ));
    }

    public Integer getByApelido(String apelido) {
        String sql = "SELECT count(*) FROM rinha.pessoas WHERE apelido = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{apelido} , Integer.class);
    }

    public Integer count() {
        String sql = "SELECT count(*) FROM rinha.pessoas";
        return jdbcTemplate.queryForObject(sql , Integer.class);
    }

    public List<PessoaDTO> filter(String t) {
        String sql = String.format("SELECT * FROM rinha.pessoas where busca like '%s' limit 50", "%"+t.toLowerCase()+"%");

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new PessoaDTO(
                                rs.getString("uuid"),
                                rs.getString("apelido"),
                                rs.getString("nome"),
                                rs.getString("nascimento"),
                                Arrays.stream((String[]) rs.getArray("stack").getArray()).collect(Collectors.toList())
                        )
        );
    }
}