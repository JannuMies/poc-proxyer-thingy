package net.talviuni.proxyer;

public interface TranslationInterface<S, T> {

    public Class<S> getSourceClass();
    
    public Class<T> getTargetClass();
    
    public T translate(S objectToTranslate);
    
}
