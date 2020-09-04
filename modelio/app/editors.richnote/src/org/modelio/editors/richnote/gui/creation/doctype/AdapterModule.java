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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

/**
 * Adapter for a module tree node.
 */
@objid ("e37f1b50-5621-4265-9ad2-ca8b43b52e79")
class AdapterModule implements IAdaptable {
    @objid ("12fd5c9d-d857-46f4-b10b-97712d88dc64")
    private List<IAdaptable> adapters;

    @objid ("0d029d77-273e-4d4f-8f27-4cbd177b7a92")
    private ModuleComponent module;

    @objid ("6132ccb5-32d9-4c16-8a8a-cdbe5ba38dbc")
    public AdapterModule(final ModuleComponent module) {
        this.module = module;
        this.adapters = new ArrayList<>();
    }

    @objid ("f0ad5cd6-3b4d-4b94-9b09-9b4c5e7184ec")
    @Override
    public <T> T getAdapter(Class<T> adapter) {
        return null;
    }

    @objid ("15f4d61d-0719-4409-a0b8-abad48a7d021")
    public AdapterStereotype getAdapter(Stereotype stereotype) {
        for (IAdaptable adpater : this.adapters) {
            if (adpater instanceof AdapterStereotype) {
                AdapterStereotype stereotypeAdapter = (AdapterStereotype) adpater;
                if (stereotypeAdapter.getStereotype().equals(stereotype)) {
                    return stereotypeAdapter;
                }
            }
        }
        return null;
    }

    /**
     * Get accessor for adapters
     * 
     * @return the child document type adapters.
     */
    @objid ("0069b6ef-88e8-400c-bb83-c14ed9bc157e")
    public List<IAdaptable> getAdapters() {
        return this.adapters;
    }

    /**
     * Get accessor for mdac
     * 
     * @return the module.
     */
    @objid ("92a24bc6-b13c-4f0c-8273-13f40f6f0b8c")
    public ModuleComponent getMdac() {
        return this.module;
    }

    @objid ("5ebe88ad-7657-4b59-bf50-a32a42862a61")
    public void removeStereotype(AdapterRichNoteType adapter) {
        this.adapters.remove(adapter);
    }

    @objid ("4f56468d-d60d-4bd9-b59d-9a4c61bb3182")
    void addDocType(ResourceType noteType) {
        Stereotype documented = noteType.getOwnerStereotype();
        
        if (documented == null) {
            AdapterRichNoteType adapter = new AdapterRichNoteType(noteType, this);
            this.adapters.add(adapter);
        } else {
            for (IAdaptable adapter : this.adapters) {
                if (adapter instanceof AdapterStereotype) {
                    AdapterStereotype stereotypeAdapter = (AdapterStereotype) adapter;
                    Stereotype currentStereotype = stereotypeAdapter.getStereotype();
                    if (isSubStereotype(documented, currentStereotype)) {
                        stereotypeAdapter.addDocType(noteType);
                    }
                }
            }
        }
    }

    @objid ("32c7f18e-fedc-4422-af9d-7944a9263ead")
    void addStereotype(Stereotype stereotype) {
        AdapterStereotype adapter = new AdapterStereotype(stereotype, this);
        this.adapters.add(adapter);
    }

    @objid ("36d0b039-1e34-4c14-9922-29f9ebc563fb")
    private boolean isSubStereotype(Stereotype parentStereotype, Stereotype currentStereotype) {
        if (currentStereotype == null) {
            return false;
        } else if (parentStereotype.equals(currentStereotype)) {
            return true;
        } else {
            Stereotype stereotype = currentStereotype.getParent();
            boolean result = false;
            if (stereotype != null) {
                result = isSubStereotype(parentStereotype, stereotype);
            }
            return result;
        }
    }

}
