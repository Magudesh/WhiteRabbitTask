package com.whiterabbit.magudesh.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Magudesh on 9/12/2020.
 */

public class EmployeeList {
    @SerializedName("")
    private ArrayList<Employee> employeeList;

    public ArrayList<Employee> getEmployeeArrayList() {
        return employeeList;
    }

    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeList = employeeArrayList;
    }
}
