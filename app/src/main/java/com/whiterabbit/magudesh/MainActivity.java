package com.whiterabbit.magudesh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.whiterabbit.magudesh.model.Employee;
import com.whiterabbit.magudesh.network.GetEmployeeDataService;
import com.whiterabbit.magudesh.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Create handle for the RetrofitInstance interface*/
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<List<Employee>> call = service.getEmployeeData();

        /*Log the URL called*/
        Log.d("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> emp=response.body();
                for(Employee e : emp) {
                    Log.d("SASKEN", e.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("SASKEN",t.getMessage());
            }
        });
    }
}