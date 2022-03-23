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
package org.modelio.uml.statikdiagram.editor.elements.informationflowgroup;

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DeferringCreateNodePolicy;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Allow creation of information flow on the link.
 * <p>
 * Defers the creation to the right {@link GmInfoFlowsGroup}.
 * <p>
 * FIXME: This policy should descend from GmLinkLayoutEditPolicy and not from DefaultCreateNodeEditPolicy. But
 * DeferringCreateNoteEditPolicy inherits from DefaultCreateNodeEditPolicy. So {@link #createListener()} and
 * {@link #decorateChildren()} had to be redefined to disable child decoration.
 * 
 * @author cmarin
 */
@objid ("815dd728-1dec-11e2-8cad-001ec947c8cc")
public class DefaultCreateInfoFlowOnLinkEditPolicy extends DeferringCreateNodePolicy {
    @objid ("815dd72a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getAddCommand(final Request request) {
        if (getTargetEditPart(request) == null)
            return null;
        return new DeferredCommand(request, getHost());
    }

    @objid ("81603976-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCloneCommand(final ChangeBoundsRequest request) {
        if (getTargetEditPart(request) == null)
            return null;
        return new DeferredCommand(request, getHost());
    }

    @objid ("81603981-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Command getCreateCommand(final CreateRequest createReq) {
        if (getTargetEditPart(createReq) == null)
            return null;
        return new DeferredCommand(createReq, getHost());
    }

    @objid ("8160398c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPart getEditPartFor(final Class<? extends MObject> metaclass, final Point location) {
        if (metaclass != InformationFlow.class)
            return null;
        
        final GmLink gmLink = (GmLink) getHost().getModel();
        final GmCompositeNode gmTargetChild = getExtensionFor(gmLink, location);
        
        if (gmTargetChild == null)
            return null;
        
        if (!gmTargetChild.isVisible())
            return getHost();
        
        final EditPart p = (EditPart) getHost().getRoot()
                                               .getViewer()
                                               .getEditPartRegistry()
                                               .get(gmTargetChild);
        return p;
    }

    /**
     * Get the extension on which the information flow label must be added.
     * <p>
     * Looks for and return the first {@link GmInfoFlowsGroup} extension on the link.
     * <p>
     * Subclasses may override this method.
     * @param gmLink The model link
     * @param location The mouse location
     * @return The composite node where the label must be added.
     */
    @objid ("8160399b-1dec-11e2-8cad-001ec947c8cc")
    protected GmCompositeNode getExtensionFor(final GmLink gmLink, final Point location) {
        MObject relatedEl = gmLink.getRelatedElement();
        
        GmCompositeNode gmTargetChild = null;
        for (GmNodeModel n : gmLink.getExtensions()) {
            if (n instanceof GmInfoFlowsGroup && n.getRelatedElement().equals(relatedEl)) {
                gmTargetChild = (GmCompositeNode) n;
                break;
            }
        }
        return gmTargetChild;
    }

    /**
     * This edit policy must not decorate new children because
     * {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already
     * does it.
     */
    @objid ("816039a6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected EditPartListener createListener() {
        return null;
    }

    /**
     * This edit policy must not decorate children because
     * {@link org.modelio.diagram.elements.core.link.GmLinkLayoutEditPolicy GmLinkLayoutEditPolicy} already
     * does it.
     */
    @objid ("816039ae-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void decorateChildren() {
        return;
    }

    @objid ("816039b3-1dec-11e2-8cad-001ec947c8cc")
    class DeferredCommand extends Command {
        @objid ("816039ba-1dec-11e2-8cad-001ec947c8cc")
        private Map<?, ?> editPartRegistry;

        @objid ("816039b9-1dec-11e2-8cad-001ec947c8cc")
        private GmLink gmLink;

        @objid ("c6144a67-56e0-4f7b-995b-d802ff221f4a")
        private Request req;

        /**
         * Create a deferred command.
         * @param req The creation request.
         * @param sender The edit part sending the request
         */
        @objid ("816039be-1dec-11e2-8cad-001ec947c8cc")
        public  DeferredCommand(final Request req, final EditPart sender) {
            this.req = req;
            this.gmLink = (GmLink) sender.getModel();
            this.editPartRegistry = sender.getViewer().getEditPartRegistry();
            
        }

        @objid ("81629bcb-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public boolean canExecute() {
            Command cmd = createCommand();
            return (cmd != null && cmd.canExecute());
        }

        @objid ("81629bd0-1dec-11e2-8cad-001ec947c8cc")
        @Override
        public void execute() {
            Command cmd = createCommand();
            if (cmd != null && cmd.canExecute())
                cmd.execute();
            
        }

        @objid ("81629bd3-1dec-11e2-8cad-001ec947c8cc")
        private Command createCommand() {
            final GmCompositeNode gmTarget = getExtensionFor(this.gmLink,
                                                             ((DropRequest) this.req).getLocation());
            
            if (gmTarget == null)
                return null;
            
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

    }

}
