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
@objid ("ec3a2341-50f1-4d75-81da-1327859c543a")
public class ResizableGroupRefreshFromModelEditPolicy extends DefaultRefreshFromModelEditPolicy {
    @objid ("7ff0d586-e111-4297-8a33-56d753e83057")
    private final boolean ordered;

    @objid ("745efa7f-920b-4d70-92fb-9f392529ebde")
    private final Function<MObject, List<? extends MObject>> expectedChildren;

    /**
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @param ordered if true, order of the graphic elements will be synchronized with the order returned by the function
     */
    @objid ("c37bbb3f-08c7-42f5-962d-3bb1a2e00752")
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
    @objid ("c62b64fd-bf3e-455e-b59e-b7bde2e38d8e")
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
    @objid ("4bae3dac-1a08-4980-8c2e-5751979fed57")
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
        LayoutChildrenNodeConnectionsHelper.forRequest(null)
        .addEditPart((GraphicalEditPart) getHost())
        .removeEditParts(deletedEp)
        .createCommands(command);
        return command.unwrap();
    }

    @objid ("a0d28cd2-55b7-4853-9f11-5444d1d7b5e2")
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

    @objid ("b4351bf7-18fa-4ce0-8550-ef85ad266d19")
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

    @objid ("55d8eb38-f617-4283-9b1b-412cd8a6b19c")
    protected Command getReorderChildCommand(GmNodeModel gmChild, int i) {
        return new ReorderChildCommand(getModel(), gmChild, i);
    }

    @objid ("57e331ac-1e50-4f02-8076-1ddb157bae0b")
    protected static class ReorderChildCommand extends Command {
        @objid ("0be8d722-3954-43ed-97e5-3b78e0204989")
        protected final int i;

        @objid ("156f3ae2-d466-482e-9d1f-0566eab5f868")
        protected final GmCompositeNode group;

        @objid ("75bb4a84-cd75-49d1-9c25-bde7da29a062")
        protected final GmNodeModel gmChild;

        @objid ("a47f1e11-650d-48fc-8ee0-80ff9258c941")
        public  ReorderChildCommand(GmCompositeNode group, GmNodeModel gmChild, int i) {
            this.group = group;
            this.gmChild = gmChild;
            this.i = i;
            
        }

        @objid ("430446d2-d37e-4df0-9c02-0675266372eb")
        @Override
        public void execute() {
            this.group.moveChild(this.gmChild, this.i);
        }

    }

}
