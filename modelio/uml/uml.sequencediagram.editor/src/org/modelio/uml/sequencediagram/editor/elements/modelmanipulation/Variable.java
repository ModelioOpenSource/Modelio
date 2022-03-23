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
package org.modelio.uml.sequencediagram.editor.elements.modelmanipulation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * A simple object used to provide a reference on an integer variable to a Predicate.
 */
@objid ("d96fdf7c-55b6-11e2-877f-002564c97630")
public class Variable {
    @objid ("d96fdf7e-55b6-11e2-877f-002564c97630")
    private int value;

    @objid ("61a02c66-02f5-488d-81cb-e2f53d2f3210")
    private boolean updated = false;

    @objid ("18af6dcf-31d5-4fe3-8dfb-97e408a7a2bf")
    private final TimeReference ref;

    /**
     * Returns the current integer value of this Variable object.
     * @return the current integer value.
     */
    @objid ("d96fdf7f-55b6-11e2-877f-002564c97630")
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the new value of this Variable object.
     * @param value the new integer value.
     */
    @objid ("d96fdf84-55b6-11e2-877f-002564c97630")
    public void setValue(final int value) {
        this.value = value;
    }

    @objid ("e8d5ee16-2b9d-4d1a-9018-ecabe16f2632")
    public  Variable(TimeReference ref) {
        this.ref = ref;
    }

    @objid ("be8dda9e-63fd-4afa-ac4b-6e11eeb51781")
    public TimeReference getRef() {
        return this.ref;
    }

    @objid ("cd5448b4-b61f-43ee-a4a8-e60e0f49bea0")
    public boolean isUpdated() {
        return this.updated;
    }

    @objid ("44b332c0-f223-4944-8a9e-135c87dc06be")
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @objid ("225223f7-7b38-495d-b004-9dbd80be7d49")
    @Override
    public String toString() {
        return this.ref + "=" + this.value;
    }

}
