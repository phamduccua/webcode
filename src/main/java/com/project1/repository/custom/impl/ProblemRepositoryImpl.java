package com.project1.repository.custom.impl;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.entity.ProblemEntity;
import com.project1.repository.custom.ProblemRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;
@Repository

public class ProblemRepositoryImpl implements ProblemRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ProblemEntity> findAll(ProblemSearchBuilder problemSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT p.id, p.title, p.description, p.difficulty, p.input_format, p.output_format, p.code, p.type , p.topic, p.class_id, p.constraints, p.time_limit, p.memory_limit, p.language, p.created_by, p.show_test \n");
        sql.append("FROM problem p \n");
        sql.append("WHERE 1 = 1\n");
        if(problemSearchBuilder.getCreatedBy() != null) {
            sql.append("AND p.created_by = " + problemSearchBuilder.getCreatedBy() + " \n");
        }
        queryNormal(sql, problemSearchBuilder);
        querySpecial(sql, problemSearchBuilder);
        sql.append(" ORDER BY p.difficulty, p.code ");
        Query query = entityManager.createNativeQuery(sql.toString(), ProblemEntity.class);
        return query.getResultList();
    }

    public void queryNormal(StringBuilder sql,ProblemSearchBuilder problemSearchBuilder){
        try{
            Field[] fieds = ProblemSearchBuilder.class.getDeclaredFields();
            for(Field item : fieds){
                item.setAccessible(true);
                String fieldName = item.getName();
                if(fieldName.equals("topic") != true){
                    Object value = item.get(problemSearchBuilder);
                    if(value != null){
                        if(fieldName.equals("group") == true){
                            sql.append(" AND p.class_id = " + value + " ");
                        }
                        else if(fieldName.equals("code") == true){
                            sql.append(" AND ( p." + fieldName + " LIKE '%" + value + "%' ");
                        }
                        else if(fieldName.equals("title") == true){
                            sql.append(" OR p.title LIKE '%" + value + "%' )");
                        }
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void querySpecial(StringBuilder sql,ProblemSearchBuilder problemSearchBuilder){
        List<String> topic = problemSearchBuilder.getTopic();
        if(topic != null && topic.size() > 0){
            for(int i = 0;i<topic.size();i++){
                if(i == 0){
                    sql.append(" AND ( p.topic LIKE '%" + topic.get(i) + "%' ");
                }
                else if(i == topic.size()-1){
                    sql.append(" OR p.topic LIKE '%" + topic.get(i) + "%' ");
                }
                else{
                    sql.append(" OR p.topic LIKE '%" + topic.get(i) + "%' ");
                }
            }
            sql.append(")");
        }

    }
}
