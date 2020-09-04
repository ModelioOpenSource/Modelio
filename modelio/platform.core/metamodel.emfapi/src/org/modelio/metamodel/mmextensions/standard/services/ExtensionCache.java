/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.metamodel.mmextensions.standard.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Metamodel extension request cache.
 * <p>
 * Used to cache requests to find a single note type, tag type, resource type, or stereotype.
 * <p>
 * Extensions that are not valid anymore are removed when found. Extensions have to be added to this cache manually.
 */
@objid ("a5c7ac27-b0ad-472f-a770-d9192683bc18")
class ExtensionCache {
    @objid ("e50c9dbc-f7d3-41ea-8365-66f07af82866")
    private MMetamodel metamodel;

    @objid ("5c41a2e5-19da-4a38-83b3-970f4f5fb0a3")
    private Map<Integer, NoteType> nmap = new HashMap<>();

    @objid ("7f9677aa-fbfd-45b2-b57a-5f5c8b15e7d7")
    private Map<Integer, Stereotype> smap = new HashMap<>();

    @objid ("0e51ab1a-5e3d-4a03-8efe-85d16d95ae8e")
    private Map<Integer, TagType> tmap = new HashMap<>();

    @objid ("a3c9ebab-d8cc-4745-a235-68ed16108814")
    private Map<Integer, ResourceType> rmap = new HashMap<>();

