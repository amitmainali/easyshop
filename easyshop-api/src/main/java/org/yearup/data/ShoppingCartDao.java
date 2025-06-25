package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    // Get the full shopping cart for a user
    ShoppingCart getByUserId(int userId);

    // Add a product to the cart (insert or increment quantity)
    void addProduct(int userId, int productId);

    // Update the quantity for a specific product in the cart
    void updateQuantity(int userId, int productId, int quantity);

    // Remove all products from the cart
    void clearCart(int userId);
}