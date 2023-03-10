package productService.example.mohaDev.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import productService.example.mohaDev.DTO.ProductDto;
import productService.example.mohaDev.Mapper.ProductMapper;
import productService.example.mohaDev.Module.Product;
import productService.example.mohaDev.Repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto){
        log.info("get All product");
         return productMapper.to(productRepository.save(productMapper.from(productDto)));
     }

    public  List<ProductDto> findAll(){
        log.info("get All product");
         return productRepository.findAll().stream().map(product -> productMapper.to(product)).toList();
     }

    public Page<ProductDto> findAll(Pageable pageable){
        log.info("get All product");
        Page<Product> productDtos= productRepository.findAll(pageable);
        return new PageImpl<>(productDtos.getContent().stream().map(product -> productMapper.to(product)).toList(),pageable,productDtos.getTotalElements());
    }

}
