package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.0f},
                {"red bun", 300.0f},
                {"sesame bun", 150.5f}
        });
    }

    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun(name, price);

        assertEquals("Имя булочки должно соответствовать переданному в конструктор",
                name, bun.getName());
        assertEquals("Цена булочки должна соответствовать переданной в конструктор",
                price, bun.getPrice(), 0.01f);
    }

    @Test
    public void testBunFieldsArePublic() {
        Bun bun = new Bun(name, price);

        assertEquals("Поле name должно быть публичным", name, bun.name);
        assertEquals("Поле price должно быть публичным", price, bun.price, 0.01f);
    }
}
