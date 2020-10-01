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

package org.modelio.diagram.editor.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteRoot;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;

@objid ("6670d454-33f7-11e2-95fe-001ec947c8cc")
public interface IDiagramConfigurer {
    @objid ("6670d45b-33f7-11e2-95fe-001ec947c8cc")
    String getContributionURI();

    /**
     * Fills the diagram palette
     * 
     * @param diagram the diagram editor
     * @param toolRegistry the tool registry where you can look for a tool from its identifier.
     * @return the created diagram palette.
     */
    @objid ("6670d45d-33f7-11e2-95fe-001ec947c8cc")
    PaletteRoot initPalette(final AbstractDiagramEditor diagram, final ToolRegistry toolRegistry);

    @objid ("638bc50f-6473-4e1d-9e49-097faf6566d1")
    default IDynamicStyler getDynamicStyler() {
        return null;
    }

}
