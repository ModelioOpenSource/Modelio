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

package org.modelio.diagram.elements.core.link.linknode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.api.module.mda.IMdaExpert;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.commands.ILinkAndNodeCreationSupport;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("88e939fc-e15e-4210-97ba-37ae810d6b68")
public class CreateLinkAndNodeCommand extends Command {
    @objid ("4c103e9d-5f65-4129-8438-8e5991ab0cc9")
    private final Command nodeCreationCommand;

    @objid ("40081b05-747f-45f5-b72c-9dd7e2be9d02")
    private final CreateConnectionRequest connectionRequest;

    @objid ("ea32fcb6-efcc-459a-8c3b-199f5c4fb8ff")
    private final IGmLinkable sourceGm;

    @objid ("39ff1b0d-b198-4d61-a682-9d41fb0dd7ac")
    private final ModelioCreationContext nodeCtx;

    /**
     * @param nodeCtx The target node creation context
     * @param nodeCreationCommand a command that implements {@link ILinkAndNodeCreationSupport}
     * @param connectionRequest The connection creation request.
     * @param sourceGm the connection source node
     */
    @objid ("265570c4-6e29-4b94-ba08-7c1e52606c1c")
    public CreateLinkAndNodeCommand(ModelioCreationContext nodeCtx, Command nodeCreationCommand, CreateConnectionRequest connectionRequest, IGmLinkable sourceGm) {
        this.nodeCreationCommand = nodeCreationCommand;
        this.connectionRequest = connectionRequest;
        this.sourceGm = sourceGm;
        this.nodeCtx = nodeCtx;
    }

    @objid ("6df18e97-889f-41bf-9862-6494a11ac341")
    @Override
    public void execute() {
        // create choosen node
        this.nodeCreationCommand.execute();
        
        GraphicalViewer viewer = (GraphicalViewer) this.connectionRequest.getSourceEditPart().getViewer();
        GmNodeModel targetGm = ((ILinkAndNodeCreationSupport) this.nodeCreationCommand).getMainLinkable();
        if (targetGm instanceof GmPortContainer) {
            targetGm = ((GmPortContainer) targetGm).getMainNode();
        }
        
        GraphicalEditPart targetNodeEp = (GraphicalEditPart) viewer.getEditPartRegistry().get(targetGm);
        
        centerNodeOnTopLeft(targetNodeEp);
        
        Object savedType = this.connectionRequest.getType();
        this.connectionRequest.setTargetEditPart(targetNodeEp);
        this.connectionRequest.setType(RequestConstants.REQ_CONNECTION_END);
        
        Command linkCmd = targetNodeEp.getCommand(this.connectionRequest);
        
        this.connectionRequest.setType(savedType);
        
        if (linkCmd != null && linkCmd.canExecute()) {
            linkCmd.execute();
        }
    }

    @objid ("a1f41df5-86af-482a-8a0d-ef84370e4cdf")
    @Override
    public boolean canExecute() {
        // The diagram must be valid and modifiable.
        final IGmDiagram gmDiagram = this.sourceGm.getDiagram();
        if (!MTools.getAuthTool().canModify(gmDiagram.getRelatedElement())) {
            return false;
        }
        
        // Node creation possible ?
        if (this.nodeCreationCommand.canExecute() == false) {
            return false;
        }
        
        // Link creation possible ?
        MObject srcElement = this.sourceGm.getRelatedElement();
        final ModelioLinkCreationContext linkCtx = ModelioLinkCreationContext.lookRequest(this.connectionRequest);
        if (linkCtx == null) {
            return false;
        }
        MClass linkMetaclass = linkCtx.getMetaclass();
        Stereotype linkStereotype = linkCtx.getStereotype();
        IMdaExpert mdaExpert = gmDiagram.getModelManager().getMdaExpert();
        
        // Theoretical study based on metaclasses
        if (!mdaExpert.canLink(linkStereotype, linkMetaclass, srcElement.getMClass(), this.nodeCtx.getMetaclass())) {
            return false;
        }
        
        // Link Source : The access right expert must allow the command
        if (!MTools.getAuthTool().canCreateLinkFrom(linkMetaclass.getJavaInterface(), srcElement)) {
            return false;
        }
        
        // All conditions are fulfilled
        return true;
    }

    @objid ("d8f054bd-8976-4862-8b98-e0b766399c29")
    private void centerNodeOnTopLeft(GraphicalEditPart ep) {
        IFigure fig = ep.getFigure();
        
        fig.getUpdateManager().performValidation();
        
        Dimension delta = fig.getSize().scale(0.5).negate();
        fig.translateToAbsolute(delta);
        
        ChangeBoundsRequest r = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        r.setEditParts(ep);
        r.setMoveDelta(new PrecisionPoint(delta.preciseWidth(), delta.preciseHeight()));
        
        Command c = ep.getCommand(r);
        if (c != null && c.canExecute()) {
            c.execute();
        }
        
        fig.getUpdateManager().performValidation();
    }

}
