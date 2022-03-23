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
package org.modelio.uml.communicationdiagram.editor.elements.communicationmessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationChannel;
import org.modelio.metamodel.uml.behavior.communicationModel.CommunicationMessage;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.uml.statikdiagram.editor.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.model.spi.mtools.IAuthTool;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Allow creation of information flow on the link.
 * <p>
 * Defers the creation to the right {@link GmInfoFlowsGroup}.
 * <p>
 * {@link #createListener()} and {@link #decorateChildren()} are redefined to disable child decoration.
 * 
 * @author cmarin
 */
@objid ("7a3ef9d9-55b6-11e2-877f-002564c97630")
public class CreateInfoFlowOnMessageEditPolicy extends DeferringCreateNodePolicy {
    @objid ("7a3ef9dd-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getAddCommand(final Request request) {
        if (getTargetEditPart(request) == null)
            return null;
        return new DeferredCommand(request, getHost());
    }

    @objid ("7a3ef9e4-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getTargetEditPart(request) == null)
            return null;
        return new DeferredCommand(request, getHost());
    }

    @objid ("7a3ef9eb-55b6-11e2-877f-002564c97630")
    @Override
    protected Command getCreateCommand(final CreateRequest createReq) {
        if (getTargetEditPart(createReq) == null)
            return null;
        return new DeferredCommand(createReq, getHost());
    }

    @objid ("7a3ef9f2-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPart getEditPartFor(final Class<? extends MObject> metaclass, final Point location) {
        if (!InformationFlow.class.isAssignableFrom(metaclass))
            return null;
        
        final GmNodeModel gmNode = (GmNodeModel) getHost().getModel();
        MRef ref = gmNode.getRepresentedRef();
        
        // Look for the child node accepting the given node type.
        GmNodeModel flowGroup = getNodeFor(((GmCompositeNode) gmNode.getParent()).getChildren(),
                GmInfoFlowsGroup.class,
                ref);
        
        // If no one can contain the element, return null to forward to the parent.
        if (flowGroup == null)
            return getHost();
        
        // Return the edit part of the child node.
        final EditPart p = (EditPart) getHost().getRoot().getViewer().getEditPartRegistry().get(flowGroup);
        return p;
    }

    /**
     * This edit policy must not decorate new children because {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already does it.
     */
    @objid ("7a3ef9fd-55b6-11e2-877f-002564c97630")
    @Override
    protected EditPartListener createListener() {
        return null;
    }

    /**
     * This edit policy must not decorate children because {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already does it.
     */
    @objid ("7a3efa03-55b6-11e2-877f-002564c97630")
    @Override
    protected void decorateChildren() {
        return;
    }

    /**
     * Look in the given list for a node of the given class relating the given element reference.
     * @param nodes the list to search into
     * @param cl the node class
     * @param relatedRef the represented element reference
     * @return the found node or <code>null</code> if not found.
     */
    @objid ("7a3efa08-55b6-11e2-877f-002564c97630")
    GmNodeModel getNodeFor(final List<GmNodeModel> nodes, final Class<?> cl, final MRef relatedRef) {
        for (GmNodeModel c : nodes) {
            if (cl.isInstance(c) && c.getRepresentedRef().equals(relatedRef))
                return c;
        }
        return null;
    }

    @objid ("7a40807a-55b6-11e2-877f-002564c97630")
    class DeferredCommand extends Command {
        @objid ("7a40807f-55b6-11e2-877f-002564c97630")
        private Map<?, ?> editPartRegistry;

        @objid ("9c9938f9-55c1-11e2-9337-002564c97630")
        private GmNodeModel gmNode;

        @objid ("914e39ed-78dd-4a31-acb4-13e8c208a968")
        private Request req;

        /**
         * Create a deferred command.
         * @param req The creation request.
         * @param sender The edit part sending the request
         */
        @objid ("7a408083-55b6-11e2-877f-002564c97630")
        public  DeferredCommand(final Request req, final EditPart sender) {
            this.req = req;
            this.gmNode = (GmNodeModel) sender.getModel();
            this.editPartRegistry = sender.getViewer().getEditPartRegistry();
            
        }

        @objid ("7a40808a-55b6-11e2-877f-002564c97630")
        @SuppressWarnings ("synthetic-access")
        @Override
        public boolean canExecute() {
            CommunicationMessage msg = (CommunicationMessage) getHostElement();
            CommunicationChannel channel = msg.getChannel();
            if (channel == null)
                channel = msg.getInvertedChannel();
            NameSpace ns = getCommonNameSpace(channel.getStart(), channel.getEnd());
            IAuthTool authHelper = MTools.getAuthTool();
            return (authHelper.canModify(this.gmNode.getDiagram().getRelatedElement()) && authHelper.canModify(msg) && authHelper.canModify(ns));
        }

        @objid ("7a40808f-55b6-11e2-877f-002564c97630")
        @Override
        public void execute() {
            Command cmd = createCommand();
            if (cmd != null && cmd.canExecute())
                cmd.execute();
            
        }

        @objid ("7a408092-55b6-11e2-877f-002564c97630")
        private Command createCommand() {
            GmCompositeNode gmTarget = (GmCompositeNode) getNodeFor(((GmCompositeNode) this.gmNode.getParent()).getChildren(),
                    GmInfoFlowsGroup.class,
                    this.gmNode.getRepresentedRef());
            
            if (gmTarget == null) {
                gmTarget = new GmInfoFlowsGroup(this.gmNode.getDiagram(), this.gmNode.getRepresentedRef());
                GmCompositeNode parent = this.gmNode.getParentNode();
                parent.addChild(gmTarget, parent.getChildIndex(this.gmNode) + 1);
            }
            
            if (!gmTarget.isVisible())
                gmTarget.setVisible(true);
            
            final EditPart p = (EditPart) this.editPartRegistry.get(gmTarget);
            if (p == null)
                return null;
            
            final EditPart targetPart = p.getTargetEditPart(this.req);
            if (targetPart != null)
                return targetPart.getCommand(this.req);
            else
                return null;
            
        }

        /**
         * Get the name space owning both model elements
         * @return
         */
        @objid ("7a408095-55b6-11e2-877f-002564c97630")
        private NameSpace getCommonNameSpace(final MObject aSource, final MObject aTarget) {
            final ArrayList<MObject> l1 = new ArrayList<>(20);
            final ArrayList<MObject> l2 = new ArrayList<>(20);
            
            MObject el = aSource;
            while (el != null) {
                l1.add(el);
                el = el.getCompositionOwner();
            }
            
            el = aTarget;
            while (el != null) {
                l2.add(el);
                el = el.getCompositionOwner();
            }
            
            Collections.reverse(l1);
            Collections.reverse(l2);
            
            MObject ret = null;
            int i = 0;
            final int max = Math.min(l1.size(), l2.size());
            do {
                el = l1.get(i);
                if (el instanceof NameSpace) {
                    if (el.equals(l2.get(i))) {
                        ret = el;
                    } else {
                        return (NameSpace) ret;
                    }
                } else if (ret != null) {
                    return (NameSpace) ret;
                }
                i++;
            
            } while (i < max);
            
            // Reaching this point means aSource == aTarget
            if (ret != null)
                return (NameSpace) ret;
            
            // Should never reach this point.
            throw new IllegalArgumentException("No common namespace between " + aSource + " and " + aTarget);
            
        }

    }

}
