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
package org.modelio.edition.notes.constraintChooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.MdaResources;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;

/**
 * Default label provider for the constraint chooser dialog.
 */
@objid ("26d79eee-186f-11e2-bc4e-002564c97630")
public class ConstraintChooserLabelProvider extends LabelProvider {
    @objid ("26d79eef-186f-11e2-bc4e-002564c97630")
    @Override
    public Image getImage(Object element) {
        if (element instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) element;
            return MdaResources.getModuleImage(moduleComponent);
        } else if (element instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) element;
            Image image = MdaResources.getIcon(stereotype);
            if (image != null) {
                return image;
            }
        }
        return MetamodelImageService.getIcon(Constraint.MQNAME);
    }

    @objid ("26d79ef5-186f-11e2-bc4e-002564c97630")
    @Override
    public String getText(Object element) {
        if (element instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) element;
            String label = MdaResources.getLabel(moduleComponent);
            if (label != null && !label.isEmpty()) {
                return label;
            }
            return moduleComponent.getName();
        } else if (element instanceof Stereotype) {
            StringBuilder constraintTypeLabel = new StringBuilder();
            Stereotype stereotype = (Stereotype) element;
            String label = MdaResources.getLabel(stereotype);
            if (label != null && !label.isEmpty()) {
                constraintTypeLabel.append(label);
            } else {
                constraintTypeLabel.append(stereotype.getName());
            }
            return constraintTypeLabel.toString();
        } else {
            return EditionNotes.I18N.getString("Constraint");
        }
        
    }

}
