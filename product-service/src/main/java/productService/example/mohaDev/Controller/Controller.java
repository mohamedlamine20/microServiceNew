package productService.example.mohaDev.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDto>> findAll(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "100") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }



}
