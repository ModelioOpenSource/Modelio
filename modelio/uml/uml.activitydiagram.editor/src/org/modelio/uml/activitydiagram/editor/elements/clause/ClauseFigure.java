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
package org.modelio.uml.activitydiagram.editor.elements.clause;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.figures.GradientFigure;
import org.modelio.diagram.elements.core.figures.ToolbarLayoutWithGrab;

/**
 * Figure that represents a conditional node clause.
 * <p>
 * It is a gradient figure with a vertical toolbar layout that make the last child grab excess space.
 * 
 * @author cmarin
 */
@objid ("2a04574e-55b6-11e2-877f-002564c97630")
public class ClauseFigure extends GradientFigure {
    /**
     * Constructs the figure.
     */
    @objid ("2a045752-55b6-11e2-877f-002564c97630")
    public  ClauseFigure() {
        this.setOpaque(true);
        this.setLayoutManager(new ToolbarLayoutWithGrab());
        
    }

}
