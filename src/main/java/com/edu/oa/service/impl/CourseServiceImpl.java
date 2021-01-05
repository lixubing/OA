package com.edu.oa.service.impl;

import com.edu.oa.mdo.CourseDo;
import com.edu.oa.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public Map<String, List> getCourseAndTeacher(String studentNo) {
        CourseDo mdo = new CourseDo();
        mdo.setStudentNo(studentNo);
        List<CourseDo> list = mdo.getCourseDoByStudentNo();
        Map<String,List> m = new HashMap<>();
        List result = new ArrayList();
        for (CourseDo courseDo : list) {
            Map<String, String> map = new HashMap<>();
            map.put("name",courseDo.getTeacherName());
            map.put("course",courseDo.getCourseName());
            map.put("id",courseDo.getTeacherNo());
            result.add(map);
        }
        m.put("rows",result);
        return m;
    }
}
