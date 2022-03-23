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
import org.eclipse.core.runtime.ListenerList;
import org.modelio.editors.texteditors.input.IInput.Listener;

/**
 * Base implementation of {@link IInput}.
 * <p>
 * To be redefined by concrete implementations.
 */
@objid ("7b51ad32-2a77-11e2-9fb9-bc305ba4815c")
public abstract class AbstractInput implements IInput {
    @objid ("7b51ad34-2a77-11e2-9fb9-bc305ba4815c")
    private boolean dirty = false;

    @objid ("7b51ad33-2a77-11e2-9fb9-bc305ba4815c")
    private ListenerList listeners = new ListenerList();

    @objid ("7b51ad35-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    @objid ("7b5333d2-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    @objid ("7b5333d5-2a77-11e2-9fb9-bc305ba4815c")
    private void fireDirtyChange(boolean oldValue, boolean newValue) {
        this.dirty = newValue;
        for( Object l : this.listeners.getListeners() ) {
            ((Listener)l).propertyChanged(DIRTY, oldValue, newValue);
        }
        
    }

    @objid ("7b5333d9-2a77-11e2-9fb9-bc305ba4815c")
    protected void setDirty(boolean dirty) {
        if( dirty != this.dirty ) {
            fireDirtyChange(this.dirty, this.dirty = dirty);
        }
        
    }

    /**
     * @return whether the input needs saving.
     */
    @objid ("7b5333dc-2a77-11e2-9fb9-bc305ba4815c")
    public boolean isDirty() {
        return this.dirty;
    }

}
