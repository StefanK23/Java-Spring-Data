package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.Picture;

import softuni.exam.instagraphlite.models.dto.json.ImportPictureDTO;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper mapper;
    private final Validator validator;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;

        this.gson = new GsonBuilder().create();
        this.mapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        Path path = Path.of("src", "main", "resources", "files", "pictures.json");
        return String.join("\n", Files.readString(path));
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readFromFileContent();
        ImportPictureDTO[] pictureDTOS = this.gson.fromJson(json,ImportPictureDTO[].class);

        return Arrays.stream(pictureDTOS)
                .map(this::importPicture)
                .collect(Collectors.joining("\n"));
    }

    private String importPicture(ImportPictureDTO dto) {
        Set<ConstraintViolation<ImportPictureDTO>> errors = this.validator.validate(dto);

        if(!errors.isEmpty()){
            return "Invalid Picture";
        }

        Optional<Picture> optPicture = this.pictureRepository.findByPath(dto.getPath());
        if(optPicture.isPresent()){
            return "Invalid Picture";
        }

        Picture picture = this.mapper.map(dto,Picture.class);
        this.pictureRepository.save(picture);
        return "Successfully imported Picture, with size %.2f " + picture.getSize();
    }

    @Override
    public String exportPictures() {

        double size = 30000;

        List<Picture> pictures = pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(size);

        return pictures
                .stream().map(Picture::toString)
                .collect(Collectors.joining("\n"));

    }


}
