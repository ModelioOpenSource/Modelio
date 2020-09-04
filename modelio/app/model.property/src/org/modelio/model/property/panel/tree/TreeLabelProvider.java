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

package org.modelio.model.property.panel.tree;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.StandardMetamodel;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.ui.UIImages;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Label provider of the annotation tree.
 */
@objid ("8faeec7a-c068-11e1-8c0a-002564c97630")
public class TreeLabelProvider extends LabelProvider {
    @objid ("a5dcd203-ed37-4b8a-aa1f-014ba77b0933")
    private Image moduleDefaultIcon;

    @objid ("8faeec7e-c068-11e1-8c0a-002564c97630")
    @Override
    public Image getImage(Object element) {
        // Stereotype
        if (element instanceof Stereotype) {
            return getStereotypeIcon((Stereotype) element);
        } else if (element instanceof ModuleComponent) {
            return getModuleIcon((ModuleComponent) element);
        } else if (element instanceof MClass) {
            return MetamodelImageService.getIcon((MClass) element, null);
        } else if (element instanceof PropertyTableDefinition) {
            return MetamodelImageService.getIcon(((PropertyTableDefinition) element).getMClass(), null);
        }
        return null;
    }

    @objid ("8faeec84-c068-11e1-8c0a-002564c97630")
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
        
        // PropertyTableDefinition
        if (element instanceof PropertyTableDefinition) {
            final PropertyTableDefinition table = (PropertyTableDefinition) element;
            // FIXME i18n
            return table.getName();
        }
        
        // Others (Element)
        if (element instanceof MClass) {
            MClass mClass = (MClass) element;
            String fragment = mClass.getOrigin().getName();
            String prefix;
            if (fragment.equals(StandardMetamodel.NAME)) {
                prefix = mClass.getName().startsWith("Bpmn") ? "BPMN" : "UML";
            } else {
                prefix = fragment;
            }
            return prefix + " - " + mClass.getName();
        } else {
            return "?" + element.toString();
        }
    }

    @objid ("8faeec89-c068-11e1-8c0a-002564c97630")
    private Image getStereotypeIcon(Stereotype stereotype) {
        Image icon = null;
        
        // If it is valid, get the stereotype image
        if (stereotype.isValid()) {
            icon = ModuleI18NService.getIcon(stereotype);
        }
        
        // If null, use the default stereotype icon
        if (icon == null) {
            icon = UIImages.DOT;
        }
        return icon;
    }

    /**
     * Constructor creating the images.
     */
    @objid ("8faeec90-c068-11e1-8c0a-002564c97630")
    public TreeLabelProvider() {
        this.moduleDefaultIcon = ModelProperty.getImageDescriptor("icons/moduleproptable.png").createImage();
    }

    @objid ("8faeec92-c068-11e1-8c0a-002564c97630")
    @Override
    public void dispose() {
        if (this.moduleDefaultIcon != null) {
            this.moduleDefaultIcon.dispose();
            this.moduleDefaultIcon = null;
        }
        
        super.dispose();
    }

    @objid ("c374c119-3e17-11e2-b901-002564c97630")
    private Image getModuleIcon(ModuleComponent moduleComponent) {
        Image icon = null;
        
        // If it is valid, get the module image
        if (moduleComponent.isValid()) {
            icon = ModuleI18NService.getModuleImage(moduleComponent);
        }
        
        // If null, use the default module icon
        if (icon == null) {
            icon = this.moduleDefaultIcon;
        }
        return icon;
    }

}
