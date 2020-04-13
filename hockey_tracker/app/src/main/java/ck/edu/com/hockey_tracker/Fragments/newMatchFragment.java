package ck.edu.com.hockey_tracker.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import ck.edu.com.hockey_tracker.Data.DatabaseHelper;
import ck.edu.com.hockey_tracker.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class newMatchFragment extends Fragment implements OnMapReadyCallback, LocationListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    EditText homeTeamName;
    EditText awayTeamName;
    String address;

    private LocationManager locationManager;
    private String provider;

    private Geocoder geocoder;
    private List<Address> addresses;

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;

    private int ACCESS_FINE_LOCATION = 1;
    private int INTERNET = 1;
    private int ACCESS_COARSE_LOCATION = 1;
    private static final int REQUEST_LOCATION = 123;
    private int lat;
    private int lng;



    DatabaseHelper databaseHelper;

    private OnFragmentInteractionListener mListener;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


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


        databaseHelper = new DatabaseHelper(getActivity().getApplicationContext());

        setHasOptionsMenu(true);

        SupportMapFragment mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mMapFragment != null) {
            mMapFragment.getMapAsync(this);
        }

        // Get the location manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        }

        Location location = locationManager.getLastKnownLocation(provider);


        if (location != null) {
            Log.d(TAG, "Location achieved!");

            onLocationChanged(location);
        } else {
            Log.d(TAG, "No location :(");
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /* Request updates at startup */
    @Override
    public void onResume() {
        super.onResume();
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates((LocationListener) this);
    }


    public void newMatch() {
        if (homeTeamName.getText().toString().isEmpty()) {
            homeTeamName.setError("Please Enter home Team name");
        } else if (awayTeamName.getText().toString().isEmpty()) {
            awayTeamName.setError("Please Enter away Team name");
        } else {
            LocalDateTime now = LocalDateTime.now();
            String dateNow = dtf.format(now);

            mListener.onSubmit(homeTeamName.getText().toString(), awayTeamName.getText().toString(),
                    dateNow, address);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment_first to allow an interaction in this fragment_first to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSubmit(String homeTeam, String awayTeam, String date, String location);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = (int) (location.getLatitude());
        lng = (int) (location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity().getApplicationContext(), "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getActivity().getApplicationContext(), "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getActivity().getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);

        geocoder = new Geocoder(getActivity().getApplicationContext(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        address = getAddress();

        Toast.makeText(getActivity().getApplicationContext(), address, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: " + address);

        mMap.addMarker(new MarkerOptions().position(sydney).title("current localisation"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public String getAddress(){
        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();

        return address + " " + city + " " + state + " " + country;
    }


}
