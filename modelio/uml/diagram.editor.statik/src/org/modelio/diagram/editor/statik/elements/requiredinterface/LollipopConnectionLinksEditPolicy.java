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

package org.modelio.diagram.editor.statik.elements.requiredinterface;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.modelio.diagram.elements.common.linktovoid.LinkToVoidFinishCreationEditPolicy;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.GmPath;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that allow connect and disconnection of required interfaces and provided interface links to the
 * {@link LollipopConnectionEditPart}.
 * 
 * @author cmarin
 */
@objid ("367c102f-55b7-11e2-877f-002564c97630")
public class LollipopConnectionLinksEditPolicy extends LinkToVoidFinishCreationEditPolicy {
    @objid ("367c1033-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getReconnectTargetCommand(final ReconnectRequest request) {
        final GmLink gmLink = (GmLink) request.getConnectionEditPart().getModel();
        final GmNodeModel newTargetNode = (GmNodeModel) request.getTarget().getModel();
        
        final MObject el = gmLink.getRelatedElement();
        if (el instanceof RequiredInterface) {
            final ReconnectRequiredInterfaceCommand cmd = new ReconnectRequiredInterfaceCommand(gmLink,
                    newTargetNode);
        
            return cmd;
        } else if (el instanceof ProvidedInterface) {
            final ReconnectProvidedInterfaceCommand cmd = new ReconnectProvidedInterfaceCommand(gmLink,
                    newTargetNode);
        
            return cmd;
        }
        return null;
    }

    @objid ("367c103a-55b7-11e2-877f-002564c97630")
    @Override
    protected Command getConnectionCompleteCommand(final CreateConnectionRequest request) {
        if (isHandled(request)) {
            return new CreateConnectedConnectionCommand(request, getHost(), request.getLocation());
        } else {
            return null;
        }
    }

    @objid ("367c1040-55b7-11e2-877f-002564c97630")
    private boolean isHandled(final CreateConnectionRequest r) {
        ModelioCreationContext ctx = ModelioCreationContext.lookRequest(r);
        if (ctx != null) {
            Class<? extends MObject> c = ctx.getMetaclass().getJavaInterface();
            return (ProvidedInterface.class.isAssignableFrom(c) || RequiredInterface.class.isAssignableFrom(c));
        } else {
            return false;
        }
    }

    /**
     * Command that moves the target of a required interface link.
     */
    @objid ("367c1046-55b7-11e2-877f-002564c97630")
    private static class ReconnectRequiredInterfaceCommand extends Command {
        @objid ("a788a429-55c2-11e2-9337-002564c97630")
        private GmLink gmLink;

        @objid ("a788a42a-55c2-11e2-9337-002564c97630")
        private GmNodeModel gmTarget;

        @objid ("367c104f-55b7-11e2-877f-002564c97630")
        public ReconnectRequiredInterfaceCommand(final GmLink gmLink, final GmNodeModel newTarget) {
            this.gmLink = gmLink;
            this.gmTarget = newTarget;
        }

        @objid ("367d96bd-55b7-11e2-877f-002564c97630")
        @Override
        public void execute() {
            updateModel();
            updateGmLink();
        }

        @objid ("367d96c0-55b7-11e2-877f-002564c97630")
        protected void updateModel() {
            final RequiredInterface requiredLink = (RequiredInterface) this.gmLink.getRepresentedElement();
            final MObject target = this.gmTarget.getRelatedElement();
            
            if (this.gmTarget instanceof GmLollipopConnection) {
                if (!isRequiredConnectedTo(requiredLink, target)) {
                    disconnect(requiredLink);
            
                    final IStandardModelFactory modelFactory = this.gmLink.getDiagram().getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
            
                    final NaryConnectorEnd linkEnd = modelFactory.createNaryConnectorEnd();
                    linkEnd.setConsumer(requiredLink);
                    linkEnd.setSource(requiredLink.getRequiring());
                    linkEnd.setNaryLink((NaryConnector) target);
                }
            
            } else {
                disconnect(requiredLink);
            }
        }

        /**
         * Update the graphic model.
         */
        @objid ("367d96c2-55b7-11e2-877f-002564c97630")
        protected void updateGmLink() {
            this.gmLink.getTo().removeEndingLink(this.gmLink);
            
            final GmPath p = new GmPath(this.gmLink.getPath());
            p.setTargetAnchor(null);
            this.gmLink.setLayoutData(p);
            
            this.gmTarget.addEndingLink(this.gmLink);
        }

        /**
         * Disconnect the required interface from any lollipop.
         * 
         * @param link The required interface link to disconnect.
         */
        @objid ("367d96c5-55b7-11e2-877f-002564c97630")
        private void disconnect(final RequiredInterface link) {
            link.getMClass().getMetamodel().getMExpert().setTarget(link, null, null);
        }

        /**
         * Tells whether the required interface is connected to the given lollipop Connector
         * 
         * @param link The required interface
         * @param target the lollipop, should be a Connector.
         * @return <code>true</code> only if the required interface is connected to the lollipop Connector
         */
        @objid ("367d96cc-55b7-11e2-877f-002564c97630")
        private boolean isRequiredConnectedTo(final RequiredInterface link, final MObject target) {
            for (NaryLinkEnd l : link.getNaryProvider()) {
            
                if (target.equals(l.getNaryLink())) {
                    return true;
                }
            }
            return false;
        }

        @objid ("367d96d9-55b7-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
                return false;
            }
            
