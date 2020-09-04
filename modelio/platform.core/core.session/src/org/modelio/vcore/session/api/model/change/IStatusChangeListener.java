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

package org.modelio.vcore.session.api.model.change;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Model change listeners are fired after a top level transaction has been committed, undone or redone.
 * 
 * Model change listeners <strong>cannot</strong> modify the level, trying to create a transaction will throw  a TransactionForbiddenException.
 */
@objid ("0041b59a-d428-1f3b-aafd-001ec947cd2a")
public interface IStatusChangeListener {
    @objid ("0041b3b0-d428-1f3b-aafd-001ec947cd2a")
    void statusChanged(final IStatusChangeEvent event);

}
