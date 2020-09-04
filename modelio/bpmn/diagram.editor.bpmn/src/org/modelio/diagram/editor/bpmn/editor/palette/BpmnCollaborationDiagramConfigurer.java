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

package org.modelio.diagram.editor.bpmn.editor.palette;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.bpmn.editor.BpmnDiagramEditor;
import org.modelio.diagram.editor.plugin.AbstractDiagramConfigurer;
import org.modelio.diagram.editor.plugin.ToolRegistry;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;

/**
 * Configures the BPMN collaboration diagram palette.
 */
@objid ("622a1ed4-55b6-11e2-877f-002564c97630")
public class BpmnCollaborationDiagramConfigurer extends AbstractDiagramConfigurer {
    @objid ("8de98ff3-f716-412e-b082-1b71f0eb0e31")
    private static final String COMPLETEPALETTE_ID = "org.modelio.diagram.editor.bpmn.BpmnCollaborationDiagram.PaletteID";

    @objid ("046ca6fa-5344-4fc4-abeb-f8e50f99c138")
    private static final String SIMPLEPALETTE_ID = "org.modelio.diagram.editor.bpmn.BpmnCollaborationDiagram2.PaletteID";

    @objid ("5c8430fd-d054-438d-b476-f4912bc99c80")
    private boolean isCompletePalette = true;

    @objid ("622ba56a-55b6-11e2-877f-002564c97630")
    @Override
    public String getPaletteId() {
        return this.isCompletePalette ? BpmnCollaborationDiagramConfigurer.COMPLETEPALETTE_ID : BpmnCollaborationDiagramConfigurer.SIMPLEPALETTE_ID;
    }

    @objid ("3b475a5d-77bf-4728-af4e-0f103e042f21")
    @Override
    public String getContributionURI() {
        return BpmnDiagramEditor.ID;
    }

    @objid ("c8c5a58c-ff02-4972-b924-e2e35a80a923")
    @Override
    public PaletteRoot initPalette(AbstractDiagramEditor diagram, ToolRegistry toolRegistry) {
        // Only Collaborations owned by a process have a complete palette
        BpmnCollaboration collaboration = (BpmnCollaboration) diagram.getEditorInput().getDiagram().getOrigin();
        this.isCompletePalette = collaboration.getDefinedProcess() != null;
        return readPaletteFromPlugin(diagram, toolRegistry);
    }

}
