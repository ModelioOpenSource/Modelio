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
 * <h2>History</h2>
 * This class is part of orthogonal links layout, a very fragile feature.
 * <p>
 * <ul>
 * <li>18/08/2022: Mostly disabled. Its work is done by OrthogonalRectifierRouter
 * <li>29/08/2022: Enabled back due to issues.
 * Used only for (BPMN) lane containers where moving a lane actually moves all of them.
 * Also enabled 5.0 behavior in GmNodeDragTracer.
 * </ul>
 * @author cma
 * @since 5.1.0
 */
@objid ("ddb58533-72a5-49f4-b1b8-db6d40cf6894")
public class LayoutChildrenNodeConnectionsHelper {
    @objid ("76a41e89-7981-474a-956d-6e9651ba7bb3")
    private static final boolean DISABLED = false;

    @objid ("81980a7a-17e9-402a-b090-d082d97d0bb6")
    private static final LayoutChildrenNodeConnectionsHelper SHARED = new LayoutChildrenNodeConnectionsHelper();

    @objid ("e2333e3e-2a0e-4c4a-925c-63fae63cd1de")
    private final Set<GraphicalEditPart> editParts2 = new HashSet<>();

    @objid ("b3a62a71-fd67-4ec6-b8c3-8717c7b80e47")
    private Request parentRequest;

    /**
     * Add the given edit part to the edited nodes set.
     * @param anEditPart an edit part.
     * @return this instance
     */
    @objid ("31e42cea-f2c2-456f-b205-5485a3a2a1ba")
    public LayoutChildrenNodeConnectionsHelper addEditPart(GraphicalEditPart anEditPart) {
        if (DISABLED)
            return this;
        
        this.editParts2.add(anEditPart);
        return this;
    }

    /**
     * Add {@link GroupRequest#getEditParts()} to the edited nodes set.
     * @param req the move/resize/... request.
     * @return this instance
     */
    @objid ("73f6fcb4-3fae-4191-9b91-7b958ff8f8fe")
    public LayoutChildrenNodeConnectionsHelper addEditParts(GroupRequest req) {
        if (DISABLED)
            return this;
        
        this.editParts2.addAll(req.getEditParts());
        return this;
    }

    /**
     * Add the given edit parts to the edited nodes set.
     * @param nodes some edit parts.
     * @return this instance
     */
    @objid ("6bd26e1b-876a-4aea-b72f-0a38afe749a8")
    public LayoutChildrenNodeConnectionsHelper addEditParts(Collection<GraphicalEditPart> nodes) {
        if (DISABLED)
            return this;
        
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
    @objid ("82df6c2d-6500-4b9a-ac62-71d6f9b796fc")
    public Command createChainedCommand(Command initialCommand) {
        if (DISABLED)
            return initialCommand;
        
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
        
        // Add the layout connections commands
        createCommands(command);
        return command.unwrap();
    }

    /**
     * Create the connection layout commands and add them to the passed compound command
     * @param command the compound command to fill.
     * @return The passed <code>command</code> .
     */
    @objid ("7a1705a8-6cb1-432a-8313-2843e020d417")
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
    @objid ("628aa663-32e3-43d9-a429-368aac8dbe98")
    public static LayoutChildrenNodeConnectionsHelper forRequest(Request parentRequest) {
        return SHARED.init(parentRequest);
    }

    /**
     * Reset this instance
     * @param newParentRequest the parent request. May be null if no request.
     * @return this instance
     */
    @objid ("5986d370-0754-4b0c-9710-0b1e69881810")
    public LayoutChildrenNodeConnectionsHelper init(Request newParentRequest) {
        if (DISABLED)
            return this;
        
        this.editParts2.clear();
        this.parentRequest = newParentRequest;
        return this;
    }

    /**
     * Remove the given edit parts from the edited nodes set.
     * @param deleted some edit parts.
     * @return this instance
     */
    @objid ("5915efdd-2ab2-47d7-9758-a06e3c90478d")
    public LayoutChildrenNodeConnectionsHelper removeEditParts(Collection<GraphicalEditPart> deleted) {
        if (DISABLED)
            return this;
        
        this.editParts2.removeAll(deleted);
        return this;
    }

    /**
     * Remove {@link GroupRequest#getEditParts()} from the edited nodes set.
     * @param req the move/resize/... request.
     * @return this instance
     */
    @objid ("f9aaf504-a13c-45d3-ad9f-f07b420e5986")
    public LayoutChildrenNodeConnectionsHelper removeEditParts(GroupRequest req) {
        if (DISABLED)
            return this;
        
        this.editParts2.removeAll(req.getEditParts());
        return this;
    }

    /**
     * Private constructor
     * @see #forRequest(Request)
     */
    @objid ("021fde1e-60f0-40b5-977f-40cb8c4b45a1")
    private  LayoutChildrenNodeConnectionsHelper() {
        
    }

    @objid ("e6640f23-2d66-4df9-86ab-f5e1ca0a2f0f")
    private static void addLayoutConnectionCommands(CompoundCommand command, Request parentRequest, Set<GraphicalEditPart> nodes) {
        // Completely disable policy
        if (DISABLED)
            return;
        
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

    @objid ("420e9657-ead7-46ad-b781-7f91d81db7af")
    private static void addCommandsForAllDiagramConnections(CompoundCommand command, Request parentRequest, Set<GraphicalEditPart> nodes) {
        // Completely disable policy
        if (DISABLED)
            return;
        
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

    @objid ("86e21fd5-d6ab-4e88-8151-2065c55b1a1d")
    private static void addSharedEditParts(Request req, Set<GraphicalEditPart> to) {
        if (req instanceof GroupRequest) {
            to.addAll(RequestHelper.getSharedEditParts((GroupRequest) req));
        }
        
    }

    @objid ("6af80b08-8f7e-4990-9093-7ab54c30ae2c")
    private static ChangeBoundsRequest getAdaptedRequest(Request parentRequest) {
        ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        if (parentRequest != null) {
            req.setExtendedData(parentRequest.getExtendedData());
            if (RequestConstants.REQ_MOVE.equals(parentRequest.getType())
                    || RequestConstants.REQ_MOVE_CHILDREN.equals(parentRequest.getType())) {
                // xx/08/2022 : Mantis 14514 & 14499 : add move delta for old orthogonal edit policies
                // that rely on it.
                // Edit the already existing MoveDelta point instead of creating a new Point.
                req.getMoveDelta().setLocation(((ChangeBoundsRequest) parentRequest).getMoveDelta());
            }
        }
        return req;
    }

}
