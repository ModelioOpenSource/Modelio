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

package org.modelio.uml.objectdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("9d5d2671-55b6-11e2-877f-002564c97630")
public class ObjectDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("9d5d2675-55b6-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.uml.objectdiagram.editor.ObjectDiagramEditorID";

    @objid ("96680763-d554-4bcd-85d1-6d5fa8e4fbe9")
    private static final String POPUP_ID = "org.modelio.uml.objectdiagram.editor.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("9d5d267b-55b6-11e2-877f-002564c97630")
    public ObjectDiagramEditor() {
        super();
    }

    @objid ("9bf3f494-ff28-4b07-8f29-bf02f06474cc")
    @Override
    protected String getPopupId() {
        return ObjectDiagramEditor.POPUP_ID;
    }

}
