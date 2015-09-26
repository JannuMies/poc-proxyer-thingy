# pox-proxyer-thingy
A quick "how is this done" thing to understand dynamic proxying.

I wanted to understand how to go from manually writing a wrapper to dynamically creating a wrapper from
an interface and a wrapped implementation. So instead of having this:

public class FoodShop {
  Pastry getFood(Pound money) {
      Euro euro = new Euro();
      euro.setValue(money.getValue() * 0.8);
      
      StrudelShop strudelShop = new StrudelShop();
      Strudel strudel = strudelShop.getFood(euro);
      
      Pastry pastry = new Pastry();
      pastry.setName(strudel.getName());
      pastry.setPrice(strudel.getPrice());
      return pastry;
  }
}
  
to this;

public interface FoodShop {
  Pastry getFood(Pound money);
}

used with:
  FoodShop proxyedFoodStore = proxyer.getInstance(FoodShop.class);
  Pastry pastry = proxyedFoodStore.getFood(new Pound(1.35));
