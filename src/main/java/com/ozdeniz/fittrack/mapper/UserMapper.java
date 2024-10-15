package com.ozdeniz.fittrack.mapper;

import com.ozdeniz.fittrack.dto.UserDto;
import com.ozdeniz.fittrack.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {



}
