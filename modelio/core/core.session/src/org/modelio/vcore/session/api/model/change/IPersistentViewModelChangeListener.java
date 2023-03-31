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
package org.modelio.vcore.session.api.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.session.impl.transactions.TransactionManager;

/**
 * Persistent view model change listeners are fired before a session (top level transaction) is committed.
 * 
 * Persistent view model change listeners:
 * <ul>
 * <li>They may modify the model, but should make only non structural modifications.</li>
 * <li>They should not create new transactions.</li>
 * <li>They may throw exceptions. In this case the transaction will be rollbacked by the try-with-resources that the caller MUST have used to open the transaction.</li>
 * <li>They are called after the IModelChangeHandler but before IModelChangeListener<li>
 * <li>They are mainly intended for diagrams who needs to update their contents and eventually save their diagram serialization string.</li>
 * </ul>
 * 
 * See {@link TransactionManager} for the handlers/listeners call sequence.
 */
@objid ("a93f2d4e-4e79-4630-8f34-09348d9db89e")
public interface IPersistentViewModelChangeListener {
    @objid ("f5d00b18-d4fc-44e7-a9c1-d31097596c2c")
    void updateView(final IModelChangeEvent event);
}

