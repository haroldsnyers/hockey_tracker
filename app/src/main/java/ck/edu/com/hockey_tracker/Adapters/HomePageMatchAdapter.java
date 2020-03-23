package ck.edu.com.hockey_tracker.Adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.Data.MatchModel;
import ck.edu.com.hockey_tracker.R;

public class HomePageMatchAdapter extends RecyclerView.Adapter<HomePageMatchAdapter.viewHolder> {

    Context context;
    ArrayList<MatchModel> arrayList;
    DatabaseHelper databaseHelper;

    public static OnItemClickListener onItemClickListener;

    public HomePageMatchAdapter(Context context, ArrayList<MatchModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public HomePageMatchAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home_fragment, viewGroup, false);
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
        viewHolder.location.setText(arrayList.get(i).getHometeam());
        viewHolder.test.setText(arrayList.get(i).getHometeam());

//        databaseHelper = new DatabaseHelper(context);
//
//        viewHolder
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView homeCrest, awayCrest;
        TextView homeTeam, score, date, awayTeam, location, test;
        LinearLayout matchDetail;

        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;

        public viewHolder(final View itemView) {
            super(itemView);
            homeTeam = (TextView) itemView.findViewById(R.id.home_name);
            score = (TextView) itemView.findViewById(R.id.score_textview);
            date = (TextView) itemView.findViewById(R.id.date_textview);
            awayTeam = (TextView) itemView.findViewById(R.id.away_name);
            location = (TextView) itemView.findViewById(R.id.matchday_textview);
            test = (TextView) itemView.findViewById(R.id.league_textview);
            matchDetail = (LinearLayout) itemView.findViewById(R.id.match_detail);
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

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(getAdapterPosition(), v);
//
//                }
//            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
