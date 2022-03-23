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
package org.modelio.diagram.diagramauto.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.plugin.DiagramStyles;

@objid ("01f2926d-1d2d-4a14-bdf5-71684f013013")
public class DiagramStyleHandle implements IStyleHandle {
    @objid ("5f20624a-39b3-48dc-a6d8-a7bf62263f08")
    private final String name;

    @objid ("761b2fcc-33fe-4704-84bf-e42d36ad31a0")
    private final NamedStyle style;

    @objid ("cff80552-ca7c-46c5-9db6-a1fed5f5efb4")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("92c12e1d-ac8a-44a2-a08b-787d2079a869")
    public NamedStyle getStyle() {
        return this.style;
    }

    @objid ("419b7bfe-4d6f-465f-9d67-946b04aa21ce")
    public  DiagramStyleHandle(final String name) {
        this(DiagramStyles.getStyleManager().getStyle(name));
    }

    @objid ("301537ae-bf62-4f31-a756-41e46dec6833")
    public  DiagramStyleHandle(final NamedStyle style) {
        this.style = style;
        this.name = style.getName();
        
    }

    @objid ("fa6c55d1-50a1-4bd3-8af9-74331d10eab6")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @objid ("3d60fa01-ef16-4cd7-a229-77f1469b7394")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiagramStyleHandle other = (DiagramStyleHandle) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
