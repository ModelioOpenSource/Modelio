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
package org.modelio.diagram.elements.core.model.factory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Factory that creates graphic nodes representing a given model element.
 * <p>
 * Creating a {@link GmModel} from a {@link MObject} is also know as unmasking the element.
 * </p>
 */
@objid ("edbbdcf0-0317-414b-9a98-f37a7c216a7a")
public interface IGmNodeFactory {
    /**
     * Unmask an element under a composite graphic node.
     * @param diagram the diagram the gm node is created into.
     * @param parentNode the graphic node in which the element must be displayed
     * @param elementToUnmask The element to unmask
     * @param initialLayoutData The initial layout data of the node
     * @return The created graphic node
     */
    @objid ("5fe45970-4d0a-419c-9c6a-02557118bfa1")
    GmNodeModel create(IGmDiagram diagram, GmCompositeNode parentNode, MObject elementToUnmask, Object initialLayoutData);

    /**
     * Returns the {@link Class} object associated with the class with the given namespace.
     * <p>
     * Implementers should use this method when a Gm class is moved or renamed in order for the diagram persistence to properly instantiate persisted objects.
     * </p>
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("14726dde-e09f-4284-84a8-fab776d8e23c")
    Class<? extends IPersistent> resolveClass(String namespace);

    /**
     * Returns the {@link Class} object associated with the class with the given namespace.
     * <p>
     * Implementers should use this method when a Gm class is moved or renamed in order for the diagram persistence to properly instantiate these objects.
     * </p>
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("943ff73b-8bae-4537-b6a5-259d4523e480")
    Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace);

    /**
     * Returns the {@link Class} object associated with the migrator class with the given namespace.
     * <p>
     * Implementers should use this method when an enumeration class is moved or renamed in order for the diagram persistence to properly instantiate these objects.
     * </p>
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("a032e459-bf14-4450-aa9b-61327e97166c")
    Class<? extends Enum<?>> resolveEnumClass(String namespace);

    @objid ("9d4662bc-46a0-412a-8b07-999d35256903")
    default String migrateNamespace(String namespace) {
        return namespace;
    }
}

