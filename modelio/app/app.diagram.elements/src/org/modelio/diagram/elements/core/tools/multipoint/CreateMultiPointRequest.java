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

package org.modelio.diagram.elements.core.tools.multipoint;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.TargetRequest;

/**
 * A request used by {@link MultiPointCreationTool}.
 * 
 * @author fpoyer
 */
@objid ("80e903d9-1dec-11e2-8cad-001ec947c8cc")
public class CreateMultiPointRequest extends CreateRequest implements TargetRequest {
    /**
     * Request type used by MultiPoint interaction when trying to determine the first element.
     */
    @objid ("817ee225-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_MULTIPOINT_FIRST = "Multipoint First";

    /**
     * Request type used by MultiPoint interaction when trying to determine the additional elements.
     */
    @objid ("817ee22b-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_MULTIPOINT_ADDITIONAL = "MultiPoint Additional";

    /**
     * Request type used by MultiPoint interaction when trying to determine the last element.
     */
    @objid ("817ee231-1e83-11e2-8cad-001ec947c8cc")
    public static final String REQ_MULTIPOINT_LAST = "MultiPoint Last";

    /**
     * The list of Commands issued so far.
     */
    @objid ("6375e24a-1e83-11e2-8cad-001ec947c8cc")
    private List<Command> startCommand = new ArrayList<>();

    @objid ("637844a5-1e83-11e2-8cad-001ec947c8cc")
    private EditPart targetEditPart;

    /**
     * The list of EditPart accepted so far.
     */
    @objid ("637aa6fd-1e83-11e2-8cad-001ec947c8cc")
    private List<EditPart> acceptedEditParts = new ArrayList<>();

    /**
     * Returns the EditParts that were accepted so far.
     * 
     * @return the source EditParts
     */
    @objid ("80e903f7-1dec-11e2-8cad-001ec947c8cc")
    public List<EditPart> getAcceptedEditParts() {
        return this.acceptedEditParts;
    }

    /**
     * Returns the EditPart that the target end of the connection should be connected to.
     * 
     * @return the target EditPart
     */
    @objid ("80e90400-1dec-11e2-8cad-001ec947c8cc")
    public EditPart getTargetEditPart() {
        return this.targetEditPart;
    }

    /**
     * Returns the start commands. These commands should only be used to pass on information to the target EditPart so
     * it can create the final command.
     * 
     * @return the commands
     */
    @objid ("80e90407-1dec-11e2-8cad-001ec947c8cc")
    public List<Command> getStartCommands() {
        return this.startCommand;
    }

    /**
     * Adds an EditPart to the list of sources.
     * 
     * @param part the source EditPart to add
     */
    @objid ("80e90410-1dec-11e2-8cad-001ec947c8cc")
    public void addSourceEditPart(final EditPart part) {
        this.acceptedEditParts.add(part);
    }

    /**
     * Removes an EditPart to the list of sources.
     * 
     * @param part the source EditPart to remove
     */
    @objid ("80e90417-1dec-11e2-8cad-001ec947c8cc")
    public void removeSourceEditPart(final EditPart part) {
        this.acceptedEditParts.remove(part);
    }

    /**
     * Sets the target of the Connection to the given EditPart.
     * 
     * @param part the target EditPart
     */
    @objid ("80e9041e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTargetEditPart(final EditPart part) {
        this.targetEditPart = part;
    }

    /**
     * Adds a Command to the list of start commands. This command is only used to pass on information to the target
     * EditPart so it can create the final command.
     * 
     * @param command the command
     */
    @objid ("80e90426-1dec-11e2-8cad-001ec947c8cc")
    public void addStartCommand(final Command command) {
        this.startCommand.add(command);
    }

    /**
     * Removes a Command from the list of start commands.
     * 
     * @param command the command
     */
    @objid ("80eb6633-1dec-11e2-8cad-001ec947c8cc")
    public void removeStartCommand(final Command command) {
        this.startCommand.remove(command);
    }

}
