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

package org.modelio.diagram.editor.communication.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("7a1be19b-55b6-11e2-877f-002564c97630")
public class CommunicationDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("7a1be19f-55b6-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.diagram.editor.communication.CommunicationDiagramEditorID";

    @objid ("5d1f1b00-17e2-4f64-8a73-c3330f3aa776")
    private static final String POPUP_ID = "org.modelio.diagram.editor.communication.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("7a1be1a5-55b6-11e2-877f-002564c97630")
    public CommunicationDiagramEditor() {
        super();
    }

    @objid ("073e1144-4e5d-45bb-a42b-ecfc072430b8")
    @Override
    protected String getPopupId() {
        return CommunicationDiagramEditor.POPUP_ID;
    }

}
