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

package org.modelio.uml.compositediagram.editor.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("b39b9dd9-7221-4040-bcd1-3c6844de956a")
public class CompositeDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("05ad19b8-ab82-41f3-abea-11659d59ef6d")
    public static final String ID = "org.modelio.uml.compositediagram.editor.CompositeDiagramEditorID";

    @objid ("8f917e1d-bcee-4596-9ce0-c9c8a0369541")
    private static final String POPUP_ID = "org.modelio.uml.compositediagram.editor.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("817b186f-37f3-4044-b9fc-7cc4ce5b80db")
    public CompositeDiagramEditor() {
        super();
    }

    @objid ("cb899238-65e0-402f-a21a-bc92888ffd55")
    @Override
    protected String getPopupId() {
        return CompositeDiagramEditor.POPUP_ID;
    }

}
