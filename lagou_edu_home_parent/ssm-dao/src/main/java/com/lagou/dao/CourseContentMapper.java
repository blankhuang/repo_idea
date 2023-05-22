package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

public interface CourseContentMapper {
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    public Course findCourseByCourseId(Integer id);

    public void saveSection(CourseSection courseSection);


    void updateSection(CourseSection courseSection);

    public void updateSectionStatus(CourseSection courseSection);

    public void saveLesson(CourseLesson courseLesson);

}
