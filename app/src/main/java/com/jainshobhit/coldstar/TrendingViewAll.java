package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;

import java.util.ArrayList;
import java.util.List;


public class TrendingViewAll extends Fragment {


    private static final String IS_MOVIE = "IS_MOVIE";
    private static final String RESULTS_LIST = "RESULTS_LIST";
    private static final String TITLE_TEXT = "TITLE_TEXT";

    private boolean isMovie;
    private List<SearchMultiDataNode.Results> resultsList;

    private String title_text;

    public TrendingViewAll() {
        // Required empty public constructor
    }


    public static TrendingViewAll newInstance(boolean isMovie, ArrayList<SearchMultiDataNode.Results> resultsList, String title_text) {
        TrendingViewAll fragment = new TrendingViewAll();
        Bundle args = new Bundle();
        args.putBoolean(IS_MOVIE, isMovie);
        args.putSerializable(RESULTS_LIST, resultsList);
        args.putString(TITLE_TEXT, title_text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isMovie = getArguments().getBoolean(IS_MOVIE);
            title_text = getArguments().getString(TITLE_TEXT);
            resultsList = (List<SearchMultiDataNode.Results>) getArguments().getSerializable(RESULTS_LIST);
        }
    }

    TextView trendingTitle;
    RecyclerView viewAllRec;
    TrendingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending_view_all, container, false);

        trendingTitle = view.findViewById(R.id.trending_viewAll_title);
        viewAllRec = view.findViewById(R.id.viewAll_recview);

        viewAllRec.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter = new TrendingAdapter(getContext(),getActivity().getSupportFragmentManager(),resultsList,isMovie);
        viewAllRec.setAdapter(adapter);

        trendingTitle.setText(title_text);

        return view;
    }
}