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

package org.modelio.uml.ui.dg.communication;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.communication.elements.communicationchannel.GmCommunicationChannel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'CommunicationChannel' element.
 */
@objid ("6c30d542-1ad9-4dea-8fdf-13cbbd3ee426")
public class CommunicationChannelDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("2b697b01-b063-4063-a067-13832e0edcec")
    public CommunicationChannelDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("c0165a4c-c0d3-4a9d-8014-eb32fe12bb8f")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case FROM_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmCommunicationChannel.ROLE_BACKWARD_ARROW);
        case FROM_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmCommunicationChannel.ROLE_BACKWARD_MSG_GROUP);
        case TO_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmCommunicationChannel.ROLE_FORWARD_ARROW);
        case TO_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmCommunicationChannel.ROLE_FORWARD_MSG_GROUP);
            
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
