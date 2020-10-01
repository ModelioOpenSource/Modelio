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

package org.modelio.diagram.editor.silent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * Redefine Eclipse command stack to use our transaction instead of Eclipse one;
 */
@objid ("66949785-33f7-11e2-95fe-001ec947c8cc")
class DiagramCommandStack extends CommandStack {
    @objid ("2c821204-3a3d-11e2-a430-001ec947c8cc")
    private ITransactionSupport session;

    /**
     * Initialise the command stack.
     * 
     * @param session a modelling session
     */
    @objid ("6694978a-33f7-11e2-95fe-001ec947c8cc")
    public DiagramCommandStack(ITransactionSupport session) {
        this.session = session;
    }

    @objid ("66949790-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean canRedo() {
        return this.session.hasRedo();
    }

    @objid ("66949795-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean canUndo() {
        return this.session.hasUndo();
    }

    @objid ("6694979a-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings("deprecation")
    @Override
    public void undo() {
        Command command = null;
        
        notifyListeners(command, PRE_UNDO);
        try {
            this.session.undo();
            notifyListeners();
        } finally {
            notifyListeners(command, POST_UNDO);
        }
    }

    @objid ("6694979d-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings("deprecation")
    @Override
    public void redo() {
        if (!canRedo())
            return;
        Command command = null;
        notifyListeners(command, PRE_REDO);
        try {
            this.session.redo();
            notifyListeners();
        } finally {
            notifyListeners(command, POST_REDO);
        }
    }

    @objid ("669497a0-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public int getUndoLimit() {
        return -1;
    }

    @objid ("6696f9e0-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @objid ("6696f9e3-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean isDirty() {
        return this.session.hasUndo();
    }

    @objid ("6696f9e8-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings("deprecation")
    @Override
    public void execute(Command command) {
        if (command == null || !command.canExecute())
            return;
        
        notifyListeners(command, PRE_EXECUTE);
        
        try {
            try {
                command.execute();
                notifyListeners();
            } catch (Exception e) {
                e.printStackTrace();
                //throw new InvocationTargetException(e);
            }
        } finally {
            notifyListeners(command, POST_EXECUTE);
        }
    }

    @objid ("6696f9ec-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void markSaveLocation() {
        // Nothing to do.
    }

}
