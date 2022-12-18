package productService.example.mohaDev.Module;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Product {
    @Id
    private  String id;
    private String name;
    private String description;

    private Double price;


}
