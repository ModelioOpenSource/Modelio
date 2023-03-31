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
    @objid ("3ce46b43-00c4-4f73-8cf9-c28134229a46")
    private final boolean ordered;

    @objid ("a8d91b0b-9b7d-47bd-8b4e-d291d96c1588")
    private final Function<MObject, List<? extends MObject>> expectedChildren;

    /**
     * Calls {@link #GroupRefreshFromModelEditPolicy(Function, boolean) GroupRefreshFromModelEditPolicy(Function, true)}.
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @deprecated Use {@link #GroupRefreshFromModelEditPolicy(Function, boolean)}
     */
    @objid ("971583ea-5ebe-4581-aa91-5f8ecb013857")
    @Deprecated
    public  GroupRefreshFromModelEditPolicy(Function<MObject, List<? extends MObject>> expectedChildren) {
        this(expectedChildren, true);
    }

    /**
     * @param expectedChildren a function that return the model elements that must be displayed, in order
     * @param ordered whether the Gm order must be synchronized from the Ob model
     */
    @objid ("f8978e1f-e729-4aa6-931a-303190e57784")
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
    @objid ("1e3a37b4-f884-48eb-adda-1d76339fc4d2")
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
        
        if (false) {
            // Add layout links command
            LayoutChildrenNodeConnectionsHelper.forRequest(null)
            .addEditPart((GraphicalEditPart) getHost())
            .removeEditParts(deletedEp)
            .createCommands(command);
        }
        return command.unwrap();
    }

    @objid ("9d9db7a0-47eb-4e3c-b3f7-a5c1f7d85dd1")
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

    @objid ("e0207dc5-a262-4b35-9696-8034b0abbdf7")
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

    @objid ("3b8d47c6-79b1-4722-b16e-ecc526305544")
    protected Command getReorderChildCommand(GmNodeModel gmChild, int i) {
        return new ReorderChildCommand(getModel(), gmChild, i);
    }

    @objid ("f664306e-863f-4dde-b767-c52c1353def5")
    protected static class ReorderChildCommand extends Command {
        @objid ("832b248a-7781-44ec-a7ba-46325ff17d8a")
        protected final int i;

        @objid ("100cb82c-7517-401a-8d4a-51bdf3867b22")
        protected final GmGroup group;

        @objid ("204ccd80-53ef-4a48-a128-e112d00325b8")
        protected final GmNodeModel gmChild;

        @objid ("07c122f4-e745-436a-82ef-86ce762b1c1a")
        public  ReorderChildCommand(GmGroup group, GmNodeModel gmChild, int i) {
            this.group = group;
            this.gmChild = gmChild;
            this.i = i;
            
        }

        @objid ("a8b17323-53d2-4f20-a4f9-cd243ea5af5a")
        @Override
        public void execute() {
            this.group.moveChild(this.gmChild, this.i);
        }

    }

}
