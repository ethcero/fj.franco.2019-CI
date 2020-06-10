
package es.codeurjc.daw.dto;

/**
 * @author fran
 */
public class NewPostDTO {


    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String content;

    public NewPostDTO() {
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
