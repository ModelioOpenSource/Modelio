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

package org.modelio.diagram.persistence;

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface for classes able to deserialize persistent object from a text string.
 * <p>
 * <b>Usage:</b><br/>
 * The caller must call {@link #readDiagram(String, IPersistent)} .
 * <p>
 * Persistent classes must implement {@link IPersistent} and have a default constructor. Their
 * {@link IPersistent#read(IDiagramReader)} should then call <tt>readXxxx(...)</tt> methods.
 * 
 * @author cmarin
 * @see IDiagramWriter
 */
@objid ("cb6b8504-186f-11e2-92d2-001ec947c8cc")
public interface IDiagramReader {
    /**
     * Get the root object being read.
     * <p>
     * The root object is the persistent object passed to {@link #readDiagram(String, IPersistent)}.
     * 
     * @return the root object being read.
     */
    @objid ("cb6b8506-186f-11e2-92d2-001ec947c8cc")
    IPersistent getRoot();

    /**
     * Read all attributes at once.
     * @param attName
     * The attribute name
     * @throws PersistenceException
     * 
     * @return a map with the attribute name as key and the attribute value as value.
     */
    @objid ("cb6b8509-186f-11e2-92d2-001ec947c8cc")
    Map<String, Object> readAllProperties() throws PersistenceException;

    /**
     * Deserialize all the persistent data into the given persistent object.
     * 
     * @param data The persistent data
     * @param into The root object the data represents
     */
    @objid ("cb6b850f-186f-11e2-92d2-001ec947c8cc")
    void readDiagram(String data, IPersistent into) throws PersistenceException;

    /**
     * Read a persistent {@link Map}.
     * <p>
     * Supported types for the map key and value are {@link IPersistent} and all supported primitive types.
     * @param <K>
     * The map key type.
     * @param <V>
     * The map value type.
     * @throws PersistenceException
     * 
     * @param mapName The map name
     * @return The map
     */
    @objid ("cb6b8513-186f-11e2-92d2-001ec947c8cc")
    <K,V> Map<K, V> readMapProperty(String mapName) throws PersistenceException;

    /**
     * Read an attribute whose type is not constant.
     * @throws PersistenceException
     * 
     * @param attName The attribute name
     * @return The attribute value or <tt>null</tt> if the attribute has no value.
     */
    @objid ("cb6b851c-186f-11e2-92d2-001ec947c8cc")
    Object readProperty(String attName) throws PersistenceException;

    /**
     * Read a persistent {@link List}.
     * <p>
     * Supported types for the list values are {@link IPersistent} and all supported primitive types.
     * @param <T>
     * The map value type.
     * @throws PersistenceException
     * 
     * @param listName The list name
     * @return The list
     */
    @objid ("cb6b8520-186f-11e2-92d2-001ec947c8cc")
    <T> List<T> readListProperty(String listName) throws PersistenceException;

}
