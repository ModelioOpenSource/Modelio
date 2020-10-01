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

/**
 * Session handlers are fired before a session (top level transaction) is committed.
 * 
 * Session handlers have the right to modify the model, contrary to session listeners.
 * These modifications will be a part of the committed transaction and handled has such.
 */
@objid ("006f8a38-0d1e-1f20-85a5-001ec947cd2a")
public interface IModelChangeHandler {
    @objid ("001dd2d8-c252-1f3b-aafd-001ec947cd2a")
    void handleModelChange(final IModelChangeEvent event);

}
