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

package org.modelio.vcore.session.impl.transactions.smAction.smActionInteractions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.session.impl.transactions.smAction.AppendDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.CreateElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.DeleteElementAction;
import org.modelio.vcore.session.impl.transactions.smAction.EraseDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.MoveDependencyAction;
import org.modelio.vcore.session.impl.transactions.smAction.SetAttributeAction;

/**
 * Visitor pattern on the IAction inheritance tree.
 */
@objid ("006e76f2-0d1e-1f20-85a5-001ec947cd2a")
@SuppressWarnings("javadoc")
public interface IActionVisitor {
    @objid ("006db230-0d1e-1f20-85a5-001ec947cd2a")
    void visitTransaction(final Transaction theTransaction);

    @objid ("006db410-0d1e-1f20-85a5-001ec947cd2a")
    void visitCreateElementAction(final CreateElementAction theCreateElementAction);

    @objid ("006db4b0-0d1e-1f20-85a5-001ec947cd2a")
    void visitDeleteElementAction(final DeleteElementAction theDeleteElementAction);

    @objid ("006db550-0d1e-1f20-85a5-001ec947cd2a")
    void visitSetAttributeAction(final SetAttributeAction theSetAttributeAction);

    @objid ("006db5f0-0d1e-1f20-85a5-001ec947cd2a")
    void visitEraseDependencyAction(final EraseDependencyAction theEraseDependencyAction);

    @objid ("006db690-0d1e-1f20-85a5-001ec947cd2a")
    void visitAppendDependencyAction(final AppendDependencyAction theAppendDependencyAction);

    @objid ("006db73a-0d1e-1f20-85a5-001ec947cd2a")
    void visitMoveDependencyAction(final MoveDependencyAction theMoveDependencyAction);

}
