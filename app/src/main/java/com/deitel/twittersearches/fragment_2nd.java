package com.deitel.twittersearches;

/**
 * Created by dell on 2015/3/23.
 */
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.deitel.twittersearches.R;


public class fragment_2nd extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "p1";
    private static final String ARG_PARAM2 = "p2";
    private static final String SEARCHES = "searches";

    private String p1;
    private String p2;

    private WebView webView;
    private TextView textView;


    public static fragment_2nd newInstance(String p1, String p2) {
        fragment_2nd fragment = new fragment_2nd();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, p1);
        args.putString(ARG_PARAM2, p2);
        fragment.setArguments(args);
        return fragment;
    }

    public fragment_2nd() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            p1 = getArguments().getString(ARG_PARAM1);
            p2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2nd, container, false);
        webView = (WebView)view.findViewById(R.id.webView);
        textView = (TextView) view.findViewById(R.id.textView2);
        String str = p2 + " Search";
        textView.setText(str);
        webView.loadUrl(p1);
        return view;
    }

}
