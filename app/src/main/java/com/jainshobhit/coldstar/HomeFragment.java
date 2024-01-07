package com.jainshobhit.coldstar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview;
import com.jainshobhit.coldstar.DataNode.ImagesNode;
import com.jainshobhit.coldstar.DataNode.SearchMultiDataNode;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    List<SliderItem> sliderItemList = new ArrayList<>();
    SliderView sliderView;
    SliderAdapter adapter;
    CarouselRecyclerview latestRelease;
    CarouselAdapter latestReleaseAdapter;
    RecyclerView horrorMovies,upcommingMovies;
    SearchAdapter horrorAdapter,upcommingMoviesAdapter;
    List<HashMap<String,Object>> latestReleaseData = new ArrayList<>();
    ArrayList<HashMap<String,Object>> horrorList = new ArrayList<>();
    ArrayList<HashMap<String,Object>> upcommingMovieseData = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SmoothBottomBar bottomBar = getActivity().findViewById(R.id.mainBottomNav);
        bottomBar.setItemActiveIndex(0);
        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapter(getContext(), getActivity().getSupportFragmentManager());
        sliderView.setSliderAdapter(adapter);

        latestRelease = view.findViewById(R.id.latestreleaseofhome);
        latestReleaseAdapter = new CarouselAdapter(getContext(), getActivity().getSupportFragmentManager(), latestReleaseData);
        latestRelease.setAdapter(latestReleaseAdapter);

        latestRelease.setAlpha(true);
        latestRelease.set3DItem(true);
        latestRelease.setInfinite(true);

        horrorMovies = view.findViewById(R.id.horrormoviesofhome);
        horrorMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        horrorAdapter = new SearchAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), horrorList,false);
        horrorMovies.setAdapter(horrorAdapter);

        upcommingMovies = view.findViewById(R.id.upcommingmoviesofhome);
        upcommingMovies.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        upcommingMoviesAdapter = new SearchAdapter(getActivity().getApplicationContext(), getActivity().getSupportFragmentManager(), upcommingMovieseData,false);
        upcommingMovies.setAdapter(upcommingMoviesAdapter);

        loadSlider();
        loadHorrorMovies();
        loadLatestRelease();
        loadUpcommingMovies();

        adapter.renewItems(sliderItemList);


//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.startAutoCycle();



        return view;

    }

    private void loadUpcommingMovies() {
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getUpcommingMovies();
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();

                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",true);
                    upcommingMovieseData.add(oneResult);
                }
                upcommingMoviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private void loadHorrorMovies() {
        horrorList.clear();
        Call<SearchMultiDataNode> call = ApiRef.getInstance().getData().getByGenre("27");
        call.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();

                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getPoster_path()==null) continue;
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results1.getId());
                    oneResult.put("poster_path",results1.getPoster_path());
                    oneResult.put("isMovie",true);
                    horrorList.add(oneResult);
                }
                horrorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private void loadLatestRelease() {
        latestReleaseData.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date mydate = new Date();
        String date = format.format(mydate);
        Call<SearchMultiDataNode> horrorCall = ApiRef.getInstance().getData().getLatestReleaseMovies(date);
        horrorCall.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode node = response.body();
                for(SearchMultiDataNode.Results results : node.getResults()){
                    HashMap<String,Object> oneResult = new HashMap<>();
                    oneResult.put("id",results.getId());
                    oneResult.put("isMovie",true);
                    oneResult.put("poster_path",results.getPoster_path());
                    latestReleaseData.add(oneResult);
                    latestReleaseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });
    }

    private void loadSlider() {
        sliderItemList.clear();

        Call<SearchMultiDataNode> sliderItemCall = ApiRef.getInstance().getData().getTrending();
        sliderItemCall.enqueue(new Callback<SearchMultiDataNode>() {
            @Override
            public void onResponse(Call<SearchMultiDataNode> call, Response<SearchMultiDataNode> response) {
                SearchMultiDataNode myData = response.body();
                int i = 0;
                for(SearchMultiDataNode.Results results1 : myData.getResults()){
                    if(results1.getFirst_air_date()!=null){
                        Call<ImagesNode> imagesNodeCall = ApiRef.getInstance().getData().getImages("tv",results1.getId().toString());
                        imagesNodeCall.enqueue(new Callback<ImagesNode>() {
                            @Override
                            public void onResponse(Call<ImagesNode> call, Response<ImagesNode> response) {
                                ImagesNode node = response.body();
                                SliderItem sliderItem = new SliderItem(results1.getId(),results1.getBackdrop_path(), node.getLogos().get(0).getFile_path(), results1.getName(),false);
//                                HashMap<String,Object> map = new HashMap<>();
//                                map.put("id", results1.getId());
//                                map.put("back_image", results1.getBackdrop_path());
//                                map.put("title_image", node.getLogos().get(0).getFile_path());
//                                map.put("title", results1.getName());
                                sliderItemList.add(sliderItem);
                                adapter.renewItems(sliderItemList);
                            }

                            @Override
                            public void onFailure(Call<ImagesNode> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        Call<ImagesNode> imagesNodeCall = ApiRef.getInstance().getData().getImages("movie",results1.getId().toString());
                        imagesNodeCall.enqueue(new Callback<ImagesNode>() {
                            @Override
                            public void onResponse(Call<ImagesNode> call, Response<ImagesNode> response) {
                                ImagesNode node = response.body();
                                SliderItem sliderItem = new SliderItem(results1.getId(),results1.getBackdrop_path(), node.getLogos().get(0).getFile_path(), results1.getTitle(),true);
//                                HashMap<String,Object> map = new HashMap<>();
//                                map.put("id", results1.getId());
//                                map.put("back_image", results1.getBackdrop_path());
//                                map.put("title_image", node.getLogos().get(0).getFile_path());
//                                map.put("title", results1.getTitle());
                                sliderItemList.add(sliderItem);
                                adapter.renewItems(sliderItemList);
                            }

                            @Override
                            public void onFailure(Call<ImagesNode> call, Throwable t) {

                            }
                        });
                    }

                    i++;
                    if (i==5) break;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SearchMultiDataNode> call, Throwable t) {

            }
        });

    }

    public void here(View view) {
        MoviesFragment bottomSheetFragment = new MoviesFragment();
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}