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
package org.modelio.diagram.api.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.plugin.DiagramStyles;

@objid ("c24f9ec1-f6d5-4d25-a3ed-c7bde9d228e9")
public class StyleHandle implements IStyleHandle {
    @objid ("40248094-c886-464f-a89a-c8f865cde789")
    private final String name;

    @objid ("bfe2da51-0799-4e5a-b27e-77dfcd92f8de")
    private final NamedStyle style;

    @objid ("7fd9ef12-48c8-4630-a5f7-364ca48669ce")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("c599cf3f-9bec-47b0-87dd-3492e5785d41")
    public NamedStyle getStyle() {
        return this.style;
    }

    @objid ("c35f5032-a423-4591-9568-a9bfb1fb5f9b")
    public  StyleHandle(final String name) {
        this(DiagramStyles.getStyleManager().getStyle(name));
    }

    @objid ("a28a3b82-92b2-4ceb-a566-946a76bb29db")
    public  StyleHandle(final NamedStyle style) {
        this.style = style;
        this.name = style.getName();
        
    }

    @objid ("eb2b054c-b817-47f9-8edf-6d8121503ec6")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }

    @objid ("76f927ee-6116-4050-816b-d21dbefae387")
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
        final StyleHandle other = (StyleHandle) obj;
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
