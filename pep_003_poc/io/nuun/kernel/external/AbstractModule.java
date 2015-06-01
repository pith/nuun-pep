package io.nuun.kernel.external;

public abstract class AbstractModule {
    protected BindToClass bind(final Class<?> aClass) {
        return new BindToClass();
    }

    protected abstract void configure();

    protected static class BindToClass {
        public void toInstance(Object object) {}
    }
}
