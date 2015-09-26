package net.talviuni.proxyer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.talviuni.proxyer.example.FoodShop;
import net.talviuni.proxyer.example.StrudelShop;
import net.talviuni.proxyer.example.translator.PoundToEuroConverter;
import net.talviuni.proxyer.example.translator.StrudelToPastryConverter;

public class Proxyer {
    private final Map<Class<?>, TranslationInterface> translators;
    private final Map<Class<?>, Class<?>> wrappers;
    
    public Proxyer() {
        translators = new HashMap<Class<?>, TranslationInterface>();
        PoundToEuroConverter poundToEuroConverter = new PoundToEuroConverter();
        StrudelToPastryConverter strudelToPastryConverter = new StrudelToPastryConverter();
        translators.put(strudelToPastryConverter.getSourceClass(), strudelToPastryConverter);
        translators.put(poundToEuroConverter.getSourceClass(), poundToEuroConverter);
        
        wrappers = new HashMap<Class<?>, Class<?>>();
        wrappers.put(FoodShop.class, StrudelShop.class);
    }
    
    public <T> T getInstance(final Class<T> sourceClass) throws Exception {
        Class<?> targetClass = wrappers.get(sourceClass);
        final Object targetInstance = targetClass.newInstance();
        
        InvocationHandler invocationHandler = new InvocationHandler() {
            
            public Object invoke(Object proxy, Method sourceMethod, Object[] args) throws Throwable {
                Method targetMethod = getMatchingMethod(sourceMethod, targetInstance);
                Class<?>[] sourceParametersTypes = sourceMethod.getParameterTypes();
                Class<?>[] targetParameterTypes = targetMethod.getParameterTypes();
                
                List<Object> translatedParameters = new ArrayList<Object>();
                for(int i=0; i<sourceMethod.getParameterTypes().length; i++) {
                    TranslationInterface<?, ?> translator = getTranslator(sourceParametersTypes[i], targetParameterTypes[i]);
                    Object translatedParameter = translator.translate(args[i]);
                    translatedParameters.add(translatedParameter);
                  }
                
                Object returnValue = targetMethod.invoke(targetInstance, translatedParameters.toArray());
                
                TranslationInterface<?, ?> resultTranslator = getTranslator(targetMethod.getReturnType(), sourceMethod.getReturnType());
                
                
                return resultTranslator.translate(returnValue);
            }
            
            private TranslationInterface<?, ?> getTranslator(Class<?> sourceClass, Class<?> targetClass) {
                return translators.get(sourceClass);
            }

            private Method getMatchingMethod(Method wantedMethod, Object targetInstance) throws Exception {
                Method[] methods = targetInstance.getClass().getMethods();
                for(Method possibleMethod : methods) {
                    if(possibleMethod.getName().contains("getFood")) {
                        return possibleMethod;
                    }
                }
                throw new Exception("No method found for name: " + wantedMethod.toGenericString());
            }
        };
        
        Class<?> proxyClass = Proxy.getProxyClass(this.getClass().getClassLoader(), sourceClass);
        return (T) proxyClass.getConstructor(new Class[]{InvocationHandler.class}).newInstance(invocationHandler);
    }

}
