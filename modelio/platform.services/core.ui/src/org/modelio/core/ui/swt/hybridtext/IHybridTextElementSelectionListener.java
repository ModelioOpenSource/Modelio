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

package org.modelio.core.ui.swt.hybridtext;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("032eb045-36d8-47d0-af4e-096c5d72035d")
public interface IHybridTextElementSelectionListener {
    /**
     * Called when the selected element changes.
     * @param oldElement previously selected element. May be null.
     * @param newElement newly selected element. May be null.
     */
    @objid ("3701b524-c347-4178-b5f2-9e7824613e3b")
    void selectedElementChanged(final Object oldValue, final Object newValue);

}
