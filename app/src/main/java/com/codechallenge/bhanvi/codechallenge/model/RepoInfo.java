package com.codechallenge.bhanvi.codechallenge.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bhanvisangar on 2/12/17.
 */
public class RepoInfo {
    public String id;
    public String name;
    public String language;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("clone_url")
    public String cloneUrl;

    public Owner owner;

    public class Owner {
        public String login;
        @SerializedName("avatar_url")
        public String avatarUrl;
    }
}
