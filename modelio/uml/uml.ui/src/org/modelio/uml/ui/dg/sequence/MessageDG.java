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

package org.modelio.uml.ui.dg.sequence;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.InvalidDestinationPointException;
import org.modelio.api.modelio.diagram.InvalidPointsPathException;
import org.modelio.api.modelio.diagram.InvalidSourcePointException;
import org.modelio.diagram.api.dg.LinkPath;
import org.modelio.diagram.api.services.DiagramHandle;
import org.modelio.diagram.api.services.DiagramLink;
import org.modelio.diagram.editor.sequence.elements.message.GmMessage;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmNode;

/**
 * This class represents the DiagramGraphic of a 'Message' element.
 */
@objid ("3ad0744b-355d-4b51-8a32-94fc29a85410")
public class MessageDG extends DiagramLink {
    /**
     * @param diagramHandle The diagram manipulation class.
     * @param link The gm link represented by this class.
     */
    @objid ("95bfc49c-07a7-4756-adad-8e2bbb9bd988")
    public MessageDG(DiagramHandle diagramHandle, IGmLink link) {
        super(diagramHandle, link);
    }

    @objid ("04620a3a-7dde-4ba7-8f41-d0408122ac61")
    @Override
    public ILinkPath getPath() {
        return new LinkPath();
    }

    @objid ("90361ae4-9e3f-4190-b1c4-8ec720db8ec0")
    @Override
    public void setPath(final Collection<Point> points) {
        // Do nothing
    }

    @objid ("48a84774-55b9-450a-a374-e980ecf9b92f")
    @Override
    public void setPath(final ILinkPath linkPath) throws InvalidDestinationPointException, InvalidPointsPathException, InvalidSourcePointException {
        // Do nothing
    }

    @objid ("f41d1b49-25fa-4e67-8fdd-3b8fba1c33d8")
    @Override
    public LinkRouterKind getRouterKind() {
        return LinkRouterKind.DIRECT;
    }

    @objid ("b620b2a4-7695-4d7d-a962-a3ccf7e6e523")
    @Override
    public void setRouterKind(final LinkRouterKind routerKind) {
        // Do nothing
    }

    @objid ("0eb2df9b-3541-486d-9218-6ac3a78a5845")
    @Override
    protected Collection<IGmNode> getGmNodes(ExtensionRole role) {
        switch (role) {
        case MAIN:
            return getGmLink().getExtensions(IGmLink.ROLE_MAIN_LABEL);
        case TO_INFOFLOW_ARROW:
            return getGmLink().getExtensions(GmMessage.ROLE_INFOFLOW_ARROW);
        case TO_INFOFLOW_GROUP:
            return getGmLink().getExtensions(GmMessage.ROLE_INFOFLOW_GRP);
            
            //$CASES-OMITTED$
        default:
            return Collections.emptyList();
        
        }
    }

}
