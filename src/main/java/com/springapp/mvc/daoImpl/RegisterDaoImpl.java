package com.springapp.mvc.daoImpl;
import com.springapp.mvc.dao.RegisterDao;
import com.springapp.mvc.entity.UserInfo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by think on 2015/8/9.
 */
public class RegisterDaoImpl implements RegisterDao {
     private static Logger logger = LoggerFactory.getLogger(RegisterDaoImpl.class);
     private JdbcTemplate jdbcTemplate;
     public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
         this.jdbcTemplate = jdbcTemplate;
     }

     public JdbcTemplate getJdbcTemplate() {
         return jdbcTemplate;
     }
    @Override
    public JSONObject register(final UserInfo userInfo) {
        logger.info("registerdaoImlp start");
        try{
            JSONObject returnObject = new JSONObject();
            int count=0;
            final  String sql ="INSERT INTO userinfo(psw,nickname,headimg,sex,birthday,city,realname,email,regtime) VALUES (?,?,?,?,?,?,?,?,?); ";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            count=jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql,new String[]{"userid"});
                    ps.setString(1,userInfo.getPassword());
                    ps.setString(2,userInfo.getNickName());
                    ps.setString(3,userInfo.getHeadimg());
                    ps.setString(4,userInfo.getSex());
                    ps.setString(5,userInfo.getBirthday());
                    ps.setString(6,userInfo.getCity());
                    ps.setString(7,userInfo.getRealname());
                    ps.setString(8,userInfo.getEmail());
                    ps.setString(9,userInfo.getRegisterTime());
                    return ps;
                }
            },keyHolder);
            if(count==1){
                returnObject.put("userNum",keyHolder.getKey().intValue());
                returnObject.put("status","ok");
                returnObject.put("message","success");
            }else{
                returnObject.put("status","error");
                returnObject.put("message","userInfo insert error");
            }
            return  returnObject;
        }catch (Exception e){
            logger.error("register insert error");
            JSONObject returnObject = new JSONObject();
            returnObject.put("status","error");
            returnObject.put("message",e.getMessage());
            return returnObject;
        }
    }
}
