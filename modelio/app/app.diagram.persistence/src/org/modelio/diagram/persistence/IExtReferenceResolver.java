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
package org.modelio.diagram.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * External reference resolver.
 * 
 * @author cmarin
 */
@objid ("cb6de7a3-186f-11e2-92d2-001ec947c8cc")
public interface IExtReferenceResolver {
    /**
     * Return an instance of the given class for the given diagram reader in its current state. {@link IDiagramReader
     * readXxxxAtt(String)} methods can be called to get more informations.
     * <p>
     * This method may create a new instance or return an existing one.
     * @param type The element type.
     * @param dbId The location of the external element. May be empty.
     * @param extId The external element id.
     * @return The created instance.
     * 
     * @exception PersistenceException
     * in case of failure.
     */
    @objid ("cb6de7a5-186f-11e2-92d2-001ec947c8cc")
    IPersistent resolveReference(String type, String dbId, String extId) throws PersistenceException;
}

