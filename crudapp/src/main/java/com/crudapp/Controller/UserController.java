package com.crudapp.Controller;


import com.crudapp.Model.UserModel;
import com.crudapp.RequestPojo.UserRequest;
import com.crudapp.Response.UserResponse;
import com.crudapp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    /*
    user/create->post
	user/list->get
	user/delete->post
	user/update/{user_id}->post
     */
    @Autowired
    UserService userService;
    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        UserModel userModel = userService.createUser(userRequest);
        return ResponseEntity.ok(userModel);
    }
    @PostMapping("update/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable  Long userId,@RequestBody UserRequest userRequest){
        try{
            UserModel userModel =userService.updateUser(userId,userRequest);
            return ResponseEntity.ok(userModel);
        }catch (Exception e){
            e.printStackTrace();
            UserResponse response = new UserResponse();
            response.setMessage("Error "+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("list")
    public ResponseEntity<?> listUser(){
        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping("list/{email}")
    public ResponseEntity<?> listUser(@PathVariable String email){
        try{
            UserModel userModel =userService.getUserByEmail(email);
            return ResponseEntity.ok(userModel);
        }catch (Exception e){
            UserResponse response = new UserResponse();
            response.setMessage("Error "+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable  Long userId){
        try{
            UserModel userModel =userService.deleteUser(userId);
            UserResponse response = new UserResponse();
            response.setMessage("Deleted successfully");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            UserResponse response = new UserResponse();
            response.setMessage("Error "+e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
