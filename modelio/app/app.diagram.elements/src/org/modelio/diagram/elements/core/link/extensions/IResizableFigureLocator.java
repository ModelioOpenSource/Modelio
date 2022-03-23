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
package org.modelio.diagram.elements.core.link.extensions;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.Locator;

/**
 * Locator extension for locators that support constraining the figure size.
 * @author cmarin
 * @since Modelio 3.4
 */
@objid ("89ee5b0d-f12a-4cbd-976c-6cd6d9cb5941")
public interface IResizableFigureLocator extends Locator {
    /**
     * Get the width constraint.
     * <p>
     * -1 means no constraint.
     * @return the width constraint.
     */
    @objid ("92c331b7-5609-4770-9b2b-b79396ce62c9")
    int getWidthConstraint();

    /**
     * Set the width constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedWidth the width constraint.
     */
    @objid ("0cd5ba85-95ba-4f36-b1ae-6817e4f0c1c9")
    void setWidthConstraint(int fixedWidth);

    /**
     * Get the height constraint.
     * <p>
     * -1 means no constraint.
     * @return the height constraint.
     */
    @objid ("32e59d6f-3863-4df9-8ec6-e7df554c790e")
    int getHeightConstraint();

    /**
     * Set the height constraint.
     * <p>
     * -1 means no constraint.
     * @param fixedHeight the height constraint.
     */
    @objid ("46652d74-4d9d-4d9c-936c-ec13e8fdd008")
    void setHeightConstraint(int fixedHeight);

}
