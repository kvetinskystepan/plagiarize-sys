package com.thenarbox.psys.abstraction;

/**
 * Represents controllable class by means of initialization and termination.
 *
 * @author Thenarbox
 */
public interface Controllable {

    /**
     * Initializes state.
     */
    void initialize();

    /**
     * Terminates state.
     */
    void terminate();

}
