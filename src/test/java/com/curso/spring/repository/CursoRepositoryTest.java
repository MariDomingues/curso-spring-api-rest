package com.curso.spring.repository;

import com.curso.spring.model.entity.CursoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
// indica ao Spring que os testes da interface ‘Repository` devem ser realizados em outro banco de dados que não seja o h2
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria carregar um curso ao buscar pelo nome")
    public void findByNome1() {

        String nomeCurso = "HTML 5";

        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setNome(nomeCurso);
        cursoEntity.setCategoria("Programacao");
        testEntityManager.persist(cursoEntity);

        CursoEntity curso = cursoRepository.findByNome(nomeCurso);

        Assert.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    @DisplayName("Não deveria carregar um curso ao buscar pelo nome")
    public void findByNome2() {

        String nomeCurso = "Java";

        CursoEntity curso = cursoRepository.findByNome(nomeCurso);

        Assert.assertNull(curso);
    }
}