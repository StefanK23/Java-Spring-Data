package softuni.exam.instagraphlite.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPostDTO {

    @NotNull
    @Size(min = 21)
    private String caption;

    @NotNull
    private UserNameDTO user;

    @NotNull
    private PicturePathDTO picture;

    public ImportPostDTO(){}

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserNameDTO getUser() {
        return user;
    }

    public void setUser(UserNameDTO user) {
        this.user = user;
    }

    public PicturePathDTO getPicture() {
        return picture;
    }

    public void setPicture(PicturePathDTO picture) {
        this.picture = picture;
    }
}
