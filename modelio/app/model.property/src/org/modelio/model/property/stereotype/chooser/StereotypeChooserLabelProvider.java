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

package org.modelio.model.property.stereotype.chooser;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.IModelioElementLabelProvider;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.ui.UIImages;

/**
 * Default label provider for the note chooser dialog.
 */
@objid ("a4375516-7c8e-42ce-b70f-198f1ddfddab")
public class StereotypeChooserLabelProvider extends LabelProvider implements IModelioElementLabelProvider {
    @objid ("ca5efc3a-fad2-4e37-9d7c-6c04a195bcd8")
    @Override
    public Image getImage(Object element) {
        if (element instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) element;
            return ModuleI18NService.getModuleImage(moduleComponent);
        } else if (element instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) element;
            Image image = ModuleI18NService.getIcon(stereotype);
            if (image != null) {
                return image;
            }
            return UIImages.DOT;
        }
        return null;
    }

    @objid ("b2d7dece-82e3-4ee4-b1ff-ac73c2396a9d")
    @Override
    public String getText(Object element) {
        if (element instanceof ModuleComponent) {
            ModuleComponent moduleComponent = (ModuleComponent) element;
            return ModuleI18NService.getLabel(moduleComponent);
        } else if (element instanceof Stereotype) {
            StringBuffer noteTypeLabel = new StringBuffer();
            Stereotype stereotype = (Stereotype) element;
        
            noteTypeLabel.append("<<");
            String label = ModuleI18NService.getLabel(stereotype);
            if (!"".equals(label)) {
                noteTypeLabel.append(label);
            } else {
                noteTypeLabel.append(stereotype.getName());
            }
            noteTypeLabel.append(">>");
        
            return noteTypeLabel.toString();
        } else {
            return element.toString();
        }
    }

    @objid ("d7e3665d-0d73-42ee-8775-e402f70c6981")
    @Override
    public String getToolTipText(Object element) {
        if (element instanceof Stereotype) {
            Stereotype stereotype = (Stereotype) element;
            return ModuleI18NService.getDescription(stereotype);
        } else {
            return null;
        }
    }

    @objid ("98c7d17f-c83f-489d-9f33-26a6e6db4353")
    @Override
    public StyledString getStyledText(Object element) {
        return new StyledString(getText(element));
    }

    @objid ("85b61b6b-ebc8-4671-b65c-aeb3d0487530")
    @Override
    public boolean showAsReference(Object object) {
        return false;
    }

}
