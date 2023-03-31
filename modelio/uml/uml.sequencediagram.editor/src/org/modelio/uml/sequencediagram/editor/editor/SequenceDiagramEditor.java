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
package org.modelio.uml.sequencediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.core.link.ConnectionRoutingServices;

@objid ("d8bd584a-55b6-11e2-877f-002564c97630")
public class SequenceDiagramEditor extends AbstractDiagramEditor {
    @objid ("d8bd584e-55b6-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.uml.sequencediagram.editor.SequenceDiagramEditorID";

    @objid ("88b93d76-61b6-4386-9a11-eb613608571a")
    private static final String POPUP_ID = "org.modelio.uml.sequencediagram.editor.menu.popupmenu";

    @objid ("162bae9e-1ae4-48f3-9d0f-afe764f0d480")
    @Override
    protected String getPopupId() {
        return SequenceDiagramEditor.POPUP_ID;
    }

    @objid ("1dda4d3d-a9c0-4dc1-9561-f34c227ba099")
    @Override
    protected ConnectionRoutingServices initializeConnectionRoutingServices() {
        return ConnectionRoutingServices.builder()
                .withLegacyDefaults()
                .build();
        
    }

}
