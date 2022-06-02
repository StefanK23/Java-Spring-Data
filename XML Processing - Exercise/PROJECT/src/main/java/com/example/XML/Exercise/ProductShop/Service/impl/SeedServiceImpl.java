package com.example.XML.Exercise.ProductShop.Service.impl;

import com.example.XML.Exercise.ProductShop.Repositories.CategoryRepository;
import com.example.XML.Exercise.ProductShop.Repositories.ProductRepository;
import com.example.XML.Exercise.ProductShop.Repositories.UserRepository;
import com.example.XML.Exercise.ProductShop.Service.SeedService;
import com.example.XML.Exercise.ProductShop.entities.Category.Category;
import com.example.XML.Exercise.ProductShop.entities.Category.CategoryImportDTO;
import com.example.XML.Exercise.ProductShop.entities.Products.Product;
import com.example.XML.Exercise.ProductShop.entities.Products.ProductsImportDTO;
import com.example.XML.Exercise.ProductShop.entities.Users.User;

import com.example.XML.Exercise.ProductShop.entities.Users.UsersImportDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class SeedServiceImpl implements SeedService {

    private static final Path USERS_XML_PATH =
            Path.of("src", "main", "resources", "productShop", "users.xml");

    private static final Path CATEGORIES_XML_PATH =
            Path.of("src", "main", "resources", "productShop", "categories.xml");

    private static final Path PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "productShop", "products.xml");


    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;

        this.modelMapper = new ModelMapper();

    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader xmlReader = Files.newBufferedReader(USERS_XML_PATH);
       UsersImportDTO usersDTOs = (UsersImportDTO)  unmarshaller.unmarshal(xmlReader);

       List<User> entities = usersDTOs.getUsers()
               .stream()
               .map(dto -> this.modelMapper.map(dto,User.class))
               .collect(Collectors.toList());

       this.userRepository.saveAll(entities);
    }

    @Override
    public void seedCategories() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader xmlReader = new FileReader(CATEGORIES_XML_PATH.toAbsolutePath().toString());
        CategoryImportDTO importDTO = (CategoryImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Category> entities = importDTO
                .getCategories()
                .stream()
                .map(catName -> new Category(catName.getName()))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(entities);

    }

    @Override
    public void seedProducts() throws FileNotFoundException, JAXBException {

        JAXBContext context= JAXBContext.newInstance(ProductsImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader xmlReader  = new FileReader(PRODUCTS_XML_PATH.toAbsolutePath().toString());
        ProductsImportDTO importDTO = (ProductsImportDTO) unmarshaller.unmarshal(xmlReader);

        List<Product> entities = importDTO.getProducts()
                .stream()
                .map(dto -> this.modelMapper.map(dto,Product.class))
                .map(this::setRandomCategories)
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .collect(Collectors.toList());

        this.productRepository.saveAll(entities);



    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomId = random.nextInt((int) categoriesDbCount) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(944)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();

        product.setBuyer(buyer.get());

        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();

        product.setSeller(seller.get());

        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count(); // 1..5

        // 0..4
        int randomUserId = new Random().nextInt((int) usersCount) + 1;

        Optional<User> seller = this.userRepository.findById(randomUserId);
        return seller;

    }
}
