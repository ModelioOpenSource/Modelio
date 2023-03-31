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
package org.modelio.platform.model.ui.popupmenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.MCoreExpression;
import org.eclipse.e4.ui.model.application.ui.MUiFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.gproject.core.IGProject;
import org.modelio.platform.core.events.ModelioEventTopics;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.utils.i18n.BundledMessages;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Abstract class used to fill a dynamic element creation menu from an XML configuration file.
 * <p>
 * Relies on the {@link AboutToShow} injection annotation from Eclipse 4.3 to show {@link MHandledMenuItem} when an element is selected.
 * </p>
 * <p>
 * Several methods must be redefined by extending classes.
 * </p>
 */
@objid ("dcef8550-3115-4897-a5d6-5e4f90647664")
public abstract class AbstractPopupProvider {
    @objid ("a30ed536-541e-4a39-8fb4-24de89e559c2")
    @Inject
    protected MApplication application;

    /**
     * A map containing all valid creation popup configurations according to the selected metaclass.
     * <p>
     * N.B. storing MMenuElement instead of CreationPopupEntryDescriptor might seem like a good idea, but they can't be displayed anymore after the first popup show... Something in the platform is probably disposed, making a new instantiation mandatory.
     * </p>
     * .
     */
    @objid ("67098f84-ac7a-4222-91cf-64dc8b5a423f")
    private Map<String, List<IPopupEntryDescriptor>> popupEntries = null;

    /**
     * A local MCommand cache, to minimize navigation in the e4 model.
     */
    @objid ("82d22b7b-fcaa-4755-a88e-993ed08f8ad1")
    private final Map<String, MCommand> commandCache = new HashMap<>();

    /**
     * Fills a dynamic creation menu with selection-compatible contributions before display. <br/>
     * Called by the rcp platform through injection.
     * @param items the item list to fill.
     */
    @objid ("d483d62b-e85c-4d95-a1f7-565806980dd9")
    @AboutToShow
    private void onPopupShow(final List<MMenuElement> items) {
        final String contributorId = getContributorId();
        
        // Initialize all possible contents if needed.
        if (this.popupEntries == null) {
            loadPopupEntries();
        }
        
        // Add the creation menu with selection-compatible commands
        final List<IPopupEntryDescriptor> entries = getPopupEntries(getSelectedElement());
        if (!entries.isEmpty()) {
            // create the menu itself
            final MMenu menu = MMenuFactory.INSTANCE.createMenu();
            menu.setLabel(getMenuLabel());
            menu.setIconURI(getMenuIconPath());
            menu.setContributorURI(contributorId);
        
            // make the menu visible
            menu.setEnabled(true);
            menu.setToBeRendered(true);
            menu.setVisible(true);
        
            final MCoreExpression isVisibleWhenExpression = MUiFactory.INSTANCE.createCoreExpression();
            isVisibleWhenExpression.setCoreExpressionId("app.project.ui.expression.isMObject");
            isVisibleWhenExpression.setContributorURI(contributorId);
        
            menu.setVisibleWhen(isVisibleWhenExpression);
        
            // bound the menu to the contributing plugin
            menu.setContributorURI(contributorId);
        
            // add menu items
            menu.getChildren().addAll(createMenuItems(entries, contributorId));
        
            // bind the new menu to the popup
            items.add(menu);
        }
        
    }

    /**
     * When the project closes, clean the popup entries cache.
     */
    @objid ("394f20f3-0e0e-48c8-b319-7821722f7cc9")
    @Optional
    @Inject
    private void onProjectClosing(@SuppressWarnings ("unused")
    @UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final IGProject project) {
        this.popupEntries = null;
    }

    /**
     * Create a new handled menu items from popup entry descriptors.
     * @param entries the descriptors to convert.
     * @return a list of menu elements.
     */
    @objid ("3016eea0-5a53-4111-a99d-2f25d7270ba7")
    private List<MMenuElement> createMenuItems(final List<IPopupEntryDescriptor> entries, final String contributorId) {
        final List<MMenuElement> items = new ArrayList<>();
        for (final IPopupEntryDescriptor entry : entries) {
            if (entry instanceof CreationPopupEntryDescriptor) {
                items.add(createMenuItem((CreationPopupEntryDescriptor) entry, contributorId));
            } else if (entry instanceof SeparatorPopupEntryDescriptor) {
                items.add(createMenuSeparator(contributorId));
            } else {
                CoreUi.LOG.debug("Unsupported popup entry ignored : " + entry);
            }
        }
        return items;
    }

