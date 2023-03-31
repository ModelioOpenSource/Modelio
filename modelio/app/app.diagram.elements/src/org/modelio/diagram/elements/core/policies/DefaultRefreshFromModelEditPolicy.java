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
package org.modelio.diagram.elements.core.policies;

import java.beans.PropertyChangeEvent;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.IGmModelRelated;

/**
 * @since 3.7, remade in 5.1.0 to handle connections layout
 */
@objid ("9c6f2892-4483-41bb-bffc-febe65bb8aae")
public class DefaultRefreshFromModelEditPolicy extends AbstractRefreshFromModelEditPolicy {
    /**
     * Get a command to run to refresh the graphic model from the object model.
     * <p>
     * Called when a {@link IGmModelRelated#PROP_REFRESH_FROM_OBMODEL}  property change event is caught.
     * Does nothing by default.
     * @param ev the property change event, in case it contains useful informations
     * @return a command or null.
     */
    @objid ("e7786089-a34e-4af8-b6c6-1be03abf21b2")
    @Override
    protected Command getRefreshFromModelCommand(PropertyChangeEvent ev) {
        return null;
    }

}
