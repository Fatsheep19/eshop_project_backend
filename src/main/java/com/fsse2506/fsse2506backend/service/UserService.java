package com.fsse2506.fsse2506backend.service;

import com.fsse2506.fsse2506backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2506.fsse2506backend.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByFirebaseUserData(FirebaseUserData firebaseUserData);
}
