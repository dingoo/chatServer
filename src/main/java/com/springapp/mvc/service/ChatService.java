package com.springapp.mvc.service;

import com.springapp.mvc.Configure.Configure;
import com.springapp.mvc.dao.RegisterDao;
import com.springapp.mvc.dao.UserInfoDao;
import com.springapp.mvc.entity.UserInfo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by think on 2015/8/8.
 */
@Service
public class ChatService {
    Logger logger = LoggerFactory.getLogger(ChatService.class);
    @Resource
    private RegisterDao registerDao;
    public String register(String requestBody){
        logger.info("Service register start---");
        JSONObject bodyData = new JSONObject(requestBody);
        JSONObject returnObject = new JSONObject();
        String password = bodyData.has("password")?bodyData.getString("password"):" ";
        String nickName = bodyData.has("nickName")?bodyData.getString("nickName"):" ";
        String headImg = bodyData.has("headImg")?bodyData.getString("headImg"): Configure.DEFAULTHEADIMG;
        String sex = bodyData.has("sex")?bodyData.getString("sex"):" ";
        String birthday = bodyData.has("birthday")?bodyData.getString("birthday"):Configure.DEFAULTBIRTHDAY;
        String city = bodyData.has("city")?bodyData.getString("city"):"-1";
        String realName = bodyData.has("realName")?bodyData.getString("realName"):"-1";
        String email = bodyData.has("email")?bodyData.getString("email"):"-1";
        if(nickName.equals("")&&password.equals("")&&sex.equals("")){
            returnObject.put("status","error");
            returnObject.put("message","info is null");
            return  returnObject.toString();
        }
        Date dateTime = new Date();
        Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String registerTime = format.format(dateTime);
        UserInfo userInfo = new UserInfo("",password,nickName,headImg,sex,birthday,city,realName,email,registerTime);
        returnObject=registerDao.register(userInfo);
        if(!returnObject.getString("status").equals("ok")){
            returnObject.put("status","error");
            returnObject.put("message","userInfo insert error");
            return  returnObject.toString();
        }
        return returnObject.toString();
    }
    @Resource
    UserInfoDao userInfoDao;
    public String getUserInfo(String requestBody) {
        logger.info("Service getUserInfo start---");
        JSONObject bodyData = new JSONObject(requestBody);
        String userId = bodyData.has("userId") ? bodyData.getString("userId"):"";
        if(userId.equals("")){
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message","userId is needed");
            return returnObject.toString();
        }
        try{
            JSONObject returnObject = new JSONObject();
            returnObject=userInfoDao.getUserInfo(userId);
            return  returnObject.toString();
        }catch (Exception e){
            logger.error("ChatService getUserInfo is error");
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message",e.getMessage());
            return  returnObject.toString();
        }
    }

    public String getFriends(String requestBody) {
        logger.info("Service getFriends start---");
        JSONObject bodyData = new JSONObject(requestBody);
        String userId = bodyData.has("userId") ? bodyData.getString("userId"):"";
        if(userId.equals("")){
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message","userId is needed");
            return returnObject.toString();
        }
        try{
            JSONObject returnObject = new JSONObject();
            returnObject=userInfoDao.getUserFriends(userId);
            return  returnObject.toString();
        }catch (Exception e){
            logger.error("ChatService getUserInfo is error");
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message",e.getMessage());
            return  returnObject.toString();
        }
    }
}
