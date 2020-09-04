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

package org.modelio.module.commands;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MHandler;
import org.modelio.api.module.command.ActionLocation;
import org.modelio.api.module.command.IModuleAction;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.mda.infra.service.IRTModule;

/**
 * This class "translates" IModuleActions provided by modules into MCommand (with a default MHandler, although it is recommended that each contribution point use more local handlers) of the e4 model. These commands and handlers are created and activated when the module is started, and deactivated and deleted when the module stops.
 */
@objid ("c582fed7-1441-11e2-a678-001ec947c8cc")
@Creatable
public class ModuleCommandsRegistry {
    @objid ("92384a3b-1444-11e2-a678-001ec947c8cc")
    private static final String MODULE_COMMAND_TAG = "command brought by a module";

    @objid ("dfcf3684-12a8-11e2-8b3b-001ec947c8cc")
    private static final String CONTRIBUTOR_ID = "platform:/plugin/org.modelio.module.commands";

    @objid ("491729d0-12dd-11e2-8549-001ec947c8cc")
    @Inject
    private static MApplication application;

    @objid ("dfc80f3f-12a8-11e2-8b3b-001ec947c8cc")
    private static Map<String, MCommand> commandCache = new HashMap<>();

    @objid ("4f5c55db-1449-11e2-a678-001ec947c8cc")
    private static Map<String, MHandler> defaultHandlersCache = new HashMap<>();

    @objid ("9222d4d7-1444-11e2-a678-001ec947c8cc")
    @Execute
    static void execute(IEclipseContext context) {
        // Create instance and put it in the context.
        context.set(ModuleCommandsRegistry.class, ContextInjectionFactory.make(ModuleCommandsRegistry.class, context));
    }

    /**
     * Returns the {@link MCommand} with the given ID, creating it in not found in the application.
     * 
     * @param commandId ID of the requested command.
     * @return the MCommand with the given ID.
     */
    @objid ("491beeaa-12dd-11e2-8549-001ec947c8cc")
    private static MCommand getCommand(String commandId) {
        // Search command in local cache
        MCommand command = commandCache.get(commandId);
        if (command == null) {
            // Command not found in local cache, search it in the application
            for (MCommand c : application.getCommands()) {
                if (commandId.equals(c.getElementId())) {
                    command = c;
                    // Put it in local cache for faster access next time.
                    commandCache.put(commandId, command);
                    break;
                }
            }
            if (command == null) {
                // Command not (yet) defined in the application, define it!
                command = MCommandsFactory.INSTANCE.createCommand();
                // Tag it so we can find it and remove it easily later (some commands used by modules might not be created by us and
                // thus should not be removed/deleted).
                command.getTags().add(MODULE_COMMAND_TAG);
                command.setElementId(commandId);
                command.setCommandName(commandId);
                // Add command to the application (as it is meant to be in the e4xmi model) and to the local cache for faster
                // access next time.
                application.getCommands().add(command);
                commandCache.put(commandId, command);
            }
        }
        return command;
    }

    /**
     * Builds a MCommand ID for the given module/action pair.
     */
    @objid ("4f5c55e1-1449-11e2-a678-001ec947c8cc")
    private static String getCommandId(IRTModule module, IModuleAction action) {
        return module.getName() + "." + action.getName();
    }

    /**
     * Returns the {@link MCommand} corresponding to the given module/action pair, creating it in not found in the application.
     * @param commandId
     * ID of the requested command.
     * 
     * @return the MCommand corresponding to the given module/action pair.
     */
    @objid ("4f5c55e8-1449-11e2-a678-001ec947c8cc")
    public static MCommand getCommand(IRTModule module, IModuleAction action) {
        return getCommand(getCommandId(module, action));
    }

    @objid ("4f5c55ef-1449-11e2-a678-001ec947c8cc")
    @SuppressWarnings("static-method")
    @Inject
    @Optional
    void onModuleStarted(@UIEventTopic(ModelioEventTopics.MODULE_STARTED) final IRTModule module) {
        // For each IModuleAction provided by this module, create a corresponding MCommand and a MHandler to be used as the default
        // handler if no handler more specific is defined.
        for (ActionLocation location : ActionLocation.values()) {
            for (IModuleAction action : module.getActions(location)) {
                // Requesting the command corresponding to this module.action couple will cause its creation if it doesn't exist
                // already.
                MCommand command = getCommand(module, action);
                createAndActivateHandler(command, module, action);
            }
        }
    }

    @objid ("4f5eb83a-1449-11e2-a678-001ec947c8cc")
    @SuppressWarnings("static-method")
    @Inject
    @Optional
    void onModuleStopped(@UIEventTopic(ModelioEventTopics.MODULE_STOPPED) final IRTModule module) {
        // For each IModuleAction provided by this module, remove and delete the corresponding MCommand and default MHandler.
        for (ActionLocation location : ActionLocation.values()) {
            for (IModuleAction action : module.getActions(location)) {
                MCommand command = getCommand(module, action);
                // Remove the default MHandler from the application
                deactivateAndRemoveHandler(command);
                // Remove the MCommands from the application
                removeCommand(command);
            }
        }
    }

    @objid ("4f5eb83f-1449-11e2-a678-001ec947c8cc")
    protected static MHandler createAndActivateHandler(MCommand command, IRTModule module, IModuleAction action) {
        // Instantiate the actual handler class
        Object handler = new ExecuteModuleActionHandler(module, action);
        // Fit it into a MHandler
        MHandler mHandler = MCommandsFactory.INSTANCE.createHandler();
        mHandler.setObject(handler);
        // mHandler.setElementId(command.getElementId().replace("command", "handler"));
        // Bind it to the corresponding command
        mHandler.setCommand(command);
        // Define scope of this handler as the application (making it the default handler in effect).
        application.getHandlers().add(mHandler);
        // Put it in cache for faster access next time.
        defaultHandlersCache.put(command.getElementId(), mHandler);
        
        // Activate the handler "by hand" since this part of the model may not be read again.
        EHandlerService handlerService = application.getContext().get(EHandlerService.class);
        handlerService.activateHandler(command.getElementId(), handler);
        return mHandler;
    }

    @objid ("4f5eb846-1449-11e2-a678-001ec947c8cc")
    protected static void deactivateAndRemoveHandler(MCommand command) {
        // Do the exact opposite of what is done in #createAndActivateHandler(MCommand, IRTModule, IModuleAction)
        EHandlerService handlerService = application.getContext().get(EHandlerService.class);
        handlerService.deactivateHandler(command.getElementId(), null);
        MHandler handlerToRemove = defaultHandlersCache.remove(command.getElementId());
        if (handlerToRemove != null) {
            application.getHandlers().remove(handlerToRemove);
            // Note: this might be a little over-the-top, but better safe than sorry.
            handlerToRemove.setCommand(null);
            handlerToRemove.setObject(null);
        }
    }

    @objid ("6446ee97-144c-11e2-a678-001ec947c8cc")
    protected static void removeCommand(MCommand command) {
        // In any case, remove it from cache
        commandCache.remove(command.getElementId());
        
        // If this command was created by us, remove it from the application, otherwise leave it to whoever created it.
        if (command.getTags().contains(MODULE_COMMAND_TAG)) {
            application.getCommands().remove(command);
        }
    }

}
