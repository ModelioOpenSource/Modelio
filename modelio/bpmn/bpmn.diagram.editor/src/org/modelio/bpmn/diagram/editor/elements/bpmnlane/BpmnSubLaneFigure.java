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
package org.modelio.bpmn.diagram.editor.elements.bpmnlane;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Dedicated figure for Partition. Based on a GradientFigure, with an added LineBorder.
 */
@objid ("6117766c-55b6-11e2-877f-002564c97630")
class BpmnSubLaneFigure extends BpmnLaneFigure {
    @objid ("6117766e-55b6-11e2-877f-002564c97630")
    @Override
    public Rectangle getHandleBounds() {
        if (!getChildren().isEmpty()) {
            return ((IFigure) getChildren().get(0)).getBounds().getCopy();
        }
        return super.getHandleBounds();
    }

}
