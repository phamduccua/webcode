package com.project1.converter;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.utils.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProblemSearchBuilderConverter {
    public ProblemSearchBuilder toProblemSearchBuilder(ProblemSearchRequest request) {
        Long group = null;
        if(request.getGroup()!=null){
            if(request.getGroup().equals("NGON_NGU_LAP_TRINH_C")) group = 1L;
            else if(request.getGroup().equals("NGON_NGU_LAP_TRINH_CPP")) group = 2L;
            else if(request.getGroup().equals("CAU_TRUC_DU_LIEU_VA_GIAI_THUAT")) group = 3L;
            else if(request.getGroup().equals("NGON_NGU_LAP_TRINH_JAVA")) group = 4L;
            else if(request.getGroup().equals("NGON_NGU_LAP_TRINH_PYTHON")) group = 5L;
            else if(request.getGroup().equals("CONTEST")) group = 0L;
            else group = -1L;
        }
        ProblemSearchBuilder problemSearchBuilder = new ProblemSearchBuilder.Builder()
                .setCode(MapUtils.getObject(request.getCodeOrtitle(),String.class))
                .setTitle(MapUtils.getObject(request.getCodeOrtitle(),String.class))
                .setTopic(MapUtils.getObject(request.getTopic(), List.class))
                .setGroup(MapUtils.getObject(group, Long.class))
                .setCreatedBy(MapUtils.getObject(request.getCreatedBy(), Long.class))
                .build();
        return problemSearchBuilder;
    }
}
