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

package org.modelio.diagram.editor.activity.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("296a3a1a-55b6-11e2-877f-002564c97630")
public class ActivityDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("296d475b-55b6-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.diagram.editor.activity.ActivityDiagramEditorID";

    @objid ("ca6b72be-ae21-415e-afdf-82cb1083e6b5")
    private static final String POPUP_ID = "org.modelio.diagram.editor.activity.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("296d4766-55b6-11e2-877f-002564c97630")
    public ActivityDiagramEditor() {
        super();
    }

    @objid ("40679590-d911-4b51-abd5-623c05b6e33e")
    @Override
    protected String getPopupId() {
        return ActivityDiagramEditor.POPUP_ID;
    }

}
