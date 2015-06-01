package io.nuun.kernel.fluent;

/**
 * @author Pierre Thirouin
 *         Date: 01/06/15
 */
public interface InitBuilder {

    public void build(InitContext initState, ModuleInstaller moduleInstaller);
}
