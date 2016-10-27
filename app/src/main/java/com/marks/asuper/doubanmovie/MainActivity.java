package com.marks.asuper.doubanmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.marks.asuper.doubanmovie.adapter.MovieAdapter;
import com.marks.asuper.doubanmovie.api.DoubanService;
import com.marks.asuper.doubanmovie.model.DoubanMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnViewItemClickListener {

    private static final String TAG = "MainActivity";
    private DoubanService mDoubanServices;

    private List<DoubanMovie> mMovieList;
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        getDate();
    }


    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initDate() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://api.douban.com/v2/");
        builder.addConverterFactory(ScalarsConverterFactory.create());
        // 增加Call类型的转换器, 可以吧Retrofit和 RxJava 联动
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));
        Retrofit retrofit = builder.build();
        mDoubanServices = retrofit.create(DoubanService.class);
        mMovieList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DoubanMovie movie = new DoubanMovie();
            movie.setTitle("挑剔" + i);
            mMovieList.add(movie);
        }
        mAdapter = new MovieAdapter(mMovieList, this);
        mAdapter.setOnViewItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void getDate() {
        mDoubanServices.getMovieTop250(0, 30).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onNext: 成功");
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray array = jsonObject.optJSONArray("subjects");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject json = (JSONObject) array.get(i);
                        DoubanMovie movice = DoubanMovie.createJson2Movice(json);
                        mMovieList.add(movice);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {

                }

            }

            @Override
            public void onStart() {
                mMovieList.clear();
                super.onStart();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        DoubanMovie movie = mMovieList.get(position);
        String url = movie.getAlt();
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
