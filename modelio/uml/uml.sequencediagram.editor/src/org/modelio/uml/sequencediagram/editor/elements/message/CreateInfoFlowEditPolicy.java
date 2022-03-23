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
package org.modelio.uml.sequencediagram.editor.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.DefaultCreateInfoFlowOnLinkEditPolicy;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.GmInfoFlowsGroup;

/**
 * Allow creation of information flow on the link.
 * <p>
 * Defers the creation to the {@link GmInfoFlowsGroup}.
 * 
 * @author fpoyer
 */
@objid ("d94fd43a-55b6-11e2-877f-002564c97630")
public class CreateInfoFlowEditPolicy extends DefaultCreateInfoFlowOnLinkEditPolicy {
    /**
     * Redefined to return the {@link GmInfoFlowsGroup} for the nearest association role from the mouse.
     * @param gmLink The association model
     * @param location The mouse location
     * @return The nearest {@link GmInfoFlowsGroup} from the mouse.
     */
    @objid ("d94fd43e-55b6-11e2-877f-002564c97630")
    @Override
    protected GmCompositeNode getExtensionFor(final GmLink gmLink, final Point location) {
        for (GmNodeModel n : gmLink.getExtensions()) {
            if (n instanceof GmInfoFlowsGroup) {
                return (GmCompositeNode) n;
            }
        }
        return null;
    }

}
