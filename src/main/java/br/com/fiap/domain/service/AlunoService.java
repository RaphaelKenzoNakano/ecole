package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Aluno;
import br.com.fiap.domain.repository.AlunoRepository;
import br.com.fiap.domain.repository.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class AlunoService implements Service<Aluno, Long> {

    private Set<Aluno> alunos;

    private static volatile AlunoRepository instance;

    private AlunoService() {
        alunos = new LinkedHashSet<>();
    }
    public static AlunoRepository of(){
        AlunoRepository result = instance;
        if(Objects.nonNull( result) ){
            return result;
        }

        synchronized (AlunoRepository.class){
            if(Objects.isNull( null )){
                instance = new AlunoRepository();
            }
            return instance;
        }
    }



    @Override
    public List<Aluno> findAll() {
        return alunos.stream().toList();
    }

    @Override
    public Aluno findById(Long id) {
        return alunos.stream()
                .filter( a -> a.getId().equals( id ) )
                .findFirst()
                .orElse( null );
    }

    @Override
    public List<Aluno> findByName(String texto) {
        return alunos.stream()
                .filter( a -> a.getNome().toLowerCase().contains( texto.toLowerCase() ) )
                .toList();
    }

    /**
     * Gere matricula de forma randômica.
     * e-mail deve ser validado
     * id deve ser gerado pelo repository
     *
     * @param aluno
     * @return
     */
    @Override
    public Aluno persist(Aluno aluno) {
        if(Objects.isNull( aluno )) return null;
        if(Objects.isNull( aluno.getId() )) aluno.setId( alunos.size() + 1L );
        alunos.add( aluno );
        return aluno;
    }
}
    /**
     * Validando email
     * @param emailAddress
     * @return
     */


    /**
     * Gerando matricula randomicamente
     * @return
     */
    public String gerarMatricula() {
        Random r = new Random();
        var matricula = LocalDate.now().getYear() + "." + r.nextInt(1000, 9999) + "-" + r.nextInt(10, 99);
        return matricula;
    }

}
