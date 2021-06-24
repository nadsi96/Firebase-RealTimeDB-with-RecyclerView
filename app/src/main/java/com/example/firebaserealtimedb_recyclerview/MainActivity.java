package com.example.firebaserealtimedb_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/* build.gradle app
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementaion 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
*/

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MyItem> arrayList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // RecyclerView의 크기 고정
        /*
        RecyclerView의 크기는 item이 추가/제거될 때마다 변경되기 때문에, layout을 그릴 때 다시 측정하고 그리는 작업 반복
        setHasFixedSize를 true로 설정하면 RecyclerView의 크기가 변경되지 않음
         */
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>(); //MyItem 담을 객체 List


        database = FirebaseDatabase.getInstance(); // Firebase Database 연동

        databaseReference = database.getReference("User"); // Table 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Firebase database의 data 받아오는 곳

                // Data List 추출
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MyItem myItem = dataSnapshot.getValue(MyItem.class);
                    arrayList.add(myItem);
                    Log.i("Item", arrayList.get(arrayList.size()-1).getName());
                }

                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // DB 가져오는 중 Error 발생 시
                Log.e("MainActivity", String.valueOf(error.toException()));
                Log.e("Call Data", "Fail...");
            }
        });


        adapter = new MyRecyclerViewAdapter(arrayList, this);
//        adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

        recyclerView.setAdapter(adapter);
    }

    public void onClickAdd(View view){
        if (arrayList.size()%2 == 0){
            arrayList.add(arrayList.get(0));
        }else{
            arrayList.add(arrayList.get(1));
        }
        adapter.notifyDataSetChanged();
        Log.i("Add", "item added");
    }
    public void onClickDel(View view){
        if (arrayList.size() > 1){
            arrayList.remove(arrayList.size()-1);
            Log.i("Del", "item deleted");
        }
        adapter.notifyDataSetChanged();
    }
}