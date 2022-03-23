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
package org.modelio.diagram.elements.core.policies;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.commands.DeleteInDiagramCommand;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmModelRelated;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Edit policy that listen for {@link IGmModelRelated#PROP_REFRESH_FROM_OBMODEL} and
 * {@link IGmModelRelated#PROP_OBMODEL_DELETED} property change events.
 * to refresh a {@link GmModel} content from the model, by using requests and commands.
 * <p>
 * GmModel may fire {@link IGmModelRelated#PROP_REFRESH_FROM_OBMODEL} property change event in its {@link GmModel#refreshFromObModel()} method.
 * <p>
 * <h2>Implemetation directives</h2>
 * When executing commands do not use the command stack, it would use a transaction and potentially break the undo/redo ,
 * call {@link Command#execute()} directly.
 * @author cma
 * @since 5.1.0
 */
@objid ("cf3e7d15-a30b-4ee5-af19-6740d675321f")
public abstract class AbstractRefreshFromModelEditPolicy extends GraphicalEditPolicy implements PropertyChangeListener {
    /**
     * The role to use for this edit policy
     */
    @objid ("41159117-658a-404d-af5d-07ffd3371eae")
    public static final Object ROLE = "Refresh from Ob model";

    @objid ("a8d0f5eb-46fc-441e-a7f0-85335041a7ec")
    @Override
    public void activate() {
        super.activate();
        
        IGmModelRelated grp = getModel();
        grp.addPropertyChangeListener(this);
        
    }

    @objid ("2fbaf8b5-c8e1-4013-8224-e38e53352b75")
    @Override
    public void deactivate() {
        IGmModelRelated grp = getModel();
        grp.removePropertyChangeListener(this);
        
        super.deactivate();
        
    }

    @objid ("4ac42e48-8398-4c7e-9625-65d2849ee97f")
    @SuppressWarnings ("unchecked")
    protected <T extends IGmModelRelated> T getModel() {
        return (T) getHost().getModel();
    }

    /**
     * Handles {@link IGmModelRelated#PROP_REFRESH_FROM_OBMODEL} and {@link IGmModelRelated#PROP_OBMODEL_DELETED} property change events
     * to refresh a {@link IGmModelRelated} content from related model element, by using requests and commands.
     * 
     * <h2>Implementation directives</h2>
     * 
     * When executing commands do not use the command stack: it would use a transaction and potentially break the undo/redo.
     * Call {@link Command#execute()} directly.
     */
    @objid ("32da470f-853c-486f-a92e-07b8586eaec5")
    @Override
    public void propertyChange(PropertyChangeEvent ev) {
        String propertyName = ev.getPropertyName();
        if (IGmModelRelated.PROP_REFRESH_FROM_OBMODEL.equals(propertyName)) {
            MObject el = getModel().getRelatedElement();
            if (el != null && el.isValid()) {
                Command cmd = getRefreshFromModelCommand(ev);
        
                // Warning: do not use the command stack, it would use a transaction and potentially break the undo/redo
                if (cmd != null && cmd.canExecute())
                    cmd.execute();
            }
        } else if (IGmModelRelated.PROP_OBMODEL_DELETED.equals(propertyName)) {
            MObject el = getModel().getRelatedElement();
            if (el != null) {
                Command cmd = getObElementDeletedCommand(ev);
        
                // Warning: do not use the command stack, it would use a transaction and potentially break the undo/redo
                if (cmd != null && cmd.canExecute())
                    cmd.execute();
            }
        }
        
    }

    /**
     * Create a command to run when the related model element is deleted.
     * <p>
     * Default implementation builds a REQ_DELETE request and return the matching command.
     * @param ev the property change event, in case it contains useful informations
     * @return a command to run when the related model element is deleted.
     */
    @objid ("85d654c4-9af0-4212-aa15-55f9ab4122e6")
    protected Command getObElementDeletedCommand(PropertyChangeEvent ev) {
        GroupRequest req = new GroupRequest(RequestConstants.REQ_DELETE);
        EditPart host = getHost();
        req.setEditParts(host);
        Command command = host.getCommand(req);
        
        if (command != null)
            return command;
        
        // No delete policy: Try to ask its parent
        EditPart parent = host.getParent();
        if (parent == null) {
            assert false : String.format("Delete request not handled on orphan %s .", host);
            return null;
        }
        
        req.setType(REQ_DELETE_DEPENDANT);
        command = parent.getCommand(req);
        
        if (command != null)
            return command;
        
        // Last resort, we shouldn't get here
        assert false : String.format("Delete request handled neither on %s nor its parent %s", host, parent);
        
        command = new DeleteInDiagramCommand().setNodetoDelete(getModel());
        return command;
    }

    /**
     * Get a command to run to refresh the graphic model from the object model.
     * <p>
     * Called when a {@link IGmModelRelated#PROP_REFRESH_FROM_OBMODEL}  property change event is caught.
     * Does nothing by default.
     * @param ev the property change event, in case it contains useful informations
     * @return a command or null.
     */
    @objid ("459e5657-d119-4a27-a2b5-35e462b3730f")
    protected abstract Command getRefreshFromModelCommand(PropertyChangeEvent ev);

}
