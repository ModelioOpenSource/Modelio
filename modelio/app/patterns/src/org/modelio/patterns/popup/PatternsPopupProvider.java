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

package org.modelio.patterns.popup;

import java.net.URL;
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
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.patterns.api.IPatternService;
import org.modelio.patterns.apply.ApplyPatternHandler;
import org.modelio.patterns.model.CategoryData;
import org.modelio.patterns.model.RuntimePattern;
import org.modelio.patterns.model.information.Pattern;
import org.modelio.patterns.plugin.Patterns;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

/**
 * Class used to fill a dynamic pattern menu from the catalog.
 * <p>
 * Relies on the {@link AboutToShow} injection annotation from Eclipse 4.3 to show {@link MHandledMenuItem} when an element is
 * selected.
 * </p>
 */
@objid ("48e1a5ce-8fff-46ca-ba9d-4df713ad87ac")
public class PatternsPopupProvider {
    @objid ("674c77da-764c-489e-92e1-f506b442e2a1")
    @Inject
    private MApplication application;

    @objid ("231d8dbb-7f8c-4c30-b2e9-31b32ac39671")
    @Inject
    private IPatternService patternService;

    /**
     * Fills a dynamic creation menu with selection-compatible contributions before display. <br/>
     * Called by the rcp platform through injection.
     * 
     * @param items the item list to fill.
     */
    @objid ("e7b6b372-e14b-41f0-8430-16fecb137cc1")
    @AboutToShow
    public void aboutToShow(final List<MMenuElement> items) {
        // Fill creation menu with selection-compatible commands
        final String contributorId = getContributorId(Patterns.getContext().getBundle());
        items.addAll(createCategoryMenus(this.patternService.getCatalog().getCategories(getSelectedElements()), contributorId));
        
        if (!items.isEmpty()) {
            final MMenuSeparator separator = MMenuFactory.INSTANCE.createMenuSeparator();
            separator.setContributorURI(contributorId);
            items.add(separator);
        }
    }

    /**
     * Clean up the dynamic items, as they are not kept between two openings.
     */
    @objid ("c36931ee-417f-4ede-b5e8-4fe408a944a1")
    @AboutToHide
    public void aboutToHide(final List<MMenuElement> items) {
        // Clean up contributions...
        if (items != null) {
            for (final MMenuElement item : items) {
                final MElementContainer<MUIElement> parent = item.getParent();
                if (parent != null) {
                    parent.getChildren().remove(item);
                }
            }
        }
    }

    /**
     * Compute a contributor id from a bundle.
     * 
     * @return a contributor id.
     */
    @objid ("1e4a5d82-e5d7-4117-a29c-f7775e7c5f00")
    private String getContributorId(final Bundle bundle) {
        return "platform:/plugin/" + bundle.getSymbolicName();
    }

    /**
     * Create new handled menu items from patterns referenced by a category.
     * <p>
     * Only categories containing at least one pattern shown in the contextual menu are returned.
     * </p>
     * @param entries
     * the category to build menu elements from.
     * 
     * @return a list of menu elements.
     */
    @objid ("48ec3068-f416-48af-83a6-aae49efcb9b2")
    private List<MMenuElement> createPatternMenuItems(final Collection<RuntimePattern> patterns, final String contributorId) {
        final List<MMenuElement> items = new ArrayList<>();
        for (final RuntimePattern pattern : patterns) {
            items.add(createPatternMenuItem(pattern, contributorId));
        }
        return items;
    }

    /**
     * Create a new handled menu item from a pattern.
     * @param entry
     * the pattern to create the menu item from.
     * 
     * @return a new menu element.
     */
    @objid ("0fb9d932-3195-4b50-9e5d-e7a0b6996294")
    private MMenuElement createPatternMenuItem(final RuntimePattern pattern, final String contributorId) {
        final Pattern infos = pattern.getInfos();
        
        // create a new handled item
        final MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        final MCommand command = getCommand();
        item.setCommand(command);
        
        // compute the element id
        item.setElementId(infos.getName());
        
        // compute label, tooltip and icon
        item.setLabel(infos.getName());
        item.setTooltip(infos.getDescription());
        if (pattern.getIconPath() != null) {
            item.setIconURI(pattern.getIconPath().toString());
        }
        
        // make the item visible
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        
        // bound the item to the contributing plugin
        item.setContributorURI(contributorId);
        
        // add file parameter
        final MParameter p = MCommandsFactory.INSTANCE.createParameter();
        p.setContributorURI(contributorId);
        p.setName(ApplyPatternHandler.APPLY_PATTERN_NAME);
        p.setValue(infos.getName());
        item.getParameters().add(p);
        return item;
    }

