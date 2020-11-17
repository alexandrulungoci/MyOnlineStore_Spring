package com.sda.onlinestore.service;

import com.sda.onlinestore.dto.UserAccountDto;

import java.util.List;

public interface UserAccountService {

    UserAccountDto getAccount(Long id);

    List<UserAccountDto> getAllUsers();

    void addUser(UserAccountDto userAccountDto);

    void updateUser(UserAccountDto userAccountDto);

    void deleteUser(Long id);
}
