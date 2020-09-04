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

package org.modelio.ui.htmleditor.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author Tom Seidel <tom.seidel@remus-software.org>
 */
@objid ("05c9d42e-4455-48ca-b271-408fb9a5369a")
public enum TriState {
    ON ("1.0"),
    OFF ("2.0"),
    DISABLED ("3.0");

    @objid ("db2457bd-0dd4-4297-8804-3274b590bfb0")
    private final String representation;

    @objid ("89e02ee4-3e0a-45a8-8db8-bc9a85843a68")
    TriState(String representation) {
        this.representation = representation;
    }

    @objid ("9821d862-0b24-486a-9b66-c3ce8560c8c2")
    public static TriState fromString(String str) {
        for (final TriState state : TriState.values()) {
            if (state.representation.equals(str)) {
                return state;
            }
        }
        return DISABLED;
    }

    @objid ("b90c9b18-2ad4-4b6c-82d8-bf75c51d2917")
    public String getRepresentation() {
        return this.representation;
    }

}
