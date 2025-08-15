package com.fsse2506.fsse2506backend.service.impl;

import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import com.fsse2506.fsse2506backend.mapper.user.UserEntityMapper;
import com.fsse2506.fsse2506backend.repository.UserRepository;
import com.fsse2506.fsse2506backend.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;
     private final UserEntityMapper userEntityMapper;

     public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
          this.userRepository = userRepository;
          this.userEntityMapper = userEntityMapper;
     }

     @Override
     public UserEntity getUserEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
         return userRepository.findByEmail(firebaseUserData.getEmail()).
                 orElseGet(
                         () -> userRepository.save(
                                 userEntityMapper.toUserEntity(firebaseUserData)
                         )
                 );
     }
}
