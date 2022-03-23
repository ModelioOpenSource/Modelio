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
package org.modelio.diagram.elements.core.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmLinkable;

/**
 * This interface is intended to be implemented by commands that create nodes or links.
 * A node creation command implementing <code>ICreationCommand</code> shall provide the GmModel of the nodeor link it creates.
 * Such commands can therefore be used to chain other commands.<p/>
 * See {@link DefaultCreateElementCommand} for an implementation example.
 */
@objid ("7ee6ad2f-c5a1-45ec-ac86-c1080939f00f")
public interface ICreationCommand<T extends IGmLinkable> {
    /**
     * Get the created graphic model.
     * <p>
     * This method should be called only once the command has been executed.
     * in the other case the result is not specified.
     * @return the created graphic model.
     */
    @objid ("3dbaba78-e52e-450b-8b0f-b6dd15fb4b86")
    T getCreatedGraphicModel();

}
