package io.nuun.kernel;

import io.nuun.kernel.external.MyInterfaceSpecification;
import io.nuun.kernel.external.PrivateModule;
import io.nuun.kernel.fluent.PluginBuilder;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public class SimplePlugin2 extends PluginBuilder {

    public static final MyInterfaceSpecification INTERFACE_SPEC = new MyInterfaceSpecification();

    @Override
    protected void build() {
        plugin("simple-2")

                .configure((config) -> config.scanClasses(INTERFACE_SPEC))

                .init((init, installer) -> installer.install(new PrivateModule() {
                    @Override
                    protected void configure() {
                        init.classes(INTERFACE_SPEC).forEach((c) -> bind(c));
                    }
                }));
    }
}
