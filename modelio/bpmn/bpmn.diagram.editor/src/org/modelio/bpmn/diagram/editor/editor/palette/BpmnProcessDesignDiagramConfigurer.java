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
 * Configures the BPMN process design diagram palette.
 */
@objid ("bb70283c-71d7-47fb-ac5a-7ff39aa3e771")
public class BpmnProcessDesignDiagramConfigurer extends AbstractDiagramConfigurer {
    @objid ("a272991c-0a22-403d-a67f-9f67d7f05cbf")
    private static final String PALETTE_ID = "org.modelio.bpmn.diagram.editor.BpmnProcessDesignDiagram.PaletteID";

    @objid ("da4acad8-20d3-45b7-ac7b-5bed489f2953")
    @Override
    public String getPaletteId() {
        return BpmnProcessDesignDiagramConfigurer.PALETTE_ID;
    }

    @objid ("06deae7f-7cd4-461e-99c8-a65db415a33c")
    @Override
    public String getContributionURI() {
        return BpmnDiagramEditor.ID;
    }

}
