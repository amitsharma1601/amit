package Adapters;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment3.Activities.ViewModifyActivity;
import com.example.assignment3.R;

import java.util.List;

import Models.StudentDetails;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentDetailsAdapter extends RecyclerView.Adapter<StudentDetailsAdapter.ViewHolder> {

    List<StudentDetails> studentsDetails;

    public boolean isList() {
        return list;
    }

    public void setList(boolean list) {
        this.list = list;
    }

    boolean list;

    public StudentDetailsAdapter(List<StudentDetails> studentsDetails, boolean list) {
        this.studentsDetails = studentsDetails;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.d("onBindViewHolder: ", "chala");
        if (list) {
            return new ViewHolder(inflater.inflate(R.layout.item_student_detail, parent, false));
        }
        else {
            return new ViewHolder(inflater.inflate(R.layout.item_student_data_grid, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvStudentName.setText(studentsDetails.get(position).getStudentName());
        holder.tvStudentClass.setText(new Integer(studentsDetails.get(position).getStudentClass()).toString());

        holder.parentView.setOnClickListener(new View.OnClickListener() {

            AlertDialog alertDialog;

            @Override
            public void onClick(View v) {
                View alertDialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.layout_alert_dialog, null);
                alertDialog = new AlertDialog.Builder(v.getContext())
                        .setView(alertDialogView).create();
                alertDialog.show();

                Button buttonView = alertDialogView.findViewById(R.id.btn_alert_view);
                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleClickEvents(v, 0);
                    }
                });

                Button buttonEdit = alertDialogView.findViewById(R.id.btn_alert_edit);
                buttonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handleClickEvents(v, 1);
                    }
                });

                Button buttonDelete = alertDialogView.findViewById(R.id.btn_alert_delete);
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentsDetails.remove(position);
                        notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                });
            }

            public void handleClickEvents(View v, int value) {
                Intent intent = new Intent(v.getContext(), ViewModifyActivity.class);
                intent.putExtra("detail", studentsDetails.get(position));
                intent.putExtra("flag", value);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName;
        TextView tvStudentClass;
        View parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView;
            tvStudentName = itemView.findViewById(R.id.tv_item_student_detail_grid_name);
            tvStudentClass = itemView.findViewById(R.id.tv_item_student_detail_grid_class);
        }
    }
}
