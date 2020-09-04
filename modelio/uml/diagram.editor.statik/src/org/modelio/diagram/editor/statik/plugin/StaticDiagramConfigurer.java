/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.plugin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.plugin.IDiagramConfigurer;
import org.modelio.diagram.editor.plugin.ToolRegistry;
import org.modelio.diagram.editor.statik.editor.StaticDiagramEditor;

/**
 * Configures the Static diagram palette.
 */
@objid ("36f007d2-55b7-11e2-877f-002564c97630")
public class StaticDiagramConfigurer implements IDiagramConfigurer {
    @objid ("36f18e3a-55b7-11e2-877f-002564c97630")
    @Override
    public String getContributionURI() {
        return StaticDiagramEditor.ID;
    }

    @objid ("36f18e3f-55b7-11e2-877f-002564c97630")
    @Override
    public PaletteRoot initPalette(final AbstractDiagramEditor diagram, final ToolRegistry toolRegistry) {
        PaletteRoot paletteRoot = new PaletteRoot();
        paletteRoot.add(createCommonGroup(toolRegistry));
        return paletteRoot;
    }

    @objid ("36f18e4c-55b7-11e2-877f-002564c97630")
    private PaletteEntry createCommonGroup(final ToolRegistry toolRegistry) {
        final PaletteDrawer commonGroup = new PaletteDrawer(DiagramEditorStatik.I18N.getMessage("StatikPaletteGroup.Common"), null);
        commonGroup.add(new SelectionToolEntry());
        
        MarqueeToolEntry entry = new MarqueeToolEntry();
        entry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, true);
        commonGroup.add(entry);
        
        commonGroup.add(toolRegistry.getTool(IDiagramService.TOOL_POPUPMENU_CREATELINK));
        
        commonGroup.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        return commonGroup;
    }

}