    @objid ("61ab750e-1913-41fd-9c25-5a2112eb8661")
    public ExtensionCache(MMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    /**
     * Add a note type to the cache
     * @param moduleName the module name
     * @param metaclass the metaclass to register. Might be <code>null</code>.
     * @param element the note type
     */
    @objid ("92ab1816-7ff6-49be-9b0e-dbcd66e9bf9f")
    public void add(String moduleName, MClass metaclass, NoteType element) {
        int key = getKey(moduleName, getOwnerName(element), element.getName(), metaclass);
        this.nmap.put(key, element);
    }

    /**
     * Add a tag type to the cache
     * @param moduleName the module name
     * @param metaclass the metaclass to register. Might be <code>null</code>.
     * @param element the tag type
     */
    @objid ("eac9d400-0f54-4028-b502-842b08bfb5e5")
    public void add(String moduleName, MClass metaclass, TagType element) {
        int key = getKey(moduleName, getOwnerName(element), element.getName(), metaclass);
        this.tmap.put(key, element);
    }

    /**
     * Add a stereotype to the cache
     * @param moduleName the module name
     * @param metaclass the metaclass to register.
     * @param element the stereotype
     */
    @objid ("f94b82fb-b108-4411-a8a2-b30eecfd31d8")
    public void add(String moduleName, MClass metaclass, Stereotype element) {
        int key = getKey(moduleName, null, element.getName(), metaclass);
        this.smap.put(key, element);
    }

    /**
     * Find a note type.
     * @param moduleName the module name
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type.
     * @param noteTypeName the note type name
     * @param metaclass the metaclass
     * @return the found element or <code>null</code>.
     */
    @objid ("ce57cec4-09d0-44ca-94e4-8f2255ff1491")
    public NoteType getNoteType(String moduleName, String ownerName, String noteTypeName, MClass metaclass) {
        int key = getKey(moduleName, ownerName, noteTypeName, metaclass);
        
        NoteType n = this.nmap.get(key);
        if (n != null) {
            // Make sure the type still matches the criteria
            final ModuleComponent module = n.getModule();
            final MClass foundMClass = getBaseClass(n.getOwnerReference(), n.getOwnerStereotype());
            if (n.isValid() && noteTypeName.equals(n.getName()) && module != null && foundMClass != null && moduleName.equals(module.getName()) && metaclass.hasBase(foundMClass)) {
                return n;
            }
        
            this.nmap.remove(key);
        }
        return null;
    }

    /**
     * Find a stereotype.
     * @param moduleName the module name
     * @param name the note type name
     * @param metaclass the metaclass
     * @return the found element or <code>null</code>.
     */
    @objid ("5a5bf417-274c-4c04-9ef9-51acb3172c62")
    public Stereotype getStereotype(String moduleName, String name, MClass metaclass) {
        int key = getKey(moduleName, null, name, metaclass);
        
        Stereotype n = this.smap.get(key);
        if (n != null) {
            // Make sure the type still matches the criteria
            final ModuleComponent module = n.getModule();
            final MClass foundMClass = getBaseClass(n.getBaseClassName());
            if (name.equals(n.getName()) && module != null && foundMClass != null && moduleName.equals(module.getName())
                    && metaclass.hasBase(foundMClass)) {
                return n;
            }
        
            this.smap.remove(key);
        }
        return null;
    }

    /**
     * Find a tag type.
     * @param moduleName the module name
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type.
     * @param tagTypeName the note type name
     * @param metaclass the metaclass
     * @return the found element or <code>null</code>.
     */
    @objid ("e4fa8821-896b-4249-8160-3cdb3fbd3efe")
    public TagType getTagType(String moduleName, String ownerName, String tagTypeName, MClass metaclass) {
        int key = getKey(moduleName, ownerName, tagTypeName, metaclass);
        
        TagType n = this.tmap.get(key);
        if (n != null) {
            // Make sure the type still matches the criteria
            final ModuleComponent module = n.getModule();
            final MClass foundMClass = getBaseClass(n.getOwnerReference(), n.getOwnerStereotype());
            if (n.isValid() && tagTypeName.equals(n.getName()) && module != null && foundMClass != null && moduleName.equals(module.getName()) && metaclass.hasBase(foundMClass)) {
                return n;
            }
        
            this.tmap.remove(key);
        }
        return null;
    }

    @objid ("60a03d8c-8fbb-43e0-ad88-77fcc9960dec")
    private MClass getBaseClass(MetaclassReference classRef, Stereotype ste) {
        if (ste != null) {
            return getBaseClass(ste.getBaseClassName());
        } else if (classRef != null) {
            return getBaseClass(classRef.getReferencedClassName());
        }
        return null;
    }

    @objid ("c939d8a5-9ca3-4bf0-9c5f-8db582c2f3e1")
    private MClass getBaseClass(String baseName) {
        return this.metamodel.getMClass(baseName);
    }

    /**
     * Compute a hash key.
     * @param moduleName a module spec
     * @param ownerName name of the extension's composition owner
     * @param extensionName the note/tag/stereotype name
     * @param metaclass the metaclass
     * @return the hash key
     */
    @objid ("e8584cec-a32b-4d77-b0ba-0e4e2e07ed5e")
    private static int getKey(String moduleName, String ownerName, String extensionName, MClass metaclass) {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((metaclass == null) ? 0 : metaclass.hashCode());
        result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
        result = prime * result + ((moduleName == null) ? 0 : moduleName.hashCode());
        result = prime * result + ((extensionName == null) ? 0 : extensionName.hashCode());
        return result;
    }

    /**
     * Add a resource type to the cache
     * @param moduleName the module name
     * @param metaclass the metaclass to register. Might be <code>null</code>.
     * @param element the resource type
     */
    @objid ("74bbbace-b624-409a-aba4-b32c5676b444")
    public void add(String moduleName, MClass metaclass, ResourceType element) {
        int key = getKey(moduleName, getOwnerName(element), element.getName(), metaclass);
        this.rmap.put(key, element);
    }

    /**
     * Find a resource type.
     * @param moduleName the module name
     * @param ownerName the name of the {@link Stereotype} or {@link MetaclassReference} owning the type.
     * @param resourceTypeName the resource type name
     * @param metaclass the metaclass
     * @return the found element or <code>null</code>.
     */
    @objid ("16bc6d30-38c6-4d56-8b34-e13eb7e01064")
    public ResourceType getResourceType(String moduleName, String ownerName, String resourceTypeName, MClass metaclass) {
        int key = getKey(moduleName, ownerName, resourceTypeName, metaclass);
        
        ResourceType n = this.rmap.get(key);
        
        if (n != null) {
            // Make sure the type still matches the criteria
            final ModuleComponent module = n.getModule();
            final MClass foundMClass = getBaseClass(n.getOwnerReference(), n.getOwnerStereotype());
            if (n.isValid() && resourceTypeName.equals(n.getName()) && module != null && foundMClass != null && moduleName.equals(module.getName()) && metaclass.hasBase(foundMClass)) {
                return n;
            }
        
            this.rmap.remove(key);
        }
        return null;
    }

    /**
     * @param stereotypes a list of stereotypes.
     * @return a set containing the initial stereotypes as well as all parents in their inheritance tree.
     */
    @objid ("fdaee2b3-94f2-4f0c-ba58-80616605c03d")
    private Set<Stereotype> getInheritedStereotypes(List<Stereotype> stereotypes) {
        Set<Stereotype> ret = new HashSet<>(stereotypes);
        for (Stereotype stereotype : stereotypes) {
            Stereotype parent = stereotype.getParent();
            while (parent != null) {
                ret.add(parent);
                parent = parent.getParent();
            }
        }
        return ret;
    }

    @objid ("6a686737-b862-467a-a870-4f3ea5559cde")
    private String getOwnerName(MObject type) {
        MObject owner = type.getCompositionOwner();
        return owner != null ? owner.getName() : null;
    }

}
