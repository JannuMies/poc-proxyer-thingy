package net.talviuni.proxyer.example;

public class StrudelShop {
    public static final String PREAMBLE = "Strudel for ";

    public Strudel getFood(Euro money) {
        Strudel strudel = new Strudel();
        strudel.setPrice(money.getValue());
        strudel.setName(PREAMBLE + money);
        return strudel;
    }
}
