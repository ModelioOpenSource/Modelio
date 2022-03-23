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
package org.modelio.diagram.elements.core.node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.swt.graphics.Cursor;
import org.modelio.diagram.elements.core.commands.DefaultReparentElementCommand;
import org.modelio.diagram.elements.core.requests.NavigationRequest;
import org.modelio.platform.ui.gef.SharedCursors2;

/**
 * Set the cursor for reparenting nodes.
 * 
 * @author fpo
 */
@objid ("808e6aa7-1dec-11e2-8cad-001ec947c8cc")
public class GmNodeDragTracker extends DragEditPartsTracker {
    @objid ("1260a779-2fa2-4a5d-8694-fe14bf6b2109")
    private ArrayList<IFigure> exclusionSet = null;

    /**
     * Constructs a new GmNodeDragTracker with the given source edit part.
     * @param sourceEditPart the source edit part.
     */
    @objid ("808e6ab0-1dec-11e2-8cad-001ec947c8cc")
    public  GmNodeDragTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    @objid ("808e6ab6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Cursor calculateCursor() {
        if (isInState(AbstractTool.STATE_DRAG_IN_PROGRESS | AbstractTool.STATE_ACCESSIBLE_DRAG_IN_PROGRESS)) {
            if (!isMove() && !isCloneActive()) {
                // Try to reparent in progress: analyse the current command to
                // determine if the target accepts the add
                Command currentCommand = getCurrentCommand();
                return getReparentCursor(currentCommand);
        
            }
        }
        return super.calculateCursor();
    }

    @objid ("808e6abb-1dec-11e2-8cad-001ec947c8cc")
    private Cursor getReparentCursor(Command command) {
        DefaultReparentElementCommand reparentCmd = getReparentCommand(command);
        if (reparentCmd != null) {
            if (reparentCmd.canExecute()) {
                return SharedCursors2.CURSOR_REPARENT;
            } else {
                return SharedCursors2.CURSOR_REPARENT_NOT;
            }
        }
        return super.calculateCursor();
    }

    /**
     * Returns a list of all the edit parts in the {@link AbstractTool#getOperationSet() operation set}.
     * <p>
     * Redefined to not exclude the {@link org.eclipse.draw2d.ConnectionLayer}.
     * @see org.eclipse.gef.tools.TargetingTool#getExclusionSet()
     */
    @objid ("8090ccdb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected Collection<?> getExclusionSet() {
        if (this.exclusionSet == null) {
            List<GraphicalEditPart> set = getOperationSet();
            this.exclusionSet = new ArrayList<>(set.size() + 1);
            for (GraphicalEditPart editpart : set) {
                this.exclusionSet.add(editpart.getFigure());
            }
        }
        return this.exclusionSet;
    }

    @objid ("8090ccf4-1dec-11e2-8cad-001ec947c8cc")
    private DefaultReparentElementCommand getReparentCommand(Command command) {
        if (command instanceof DefaultReparentElementCommand) {
            return (DefaultReparentElementCommand) command;
        } else if (command instanceof CompoundCommand) {
            List<Command> nestedCommands = ((CompoundCommand) command).getCommands();
        
            // Look for a reparent command in the compound
            for (Command nested : nestedCommands) {
                DefaultReparentElementCommand ret = getReparentCommand(nested);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return null;
    }

    /**
     * fire a Modelio navigation event on &lt;ctrl>+&lt;alt>+click.
     */
    @objid ("5b195262-1f05-4cf8-857d-a50b9dfd38a4")
    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 1) {
            final Input currentInput = getCurrentInput();
        
            if (currentInput.isControlKeyDown() && currentInput.isAltKeyDown()) {
                performNavigation();
                return true;
            }
        }
        return super.handleButtonDown(button);
    }

    /**
     * Creates a {@link NavigationRequest} and sends it to the source edit part via {@link EditPart#performRequest(Request)}.
     * <p>
     * Uses are to fire a Modelio navigation on the selected item to select it in the browser.
     */
    @objid ("f3732501-f95e-4f73-9309-07e0db426695")
    protected void performNavigation() {
        NavigationRequest request = new NavigationRequest();
        request.setLocation(getLocation());
        getSourceEditPart().performRequest(request);
        
    }

}
