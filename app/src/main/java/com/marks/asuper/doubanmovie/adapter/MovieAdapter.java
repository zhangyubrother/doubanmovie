package com.marks.asuper.doubanmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marks.asuper.doubanmovie.R;
import com.marks.asuper.doubanmovie.model.DoubanMovie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2016/10/27.
 * Email ${EMAIL}
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<DoubanMovie> mMovieList;
    private Context mContext;
    private OnViewItemClickListener mOnViewItemClickListener;

    public MovieAdapter(List<DoubanMovie> movieList, Context context) {
        mMovieList = movieList;
        mContext = context;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextView mTxtTitle;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder ret = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        ret = new ViewHolder(view);
        ret.mImage = ((ImageView) view.findViewById(R.id.item_movie_image));
        ret.mTxtTitle = ((TextView) view.findViewById(R.id.item_movie_title));
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DoubanMovie movie = mMovieList.get(position);
        String url = movie.getImagesUrl();
        String title = movie.getTitle();
        Picasso.with(mContext)
                .load(url)
                .into(holder.mImage);
        holder.mTxtTitle.setText(title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnViewItemClickListener != null) {
                    mOnViewItemClickListener.onItemClick(position);
                }
            }
        });

    }

    public void setOnViewItemClickListener(OnViewItemClickListener onViewItemClickListener) {
        mOnViewItemClickListener = onViewItemClickListener;
    }

    public interface OnViewItemClickListener {
        void onItemClick(int posotion);
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if (mMovieList != null) {
            ret = mMovieList.size();
        }
        return ret;
    }
}
