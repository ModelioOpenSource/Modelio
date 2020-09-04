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

package org.modelio.diagram.elements.core.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.tools.AbstractConnectionCreationTool;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.modelio.diagram.elements.core.link.linknode.AbstractCreateLinkChooseNodeEditPolicy;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.ui.gef.SharedCursors2;

/**
 * Link+node creation tool for Modelio connections.
 * <p>
 * This tool will support bend points at creation and orthogonal path. To be controlled with a {@link AbstractCreateLinkChooseNodeEditPolicy}, optionally provides a popup choice to create a node
 */
@objid ("3696dad5-bae5-4be5-92d2-20589b9478a5")
public class BendedConnectionAndNodeCreationTool extends BendedConnectionCreationTool {
    /**
     * The original viewer contextual menu.
     */
    @objid ("1f57fe0d-7366-4ccd-9797-a078740ec789")
    private Menu savedmenu;

    /**
     * Redefined to display create node and link cursor.
     * 
     * {@inheritDoc}
     */
    @objid ("e1d0e2f7-c031-4933-acba-4d8d240e817c")
    @Override
    protected Cursor calculateCursor() {
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED) &&
                isCreateLinkChooseNodeRequest()) {
            final Command command = getCurrentCommand();
            if (command != null && command.canExecute()) {
                return SharedCursors2.CURSOR_LINK_END_MENU;
            } else {
                return getDisabledCursor();
            }
        }
        return super.calculateCursor();
    }

    /**
     * Called when the user creates a node and link.
     * @see #handleCreateConnection()
     * @return always true
     */
    @objid ("7cc66948-5a73-4f89-9110-513c3ecb43fd")
    protected boolean handleCreateConnectedNode() {
        Command endCommand = getCommand();
        setCurrentCommand(endCommand);
        executeCurrentCommand();
        
        eraseTargetFeedback();
        eraseSourceFeedback();
        
        EditPartViewer viewer = getCurrentViewer();
        selectAddedObject(viewer);
        return true;
    }

    /**
     * Temporarily remove the viewer popup menu and save it to restore it later. The method is guarded agasin'st multiple calls.
     */
    @objid ("17fcf32c-66ed-468e-9d80-491c193541f7")
    private void disableViewerPopupMenu() {
        if (this.savedmenu == null) {
            Control viewerControl = getCurrentViewer().getControl();
        
            this.savedmenu = viewerControl.getMenu();
            viewerControl.setMenu(null);
        }
    }

    @objid ("844302c2-9393-48e3-bda7-9c9b953d4c95")
    @Override
    protected void updateTargetRequest() {
        // When the interaction starts, remove the popup menu
        disableViewerPopupMenu();
        super.updateTargetRequest();
    }

    /**
     * Restore the temporarily removed viewer popup menu. The method is guarded agaisns't multiple calls or out of sequence calls (restoring without having saved first)
     */
    @objid ("73534f9b-0e96-4c01-a877-3a6b77210c7b")
    private void restoreViewerPopupMenu() {
        if (this.savedmenu != null && getCurrentViewer() != null) {
            getCurrentViewer().getControl().setMenu(this.savedmenu);
            this.savedmenu = null;
        }
    }

    @objid ("46e780fb-07d8-4cda-8b7e-9fe00d074e6e")
    @Override
    public void deactivate() {
        // Just in case an interaction has been aborted while the menu was removed.
        restoreViewerPopupMenu();
        super.deactivate();
    }

    @objid ("6e42d70f-c2a7-446f-b7ad-59efadf9357f")
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {
            // First look for a target in the current state
            String newRequestType = getCommandName();
            EditPart editPart = findTargetUnderMouse(newRequestType);
        
            if (editPart == null && isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
                // If no target can end the link, ask to add a node and link
                if (getCurrentInput().isControlKeyDown() || getCurrentInput().isMouseButtonDown(3)) {
                    newRequestType = (CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE);
                    editPart = findTargetUnderMouse(newRequestType);
                }
        
                if (editPart == null) {
                    // If no target can add a node and link, ask to add a bendpoint
                    newRequestType = (CreateLinkConstants.REQ_CONNECTION_ADD_BENDPOINT);
                    editPart = findTargetUnderMouse(newRequestType);
                }
        
                if (editPart == null) {
                    newRequestType = getCommandName();
                }
            }
        
            return updateTargetEditPart(editPart, newRequestType);
        
        } else {
            return false;
        }
    }

    @objid ("881d2948-8623-4812-a325-53ef033c8024")
    @Override
    protected boolean handleCreateConnection() {
        if (isCreateLinkChooseNodeRequest()) {
            if (handleCreateConnectedNode()) {
                restoreViewerPopupMenu();
                setState(AbstractTool.STATE_TERMINAL);
                // // Fake a drag to cause feedback to be displayed immediately on mouse down.
                // handleDrag();
                return true;
            }
        }
        return super.handleCreateConnection();
    }

    @objid ("83bdc7e2-b8a0-45e7-be6e-399c39b32acb")
    @Override
    protected boolean handleButtonDown(int button) {
        AbstractTool.Input input = getCurrentInput();
        
        if (isInState(AbstractConnectionCreationTool.STATE_CONNECTION_STARTED)) {
            if (button == 1 ) {
                if (input.isControlKeyDown()) {
                    if (isCreateLinkChooseNodeRequest() && handleCreateConnection()) {
                        return true;
                    }
                }
            } else if (button == 3) {
                boolean fallBack = super.handleButtonDown(button);
                if (isCreateLinkChooseNodeRequest() && handleCreateConnection()) {
                    return true;
                }
                return fallBack;
            }
        }
        return super.handleButtonDown(button);
    }

    /**
     * @return whether the current request is a {@link CreateLinkConstants#REQ_CONNECTION_CREATE_LINK_CHOOSENODE} request.
     */
    @objid ("a6a3f9ba-c163-4343-901c-4b42b375904f")
    protected final boolean isCreateLinkChooseNodeRequest() {
        return CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE.equals(getTargetRequest().getType());
    }

}
