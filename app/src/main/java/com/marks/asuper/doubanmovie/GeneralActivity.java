package com.marks.asuper.doubanmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.marks.asuper.doubanmovie.adapters.DoubanAdapter;
import com.marks.asuper.doubanmovie.model.DoubanBean;
import com.marks.asuper.doubanmovie.services.DoubanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GeneralActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "GeneralActivity";
    private ListView mListView;
    private List<DoubanBean.Subjects> mList;
    private DoubanAdapter mAdapter;
    private DoubanService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        initView();
    }
    public void initView() {
        mListView = (ListView) findViewById(R.id.General_listView);
        mList = new ArrayList<>();
        mAdapter = new DoubanAdapter(this,mList);
        if (mService == null) {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl("https://api.douban.com/");
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()));
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            mService = retrofit.create(DoubanService.class);
        }
        mListView.setAdapter(mAdapter);
        Observable<DoubanBean> observable = mService.getDoubanBean(0, 20);
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DoubanBean>() {
                    @Override
                    public void onCompleted() {
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mList.clear();
                        e.printStackTrace();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNext(DoubanBean bean) {
                        List<DoubanBean.Subjects> subjectses = bean.getSubjectses();
                        mList.addAll(subjectses);
                        mAdapter.notifyDataSetChanged();
                    }
                });
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DoubanBean.Subjects subjects = mList.get(position);
        String url = subjects.getContextUrl();
        Intent intent = new Intent(this , WebViewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
