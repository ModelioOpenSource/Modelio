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

package org.modelio.linkeditor.gef.background.typeselection;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.linkeditor.LinkTypeDescriptor;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.ui.UIColor;

/**
 * Label and image provider for the type selection dialog.
 */
@objid ("1b960462-5e33-11e2-b81d-002564c97630")
class TypeSelectionLabelProvider extends LabelProvider implements IStyledLabelProvider {
    @objid ("353539ef-4e10-4f40-b2a0-d07f701d486d")
    private Styler auxStyler;

    @objid ("1b98658f-5e33-11e2-b81d-002564c97630")
    @Override
    public Image getImage(final Object element) {
        if (element instanceof LinkTypeDescriptor) {
            LinkTypeDescriptor type = (LinkTypeDescriptor) element;
            Image stImage = (type.getStereotype() != null) ? getStereotypeImage(type.getStereotype()) : null;
            return (stImage != null) ? stImage : MetamodelImageService.getIcon(type.getMClass());
        } else if (element instanceof ModuleComponent) {
            return getModuleImage((ModuleComponent) element);
        }
        return null;
    }

    @objid ("1b98659c-5e33-11e2-b81d-002564c97630")
    private Image getModuleImage(final ModuleComponent moduleComponent) {
        Image icon = null;
        
        // If it is valid, get the module image
        if (moduleComponent.isValid()) {
            icon = ModuleI18NService.getModuleImage(moduleComponent);
        }
        return icon;
    }

    @objid ("1b9865a2-5e33-11e2-b81d-002564c97630")
    private Image getStereotypeImage(final Stereotype stereotype) {
        Image image = null;
        
        // If it is valid, get the stereotype image
        if (stereotype.isValid()) {
            image = ModuleI18NService.getIcon(stereotype);
        }
        return image;
    }

    @objid ("c3d57e8c-b3f6-49ca-99f5-e1e910df1fb2")
    public TypeSelectionLabelProvider(Viewer viewer) {
        LocalResourceManager res = new LocalResourceManager(JFaceResources.getResources(), viewer.getControl());
        Font auxFont = res.createFont(FontDescriptor.createFrom(viewer.getControl().getFont()).withStyle(SWT.ITALIC));
        
        this.auxStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
                textStyle.font = auxFont;
                textStyle.foreground = UIColor.LABEL_TIP_FG;
            }
        };
    }

    @objid ("77c4193b-4ede-40a4-87b3-79fc8edf7eda")
    @Override
    public StyledString getStyledText(Object element) {
        if (element instanceof String) {
            return getStyledText((String) element);
        } else if (element instanceof ModuleComponent) {
            return getModuleLabel((ModuleComponent) element);
        } else if (element instanceof LinkTypeDescriptor) {
            return getStyledText((LinkTypeDescriptor) element);
        } else {
            return new StyledString(element.toString());
        }
    }

    @objid ("269f0a59-5d52-4cfb-b26d-35fce0f62749")
    private StyledString getStyledText(String groupName) {
        return new StyledString(groupName);
    }

    @objid ("954190bf-a27e-45ad-b030-d9cad966deca")
    private StyledString getStyledText(LinkTypeDescriptor type) {
        StyledString result = new StyledString();
        
        // If there is a stereotype use its name as the main text and complete it with the metaclass
        if (type.getStereotype() != null) {
            result.append("\u00AB ");
        
            String label = ModuleI18NService.getLabel(type.getStereotype());
            if (label == null || label.isEmpty()) {
                label = type.getStereotype().getName();
            }
        
            result.append(label);
            result.append(" \u00BB - ");
            result.append(type.getMClass().getQualifiedName(), this.auxStyler);
        
        } else {
            // no stereotype, use the metaclass qualified name
            result.append(type.getMClass().getQualifiedName());
        }
        return result;
    }

    @objid ("fa053c46-80bf-469d-a02a-9568508e73b9")
    private StyledString getModuleLabel(final ModuleComponent moduleComponent) {
        StyledString result = new StyledString();
        
        // If it is valid, get the module image
        if (moduleComponent.isValid()) {
            result.append(ModuleI18NService.getLabel(moduleComponent));
        }
        return result;
    }

}
