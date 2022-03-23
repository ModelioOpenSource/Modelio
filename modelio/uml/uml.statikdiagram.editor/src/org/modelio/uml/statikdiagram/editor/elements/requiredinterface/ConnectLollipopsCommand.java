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
package org.modelio.uml.statikdiagram.editor.elements.requiredinterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.uml.statikdiagram.editor.elements.providedinterface.ProvidedInterfaceLinkEditPart;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MExpert;

/**
 * Command that connects a {@link RequiredInterfaceLinkEditPart} to a {@link ProvidedInterfaceLinkEditPart}
 * 
 * @author cmarin
 */
@objid ("366ccdda-55b7-11e2-877f-002564c97630")
public class ConnectLollipopsCommand extends Command {
    @objid ("63713969-5bd5-11e2-9e33-00137282c51b")
    private RequiredInterfaceLinkEditPart reqEditPart;

    @objid ("6371396a-5bd5-11e2-9e33-00137282c51b")
    private ProvidedInterfaceLinkEditPart provEditPart;

    @objid ("8f488ead-ff63-43bd-92fa-0f7686fcca1a")
    private Point location;

    /**
     * Initialize the command.
     * @param reqEditPart the required interface
     * @param provEditPart the provided interface
     * @param location the junction location
     */
    @objid ("366ccddf-55b7-11e2-877f-002564c97630")
    public  ConnectLollipopsCommand(final RequiredInterfaceLinkEditPart reqEditPart, final ProvidedInterfaceLinkEditPart provEditPart, final Point location) {
        this.reqEditPart = reqEditPart;
        this.provEditPart = provEditPart;
        this.location = location;
        
    }

    @objid ("366ccde8-55b7-11e2-877f-002564c97630")
    @Override
    public void execute() {
        final GmRequiredInterfaceLink gmReq = (GmRequiredInterfaceLink) this.reqEditPart.getModel();
        final GmProvidedInterfaceLink gmProv = (GmProvidedInterfaceLink) this.provEditPart.getModel();
        
        final RequiredInterface req = gmReq.getRelatedElement();
        final ProvidedInterface prov = gmProv.getRelatedElement();
        final Port reqPort = req.getRequiring();
        final Port provPort = prov.getProviding();
        
        final MExpert expert = req.getMClass().getMetamodel().getMExpert();
        
        // Disconnect required and provided from any existing lollipop
        expert.setTarget(req, null,null);
        expert.setTarget(prov, null,null);
        
        
        // Create the connector
        final IGmDiagram gmDiagram = gmReq.getDiagram();
        final IStandardModelFactory factory = gmDiagram.getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
        
        final NaryConnectorEnd reqConn = factory.createNaryConnectorEnd();
        final NaryConnectorEnd provConn = factory.createNaryConnectorEnd();
        final NaryConnector conn = factory.createNaryConnector();
        
        reqConn.setNaryLink(conn);
        provConn.setNaryLink(conn);
        
        reqConn.setSource(reqPort);
        reqConn.setConsumer(req);
        
        provConn.setSource(provPort);
        provConn.setProvider(prov);
        
        // Unmask the connector
        GmLollipopConnection cnx;
        cnx = new GmLollipopConnection(gmDiagram, conn);
        ((GmCompositeNode) gmDiagram).addChild(cnx);
        
        Point pt = this.location.getCopy();
        IFigure targetFig = ((AbstractGraphicalEditPart) this.reqEditPart.getTarget()).getFigure();
        targetFig.translateToRelative(pt);
        
        cnx.setLayoutData(new Rectangle(pt.x, pt.y, -1, -1));
        
    }

    @objid ("366e547a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canExecute() {
        final GmRequiredInterfaceLink gmReq = (GmRequiredInterfaceLink) this.reqEditPart.getModel();
        final GmProvidedInterfaceLink gmProv = (GmProvidedInterfaceLink) this.provEditPart.getModel();
        
        if (!MTools.getAuthTool().canModify(gmProv.getDiagram().getRelatedElement())) {
            return false;
        }
        
        final RequiredInterface req = gmReq.getRelatedElement();
        final ProvidedInterface prov = gmProv.getRelatedElement();
        final Port reqPort = req.getRequiring();
        final Port provPort = prov.getProviding();
        return (MTools.getAuthTool().canModify(req) && MTools.getAuthTool().canModify(prov) && MTools.getAuthTool().canModify(reqPort) && MTools.getAuthTool().canModify(provPort));
    }

}
