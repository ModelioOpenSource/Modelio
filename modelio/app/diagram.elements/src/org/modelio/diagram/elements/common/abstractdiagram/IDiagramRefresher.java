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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Display;
import org.modelio.vcore.session.api.model.change.ChangeCause;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IPersistentViewModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

/**
 * <code>IDiagramRefresher</code> groups two interfaces relative to model change events:
 * {@link IModelChangeListener} and {@link IPersistentViewModelChangeListener}.
 * The interface also defines the {@link #visibilityChanged(boolean)} method.
 * <p>
 * The <code>IDiagramRefresher</code> is used by view (like diagrams) that need to update
 * their contents in case of model change and to save some persistent data in the model (typical for diagrams, the serialization string)
 * <p>
 * Implementers should define:
 * <ul>
 * <li>{@link #modelChanged(org.modelio.vcore.session.api.model.change.IModelChangeEvent)} to refresh the contents
 * if there is NO modification to make on the model (persistence)</li>
 * <li>{@link #updateView(org.modelio.vcore.session.api.model.change.IModelChangeEvent)} to refresh the contents
 * when there are some modifications to make on the model (persistence) . <br>
 * These modifications CANNOT be structural: see {@link IPersistentViewModelChangeListener} for more informations</li>
 * <li>{@link #visibilityChanged(boolean)}) when they need to update their contents when the view becomes visible</li>
 * </ul>
 */
@objid ("7e2024c0-1dec-11e2-8cad-001ec947c8cc")
public interface IDiagramRefresher extends IModelChangeListener, IPersistentViewModelChangeListener, IStatusChangeListener {
    /**
     * This method is called when the diagram visibility (ie its part editor) changes. Default implementation does nothing. Typical use: a diagram that was not visible to the end user is outdated because it has been ignoring update events while being
     * hidden may require an udpate when being made visible to the end-user.
     */
    @objid ("2d557b3f-2108-4b81-89bf-d57f3aa74be7")
    void visibilityChanged(boolean visible);

    @objid ("053f215e-9527-4cc7-b9aa-75f59b33498e")
    @Override
    default void statusChanged(IStatusChangeEvent ev) {
        if (ev.getCause() == ChangeCause.REPOSITORY ) {
            // module may have been added/removed : all icons must be reloaded
            // don't filter on ev.getShellStateChanged().isEmpty(), it is often empty because elements are unloaded then reloaded to same state
            Display.getDefault().asyncExec(() -> visibilityChanged(true));
        }
    }

}
