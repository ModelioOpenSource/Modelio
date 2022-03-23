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
package org.modelio.diagram.elements.core.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;
import org.modelio.diagram.elements.core.helpers.ToolSelectionUtils;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Helper for layout edit policies to ask on their children recursively to layout their connections.
 * 
 * @author cma
 * @since 5.1.0
 */
@objid ("17e04dd5-496b-456d-8a04-08e31a6efcfd")
public class LayoutChildrenNodeConnectionsHelper {
    @objid ("83747ff1-2f7c-4c0a-9dea-e8be75eef5be")
    private static final LayoutChildrenNodeConnectionsHelper SHARED = new LayoutChildrenNodeConnectionsHelper();

    @objid ("41ed4844-7b81-4295-8612-b14c931f8f04")
    private final Set<GraphicalEditPart> editParts2 = new HashSet<>();

    @objid ("5e4a9428-3162-440e-ad9b-ad5c7f833725")
    private Request parentRequest;

    @objid ("ce57ef6c-c8c6-4d6c-a532-fb4d88dc8089")
    public LayoutChildrenNodeConnectionsHelper addEditPart(GraphicalEditPart host) {
        this.editParts2.add(host);
        return this;
    }

    /**
     * Add {@link GroupRequest#getEditParts()} to the edited nodes set.
     * @param req the move/resize/... request.
     * @return this instance
     */
    @objid ("118717a0-c3ad-42b9-b3da-210e78533a3e")
    public LayoutChildrenNodeConnectionsHelper addEditParts(GroupRequest req) {
        this.editParts2.addAll(req.getEditParts());
        return this;
    }

    @objid ("5bc9bcf7-6555-4478-b9b0-1d1ce045bbbf")
    public LayoutChildrenNodeConnectionsHelper addEditParts(Collection<GraphicalEditPart> nodes) {
        this.editParts2.addAll(nodes);
        return this;
    }

    /**
     * Create the connection layout commands and chain them with the passed command.
     * <p>
     * If <code>initialCommand</code> is null, does nothing and return <i>null</i>;
     * @param initialCommand the initial command, to be executed first. May be <i>null</i>.
     * @return a command that executes the main command then connection layout commands, or <i>null</i>.
     */
    @objid ("e4e8cabd-afc1-4691-9f61-39b8e4a8aede")
    public Command createChainedCommand(Command initialCommand) {
        if (initialCommand == null) {
            return null;
        }
        
        CompoundCommand command;
        if (initialCommand instanceof CompoundCommand) {
            command = (CompoundCommand) initialCommand;
        } else {
            command = new CompoundCommand();
            command.setLabel(initialCommand.getLabel());
            command.add(initialCommand);
        }
        createCommands(command);
        return command.unwrap();
    }

    /**
     * Create the connection layout commands and add them to the passed compound command
     * @param command the compound command to fill.
     * @return The passed <code>command</code> .
     */
    @objid ("d8b8c84f-57e1-41d2-b74e-9164b45d2a96")
    public CompoundCommand createCommands(CompoundCommand command) {
        addLayoutConnectionCommands(command, this.parentRequest, this.editParts2);
        return command;
    }

    /**
     * Get and initialize a shared instance.
     * <p>
     * The returned object must be used then forgot, don't keep a reference.
     * @param parentRequest the parent request. May be null if no request.
     * @return a shared instance.
     */
    @objid ("37374560-b4b4-47cb-a9fb-fcd439044aac")
    public static LayoutChildrenNodeConnectionsHelper forRequest(Request parentRequest) {
        return SHARED.init(parentRequest);
    }

    /**
     * Reset this instance
     * @param newParentRequest the parent request. May be null if no request.
     * @return this instance
     */
    @objid ("03002c69-b5e3-43b9-aa9d-73cad29711c9")
    public LayoutChildrenNodeConnectionsHelper init(Request newParentRequest) {
        this.editParts2.clear();
        this.parentRequest = newParentRequest;
        return this;
    }

    @objid ("8eb5799f-ae1b-43da-8ca0-4d021d5e7b0b")
    public LayoutChildrenNodeConnectionsHelper removeEditParts(Collection<GraphicalEditPart> deleted) {
        this.editParts2.removeAll(deleted);
        return this;
    }

    /**
     * Remove {@link GroupRequest#getEditParts()} from the edited nodes set.
     * @param req the move/resize/... request.
     * @return this instance
     */
    @objid ("071c3413-acb5-445b-98ad-ad75f0e14d91")
    public LayoutChildrenNodeConnectionsHelper removeEditParts(GroupRequest req) {
        this.editParts2.removeAll(req.getEditParts());
        return this;
    }

    /**
     * Private constructor
     * @see #forRequest(Request)
     */
    @objid ("59602305-d40d-4869-99d1-88d7c0b9fb4b")
    private  LayoutChildrenNodeConnectionsHelper() {
        
    }

    @objid ("27cddddc-473d-4311-923d-e5ed81e2aa06")
    private static void addLayoutConnectionCommands0(CompoundCommand command, Request parentRequest, Set<GraphicalEditPart> nodes) {
        ChangeBoundsRequest req = getAdaptedRequest(parentRequest);
        
        ToolSelectionUtils.addAllLinksFor(nodes, req, false);
        req.setEditParts(new ArrayList<>(nodes));
        
        if (parentRequest instanceof GroupRequest) {
            RequestHelper.addSharedEditParts(req, (GroupRequest) parentRequest);
        }
        
        for (EditPart editPart : nodes) {
            if (editPart instanceof ConnectionEditPart) {
                Command subCommand = editPart.getCommand(req);
                if (subCommand != null) {
                    command.add(subCommand);
                }
            }
        }
        
    }

    @objid ("3c4d0722-c77a-4238-8101-cd613a76dc15")
    private static void addLayoutConnectionCommands(CompoundCommand command, Request parentRequest, Set<GraphicalEditPart> nodes) {
        if (nodes.isEmpty()) {
            return;
        }
        
        ChangeBoundsRequest req = getAdaptedRequest(parentRequest);
        req.setEditParts(new ArrayList<>(nodes));
        
        if (parentRequest instanceof GroupRequest) {
            RequestHelper.addSharedEditParts(req, (GroupRequest) parentRequest);
        }
        
        Map<Object, EditPart> editPartRegistry = nodes.iterator().next().getViewer().getEditPartRegistry();
        
        nodes.stream()
                .map(ep -> ((IGmObject) ep.getModel()).getDiagram())
                .distinct()
                .forEach(diag -> {
                    for (IGmLinkObject gmLink : diag.getAllLinks()) {
                        EditPart linkEp = editPartRegistry.get(gmLink);
                        if (linkEp != null && linkEp.isActive()) {
                            command.add(linkEp.getCommand(req));
                        }
                    }
                });
        
    }

    @objid ("3e9d1245-f22d-4b1a-9d1d-026ac3d3a227")
    private static void addSharedEditParts(Request req, Set<GraphicalEditPart> to) {
        if (req instanceof GroupRequest) {
            to.addAll(RequestHelper.getSharedEditParts((GroupRequest) req));
        }
        
    }

    @objid ("d11a5b2c-69a3-4631-bb06-68439b09dabe")
    private static ChangeBoundsRequest getAdaptedRequest(Request parentRequest) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        if (parentRequest != null) {
            req.setExtendedData(parentRequest.getExtendedData());
        }
        return req;
    }

}
