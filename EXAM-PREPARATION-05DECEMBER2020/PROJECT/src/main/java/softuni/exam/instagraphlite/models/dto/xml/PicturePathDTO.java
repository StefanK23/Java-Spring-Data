package softuni.exam.instagraphlite.models.dto.xml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PicturePathDTO {

    @NotNull
    private String path;

    public PicturePathDTO(){}

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
