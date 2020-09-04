/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Stores in the model the location of a link extension.
 * 
 * @author cmarin
 */
@objid ("8008e6cd-1dec-11e2-8cad-001ec947c8cc")
public interface IGmLocator extends IPersistent {
    /**
     * Get the width constraint. -1 means no constraint.
     * @return the width constraint.
     */
    @objid ("a186336d-e6bb-4914-8d42-577939673207")
    int getWidthConstraint();

    /**
     * Get the height constraint. -1 means no constraint.
     * @return the height constraint.
     */
    @objid ("aa8ef67c-5b97-499e-9c97-a567ba9457f9")
    int getHeightConstraint();

    /**
     * Get the width constraint. -1 means no constraint.
     * @param val the width constraint.
     */
    @objid ("0a0f2b4b-c0e5-46df-9287-8f51a63f2a1f")
    void setWidthConstraint(int val);

    /**
     * Get the height constraint. -1 means no constraint.
     * @param val the height constraint.
     */
    @objid ("c0e1e5da-ee5d-4261-a2a4-e2918e4949b0")
    void setHeightConstraint(int val);

    /**
     * @return a copy of this object.
     */
    @objid ("2ff479b5-4f92-4827-a437-09bc7c8ddd1a")
    IGmLocator copy();

}
