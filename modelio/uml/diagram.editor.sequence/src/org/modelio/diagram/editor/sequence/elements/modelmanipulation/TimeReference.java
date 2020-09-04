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

package org.modelio.diagram.editor.sequence.elements.modelmanipulation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A structure used to describe a point in time in a Sequence.
 * <p>
 * For example, this might either describe an ExecutionOccurrenceSpecification (which is a "point") or the beginning or end of an InteractionUse (which is a "box"), etc.
 */
@objid ("d96e58da-55b6-11e2-877f-002564c97630")
public class TimeReference {
    @objid ("0bb9e687-8a9e-487c-997f-ccc07bf6c111")
    private Discriminant discriminant = null;

    @objid ("fe21a2a8-4ae4-4c8b-ad2d-5d17b636b648")
    private MObject modelElement;

    @objid ("d96fdf5c-55b6-11e2-877f-002564c97630")
    public TimeReference(final MObject modelElement, final Discriminant discriminant) {
        this.modelElement = modelElement;
        this.discriminant = discriminant;
    }

    @objid ("d96fdf64-55b6-11e2-877f-002564c97630")
    public TimeReference(final MObject modelElement) {
        this.modelElement = modelElement;
    }

    @objid ("d96fdf6a-55b6-11e2-877f-002564c97630")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.discriminant == null) ? 0 : this.discriminant.hashCode());
        result = prime * result + ((this.modelElement == null) ? 0 : this.modelElement.hashCode());
        return result;
    }

    @objid ("d96fdf6f-55b6-11e2-877f-002564c97630")
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
        TimeReference other = (TimeReference) obj;
        if (this.discriminant == null) {
            if (other.discriminant != null) {
                return false;
            }
        } else if (this.discriminant != other.discriminant) {
            return false;
        }
        if (this.modelElement == null) {
            if (other.modelElement != null) {
                return false;
            }
        } else if (!this.modelElement.equals(other.modelElement)) {
            return false;
        }
        return true;
    }

    @objid ("eca89d93-9ded-4358-af6c-2fff8145db09")
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.modelElement);
        if (this.discriminant != null) {
            sb.append("[");
            sb.append(this.discriminant.name());
            sb.append("]");
        }
        return sb.toString();
    }

    @objid ("797b694e-f56e-439e-9f51-f46e64c44cf7")
    public MObject getElement() {
        return this.modelElement;
    }

    /**
     * Enumeration used to distinguish specific cases, like the beginning and end of an InteractionUse.
     */
    @objid ("d96fdf76-55b6-11e2-877f-002564c97630")
    public enum Discriminant {
        BEGINNING,
        END;
    }

}
