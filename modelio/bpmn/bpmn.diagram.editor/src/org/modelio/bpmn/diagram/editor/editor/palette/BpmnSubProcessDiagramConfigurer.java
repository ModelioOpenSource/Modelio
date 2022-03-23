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
package org.modelio.bpmn.diagram.editor.editor.palette;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.editor.BpmnDiagramEditor;
import org.modelio.diagram.editor.plugin.AbstractDiagramConfigurer;

/**
 * BPMN SubProcess diagram configuration.
 */
@objid ("622d2c02-55b6-11e2-877f-002564c97630")
public class BpmnSubProcessDiagramConfigurer extends AbstractDiagramConfigurer {
    @objid ("a729f449-e764-41b0-a84d-318e6e8f7d38")
    private static final String PALETTE_ID = "org.modelio.bpmn.diagram.editor.BpmnSubProcessDiagram.PaletteID";

    @objid ("622d2c14-55b6-11e2-877f-002564c97630")
    @Override
    public String getContributionURI() {
        return BpmnDiagramEditor.ID;
    }

    @objid ("bacd148e-3f18-47fd-b85f-afa1c7829d59")
    @Override
    protected String getPaletteId() {
        // we have 2 configurations for BPMN diagrams
        return BpmnSubProcessDiagramConfigurer.PALETTE_ID;
    }

}
