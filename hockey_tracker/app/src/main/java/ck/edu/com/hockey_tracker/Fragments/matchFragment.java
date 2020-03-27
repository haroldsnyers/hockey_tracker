package ck.edu.com.hockey_tracker.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import ck.edu.com.hockey_tracker.Adapters.HomePageMatchAdapter;
import ck.edu.com.hockey_tracker.Data.MatchModel;
import ck.edu.com.hockey_tracker.R;

/**
 * A fragment representing a list of Items.
 */
public class matchFragment extends Fragment {
    ArrayList<MatchModel> arrayList;
    RecyclerView recyclerView;
    ArrayList<String> list;
    Spinner spinner;
    HomePageMatchAdapter homePageMatchAdapter;

    FloatingActionButton floatingActionButtonAdd;
    ObjectMapper objectMapper;

    private OnFragmentInteractionListener mListener;

    private static final String ARG_PARAM1 = "";
    private String mParam1 = "";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public matchFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static matchFragment newInstance(String param1) {
        matchFragment fragment = new matchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);

        floatingActionButtonAdd = view.findViewById(R.id.new_match_button);

        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new newMatchFragment());
            }
        });

        arrayList = new ArrayList<>();
        objectMapper = new ObjectMapper();

        recyclerView = view.findViewById(R.id.recycleViewPrevious);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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

    public void setMatchModel(ArrayList<MatchModel> matchModelArrayList) {
        arrayList = matchModelArrayList;
        Log.d("TEST1", arrayList.toString());
    }

    public void addItems() {
        Log.d("TEST2", arrayList.toString());
        try {
            arrayList = objectMapper.readValue(mParam1, new TypeReference<ArrayList<MatchModel>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        homePageMatchAdapter = new HomePageMatchAdapter(getActivity().getApplicationContext(), arrayList, R.layout.layout_match_view_model);
        recyclerView.setAdapter(homePageMatchAdapter);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment_first to allow an interaction in this fragment_first to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentLoaded(boolean ok);
    }
}
