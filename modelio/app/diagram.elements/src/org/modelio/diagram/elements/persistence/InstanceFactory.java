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

package org.modelio.diagram.elements.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.core.model.factory.IGmNodeFactory;
import org.modelio.diagram.persistence.IInstanceFactory;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.Style;

/**
 * Implementation of {@link IInstanceFactory} to read diagrams.
 * 
 * @author cmarin
 */
@objid ("810a64ce-1dec-11e2-8cad-001ec947c8cc")
public class InstanceFactory implements IInstanceFactory {
    @objid ("3a91bdea-34ba-41c8-bf88-65c53164b990")
    private final IGmNodeFactory nodeFactory;

    @objid ("22458d16-9510-431a-bd5e-ca3d6f9e316a")
    private final IGmLinkFactory linkFactory;

    /**
     * Instantiate the {@link InstanceFactory} for a diagram.
     * 
     * @param gmDiagram a gm diagram to get the gm node/link factories from.
     */
    @objid ("a89df6fd-22d7-4a23-bd93-dfd5fda8c2af")
    public InstanceFactory(IGmDiagram gmDiagram) {
        this.nodeFactory = gmDiagram.getGmNodeFactory();
        this.linkFactory = gmDiagram.getGmLinkFactory();
    }

    @objid ("810a64d0-1dec-11e2-8cad-001ec947c8cc")
    private Class<? extends IPersistent> resolveClass(String classNamespace) throws PersistenceException {
        // Get the java class
        try {
            Class<?> clazz = Class.forName(classNamespace);
            return clazz.asSubclass(IPersistent.class);
        } catch (ClassNotFoundException e) {
            // Call registered node factories.
            Class<? extends IPersistent> clazz = this.nodeFactory.resolveClass(classNamespace);
            if (clazz != null) {
                return clazz;
            }
        
            // Call registered link factories.
            clazz = this.linkFactory.resolveClass(classNamespace);
            if (clazz != null) {
                return clazz;
            }
        
            // Old migration case, diagram namespacing changed a long time ago...
            if (classNamespace.startsWith("com.modeliosoft.modelio.diagram")) {
                return resolveClass(classNamespace.replace("com.modeliosoft.modelio.diagram", "org.modelio.diagram"));
            }
        
            throw new PersistenceException(classNamespace + " class cannot be found.", e);
        }
    }

    @objid ("810a64d7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IPersistent createInstance(String classNamespace) throws PersistenceException {
        Class<? extends IPersistent> clazz = resolveClass(classNamespace);
        if (clazz == FactoryStyle.class) {
            return FactoryStyle.getInstance();
        } else if (clazz == NamedStyle.class) {
            throw new IllegalArgumentException("NamedStyle not supported: should be an external reference.");
        } else if (clazz == Style.class) {
            return new Style(FactoryStyle.getInstance());
        } else {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                throw new PersistenceException(e);
            } catch (IllegalAccessException e) {
                throw new PersistenceException(e);
            }
        }
    }

    @objid ("810a64dd-1dec-11e2-8cad-001ec947c8cc")
    @SuppressWarnings ("unchecked")
    @Override
    public <T extends  Enum<T>> Class<T> getEnumClass(String enumNamespace) {
        // Get the java class
        try {
            return (Class<T>) Class.forName(enumNamespace);
        } catch (ClassNotFoundException e) {
            // Call registered node factories.
            Class<? extends Enum<?>> clazz = this.nodeFactory.resolveEnumClass(enumNamespace);
            if (clazz != null) {
                return (Class<T>) clazz;
            }
        
            // Call registered link factories.
            clazz = this.linkFactory.resolveEnumClass(enumNamespace);
            if (clazz != null) {
                return (Class<T>) clazz;
            }
        
            // Old migration case, diagram namespacing changed a long time ago...
            if (enumNamespace.startsWith("com.modeliosoft.modelio.diagram")) {
                return getEnumClass(enumNamespace.replace("com.modeliosoft.modelio.diagram", "org.modelio.diagram"));
            }
        
            throw new PersistenceException(enumNamespace + " enum cannot be found.", e);
        }
    }

    @objid ("52f01427-49b7-4722-be58-8fc95a14bd3e")
    private Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) throws PersistenceException {
        // Get the java class
        try {
            Class<?> clazz = Class.forName(classNamespace);
            return clazz.asSubclass(IPersistentMigrator.class);
        } catch (ClassNotFoundException e) {
            // Call registered node factories.
            Class<? extends IPersistentMigrator> clazz = this.nodeFactory.resolveMigratorClass(classNamespace);
            if (clazz != null) {
                return clazz;
            }
        
            // Call registered link factories.
            clazz = this.linkFactory.resolveMigratorClass(classNamespace);
            if (clazz != null) {
                return clazz;
            }
        
            // Old migration case, diagram namespacing changed a long time ago...
            if (classNamespace.startsWith("com.modeliosoft.modelio.diagram")) {
                return resolveMigratorClass(classNamespace.replace("com.modeliosoft.modelio.diagram", "org.modelio.diagram"));
            }
        
            throw new PersistenceException(classNamespace + " class cannot be found.", e);
        }
    }

    @objid ("748335ee-2a34-49cd-9808-a30e49f3b715")
    @Override
    public IPersistentMigrator createMigratorInstance(String classNamespace) throws PersistenceException {
        Class<? extends IPersistentMigrator> clazz = resolveMigratorClass(classNamespace);
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new PersistenceException(e);
        } catch (IllegalAccessException e) {
            throw new PersistenceException(e);
        }
    }

}
