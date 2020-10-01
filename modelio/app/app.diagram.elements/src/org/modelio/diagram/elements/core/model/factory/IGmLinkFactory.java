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
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Factory that creates graphic links representing a given link element.
 */
@objid ("de985ebb-7579-4cff-a8b4-d751265e57a1")
public interface IGmLinkFactory {
    /**
     * Creates a graphic link representing the given link element.
     * 
     * @param diagram the diagram in which the gm is to be created
     * @param linkElement The model element to display
     * @return the created graphic link
     */
    @objid ("a746f2ce-4ad1-4350-b1d8-0f17db4304c1")
    GmLink create(IGmDiagram diagram, MObject linkElement);

    /**
     * Returns the {@link Class} object associated with the class or interface with the given string name.
     * <p>
     * Implementers should use this method when a Gm class is moved or renamed in order for the diagram persistence to properly instantiate persisted objects.
     * </p>
     * 
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("21ad611b-201d-40cb-bab4-0c0a27a12eba")
    Class<? extends IPersistent> resolveClass(String namespace);

    /**
     * Returns the {@link Class} object associated with the class with the given namespace.
     * <p>
     * Implementers should use this method when a Gm class is moved or renamed in order for the diagram persistence to properly instantiate these objects.
     * </p>
     * 
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("1dbed792-770a-46d5-851c-57bd76268da6")
    Class<? extends IPersistentMigrator> resolveMigratorClass(String namespace);

    /**
     * Returns the {@link Class} object associated with the migrator class with the given namespace.
     * <p>
     * Implementers should use this method when an enumeration class is moved or renamed in order for the diagram persistence to properly instantiate these objects.
     * </p>
     * 
     * @param namespace The class name
     * @return The matching class or <i>null</i> if none was found.
     */
    @objid ("d74140e5-6e0b-4b6b-b694-018085f6a796")
    Class<? extends Enum<?>> resolveEnumClass(String namespace);

    @objid ("3b873e94-d37b-4e6a-99f2-fdeca4c83bd0")
    default String migrateNamespacing(String namespace) {
        return namespace;
    }

}
