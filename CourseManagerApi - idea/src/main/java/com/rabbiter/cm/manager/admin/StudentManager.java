package com.rabbiter.cm.manager.admin;

import com.rabbiter.cm.manager.BaseManager;
import com.rabbiter.cm.dao.ClassDAO;
import com.rabbiter.cm.dao.StudentCourseDAO;
import com.rabbiter.cm.dao.StudentDAO;
import com.rabbiter.cm.model.entity.ClassEntity;
import com.rabbiter.cm.model.entity.StudentEntity;
import com.rabbiter.cm.model.vo.response.IdNameVO;
import com.rabbiter.cm.model.vo.response.table.StudentItemVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentManager extends BaseManager {
    private final ClassDAO classDAO;
    private final StudentCourseDAO studentCourseDAO;
    private final StudentDAO studentDAO;

    public StudentManager(ClassDAO classDAO, StudentCourseDAO studentCourseDAO, StudentDAO studentDAO) {
        this.classDAO = classDAO;
        this.studentCourseDAO = studentCourseDAO;
        this.studentDAO = studentDAO;
    }

    public Integer getPageCount(String majorName, String className, String name) {
        int count = studentDAO.count(majorName, className, name);
        return calcPageCount(count, StudentDAO.PAGE_SIZE);
    }

    public List<StudentItemVO> getPage(Integer index, String majorName, String className, String name) {
        return studentDAO.getPage(index, majorName, className, name);
    }

    public StudentEntity get(Integer id) {
        return studentDAO.get(id);
    }

    public int create(StudentEntity entity) {
        return studentDAO.insert(entity);
    }

    public int update(StudentEntity entity) {
        return studentDAO.update(entity);
    }

    public int delete(Integer id) {
        return studentDAO.delete(id);
    }

    public ClassEntity getClassById(Integer classId) {
        return classDAO.get(classId);
    }

    public boolean hasStudentCourse(Integer studentId) {
        return studentCourseDAO.countByStudentId(studentId) > 0;
    }

    public List<IdNameVO> listName() {
        List<IdNameVO> voList = new ArrayList<>();
        List<StudentEntity> entityList = studentDAO.listName();
        for (StudentEntity entity : entityList) {
            voList.add(new IdNameVO(entity.getId(), entity.getName()));
        }

        return voList;
    }
}
