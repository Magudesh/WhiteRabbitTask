package com.whiterabbit.magudesh;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.whiterabbit.magudesh.adapter.EmployeeAdapter;
import com.whiterabbit.magudesh.database.SQLiteHelper;
import com.whiterabbit.magudesh.model.Employee;
import com.whiterabbit.magudesh.network.GetEmployeeDataService;
import com.whiterabbit.magudesh.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteHelper = new SQLiteHelper(MainActivity.this);
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);
        Call<ArrayList<Employee>> call = service.getEmployeeData();

        call.enqueue(new Callback<ArrayList<Employee>>() {
            @Override
            public void onResponse(Call<ArrayList<Employee>> call, Response<ArrayList<Employee>> response) {
                ArrayList<Employee> emp=response.body();
                sqLiteHelper.deleteAllRecords();
                for(Employee e : emp) {
                    sqLiteHelper.insertRecord(e);
                }
                setAdapter(sqLiteHelper.getAllRecords());
            }

            @Override
            public void onFailure(Call<ArrayList<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter(ArrayList<Employee> empDataList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList, getApplicationContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}