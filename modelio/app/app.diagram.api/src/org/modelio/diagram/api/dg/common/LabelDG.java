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
package org.modelio.diagram.api.dg.common;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * This class represents the DiagramGraphic of a 'Label' element.
 */
@objid ("c9fc784d-4268-4d0f-95cd-312c4cc03ecb")
public class LabelDG extends LeafNodeDG {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param node The gm node represented by this class.
     */
    @objid ("31d3f7a6-becb-4ed2-9a49-fbef1541faae")
    public  LabelDG(DiagramHandle diagramHandle, GmNodeModel node) {
        super(diagramHandle, node);
    }

    /**
     * For a floating label, we must set the proper size before moving to the location to avoid
     * port container resize issues.
     */
    @objid ("526a07f0-5fb6-42bd-8b5a-7e662406faa9")
    @Override
    public final void setBounds(Rectangle newBounds) {
        if (newBounds.height == -1 || newBounds.width == -1) {
            return;
        }
        
        setSize(newBounds.width, newBounds.height);
        setLocation(newBounds.x, newBounds.y);
        
    }

}
