package net.talviuni.proxyer.example;

//public interface FoodShop {
//    public Pastry getFood(Pound money);
    

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
