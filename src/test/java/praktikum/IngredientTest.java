package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f},
                {IngredientType.FILLING, "cutlet", 300.0f},
                {IngredientType.FILLING, "cheese", 150.5f},
                {IngredientType.SAUCE, "chili sauce", 75.25f},
                {IngredientType.FILLING, "salad", 50.0f}
        });
    }

    @Test
    public void testIngredientConstructorAndGetters() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals("Тип ингредиента должен соответствовать переданному в конструктор",
                type, ingredient.getType());
        assertEquals("Имя ингредиента должно соответствовать переданному в конструктор",
                name, ingredient.getName());
        assertEquals("Цена ингредиента должна соответствовать переданной в конструктор",
                price, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void testIngredientFieldsArePublic() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals("Поле type должно быть публичным", type, ingredient.type);
        assertEquals("Поле name должно быть публичным", name, ingredient.name);
        assertEquals("Поле price должно быть публичным", price, ingredient.price, 0.01f);
    }
}