            final RequiredInterface requiredLink = (RequiredInterface) this.gmLink.getRepresentedElement();
            final MObject target = this.gmTarget.getRelatedElement();
            
            if (this.gmTarget instanceof GmLollipopConnection) {
                if (!isRequiredConnectedTo(requiredLink, target)) {
                    if (!MTools.getAuthTool().canModify(requiredLink) || !MTools.getAuthTool().canModify(target)) {
                        return false;
                    }
            
                    return canDisconnect(requiredLink);
            
                }
            } else {
                return canDisconnect(requiredLink);
            }
            return super.canExecute();
        }

        @objid ("367d96de-55b7-11e2-877f-002564c97630")
        private boolean canDisconnect(final RequiredInterface requiredLink) {
            if (!MTools.getAuthTool().canModify(requiredLink)) {
                return false;
            }
            
            for (NaryLinkEnd end : requiredLink.getNaryProvider()) {
                final NaryLink l = end.getNaryLink();
                if (l != null && l.isValid() && !l.getStatus().isModifiable()) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * Command that moves the target of a required interface link.
     */
    @objid ("367d96e6-55b7-11e2-877f-002564c97630")
    private static class ReconnectProvidedInterfaceCommand extends Command {
        @objid ("a78bb169-55c2-11e2-9337-002564c97630")
        private GmLink gmLink;

        @objid ("a78bb16a-55c2-11e2-9337-002564c97630")
        private GmNodeModel gmTarget;

        @objid ("367d96ef-55b7-11e2-877f-002564c97630")
        public ReconnectProvidedInterfaceCommand(final GmLink gmLink, final GmNodeModel newTarget) {
            this.gmLink = gmLink;
            this.gmTarget = newTarget;
        }

        @objid ("367d96f9-55b7-11e2-877f-002564c97630")
        @Override
        public void execute() {
            updateModel();
            updateGmLink();
        }

        @objid ("367d96fc-55b7-11e2-877f-002564c97630")
        protected void updateModel() {
            final ProvidedInterface providedLink = (ProvidedInterface) this.gmLink.getRepresentedElement();
            final MObject target = this.gmTarget.getRelatedElement();
            
            if (this.gmTarget instanceof GmLollipopConnection) {
                if (!isProvidedConnectedTo(providedLink, target)) {
                    disconnect(providedLink);
            
                    final IStandardModelFactory modelFactory = this.gmLink.getDiagram().getModelManager().getModelFactory().getFactory(IStandardModelFactory.class);
            
                    final NaryConnectorEnd linkEnd = modelFactory.createNaryConnectorEnd();
                    linkEnd.setProvider(providedLink);
                    linkEnd.setSource(providedLink.getProviding());
                    linkEnd.setNaryLink((NaryConnector) target);
                }
            
            } else {
                disconnect(providedLink);
            }
        }

        /**
         * Update the graphic model.
         */
        @objid ("367f1d59-55b7-11e2-877f-002564c97630")
        protected void updateGmLink() {
            this.gmLink.getTo().removeEndingLink(this.gmLink);
            
            final GmPath p = new GmPath(this.gmLink.getPath());
            p.setTargetAnchor(null);
            this.gmLink.setLayoutData(p);
            
            this.gmTarget.addEndingLink(this.gmLink);
        }

        /**
         * Disconnect the required interface from any lollipop.
         * 
         * @param link The required interface link to disconnect.
         */
        @objid ("367f1d5c-55b7-11e2-877f-002564c97630")
        private void disconnect(final ProvidedInterface link) {
            link.getMClass().getMetamodel().getMExpert().setTarget(link, null, null);
        }

        /**
         * Tells whether the provided interface is connected to the given lollipop Connector
         * 
         * @param link The required interface
         * @param target the lollipop, should be a Connector.
         * @return <code>true</code> only if the provided interface is connected to the lollipop Connector
         */
        @objid ("367f1d63-55b7-11e2-877f-002564c97630")
        private boolean isProvidedConnectedTo(final ProvidedInterface link, final MObject target) {
            for (NaryLinkEnd l : link.getNaryConsumer()) {
                if (target.equals(l.getNaryLink())) {
                    return true;
                }
            }
            return false;
        }

        @objid ("367f1d70-55b7-11e2-877f-002564c97630")
        @Override
        public boolean canExecute() {
            if (!MTools.getAuthTool().canModify(this.gmLink.getDiagram().getRelatedElement())) {
                return false;
            }
            
            final ProvidedInterface providedLink = (ProvidedInterface) this.gmLink.getRepresentedElement();
            final MObject target = this.gmTarget.getRelatedElement();
            
            if (this.gmTarget instanceof GmLollipopConnection) {
                if (!isProvidedConnectedTo(providedLink, target)) {
                    if (!MTools.getAuthTool().canModify(providedLink) || !MTools.getAuthTool().canModify(target)) {
                        return false;
                    }
            
                    return canDisconnect(providedLink);
            
                }
            } else {
                return canDisconnect(providedLink);
            }
            return super.canExecute();
        }

        @objid ("367f1d75-55b7-11e2-877f-002564c97630")
        private boolean canDisconnect(final ProvidedInterface providedLink) {
            if (!MTools.getAuthTool().canModify(providedLink)) {
                return false;
            }
            
            for (NaryLinkEnd end : providedLink.getNaryConsumer()) {
                final NaryLink l = end.getNaryLink();
                if (l != null && l.isValid() && !l.getStatus().isModifiable()) {
                    return false;
                }
            }
            return true;
        }

    }

}
