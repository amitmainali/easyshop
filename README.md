# EasyShop E-Commerce API

This project is a Spring Boot-based REST API for an e-commerce platform. It supports managing categories, products, shopping carts, and user operations with secure authentication and role-based access.

## Features

- Browse and search for products
- View products by category
- Admin controls for managing categories and products
- Shopping cart functionality
- JWT-based user authentication and authorization

## Interesting Code Snippet

One of the more interesting implementations is the logic for updating a product in the shopping cart without duplicating entries. It ensures that updates are safe and accurate without inflating the cart.

**File:** `ShoppingCartController.java`

```java
@PutMapping("/products/{productId}")
public void updateProduct(@PathVariable int productId,
                          @RequestBody ShoppingCartItem item,
                          Principal principal)
{
    try
    {
        int userId = getUserId(principal);
        shoppingCartDao.updateQuantity(userId, productId, item.getQuantity());
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not update product quantity.");
    }
}
```

## Technologies Used

- Java 17
- Spring Boot
- MySQL
- JUnit 5 (for testing)

## Setup Instructions

1. Create a MySQL database called `easyshop`.
2. Run the schema and seed SQL script.
3. Configure `application.properties` with your database credentials.
4. Start the Spring Boot application.

## Running Tests

Ensure the test database is properly configured in your environment properties. Run tests using JUnit support in your IDE or CLI.

## Future Versions

- Add support for product reviews and ratings
- Enable order checkout and payment integration
- Implement user profile updates and order history
- Integrate admin dashboard UI

## Author

Amit Mainali
