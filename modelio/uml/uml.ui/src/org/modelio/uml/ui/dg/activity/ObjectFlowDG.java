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

package org.modelio.uml.ui.dg.activity;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.activity.elements.objectflow.GmObjectFlow;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'ObjectFlow' element.
 */
@objid ("491bafb1-e5d3-4543-90b9-3dbd3eca8076")
public class ObjectFlowDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("f776708c-77c4-48e2-9772-0f38df93f19c")
    public ObjectFlowDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("9c14b72c-fc12-4482-beb7-ab9a391ab0bf")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case EDGE_GUARD:
            return getGmLink().getExtensions(GmObjectFlow.ROLE_GUARD);
        case EDGE_WEIGHT:
            return getGmLink().getExtensions(GmObjectFlow.ROLE_WEIGHT);
        case TO_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmObjectFlow.ROLE_INFOFLOW_ARROW);
        case TO_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmObjectFlow.ROLE_INFOFLOW_GROUP);
            
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
