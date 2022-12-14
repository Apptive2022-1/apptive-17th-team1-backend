package com.example.wineapi.domain.question.repository;

import com.example.wineapi.domain.question.entity.LikertScale;
import com.example.wineapi.domain.question.entity.Question;
import com.example.wineapi.domain.question.entity.QuestionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionRepository {
    private final EntityManager em;

    @Autowired
    public QuestionRepository(EntityManager em) {
        this.em = em;
    }

    // id를 parameter로 database에 저장된 question 검색 (데이터베이스 인덱스 기법 미적용)
    public Optional<Question> findById(Integer id) {
        return em.createQuery("select m from Question m where m.id = :id", Question.class)
                .setParameter("id", id)
                .getResultList()
                .stream().findAny();
    }

    public ArrayList<Question> findQuestionByCategory(Integer category) {
        ArrayList<Question> result = new ArrayList<>();
        result.addAll(em.createQuery("select m from Question m where m.category = :category", Question.class)
                .setParameter("category", category)
                .getResultList());
        return result;
    }
    public List<QuestionOption> findByQuestionOption(Long questionId) {
        return em.createQuery("select m from QuestionOption m where m.questionId = :questionId", QuestionOption.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

    public List<LikertScale> findScale(Long questionId) {
        return em.createQuery("select m from LikertScale m where m.questionId = :questionId", LikertScale.class)
                .setParameter("questionId", questionId)
                .getResultList();
    }

}
