package com.example.meu_primeiro_projeto.service;


import com.example.meu_primeiro_projeto.dto.UserDTO;
import com.example.meu_primeiro_projeto.model.User;
import com.example.meu_primeiro_projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    public Optional<UserDTO> getUserById(Long id){
        return userRepository.findById(id).map(UserDTO::new);
    }

    public User addUser(User user){
         return userRepository.save(user);
    }

    public boolean deleteUser(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
