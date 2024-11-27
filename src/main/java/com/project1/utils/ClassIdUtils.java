package com.project1.utils;

public class ClassIdUtils {
    public static Long toClassId(String item){
        if(item != null){
            if(item.equals("NGON_NGU_LAP_TRINH_C") == true){
                return 1L;
            }
            else if(item.equals("NGON_NGU_LAP_TRINH_CPP") == true){
                return 2L;
            }
            else if(item.equals("CAU_TRUC_DU_LIEU_VA_GIAI_THUAT") == true){
                return 3L;
            }
            else if(item.equals("NGON_NGU_LAP_TRINH_JAVA") == true){
                return 4L;
            }
            else if(item.equals("NGON_NGU_LAP_TRINH_PYTHON") == true){
                return 5L;
            }
            else return 0L;
        }
        return 0L;
    }
    public static String toClassId(Long classId){
        if(classId != null){
            if(classId == 1L){
                return "NGON_NGU_LAP_TRINH_C";
            }
            else if(classId == 2L){
                return "NGON_NGU_LAP_TRINH_CPP";
            }
            else if(classId == 3L){
                return "CAU_TRUC_DU_LIEU_VA_GIAI_THUAT";
            }
            else if(classId == 4L){
                return "NGON_NGU_LAP_TRINH_JAVA";
            }
            else if(classId == 5L){
                return "NGON_NGU_LAP_TRINH_PYTHON";
            }
            else return null;
        }
        return null;
    }
}
