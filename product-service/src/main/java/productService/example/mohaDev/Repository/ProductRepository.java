package productService.example.mohaDev.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import productService.example.mohaDev.Module.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product ,Long> {
}
