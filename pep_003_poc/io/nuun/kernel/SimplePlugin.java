package io.nuun.kernel;

import io.nuun.kernel.fluent.AbstractFluentPlugin;
import io.nuun.kernel.external.MyInterfaceSpecification;
import io.nuun.kernel.external.SomeTrait;
import io.nuun.kernel.fluent.ConfigurationBuilder;
import io.nuun.kernel.external.MyImplementationSpecification;

public class SimplePlugin extends AbstractFluentPlugin {
    @Override
    public void configure(ConfigurationBuilder configurationBuilder) {
        configurationBuilder.after(SomeTrait.class).bindClasses(new MyInterfaceSpecification(), new MyImplementationSpecification());
    }
}
