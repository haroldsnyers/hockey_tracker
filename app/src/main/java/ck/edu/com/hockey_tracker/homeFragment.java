package ck.edu.com.hockey_tracker;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.Data.MatchModel;


public class homeFragment extends Fragment {

    ArrayList<MatchModel> arrayList;
    RecyclerView recyclerView;
    ArrayList<String> list;
    Spinner spinner;
    HomePageMatchAdapter homePageMatchAdapter;
    DatabaseHelper databaseHelper;

    FloatingActionButton floatingActionButtonAdd;

    // private OnFragmentInteractionListener mListener;

    public homeFragment() {
        // Required empty public constructor
    }

    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        floatingActionButtonAdd = view.findViewById(R.id.new_match_button);

        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new newMatchFragment());
            }
        });

        recyclerView = view.findViewById(R.id.recycleView);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        arrayList = new ArrayList<>(databaseHelper.getMatches());
        list = new ArrayList<>();

        list.add("Vertical List");
        list.add("Horizontal List");
        list.add("Grid Layout Manager");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), R.layout.spinner_list, list);
        spinner.setAdapter(adapter);

        addItems();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void  onItemSelected(AdapterView parent, View view, int position, long id) {
                if(position == 0) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }else if (position == 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }else {
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    addItems();
                }

            }
            @Override
            public void  onNothingSelected(AdapterView parent) {

            }
        });

        return view;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();
    }

    public void addItems() {

        homePageMatchAdapter = new HomePageMatchAdapter(getActivity().getApplicationContext(), arrayList);
        recyclerView.setAdapter(homePageMatchAdapter);
    }
}
