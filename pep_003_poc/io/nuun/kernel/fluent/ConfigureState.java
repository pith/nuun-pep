package io.nuun.kernel.fluent;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public interface ConfigureState {

    ConfigureTransition configure(ConfigBuilder configurationBuilder);
}
