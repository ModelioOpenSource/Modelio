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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.modelio.diagram.elements.common.portcontainer.SatelliteDragEditPartsTracker;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.IDragTrackerProvider;
import org.modelio.diagram.elements.core.policies.FireNavigateEditPartTracker;

/**
 * Provides a specific {@link ContentAsSatelliteDragEditPartsTracker} that allows some reparenting (where the standard
 * {@link SatelliteDragEditPartsTracker} never allows reparenting).
 * 
 * @author fpoyer
 */
@objid ("36175ad9-55b7-11e2-877f-002564c97630")
public class ContentAsSatelliteDragTrackerProvider implements IDragTrackerProvider {
    @objid ("622a973c-5bd5-11e2-9e33-00137282c51b")
    private final EditPart editPart;

    /**
     * Constructor.
     * 
     * @param editPart the EditPart for which DragTracker are to be returned.
     */
    @objid ("36175ade-55b7-11e2-877f-002564c97630")
    public ContentAsSatelliteDragTrackerProvider(final EditPart editPart) {
        this.editPart = editPart;
    }

    /**
     * Returns a Drag Tracker.
     * 
     * @param request current request for which a Drag Tracker is needed.
     * @return a DragTracker.
     */
    @objid ("36175ae3-55b7-11e2-877f-002564c97630")
    @Override
    public DragTracker getDragTracker(Request request) {
        if (isModelUserEditable()) {
            return new ContentAsSatelliteDragEditPartsTracker(this.editPart);
        } else {
            return new FireNavigateEditPartTracker(this.editPart);
        }
    }

    @objid ("07187003-56a1-412b-97a7-8aa01e8dc4ef")
    protected boolean isModelUserEditable() {
        IGmObject m = (IGmObject) this.editPart.getModel();
        return m.isUserEditable();
    }

}
