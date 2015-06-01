package io.nuun.kernel.fluent;

import io.nuun.kernel.external.Specification;

public interface ConfigurationBuilder {
    ConfigurationBuilder after(Class<?> theClass);

    ConfigurationBuilder before(Class<?> theClass);

    ConfigurationBuilder scanClasses(Specification<?>... specification);

    ConfigurationBuilder scanResources(String s);

    ConfigurationBuilder bindClasses(Specification<?>... specification);

    ConfigurationBuilder addPackageRoot(String s);

    ConfigurationBuilder nextRound();
}
