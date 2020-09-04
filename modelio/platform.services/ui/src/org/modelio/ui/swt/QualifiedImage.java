/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.ui.swt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;

/**
 * Represents a metamodel or stereotype icon or image for a model element along with its qualifier.<br/>
 * The qualifier indicates the image origin, ie which image (or icon) has been chosen for the element. Note the image choice depends on the tool configuration and the element state when the QualifiedImage was retrieved and may vary during a Modelio
 * session. For example, installing or removing modules or changing the current workbench/expertises modifies the image choice algorythm.
 * 
 * Use QualifiedImage when both the image and its 'origin' are needed.
 * @since 4.0
 */
@objid ("00252efe-eb5c-4e44-8857-2e71c216402a")
public class QualifiedImage {
    @objid ("6777a093-5fa4-4892-acd0-ca7895d8e06c")
    private final String qualifier;

    @objid ("fe0929ff-8db5-4bea-ac65-aec1fb600fe6")
    private final Image image;

    @objid ("79b2f838-ad30-4070-8493-6a83a78852af")
    public QualifiedImage(Image image, String qualifier) {
        this.image = image;
        this.qualifier = qualifier;
    }

    @objid ("5eb5c2bc-3048-4253-a9ac-0c81ddc3cea5")
    public Image getImage() {
        return this.image;
    }

    @objid ("11c465a2-ec11-45b0-979b-6376fcfdee61")
    public String getQualifier() {
        return this.qualifier;
    }

}
