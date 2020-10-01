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

package org.modelio.diagram.browser.model.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.DiagramSet;

@objid ("00338a1a-0d4f-10c6-842f-001ec947cd2a")
public class DiagramRef implements IAdaptable {
    @objid ("0033947e-0d4f-10c6-842f-001ec947cd2a")
    private final AbstractDiagram referencedDiagram;

    @objid ("0033ae0a-0d4f-10c6-842f-001ec947cd2a")
    private final DiagramSet referenceOwner;

    @objid ("0033c05c-0d4f-10c6-842f-001ec947cd2a")
    public DiagramRef(AbstractDiagram referencedDiagram, DiagramSet referenceOwner) {
        this.referencedDiagram = referencedDiagram;
        this.referenceOwner = referenceOwner;
    }

    @objid ("0033f16c-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.referenceOwner == null) ? 0 : this.referenceOwner.hashCode());
        result = prime * result + ((this.referencedDiagram == null) ? 0 : this.referencedDiagram.hashCode());
        return result;
    }

    @objid ("00341610-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DiagramRef)) {
            return false;
        }
        DiagramRef other = (DiagramRef) obj;
        if (this.referenceOwner == null) {
            if (other.referenceOwner != null) {
                return false;
            }
        } else if (!this.referenceOwner.equals(other.referenceOwner)) {
            return false;
        }
        if (this.referencedDiagram == null) {
            if (other.referencedDiagram != null) {
                return false;
            }
        } else if (!this.referencedDiagram.equals(other.referencedDiagram)) {
            return false;
        }
        return true;
    }

    @objid ("003440ae-0d4f-10c6-842f-001ec947cd2a")
    public AbstractDiagram getReferencedDiagram() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.referencedDiagram;
    }

    @objid ("003495cc-0d4f-10c6-842f-001ec947cd2a")
    public DiagramSet getReferenceOwner() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.referenceOwner;
    }

    @objid ("0034be80-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public String toString() {
        return "DiagramRef [referenceOwner=" + this.referenceOwner + ", referencedDiagram=" + this.referencedDiagram + "]";
    }

    @objid ("3430672c-67b7-4ea5-b8b8-c7a8ea3557cc")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter.isInstance(this.referencedDiagram))
            return adapter.cast(this.referencedDiagram);
        return null;
    }

}
