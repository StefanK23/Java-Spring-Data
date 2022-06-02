package softuni.exam.instagraphlite.service.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dto.json.ImportUserDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final PostRepository postRepository;

    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postRepository = postRepository;


        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {

        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "users.json");
        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importUsers() throws IOException {
        String json = readFromFileContent();
        ImportUserDTO[] UserDTOS = this.gson.fromJson(json, ImportUserDTO[].class);

        return Arrays.stream(UserDTOS)
                .map(this::importUser)
                .collect(Collectors.joining("\n"));
    }

    private String importUser(ImportUserDTO dto) {
        Set<ConstraintViolation<ImportUserDTO>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid User";
        }
        Optional<Picture> optPicture = this.pictureRepository.findByPathEquals(dto.getProfilePicture());
        if (optPicture.isPresent()) {
            User user = mapper.map(dto, User.class);

            userRepository.save(user);

            return "Successfully imported User: " + user.getUsername();
        }

        return "Invalid User";
    }

    @Override
    public String exportUsersWithTheirPosts() {
        List<User> users = userRepository.findAllOrderByPostCountAsc();


        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            List<Post> posts = postRepository.findAllByUserIdOrderByPictureSize(user.getId());
            sb.append("User: ")
                    .append(user.getUsername())
                    .append(System.lineSeparator())
                    .append("Posts count: ")
                    .append(posts.size())
                    .append(System.lineSeparator())
                    .append(posts.toString());



        }

        return sb.toString();
    }

}
