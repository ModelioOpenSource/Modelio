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
package org.modelio.vcore.smkernel.mapi;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * A metamodel fragment.
 * <p>
 * The runtime metamodel of Modelio is composed of several fragments:
 * <ul>
 * <li>Modelio metamodel fragments covering UML and BPMN standards, provided by Modelio application, always present</li>
 * <li>extension metamodel fragments provided by application extensions (eg: Modules)</li>
 * </ul>
 * Note the Modelio kernel does not know anything about metamodel fragment providers and that the above description is there only
 * for the sake of clarity. Practically, once initialized with its composing fragments the Metamodel is only a repository for known
 * MClass instances.
 * 
 * <h2>Modelio and provider version management</h2>
 * 
 * The fragment has a Modelio version and a provider version.
 * <p>
 * Metamodel fragments are often a port from a tool to Modelio and
 * this porting may be made in several steps.
 * <ul>
 * <li>Each porting should result in an increase of the Modelio version.<br>
 * <li>When the source tool metamodel changes, the Modelio version should be modified
 * and the provider version should reflect the new metamodel version from the provider tool.
 * </ul>
 * 
 * <h2>Metamodel compatibility checking convention</h2>
 * 
 * Compatibility checking should be done by looking at the Modelio version as following:<ul>
 * <li> if major == 0 : old version numbering before Modelio 3.4
 * <li> major version different : major migration or migration impossible
 * <li> minor version different : migration needed
 * <li> release version different : can be opened by newer metamodel
 * </ul>
 * 
 * <h2>Comparison</h2>
 * Two metamodel fragments are equal if their name and version are equal.
 * The vendor is only informative.
 * 
 * @since Modelio 3.4
 * @author cmarin
 */
@objid ("b5203fb6-296a-4064-82ed-c8cdd30f41ab")
public interface MMetamodelFragment {
    /**
     * The mandatory name of this metamodel fragment. Must be distinct from any other loaded metamodel fragment.
     * 
     * Examples: org.modelio.metamodel.standard
     * @return name of this metamodel fragment.
     */
    @objid ("f0b4c213-1683-4e2d-9d38-a56891e75910")
    String getName();

    /**
     * The version of the metamodel fragment for Modelio.
     * <p>
     * Compatibility checking must be done as following:<ul>
     * <li> if major == 0 : old version numbering
     * <li> major different : major migration or migration impossible
     * <li> minor different : migration needed
     * <li> release different : can be opened by newer metamodel
     * </ul>
     * @return The version identifier.
     */
    @objid ("5d853a4a-489a-4c47-afac-9b6eeac8894b")
    Version getVersion();

    /**
     * The optional name of the provider of this metamodel fragment, typically the vendor.
     * 
     * Example: Modeliosoft
     * @return name of the provider
     */
    @objid ("d73febc3-e78f-4e9a-b3f6-3b3e7902c537")
    String getProvider();

    /**
     * The metamodel version for/from the provider tool.
     * <p>
     * This provider metamodel version is purely informative and should reflect the metamodel version
     * from the source tool.
     * <p>
     * Metamodel fragments are often a port from a tool to Modelio.
     * This porting may be made in several steps.
     * Each porting should result in an increase of the Modelio version.
     * When the source tool metamodel changes, the Modelio version should be modified
     * and the provider version should reflect the new metamodel version from the provider tool.
     * @return The metamodel version for/from the provider.
     */
    @objid ("a2414efd-daed-48dd-b5bb-3cd91bb97783")
    String getProviderVersion();

    /**
     * Tells whether this metamodel fragment is an extension
     * or a standard Modelio metamodel fragment.
     * <p>
     * By default all metamodel fragments are extension fragments.
     * <p>
     * Standard Modelio metamodel fragments are guaranteed to have no metaclass name collisions.
     * @return <i>true</i> if the fragment is an extension, <i>false</i> if it is a Modelio standard fragment.
     */
    @objid ("4bac70a9-db57-4e90-acb9-f55c703d1514")
    boolean isExtension();

    /**
     * @return the metamodel fragments needed by this fragment.
     * @since 3.6
     */
    @objid ("3a023076-4f06-488a-bdb7-2ef3cfe5a16b")
    Collection<VersionedItem<MMetamodelFragment>> getNeededFragments();

    /**
     * Tells whether this metamodel fragment is a fake metamodel fragment.
     * 
     * A fake metamodel fragment represents a missing metamodel fragment. Fake metamodel model metaclasses are all fake metaclasses.
     * @return true if this metamodel fragment is fake.
     */
    @objid ("7d9391b5-84a2-49d5-99bb-c969ed8368f9")
    boolean isFake();

    /**
     * Returns the dynamic behavior to which the specified key is mapped, or {@code null} if this key has no mapping.
     * @throws ClassCastException if the mapped behavior does not match the given type
     * @since 3.8
     * @param key the key whose associated behavior is to be returned
     * @param type the class of the dynamic behavior to get
     * @return the dynamic behavior to which the specified key is mapped, or {@code null} if key has no mapping
     */
    @objid ("5a0172dc-659d-40d3-89cb-ef17a96e46ea")
    default <T> T getDynamicBehavior(String key, Class<T> type) {
        throw new UnsupportedOperationException("getDynamicBehavior is not available.");
    }

    /**
     * Add a dynamic behavior for the specified key.
     * @param key key with which the specified behavior is to be associated
     * @param value behavior to be associated with the specified key
     * @since 3.8
     */
    @objid ("1c6c7134-24e6-4483-b99b-e9ed705604f5")
    default void addDynamicBehavior(String key, Object value) {
        throw new UnsupportedOperationException("addDynamicBehavior is not available.");
    }

    /**
     * Removes the dynamic behavior for the specified key only if it is currently mapped to the specified value.
     * @param key key with which the specified behavior is associated
     * @param value behavior expected to be associated with the specified key
     * @since 3.8
     */
    @objid ("17fdc3b7-4d15-49ab-9c1c-8fd3ccb626fe")
    default void removeDynamicBehavior(String key, Object value) {
        throw new UnsupportedOperationException("removeDynamicBehavior is not available.");
    }
}

