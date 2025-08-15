package com.fsse2506.fsse2506backend.mapper.user;

import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.domainObject.response.UserResponseData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {

    public FirebaseUserData toFirebaseUserData (Jwt jwt){
        FirebaseUserData firebaseUserData = new FirebaseUserData();
         firebaseUserData.setFirebaseUid(jwt.getClaimAsString("user_id"));
         firebaseUserData.setEmail(jwt.getClaimAsString("email"));
         return firebaseUserData;
    }

    public UserResponseData toUserResponseData (UserEntity userEntity){
        UserResponseData userResponseData = new UserResponseData();
        userResponseData.setEmail(userEntity.getEmail());
        userResponseData.setFirebaseUid(userEntity.getFirebaseUid());
        userResponseData.setUid(userEntity.getUid());
        return userResponseData;
    }

}
