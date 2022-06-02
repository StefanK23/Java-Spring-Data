package softuni.exam.instagraphlite.models.dto.json;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ImportPictureDTO {

    @NotNull
    private String path;

    @NotNull
    @Min(500)
    @Max(60000)
    private double size;

    public ImportPictureDTO(){}

    @NotBlank
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
