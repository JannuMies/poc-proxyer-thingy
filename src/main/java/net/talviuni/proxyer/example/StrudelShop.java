package net.talviuni.proxyer.example;

public class StrudelShop {
    public Strudel getFood(Euro money) {
        Strudel strudel = new Strudel();
        strudel.setPrice(money.getValue());
        strudel.setName("Strudels for ");
        return strudel;
    }
}
