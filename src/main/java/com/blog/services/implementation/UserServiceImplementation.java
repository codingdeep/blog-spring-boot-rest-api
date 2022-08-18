package com.blog.services.implementation;

import com.blog.exceptions.ResourceExistException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.models.User;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepository;
import com.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) throws Exception {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.findByEmail(user.getEmail());
        if(savedUser != null){
            System.out.println("An user already exist with this email address!");
            throw new ResourceExistException("user","email", savedUser.getEmail());
        }else{
            savedUser = this.userRepository.save(user);
        }
        return this.modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());

        User updatedUser = this.userRepository.save(user);

        UserDto userDto1 = this.modelMapper.map(updatedUser,UserDto.class);
        //UserDto userDto1 = this.userToDto(updatedUser);
        return  userDto1;

    }

    @Override
    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        //UserDto userDto = this.userToDto(user);
        return userDto;
    }
    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepository.findAll();


//        List<UserDto> userDtos = new ArrayList<>();
//        for (User ur:users){
//            UserDto userDto = this.userToDto(ur);
//            userDtos.add(userDto);
//        }

        List<UserDto> userDtos = users.stream().map(ur->this.userToDto(ur)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        //UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
