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

package org.modelio.vbasic.version;

import java.io.Serializable;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Represents a versioned item.
 * <p>
 * It is composed of a name identifying it and a version.
 * Only these fields are used for equality and comparisons.
 * <p>
 * The represented object or any other data may be attached by the {@link #setObject(Object)} relation.
 * 
 * @author cmarin
 * 
 * @param <T> the attached data type.
 */
@objid ("0dcc1d73-db82-4a65-8bd8-bea3275a4eca")
public class VersionedItem<T> implements Comparable<VersionedItem<?>>, Serializable {
    @objid ("6c889c62-e07b-4e51-8de0-3edd4eef43d3")
    private final String name;

    @objid ("4791426a-bb95-4a43-9383-2e7dbb293251")
    private T object;

    @objid ("9ded93d3-0c0b-4535-baf4-f3d2128db35c")
    private static final long serialVersionUID = 1L;

    @objid ("65156f9f-1e43-4ece-a4ff-efd4201afbdb")
    private final Version version;

    /**
     * @param name the item name
     * @param version the item version
     * @param object the represented object
     */
    @objid ("5ab48a8b-64b5-4f94-95ef-846b2dc91ea1")
    public VersionedItem(String name, Version version, T object) {
        super();
        this.name = name;
        this.version = version;
        this.object = object;
    }

    /**
     * @param name the name
     * @param version the version
     */
    @objid ("2e7c59c9-a161-43d7-8395-6746188541c1")
    public VersionedItem(String name, Version version) {
        this(name, version , null);
    }

    @objid ("240f3f03-2ce6-4aba-9769-25b16d2350ae")
    @Override
    public int compareTo(VersionedItem<?> o) {
        if (o == null) {
            return 1;
        }
        
        int c = nullCompare(getName(),o.getName());
        if (c == 0) {
            return nullCompare(getVersion(),o.getVersion());
        }
        return c;
    }

    @objid ("08bbb2ed-1278-438f-b066-943eab0c8a2e")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        VersionedItem<?> other = (VersionedItem<?>) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!this.version.equals(other.version)) {
            return false;
        }
        return true;
    }

    /**
     * Convenience to find a versioned item by name in a generic collection.
     * 
     * @param name the name to look for
     * @param coll the collection to look into
     * @return the found item or <i>null</i>.
     */
    @objid ("1f81ac33-e930-45d6-987c-25bf8a03524a")
    public static <T> VersionedItem<T> find(String name, Collection<VersionedItem<T>> coll) {
        for (VersionedItem<T> versionedItem : coll) {
            if (versionedItem.getName().equals(name)) {
                return versionedItem;
            }
        }
        return null;
    }

    /**
     * @return the name
     */
    @objid ("d94909f4-8d05-43fe-8921-9e67f0f3c8b9")
    public String getName() {
        return this.name;
    }

    /**
     * Get the represented object, or any data attached.
     * 
     * @return the object
     */
    @objid ("b6245d28-9c01-4886-864c-0d1dc153bf1a")
    public T getObject() {
        return this.object;
    }

    /**
     * @return the version
     */
    @objid ("80d8be81-8e9f-46b7-bb8a-079e85b78382")
    public Version getVersion() {
        return this.version;
    }

    @objid ("092b8781-6519-4e74-a96b-7c50c21bf89f")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result
                + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    /**
     * Attach the represented object or any other data.
     * 
     * @param object the object to attach.
     */
    @objid ("98d2a4cd-1031-4160-a7d1-3d257bda7a7e")
    public void setObject(T object) {
        this.object = object;
    }

    @objid ("94a1c256-2abe-4def-ad67-0e8c5a51b759")
    private static <T extends  Comparable<T>> int nullCompare(T a, T b) {
        if (a == null) {
            if (b != null) {
                return -1;
            } else {
                return 0;
            }
        } else if (b == null) {
            return 1;
        } else {
            return a.compareTo(b);
        }
    }

    @objid ("609c3374-e846-4c41-a4ad-41d199ff4405")
    @Override
    public String toString() {
        return getName()+" v"+getVersion();
    }

}
