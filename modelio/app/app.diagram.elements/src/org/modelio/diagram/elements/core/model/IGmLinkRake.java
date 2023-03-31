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
    @objid ("98f1b725-b146-44d4-baef-b89d0b8015ff")
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
    @objid ("5a6d87cd-4659-496a-a89c-a87777feac72")
    void addListener(PropertyChangeListener listener);

    /**
     * Remove a listener registered with {@link #addListener(PropertyChangeListener)}.
     * @param listener a listener to remove
     */
    @objid ("72002c35-0cda-47d9-bf45-638667c53c90")
    void removeListener(PropertyChangeListener listener);

    /**
     * Set the shared anchor.
     * <p>
     * Fires a {@link #PROP} property change event to all listeners registered with {@link #addListener(PropertyChangeListener)}.
     * @param anchor the shared anchor.
     */
    @objid ("4701dad8-74f4-4e6b-a78e-cdd52621e03c")
    void setSharedAnchor(final Object anchor);
}

