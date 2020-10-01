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

package org.modelio.platform.model.ui.swt.images.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Interface to be implemented by a metamodel fragment related extension point to provide images for its metaclasses.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("4ab003c9-1c37-4a41-a21c-cb780f45a5c1")
public interface IMetamodelImageProvider {
    /**
     * Get the icon for a metaclass and a flavor
     * 
     * @param mClass a metaclass.
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the metaclass browser icon.
     */
    @objid ("1f660788-252a-4ad2-89a5-c81f4a61b0ed")
    QualifiedImage getIcon(MClass mClass, String flavor);

    /**
     * Get the image for a metaclass and a flavor
     * 
     * @param mClass a metaclass.
     * @param flavor a flavor to concatenate to the lookup key.
     * @return the metaclass diagram image.
     */
    @objid ("d5280b2a-5268-42eb-8ec9-bc885eedb756")
    QualifiedImage getImage(MClass mClass, String flavor);

    /**
     * Get the icon for a metaclass.
     * 
     * @param metaclassName a metaclass name.
     * @return the metaclass icon.
     * @deprecated this method is not fully compatible with metamodel extensions, use {@link #getIcon(MClass, String)} instead.
     */
    @objid ("59c4d899-55c9-4953-8ce5-5a4fa84a972b")
    @Deprecated
    QualifiedImage getIcon(String metaclassName);

    /**
     * Get the full physical path of a metaclass icon.
     * <p>
     * Usually needed when programmatically building e4 contributions.
     * </p>
     * 
     * @param metaclassName name of a metaclass
     * @return path to the found icon or <code>null</code>.
     * @since 3.8
     */
    @objid ("e4169a0e-7bb1-4f38-afc8-2add9cb455f4")
    String getIconCompletePath(String metaclassName);

    /**
     * Get the full physical path of a metaclass icon.
     * <p>
     * Usually needed when programmatically building e4 contributions.
     * </p>
     * 
     * @param metaclass a metaclass
     * @return path to the found icon or <code>null</code>.
     * @since 3.8
     */
    @objid ("8702e56b-c3e6-42f9-94d0-091c9a2db50a")
    String getIconCompletePath(MClass metaclass);

}
