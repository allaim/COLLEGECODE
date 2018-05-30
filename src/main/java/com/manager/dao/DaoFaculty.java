package com.manager.dao;

import com.manager.domain.Faculty;
import com.manager.domain.Student;

import java.util.List;

public interface DaoFaculty {

    public Faculty save(Faculty faculty);

    public Faculty searchById(Integer id);

    public List<Faculty> searchByName(String name);

}
