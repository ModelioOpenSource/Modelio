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

package org.modelio.diagram.elements.core.link;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.NodeEditPart;
import org.modelio.diagram.persistence.IPersistent;

/**
 * Defines the implementer is able to create a serializable anchor model from a figure anchor provided by
 * {@link #getSourceConnectionAnchor(org.eclipse.gef.Request)} or
 * {@link #getTargetConnectionAnchor(org.eclipse.gef.Request)}.
 * <p>
 * The returned model should be a primitive type or a {@link IPersistent}.
 */
@objid ("802a47e2-1dec-11e2-8cad-001ec947c8cc")
public interface IAnchorModelProvider extends NodeEditPart {
    /**
     * Create a serializable anchor model from the given anchor.
     * 
     * @param anchor a figure anchor
     * @return an anchor model.
     */
    @objid ("802a47e6-1dec-11e2-8cad-001ec947c8cc")
    Object createAnchorModel(final ConnectionAnchor anchor);

}
