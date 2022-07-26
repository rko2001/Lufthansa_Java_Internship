package com.example.backendservicecore.model.mapper;

import com.example.backendservicecore.model.UserData;
import com.example.backendservicecore.model.dto.UserDataDTO;

public class UserDataMapper implements Mapper<UserData, UserDataDTO>{
    private static UserDataMapper userDataMapper = null;

    private UserDataMapper() { }

    public static UserDataMapper getInstance() {
        if (userDataMapper == null) {
            userDataMapper = new UserDataMapper();
        }
        return userDataMapper;
    }

    @Override
    public UserData convertFromDTO(UserDataDTO accountDTO) {
        return UserData.builder()
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .username(accountDTO.getUsername())
                .password(accountDTO.getPassword())
                .build();
    }

    @Override
    public UserDataDTO convertToDTO(UserData account) {
        return UserDataDTO.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .username(account.getUsername())
                .password(account.getPassword())
                .build();
    }
}