    /**
     * Get the MCommand defined in the application having a specific id.
     * @param commandId
     * the element id of the MCommand to find.
     * 
     * @return a MCommand, or <code>null</code> if the id is not found.
     */
    @objid ("926b9c84-18ca-4c2a-be4c-63f7586bb9aa")
    private MCommand getCommand() {
        // look into the application commands
        for (final MCommand c : this.application.getCommands()) {
            if (ApplyPatternHandler.APPLY_PATTERN.equals(c.getElementId())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get the currently selected elements, or an empty collection.
     * 
     * @return the selected elements.
     */
    @objid ("b551c786-e666-498b-bff0-81cb00778897")
    private Collection<MObject> getSelectedElements() {
        final Collection<MObject> ret = new ArrayList<>();
        
        // Get the active selection from the application, to avoid context-related issues when opening the same diagram several
        // times...
        final IStructuredSelection selection = (IStructuredSelection) this.application.getContext().get(
                IServiceConstants.ACTIVE_SELECTION);
        if (selection != null) {
            for (final Object obj : selection.toList()) {
                if (obj instanceof MObject) {
                    ret.add((MObject) obj);
                } else if (obj instanceof IAdaptable) {
                    final IAdaptable adaptable = (IAdaptable) obj;
                    final MObject adapter = adaptable.getAdapter(MObject.class);
                    if (adapter != null) {
                        ret.add(adapter);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Create new menus from a list of categories.
     * <p>
     * Only categories containing at least one pattern shown in the contextual menu are inserted in the menu.
     * </p>
     * 
     * @param categories the categories to build menu elements from.
     * @return a list of menu elements.
     */
    @objid ("d1593272-c35c-4c43-ab25-3606d20594dc")
    private List<MMenuElement> createCategoryMenus(final Collection<CategoryData> categories, final String contributorId) {
        final List<MMenuElement> items = new ArrayList<>();
        for (final CategoryData category : categories) {
            if (!category.getPatterns().isEmpty()) {
                if (!category.getName().isEmpty() && !"Default".equals(category.getName())) {
                    // Create pattern menu items in a category menu
                    final MMenu categoryMenu = createCategoryMenu(category, contributorId);
        
                    final List<MMenuElement> patternItems = createPatternMenuItems(category.getPatterns(), contributorId);
                    categoryMenu.getChildren().addAll(patternItems);
        
                    items.add(categoryMenu);
                } else {
                    // Create pattern menu items directly
                    final List<MMenuElement> patternItems = createPatternMenuItems(category.getPatterns(), contributorId);
                    items.addAll(patternItems);
                }
            }
        }
        return items;
    }

    /**
     * Create a new menu item from a category.
     * @param entry
     * the pattern to create the menu item from.
     * 
     * @return a new menu element.
     */
    @objid ("dc954bd1-7149-4e70-a508-5efa871409ab")
    private MMenu createCategoryMenu(final CategoryData category, final String contributorId) {
        // create a new handled item
        final MMenu menu = MMenuFactory.INSTANCE.createMenu();
        
        // compute the element id
        menu.setElementId(category.getName());
        
        // compute label, tooltip and icon
        menu.setLabel(category.getName());
        URL catIconUrl = category.getIconUrl();
        if (catIconUrl != null) {
            menu.setIconURI(category.getIconUrl().toString());
        }
        
        // make the item visible
        menu.setEnabled(true);
        menu.setToBeRendered(true);
        menu.setVisible(true);
        
        // bound the item to the contributing plugin
        menu.setContributorURI(contributorId);
        return menu;
    }

}
