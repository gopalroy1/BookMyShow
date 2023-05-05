package com.example.Book_My_show.Converters;

import com.example.Book_My_show.DTO.EntryDTO.UserEntryDTO;
import com.example.Book_My_show.Entity.UserEntity;

public class UserConverter {
    //// Making converters as static so that we don't have to make it's object to call it
    public static UserEntity convertToUserEntity(UserEntryDTO userEntryDTO){
        UserEntity userEntity = UserEntity.builder().age(userEntryDTO.getAge()).name(userEntryDTO.getName()).
                email(userEntryDTO.getEmail()).mobNo(userEntryDTO.getMobNo()).address(userEntryDTO.getAddress())
                .password(userEntryDTO.getPassword())
                .build();
        return userEntity;
    }
}
