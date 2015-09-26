package net.talviuni.proxyer;

public interface TranslationInterface<S, T> {

    public Class<S> getSourceClass();
    
    public Class<T> getTargetClass();
    
    public <O extends Object> T translate(O objectToTranslate);
    
}
