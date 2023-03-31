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
package org.modelio.uml.sequencediagram.editor.elements.common.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.anchors.INodeAnchorProvider;
import org.modelio.diagram.elements.core.link.anchors.RectangleNodeAnchorProvider;
import org.modelio.diagram.elements.core.node.AbstractNodeEditPart;

/**
 * Common node edit parts in sequence diagrams
 * @author cmarin
 * @since 5.3.1
 */
@objid ("79e70df0-4168-48f4-b7e7-ae19708e0c17")
public abstract class AbstractSequenceNodeEditPart extends AbstractNodeEditPart {
    /**
     * Use slideable anchors in sequence diagrams
     */
    @objid ("be5b48cb-acc8-4b9c-8add-8a2659e1ea35")
    @Override
    protected INodeAnchorProvider createAnchorProvider() {
        return RectangleNodeAnchorProvider.getSlidable();
    }

}
