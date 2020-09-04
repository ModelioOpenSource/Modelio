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

package org.modelio.editors.richnote.gui.creation.doctype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.modelio.metamodel.uml.infrastructure.ResourceType;

@objid ("9ccbaf16-0f24-4067-afb1-966684220662")
class AdapterRichNoteType implements IAdaptable {
    @objid ("f75091cd-fad0-4537-8c16-2c6c10122ef3")
    private ResourceType docType;

    @objid ("b24dd343-a07e-460f-9cd4-512f69730986")
    private IAdaptable parent;

    @objid ("810c6418-2cbc-48c2-b2d2-9b63daf48eb2")
    public AdapterRichNoteType(ResourceType noteType, IAdaptable parent) {
        this.docType = noteType;
        this.parent = parent;
    }

    @objid ("0b1311e0-713e-4abd-b36d-84532190b337")
    @SuppressWarnings ("unchecked")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (this.docType != null && adapter.isInstance(this.docType)) {
            return (T) this.docType;
        }
        return null;
    }

    /**
     * Get accessor for noteType
     * 
     * @return the document type.
     */
    @objid ("24cd6043-ed71-4be2-8dd5-2695604af250")
    public ResourceType getDocType() {
        return this.docType;
    }

    /**
     * Get accessor for parent
     * 
     * @return the parent node.
     */
    @objid ("7008d1e7-fe57-4435-b703-211e502ccd8e")
    public IAdaptable getParent() {
        return this.parent;
    }

}
