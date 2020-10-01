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

package org.modelio.uml.statikdiagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("33d1feba-55b7-11e2-877f-002564c97630")
public class StaticDiagramEditor extends AbstractDiagramEditor {
    /**
     * Id of the static diagram editor for plugins.xml .
     */
    @objid ("33d1febe-55b7-11e2-877f-002564c97630")
    public static String ID = "org.modelio.uml.statikdiagram.editor.StatikDiagramEditorID";

    @objid ("4b4af4cf-2cad-4b9f-a31b-7cc61f9fac09")
    private static final String POPUP_ID = "org.modelio.uml.statikdiagram.editor.menu.popupmenu";

    @objid ("5dc2d7b5-48d3-43ec-8062-cfdc2fd57a8a")
    @Override
    protected String getPopupId() {
        return StaticDiagramEditor.POPUP_ID;
    }

}
