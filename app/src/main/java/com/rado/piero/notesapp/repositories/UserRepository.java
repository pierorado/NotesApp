package com.rado.piero.notesapp.repositories;

import com.orm.SugarRecord;
import com.rado.piero.notesapp.models.User;

import java.util.List;

public class UserRepository {
    public static void create(String fullname ,String usuario,String email,String password){
        User user=new User();
        user.setFullname(fullname);
        user.setUsuario(usuario);
        user.setEmail(email);
        user.setPassword(password);
        SugarRecord.save(user);
    }
    public static User Login(String usuario ,String password){
        List<User> users = SugarRecord.find(User.class,"usuario=? and password=?",usuario,password);
        if (!users.isEmpty()){
            return users.get(0);
        }
        return null;
    }
    public static User load(Long id){
        User user=SugarRecord.findById(User.class,id);
        return user;
    }
}
