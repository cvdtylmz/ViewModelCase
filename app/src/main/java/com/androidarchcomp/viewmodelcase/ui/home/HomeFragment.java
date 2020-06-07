package com.androidarchcomp.viewmodelcase.ui.home;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidarchcomp.viewmodelcase.R;
import com.androidarchcomp.viewmodelcase.adapter.weather.AdapterWeather;


import java.util.ArrayList;

import java.util.Objects;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class HomeFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView rvWeather;
    private View view;
    private CityViewModel cityViewModel;
    private WeatherViewModel weatherViewModel;
    private ProgressBar loadingView;
    private String city;
    private ArrayAdapter<String> adapterCity;
    private ArrayList<String> cityList = new ArrayList<>();
    private ListView cityNameListView;
    private LinearLayoutManager manager = new LinearLayoutManager(getContext());
    private SnapHelper snapHelper = new PagerSnapHelper();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_page, container, false);
        bindViews();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        observeViewModels();
        rvWeather.setAdapter(new AdapterWeather(weatherViewModel,this));
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvWeather.setLayoutManager(manager);
        snapHelper.attachToRecyclerView(rvWeather);
        searchViewTextListener();
        cityNameListView.setOnItemClickListener((adapterView, view1, i, l) -> {
            city = cityList.get(i);
            weatherViewModel.fetchWeather(city);
            searchView.clearFocus();
        });

    }

    private void observeViewModels () {
        cityViewModel.getCities().observe(this, cities -> {
            if (cities != null ) {
               for (int i = 0; i <cities.getData().size(); i++) {
                   cityList.add(cities.getData().get(i).getName());
               }
                adapterCity = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1, cityList);
                cityNameListView.setAdapter(adapterCity);
            }
        });

        //noinspection LambdaParameterTypeCanBeSpecified
        cityViewModel.getCitiesError().observe(this, aBoolean -> {
            if (aBoolean){
                searchView.setVisibility(View.GONE);
                Toast.makeText(getContext(), Objects.requireNonNull(getContext()).getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
            }else {
                searchView.setVisibility(View.VISIBLE);
            }
          });

        cityViewModel.getCitiesLoading().observe(this, isLoading -> {
            //noinspection ConditionalExpression
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                rvWeather.setVisibility(View.GONE);
            }
        });

        weatherViewModel.getWeatherData().observe(this, weatherData -> {
            if (weatherData != null){
                rvWeather.setVisibility(View.VISIBLE);
                cityNameListView.setVisibility(View.GONE);
            }
        });


        //noinspection LambdaParameterTypeCanBeSpecified
        weatherViewModel.getWeatherError().observe(this, aBoolean -> {
            if (aBoolean){
                rvWeather.setVisibility(View.GONE);
                Toast.makeText(getContext(), getResources().getString(R.string.weather_error), Toast.LENGTH_SHORT).show();
            }else {
                rvWeather.setVisibility(View.VISIBLE);
            }
        });

        weatherViewModel.getWeatherLoading().observe(this, isLoading -> {
            //noinspection ConditionalExpression
            loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (isLoading) {
                rvWeather.setVisibility(View.GONE);
            }
        });


    }
    private void searchViewTextListener () {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (cityList.contains(s.toUpperCase())) {
                    adapterCity.getFilter().filter(s);
                    city = s;
                }
                else Toast.makeText(getContext(), getResources().getString(R.string.failed_city_filter), Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!cityNameListView.isShown() && rvWeather.isShown() && !s.isEmpty()) {
                    cityNameListView.setVisibility(View.VISIBLE);
                    rvWeather.setVisibility(View.GONE);
                }
                adapterCity.getFilter().filter(s);
                return false;
            }
        });
    }

    private void bindViews () {
        rvWeather = view.findViewById(R.id.rv_weather);
        searchView = view.findViewById(R.id.src_view);
        loadingView = view.findViewById(R.id.loading_view);
        cityNameListView = view.findViewById(R.id.list_city);
    }
}
