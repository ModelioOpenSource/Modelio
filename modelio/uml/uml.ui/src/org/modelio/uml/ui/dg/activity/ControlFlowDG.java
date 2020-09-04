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

package org.modelio.uml.ui.dg.activity;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.activity.elements.controlflow.GmControlFlow;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'ControlFlow' element.
 */
@objid ("626c5583-abcf-4885-98bd-66cbaaacf428")
public class ControlFlowDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("1d263529-ec8f-4a66-8b0a-2516fbcce169")
    public ControlFlowDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("dbe3f731-76cf-4d12-b476-01a581839af5")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case EDGE_GUARD:
            return getGmLink().getExtensions(GmControlFlow.ROLE_GUARD);
        case EDGE_WEIGHT:
            return getGmLink().getExtensions(GmControlFlow.ROLE_WEIGHT);
        case TO_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmControlFlow.ROLE_INFOFLOW_ARROW);
        case TO_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmControlFlow.ROLE_INFOFLOW_GROUP);
            
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
