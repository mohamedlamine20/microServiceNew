package productService.example.mohaDev.Mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import productService.example.mohaDev.DTO.ProductDto;
import productService.example.mohaDev.Module.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product from (ProductDto productDto);

    @InheritInverseConfiguration
    ProductDto to(Product  product);
}
