package dto;

import domain.post.Post;
import lombok.Data;

@Data
public class SimplePostDTO {
    private Long id;
    private String title;
    private int price;

    public SimplePostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.price = post.getPrice();
    }
}