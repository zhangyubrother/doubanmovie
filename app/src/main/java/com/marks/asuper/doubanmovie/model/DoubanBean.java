package com.marks.asuper.doubanmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */

public class DoubanBean {
    @SerializedName("count")
    private int count;
    @SerializedName("title")
    private String title;
    @SerializedName("subjects")
    private List<Subjects> mSubjectses;

    @Override
    public String toString() {
        return "DoubanBean{" +
                "count=" + count +
                ", title='" + title + '\'' +
                ", mSubjectses=" + mSubjectses +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subjects> getSubjectses() {
        return mSubjectses;
    }

    public void setSubjectses(List<Subjects> subjectses) {
        mSubjectses = subjectses;
    }

    public class Subjects {
        @SerializedName("title")
        private String title;
        @SerializedName("alt")
        private String contextUrl;

        @SerializedName("images")
        private Images mImages;
        @SerializedName("casts")
        private List<Actor> mActors;
        @SerializedName("directors")
        private List<Director> mDirector;

        @Override
        public String toString() {
            return "Subjects{" +
                    "title='" + title + '\'' +
                    ", contextUrl='" + contextUrl + '\'' +
                    ", mImages=" + mImages +
                    ", mActors=" + mActors +
                    ", mDirector=" + mDirector +
                    '}';
        }


        public List<Actor> getActors() {
            return mActors;
        }

        public void setActors(List<Actor> actors) {
            mActors = actors;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContextUrl() {
            return contextUrl;
        }

        public void setContextUrl(String contextUrl) {
            this.contextUrl = contextUrl;
        }

        public Images getImages() {
            return mImages;
        }

        public void setImages(Images images) {
            mImages = images;
        }

        public List<Director> getDirector() {
            return mDirector;
        }

        public void setDirector(List<Director> director) {
            mDirector = director;
        }

        public class Images {
            @SerializedName("small")
            private String smallIcon;

            @Override
            public String toString() {
                return "Images{" +
                        "smallIcon='" + smallIcon + '\'' +
                        '}';
            }

            public String getSmallIcon() {
                return smallIcon;
            }

            public void setSmallIcon(String smallIcon) {
                this.smallIcon = smallIcon;
            }
        }
        public class Actor {
            @SerializedName("name")
            private String name;

            @Override
            public String toString() {
                return "Actor{" +
                        "name='" + name + '\'' +
                        '}';
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        public class Director {
            @SerializedName("name")
            private String name;

            @Override
            public String toString() {
                return "Actor{" +
                        "name='" + name + '\'' +
                        '}';
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
