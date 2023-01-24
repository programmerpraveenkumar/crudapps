package com.crudapp.Service;

import com.crudapp.Model.UserModel;
import com.crudapp.Repository.UserRepo;
import com.crudapp.RequestPojo.UserRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public UserModel createUser(UserRequest userReq){
            UserModel userModel = new UserModel();
            userModel.setAddress(userReq.getAddress());
            userModel.setDob(userReq.getDob());
            userModel.setEmail(userReq.getEmail());
            userModel.setMobile(userReq.getMobile());
            userModel.setName(userReq.getName());
            userRepo.save(userModel);//insert the new user in the Database
            return userModel;
    }
    public UserModel updateUser(Long id,UserRequest userReq) throws Exception{
        UserModel userModel = this.getUserById(id);

        if(!ObjectUtils.isEmpty(userReq.getAddress())){
            userModel.setAddress(userReq.getAddress());
        }if(!ObjectUtils.isEmpty(userReq.getDob()) ){
            userModel.setDob(userReq.getDob());
        }if(!ObjectUtils.isEmpty(userReq.getEmail()) ){
            userModel.setEmail(userReq.getEmail());
        }if(!ObjectUtils.isEmpty(userReq.getMobile()) ){
            userModel.setMobile(userReq.getMobile());
        }if(!ObjectUtils.isEmpty(userReq.getName())){
            userModel.setName(userReq.getName());
        }
        userRepo.save(userModel);//update will happen based on existing userId
        return userModel;
    }

    private UserModel getUserById(Long id)throws  Exception{
         UserModel userModel = userRepo.findById(id).orElseThrow(()->new Exception("User is not found"));
         return  userModel;
    }
    public List<UserModel> getUsers( ){
        List<UserModel> userModelList = userRepo.findAll();
        return  userModelList;
    }
    public UserModel getUserByEmail( String email)throws  Exception{
        UserModel userModel = userRepo.getUserByEmail(email).orElseThrow(()->new Exception("User is not found"));
        return  userModel;
    }
    public UserModel deleteUser(Long id) throws Exception{
        UserModel userModel = this.getUserById(id);
        userRepo.delete(userModel);
        return userModel;
    }


}
