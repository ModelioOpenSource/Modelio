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

package org.modelio.api.impl.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.modelio.model.IImageService;
import org.modelio.api.module.IPeerModule;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class implements the API to get the image of a model element.
 */
@objid ("cd3f4d4a-a4b4-4785-af85-0037f7ba6d09")
public class ImageService implements IImageService {
    @objid ("81329ec5-1785-441e-a295-7427bb85fb39")
    private MMetamodel metamodel;

    /**
     * C'tor
     * 
     * @param metamodel the metamodel used in the open project.
     */
    @objid ("bceb9603-dd15-46a7-8e54-8188344546e4")
    public ImageService(MMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    @objid ("7783b96e-8086-4302-8b99-0d96ba6a6ba4")
    @Override
    public Image getIcon(final MObject element) {
        return ElementImageService.getIcon(element);
    }

    @objid ("f1537e43-1a44-4f07-9da5-687e2bacd67d")
    @Override
    public Image getIcon(final Class<? extends MObject> metaclass) {
        return MetamodelImageService.getIcon(this.metamodel.getMClass(metaclass));
    }

    @objid ("7f20b490-605f-453c-9376-03391306366f")
    @Override
    public Image getIcon(final MObject element, final IPeerModule filter) {
        if (filter != null) {
            if (element instanceof ModelElement) {
                return ModuleI18NService.getIcon((ModelElement) element, filter);
            } else {
                return null;
            }
        } else {
            return ElementImageService.getIcon(element);
        }
    }

    @objid ("9f4bb189-eac8-4b57-8ae0-6394318f7427")
    @Override
    public Image getIcon(MClass metaclass) {
        return MetamodelImageService.getIcon(metaclass);
    }

    @objid ("3a986efd-99c8-494f-b968-af00f0a2a5ac")
    @Override
    public Image getImage(MObject element) {
        return ElementImageService.getImage(element);
    }

    @objid ("7492612f-42a2-4008-86d5-930e3b1bf1b4")
    @Override
    public Image getImage(Class<? extends MObject> metaclass) {
        return MetamodelImageService.getImage(this.metamodel.getMClass(metaclass));
    }

    @objid ("bf8dbab6-1bf2-46b4-bab9-d4f4bcedf738")
    @Override
    public Image getImage(MObject element, IPeerModule filter) {
        if (filter != null) {
            if (element instanceof ModelElement) {
                return ModuleI18NService.getImage((ModelElement) element, filter);
            } else {
                return null;
            }
        } else {
            return ElementImageService.getImage(element);
        }
    }

    @objid ("c29a6704-09f0-4fad-aa80-4376ac708075")
    @Override
    public Image getImage(MClass metaclass) {
        return MetamodelImageService.getImage(metaclass);
    }

    @objid ("6a08afb4-b48c-42a1-bc22-8dbe38c0982b")
    @Override
    public QualifiedImage getQualifiedIcon(final MObject element, final IPeerModule filter) {
        if (filter != null) {
            if (element instanceof ModelElement) {
                return ModuleI18NService.getQualifiedIcon((ModelElement) element, filter);
            } else {
                return null;
            }
        } else {
            return ElementImageService.getQualifiedIcon(element);
        }
    }

    @objid ("11b0e67b-f0f8-4de4-9f46-9d7f1162e51d")
    @Override
    public QualifiedImage getQualifiedIcon(MClass metaclass) {
        return MetamodelImageService.getQualifiedIcon(metaclass, null);
    }

    @objid ("0f0a1554-e1a9-4b18-9222-8eed50459d53")
    @Override
    public QualifiedImage getQualifiedImage(final MObject element, final IPeerModule filter) {
        if (filter != null) {
            if (element instanceof ModelElement) {
                return ModuleI18NService.getQualifiedImage((ModelElement) element, filter);
            } else {
                return null;
            }
        } else {
            return ElementImageService.getQualifiedImage(element);
        }
    }

    @objid ("c75c8dbe-f834-4fb5-8fed-da97e5c2fdc8")
    @Override
    public QualifiedImage getQualifiedImage(MClass metaclass) {
        return MetamodelImageService.getQualifiedImage(metaclass, null);
    }

    @objid ("a4b49ab5-05cb-42c4-8a5f-efccb04cbc0f")
    @Override
    public Image getStereotypeIcon(final Stereotype stereotype) {
        return ModuleI18NService.getIcon(stereotype);
    }

    @objid ("09d7e8aa-5f78-409a-8e7b-564c6dd9aa61")
    @Override
    public Image getStereotypeImage(final Stereotype stereotype) {
        return ModuleI18NService.getImage(stereotype);
    }

}
