package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    public Course findCourseByCourseId(Integer courseId);

    public void saveSection(CourseSection courseSection);

    void updateSection(CourseSection courseSection);

    public void updateSectionStatus(Integer id,Integer status);

    public void saveLesson(CourseLesson courseLesson);


}
