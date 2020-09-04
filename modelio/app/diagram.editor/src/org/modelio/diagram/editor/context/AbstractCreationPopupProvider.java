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

package org.modelio.diagram.editor.context;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.AboutToHide;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.utils.i18n.BundledMessages;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Abstract class used to fill a dynamic element creation menu from an XML configuration file.
 * <p>
 * Relies on the {@link AboutToShow} injection annotation from Eclipse 4.3 to show {@link MHandledMenuItem} when an element is selected.
 * </p>
 * <p>
 * {@link #getBundle()} and {@link #getI18nBundle()} methods must be redefined by extending classes.
 * </p>
 * <p>
 * {@link #getCreatePopupXmlFile()} can be redefined to specify a different XML configuration file. Default location is <b>bundle/res/diagram-create-popups.xml<b/>
 * </p>
 */
@objid ("768ee972-0927-4756-8e70-8a144f934e1b")
public abstract class AbstractCreationPopupProvider {
    @objid ("2a793aa6-0c31-4bfc-a5f3-b59dcb0b480f")
    @Inject
    protected MApplication application;

    /**
     * A map containing all valid creation popup configurations according to the selected metaclass.
     * <p>
     * N.B. storing MMenuElement instead of CreationPopupEntryDescriptor might seem like a good idea, but they can't be displayed anymore after the first popup show... Something in the platform is probably disposed, making a new instantiation mandatory.
     * </p>
     * .
     */
    @objid ("ee5518d5-91cb-451f-923e-78230faf7ce8")
    private Map<String, List<CreationPopupEntryDescriptor>> popupEntries = null;

    /**
     * A local MCommand cache, to minimize navigation in the e4 model. Most of the time, creation popup entries use "org.modelio.app.ui.command.create.element".
     */
    @objid ("b073cb7c-ade6-43d1-8846-87fd7ad637d0")
    private Map<String, MCommand> commandCache = new HashMap<>();

    /**
     * Get the bundle defining the new creation popup menu.
     * @return An installed bundle in the Framework.
     */
    @objid ("3324e43a-562f-474f-b878-74e2f7e86248")
    protected abstract Bundle getBundle();

    /**
     * Get the i18n bundle containing all keys referenced by the current popup menu.<br/>
     * Default location is often <b>bundle/res/diagram-create-popups.properties<b/>
     * </p>
     * @return an i18n bundle.
     */
    @objid ("e3486435-e49f-4588-a023-f2c5ac5eddf3")
    protected abstract BundledMessages getI18nBundle();

    @objid ("c6c47d6d-00a9-463b-a209-1ba2b4917c65")
    protected URL getCreatePopupXmlFile() {
        Bundle bundle = getBundle();
        IPath contextualMenuContent = new Path("/res/diagram-create-popups.xml");
        URL url = FileLocator.find(bundle, contextualMenuContent, null);
        return url;
    }

    /**
     * Fills a dynamic creation menu with selection-compatible contributions before display. <br/>
     * Called by the rcp platform through injection.
     * @param items the item list to fill.
     */
    @objid ("56be8b8e-9246-4ce0-a0d1-6ecb9a288226")
    @AboutToShow
    public void aboutToShow(List<MMenuElement> items) {
        // Initialize all possible contents if needed.
        if (this.popupEntries == null) {
            loadPopupEntries();
        }
        
        // Add the creation menu with selection-compatible commands
        List<CreationPopupEntryDescriptor> entries = getPopupEntries(getSelectedElement());
        if (!entries.isEmpty()) {
            // create a new handled item
            MMenu elementCreationMenu = MMenuFactory.INSTANCE.createMenu();
            elementCreationMenu.setLabel(getMenuLabel());
            elementCreationMenu.setIconURI(getMenuIconPath());
        
            // make the menu visible
            elementCreationMenu.setEnabled(true);
            elementCreationMenu.setToBeRendered(true);
            elementCreationMenu.setVisible(true);
        
            // bound the menu to the contributing plugin
            elementCreationMenu.setContributorURI(getContributorId(getBundle()));
        
            // add creation items
            elementCreationMenu.getChildren().addAll(createMenuItems(entries));
        
            // bind the new menu to the popup
            items.add(elementCreationMenu);
        
            // Add a separator
            MMenuSeparator separator = MMenuFactory.INSTANCE.createMenuSeparator();
            items.add(separator);
        }
    }

    @objid ("4d9645ee-6631-4a39-8107-33070308ce54")
    @AboutToHide
    public void aboutToHide(@SuppressWarnings ("unused") List<MMenuElement> items) {
        // Here, we could dispose things and so on...
    }

    /**
     * Get the currently selected element, or <code>null</code> if the selection size is not equal to one.
     * @return the selected element.
     */
    @objid ("35396dea-7520-42f8-849d-d2f3aa58f2f5")
    protected MObject getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several
        // times...
        IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(
                IServiceConstants.ACTIVE_SELECTION);
        if (selection.size() != 1) {
            return null;
        }
        
        final Object obj = selection.getFirstElement();
        if (obj instanceof MObject) {
            return (MObject) obj;
        } else if (obj instanceof IAdaptable) {
            IAdaptable adaptable = (IAdaptable) obj;
            return adaptable.getAdapter(MObject.class);
        }
        return null;
    }

    /**
     * Compute a contributor id from a bundle.
     * @return a contributor id.
     */
    @objid ("82f5952b-42d7-4fde-a955-9bfaa50107d6")
    private String getContributorId(Bundle bundle) {
        return "platform:/plugin/" + bundle.getSymbolicName();
    }

    /**
     * Initialize all possible contents from the xml file.
     * @see #getCreatePopupXmlFile()
     */
    @objid ("4d815172-dec8-4346-bdaf-7a394227e39d")
    private void loadPopupEntries() {
        URL url = getCreatePopupXmlFile();
        
        CreationPopupXmlLoader loader = new CreationPopupXmlLoader();
        
        this.popupEntries = loader.parseCreationPopupEntries(url);
    }

    @objid ("85b9d5b8-498c-4fb3-9d89-d1fb14b0fdac")
    private List<CreationPopupEntryDescriptor> getPopupEntries(MObject obj) {
        if (obj == null) {
            return Collections.emptyList();
        }
        
        MClass mclass = obj.getMClass();
        
        List<CreationPopupEntryDescriptor> validCommands = new ArrayList<>();
        
        // Find commands with short metaclass name
        List<CreationPopupEntryDescriptor> cmds = this.popupEntries.get(mclass.getName());
        if (cmds != null) {
            validCommands.addAll(cmds);
        }
        
        // Find commands with qualified metaclass name
        cmds = this.popupEntries.get(mclass.getQualifiedName());
        if (cmds != null) {
            validCommands.addAll(cmds);
        }
        return validCommands;
    }

    /**
     * Create a new handled menu items from popup entry descriptors.
     * @param entries the descriptors to convert.
     * @return a list of menu elements.
     */
    @objid ("49a47d0a-15e3-418f-8079-d024cc92db98")
    private List<MMenuElement> createMenuItems(List<CreationPopupEntryDescriptor> entries) {
        List<MMenuElement> items = new ArrayList<>();
        for (CreationPopupEntryDescriptor entry : entries) {
            items.add(createMenuItem(entry));
        }
        return items;
    }

    /**
     * Create a new handled menu item from a popup entry descriptor.
     * @param entry the descriptor to convert.
     * @return a new menu elements.
     */
    @objid ("2c2c4a13-0cd8-4f04-aa3f-d12c6651fb2a")
    private MHandledMenuItem createMenuItem(CreationPopupEntryDescriptor entry) {
        // create a new handled item
        MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        MCommand command = getCommand(entry.commandId);
        item.setCommand(command);
        
        // compute the element id
        String sourceMetaclass = entry.sourceMetaclass;
        String dependency = entry.parameters.getProperty("dependency", "");
        String targetMetaclass = entry.parameters.getProperty("metaclass", "");
        String targetStereotype = entry.parameters.getProperty("stereotype", "");
        String i18nKey = entry.parameters.getProperty("i18nKey", "");
        if (i18nKey.isEmpty()) {
            // Build a key with simple metaclasses instead of qualified metaclasses
            i18nKey = "$" + sourceMetaclass.replaceAll("^[a-zA-Z]+\\.", "") + dependency + targetMetaclass.replaceAll("^[a-zA-Z]+\\.", "") + targetStereotype;
        }
        item.setElementId(sourceMetaclass + dependency + targetMetaclass + targetStereotype);
        
        // compute label, tooltip and icon
        BundledMessages i18nBundle = getI18nBundle();
        item.setLabel(i18nBundle.getString(i18nKey + ".label"));
        item.setTooltip(i18nBundle.getString(i18nKey + ".tooltip"));
        
        String baseIcon = i18nBundle.getString(i18nKey + ".icon");
        if (!baseIcon.contains("!")) {
            item.setIconURI(MetamodelImageService.getIconCompletePath(baseIcon));
        }
        
        // make the item visible
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bound the item to the contributing plugin
        item.setContributorURI(getContributorId(getBundle()));
        
        // add creation parameters
        for (Entry<Object, Object> param : entry.parameters.entrySet()) {
            MParameter p = MCommandsFactory.INSTANCE.createParameter();
            p.setName((String) param.getKey());
            p.setValue((String) param.getValue());
            item.getParameters().add(p);
        }
        return item;
    }

    /**
     * Get the MCommand defined in the application having a specific id.
     * @param commandId the element id of the MCommand to find.
     * @return a MCommand, or <code>null</code> if the id is not found.
     */
    @objid ("232db0fa-83a7-4933-ac96-27231170bc3a")
    private MCommand getCommand(String commandId) {
        // Try the cache first...
        MCommand command = this.commandCache.get(commandId);
        if (command == null) {
            // Not in the cache, look into the application commands
            for (MCommand c : this.application.getCommands()) {
                if (commandId.equals(c.getElementId())) {
                    // Match, keep it in the cache...
                    command = c;
                    this.commandCache.put(commandId, command);
                    break;
                }
            }
        }
        return command;
    }

    @objid ("c879bed1-5a48-42c2-9d8c-fc7bfa78d0f2")
    protected String getMenuIconPath() {
        return "platform:/plugin/org.modelio.diagram.editor/icons/uml.png";
    }

    @objid ("5fcca183-4bf9-4fee-9bdf-d933304631de")
    protected String getMenuLabel() {
        return DiagramEditor.I18N.getString("CreateElementMenu.label");
    }

}
