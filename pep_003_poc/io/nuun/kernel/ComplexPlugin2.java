package io.nuun.kernel;

import io.nuun.kernel.external.*;
import io.nuun.kernel.fluent.PluginBuilder;

import java.io.IOException;
import java.util.*;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public class ComplexPlugin2 extends PluginBuilder {
    public static final String PROPS = ".*\\.props";
    public static final MyInterfaceSpecification MY_INTERFACE_SPECIFICATION = new MyInterfaceSpecification();
    public static final MyImplementationSpecification MY_IMPLEMENTATION_SPECIFICATION = new MyImplementationSpecification();


    private final Map<Class<? extends Factory>, Set<Class<? extends Factory>>> factories = new HashMap<>();

    @Override
    protected void build() {
        plugin("complex-2")

                // Round 1

                .configure((config) -> config
                                .after(SomeTrait.class) // replaces requiredPlugins
                                .before(SomeOtherTrait.class) // replaces dependentPlugins
                                .scanClasses(MY_INTERFACE_SPECIFICATION) // can use spec-DSL to build it
                                .scanResources(PROPS)
                                .addPackageRoot("org.seedstack")
                        // can use spec-DSL to build it)
                )

                .init((initContext, moduleInstaller) -> {
                    
                    final Properties properties = new Properties();

                    initContext.trait(SomeTrait.class).doSomething();

                    initContext.resources(PROPS).forEach((url) -> {
                        try {
                            properties.load(url.openStream());
                        } catch (IOException e) {
                            throw new IllegalStateException("Unable to load URL " + url);
                        }
                    });

                    initContext.classes(MY_INTERFACE_SPECIFICATION).forEach((factoryInterface) -> factories.put(factoryInterface, new HashSet<>()));

                    moduleInstaller.install(new AbstractModule() {
                        @Override
                        protected void configure() {
                            bind(Properties.class).toInstance(properties);
                        }
                    });
                })

                // Round 2

                .configure((config) -> config.scanClasses(MY_IMPLEMENTATION_SPECIFICATION))

                .init((initContext, moduleInstaller) -> {

                    initContext.classes(MY_IMPLEMENTATION_SPECIFICATION).forEach((factoryImplementation) -> factories.get(findInterfaceForImpl(factoryImplementation)).add(factoryImplementation));

                    moduleInstaller.install(new PrivateModule() {
                        @Override
                        protected void configure() {
                            factories.forEach((factoryInterface, factoryImplementations)
                                    -> factoryImplementations.forEach((factoryImpl)
                                    -> bind(factoryInterface).to(factoryImpl)));
                        }
                    });
                });
    }

    private Class<? extends Factory> findInterfaceForImpl(Class<? extends Factory> implClass) {
        return null;
    }
}
