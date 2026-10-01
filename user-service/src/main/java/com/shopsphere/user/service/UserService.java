package com.shopsphere.user.service;

import com.shopsphere.user.dto.UserRequest;
import com.shopsphere.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest request);

    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
}