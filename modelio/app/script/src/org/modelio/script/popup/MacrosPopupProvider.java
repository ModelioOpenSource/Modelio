/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.script.popup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.script.handlers.RunMacroHandler;
import org.modelio.script.macro.IMacroService;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.plugin.Script;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Class used to fill a dynamic macro menu from the catalog.
 * <p>Relies on the {@link AboutToShow} injection annotation from Eclipse 4.3 to show {@link MHandledMenuItem} when an element is selected.</p>
 */
@objid ("1e87b808-ca52-4897-aabb-0df0552b6601")
public class MacrosPopupProvider {
    @objid ("dd81ed54-41f3-4c12-bdc9-434c36bd01da")
    @Inject
    private MApplication application;

    @objid ("273f9592-3828-45d4-bf01-d291ea02556d")
    @Inject
    private IMacroService macroService;

    /**
     * Fills a dynamic creation menu with selection-compatible contributions before display.
     * <br/>
     * Called by the rcp platform through injection.
     * @param items the item list to fill.
     */
    @objid ("a3221459-605b-4db4-a5bd-b42306e609b5")
    @AboutToShow
    public void aboutToShow(List<MMenuElement> items) {
        // Fill creation menu with selection-compatible commands
        items.addAll(createMenuItems(this.macroService.getMacros(getSelectedElements())));
    }

    /**
     * Clean up the dynamic items, as they are not kept between two openings.
     * @param items
     */
    @objid ("d1a4c9ec-6cc0-4f50-bd4f-8b4a5fa2e910")
    @AboutToHide
    public void aboutToHide(List<MMenuElement> items) {
        // Clean up contributions...
        if (items != null) {
            for (MMenuElement item : items) {
                if (item.getParent() != null)
                    item.getParent().getChildren().remove(item);
            }
        }
    }

    /**
     * Compute a contributor id from a bundle.
     * @return a contributor id.
     */
    @objid ("ed31edc4-c5b6-424e-960f-79db001b7ead")
    private String getContributorId(Bundle bundle) {
        return "platform:/plugin/" + bundle.getSymbolicName();
    }

    /**
     * Create new handled menu items from a list of macros.
     * <p>Only macros shown in the contextual menu are returned.</p>
     * @param entries the macros to build menu elements from.
     * @return a list of menu elements.
     */
    @objid ("9b4b8d3b-2e9b-4b4c-bbce-51c678ebf07c")
    private List<MMenuElement> createMenuItems(List<Macro> entries) {
        List<MMenuElement> items = new ArrayList<>();
        for (Macro entry : entries) {
            if (entry.shownInContextualMenu()) {
                items.add(createMenuItem(entry));
            }
        }
        return items;
    }

    /**
     * Create a new handled menu item from a macro.
     * @param entry the macro to create the menu item from.
     * @return a new menu element.
     */
    @objid ("c5f0d257-9544-4c43-86fb-ae3f328b1499")
    private MMenuElement createMenuItem(Macro entry) {
        // create a new handled item
        MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        MCommand command = getCommand();
        item.setCommand(command);
        
        // compute the element id
        item.setElementId(entry.getName());
        
        // compute label, tooltip and icon
        item.setLabel(entry.getName());
        item.setTooltip(entry.getDescription());
        if (entry.getIconPath() != null) {
            item.setIconURI(entry.getIconPath().toUri().toString());
        }
        
        // make the item visible
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bound the item to the contributing plugin
        item.setContributorURI(getContributorId(Script.getContext().getBundle()));
        
        // add file parameter
        MParameter p = MCommandsFactory.INSTANCE.createParameter();
        p.setName(RunMacroHandler.RUNMACRO_FILE);
        p.setValue(entry.getScriptPath().toString());
        item.getParameters().add(p);
        return item;
    }

    /**
     * Get the MCommand defined in the application having a specific id.
     * @param commandId the element id of the MCommand to find.
     * @return a MCommand, or <code>null</code> if the id is not found.
     */
    @objid ("982cd6c1-7f0d-4fc9-8277-21f4d1c4bf5d")
    private MCommand getCommand() {
        // look into the application commands
        for (MCommand c : this.application.getCommands()) {
            if (RunMacroHandler.RUNMACRO.equals(c.getElementId())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the currently selected elements, or an empty collection.
     * @return the selected elements.
     */
    @objid ("b975b2f0-cbc0-471f-b9d8-002af672eaad")
    private Collection<MObject> getSelectedElements() {
        Collection<MObject> ret = new ArrayList<>();
        
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several times...
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        if (selection != null) {
            for (Object obj : selection.toList()) {
                if (obj instanceof MObject) {
                    ret.add((MObject) obj);
                } else if (obj instanceof IAdaptable) {
                    IAdaptable adaptable = (IAdaptable) obj;
                    final MObject adapter = adaptable.getAdapter(MObject.class);
                    if (adapter != null) {
                        ret.add(adapter);
                    }
                }
            }
        }
        return ret;
    }

}
