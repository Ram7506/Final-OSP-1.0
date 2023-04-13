package com.common.serviceImpl;

import com.common.entity.User;
import com.common.repository.UserRepository;
import com.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public String updateUser(User user, long userId) {

        User newUser = this.userRepository.findById(userId).get();

        if (newUser!=null && newUser.getId() == userId) {
            newUser.setUsername(user.getUsername());
            newUser.setAddress(user.getAddress());
            newUser.setEmail(user.getEmail());
            newUser.setPhoneNo(user.getPhoneNo());
            userRepository.save(newUser);
            return "User Updated....";
        }
        else
            return "Something went wrong...!!!";
    }

    @Override
    public User findbyId(Long userId) {
        return this.userRepository.findById(userId).get();
    }
}
