import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    Book book1 = new Book(1, "Мастер и Маргарита", 100, "Булгаков");
    Smartphone smartphone = new Smartphone(2, "Samsung", 200, "Samsung Group");
    Book book2 = new Book(3, "Маргарита", 300, "Гейман");

    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone);
    }

    @Test
    public void testSearching() {

        Product[] actual = manager.searchBy("Мастер");
        Product[] expected = {book1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchingMissing() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        manager.add(book1);
        manager.add(smartphone);
        manager.add(book2);
        Product[] actual = manager.searchBy("Кукушка");
        Product[] expected = {};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSeveral() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        manager.add(book1);
        manager.add(smartphone);
        manager.add(book2);
        Product[] actual = manager.searchBy("Маргарита");
        Product[] expected = {book1, book2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testAdding() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        manager.add(book1);
        manager.add(smartphone);
        manager.add(book2);
        Product[] expected = {book1, smartphone, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testAdd() {
        ProductRepository repo = new ProductRepository();
        repo.addProduct(book1);
        repo.addProduct(smartphone);
        repo.addProduct(book2);
        Product[] expected = {book1, smartphone, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testAddEmpty() {
        ProductRepository repo = new ProductRepository();

        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testRemoving() {
        ProductRepository repo = new ProductRepository();
        repo.addProduct(book1);
        repo.addProduct(smartphone);
        repo.addProduct(book2);
        repo.removeById(2);
        Product[] expected = {book1, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);

    }

}
