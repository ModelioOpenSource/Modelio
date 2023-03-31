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
package org.modelio.platform.model.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.metamodel.MetamodelExtensionPoint;
import org.modelio.platform.mda.infra.MdaResources;
import org.modelio.platform.model.ui.swt.images.spi.IElementImageProvider;
import org.modelio.platform.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.DeadObjectException;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Service to get an icon or an image for a model object.
 */
@objid ("004d5b20-5f0b-100d-835d-001ec947cd2a")
public class ElementImageService {
    @objid ("36cb23bf-cadd-4c0c-bd26-532f1c79821b")
    private static final String EXTENSION_POINT_ID = "org.modelio.platform.model.ui.element.imageprovider";

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
        QualifiedImage qualifiedIcon = getQualifiedIcon(element);
        return qualifiedIcon != null ? qualifiedIcon.getImage() : null;
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
        QualifiedImage qualifiedIcon = getQualifiedImage(element);
        return qualifiedIcon != null ? qualifiedIcon.getImage() : null;
    }

    @objid ("0069b8b0-a3a1-100e-835d-001ec947cd2a")
    private static String getFlavor(MObject element) {
        return null;
    }

    @objid ("1f5fab9f-cfc7-4323-a4b7-8741425f823f")
    public static QualifiedImage getQualifiedIcon(MObject element) {
        try {
            // Only model elements can have stereotypes
            QualifiedImage icon = element instanceof ModelElement ? MdaResources.getQualifiedIcon((ModelElement) element, null) : null;
            if (icon != null) {
                return icon;
            }
        
            IElementImageProvider svc = ElementImageService.extensionPoint.get(element);
            if (svc != null) {
                return svc.getIcon(element);
            }
        
            return MetamodelImageService.getQualifiedIcon(element.getMClass(), ElementImageService.getFlavor(element));
        } catch (DeadObjectException e) {
            return MetamodelImageService.getQualifiedIcon(element.getMClass(), null);
        }
        
    }

    @objid ("662f9841-6a96-4b6e-a215-987a71e1a290")
    public static QualifiedImage getQualifiedImage(MObject element) {
        try {
            // Only model elements can have stereotypes
            QualifiedImage image = element instanceof ModelElement ? MdaResources.getQualifiedImage((ModelElement) element, null) : null;
            if (image != null) {
                return image;
            }
        
            IElementImageProvider svc = ElementImageService.extensionPoint.get(element);
            if (svc != null) {
                return svc.getImage(element);
            }
        
            return MetamodelImageService.getQualifiedImage(element.getMClass(), ElementImageService.getFlavor(element));
        } catch (DeadObjectException e) {
            return MetamodelImageService.getQualifiedImage(element.getMClass(), null);
        }
        
    }

}
