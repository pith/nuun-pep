package io.nuun.kernel.fluent;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public abstract class PluginBuilder {

    protected abstract void build();

    protected final ConfigureState plugin(String name) {
        return new ConfigureState() {
            @Override
            public ConfigureTransition configure(ConfigBuilder configurationBuilder) {
                return null;
            }
        };
    }

}
