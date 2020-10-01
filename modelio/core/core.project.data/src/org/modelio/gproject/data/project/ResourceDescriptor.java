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

package org.modelio.gproject.data.project;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Synchronized resource descriptor.
 * <p>
 * A resource may be a file or a directory.
 * <p>
 * A resource is synchronized by comparing the file or directory last modification time with the recorded one.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("5dee9349-08b9-4b80-940f-f222c4c20d31")
public class ResourceDescriptor {
    /**
     * Remote path of the project for shared projects.
     */
    @mdl.prop
    @objid ("87406901-4be3-4f51-bb95-648649849552")
    private String id;

    @mdl.propgetter
    public String getId() {
        // Automatically generated method. Please do not modify this code.
        return this.id;
    }

    @mdl.propsetter
    public void setId(String value) {
        // Automatically generated method. Please do not modify this code.
        this.id = value;
    }

    /**
     * Remote path of the project for shared projects.
     */
    @mdl.prop
    @objid ("44a22277-3502-42b9-b186-569a9e3fffc7")
    private String targetLocation;

    @mdl.propgetter
    public String getTargetLocation() {
        // Automatically generated method. Please do not modify this code.
        return this.targetLocation;
    }

    @mdl.propsetter
    public void setTargetLocation(String value) {
        // Automatically generated method. Please do not modify this code.
        this.targetLocation = value;
    }

    /**
     * Remote path of the project for shared projects.
     */
    @mdl.prop
    @objid ("79878d9a-0900-40fd-ae94-5022bbea3e4d")
    private long timestamp;

    @mdl.propgetter
    public long getTimestamp() {
        // Automatically generated method. Please do not modify this code.
        return this.timestamp;
    }

    @mdl.propsetter
    public void setTimestamp(long value) {
        // Automatically generated method. Please do not modify this code.
        this.timestamp = value;
    }

    @objid ("4fac34ae-70a7-460e-8267-41d1598537ab")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.targetLocation == null) ? 0 : this.targetLocation.hashCode());
        result = prime * result + (int) (this.timestamp ^ (this.timestamp >>> 32));
        return result;
    }

    @objid ("628c0e30-0d71-43ae-a341-8840036c126b")
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
        ResourceDescriptor other = (ResourceDescriptor) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.targetLocation == null) {
            if (other.targetLocation != null) {
                return false;
            }
        } else if (!this.targetLocation.equals(other.targetLocation)) {
            return false;
        }
        if (this.timestamp != other.timestamp) {
            return false;
        }
        return true;
    }

}
