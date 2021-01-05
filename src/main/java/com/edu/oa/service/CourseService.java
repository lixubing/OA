package com.edu.oa.service;

import java.util.List;
import java.util.Map;

public interface CourseService {
    public Map<String,List> getCourseAndTeacher(String studentNo);
}
