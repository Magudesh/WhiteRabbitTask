package com.whiterabbit.magudesh.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.whiterabbit.magudesh.model.Employee;
import com.whiterabbit.magudesh.R;
import java.util.ArrayList;



/**
 * Created by Magudesh on 9/12/2020.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private ArrayList<Employee> dataList;
    Context context;
    public EmployeeAdapter(ArrayList<Employee> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.txtEmpName.setText(dataList.get(position).getName());
        holder.txtEmpEmail.setText(dataList.get(position).getEmail());
        holder.txtEmpPhone.setText(dataList.get(position).getPhone());
        Glide.with(context).load(dataList.get(position).getProfileImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmpName, txtEmpEmail, txtEmpPhone;
        ImageView img;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            txtEmpName = (TextView) itemView.findViewById(R.id.txt_employee_name);
            txtEmpEmail = (TextView) itemView.findViewById(R.id.txt_employee_email);
            txtEmpPhone = (TextView) itemView.findViewById(R.id.txt_employee_phone);
            img = itemView.findViewById(R.id.img);
        }
    }
}