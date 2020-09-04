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

package org.modelio.diagram.elements.core.commands;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.modelio.diagram.elements.core.helpers.RequestHelper;

/**
 * Command used to notify edit parts their layout changed.
 * <p>
 * Usage :
 * On the node layout change command {@link Command#execute()} method:
 * <ol>
 * <li> instantiate this class
 * <li> call {@link #add(Command)} with the layout command(s)
 * <li> make somebody call {@link #execute()} on this instance
 * </ol>
 * 
 * Edit parts wishing to listen for node layout changes must understand the {@link #REQ_TYPE} request type.
 * <p>
 * This command will look for edit parts in the composition tree that understand {@link #REQ_TYPE} request type
 * and ask them immediately for commands.
 * The iteration skips the composition tree for edit parts that understand the request or that are included
 * in the {@link #REQPARAM_NOPOSTLAYOUT} request extended data.
 * 
 * 
 * @author cmarin
 * @since 3.4
 */
@objid ("72fe72a5-c547-4a75-8c06-836a8f9da466")
public class PostLayoutCommand extends CompoundCommand {
    /**
     * to avoid asking edit parts twice
     */
    @objid ("fd82f273-a4f3-49f6-a109-fa17dda8582b")
    private Collection<EditPart> postLayouters = new HashSet<>();

    /**
     * The request type edit part can listen for.
     */
    @objid ("7f3c63c9-97c9-4cc4-95e9-8b07d00948cd")
    public static final Object REQ_TYPE = PostLayoutCommand.class;

    /**
     * Request extended parameter key containing a collection of edit parts for which
     * post layout must not be called.
     */
    @objid ("620bd04a-1d27-44f7-93d7-0f1a30141dbc")
    public static final Object REQPARAM_NOPOSTLAYOUT = "no post layout for";

    /**
     * Origin of this command, for excluding edit parts that don't want post layout and debugging.
     */
    @objid ("91efc4bc-c346-4a1c-86cb-a80f9059790a")
    private ChangeBoundsRequest origin;

    /**
     * @param origin the origin request
     */
    @objid ("39423a69-d215-4079-aa1d-3240e71eb772")
    public PostLayoutCommand(ChangeBoundsRequest origin) {
        super("Layout and post layout for:"+RequestHelper.toString(origin));
        this.origin = origin;
    }

    /**
     * Add the given command only if the edit part has not already be recorded.
     * 
     * @param part the involved edit part
     * @param c the command for the edit part.
     */
    @objid ("e53ea9af-1273-4433-8f60-194a5aa8f978")
    private void add(EditPart part, Command c) {
        if (this.postLayouters.add(part)) {
            super.add(c);
        }
    }

    @objid ("7cd6a4af-490b-4b0e-9242-ca7f55a8eddb")
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(getClass().getSimpleName());
        s.append("[\n");
        for (Object cmd : getCommands()) {
            s.append(" \t -");
            s.append(cmd);
            s.append("\n");
        }
        s.append("\n");
        s.append("\tas post layout of: ");
        s.append(RequestHelper.toString(this.origin));
        s.append("]");
        return s.toString();
    }

    /**
     * Look for edit parts in the composition tree that understand {@link #REQ_TYPE} request type
     * and ask them immediately for commands.
     * The iteration skips the composition tree for edit parts that understand the request.
     * @param part the edit part to iterate.
     */
    @objid ("0776d336-c8db-4d81-b0ee-45c225251f62")
    private void collectPostExecuteCommands() {
        ChangeBoundsRequest req = RequestHelper.shallowCopy(this.origin);
        req.setType(REQ_TYPE);
        
        for (Iterator<EditPart> iterator = req.getEditParts().iterator(); iterator.hasNext();) {
            EditPart child = iterator.next();
            collectPostExecuteCommands(req, child);
        }
    }

    @objid ("2356ddec-8c43-4987-b2db-d673c261aa0d")
    private void collectPostExecuteCommands(ChangeBoundsRequest req, EditPart ep) {
        if (! RequestHelper.containsParamValue(req, REQPARAM_NOPOSTLAYOUT, ep)) {
            if (ep.understandsRequest(req)) {
                Command cmd = ep.getCommand(req);
                if (cmd != null && cmd.canExecute()) {
                    add(ep, cmd);
                }
            } else {
                for (Iterator<EditPart> iterator = ep.getChildren().iterator(); iterator.hasNext();) {
                    EditPart child = iterator.next();
                    collectPostExecuteCommands(req, child);
                }
            }
        }
    }

    /**
     * @param c a layout command to add immediately.
     * @param origin the origin request
     */
    @objid ("9ec29e8f-2435-4d52-be87-625114c4dfa3")
    public PostLayoutCommand(Command c, ChangeBoundsRequest origin) {
        this(origin);
        add(c);
    }

    @objid ("0fbac638-6765-40bc-a4d2-c5157daab014")
    @Override
    public void execute() {
        // Collect post layout commands
        collectPostExecuteCommands();
        
        // Run layout command followed by post layout commands
        super.execute();
    }

    @objid ("cbfb0594-eb13-45e4-bceb-a4fc29d5b441")
    @Override
    public Command unwrap() {
        if (isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        } else {
            return this;
        }
    }

}
