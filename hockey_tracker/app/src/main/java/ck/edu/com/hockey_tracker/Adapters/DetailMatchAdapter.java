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
import ck.edu.com.hockey_tracker.Data.QuarterModel;
import ck.edu.com.hockey_tracker.DetailActivity;
import ck.edu.com.hockey_tracker.R;

public class DetailMatchAdapter extends RecyclerView.Adapter<DetailMatchAdapter.viewHolder> {

    Context context;
    ArrayList<QuarterModel> arrayList;
    int layoutId;

    public static DetailMatchAdapter.OnItemClickListener onItemClickListener;

    public DetailMatchAdapter(Context context, ArrayList<QuarterModel> arrayList, int layoutId) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutId = layoutId;
    }

    @Override
    public DetailMatchAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false);
        return new DetailMatchAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DetailMatchAdapter.viewHolder viewHolder, final int i) {
        viewHolder.shotsHome.setText(String.valueOf(arrayList.get(i).getShotsHome()));
        viewHolder.shotsAway.setText(String.valueOf(arrayList.get(i).getShotsAway()));
        viewHolder.shotsHomeOnGoal.setText(String.valueOf(
                arrayList.get(i).getShotsHomeMissedKeeper()+arrayList.get(i).getGoalsHome()));
        viewHolder.shotsAwayOnGoal.setText(String.valueOf(
                arrayList.get(i).getShotsAwayMissedKeeper()+arrayList.get(i).getGoalsAway()));
        viewHolder.shotsHomeSaved.setText(String.valueOf(arrayList.get(i).getShotsAwayMissedKeeper()));
        viewHolder.shotsAwaySaved.setText(String.valueOf(arrayList.get(i).getShotsHomeMissedKeeper()));
        viewHolder.pcHome.setText(String.valueOf(
                arrayList.get(i).getPcConvertedHome()+arrayList.get(i).getPcNotConvertedHome()));
        viewHolder.pcAway.setText(String.valueOf(
                arrayList.get(i).getPcConvertedAway()+arrayList.get(i).getPcNotConvertedAway()));
        viewHolder.pcConvertedHome.setText(String.valueOf(arrayList.get(i).getPcConvertedHome()));
        viewHolder.pcConvertedAway.setText(String.valueOf(arrayList.get(i).getPcConvertedAway()));
        viewHolder.strokeHome.setText(String.valueOf(
                arrayList.get(i).getStrokeConvertedHome()+arrayList.get(i).getStrokeNotConvertedHome()));
        viewHolder.strokeAway.setText(String.valueOf(
                arrayList.get(i).getStrokeConvertedAway()+arrayList.get(i).getStrokeNotConvertedAway()));
        viewHolder.strokeConvertedHome.setText(String.valueOf(arrayList.get(i).getStrokeConvertedHome()));
        viewHolder.strokeConvertedHome.setText(String.valueOf(arrayList.get(i).getStrokeConvertedAway()));
        int faultTotalHome = arrayList.get(i).getFaultHomeKick()+ arrayList.get(i).getFaultHomeStick()+
                arrayList.get(i).getFaultHomeBackstick()+arrayList.get(i).getFaultHomeObstruction()+
                arrayList.get(i).getFaultHomeUndercutting();
        viewHolder.faultHome.setText(String.valueOf(faultTotalHome));
        int faulttotalaway = arrayList.get(i).getFaultAwayKick()+ arrayList.get(i).getFaultAwayStick()+
                arrayList.get(i).getFaultAwayBackstick()+arrayList.get(i).getFaultAwayObstruction()+
                arrayList.get(i).getFaultAwayUndercutting();
        viewHolder.faultAway.setText(String.valueOf(faulttotalaway));
        viewHolder.cardsHome.setText(String.valueOf(arrayList.get(i).getHomeGreenCards()+arrayList.get(i).getHomeYellowCards()+
                arrayList.get(i).getHomeRedCards()));
        viewHolder.cardsAway.setText(String.valueOf(arrayList.get(i).getAwayGreenCards()+arrayList.get(i).getAwayYellowCards()+
                arrayList.get(i).getAwayRedCards()));
        viewHolder.homeGreenCards.setText(String.valueOf(arrayList.get(i).getHomeGreenCards()));
        viewHolder.awayGreenCards.setText(String.valueOf(arrayList.get(i).getAwayGreenCards()));
        viewHolder.homeYellowCards.setText(String.valueOf(arrayList.get(i).getHomeYellowCards()));
        viewHolder.awayYellowCards.setText(String.valueOf(arrayList.get(i).getAwayYellowCards()));
        viewHolder.homeRedCards.setText(String.valueOf(arrayList.get(i).getHomeRedCards()));
        viewHolder.awayRedCards.setText(String.valueOf(arrayList.get(i).getAwayRedCards()));
        viewHolder.outsideHome.setText(String.valueOf(arrayList.get(i).getOutsideHomeSide()+arrayList.get(i).getOutsideHomeClearance()+
                arrayList.get(i).getOutsideHomeCorner()));
        viewHolder.outsideAway.setText(String.valueOf(arrayList.get(i).getOutsideAwaySide()+arrayList.get(i).getOutsideAwayClearance()+
                arrayList.get(i).getOutsideAwayCorner()));
        viewHolder.outsideHomeClearance.setText(String.valueOf(arrayList.get(i).getOutsideHomeClearance()));
        viewHolder.outsideAwayClearance.setText(String.valueOf(arrayList.get(i).getOutsideAwayClearance()));
        viewHolder.outsideAwaySide.setText(String.valueOf(arrayList.get(i).getOutsideAwaySide()));
        viewHolder.outsideHomeSide.setText(String.valueOf(arrayList.get(i).getOutsideHomeSide()));
        viewHolder.outsideHomeCorner.setText(String.valueOf(arrayList.get(i).getOutsideHomeCorner()));
        viewHolder.outsideAwayCorner.setText(String.valueOf(arrayList.get(i).getOutsideAwayCorner()));
        viewHolder.faultExtendedHome.setText(String.valueOf(faultTotalHome));
        viewHolder.faultExtendedAway.setText(String.valueOf(faulttotalaway));
        viewHolder.faultExtendedNKHome.setText(String.valueOf(arrayList.get(i).getFaultHomeKick()));
        viewHolder.faultExtendedNKAway.setText(String.valueOf(arrayList.get(i).getFaultAwayKick()));
        viewHolder.faultExtendedNSHome.setText(String.valueOf(arrayList.get(i).getFaultHomeStick()));
        viewHolder.faultExtendedNSAway.setText(String.valueOf(arrayList.get(i).getFaultAwayStick()));
        viewHolder.faultExtendedNBHome.setText(String.valueOf(arrayList.get(i).getFaultHomeBackstick()));
        viewHolder.faultExtendedNBAway.setText(String.valueOf(arrayList.get(i).getFaultAwayBackstick()));
        viewHolder.faultExtendedNOHome.setText(String.valueOf(arrayList.get(i).getFaultHomeObstruction()));
        viewHolder.faultExtendedNOAway.setText(String.valueOf(arrayList.get(i).getFaultAwayObstruction()));
        viewHolder.faultExtendedNUHome.setText(String.valueOf(arrayList.get(i).getFaultHomeUndercutting()));
        viewHolder.faultExtendedNUAway.setText(String.valueOf(arrayList.get(i).getFaultAwayUndercutting()));
        viewHolder.faultExtended25Home.setText(String.valueOf(arrayList.get(i).getFaultPosition25Home()));
        viewHolder.faultExtended25Away.setText(String.valueOf(arrayList.get(i).getFaultPosition25Away()));
        viewHolder.faultExtended50Home.setText(String.valueOf(arrayList.get(i).getFaultPosition50Home()));
        viewHolder.faultExtended50Away.setText(String.valueOf(arrayList.get(i).getFaultPosition50Away()));
        viewHolder.faultExtended75Home.setText(String.valueOf(arrayList.get(i).getFaultPosition75Home()));
        viewHolder.faultExtended75Away.setText(String.valueOf(arrayList.get(i).getFaultPosition75Away()));
        viewHolder.faultExtended100Home.setText(String.valueOf(arrayList.get(i).getFaultPosition100Home()));
        viewHolder.faultExtended100Away.setText(String.valueOf(arrayList.get(i).getFaultPosition100Away()));
//        viewHolder.pcExtendedHome.setText(arrayList.get(i).getPcConvertedHome()+arrayList.get(i).getPcNotConvertedHome());
//        viewHolder.pcExtendedAway.setText(arrayList.get(i).getPcConvertedAway()+arrayList.get(i).getPcNotConvertedAway());
//        viewHolder.pcExtendedConvertedHome.setText(arrayList.get(i).getPcConvertedHome());
//        viewHolder.pcExtendedConvertedAway.setText(arrayList.get(i).getPcConvertedAway());
//        viewHolder.pcExtendedNKHome.setText(arrayList.get(i).pc);
//        viewHolder.pcExtendedNKAway.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNSHome.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNSAway.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNBHome.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNBAway.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNOHome.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNOAway.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNUHome.setText(arrayList.get(i).);
//        viewHolder.pcExtendedNUAway.setText(arrayList.get(i).);

//        databaseHelper = new DatabaseHelper(context);
//
//        viewHolder
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView quarterNumber, shotsHome, shotsHomeOnGoal, shotsAwayOnGoal,
                shotsAway, shotsHomeSaved, shotsAwaySaved, pcHome, pcAway, strokeHome, strokeAway,
                faultHome, faultAway, homeGreenCards, homeYellowCards, cardsHome, cardsAway,
                homeRedCards, awayGreenCards, awayYellowCards, awayRedCards, strokeConvertedHome,
                pcConvertedHome,pcConvertedAway, outsideHomeSide, outsideHomeClearance, outsideHomeCorner,
                outsideAwaySide, outsideAwayClearance, outsideAwayCorner, outsideHome, outsideAway,
                faultExtendedHome, faultExtendedAway, faultExtendedNKHome, faultExtendedNKAway,
                faultExtendedNSHome, faultExtendedNSAway, faultExtendedNBHome, faultExtendedNBAway,
                faultExtendedNOHome, faultExtendedNOAway, faultExtendedNUHome, faultExtendedNUAway,
                faultExtendedGHome, faultExtendedGAway, faultExtendedYHome, faultExtendedYAway,
                faultExtendedRHome, faultExtendedRAway, faultExtended25Home, faultExtended25Away,
                faultExtended50Home, faultExtended50Away, faultExtended75Home, faultExtended75Away,
                faultExtended100Home, faultExtended100Away, pcExtendedHome, pcExtendedAway, pcExtendedNKHome,
                pcExtendedNKAway, pcExtendedNSHome, pcExtendedNSAway, pcExtendedNBHome, pcExtendedNBAway,
                pcExtendedNOHome, pcExtendedNOAway, pcExtendedNUHome, pcExtendedNUAway, pcExtendedGHome,
                pcExtendedGAway, pcExtendedYHome, pcExtendedYAway, pcExtendedRHome, pcExtendedRAway,
                pcExtendedConvertedHome, pcExtendedConvertedAway;

        private int mOriginalHeight = 0;
        private boolean mIsViewExpanded = false;
        private final Context context;

        public viewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();

            shotsHome = itemView.findViewById(R.id.shots_statistics_shots_home);
            shotsAway = itemView.findViewById(R.id.shots_statistics_shots_away);
            shotsHomeOnGoal = itemView.findViewById(R.id.shots_statistics_shots_on_goal_home);
            shotsAwayOnGoal = itemView.findViewById(R.id.shots_statistics_shots_on_goal_away);
            shotsHomeSaved = itemView.findViewById(R.id.shots_statistics_shots_saves_home);
            shotsAwaySaved = itemView.findViewById(R.id.shots_statistics_shots_saves_away);

            pcHome = itemView.findViewById(R.id.pc_statistics_total_home);
            pcAway = itemView.findViewById(R.id.pc_statistics_total_away);
            pcConvertedHome = itemView.findViewById(R.id.pc_statistics_converted_home);
            pcConvertedAway = itemView.findViewById(R.id.pc_statistics_converted_away);

            strokeHome = itemView.findViewById(R.id.stroke_statistics_total_home);
            strokeAway = itemView.findViewById(R.id.stroke_statistics_total_away);
            strokeConvertedHome = itemView.findViewById(R.id.stroke_statistics_converted_home);
            strokeConvertedHome = itemView.findViewById(R.id.stroke_statistics_converted_away);

            faultHome = itemView.findViewById(R.id.fault_statistics_home);
            faultAway = itemView.findViewById(R.id.fault_statistics_away);

            cardsHome = itemView.findViewById(R.id.card_statistics_card_home);
            cardsAway = itemView.findViewById(R.id.card_statistics_card_away);
            homeGreenCards = itemView.findViewById(R.id.card_statistics_green_card_home);
            awayGreenCards = itemView.findViewById(R.id.card_statistics_green_card_away);
            homeYellowCards = itemView.findViewById(R.id.card_statistics_yellow_card_home);
            awayYellowCards = itemView.findViewById(R.id.card_statistics_yellow_card_away);
            homeRedCards = itemView.findViewById(R.id.card_statistics_red_card_home);
            awayRedCards = itemView.findViewById(R.id.card_statistics_red_card_away);

            outsideHome = itemView.findViewById(R.id.outside_statistics_total_home);
            outsideAway = itemView.findViewById(R.id.outside_statistics_total_away);
            outsideHomeClearance = itemView.findViewById(R.id.outside_statistics_clearance_home);
            outsideAwayClearance = itemView.findViewById(R.id.outside_statistics_clearance_away);
            outsideHomeSide = itemView.findViewById(R.id.outside_statistics_side_home);
            outsideAwaySide = itemView.findViewById(R.id.outside_statistics_side_away);
            outsideHomeCorner = itemView.findViewById(R.id.outside_statistics_corner_home);
            outsideAwayCorner = itemView.findViewById(R.id.outside_statistics_corner_away);

            faultExtendedHome = itemView.findViewById(R.id.fault_extended_statistics_total_home);
            faultExtendedAway = itemView.findViewById(R.id.fault_extended_statistics_total_away);

            faultExtendedNKHome = itemView.findViewById(R.id.fault_extended_statistics_kick_home);
            faultExtendedNKAway = itemView.findViewById(R.id.fault_extended_statistics_kick_away);
            faultExtendedNSHome = itemView.findViewById(R.id.fault_extended_statistics_stick_home);
            faultExtendedNSAway = itemView.findViewById(R.id.fault_extended_statistics_stick_away);
            faultExtendedNBHome = itemView.findViewById(R.id.fault_extended_statistics_back_home);
            faultExtendedNBAway = itemView.findViewById(R.id.fault_extended_statistics_back_away);
            faultExtendedNOHome = itemView.findViewById(R.id.fault_extended_statistics_obstruction_home);
            faultExtendedNOAway = itemView.findViewById(R.id.fault_extended_statistics_obstruction_away);
            faultExtendedNUHome = itemView.findViewById(R.id.fault_extended_statistics_undercutting_home);
            faultExtendedNUAway = itemView.findViewById(R.id.fault_extended_statistics_undercutting_away);

//            faultExtendedGHome = itemView.findViewById(R.id.fault_extended_statistics_green_card_home);
//            faultExtendedGAway = itemView.findViewById(R.id.fault_extended_statistics_green_card_away);
//            faultExtendedYHome = itemView.findViewById(R.id.fault_extended_statistics_yellow_card_home);
//            faultExtendedYAway = itemView.findViewById(R.id.fault_extended_statistics_yellow_card_away);
//            faultExtendedRHome = itemView.findViewById(R.id.fault_extended_statistics_red_card_home);
//            faultExtendedRAway = itemView.findViewById(R.id.fault_extended_statistics_red_card_away);

            faultExtended25Home = itemView.findViewById(R.id.fault_extended_statistics_25_home);
            faultExtended25Away = itemView.findViewById(R.id.fault_extended_statistics_25_away);
            faultExtended50Home = itemView.findViewById(R.id.fault_extended_statistics_50_home);
            faultExtended50Away = itemView.findViewById(R.id.fault_extended_statistics_50_away);
            faultExtended75Home = itemView.findViewById(R.id.fault_extended_statistics_75_home);
            faultExtended75Away = itemView.findViewById(R.id.fault_extended_statistics_75_away);
            faultExtended100Home = itemView.findViewById(R.id.fault_extended_statistics_100_home);
            faultExtended100Away = itemView.findViewById(R.id.fault_extended_statistics_100_away);

//            pcExtendedHome = itemView.findViewById(R.id.pc_extended_statistics_pc_home);
//            pcExtendedAway = itemView.findViewById(R.id.pc_extended_statistics_pc_away);
//            pcExtendedConvertedHome = itemView.findViewById(R.id.pc_extended_statistics_pc_converted_home);
//            pcExtendedConvertedAway = itemView.findViewById(R.id.pc_extended_statistics_pc_converted_away);
//
//            pcExtendedNKHome = itemView.findViewById(R.id.pc_extended_statistics_kick_home);
//            pcExtendedNKAway = itemView.findViewById(R.id.pc_extended_statistics_kick_away);
//            pcExtendedNSHome = itemView.findViewById(R.id.pc_extended_statistics_stick_home);
//            pcExtendedNSAway = itemView.findViewById(R.id.pc_extended_statistics_stick_away);
//            pcExtendedNBHome = itemView.findViewById(R.id.pc_extended_statistics_back_home);
//            pcExtendedNBAway = itemView.findViewById(R.id.pc_extended_statistics_back_away);
//            pcExtendedNOHome = itemView.findViewById(R.id.pc_extended_statistics_obstruction_home);
//            pcExtendedNOAway = itemView.findViewById(R.id.pc_extended_statistics_obstruction_away);
//            pcExtendedNUHome = itemView.findViewById(R.id.pc_extended_statistics_undercutting_home);
//            pcExtendedNUAway = itemView.findViewById(R.id.pc_extended_statistics_undercutting_away);

//            pcExtendedGHome = itemView.findViewById(R.id.pc_extended_statistics_green_card_home);
//            pcExtendedGAway = itemView.findViewById(R.id.pc_extended_statistics_green_card_away);
//            pcExtendedYHome = itemView.findViewById(R.id.pc_extended_statistics_yellow_card_home);
//            pcExtendedYAway = itemView.findViewById(R.id.pc_extended_statistics_yellow_card_away);
//            pcExtendedRHome = itemView.findViewById(R.id.pc_extended_statistics_red_card_home);
//            pcExtendedRAway = itemView.findViewById(R.id.pc_extended_statistics_red_card_away);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(getAdapterPosition(), v);
//
//                }
//            });
        }
    }

    public void setOnItemClickListener(DetailMatchAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }
}
