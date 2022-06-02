package softuni.exam.instagraphlite.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.Post;
import softuni.exam.instagraphlite.models.User;
import softuni.exam.instagraphlite.models.dto.xml.ImportPostDTO;
import softuni.exam.instagraphlite.models.dto.xml.ImportPostRootDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    Path path = Path.of("src", "main", "resources", "files","posts.xml");
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;

        this.mapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }


    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files","posts.xml");
        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportPostRootDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ImportPostRootDTO postRootDTOs = (ImportPostRootDTO) unmarshaller
                .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        return postRootDTOs.getPosts().stream()
                .map(this::importPost)
                .collect(Collectors.joining("\n"));
    }

    private String importPost(ImportPostDTO dto) {
        Set<ConstraintViolation<ImportPostDTO>> errors = this.validator.validate(dto);

        if(!errors.isEmpty()){
            return "Invalid Post";
        }

        Optional<Picture> picture = this.pictureRepository.findByPathEquals(dto.getPicture().getPath());
        Optional<User> user = this.userRepository.findByUsername(dto.getUser().getUsername());

        if(picture.isPresent() && user.isPresent()){
            Post post = this.mapper.map(dto, Post.class);

            post.setCaption(dto.getCaption());
            post.setUser(user.get());
            post.setPicture(picture.get());

            this.postRepository.save(post);
            return String.format("Successfully imported Post, made by %s", post.getUser().getUsername());
        }
        return "Invalid Post";
    }
}
