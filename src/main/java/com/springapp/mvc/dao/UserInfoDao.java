package com.springapp.mvc.dao;

import com.springapp.mvc.entity.UserInfo;
import org.json.JSONObject;

/**
 * Created by think on 2015/8/13.
 */
public interface UserInfoDao {
    public JSONObject getUserInfo(String userId);
    public JSONObject getUserFriends(String userId);
}
