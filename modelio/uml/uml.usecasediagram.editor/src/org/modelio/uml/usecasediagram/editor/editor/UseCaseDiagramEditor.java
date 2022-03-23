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
package org.modelio.uml.usecasediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

@objid ("5e303f9c-55b7-11e2-877f-002564c97630")
public class UseCaseDiagramEditor extends AbstractDiagramEditor {
    @objid ("5e303fa0-55b7-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.uml.usecasediagram.editor.UseCaseDiagramEditorID";

    @objid ("e5b650fd-425d-4772-ac89-e72eca9578cf")
    private static final String POPUP_ID = "org.modelio.uml.usecasediagram.editor.menu.popupmenu";

    @objid ("5e31c5fc-55b7-11e2-877f-002564c97630")
    public  UseCaseDiagramEditor() {
        super();
    }

    @objid ("3cf7e238-041d-4bda-9e60-8324c5623742")
    @Override
    protected String getPopupId() {
        return UseCaseDiagramEditor.POPUP_ID;
    }

}
