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

import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an object that is able to serialize data in a stream.
 * 
 * @author cmarin
 */
@objid ("cb6b8527-186f-11e2-92d2-001ec947c8cc")
public interface IDiagramWriter {
    /**
     * Get the serialized string.
     * @return the serialized string.
     */
    @objid ("cb6b8529-186f-11e2-92d2-001ec947c8cc")
    String getOutput();

    /**
     * Save a root persistent element and all its relations.
     * @param diagram the element to save
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6b852c-186f-11e2-92d2-001ec947c8cc")
    void save(IPersistent diagram) throws PersistenceException;

    /**
     * Write an {@link Enum} attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb6b852f-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Enum<?> value);

    /**
     * Write a {@link Boolean} attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb6de74b-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Boolean value);

    /**
     * Write a {@link Double} attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb6de750-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Double value);

    /**
     * Write a string attribute
     * @param attName
     * @param value
     * @throws PersistenceException
     * in case of unexpected error.
     */
    @objid ("cb6de755-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Float value);

    /**
     * Write a string attribute.
     * @param attName
     * @param value
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de75a-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, String value) throws PersistenceException;

    /**
     * Write an integer attribute.
     * @param attName
     * @param value
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de75e-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, int value) throws PersistenceException;

    /**
     * Write a {@link Rectangle} attribute
     * @param attName
     * @param value
     * @see Rectangle
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de762-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Rectangle value) throws PersistenceException;

    /**
     * Write a {@link Point} attribute
     * @param attName
     * @param value
     * @see Point
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de768-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Point value) throws PersistenceException;

    /**
     * Write a map.
     * <p>
     * Supported types are IPersistent and all supported primitive types
     * @param <K>
     * The map key type. Supported types are IPersistent and all supported primitive types
     * @param <V>
     * The map value type.
     * @param mapName The map name
     * @param map The map
     */
    @objid ("cb6de76e-186f-11e2-92d2-001ec947c8cc")
    <K, V> void writeProperty(String mapName, Map<K, V> map);

    /**
     * Write a single object relation
     * @param relation a relation name to be fetched by readObject().
     * @param object the object to write.
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de777-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String relation, IPersistent object) throws PersistenceException;

    /**
     * Write an attribute whose type is not determined.
     * <p>
     * Only types for which a writeAtt() method is available are supported.
     * @param attName
     * @param value
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de77b-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Object value) throws PersistenceException;

    /**
     * Write an external reference to a persistent element.
     * <p>
     * An external element is an element that is not stored in this stream but in another one. The provided ids must
     * help the diagram reader to retrieve the data where is stored the external element.
     * @param extObj The external element to reference.
     * @param dbId A "database" id that can be used to retrieve the location where the element is stored
     * @param refId An identifier for the external element
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de77f-186f-11e2-92d2-001ec947c8cc")
    void writeExtRef(IPersistent extObj, String dbId, String refId) throws PersistenceException;

    /**
     * Get the root element of this writer.
     * <p>
     * The root element is the element that was passed to {@link #save(IPersistent)}.
     * @return the root element.
     */
    @objid ("cb6de784-186f-11e2-92d2-001ec947c8cc")
    IPersistent getRoot();

    /**
     * Write an {@link MRef} attribute.
     * @param attName
     * @param mRef a model element reference
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de787-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, MRef mRef) throws PersistenceException;

    /**
     * Write a list.
     * <p>
     * Supported types are IPersistent and all supported primitive types
     * @param <T>
     * The map value type. Supported types are IPersistent and all supported primitive types
     * @param listName The list name
     * @param list The list
     */
    @objid ("cb6de78b-186f-11e2-92d2-001ec947c8cc")
    <T> void writeProperty(String listName, List<T> list);

    /**
     * Write a {@link AbsoluteBendpoint} attribute
     * @param attName
     * @param value
     * @see AbsoluteBendpoint
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de792-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, AbsoluteBendpoint value) throws PersistenceException;

    /**
     * Write a {@link Dimension} attribute
     * @param attName
     * @param value
     * @see Dimension
     * @throws PersistenceException in case of unexpected error.
     */
    @objid ("cb6de798-186f-11e2-92d2-001ec947c8cc")
    void writeProperty(String attName, Dimension value) throws PersistenceException;
}

