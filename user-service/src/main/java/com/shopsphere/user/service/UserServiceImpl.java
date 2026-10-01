package com.shopsphere.user.service;

import com.shopsphere.user.dto.UserRequest;
import com.shopsphere.user.dto.UserResponse;
import com.shopsphere.user.entity.User;
import com.shopsphere.user.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserResponse create(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return toResponse(repository.save(user));
    }

    @Override
    public List<UserResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + id));

        return toResponse(user);
    }

    @Override
    public UserResponse update(
            Long id,
            UserRequest request) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return toResponse(repository.save(user));
    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}