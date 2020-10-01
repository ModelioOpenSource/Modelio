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

package org.modelio.editors.texteditors.input;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IStatus;

@objid ("7b54ba83-2a77-11e2-9fb9-bc305ba4815c")
public interface IInput {
    @objid ("ab4d7087-2a77-11e2-9fb9-bc305ba4815c")
    public static final String DIRTY = IInput.class.getName() + ".dirty";

    @objid ("7b54ba8b-2a77-11e2-9fb9-bc305ba4815c")
    void addListener(Listener listener);

    @objid ("7b54ba8d-2a77-11e2-9fb9-bc305ba4815c")
    void removeListener(Listener listener);

    @objid ("7b54ba8f-2a77-11e2-9fb9-bc305ba4815c")
    IStatus save();

    @objid ("7b54ba86-2a77-11e2-9fb9-bc305ba4815c")
    interface Listener {
        @objid ("7b54ba87-2a77-11e2-9fb9-bc305ba4815c")
        void propertyChanged(String property, Object oldValue, Object newValue);

    }

}
