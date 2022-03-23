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
package org.modelio.vcore.session.impl.transactions.smAction;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions.IActionVisitor;

/**
 * Base interface for a transaction action.
 */
@objid ("006b243e-0d1e-1f20-85a5-001ec947cd2a")
public interface IAction {
    /**
     * Visitor pattern
     * @param v a visitor
     */
    @objid ("006bd1f4-0d1e-1f20-85a5-001ec947cd2a")
    void accept(final IActionVisitor v);

    /**
     * @return whether this action is a transaction.
     */
    @objid ("006bd352-0d1e-1f20-85a5-001ec947cd2a")
    boolean isTransaction();

    /**
     * Execute le redo de l'action
     */
    @objid ("006b1d36-0d1e-1f20-85a5-001ec947cd2a")
    void redo();

    /**
     * Permet de positinner toutes les transactions contenue dans la transaction a notUndoable.
     * Cette methode est appellee par le reset.
     */
    @objid ("006b1e6c-0d1e-1f20-85a5-001ec947cd2a")
    void disableUndo();

    /**
     * Execute le undo de l'action
     * @param rollback whether the undo is for a transaction rollback or a 'undo' command.
     */
    @objid ("006b1f98-0d1e-1f20-85a5-001ec947cd2a")
    void undo(final boolean rollback);

}
