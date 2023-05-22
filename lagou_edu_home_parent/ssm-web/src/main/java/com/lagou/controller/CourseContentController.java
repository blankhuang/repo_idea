package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.impl.CourseContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentServiceImpl courseContentService;
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "章节及课时内容查询成功", list);
        return responseResult;
    }
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId()==null){
            courseContentService.saveSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        }else {
            courseContentService.updateSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id,Integer status){
        courseContentService.updateSectionStatus(id,status);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改章节成功", map);
        return responseResult;
    }
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
            courseContentService.saveLesson(courseLesson);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加课时成功", courseLesson);
            return responseResult;

    }
}
