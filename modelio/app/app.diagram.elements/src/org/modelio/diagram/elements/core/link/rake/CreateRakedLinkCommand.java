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
package org.modelio.diagram.elements.core.link.rake;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.DefaultCreateLinkCommand;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkRake;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Command that creates a link raked to another one.
 * 
 * @author cmarin
 */
@objid ("8059f6c9-1dec-11e2-8cad-001ec947c8cc")
public class CreateRakedLinkCommand extends DefaultCreateLinkCommand {
    @objid ("8059f6cb-1dec-11e2-8cad-001ec947c8cc")
    private IGmLink otherLink;

    /**
     * Initialize the command.
     * @param context the creation context.
     * @param otherLink the link to rake to.
     */
    @objid ("8059f6cc-1dec-11e2-8cad-001ec947c8cc")
    public  CreateRakedLinkCommand(final ModelioLinkCreationContext context, final IGmLink otherLink) {
        super(context);
        this.otherLink = otherLink;
        setTarget(otherLink.getTo());
        
    }

    @objid ("8059f6d3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IGmLink unmaskElement(final IGmDiagram gmDiagram, final MObject linkElement) {
        // Unmask the new link
        IGmLink gmNewLink = super.unmaskElement(gmDiagram, linkElement);
        
        if (this.otherLink.getPath().getTargetRake() == null) {
            // Connect the other link to the unmasked one.
            GmPath path = new GmPath(this.otherLink.getPath());
            IGmLinkRake rake = gmNewLink.getPath().getTargetRake();
            path.setTargetAnchor(rake.getSharedAnchor());
            path.setTargetRake(rake);
            path.setRouterKind(ConnectionRouterId.ORTHOGONAL);
            path.setPathData(gmNewLink.getPath().getPathData());
        
            this.otherLink.setLayoutData(path);
        }
        return gmNewLink;
    }

}
