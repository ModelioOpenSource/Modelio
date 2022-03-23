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
package org.modelio.uml.objectdiagram.editor.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Object diagram specific implementation of {@link IGmNodeFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes deployment diagram specific elements</li>
 * </ul>
 */
@objid ("e240d12f-44a1-4963-8f20-41448d9af273")
public final class ObjectGmNodeFactory implements IGmNodeFactory {
    @objid ("0d7e64cf-b0e3-4db0-8abb-5d46dfeb5511")
    @Override
    public GmNodeModel create(IGmDiagram diagram, GmCompositeNode parent, MObject newElement, Object initialLayoutData) {
        return null;
    }

    @objid ("7d8365d2-71ad-4b3b-b9b6-d4ee8ed0e07d")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            String fixedNamespace = migrateNamespace(namespace);
            if (fixedNamespace.startsWith("org.modelio.uml.objectdiagram.editor")) {
                Class<?> clazz = Class.forName(fixedNamespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("163e3c85-16d6-4ef3-9735-342731527412")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            String fixedNamespace = migrateNamespace(classNamespace);
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("c79fa130-3c07-48cf-9b2b-db0d6510f3fb")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            String fixedNamespace = migrateNamespace(enumNamespace);
            Class<?> clazz = Class.forName(fixedNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    @objid ("0639869b-8641-4aed-929a-81a70cba5981")
    @Override
    public String migrateNamespace(String namespace) {
        if (namespace.startsWith("org.modelio.diagram.editor.object")) {
            return namespace.replaceAll("org.modelio.diagram.editor.object", "org.modelio.uml.objectdiagram.editor");
        }
        return namespace;
    }

}
