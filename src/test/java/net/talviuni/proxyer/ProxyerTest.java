package net.talviuni.proxyer;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import net.talviuni.proxyer.example.FoodShop;
import net.talviuni.proxyer.example.Pastry;
import net.talviuni.proxyer.example.Pound;
import net.talviuni.proxyer.example.StrudelShop;

public class ProxyerTest {

    @Test
    public void ensureProxyerReturnsFoodWithNameContainingStrudelDefault() throws Exception {
        Pound money = new Pound();
        money.setValue(1253.023);
        Proxyer proxyer = new Proxyer();
        
        FoodShop proxyedFoodStore = proxyer.getInstance(FoodShop.class);
        Pastry pastry = proxyedFoodStore.getFood(money);
        
        assertTrue(pastry.getName().contains(StrudelShop.PREAMBLE));
    }
}
