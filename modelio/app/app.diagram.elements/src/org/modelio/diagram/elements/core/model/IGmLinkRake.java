/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.diagram.elements.core.model;

import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents a link rake.
 * <p>
 * All link raked together share the same link rake and must share the same anchor model.
 * 
 * @author cmarin
 */
@objid ("80801cb2-1dec-11e2-8cad-001ec947c8cc")
public interface IGmLinkRake {
    /**
     * The property name used to fire change events.
     */
    @objid ("675f58a8-ea22-4819-a0bc-c16cda580c5d")
    public static final String PROP = "rake anchor changed";

    /**
     * Get the node anchor that must be used by all link raked together.
     * @return the shared extremity anchor.
     */
    @objid ("80801cb4-1dec-11e2-8cad-001ec947c8cc")
    Object getSharedAnchor();

    /**
     * Register a listener fired with {@link #PROP} property when the shared anchor changes.
     * @param listener a property change listener
     */
    @objid ("0553b900-7f71-4913-a9d9-f466dc080f2e")
    void addListener(PropertyChangeListener listener);

    /**
     * Remove a listener registered with {@link #addListener(PropertyChangeListener)}.
     * @param listener a listener to remove
     */
    @objid ("12c556b6-0f08-48b5-b299-81c9d3ff71c8")
    void removeListener(PropertyChangeListener listener);

    /**
     * Set the shared anchor.
     * <p>
     * Fires a {@link #PROP} property change event to all listeners registered with {@link #addListener(PropertyChangeListener)}.
     * @param anchor the shared anchor.
     */
    @objid ("74443359-3b09-497a-b8da-b9f6cf83d71c")
    void setSharedAnchor(final Object anchor);

}
