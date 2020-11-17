package com.sda.onlinestore.controller;

import com.sda.onlinestore.dto.UserAccountDto;
import com.sda.onlinestore.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("getUsersAccounts")
    public ResponseEntity<List<UserAccountDto>> getUsersAccounts() {
        List<UserAccountDto> userAccountDtoList = userAccountService.getAllUsers();
        return new ResponseEntity(userAccountDtoList, HttpStatus.OK);
    }

    @DeleteMapping("deleteUserAccount/{id}")
    public ResponseEntity deleteUserAccount(@PathVariable("id") Long id) {
        userAccountService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("addUserAccount")
    public ResponseEntity addUserAccount(@RequestBody UserAccountDto userAccountDto) {
        userAccountService.addUser(userAccountDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getUserAccount/{id}")
    public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable("id") Long id) {
        UserAccountDto userAccountDto = userAccountService.getAccount(id);
        return new ResponseEntity(userAccountDto, HttpStatus.OK);
    }

    @PutMapping("editUserAccount")
    public ResponseEntity editUserAccount(@RequestBody UserAccountDto userAccountDto) {
        userAccountService.updateUser(userAccountDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
