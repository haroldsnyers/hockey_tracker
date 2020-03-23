package ck.edu.com.hockey_tracker.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.R;


public class newMatchFragment extends Fragment {

    EditText homeTeamName;
    EditText awayTeamName;
    EditText scoreTeamHome;
    EditText scoreTeamAway;
    EditText date;

    Button submit;

    DatabaseHelper databaseHelper;

    // private OnFragmentInteractionListener mListener;

    public newMatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newMatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newMatchFragment newInstance(String param1, String param2) {
        newMatchFragment fragment = new newMatchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_match, container, false);

        homeTeamName = view.findViewById(R.id.home_team);
        awayTeamName = view.findViewById(R.id.away_team);
        scoreTeamHome = view.findViewById(R.id.score_home_team);
        scoreTeamAway = view.findViewById(R.id.score_away_team);
        date = view.findViewById(R.id.date);


        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItemSave = menu.findItem(R.id.action_save);
        MenuItem menuItemCamera = menu.findItem(R.id.action_picture);
        MenuItem menuItemSettings = menu.findItem(R.id.action_settings);

        menuItemSettings.setVisible(false);
        menuItemSave.setVisible(true);
        menuItemCamera.setVisible(true);

        getActivity().setTitle(getString(R.string.menu_new_match));
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    public void newMatch() {
        if (homeTeamName.getText().toString().isEmpty()) {
            homeTeamName.setError("Please Enter Title");
        }else if(awayTeamName.getText().toString().isEmpty()) {
            awayTeamName.setError("Please Enter Description");
        }else if(scoreTeamHome.getText().toString().isEmpty()) {
            scoreTeamHome.setError("Please Enter Description");
        }else if(scoreTeamAway.getText().toString().isEmpty()) {
            scoreTeamAway.setError("Please Enter Description");
        }else if(date.getText().toString().isEmpty()) {
            date.setError("Please Enter Description");
        }else {
            databaseHelper.addmatch(
                    homeTeamName.getText().toString(),
                    awayTeamName.getText().toString(),
                    Integer.parseInt(scoreTeamHome.getText().toString()),
                    Integer.parseInt(scoreTeamAway.getText().toString()),
                    date.getText().toString());
        }
    }

//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment_first to allow an interaction in this fragment_first to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onTextChange(String editText);
//    }
}
