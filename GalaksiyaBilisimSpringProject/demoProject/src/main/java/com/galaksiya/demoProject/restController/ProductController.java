package com.galaksiya.demoProject.restController;

import com.galaksiya.demoProject.business.IProductService;
import com.galaksiya.demoProject.dto.ProductSaveDto;
import com.galaksiya.demoProject.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "Yeni product kayıt alanı", description = "Sadece managerler kullanabilir.")
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductSaveDto productSaveDto){
        Product product=productSaveDto.createProductFromProductSaveDto();
        productService.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @Operation(summary = "Id'sine göre producta erişme alanı.")
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable int id){
        Product product= productService.getById(id);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    @GetMapping("/find")
    @Operation(summary = "Available product listesi.")
    public ResponseEntity<List<Product>> findAvailableProducts(@RequestParam(required = false) String category,
                                                        @RequestParam(required = false) Double minPrice,
                                                        @RequestParam(required = false) Double maxPrice){
        List<Product> productList= productService.getAvailableProducts(category, minPrice, maxPrice);
        return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Tüm productlar.")
    public ResponseEntity<List<Product>> findAllProduct(){
        List<Product> productList= productService.getAll();
        return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
    }

    @Operation(summary = "Product güncelleme alanı.", description = "Sadece managerler erişebilir.")
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        productService.update(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @Operation(summary = "Product silme alanı.", description = "Sadece managerler erişebilir.")
    @GetMapping("/remove/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable int id){
        productService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
