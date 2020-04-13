package ck.edu.com.hockey_tracker.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import ck.edu.com.hockey_tracker.Adapters.DetailMatchAdapter;
import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.Data.QuarterModel;
import ck.edu.com.hockey_tracker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailfragmentInternal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailfragmentInternal extends Fragment {
    ArrayList<QuarterModel> arrayList;
    RecyclerView recyclerView;

    DetailMatchAdapter detailMatchAdapter;
    ObjectMapper objectMapper;

    private TextView homeTeam;
    private TextView awayTeam;

    private TextView homeTotal;
    private TextView homeQ1;
    private TextView homeQ2;
    private TextView homeQ3;
    private TextView homeQ4;

    private TextView awayTotal;
    private TextView awayQ1;
    private TextView awayQ2;
    private TextView awayQ3;
    private TextView awayQ4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private long mParam1;
    private String mParam2;
    private String mParam3;

    DatabaseHelper databaseHelper;

    public DetailfragmentInternal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Detailfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailfragmentInternal newInstance(long param1, String param2, String param3) {
        DetailfragmentInternal fragment = new DetailfragmentInternal();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getLong(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailfragment, container, false);

        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        arrayList = new ArrayList<>(databaseHelper.getQuarters(mParam1));
        objectMapper = new ObjectMapper();

        homeTeam = view.findViewById(R.id.team_home_name);
        awayTeam = view.findViewById(R.id.team_away_name);
        homeTotal = view.findViewById(R.id.home_total);
        homeQ1 = view.findViewById(R.id.home_Q1);
        homeQ2 = view.findViewById(R.id.home_Q2);
        homeQ3 = view.findViewById(R.id.home_Q3);
        homeQ4 = view.findViewById(R.id.home_Q4);
        awayTotal = view.findViewById(R.id.away_total);
        awayQ1 = view.findViewById(R.id.away_Q1);
        awayQ2 = view.findViewById(R.id.away_Q2);
        awayQ3 = view.findViewById(R.id.away_Q3);
        awayQ4 = view.findViewById(R.id.away_Q4);

        recyclerView = view.findViewById(R.id.recycleViewDetail);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        addItems();

        updateScore();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void addItems() {

        detailMatchAdapter = new DetailMatchAdapter(getActivity().getApplicationContext(), arrayList, R.layout.statistics_detail);
        recyclerView.setAdapter(detailMatchAdapter);
    }

    public void updateScore() {
        homeTeam.setText(mParam2);
        awayTeam.setText(mParam3);
        homeTotal.setText(String.format("%d", arrayList.get(0).getGoalsHome()));
        homeQ1.setText(String.format("%d", arrayList.get(1).getGoalsHome()));
        homeQ2.setText(String.format("%d", arrayList.get(2).getGoalsHome()));
        homeQ3.setText(String.format("%d", arrayList.get(3).getGoalsHome()));
        homeQ4.setText(String.format("%d", arrayList.get(4).getGoalsHome()));
        awayTotal.setText(String.format("%d", arrayList.get(0).getGoalsAway()));
        awayQ1.setText(String.format("%d", arrayList.get(1).getGoalsAway()));
        awayQ2.setText(String.format("%d", arrayList.get(2).getGoalsAway()));
        awayQ3.setText(String.format("%d", arrayList.get(3).getGoalsAway()));
        awayQ4.setText(String.format("%d", arrayList.get(4).getGoalsAway()));
    }
}
