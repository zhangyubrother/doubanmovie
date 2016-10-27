package com.marks.asuper.doubanmovie.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marks.asuper.doubanmovie.R;
import com.marks.asuper.doubanmovie.model.DoubanBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class DoubanAdapter extends BaseAdapter {
    private Context mContext;
    private List<DoubanBean.Subjects> mItems;

    public DoubanAdapter(Context context, List<DoubanBean.Subjects> items) {
        mContext = context;
        mItems = items;
    }
    @Override
    public int getCount() {
        int ret = 0;
        if (mItems != null) {
            ret = mItems.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        if (convertView != null) {
            ret = convertView;
        }else{
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ret = inflater.inflate(R.layout.douban_item, parent, false);
        }

        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder(ret,mContext);
            ret.setTag(holder);
        }

        DoubanBean.Subjects subjects = mItems.get(position);
        holder.bindView(subjects);

        return ret;
    }

    private static class ViewHolder {

        private final ImageView mImage;
        private final Context mContext;
        private TextView mActorName;
        private TextView mDirectorName;

        ViewHolder(View itemView , Context mContext){
            mActorName = (TextView) itemView.findViewById(R.id.item_actor_name);
            mDirectorName = (TextView) itemView.findViewById(R.id.item_director_name);
            mImage = (ImageView) itemView.findViewById(R.id.item_image);
            this.mContext = mContext;
        }

        void bindView( DoubanBean.Subjects subjects) {
            String temp = "";
                List<DoubanBean.Subjects.Actor> actors = subjects.getActors();
                for (DoubanBean.Subjects.Actor actor : actors) {
                    temp += actor.getName() + ",";
                }
                temp = temp.substring(0, temp.length() - 1);
                mActorName.setText(temp);
                mDirectorName.setText(subjects.getDirector().get(0).getName());
                String icon = subjects.getImages().getSmallIcon();
//                TODO: 图片加载
                Picasso.with(mContext)
                        .load(icon)
                        .resize(100,100)
                        .config(Bitmap.Config.RGB_565)
                        .into(mImage);
        }
    }
}
