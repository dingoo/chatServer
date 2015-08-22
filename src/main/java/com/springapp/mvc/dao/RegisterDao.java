package com.springapp.mvc.dao;


import com.springapp.mvc.entity.UserInfo;
import org.json.JSONObject;

/**
 * Created by think on 2015/8/9.
 */
public interface RegisterDao {
    public JSONObject register(final UserInfo userInfo);
}
