package org.yearup.data.mysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlProductDaoTest extends BaseDaoTestClass
{
    private MySqlProductDao dao;

    @BeforeEach
    public void setup() {
        dao = new MySqlProductDao(dataSource);
    }

    @Test
    public void update_shouldModifyExistingProduct_andNotDuplicate() {
        // Arrange
        Product product = new Product();
        product.setName("Temp Product");
        product.setPrice(new BigDecimal("10.00"));
        product.setCategoryId(1);
        product.setDescription("Original");
        product.setColor("Black");
        product.setImageUrl("test.jpg");
        product.setStock(10);
        product.setFeatured(false);

        product = dao.create(product);
        int id = product.getProductId();

        // Act
        product.setDescription("Updated Description");
        dao.update(id, product);
        Product updated = dao.getById(id);

        // Assert
        assertEquals("Updated Description", updated.getDescription());
    }

    @Test
    public void search_shouldHandleNullParamsWithDefaults() {
        // Act
        List<Product> results = dao.search(null, null, null, null);

        // Assert
        assertNotNull(results);
        assertTrue(results.size() >= 1); // Should return everything
    }

    @Test
    public void search_shouldReturnCorrectResultsWithinPriceRange() {
        // Arrange
        BigDecimal minPrice = new BigDecimal("500");
        BigDecimal maxPrice = new BigDecimal("1000");

        // Act
        List<Product> results = dao.search(-1, minPrice, maxPrice, "");

        // Assert
        for (Product p : results) {
            BigDecimal price = p.getPrice();
            assertTrue(price.compareTo(minPrice) >= 0 && price.compareTo(maxPrice) <= 0);
        }
    }
}