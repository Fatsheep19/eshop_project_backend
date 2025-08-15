package com.fsse2506.fsse2506backend.controller.product;

import com.fsse2506.fsse2506backend.data.product.dto.GetAllProductResponsiveDto;
import com.fsse2506.fsse2506backend.data.product.dto.ProductResponseDto;
import com.fsse2506.fsse2506backend.mapper.product.ProductDtoMapper;
import com.fsse2506.fsse2506backend.repository.ProductRepository;
import com.fsse2506.fsse2506backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/products")
@CrossOrigin("http://localhost:5173")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductRepository productRepository, ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping
    public List<GetAllProductResponsiveDto> getAllProducts (){
    return productDtoMapper.toAllProductList(productService.getAllProduct());
    }

    @GetMapping ("{pid}")
    public ProductResponseDto getProductByPid (@PathVariable ("pid") Integer pId){
            return productDtoMapper.toProductDto(productService.getProductResponsiveData(pId));
    }

}