    /**
     * Create a new handled menu item from a popup entry descriptor.
     * @param entry the descriptor to convert.
     * @return a new menu elements.
     */
    @objid ("8cb9abe8-47a9-4b70-a29a-596dbbcf6236")
    private MHandledMenuItem createMenuItem(final CreationPopupEntryDescriptor entry, final String contributorId) {
        // create a new handled item
        final MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        final MCommand command = getCommand(entry.commandId);
        item.setCommand(command);
        
        // compute the element id
        final String sourceMetaclass = entry.sourceMetaclass;
        final String dependency = entry.parameters.getProperty("dependency", "");
        final String targetMetaclass = entry.parameters.getProperty("metaclass", "");
        final String targetStereotype = entry.parameters.getProperty("stereotype", "");
        String i18nKey = entry.parameters.getProperty("i18nKey", "");
        if (i18nKey.isEmpty()) {
            // Build a key with simple metaclasses instead of qualified metaclasses
            i18nKey = computeI18nKey(sourceMetaclass.replaceAll("^[a-zA-Z]+\\.", ""), dependency, targetMetaclass.replaceAll("^[a-zA-Z]+\\.", ""), targetStereotype);
        }
        item.setElementId(sourceMetaclass + dependency + targetMetaclass + targetStereotype);
        
        // compute label, tooltip and icon
        final BundledMessages i18nBundle = getI18nBundle();
        item.setLabel(i18nBundle.getString(i18nKey + ".label"));
        item.setTooltip(i18nBundle.getString(i18nKey + ".tooltip"));
        
        final String baseIcon = i18nBundle.getString(i18nKey + ".icon");
        if (!baseIcon.contains("!")) {
            item.setIconURI(MetamodelImageService.getIconCompletePath(baseIcon));
        }
        
        // make the item visible
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bound the item to the contributing plugin
        item.setContributorURI(contributorId);
        
        // add creation parameters
        for (final Entry<Object, Object> param : entry.parameters.entrySet()) {
            final MParameter p = MCommandsFactory.INSTANCE.createParameter();
            p.setName((String) param.getKey());
            p.setValue((String) param.getValue());
            p.setContributorURI(contributorId);
            item.getParameters().add(p);
        }
        return item;
    }

    /**
     * Create a new menu separator item.
     * @return a new menu elements.
     */
    @objid ("d267e39a-dab5-4f60-991d-1d8d907b03c3")
    private MMenuSeparator createMenuSeparator(final String contributorId) {
        final MMenuSeparator item = MMenuFactory.INSTANCE.createMenuSeparator();
        // make the item visible
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bound the item to the contributing plugin
        item.setContributorURI(contributorId);
        return item;
    }

    @objid ("f106142f-abe2-4782-8494-99e048d83e27")
    private List<IPopupEntryDescriptor> getPopupEntries(final MObject obj) {
        if (obj == null) {
            return Collections.emptyList();
        }
        
        final MClass mclass = obj.getMClass();
        
        final List<IPopupEntryDescriptor> entries = new ArrayList<>();
        
        // Find commands with short metaclass name
        List<IPopupEntryDescriptor> cmds = this.popupEntries.get(mclass.getName());
        if (cmds != null) {
            entries.addAll(cmds);
        }
        
        // Find commands with qualified metaclass name
        cmds = this.popupEntries.get(mclass.getQualifiedName());
        if (cmds != null) {
            entries.addAll(cmds);
        }
        
        // Filter disabled metaclasses
        MMetamodel metamodel = mclass.getMetamodel();
        final List<IPopupEntryDescriptor> ret = new ArrayList<>();
        for (IPopupEntryDescriptor entry : entries) {
            if (entry instanceof CreationPopupEntryDescriptor) {
                CreationPopupEntryDescriptor creationEntry = (CreationPopupEntryDescriptor) entry;
                final String targetMetaclass = creationEntry.parameters.getProperty("metaclass", "");
                MClass mClass = metamodel.getMClass(targetMetaclass);
                if (mClass == null || !mClass.isEnabled()) {
                    // Skip creation command for disabled metaclasses
                    continue;
                }
            }
            ret.add(entry);
        }
        return ret;
    }

