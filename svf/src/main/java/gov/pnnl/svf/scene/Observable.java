package gov.pnnl.svf.scene;

import java.beans.PropertyChangeSupport;

/**
 * Interface for classes that use the observer pattern.
 *
 * @author Arthur Bleeker
 */
public interface Observable {

    /**
     * @return the propertyChangeSupport
     */
    PropertyChangeSupport getPropertyChangeSupport();
}
