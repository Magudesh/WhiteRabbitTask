package com.whiterabbit.magudesh.network;


import com.whiterabbit.magudesh.model.Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Magudesh on 9/12/2020.
 */

public interface GetEmployeeDataService {
    @GET("5d565297300000680030a986")
    Call<ArrayList<Employee>> getEmployeeData();
}
