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

package org.modelio.model.property.stereotype.chooser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;

@objid ("d6c3cdc8-c1f1-49cd-a841-435b9fec3465")
public class StereotypeChooserContentProvider implements ITreeContentProvider {
    @objid ("a223a0af-7228-45a9-9f8d-938a2d7d9d64")
    private IMModelServices modelService;

    @objid ("50d30d13-f844-41c8-8c84-cf036c0073ae")
    private ModelElement element;

    @objid ("17e43aa5-4bbc-4097-8010-235cedf41d29")
    @Override
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    @objid ("6079ff2f-8b6b-41a2-8a22-674d55a45a8c")
    @Override
    public void dispose() {
        // Nothing to dispose
    }

    @objid ("4c727461-0085-4ce8-903a-702cf41870c5")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Nothing to do
    }

    @objid ("4e5396b0-6292-4a16-aecf-ba3f6ab8707e")
    @Override
    public Object[] getChildren(Object parent) {
        Set<Object> ret = new HashSet<>();
        if (parent instanceof StereotypeChooserModel) {
            StereotypeChooserModel model = (StereotypeChooserModel) parent;
            this.element = model.getElement();
        
            List<Stereotype> stereotypes = this.modelService.findStereotypes(".*", ".*", this.element.getMClass());
            for (Stereotype stereotype : stereotypes) {
                if (!stereotype.isIsHidden() && !stereotype.isShell()) {
                    ModuleComponent module = stereotype.getOwner().getOwnerModule();
                    if (module != null) {
                        ret.add(module);
                    }
                }
            }
        } else if (parent instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) parent;
        
            List<Stereotype> stereotypes = this.modelService.findStereotypes(moduleComponent.getName(), ".*", this.element.getMClass());
            for (Stereotype stereotype : stereotypes) {
                if (!stereotype.isIsHidden()) {
                    ret.add(stereotype);
                }
            }
        }
        return ret.toArray();
    }

    @objid ("886fbcac-1170-4c07-b3d1-6639db33dafd")
    @Override
    public Object getParent(Object child) {
        if (child instanceof Stereotype) {
            return ((Stereotype) child).getOwner().getOwnerModule();
        } else {
            return null;
        }
    }

    @objid ("a404dadb-2021-4911-8be2-de1e680c7555")
    @Override
    public boolean hasChildren(Object parent) {
        if (parent instanceof StereotypeChooserModel) {
            StereotypeChooserModel model = (StereotypeChooserModel) parent;
            this.element = model.getElement();
        
            List<Stereotype> stereotypes = this.modelService.findStereotypes(".*", ".*", this.element.getMClass());
            for (Stereotype stereotype : stereotypes) {
                if (!stereotype.isIsHidden()) {
                    ModuleComponent module = stereotype.getOwner().getOwnerModule();
                    if (module != null) {
                        return true;
                    }
                }
            }
        } else if (parent instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) parent;
        
            List<Stereotype> stereotypes = this.modelService.findStereotypes(moduleComponent.getName(), ".*", this.element.getMClass());
            for (Stereotype stereotype : stereotypes) {
                if (!stereotype.isIsHidden()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Constructor initializing the model service.
     * 
     * @param modelService the model service needed to find elements.
     */
    @objid ("0dbdff71-379e-4cb2-bf4f-743bef84fd18")
    public StereotypeChooserContentProvider(IMModelServices modelService) {
        this.modelService = modelService;
    }

}
