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

package org.modelio.uml.ui.dg.state;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;
import org.modelio.uml.statediagram.editor.elements.transition.GmTransition;

/**
 * This class represents the DiagramGraphic of a 'Transition' element.
 */
@objid ("7b98e7ad-4d1a-4500-b06d-275127d09727")
public class TransitionDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("051efbd4-e09c-444d-8d99-ea76b15c7481")
    public TransitionDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("853d9f02-9dac-4390-89f8-49b6abc88e0d")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case EDGE_GUARD:
            return getGmLink().getExtensions(GmTransition.ROLE_GUARD);
        case POSTCONDITION:
            return getGmLink().getExtensions(GmTransition.ROLE_POSTCOND);
            
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
