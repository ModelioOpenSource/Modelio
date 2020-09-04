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

package org.modelio.diagram.editor.deployment.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.AbstractDiagramEditor;

/**
 * Graphical Editor for Diagrams.
 */
@objid ("970e28dd-55b6-11e2-877f-002564c97630")
public class DeploymentDiagramEditor extends AbstractDiagramEditor {
    /**
     * public ID of this editor.
     */
    @objid ("970e28e1-55b6-11e2-877f-002564c97630")
    public static final String ID = "org.modelio.diagram.editor.deployment.DeploymentDiagramEditorID";

    @objid ("400964e8-8d32-4993-8c38-ed78ee7ff557")
    private static final String POPUP_ID = "org.modelio.diagram.editor.deployment.menu.popupmenu";

    /**
     * C'tor.
     */
    @objid ("970e28e7-55b6-11e2-877f-002564c97630")
    public DeploymentDiagramEditor() {
        super();
    }

    @objid ("3a229f64-1ada-4d96-923c-a35471bda262")
    @Override
    protected String getPopupId() {
        return DeploymentDiagramEditor.POPUP_ID;
    }

}
