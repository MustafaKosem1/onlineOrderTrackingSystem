package com.galaksiya.demoProject;

import com.galaksiya.demoProject.entity.Product;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class ProductTest {

    @Test
    public void testPricePositiveValue(){
        Product product = new Product("Kırmızı Kitap", "Kitap", "Açıklama", 1, 1, 5.5);

        assertThat(product).isNotNull();
        assertThat(product.getPrice()).isEqualTo(5.5);
    }
}
