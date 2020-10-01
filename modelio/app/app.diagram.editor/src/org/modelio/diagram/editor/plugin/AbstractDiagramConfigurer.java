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
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.diagram.editor.AbstractDiagramEditor;
import org.modelio.diagram.editor.tools.PanSelectionTool;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;

/**
 * Abstract diagram configurer that contains commonly used palette groups.
 * <p>
 * These palette groups can be used by sub classes.
 */
@objid ("0560ae14-ac88-4d66-a93b-04d8f35c2900")
public abstract class AbstractDiagramConfigurer implements IDiagramConfigurer {
    @objid ("e9264837-720f-4614-865d-b7889cba0469")
    private static final String PALETTEEXTENSION_ID = "org.modelio.app.diagram.editor.palette";

    /**
     * Default implementation read the palette from plugin.xml
     */
    @objid ("139596dc-9e9c-4006-8724-40ce62c16b4d")
    @Override
    public PaletteRoot initPalette(final AbstractDiagramEditor diagram, final ToolRegistry toolRegistry) {
        return readPaletteFromPlugin(diagram, toolRegistry);
    }

    /**
     * Creates a drawings palette group.
     * 
     * @param toolRegistry the tool registry
     * @return the created drawings palette group.
     */
    @objid ("9928bbe3-558d-490f-8b84-ad82dd1afda8")
    protected PaletteEntry createDrawGroup(final ToolRegistry toolRegistry) {
        final PaletteDrawer group = new PaletteDrawer(DiagramEditor.I18N.getMessage("PaletteGroup.Drawings"), null);
        group.add(toolRegistry.getTool(ToolRegistry.TOOL_CREATE_DRAWING_RECTANGLE));
        group.add(toolRegistry.getTool(ToolRegistry.TOOL_CREATE_DRAWING_ELLIPSE));
        group.add(toolRegistry.getTool(ToolRegistry.TOOL_CREATE_DRAWING_TEXT));
        //group.add(toolRegistry.getTool(ToolRegistry.TOOL_CREATE_DRAWING_POLYGON));
        group.add(toolRegistry.getTool(ToolRegistry.TOOL_CREATE_DRAWING_LINE));
        
        group.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
        return group;
    }

    @objid ("929fcd53-e213-4d8a-8f51-cdfdf946e98d")
    protected static ImageDescriptor getIconDescriptor(final IConfigurationElement element) {
        final String extendingPluginId = element.getDeclaringExtension().getContributor().getName();
        final String iconPath = element.getAttribute("icon");
        if (iconPath != null) {
            return AbstractUIPlugin.imageDescriptorFromPlugin(extendingPluginId, iconPath);
        }
        return null;
    }

    /**
     * Get the identifier of the palette to look for in the plugin.xml .
     * <p>
     * By default it is {@link #getContributionURI()}, may be redefined by sub classes.
     * 
     * @return the palette identifier.
     */
    @objid ("719927b7-86c6-4758-874f-d70e6c79a310")
    protected String getPaletteId() {
        return getContributionURI();
    }

    /**
     * Read the palette content from plugin.xml.
     * 
     * @param toolRegistry the tool registry
     * @return the created palette .
     */
    @objid ("b3861798-3002-44fa-ab8f-9c30b23301cf")
    protected PaletteRoot readPaletteFromPlugin(final AbstractDiagramEditor diagram, final ToolRegistry toolRegistry) {
        PaletteRoot paletteRoot = new PaletteRoot();
        paletteRoot = new PaletteRoot();
        
        for (final IConfigurationElement e : new ExtensionPointContributionManager(PALETTEEXTENSION_ID).getExtensions("palette")) {
            if (e.getAttribute("id").equals(getPaletteId())) {
                parsePaletteContainer(paletteRoot, e, toolRegistry, paletteRoot);
            }
        }
        return paletteRoot;
    }

    @objid ("bdf46987-86c0-4e41-ab48-ee1358e2a48b")
    private void parsePaletteContainer(final PaletteContainer container, final IConfigurationElement el, final ToolRegistry toolRegistry, final PaletteRoot paletteRoot) {
        for (final IConfigurationElement child : el.getChildren()) {
            parsePaletteEntry(container, toolRegistry, paletteRoot, child);
        }
    }

    @objid ("7a16daeb-e080-4651-84b2-25b7274aaddb")
    private void parsePaletteEntry(final PaletteContainer container, final ToolRegistry toolRegistry, final PaletteRoot paletteRoot, final IConfigurationElement child) {
        ToolEntry createdToolEntry = null;
        
        switch (child.getName()) {
        case "palette_group":
            if ("false".equalsIgnoreCase(child.getAttribute("collapsible"))) {
                final PaletteGroup group = new PaletteGroup(child.getAttribute("label"));
                group.setId(child.getAttribute("id"));
                container.add(group);
                parsePaletteContainer(group, child, toolRegistry,paletteRoot);
            } else {
                final PaletteDrawer group = new PaletteDrawer(
                        child.getAttribute("label"),
                        getIconDescriptor(child));
        
                group.setId(child.getAttribute("id"));
                container.add(group);
                parsePaletteContainer(group, child, toolRegistry,paletteRoot);
        
                final String initialState = child.getAttribute("initialState");
                if (initialState == null || initialState.equals("closed")) {
                    group.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
                } else if ("open".equalsIgnoreCase(initialState)) {
                    group.setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
                } else if ("pinned".equalsIgnoreCase(initialState)) {
                    group.setInitialState(PaletteDrawer.INITIAL_STATE_PINNED_OPEN);
                }
            }
            break;
        case "separator":
            final PaletteSeparator sep = new PaletteSeparator();
            sep.setId(child.getAttribute("id"));
            sep.setVisible(! Boolean.parseBoolean(child.getAttribute("hidden")));
            container.add(sep);
            break;
        case "creation_tool":
            final String toolid = child.getAttribute("id");
            final ToolEntry tool = toolRegistry.getTool(toolid);
            container.add(tool);
            createdToolEntry = tool;
            break;
        case "selection_tool":
            final SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
            selectionToolEntry.setToolClass(PanSelectionTool.class);
            container.add(selectionToolEntry);
            createdToolEntry = selectionToolEntry;
            break;
        case "marquee_tool":
            final MarqueeToolEntry entry = new MarqueeToolEntry();
            entry.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, true);
            container.add(entry);
            createdToolEntry = entry;
            break;
        case "universal_link_tool":
            final ToolEntry ltool = toolRegistry.getTool(ToolRegistry.TOOL_POPUPMENU_CREATELINK);
            container.add(ltool);
            createdToolEntry = ltool;
            break;
        default:
            DiagramEditor.LOG.warning("AbstractDiagramConfigurer: Unknown '%s' palette entry for '%s' contributor.", child.getName(), child.getContributor().getName());
        }
        
        if (createdToolEntry != null && "true".equalsIgnoreCase(child.getAttribute("default"))) {
            paletteRoot.setDefaultEntry(createdToolEntry);
        }
    }

}
