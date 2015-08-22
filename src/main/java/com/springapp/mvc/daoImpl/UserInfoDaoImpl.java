package com.springapp.mvc.daoImpl;

import com.springapp.mvc.dao.UserInfoDao;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by think on 2015/8/13.
 */
public class UserInfoDaoImpl implements UserInfoDao{
    private static Logger logger = LoggerFactory.getLogger(RegisterDaoImpl.class);
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
    @Override
    public JSONObject getUserInfo(String userId) {
        logger.info("getUserInfo is started");
        try{
            JSONObject returnObject = new JSONObject();
            final JSONObject userInfo = new JSONObject();
            String sql="SELECT * FROM userinfo WHERE userid=?";
            jdbcTemplate.query(sql, new Object[]{userId},new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    userInfo.put("userNum",resultSet.getString("userid"));
                    userInfo.put("passWord",resultSet.getString("psw"));
                    userInfo.put("nickName",resultSet.getString("nickname"));
                    userInfo.put("headImg",resultSet.getString("headimg"));
                    userInfo.put("sex",resultSet.getString("sex"));
                    userInfo.put("birthday",resultSet.getString("birthday"));
                    userInfo.put("city",resultSet.getString("city"));
                    userInfo.put("realName",resultSet.getString("realname"));
                    userInfo.put("email",resultSet.getString("email"));
                    userInfo.put("registerTime",resultSet.getString("regtime"));
                }
            });
            returnObject.put("status","ok");
            returnObject.put("message","successful");
            returnObject.put("userInfo",userInfo);
            return returnObject;
        }catch(Exception e){
            logger.error("select userInfo error! message is " + e.getMessage());
            JSONObject returnObject = new JSONObject();
            returnObject.put("status", "error");
            returnObject.put("message",e.getMessage());
            return returnObject;
        }
    }

    @Override
    public JSONObject getUserFriends(String userId) {
        logger.info("getUserFriends is start");
        try{
            JSONObject returnObject = new JSONObject();
            final JSONArray friends = new JSONArray();
            String sql1="SELECT * FROM userinfo WHERE userid IN (SELECT userid_a FROM relationship WHERE userid_a = ? OR userid_b = ?);";
            String sql2="SELECT * FROM userinfo WHERE userid IN (SELECT userid_b FROM relationship WHERE userid_a = ?);";
            jdbcTemplate.query(sql1, new Object[]{userId, userId}, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("userNum", resultSet.getString("userid"));
                    userInfo.put("passWord", resultSet.getString("psw"));
                    userInfo.put("nickName", resultSet.getString("nickname"));
                    userInfo.put("headImg", resultSet.getString("headimg"));
                    userInfo.put("sex", resultSet.getString("sex"));
                    userInfo.put("birthday", resultSet.getString("birthday"));
                    userInfo.put("city", resultSet.getString("city"));
                    userInfo.put("realName", resultSet.getString("realname"));
                    userInfo.put("email", resultSet.getString("email"));
                    userInfo.put("registerTime", resultSet.getString("regtime"));
                    friends.put(userInfo);
                }
            });
            jdbcTemplate.query(sql2, new Object[]{userId}, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    JSONObject userInfo = new JSONObject();
                    userInfo.put("userNum", resultSet.getString("userid"));
                    userInfo.put("passWord", resultSet.getString("psw"));
                    userInfo.put("nickName", resultSet.getString("nickname"));
                    userInfo.put("headImg", resultSet.getString("headimg"));
                    userInfo.put("sex", resultSet.getString("sex"));
                    userInfo.put("birthday", resultSet.getString("birthday"));
                    userInfo.put("city", resultSet.getString("city"));
                    userInfo.put("realName", resultSet.getString("realname"));
                    userInfo.put("email", resultSet.getString("email"));
                    userInfo.put("registerTime", resultSet.getString("regtime"));
                    friends.put(userInfo);
                }
            });
            returnObject.put("status", "ok");
            returnObject.put("message", "success");
            returnObject.put("friendsArray",friends);
            return  returnObject;
        }catch (Exception e){
            logger.error("get friends error,the message is "+e.getMessage());
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message",e.getMessage());
            return returnObject;
        }
    }
}
