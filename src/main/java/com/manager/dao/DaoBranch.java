package com.manager.dao;

import com.manager.domain.Branch;
import com.manager.domain.Course;

import java.util.List;

public interface DaoBranch {

    public List<Branch> getAllBranches();

    public Branch searchById(Integer id);

    public Branch save(Branch branch);

}
