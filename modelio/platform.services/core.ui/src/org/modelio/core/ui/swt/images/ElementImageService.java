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

package org.modelio.core.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.app.core.metamodel.MetamodelExtensionPoint;
import org.modelio.core.ui.swt.images.spi.IElementImageProvider;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service to get an icon or an image for a model object.
 */
@objid ("004d5b20-5f0b-100d-835d-001ec947cd2a")
public class ElementImageService {
    @objid ("36cb23bf-cadd-4c0c-bd26-532f1c79821b")
    private static final String EXTENSION_POINT_ID = "org.modelio.app.metamodel.element.image";

    @objid ("5b19c81a-8d34-422d-ba6e-97342685f8f6")
    private static final MetamodelExtensionPoint<IElementImageProvider> extensionPoint = new MetamodelExtensionPoint<>(ElementImageService.EXTENSION_POINT_ID);

    /**
     * A so-called 'application' icon is defined as follows:
     * <ul>
     * <li>first stereotype icon found on the element. Note that 'first' is the order of the modules as returned by mda.infra, ie based on workbench</li>
     * <li>if no stereotype icon is found, return the metaclass icon</li>
     * </ul>
     * @param element a model object.
     * @return an 'application' icon for the element.
     */
    @objid ("00691036-a3a1-100e-835d-001ec947cd2a")
    public static Image getIcon(MObject element) {
        try {
            // Only model elements can have stereotypes
            Image icon = element instanceof ModelElement ? ElementImageService.getDisplayedStereotypeIcon((ModelElement) element) : null;
            if (icon != null) {
                return icon;
            }
        
            IElementImageProvider svc = ElementImageService.extensionPoint.get(element);
            if (svc != null) {
                return svc.getIcon(element);
            }
        
            return MetamodelImageService.getIcon(element.getMClass(), ElementImageService.getFlavor(element));
        } catch (DeadObjectException e) {
            return MetamodelImageService.getIcon(element.getMClass(), null);
        }
    }

    /**
     * A so-called 'application' image is defined as follows:
     * <ul>
     * <li>first stereotype image found on the element. Note that 'first' is the order of the modules as returned by mda.infra, ie based on workbench</li>
     * <li>if no stereotype image is found, return the metaclass image</li>
     * </ul>
     * @param element a model object.
     * @return an 'application' image for the element.
     */
    @objid ("006939f8-a3a1-100e-835d-001ec947cd2a")
    public static Image getImage(MObject element) {
        try {
            // Only model elements can have stereotypes
            Image image = element instanceof ModelElement ? ElementImageService.getDisplayedStereotypeImage((ModelElement) element) : null;
            if (image != null) {
                return image;
            }
        
            IElementImageProvider svc = ElementImageService.extensionPoint.get(element);
            if (svc != null) {
                return svc.getImage(element);
            }
        
            return MetamodelImageService.getImage(element.getMClass(), ElementImageService.getFlavor(element));
        } catch (DeadObjectException e) {
            return MetamodelImageService.getImage(element.getMClass(), null);
        }
    }

    /**
     * Returns the icon (usually 16*16) to use for an element having stereotypes.
     * @see ModuleI18NService#getIcon(ModelElement)
     */
    @objid ("006959ec-a3a1-100e-835d-001ec947cd2a")
    private static Image getDisplayedStereotypeIcon(ModelElement modelElement) {
        return ModuleI18NService.getIcon(modelElement, null);
    }

    /**
     * Returns the image (usually 32*32) to use for an element having stereotypes.
     * @see ModuleI18NService#getImage(ModelElement)
     */
    @objid ("00699790-a3a1-100e-835d-001ec947cd2a")
    private static Image getDisplayedStereotypeImage(ModelElement modelElement) {
        return ModuleI18NService.getImage(modelElement, null);
    }

    @objid ("0069b8b0-a3a1-100e-835d-001ec947cd2a")
    private static String getFlavor(MObject element) {
        return null;
    }

}
