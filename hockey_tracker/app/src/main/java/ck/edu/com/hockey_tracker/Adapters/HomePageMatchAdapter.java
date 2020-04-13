package ck.edu.com.hockey_tracker.Adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Formatter;

import ck.edu.com.hockey_tracker.Data.MatchModel;
import ck.edu.com.hockey_tracker.DetailActivity;
import ck.edu.com.hockey_tracker.R;

public class HomePageMatchAdapter extends RecyclerView.Adapter<HomePageMatchAdapter.viewHolder> {

    private Context context;
    private ArrayList<MatchModel> arrayList;
    private int layoutId;
    private String mode;


    public HomePageMatchAdapter(Context context, ArrayList<MatchModel> arrayList, int layoutId, String mode) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutId = layoutId;
        this.mode = mode;
    }

    @Override
    public HomePageMatchAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomePageMatchAdapter.viewHolder viewHolder, final int i) {
        viewHolder.homeTeam.setText(arrayList.get(i).getHometeam());
        viewHolder.awayTeam.setText(arrayList.get(i).getAwayteam());
        viewHolder.date.setText(arrayList.get(i).getDate());
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("%d - %d", arrayList.get(i).getScorehometeam() , arrayList.get(i).getScoreawayteam());
        viewHolder.score.setText(sbuf.toString());
        viewHolder.location.setText(arrayList.get(i).getLocation());
        viewHolder.imagePath = String.valueOf(arrayList.get(i).getImagePathList());
        viewHolder.id_match = String.valueOf(arrayList.get(i).getID());
        viewHolder.mode = mode;
        viewHolder.context = context;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView homeCrest, awayCrest;
        TextView homeTeam, score, date, awayTeam, location, test;
        LinearLayout matchDetail;
        Button buttonDetail;
        String imagePath;
        String id_match;
        String mode;

        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;
        private Context context;

        public viewHolder(final View itemView) {
            super(itemView);

            homeTeam = (TextView) itemView.findViewById(R.id.home_name);
            score = (TextView) itemView.findViewById(R.id.score_textview);
            date = (TextView) itemView.findViewById(R.id.date_textview);
            awayTeam = (TextView) itemView.findViewById(R.id.away_name);
            location = (TextView) itemView.findViewById(R.id.matchday_textview);
            matchDetail = (LinearLayout) itemView.findViewById(R.id.match_detail);
            buttonDetail = itemView.findViewById(R.id.detail_match_button);
//            homeCrest = (ImageView) itemView.findViewById(R.id.home_crest);
//            awayCrest = (ImageView) itemView.findViewById(R.id.away_crest);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOriginalHeight == 0) {
                        mOriginalHeight = itemView.getHeight();
                    }
                    ValueAnimator valueAnimator;
                    if (!mIsViewExpanded) {
                        mIsViewExpanded = true;
                        valueAnimator = ValueAnimator.ofInt(mOriginalHeight, mOriginalHeight + (int) (mOriginalHeight * 1.5));
                        matchDetail.setVisibility(View.VISIBLE);
                    } else {
                        mIsViewExpanded = false;
                        valueAnimator = ValueAnimator.ofInt(mOriginalHeight + (int) (mOriginalHeight * 1.5), mOriginalHeight);
                        matchDetail.setVisibility(View.GONE);
                    }
                    valueAnimator.setDuration(300);
                    valueAnimator.setInterpolator(new LinearInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer value = (Integer) animation.getAnimatedValue();
                            itemView.getLayoutParams().height = value.intValue();
                            itemView.requestLayout();
                        }
                    });
                    valueAnimator.start();
                }
            });

            buttonDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class destinationActivity = DetailActivity.class;
                    Intent startChildActivityintent = new Intent(context, destinationActivity);
                    // getting text entered and passing along as an extra under the name of EXTRA_TEXT
                    startChildActivityintent.putExtra("HOMENAME", homeTeam.getText());
                    startChildActivityintent.putExtra("AWAYNAME", awayTeam.getText());
                    startChildActivityintent.putExtra("LOCATION", location.getText());
                    startChildActivityintent.putExtra("DATE", date.getText());
                    startChildActivityintent.putExtra("IMAGES", imagePath);
                    startChildActivityintent.putExtra("ID", id_match);
                    startChildActivityintent.putExtra("MODE", mode);

                    context.startActivity(startChildActivityintent);
                }
            });
        }
    }
}
