package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals("Количество ингредиентов должно быть 1", 1, burger.ingredients.size());
        assertTrue("Ингредиент должен быть в списке", burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        assertEquals("Количество ингредиентов должно быть 3", 3, burger.ingredients.size());
        assertEquals("Первый ингредиент должен быть mockIngredient1",
                mockIngredient1, burger.ingredients.get(0));
        assertEquals("Второй ингредиент должен быть mockIngredient2",
                mockIngredient2, burger.ingredients.get(1));
        assertEquals("Третий ингредиент должен быть mockIngredient3",
                mockIngredient3, burger.ingredients.get(2));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1);

        assertEquals("Количество ингредиентов должно быть 2", 2, burger.ingredients.size());
        assertEquals("Первый ингредиент должен остаться",
                mockIngredient1, burger.ingredients.get(0));
        assertEquals("Третий ингредиент должен стать вторым",
                mockIngredient3, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals("Количество ингредиентов должно быть 1", 1, burger.ingredients.size());
        assertEquals("Второй ингредиент должен стать первым",
                mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        when(mockBun.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);

        float expectedPrice = 50.0f * 2;
        assertEquals("Цена должна быть равна удвоенной цене булочки",
                expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetPriceWithIngredients() {
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getPrice()).thenReturn(100.0f);
        when(mockIngredient2.getPrice()).thenReturn(150.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 50.0f * 2 + 100.0f + 150.0f;
        assertEquals("Цена должна включать булочки и ингредиенты",
                expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(mockBun.getPrice()).thenReturn(75.5f);
        when(mockIngredient1.getPrice()).thenReturn(25.0f);
        when(mockIngredient2.getPrice()).thenReturn(30.0f);
        when(mockIngredient3.getPrice()).thenReturn(45.5f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        float expectedPrice = 75.5f * 2 + 25.0f + 30.0f + 45.5f;
        assertEquals("Цена должна правильно суммироваться",
                expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceiptWithoutIngredients() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булочки",
                receipt.contains("(==== white bun ====)"));
        assertTrue("Чек должен содержать цену",
                receipt.contains("Price: 200.0"));
    }

    @Test
    public void testGetReceiptWithIngredients() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(25.0f);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булочки",
                receipt.contains("(==== black bun ====)"));
        assertTrue("Чек должен содержать соус",
                receipt.contains("= sauce hot sauce ="));
        assertTrue("Чек должен содержать начинку",
                receipt.contains("= filling cutlet ="));
        assertTrue("Чек должен содержать правильную цену",
                receipt.contains("Price: 225.0"));
    }

    @Test
    public void testGetReceiptFormat() {
        when(mockBun.getName()).thenReturn("sesame bun");
        when(mockBun.getPrice()).thenReturn(60.0f);
        when(mockIngredient1.getName()).thenReturn("cheese");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(40.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        int bunCount = receipt.split("sesame bun", -1).length - 1;
        assertEquals("Название булочки должно встречаться дважды в чеке", 2, bunCount);

        assertTrue("Чек должен содержать Price:", receipt.contains("Price:"));
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(mockBun.getName()).thenReturn("red bun");
        when(mockBun.getPrice()).thenReturn(80.0f);
        when(mockIngredient1.getName()).thenReturn("sour cream");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(20.0f);
        when(mockIngredient2.getName()).thenReturn("salad");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(30.0f);
        when(mockIngredient3.getName()).thenReturn("chili sauce");
        when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient3.getPrice()).thenReturn(15.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать все ингредиенты",
                receipt.contains("sour cream") &&
                        receipt.contains("salad") &&
                        receipt.contains("chili sauce"));
        assertTrue("Чек должен содержать правильную общую цену",
                receipt.contains("Price: 225.0"));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertEquals("Количество ингредиентов должно остаться 3", 3, burger.ingredients.size());
        assertEquals("Второй ингредиент должен стать первым",
                mockIngredient2, burger.ingredients.get(0));
        assertEquals("Третий ингредиент должен стать вторым",
                mockIngredient3, burger.ingredients.get(1));
        assertEquals("Первый ингредиент должен стать третьим",
                mockIngredient1, burger.ingredients.get(2));
    }
}
