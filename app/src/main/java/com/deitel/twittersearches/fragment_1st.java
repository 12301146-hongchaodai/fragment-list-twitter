package com.deitel.twittersearches;

/**
 * Created by dell on 2015/3/23.
 */
import android.app.Activity;
import android.app.ListFragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.deitel.twittersearches.R;

import java.util.ArrayList;
import java.util.Collections;


public class fragment_1st extends ListFragment {

    private static final String ARG_PARAM1 = "p1";
    private static final String ARG_PARAM2 = "p2";
    private String p1;
    private String p2;
    private OnFragmentInteractionListener mListener;
    private SharedPreferences savedSearches; // user's favorite searches
    private static final String SEARCHES = "searches";
    private ArrayList<String> tags; // list of tags for saved searches




    public static fragment_1st newInstance(String p1, String p2) {

        fragment_1st fragment = new fragment_1st();

        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, p1);

        args.putString(ARG_PARAM2, p2);

        fragment.setArguments(args);

        return fragment;

    }

    public fragment_1st() {
        // Required empty public constructor
    }
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
        // get the SharedPreferences containing the user's saved searches
        savedSearches = getActivity().getSharedPreferences(SEARCHES, getActivity().MODE_PRIVATE);
        // store the saved tags in an ArrayList then sort them
        tags = new ArrayList<String>(savedSearches.getAll().keySet());
        Collections.sort(tags, String.CASE_INSENSITIVE_ORDER);

        View view = inflater.inflate(R.layout.fragment_1st, container, false);
        ListView mListView=(ListView) view.findViewById(android.R.id.list);
        setListAdapter(mListener.getAdapter());
        //set listener that allows user to delete or edit a search
        mListView.setOnItemLongClickListener(mListener.getOnItemLongClickListener());
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        // get query string and create a URL representing the search

        String tag = ((TextView) v).getText().toString();
        String urlStr = getString(R.string.searchURL) +
                Uri.encode(savedSearches.getString(tag, ""), "UTF-8");
        String tagStr = ((TextView) v).getText().toString();
        mListener.onFragmentInteraction(urlStr, tagStr);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public ArrayAdapter<String> getAdapter();
        public void onFragmentInteraction(String url, String tag);
        public AdapterView.OnItemLongClickListener getOnItemLongClickListener();
    }

}