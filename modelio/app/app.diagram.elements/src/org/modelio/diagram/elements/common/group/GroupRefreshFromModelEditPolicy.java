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
package org.modelio.diagram.elements.common.group;

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
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.policies.DefaultRefreshFromModelEditPolicy;
import org.modelio.diagram.elements.core.policies.LayoutChildrenNodeConnectionsHelper;
import org.modelio.diagram.elements.core.requests.RequestProperty;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that waits for {@link GmGroup#PROP_REFRESH_FROM_OBMODEL} property change event
 * to refresh a {@link GmGroup} content from the model, by using requests and commands.
 * <p>
 * This will allow a GmGroup to better autosize when its content change, depending on on other edit policies
 * installed on the {@link GroupEditPart}.
 * <p>
 * GmGroup fires {@link GmGroup#PROP_REFRESH_FROM_OBMODEL} property change event in its {@link GmGroup#refreshFromObModel()} method.
 * 
 * @author cma
 * @since 3.7, remade in 5.1.0 to handle connections layout
 * @see org.modelio.diagram.elements.core.policies.AutoFitToContentEditPolicy
 * @version 3.7 : initial implementation modified the gm model directly when possible
 * @version 5.1.0 : remade to handle connections layout, aggregates plenty commands then execute them
 */
@objid ("0f11e132-ba7a-4791-9c97-f508b55190ca")
public class GroupRefreshFromModelEditPolicy extends DefaultRefreshFromModelEditPolicy {
    @objid ("148b2456-aede-4332-bfea-a00f8f4cd853")
    private final boolean ordered;

    @objid ("75d9afdb-d75e-4142-b8cc-143037e3ff62")
    private final Function<MObject, List<? extends MObject>> expectedChildren;

    /**
     * Calls {@link #GroupRefreshFromModelEditPolicy(Function, boolean) GroupRefreshFromModelEditPolicy(Function, true)}.
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @deprecated Use {@link #GroupRefreshFromModelEditPolicy(Function, boolean)}
     */
    @objid ("13057b14-0a99-4bed-993f-d7f5fe722060")
    @Deprecated
    public  GroupRefreshFromModelEditPolicy(Function<MObject, List<? extends MObject>> expectedChildren) {
        this(expectedChildren, true);
    }

    /**
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @param ordered whether the Gm order must be synchronized from the Ob model
     */
    @objid ("9a2c8055-6cb8-4cee-8760-6839fbc02533")
    public  GroupRefreshFromModelEditPolicy(Function<MObject, List<? extends MObject>> expectedChildren, boolean ordered) {
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
    @objid ("86517e8c-7fd9-452d-8184-bb24c4b3834f")
    protected final Command getRefreshCommand() {
        final GmGroup gmGroup = getModel();
        
        List<? extends MObject> obChildren = this.expectedChildren.apply(gmGroup.getRelatedElement());
        if (obChildren == null) {
            // Abort refresh
            return null;
        }
        
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

    @objid ("fbdeaa73-c0b9-44c8-a386-2a070cb7c55c")
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

    @objid ("41d615dc-21e6-4d2a-ac6f-a0fcd759fa4f")
    private Command getRemoveChildCommand(GmNodeModel gmChild, Map<Object, EditPart> editPartRegistry) {
        EditPart ep = editPartRegistry.get(gmChild);
        if (ep != null) {
            GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
            deleteReq.setEditParts(ep);
            Command cmd = ep.getCommand(deleteReq);
            if (cmd == null || ! cmd.canExecute()) {
                DiagramElements.LOG.debug("%s: Unable to mask %s under %s, command = %s", getClass().getSimpleName(), gmChild, getHost(), cmd);
                DiagramElements.LOG.debug(new Throwable("stack trace"));
            }
            return cmd;
        } else {
            return new DeleteInDiagramCommand().setNodetoDelete(gmChild);
        }
        
    }

    @objid ("131b8474-1ef0-4d31-b0b3-f7c7359cf9cf")
    protected Command getReorderChildCommand(GmNodeModel gmChild, int i) {
        return new ReorderChildCommand(getModel(), gmChild, i);
    }

    @objid ("a7641aee-bf57-4eec-a34c-bebbd4b5ce2c")
    protected static class ReorderChildCommand extends Command {
        @objid ("74b958ed-1eee-410d-9d93-db3bb134775f")
        protected final int i;

        @objid ("286f466c-274a-407b-aea2-c8f23cfec947")
        protected final GmGroup group;

        @objid ("5e6fc550-a837-4bda-b905-806dfdf79983")
        protected final GmNodeModel gmChild;

        @objid ("c5d674c5-0b53-467b-9aee-fa0efc354a2f")
        public  ReorderChildCommand(GmGroup group, GmNodeModel gmChild, int i) {
            this.group = group;
            this.gmChild = gmChild;
            this.i = i;
            
        }

        @objid ("e11351d3-4aac-4abc-aa3f-321013469566")
        @Override
        public void execute() {
            this.group.moveChild(this.gmChild, this.i);
        }

    }

}
