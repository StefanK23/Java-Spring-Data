package com.example.XML.Exercise.ProductShop.Service.impl;

import com.example.XML.Exercise.ProductShop.Repositories.UserRepository;
import com.example.XML.Exercise.ProductShop.Service.UserService;
import com.example.XML.Exercise.ProductShop.entities.Users.ExportSellersDTO;
import com.example.XML.Exercise.ProductShop.entities.Users.ExportUserWithSoldProductDTO;
import com.example.XML.Exercise.ProductShop.entities.Users.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportSellersDTO findUserWithSoldProducts() {
        List<User> users = this.userRepository.findUserWithSoldProducts();

        List<ExportUserWithSoldProductDTO> dtos =
                users.stream()
                .map(u -> this.mapper.map(u, ExportUserWithSoldProductDTO.class))
                .collect(Collectors.toList());
            return new ExportSellersDTO(dtos) ;
    }
}
