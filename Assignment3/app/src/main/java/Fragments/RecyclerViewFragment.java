package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment3.R;

import Adapters.StudentDetailsAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewFragment extends Fragment {
    private StudentDetailsAdapter mAdapter;
    private boolean flag;

    public RecyclerViewFragment(StudentDetailsAdapter mAdapter, boolean flag) {
        this.mAdapter = mAdapter;
        this.flag = flag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        if (flag) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
        }
        else{
            Log.d("grid", "gridchala");
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
        }
        mAdapter.setList(flag);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
