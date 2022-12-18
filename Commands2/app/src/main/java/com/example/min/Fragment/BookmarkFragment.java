package com.example.min.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.min.Adapter.CommandAdapter;
import com.example.min.Adapter.RecordAdapter;
import com.example.min.CollectActivity;
import com.example.min.ContentActivity;
import com.example.min.DatabaseHelp.RecordDatabaseHelp;
import com.example.min.ExplainActivity;
import com.example.min.Model.Commands;
import com.example.min.Model.Record;
import com.example.min.PlusActivity;
import com.example.min.R;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {
    ImageButton plus;
    ListView listView;
    Context context;
    RecordDatabaseHelp dbHelper;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_bookmark,container,false);

        plus = root.findViewById(R.id.plus);
        listView = root.findViewById(R.id.list_view);
        this.context = getActivity();
        dbHelper = new RecordDatabaseHelp(context);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PlusActivity.class);
                startActivity(intent);
            }
        });

        List<Record> relist = new ArrayList<>();
        ArrayList<Record> data = dbHelper.getAll();
        for(int i=0;i<data.size();i++){
            Record record = data.get(i);
            relist.add(record);
        }
        RecordAdapter adapter = new RecordAdapter(context,R.layout.record_item,relist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//重写
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Record record= relist.get(position) ;//获取点击的那个item
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("title",record.getTitle());
                startActivity(intent);
            }
        });

        return root;
    }
}