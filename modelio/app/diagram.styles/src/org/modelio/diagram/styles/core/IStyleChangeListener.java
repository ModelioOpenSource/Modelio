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

package org.modelio.diagram.styles.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents an element that depends on a {@link IStyle}.
 */
@objid ("8555e8ed-1926-11e2-92d2-001ec947c8cc")
public interface IStyleChangeListener {
    /**
     * Called when a property of the style of the element is modified.
     * <p>
     * The element should then update itself from the style change.
     * 
     * @param property The style property that changed
     * @param newValue The new property value
     */
    @objid ("8555e8ef-1926-11e2-92d2-001ec947c8cc")
    void styleChanged(StyleKey property, Object newValue);

    /**
     * Called when a style completely changed .
     * <p>
     * The element should then update itself completely from the style.
     * 
     * @param changedStyle The style that changed
     */
    @objid ("8555e8f3-1926-11e2-92d2-001ec947c8cc")
    void styleChanged(IStyle changedStyle);

}
