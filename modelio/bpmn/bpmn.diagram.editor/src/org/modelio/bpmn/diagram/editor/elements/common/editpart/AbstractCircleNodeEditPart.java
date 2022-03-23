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
package org.modelio.bpmn.diagram.editor.elements.common.editpart;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.core.link.anchors.fixed.AbstractFixedNodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.fixed.EllipseFixedNodeAnchorProvider;

/**
 * Base edit part for circle BPMN nodes for which we want only 4 anchors for cardinal directions,
 * or an {@link EllipseAnchor} for oblique connections.
 */
@objid ("2cfda7a2-eae6-4999-b6f8-6b2e41206f19")
public class AbstractCircleNodeEditPart extends AbstractBpmnSmallNodeEditPart {
    @objid ("40b1d24c-7670-4e6a-9481-4c04a8ae194c")
    @Override
    protected AbstractFixedNodeAnchorProvider createAnchorProvider(IFigure figure) {
        return new EllipseFixedNodeAnchorProvider();
    }

}