    /**
     * Get the MCommand defined in the application having a specific id.
     * @param commandId the element id of the MCommand to find.
     * @return a MCommand, or <code>null</code> if the id is not found.
     */
    @objid ("a84f0840-dbe1-4a2d-8b8b-cfb6952fb1c7")
    private MCommand getCommand(final String commandId) {
        // Try the cache first...
        MCommand command = this.commandCache.get(commandId);
        if (command == null) {
            // Not in the cache, look into the application commands
            for (final MCommand c : this.application.getCommands()) {
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

    /**
     * @return the contributor id from the bundle providing this popup.
     */
    @objid ("b5a49c4f-2300-45cc-ab5a-01f6e8c5f20d")
    private String getContributorId() {
        return "platform:/plugin/" + getBundle().getSymbolicName();
    }

    /**
     * Initialize all possible contents from the xml file.
     * @see #getXmlPath()
     */
    @objid ("62f5d181-87ee-4506-beb6-5b3d98a291b6")
    private void loadPopupEntries() {
        final Bundle bundle = getBundle();
        final URL url = FileLocator.find(bundle, getXmlPath(), null);
        
        final CreationPopupXmlLoader loader = new CreationPopupXmlLoader();
        
        this.popupEntries = loader.parseCreationPopupEntries(url);
        
    }

    /**
     * Get the XML file defining the popup menu's contents.
     * @return a relative path in the plugin, like <code>/res/create-popups.xml<code>.
     */
    @objid ("fd37cc63-bd1e-447b-9c93-c234d3559c83")
    protected abstract IPath getXmlPath();

    /**
     * @return a fully qualified path to the icon for the menu itself. e.g. <i>platform:/plugin/org.modelio.app.diagram.editor/icons/uml.png</i>
     */
    @objid ("9c3bfa10-67aa-43ad-900f-b2c10c89f2e4")
    protected abstract String getMenuIconPath();

    /**
     * @return an i18n label for the menu itself.
     */
    @objid ("11a488d0-f436-4155-9321-6c0cdc357b61")
    protected abstract String getMenuLabel();

    /**
     * Get the bundle defining the new creation popup menu.
     * @return An installed bundle in the Framework.
     */
    @objid ("955cc815-5982-4fb0-97eb-c8b2865b4194")
    protected abstract Bundle getBundle();

    /**
     * Get the i18n bundle containing all keys referenced by the current popup menu.
     * @return an i18n bundle.
     */
    @objid ("8b30b399-b0e7-4117-9e52-0dd07865baff")
    protected abstract BundledMessages getI18nBundle();

    /**
     * Compute the i18n key for a menu entry.
     * <p>
     * Default value is <code>"$" + sourceMetaclass + dependency + targetMetaclass + targetStereotype</code>.
     * </p>
     */
    @objid ("a55834f2-5479-4e06-ac05-10cf92923531")
    protected String computeI18nKey(final String sourceMetaclass, final String dependency, final String targetMetaclass, final String targetStereotype) {
        return "$" + sourceMetaclass + dependency + targetMetaclass + targetStereotype;
    }

    /**
     * Get the currently selected element, or <code>null</code> if the selection size is not equal to one.
     * @return the selected element.
     */
    @objid ("e8f6a37a-27bf-47df-bbc9-91b8796d625e")
    protected final MObject getSelectedElement() {
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several
        // times...
        final IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(IServiceConstants.ACTIVE_SELECTION);
        if (selection == null || selection.size() != 1) {
            return null;
        }
        
        final Object obj = selection.getFirstElement();
        if (obj instanceof MObject) {
            return (MObject) obj;
        } else if (obj instanceof IAdaptable) {
            final IAdaptable adaptable = (IAdaptable) obj;
            return adaptable.getAdapter(MObject.class);
        }
        return null;
    }

}
