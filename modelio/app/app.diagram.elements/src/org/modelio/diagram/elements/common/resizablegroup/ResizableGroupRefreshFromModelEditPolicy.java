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
package org.modelio.diagram.elements.common.resizablegroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.policies.LayoutChildrenNodeConnectionsHelper;
import org.modelio.diagram.elements.core.requests.RequestProperty;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that waits for {@link GmModel#PROP_REFRESH_FROM_OBMODEL} property change event
 * to refresh a {@link GmCompositeNode} content from the model, by using requests and commands.
 * <p>
 * Copy paste of GroupRefreshFromModelEditPolicy for GmResizableGroup, but usable for a  {@link GmCompositeNode} that behave the same.
 * <p>
 * This will allow a {@link GmCompositeNode} to better autosize when its content change, depending on on its edit policies
 * installed on the {@link ResizableGroupEditPart}.
 * <p>
 * {@link GmResizableGroup} does fire {@link GmModel#PROP_REFRESH_FROM_OBMODEL} property change event.
 * 
 * 
 * @author cma
 * @since 5.1.0
 * @see org.modelio.diagram.elements.core.policies.AutoFitToContentEditPolicy
 */
@objid ("497c7aa3-d8a5-46eb-8afb-7e634205c095")
public class ResizableGroupRefreshFromModelEditPolicy extends DefaultRefreshFromModelEditPolicy {
    @objid ("eee6e1fc-ae8f-4bdc-a6bb-457d7bbac403")
    private final boolean ordered;

    @objid ("7b3b2fd1-1c41-430f-aed7-c947d3300ea4")
    private final Function<MObject, List<? extends MObject>> expectedChildren;

    /**
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @param ordered if true, order of the graphic elements will be synchronized with the order returned by the function
     */
    @objid ("77df9754-d0e5-4909-926c-9d9720710aa7")
    public  ResizableGroupRefreshFromModelEditPolicy(Function<MObject, List<? extends MObject>> expectedChildren, boolean ordered) {
        super();
        this.expectedChildren = expectedChildren;
        this.ordered = ordered;
        
    }

    /**
     * Updates the set of children GmNodeModel so that it is in sync with the
     * OB model children. This method is called from {@link #propertyChange(java.beans.PropertyChangeEvent)}. This method
     * requires linear time to complete.
     * <P>
     * The update is performed by comparing the existing GmNodeModel with the set
     * of model children returned from {@link #expectedChildren}. GmNodeModel
     * whose models no longer exist are {@link #getRemoveChildCommand(GmNodeModel, Map) removed}.
     * New models have their GmNodeModel {@link #getCreateChildCommand(MObject, int) created}.
     * <p>
     * @author Inspired from {@link AbstractEditPart#refreshChildren()}
     */
    @objid ("d0d44b4d-860a-46b7-941d-2834f8eb705d")
    protected final Command getRefreshCommand() {
        final GmCompositeNode gmGroup = getModel();
        
        List<? extends MObject> obChildren = this.expectedChildren.apply(gmGroup.getRelatedElement());
        if (obChildren == null) {
            // Abort refresh
            return null;
        }
        return getRefreshChildrencommand(gmGroup, obChildren);
    }

    /**
     * Updates the set of children GmNodeModel so that it is in sync with the
     * OB model children. This method is called from {@link #propertyChange()}. This method
     * requires linear time to complete.
     * <P>
     * The update is performed by comparing the existing GmNodeModel with the set
     * of model children returned from {@link #expectedChildren}. GmNodeModel
     * whose models no longer exist are {@link #getRemoveChildCommand(GmNodeModel, Map) removed}.
     * New models have their GmNodeModel {@link #getCreateChildCommand(MObject, int) created}.
     * <p>
     * @author Inspired from {@link AbstractEditPart#refreshChildren()}
     * @param gmGroup the host graphic model
     * @param obChildren the children model elements to display
     */
    @objid ("d16d5324-d45e-4450-bc2e-3f843b28393a")
    protected Command getRefreshChildrencommand(final GmCompositeNode gmGroup, List<? extends MObject> obChildren) {
        final CompoundCommand command = new CompoundCommand();
        final Map<Object, EditPart> editPartRegistry = getHost().getViewer().getEditPartRegistry();
        final List<GmNodeModel> gmChildren = gmGroup.getChildren();
        
        int gmSize = gmChildren.size();
        Map<MObject, GmNodeModel> obToGm = Collections.emptyMap();
        if (gmSize > 0) {
            obToGm = new HashMap<>(gmSize);
            for (GmNodeModel gmChild : gmChildren) {
                obToGm.put(gmChild.getRelatedElement(), gmChild);
            }
        }
        
        int obSize = obChildren.size();
        int obIndex = 0;
        while (obIndex < obSize) {
            MObject obElement = obChildren.get(obIndex);
        
            // Do a quick check to see if gmChildren[i] == obChildren[i]
            if (obIndex < gmSize
                    && obElement.equals((gmChildren.get(obIndex)).getRelatedElement())) {
                obIndex++;
                obToGm.remove(obElement);
                continue;
            }
        
            // Look to see if the GmNodeModel is already around but in the
            // wrong location
            GmNodeModel gmChild = obToGm.remove(obElement);
        
            if (gmChild != null) {
                if (this.ordered) {
                    command.add(getReorderChildCommand(gmChild, obIndex));
                }
            } else {
                // A GmNodeModel for this model doesn't exist yet. Create and
                // insert one.
                command.add(getCreateChildCommand(obElement, obIndex));
            }
        
            obIndex++;
        }
        
        Collection<GraphicalEditPart> deletedEp = Collections.emptyList();
        // Remove the remaining GmNodeModel
        if (! obToGm.isEmpty()) {
            deletedEp = new ArrayList<>();
        
            for (GmNodeModel gmToDelete : obToGm.values()) {
                command.add(getRemoveChildCommand(gmToDelete, editPartRegistry));
                deletedEp.add((GraphicalEditPart) editPartRegistry.get(gmToDelete));
            }
        }
        
        if (command.size()==0)
            return null;
        
        // Add layout links command
        if (false) {
            LayoutChildrenNodeConnectionsHelper.forRequest(null)
            .addEditPart((GraphicalEditPart) getHost())
            .removeEditParts(deletedEp)
            .createCommands(command);
        }
        return command.unwrap();
    }

    @objid ("b796aa62-2df5-4935-a7b2-0e70ea3ec407")
    private Command getCreateChildCommand(MObject model, int index) {
        // Create unmask request
        CreateRequest req = new CreateRequest();
        ModelioCreationContext ctx = new ModelioCreationContext(model);
        req.setFactory(ctx);
        RequestProperty.PROP_GROUP_ITEM_INDEX.set(req, index);
        
        Command cmd = getHost().getCommand(req);
        if (cmd == null || !cmd.canExecute()) {
            DiagramElements.LOG.debug("%s: Unable to unmask %s under %s, command = %s", getClass().getSimpleName(), model, getHost(), cmd);
            DiagramElements.LOG.debug(new Throwable("stack trace"));
        }
        return cmd;
    }

    @objid ("7a538fcc-ce9f-4fe6-b772-df8e5ba6063c")
    private Command getRemoveChildCommand(GmNodeModel gmChild, Map<Object, EditPart> editPartRegistry) {
        EditPart ep = editPartRegistry.get(gmChild);
        if (ep != null) {
            GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE_DEPENDANT);
            deleteReq.setEditParts(ep);
            Command cmd = getHost().getCommand(deleteReq);
            if (cmd == null || ! cmd.canExecute()) {
                DiagramElements.LOG.debug("%s: Unable to mask %s under %s, command = %s", getClass().getSimpleName(), gmChild, getHost(), cmd);
                DiagramElements.LOG.debug(new Throwable("stack trace"));
            }
            return cmd;
        } else {
            return new DeleteInDiagramCommand().setNodetoDelete(gmChild);
        }
        
    }

    @objid ("ac7ae576-fcf2-480e-bf99-f2f4e834f34c")
    protected Command getReorderChildCommand(GmNodeModel gmChild, int i) {
        return new ReorderChildCommand(getModel(), gmChild, i);
    }

    @objid ("3b89dd9e-9304-46ef-9e09-0790a7400829")
    protected static class ReorderChildCommand extends Command {
        @objid ("75e5dc58-8929-4c5d-8832-035eeef69268")
        protected final int i;

        @objid ("3856ee6e-5b20-4bf5-82a8-d1d7f34ec82e")
        protected final GmCompositeNode group;

        @objid ("a68e2731-018d-410f-8348-1696f32e74f3")
        protected final GmNodeModel gmChild;

        @objid ("6b11e792-fc2e-4c6a-8a41-7b31f9c113ad")
        public  ReorderChildCommand(GmCompositeNode group, GmNodeModel gmChild, int i) {
            this.group = group;
            this.gmChild = gmChild;
            this.i = i;
            
        }

        @objid ("a61788a1-9561-4d9a-b636-1e7d975f9e53")
        @Override
        public void execute() {
            this.group.moveChild(this.gmChild, this.i);
        }

    }

}
