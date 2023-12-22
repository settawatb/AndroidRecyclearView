package com.cpe4231.rurecyclearview;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ArrayList<ArtsAndCulture> mCulturesData;
    private ArtsAndCultureAdapter mAdapter;
    FloatingActionButton reset_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset_bt = findViewById(R.id.reset_bt);
        reset_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeData();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        int gridColumnCount = getResources().getInteger(R.integer.grid_column_count);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));
        mCulturesData = new ArrayList<>();
        mAdapter = new ArtsAndCultureAdapter(this, mCulturesData);
        mRecyclerView.setAdapter(mAdapter);
        initializeData();
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirs) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

// Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
// Swap the items and notify the adapter.
                Collections.swap(mCulturesData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
// Remove the item from the dataset.
                mCulturesData.remove(viewHolder.getAdapterPosition());
// Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }
    private void initializeData() {
// Get the resources from the XML file.

        // ชื่อ
        String[] cultureList = getResources()
                .getStringArray(R.array.culture_titles);
        // จังหวัด
        String[] cultureProv = getResources()
                .getStringArray(R.array.culture_province);
        // สถานที่
        String[] culturePlace = getResources()
                .getStringArray(R.array.culture_place);
        // period ช่วงเวลาจัดงาน
        String[] culturePeriod = getResources()
                .getStringArray(R.array.culture_period);
        // รายละเอียดสั้น
        String[] cultureInfo = getResources()
                .getStringArray(R.array.culture_info);
        // รายละเอียดยาว
        String[] cultureDetail = getResources()
                .getStringArray(R.array.detail_text);


        TypedArray sportsImageResources =
                getResources().obtainTypedArray(R.array.culture_images);
        mCulturesData.clear();
        for (int i = 0; i < cultureList.length; i++) {
            mCulturesData.add(new ArtsAndCulture(cultureList[i], cultureInfo[i],cultureDetail[i],
                    cultureProv[i], culturePlace[i], culturePeriod[i],
                    sportsImageResources.getResourceId(i, 0)));
        }
        sportsImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }


    public void resetView(View view) {
        initializeData();
    }
}