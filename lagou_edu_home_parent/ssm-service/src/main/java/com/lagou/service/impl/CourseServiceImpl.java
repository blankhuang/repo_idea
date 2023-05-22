package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> courseByCondition = courseMapper.findCourseByCondition(courseVo);

        return courseByCondition;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {
        Course course = new Course();

        BeanUtils.copyProperties(course,courseVo);

        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);

        int id = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        courseMapper.saveTeacher(teacher);

    }

    @Override
    public CourseVo findCourseById(Integer id) {
        CourseVo courseVo = courseMapper.findCourseById(id);
        return courseVo;
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        Date date = new Date();
        course.setUpdateTime(date);

        courseMapper.updateCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        teacher.setUpdateTime(date);

        int id = course.getId();
        teacher.setCourseId(id);

        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int id, int status) {
        Course course = new Course();
        course.setStatus(status);
        course.setId(id);
        course.setUpdateTime(new Date());

        courseMapper.updateCourseStatus(course);
    }
}
