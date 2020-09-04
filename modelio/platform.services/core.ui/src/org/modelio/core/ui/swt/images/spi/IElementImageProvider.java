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

package org.modelio.core.ui.swt.images.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface to be implemented by a metamodel fragment related extension point
 * to provide images for its elements.
 * 
 * @author cmarin
 * @since 3.6
 */
@objid ("5be7bf84-9690-4596-a8b8-7480c144f72e")
public interface IElementImageProvider {
    /**
     * @param element an element
     * @return the element browser icon.
     */
    @objid ("120b6d6a-fa30-43b3-a7a5-ef96f71cc523")
    QualifiedImage getIcon(MObject element);

    /**
     * @param element an element
     * @return the element diagram image
     */
    @objid ("6d65c1eb-b79f-40f0-9cb3-8e3ee2755da2")
    QualifiedImage getImage(MObject element);

}
