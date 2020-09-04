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

package org.modelio.script;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Services publicly available from the script input view.
 */
@objid ("00085976-6478-105c-84ef-001ec947cd2a")
public interface IInputView {
    /**
     * Get the input view content.
     * @return the content
     */
    @objid ("00086b96-6478-105c-84ef-001ec947cd2a")
    String getText();

    /**
     * Append a string to the input view.
     * @param content The string to append
     */
    @objid ("00088676-6478-105c-84ef-001ec947cd2a")
    void append(String content);

}
