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
package org.modelio.diagram.elements.core.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.policies.FireNavigateEditPartTracker;

/**
 * Default implementation of the {@link IDragTrackerProvider} specific to our EditParts
 * (used mainly to specify the cursors while reparenting).
 */
@objid ("8089a5ca-1dec-11e2-8cad-001ec947c8cc")
public class DefaultDragTrackerProvider implements IDragTrackerProvider {
    @objid ("cd0e4bea-4242-4390-8c2d-584e79d99216")
    private EditPart editPart;

    @objid ("8089a5cf-1dec-11e2-8cad-001ec947c8cc")
    public  DefaultDragTrackerProvider(EditPart editPart) {
        this.editPart = editPart;
    }

    @objid ("8089a5d4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public DragTracker getDragTracker(Request request) {
        if (isModelUserEditable()) {
            return new GmNodeDragTracker(this.editPart);
        } else {
            return new FireNavigateEditPartTracker(this.editPart);
        }
        
    }

    @objid ("32c58a39-fc7b-4a59-9cc9-4da9459949d8")
    protected boolean isModelUserEditable() {
        IGmObject m = (IGmObject) this.editPart.getModel();
        return m.isUserEditable();
    }

}
