package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeHasSauce() {
        IngredientType type = IngredientType.SAUCE;
        assertNotNull("Тип SAUCE должен существовать", type);
        assertEquals("Тип должен быть SAUCE", IngredientType.SAUCE, type);
    }

    @Test
    public void testIngredientTypeHasFilling() {
        IngredientType type = IngredientType.FILLING;
        assertNotNull("Тип FILLING должен существовать", type);
        assertEquals("Тип должен быть FILLING", IngredientType.FILLING, type);
    }

    @Test
    public void testIngredientTypeValues() {
        IngredientType[] types = IngredientType.values();
        assertEquals("Должно быть ровно 2 типа ингредиентов", 2, types.length);
    }

    @Test
    public void testIngredientTypeValueOf() {
        IngredientType sauce = IngredientType.valueOf("SAUCE");
        assertEquals("valueOf должен возвращать SAUCE", IngredientType.SAUCE, sauce);

        IngredientType filling = IngredientType.valueOf("FILLING");
        assertEquals("valueOf должен возвращать FILLING", IngredientType.FILLING, filling);
    }
}
