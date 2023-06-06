package ru.netology.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void ShouldDelete() {
        ShopRepository repo = new ShopRepository();

        int sizeBeforeRemove;

        Product product1 = new Product(1, "Молоко", 50);
        Product product2 = new Product(23, "Хлеб", 40);
        Product product3 = new Product(345, "Картошка", 30);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        sizeBeforeRemove = repo.findAll().length;

        repo.removeById(23);

        Assertions.assertEquals(null, repo.findById(23));
        Assertions.assertEquals(sizeBeforeRemove - 1, repo.findAll().length);
    }

    @Test
    public void ShouldGetNotFoundID() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Молоко", 50);
        Product product2 = new Product(23, "Хлеб", 40);
        Product product3 = new Product(345, "Картошка", 30);

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(2);
        });
    }

    @Test
    public void ShouldAdd() {
        ShopRepository repo = new ShopRepository();

        int sizeBeforeAdd;

        Product product1 = new Product(1, "Молоко", 50);
        Product product2 = new Product(23, "Хлеб", 40);
        Product product3 = new Product(345, "Картошка", 30);

        repo.add(product1);
        repo.add(product2);

        sizeBeforeAdd = repo.findAll().length;

        repo.add(product3);

        Assertions.assertEquals(product3, repo.findById(345));
        Assertions.assertEquals(sizeBeforeAdd + 1, repo.findAll().length);
    }

    @Test
    public void ShouldGetAlreadyExists() {
        ShopRepository repo = new ShopRepository();
        Product product1 = new Product(1, "Молоко", 50);
        Product product2 = new Product(23, "Хлеб", 40);
        Product product3 = new Product(23, "Картошка", 30);

        repo.add(product1);
        repo.add(product2);

        

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product3);
        });
    }
}
