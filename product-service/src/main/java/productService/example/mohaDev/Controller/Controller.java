package productService.example.mohaDev.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import productService.example.mohaDev.DTO.ProductDto;
import productService.example.mohaDev.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class Controller {
     private final ProductService  productService;
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> findAll()
    {
     return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }



}
