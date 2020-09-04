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

package org.modelio.diagram.editor;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;

/**
 * Redefine Eclipse command stack to use our transaction instead of Eclipse one;
 */
@objid ("6590b73a-33f7-11e2-95fe-001ec947c8cc")
public class DiagramCommandStack extends CommandStack {
    /**
     * Used by API to prevent unnecessary save when working on lots of modifications.
     */
    @objid ("6590b73e-33f7-11e2-95fe-001ec947c8cc")
    private boolean batchMode = false;

    @objid ("6590b73c-33f7-11e2-95fe-001ec947c8cc")
    private ITransactionSupport session;

    @objid ("6590b73d-33f7-11e2-95fe-001ec947c8cc")
    private IGmDiagram diagram;

    /**
     * Initialize the command stack.
     * 
     * @param iCoreSession a modeling session
     * @param gmDiagram The diagram model to handle.
     */
    @objid ("6590b740-33f7-11e2-95fe-001ec947c8cc")
    public DiagramCommandStack(ICoreSession iCoreSession, IGmDiagram gmDiagram) {
        this.session = iCoreSession.getTransactionSupport();
        this.diagram = gmDiagram;
    }

    @objid ("6590b745-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean canRedo() {
        return this.session.hasRedo();
    }

    @objid ("6590b74a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean canUndo() {
        return this.session.hasUndo();
    }

    @objid ("6590b74f-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings ("deprecation")
    @Override
    public void undo() {
        Command command = null;
        
        notifyListeners(command, CommandStack.PRE_UNDO);
        try {
            this.session.undo();
            notifyListeners();
        } finally {
            notifyListeners(command, CommandStack.POST_UNDO);
        }
    }

    @objid ("6590b752-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings ("deprecation")
    @Override
    public void redo() {
        if (!canRedo()) {
            return;
        }
        Command command = null;
        notifyListeners(command, CommandStack.PRE_REDO);
        try {
            this.session.redo();
            notifyListeners();
        } finally {
            notifyListeners(command, CommandStack.POST_REDO);
        }
    }

    @objid ("6590b755-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public int getUndoLimit() {
        return -1;
    }

    @objid ("6590b75a-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @objid ("6590b75d-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public boolean isDirty() {
        return this.session.hasUndo();
    }

    @objid ("6590b762-33f7-11e2-95fe-001ec947c8cc")
    @SuppressWarnings ("deprecation")
    @Override
    public void execute(Command command) {
        if (command == null || !command.canExecute()) {
            return;
        }
        
        notifyListeners(command, CommandStack.PRE_EXECUTE);
        
        boolean commitFailed = true;
        try (ITransaction t = this.session.createTransaction(command.getLabel())) {
            command.execute();
            if (!this.batchMode) {
                this.diagram.save(true);
            }
            t.commit();
            commitFailed = false;
            notifyListeners();
        } catch (IllegalModelManipulationException e) {
            DiagramEditor.LOG.error(e);
        } catch (OperationCanceledException e) {
            // Ignore error, the operation was canceled
        } catch (Exception e) {
            // Keep eclipse's generic error dialog from opening...
            DiagramEditor.LOG.error(e);
        } finally {
            if (commitFailed) {
                // Commit failed, force a diagram reload
                this.diagram.load();
            }
            notifyListeners(command, CommandStack.POST_EXECUTE);
        }
    }

    @objid ("6590b766-33f7-11e2-95fe-001ec947c8cc")
    @Override
    public void markSaveLocation() {
        // Nothing to do.
    }

    /**
     * @return true if batch mode should be engaged (ie no more automatic save of the diagram).
     */
    @objid ("b18cc6d0-742b-45ee-ae67-efa55510b404")
    public boolean isBatchMode() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.batchMode;
    }

    /**
     * Sets the batch mode on/off. Should only be used by API.
     * 
     * @param value true if batch mode should be engaged (ie no more automatic save of the diagram).
     */
    @objid ("9b591753-bb10-4490-a379-79e041b56702")
    public void setBatchMode(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.batchMode = value;
    }

}
