/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.smkernel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Last access counter used by memory manager to flag and choose objects to unload.
 * @author cmarin
 */
@objid ("1c225f93-e0d5-4584-88a6-4e7086a77192")
public class AccessOrderer {
    /**
     * Property change fired when the counter overflows.
     */
    @objid ("1eedeca6-b6a1-47ca-a3db-d983d69f3de9")
    public static final String PROPERTY_COUNTER_OVERFLOW = "PROPERTY_COUNTER_OVERFLOW";

    @objid ("ec3354fa-8c6e-4a33-85be-c60ce01f02dc")
    private static int globalLastAccess = 0;

    @objid ("24b63c22-4af2-412c-ab9f-5dd843c7aec6")
    private static PropertyChangeSupport listeners = new PropertyChangeSupport(AccessOrderer.class);

    /**
     * Increments the last access counter and update the last access stamp on the given object
     * 
     * @param obj the accessed object
     */
    @objid ("9b991b14-39f5-4b1d-897c-c6b7110c34a2")
    public static void accessed(IAccessOrdered obj) {
        if (++globalLastAccess == Integer.MAX_VALUE) {
            listeners.firePropertyChange(PROPERTY_COUNTER_OVERFLOW, Integer.MAX_VALUE, 0);
        }
        
        obj.setLastAccess(globalLastAccess);
    }

    /**
     * Add a PropertyChangeListener to the listener list.
     * 
     * @param listener a listener
     */
    @objid ("c0afd8e7-3cc7-4315-a290-55f399491eae")
    public static void addListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered for all properties.
     * If listener was added more than once to the same event source,
     * it will be notified one less time after being removed.
     * If listener is null, or was never added, no exception is thrown and no action is taken.
     * 
     * @param listener the listener to remove.
     */
    @objid ("6a3fbf20-21be-4825-8ac1-d35f25d09078")
    public static void removeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }

    /**
     * @return the last access counter value.
     */
    @objid ("9cd7971a-d8e6-445a-8b6c-5680bdece889")
    public static int getLastAccess() {
        return globalLastAccess;
    }

}
