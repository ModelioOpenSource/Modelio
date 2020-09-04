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

package org.modelio.diagram.editor.bpmn.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("6072aaba-55b6-11e2-877f-002564c97630")
public class BpmnDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("6072aabe-55b6-11e2-877f-002564c97630")
    public static String ID = "org.modelio.diagram.editor.bpmn.BpmnDiagramEditorID";

    @objid ("943eaacc-0056-4429-9b2f-f308ea61b66b")
    private static final String POPUP_ID = "org.modelio.diagram.editor.bpmn.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("6072aac3-55b6-11e2-877f-002564c97630")
    public BpmnDiagramEditor() {
        super();
    }

    @objid ("1d7529a1-9618-43c7-95ca-3794f1cb416b")
    @Override
    protected String getPopupId() {
        return BpmnDiagramEditor.POPUP_ID;
    }

    @objid ("90719756-b628-4a1d-9839-a3796fee3030")
    @Override
    public void initialize() {
        super.initialize();
        
        // Force refresh to trigger Auto Unmask
        ((GmAbstractDiagram) getEditorInput().getGmDiagram()).refreshFromObModel();
    }

}
