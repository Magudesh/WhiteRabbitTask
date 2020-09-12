package com.whiterabbit.magudesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.whiterabbit.magudesh.adapter.EmployeeAdapter;
import com.whiterabbit.magudesh.model.Employee;
import com.whiterabbit.magudesh.network.GetEmployeeDataService;
import com.whiterabbit.magudesh.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);
        Call<ArrayList<Employee>> call = service.getEmployeeData();

        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                ArrayList<Employee> emp=response.body();
                for(Employee e : emp) {
                    Log.d("SASKEN", e.getName());
                }
                generateEmployeeList(emp);
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("SASKEN",t.getMessage());
            }
        });
    }

    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}