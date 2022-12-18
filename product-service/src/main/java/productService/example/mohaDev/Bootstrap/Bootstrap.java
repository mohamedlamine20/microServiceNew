package productService.example.mohaDev.Bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productService.example.mohaDev.Module.Product;
import productService.example.mohaDev.Repository.ProductRepository;

@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private  final ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        productRepository.save( Product.builder().name("macBook").description("CPU:M1,RAM:16GB").price(200000.0).build());
    }
}
