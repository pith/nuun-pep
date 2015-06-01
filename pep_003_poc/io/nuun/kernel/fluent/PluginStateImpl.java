package io.nuun.kernel.fluent;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public class PluginStateImpl implements ConfigureState, InitializeState, StartState, StopState {

    @Override
    public ConfigureTransition configure(ConfigurationBuilder configurationBuilder) {
        return null;
    }

    @Override
    public InitializeTransition init(InitContext initContext) {
        return null;
    }

    @Override
    public StopState start() {
        return null;
    }

    @Override
    public void stop() {

    }
}
