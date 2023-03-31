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
package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface that allows the edition of a model element name, signature, or other attribute.
 */
@objid ("80801c6d-1dec-11e2-8cad-001ec947c8cc")
public interface IEditableText {
    /**
     * Get the text to display in the edit field.
     * <p>
     * This text is usually the element name. It could be the element signature.
     * @return the text to display in the edit field.
     */
    @objid ("80801c6f-1dec-11e2-8cad-001ec947c8cc")
    String getText();

    /**
     * Set the represented model element name or signature.
     * @param text the new element name or signature.
     */
    @objid ("80801c72-1dec-11e2-8cad-001ec947c8cc")
    void setText(String text);
}

