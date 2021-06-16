package com.kapilsony.userservice.services.impl;

import com.kapilsony.userservice.dto.UserRequest;
import com.kapilsony.userservice.dto.UserResponse;
import com.kapilsony.userservice.entities.UserEntity;
import com.kapilsony.userservice.exceptions.UserNotFoundException;
import com.kapilsony.userservice.mappers.UserMapper;
import com.kapilsony.userservice.repositories.UserRepository;
import com.kapilsony.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(UserRequest userRequest){
        userRepository.save(userMapper.toEntity(userRequest));
    }

    @Override
    public void update(Long id, UserRequest userRequest){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User Not exist by id"));
        userEntity = userMapper.updateUserFromDTO(userEntity, userRequest);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserResponse> findAll(){
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long userId){
        return userRepository.findById(userId).map(userMapper::toDTO)
                .orElseThrow(()->new UserNotFoundException("User Not exist by id"));
    }

    @Override
    public void delete(Long userId){
        userRepository.deleteById(userId);
    }


}
