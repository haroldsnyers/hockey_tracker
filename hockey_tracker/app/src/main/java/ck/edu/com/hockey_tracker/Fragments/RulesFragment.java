package ck.edu.com.hockey_tracker.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ck.edu.com.hockey_tracker.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RulesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RulesFragment extends Fragment {

    private WebView webView;

    public RulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RulesFragment newInstance() {
        RulesFragment fragment = new RulesFragment();
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
        View view = inflater.inflate(R.layout.fragment_rules, container, false);

        webView = view.findViewById(R.id.myWebView);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }});

        webView.loadUrl("https://www.ncaa.com/news/fieldhockey/article/2019-09-29/field-hockey-rules-explained");
        // webView.loadUrl("http://www.fih.ch/inside-fih/our-official-documents/rules-of-hockey/");

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

}
