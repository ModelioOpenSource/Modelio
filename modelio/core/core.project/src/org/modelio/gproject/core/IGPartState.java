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
package org.modelio.gproject.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Holds a {@link IGPart} state at run-time.
 */
@objid ("124e9571-0ad0-4d56-9c19-af289e8702ad")
public interface IGPartState {
    @objid ("502d61c4-cf7c-4f4a-8c18-2fdea825b72c")
    GPartStateEnum getValue();

    @objid ("825e25ba-58e8-405a-9209-c36fbc699735")
    Throwable getDownError();

    /**
     * Replace the down state error.
     * <p>
     * Must be called only if the part is already down.
     * @param e the new down error
     */
    @objid ("ef25d2b6-3ca8-4dc8-999e-3fc21c096691")
    void setDownError(Throwable e);

    /**
     * All possible part states.
     */
    @objid ("f3fc900a-0f97-4165-99a1-6ffb47d89e38")
    enum GPartStateEnum {
        /**
         * Initial state.
         */
        @objid ("3855caa2-85a9-4562-bc51-667353bdc310")
        INSTANTIATED,
        /**
         * Installed
         */
        @objid ("5f79292a-c9e8-4f28-8afd-689668d0efaa")
        INSTALLED,
        /**
         * Being mounted.
         */
        @objid ("8c7b44b7-90a4-4b6d-93ec-b2539cff9c01")
        MOUNTING,
        /**
         * Mounted operational.
         */
        @objid ("26cb2c64-1a1c-484f-98b2-ae8c477f346e")
        MOUNTED,
        /**
         * Down, broken.
         */
        @objid ("ef500997-9fab-4360-9a30-0127a244c95a")
        DOWN,
        /**
         * Uninstalled, terminal state
         */
        @objid ("0251867e-4756-434c-8926-8e6dbf4dd3c6")
        UNINSTALLED;

    }
}

