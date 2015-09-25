package net.talviuni.proxyer.example.translator;

import net.talviuni.proxyer.TranslationInterface;
import net.talviuni.proxyer.example.Pastry;
import net.talviuni.proxyer.example.Strudel;

public class StrudelToPastryConverter implements TranslationInterface<Strudel, Pastry>{

    public Class<Strudel> getSourceClass() {
        return Strudel.class;
    }

    public Class<Pastry> getTargetClass() {
        return Pastry.class;
    }

    public Pastry translate(Strudel objectToTranslate) {
      Pastry pastry = new Pastry();
      pastry.setName(objectToTranslate.getName());
      pastry.setPrice(objectToTranslate.getPrice());
      return pastry;
    }

}
