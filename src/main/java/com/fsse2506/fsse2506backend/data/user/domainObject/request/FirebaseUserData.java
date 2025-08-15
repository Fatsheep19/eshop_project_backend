package com.fsse2506.fsse2506backend.data.user.domainObject.request;

public class FirebaseUserData {
    private String firebaseUid;
    private String email;

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
