package com.example.XML.Exercise.ProductShop.Service.impl;

import com.example.XML.Exercise.ProductShop.Repositories.ProductRepository;
import com.example.XML.Exercise.ProductShop.Service.ProductsService;
import com.example.XML.Exercise.ProductShop.entities.Products.ExportProductsInRangeDTO;
import com.example.XML.Exercise.ProductShop.entities.Products.Product;
import com.example.XML.Exercise.ProductShop.entities.Products.ProductWithAttributesDTO;

import com.example.XML.Exercise.ProductShop.entities.Users.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepository productRepository;
    private final TypeMap<Product, ProductWithAttributesDTO> productToDtoMapping;

    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

        ModelMapper modelMapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDTO> typeMap =
                modelMapper.createTypeMap(Product.class, ProductWithAttributesDTO.class);

        this.productToDtoMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                        .map(Product::getSeller, ProductWithAttributesDTO::setSeller));
    }

    @Override
    public ExportProductsInRangeDTO getInRange(float from, float to) {
        BigDecimal rangeFrom = BigDecimal.valueOf(from);
        BigDecimal rangeTo = BigDecimal.valueOf(to);

       List<Product> products =  this.productRepository
               .findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(rangeFrom,rangeTo);

        List<ProductWithAttributesDTO> dtos =
                products.stream()
                .map(this.productToDtoMapping::map)
                .collect(Collectors.toList());

        return new ExportProductsInRangeDTO(dtos);
    }
}
