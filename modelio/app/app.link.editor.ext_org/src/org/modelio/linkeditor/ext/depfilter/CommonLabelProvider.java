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
package org.modelio.linkeditor.ext.depfilter;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Label provider for the Set dependency filter GUI
 */
@objid ("1b5ce363-5e33-11e2-b81d-002564c97630")
public class CommonLabelProvider extends LabelProvider {
    @objid ("1b5f44a7-5e33-11e2-b81d-002564c97630")
    @Override
    public Image getImage(Object element) {
        if (element instanceof ModuleComponent) {
            return getModuleImage((ModuleComponent) element);
        } else if (element instanceof Stereotype) {
            return getStereotypeImage((Stereotype) element);
        } else {
            return null;
        }
        
    }

    @objid ("1b5f44ac-5e33-11e2-b81d-002564c97630")
    @Override
    public String getText(Object element) {
        // Stereotype
        if (element instanceof Stereotype) {
            final StringBuffer stereotypeLabel = new StringBuffer();
            final Stereotype stereotype = (Stereotype) element;
            stereotypeLabel.append("\u00AB "); // "? " : ' <<'
            String label = ModuleI18NService.getLabel(stereotype);
            if (!label.isEmpty()) {
                stereotypeLabel.append(label);
            } else {
                stereotypeLabel.append(stereotype.getName());
            }
            stereotypeLabel.append(" \u00BB"); // " ?" : ' >>'
            return stereotypeLabel.toString();
        }
        
        // Module
        if (element instanceof ModuleComponent) {
            return ModuleI18NService.getLabel((ModuleComponent) element);
        }
        
        // Others (Element)
        if (element instanceof MClass) {
            return ((MClass) element).getName().startsWith("Bpmn") ? "BPMN" : "UML";
        } else {
            return "?" + element.toString();
        }
        
    }

    @objid ("1b5f44b1-5e33-11e2-b81d-002564c97630")
    private Image getModuleImage(final ModuleComponent moduleComponent) {
        Image image = null;
        
        // If it is valid, get the module image
        if (moduleComponent.isValid()) {
            image = ModuleI18NService.getModuleImage(moduleComponent);
        }
        return image;
    }

    @objid ("1b61a605-5e33-11e2-b81d-002564c97630")
    private Image getStereotypeImage(final Stereotype stereotype) {
        Image image = null;
        
        // If it is valid, get the stereotype image
        if (stereotype.isValid()) {
            image = ModuleI18NService.getIcon(stereotype);
        }
        return image;
    }

}
