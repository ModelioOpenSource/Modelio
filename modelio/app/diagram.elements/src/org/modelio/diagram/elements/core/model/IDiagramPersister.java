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

package org.modelio.diagram.elements.core.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.PersistenceException;

/**
 * Service to use to load and save diagram in the model
 * 
 * @author cma
 * @since 3.7
 */
@objid ("4b258979-2ae8-442b-b85b-601451eb5941")
public interface IDiagramPersister {
    /**
     * Save the diagram in the model.
     * 
     * @param withEmbeddeddiagrams if true, modifiable embedded diagrams will be saved too.
     * @throws org.modelio.diagram.persistence.PersistenceException on failure
     */
    @objid ("e99d752f-7403-493a-9601-907103a31e07")
    void save(boolean withEmbeddeddiagrams) throws PersistenceException;

    /**
     * Load the diagram from the model.
     * 
     * @throws org.modelio.diagram.persistence.PersistenceException on failure
     */
    @objid ("7f9f6dae-ce28-4d97-a1e8-61068a68a4da")
    void load() throws PersistenceException;

}
