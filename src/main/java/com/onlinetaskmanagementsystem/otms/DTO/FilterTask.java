package com.onlinetaskmanagementsystem.otms.DTO;


import java.util.List;

public class FilterTask {

    private String empFilterType;
    private List<Integer> userId;

    public String getEmpFilterType() {
        return empFilterType;
    }

    public void setEmpFilterType(String empFilterType) {
        this.empFilterType = empFilterType;
    }

    public List<Integer> getUserId() {
        return userId;
    }

    public void setUserId(List<Integer> userId) {
        this.userId = userId;
    }
}
