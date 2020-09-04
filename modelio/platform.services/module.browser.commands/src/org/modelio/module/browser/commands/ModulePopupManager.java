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

package org.modelio.module.browser.commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.expressions.Expression;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.eclipse.e4.ui.model.application.ui.MCoreExpression;
import org.eclipse.e4.ui.model.application.ui.MUiFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.mda.infra.service.IRTModule;
import org.modelio.module.commands.ExecuteModuleActionHandler;
import org.modelio.module.commands.IsVisibleExpression;
import org.modelio.module.commands.ModuleCommandsRegistry;

@objid ("899a2d17-160f-4633-8e11-793b29a46a3c")
public class ModulePopupManager {
    @objid ("cbd39129-531f-451c-a98a-a6395ed28810")
    private static final String DYNAMIC_MODULE_MENU_TAG = "dynamicModuleMenu";

    @objid ("491bee9e-12dd-11e2-8549-001ec947c8cc")
    private static void createMenuEntriesForAction(IRTModule module, MMenu moduleMenu, MPart view) {
        // Map used to prevent slot multiplication
        Map<String, MMenu> slotMap = new HashMap<>();
        
        // ask module for its IModuleAction that should be inserted into the
        // contextual menu and for each of them
        for (IModuleAction action : module.getActions(ActionLocation.contextualpopup)) {
            // Create the MHandler and MHandledItem for this action.
        
            // MCommand
            MCommand command = ModuleCommandsRegistry.getCommand(module, action);
        
            // MHandler
            final MHandler handler = createAndActivateHandler(command, module, action, view);
        
            // MHandledItem
            MHandledMenuItem item = createAndInsertItem(moduleMenu, action, slotMap);
            // Bind to command
            item.setCommand(command);
        
            Expression visWhen = new IsVisibleExpression(handler.getObject(), item);
            MCoreExpression isVisibleWhenExpression = MUiFactory.INSTANCE.createCoreExpression();
            isVisibleWhenExpression.setCoreExpressionId("programmatic.value");
            isVisibleWhenExpression.setCoreExpression(visWhen);
        
            item.setVisibleWhen(isVisibleWhenExpression);
        }
    }

    @objid ("491beea2-12dd-11e2-8549-001ec947c8cc")
    private static MHandledMenuItem createItem(String label, String tooltip, String iconURI) {
        // create a new item
        MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        // item.setElementId(module.getName() + commandId);
        item.setLabel(label);
        item.setTooltip(tooltip);
        item.setIconURI(iconURI);
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(true);
        return item;
    }

    @objid ("9a6323b8-12ea-11e2-8549-001ec947c8cc")
    private static void deactivateAndRemoveHandler(MCommand command, MPart view) {
        MHandler handlerToRemove = null;
        for (MHandler handler : view.getHandlers()) {
            if (handler.getCommand() == command) {
                handlerToRemove = handler;
                break;
            }
        }
        if (handlerToRemove != null) {
            view.getHandlers().remove(handlerToRemove);
            // Note: this might be a little over-the-top, but better safe than
            // sorry.
            handlerToRemove.setCommand(null);
            handlerToRemove.setObject(null);
        }
    }

    @objid ("9a6323bb-12ea-11e2-8549-001ec947c8cc")
    private static MHandledMenuItem createAndInsertItem(MMenu moduleMenu, IModuleAction action, Map<String, MMenu> slotMap) {
        Path bitmapPath = action.getBitmapPath();
        String iconURI = bitmapPath != null ? bitmapPath.toUri().toString() : "";
        MHandledMenuItem item = createItem(action.getLabel(), action.getTooltip(), iconURI);
        
        List<String> slots = action.getSlots();
        if (!slots.isEmpty()) {
            // Default owner is the module menu
            MMenu ownerMenu = moduleMenu;
            MMenu subMenu = moduleMenu;
        
            String slotCompleteName = "";
            for (String slotSimpleName : slots) {
                slotCompleteName += (slotCompleteName.isEmpty() ? "" : "|") + slotSimpleName;
        
                // Get slot from map
                subMenu = slotMap.get(slotCompleteName);
        
                // Lazy creation
                if (subMenu == null) {
                    subMenu = MMenuFactory.INSTANCE.createMenu();
                    subMenu.setLabel(slotSimpleName);
                    subMenu.setEnabled(true);
                    subMenu.setToBeRendered(true);
                    subMenu.setVisible(true);
                    bitmapPath = action.getSlotImagePath(slots.indexOf(slotSimpleName));
                    iconURI = bitmapPath != null ? bitmapPath.toUri().toString() : "";
                    subMenu.setIconURI(iconURI);
                    slotMap.put(slotCompleteName, subMenu);
                    ownerMenu.getChildren().add(subMenu);
                }
        
                ownerMenu = subMenu;
            }
            subMenu.getChildren().add(item);
        } else {
            // No slot, add the action to the module menu
            moduleMenu.getChildren().add(item);
        }
        return item;
    }

    @objid ("9a6323cb-12ea-11e2-8549-001ec947c8cc")
    private static MHandler createAndActivateHandler(MCommand command, IRTModule module, IModuleAction action, MPart view) {
        // Instantiate the actual handler class
        Object handler = new ExecuteModuleActionHandler(module, action);
        // Fit it into a MHandler
        MHandler mHandler = MCommandsFactory.INSTANCE.createHandler();
        mHandler.setObject(handler);
        // mHandler.setElementId(command.getElementId().replace("command",
        // "handler"));
        // Bind it to the corresponding command
        mHandler.setCommand(command);
        // Define scope of this handler as the browser view.
        view.getHandlers().add(mHandler);
        return mHandler;
    }

    @objid ("c42238e7-1d14-11e2-9c7e-bc305ba4815c")
    private static String getModuleIconeURI(IRTModule module) {
        String relativePath = module.getModuleImagePath();
        if (relativePath != null && !relativePath.isEmpty()) {
            final Path moduleDirectory = module.getConfiguration().getModuleResourcesPath();
            Path imageFile = moduleDirectory.resolve(relativePath.substring(1));
        
            if (Files.isRegularFile(imageFile)) {
                return imageFile.toUri().toString();
            }
        }
        return null;
    }

    @objid ("fff3543b-6152-4665-9552-d57a3790f997")
    public static MMenuElement createMenu(final IRTModule module, MPart view) {
        // create menu entry for this module (with tag so it can be cleaned
        // later).
        MMenu moduleMenu = MMenuFactory.INSTANCE.createMenu();
        moduleMenu.setEnabled(true);
        moduleMenu.setToBeRendered(true);
        moduleMenu.setVisible(true);
        moduleMenu.setLabel(module.getLabel());
        moduleMenu.setIconURI(getModuleIconeURI(module));
        moduleMenu.getTags().add(DYNAMIC_MODULE_MENU_TAG);
        moduleMenu.getTags().add(module.getName());
        // Create command, handler and corresponding menu entry for each
        // IModuleAction provided by the module with
        // location == ActionLocation.contextualpopup
        createMenuEntriesForAction(module, moduleMenu, view);
        // If at least one command added, add the module to the main popup.
        if (!moduleMenu.getChildren().isEmpty()) {
            return moduleMenu;
        }
        return null;
    }

    @objid ("8b62147f-883f-4514-ab5d-bf553972a3fc")
    public static void removeMenu(final IRTModule module, MPart view) {
        // Unbind and remove handlers for each command.
        for (IModuleAction action : module.getActions(ActionLocation.contextualpopup)) {
            deactivateAndRemoveHandler(ModuleCommandsRegistry.getCommand(module, action), view);
        }
    }

}
