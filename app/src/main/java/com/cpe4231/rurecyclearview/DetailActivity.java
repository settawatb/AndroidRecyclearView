package com.cpe4231.rurecyclearview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView cultureTitle = findViewById(R.id.titleDetailTextview);
        TextView cultureTitle2 = findViewById(R.id.title2DetailTextview);
        TextView cultureDetail = findViewById(R.id.subTitleDetailTextview);
        ImageView cultureImage = findViewById(R.id.sportsImageDetail);

        cultureTitle.setText(getIntent().getStringExtra("title"));

        cultureTitle2.setText(getIntent().getStringExtra("prov"));

        cultureDetail.setText(getIntent().getStringExtra("detail"));

        Glide.with(this)
                .load(getIntent()
                        .getIntExtra("image_resource",0))
                .into(cultureImage);
    }
}

