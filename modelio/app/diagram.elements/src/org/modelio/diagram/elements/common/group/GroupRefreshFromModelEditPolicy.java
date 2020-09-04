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

package org.modelio.diagram.elements.common.group;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Edit policy that waits for {@link GmGroup#PROP_REFRESH_FROM_OBMODEL} property change event
 * to refresh a {@link GmGroup} content from the model, by using requests and commands.
 * <p>
 * This will allow a GmGroup to better autosize when its content change, depending on onther edit policies
 * installed on the {@link GroupEditPart}.
 * <p>
 * GmGroup does not currently fire {@link GmGroup#PROP_REFRESH_FROM_OBMODEL} property change event.
 * You have to fire it yourself from GmGroup subclass in your {@link GmGroup#refreshFromObModel()} redefinition.
 * 
 * @author cma
 * @since 3.7
 * @see org.modelio.diagram.elements.core.policies.AutoFitToContentEditPolicy
 */
@objid ("0f11e132-ba7a-4791-9c97-f508b55190ca")
public class GroupRefreshFromModelEditPolicy extends GraphicalEditPolicy implements PropertyChangeListener {
    @objid ("a8d91b0b-9b7d-47bd-8b4e-d291d96c1588")
    private final Function<MObject,List<? extends MObject>> expectedChildren;

    @objid ("971583ea-5ebe-4581-aa91-5f8ecb013857")
    public GroupRefreshFromModelEditPolicy(Function<MObject,List<? extends MObject>> expectedChildren) {
        super();
        this.expectedChildren = expectedChildren;
    }

    @objid ("ee7f5c02-2ccf-46fb-84d8-c79473739794")
    @Override
    public void activate() {
        super.activate();
        
        GmGroup grp = getModel();
        grp.addPropertyChangeListener(this);
    }

    @objid ("0a5bbf28-d119-43f6-8346-a94052b0c7f7")
    @Override
    public void deactivate() {
        GmGroup grp = getModel();
        grp.removePropertyChangeListener(this);
        
        super.deactivate();
    }

    @objid ("3c0f0d13-d3d8-4d93-9def-a9e3d429a8ca")
    private GmGroup getModel() {
        return (GmGroup) getHost().getModel();
    }

    @objid ("e6eccefd-58fe-4632-ae29-57ecc9c374ed")
    @Override
    public void propertyChange(PropertyChangeEvent ev) {
        if (GmModel.PROP_REFRESH_FROM_OBMODEL.equals(ev.getPropertyName())) {
            final GmGroup model = getModel();
            MObject el = model.getRelatedElement();
            if (el != null && el.isValid()) {
                refreshChildren();
            }
        }
    }

    /**
     * Updates the set of children GmNodeModel so that it is in sync with the
     * OB model children. This method is called from {@link #propertyChange()}. This method
     * requires linear time to complete. Clients should call this method as few
     * times as possible.
     * <P>
     * The update is performed by comparing the existing GmNodeModel with the set
     * of model children returned from {@link #expectedChildren}. GmNodeModel
     * whose models no longer exist are {@link #removeChild(GmNodeModel) removed}.
     * New models have their GmNodeModel {@link #createChild(MObject) created}.
     * <p>
     * @author Copied from {@link AbstractEditPart#refreshChildren()}
     */
    @objid ("bfb52940-c577-4914-8d51-4a0fbbd291f6")
    protected final void refreshChildren() {
        final GmGroup gmGroup = getModel();
        
        List<? extends MObject> obChildren = this.expectedChildren.apply(gmGroup.getRelatedElement());
        if (obChildren == null) {
            // Abort refresh
            return;
        }
        
        List<GmNodeModel> gmChildren = gmGroup.getChildren();
        int i;
        
        int size = gmChildren.size();
        Map<MObject, GmNodeModel> obToGm = Collections.EMPTY_MAP;
        if (size > 0) {
            obToGm = new HashMap<>(size);
            for (GmNodeModel gmChild : gmChildren) {
                obToGm.put(gmChild.getRelatedElement(), gmChild);
            }
        }
        
        for (i = 0; i < obChildren.size(); i++) {
            MObject model = obChildren.get(i);
        
            // Do a quick check to see if gmChildren[i] == obChildren[i]
            if (i < gmChildren.size()
                    && model.equals((gmChildren.get(i)).getRelatedElement())) {
                continue;
            }
        
            // Look to see if the GmNodeModel is already around but in the
            // wrong location
            GmNodeModel gmChild = obToGm.get(model);
        
            if (gmChild != null) {
                reorderChild(gmChild, i);
            } else {
                // An GmNodeModel for this model doesn't exist yet. Create and
                // insert one.
                gmChild = createChild(model);
                addChild(gmChild, i);
            }
        }
        
        // Remove the remaining GmNodeModel
        gmChildren = gmGroup.getChildren(); // Get new snapshot
        size = gmChildren.size();
        if (i < size) {
            for (GmNodeModel ep : gmChildren.subList(i, size)) {
                removeChild(ep);
            }
        }
    }

    @objid ("b7ac1cfe-fa5c-454c-8395-fb092ffd1f1f")
    private void addChild(GmNodeModel gmChild, int i) {
        getModel().moveChild(gmChild, i);
    }

    @objid ("41e84f02-7e55-4b9c-91f2-68e26764f868")
    private GmNodeModel createChild(MObject model) {
        // Create unmask request
        CreateRequest req = new CreateRequest();
        ModelioCreationContext ctx = new ModelioCreationContext(model);
        req.setFactory(ctx);
        
        Command cmd = getHost().getCommand(req);
        if (cmd != null && cmd.canExecute()) {
            // Warning: do not use the command stack, it would use a transaction and potentially break the undo/redo
            cmd.execute();
        
            // look for created GM
            final GmNodeModel createdChild = getModel().getChild(new MRef(model));
        
            assert (createdChild != null);
        
            return createdChild;
        } else {
            DiagramElements.LOG.debug("%s: Unable to unmask %s under %s, command = %s", getClass().getSimpleName(), model, getHost(), cmd);
            DiagramElements.LOG.debug(new Throwable("stack trace"));
        
            return null;
        }
    }

    @objid ("e8bbd475-f5ce-4d5b-b561-060377a8abb0")
    private void removeChild(GmNodeModel gmChild) {
        EditPart ep = (EditPart) getHost().getViewer().getEditPartRegistry().get(gmChild);
        if (ep != null) {
            GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
            deleteReq.setEditParts(ep);
            Command cmd = ep.getCommand(deleteReq);
            if (cmd != null && cmd.canExecute()) {
                // Warning: do not use the command stack, it would use a transaction and potentially break the undo/redo
                cmd.execute();
        
                assert (gmChild.getParent() == null);
            } else {
                DiagramElements.LOG.debug("%s: Unable to mask %s under %s, command = %s", getClass().getSimpleName(), gmChild, getHost(), cmd);
                DiagramElements.LOG.debug(new Throwable("stack trace"));
            }
        } else {
            gmChild.delete();
        }
    }

    @objid ("2700d476-40b7-492b-8040-5adeda724643")
    private void reorderChild(GmNodeModel gmChild, int i) {
        getModel().moveChild(gmChild, i);
    }

}
