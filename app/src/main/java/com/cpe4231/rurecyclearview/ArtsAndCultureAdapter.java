package com.cpe4231.rurecyclearview;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

class ArtsAndCultureAdapter extends RecyclerView.Adapter<ArtsAndCultureAdapter.ViewHolder>  {

    private ArrayList<ArtsAndCulture> mSportsData;
    private Context mContext;

    ArtsAndCultureAdapter(Context context, ArrayList<ArtsAndCulture> sportsData) {
        this.mSportsData = sportsData;
        this.mContext = context;
    }

    @Override
    public ArtsAndCultureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.sport_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ArtsAndCultureAdapter.ViewHolder holder, int position) {
        ArtsAndCulture currentArtsAndCulture = mSportsData.get(position);
        holder.bindTo(currentArtsAndCulture);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleText;

        private TextView mInfoText;

        private TextView mCultureProv;
        private TextView mCulturePlace;
        private TextView mCulturePeriod;

        private ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.title);
            mCultureProv = itemView.findViewById(R.id.province);
            mCulturePlace = itemView.findViewById(R.id.place);
            mCulturePeriod = itemView.findViewById(R.id.periodTitle);
            mInfoText = itemView.findViewById(R.id.info);

            mSportsImage = itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(ArtsAndCulture currentArtsAndCulture){
            mTitleText.setText(currentArtsAndCulture.getTitle());

            mCultureProv.setText(currentArtsAndCulture.getProv());
            mCulturePlace.setText(currentArtsAndCulture.getPlace());
            mCulturePeriod.setText(currentArtsAndCulture.getPeriod());
            mInfoText.setText(currentArtsAndCulture.getInfo());

            Glide.with(mContext).load(
                    currentArtsAndCulture.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            ArtsAndCulture currentArtsAndCulture = mSportsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentArtsAndCulture.getTitle());
            detailIntent.putExtra("detail", currentArtsAndCulture.getDetail());

            detailIntent.putExtra("prov", currentArtsAndCulture.getProv());
            detailIntent.putExtra("place", currentArtsAndCulture.getPlace());
            detailIntent.putExtra("period", currentArtsAndCulture.getPeriod());
            detailIntent.putExtra("info", currentArtsAndCulture.getInfo());

            detailIntent.putExtra("image_resource", currentArtsAndCulture.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
