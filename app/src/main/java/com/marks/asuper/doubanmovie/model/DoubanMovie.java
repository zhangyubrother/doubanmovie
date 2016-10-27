package com.marks.asuper.doubanmovie.model;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by XIAOHONG
 * Author: XIAOHONG.
 * Date: 2016/10/27.
 * Email ${EMAIL}
 */

public class DoubanMovie {
    @SerializedName("id")
    private String id;  // 电影ID
    @SerializedName("alt")
    private String alt;  // 电影详情
    @SerializedName("images")
    private String imagesUrl;  // 剧照
    @SerializedName("title")
    private String title; // 标题


    public static DoubanMovie createJson2Movice(JSONObject json) {
        DoubanMovie ret = new DoubanMovie();
        ret.setId(json.optString("id"));
        ret.title = json.optString("title");
        ret.alt = json.optString("alt");
        ret.imagesUrl = json.optJSONObject("images").optString("small");
        return ret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
