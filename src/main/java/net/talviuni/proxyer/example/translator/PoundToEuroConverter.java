package net.talviuni.proxyer.example.translator;

import net.talviuni.proxyer.TranslationInterface;
import net.talviuni.proxyer.example.Euro;
import net.talviuni.proxyer.example.Pound;

public class PoundToEuroConverter implements TranslationInterface<Pound, Euro> {

    public Class<Pound> getSourceClass() {
        return Pound.class;
    }

    public Class<Euro> getTargetClass() {
        return Euro.class;
    }

    public <O> Euro translate(O objectToTranslate) {
        Pound poundtoTranslate = (Pound) objectToTranslate;
        Euro euro = new Euro();
        euro.setValue(poundtoTranslate.getValue() * 0.8);
        return euro;
    }

}
