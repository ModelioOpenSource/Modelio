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

package org.modelio.vbasic.auth;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Base class for Authentication data used to connect to remote locations.
 * <p>
 * All authentication datas are stored in a Properties field. Sub classes are
 * not intended to add others.
 */
@objid ("e304880c-9459-4116-8895-4ff24ffadcd0")
public abstract class AuthData implements IAuthData {
    /**
     * All authentication datas must be stored here.
     */
    @objid ("aad4b412-1e2c-4f9c-8d25-b09c815b83e5")
    private Map<String, String> data;

    /**
     * Initialize the data.
     */
    @objid ("4aca559a-67a1-45fd-adb0-74528a577462")
    public AuthData() {
        this.data = new HashMap<>(3);
    }

    /**
     * Get all authentication data fields
     * @return all authentication data fields.
     */
    @objid ("748d888f-84d1-4ed8-b53d-63640d19dfd8")
    @Override
    public final Map<String, String> getData() {
        return this.data;
    }

    /**
     * Returns a Properties containing all data to serialize.
     * @return the datas to serialize.
     */
    @objid ("767c37a3-e4b0-48eb-8439-4f2c84ff9333")
    @Override
    public abstract Map<String, String> serialize();

    /**
     * Tells whether this authorization data is complete or some fields are missing.
     * @return <code>true</code> if this data is complete, <code>false</code> if fields are missing.
     */
    @objid ("89bb42f8-2a55-416d-9066-b6023676538c")
    @Override
    public abstract boolean isComplete();

    /**
     * Convenience method to get a property value and return a default value if
     * the property is not defined.
     * @param prop a property key
     * @param defaultValue the default value
     * @return the property value or the default value.
     */
    @objid ("470482f0-453f-44e6-bc37-c007b2ac8bd9")
    protected String getProperty(String prop, String defaultValue) {
        String ret = getData().get(prop);
        
        if (ret == null)
            return defaultValue;
        else 
            return ret;
    }

    /**
     * Default implementation that should fit any subclass.
     */
    @objid ("9f5ed6d3-924a-4ac7-8ff3-2ef3b8214c8b")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSchemeId() == null) ? 0 : getSchemeId().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        return result;
    }

    /**
     * Default implementation that should fit any subclass.
     */
    @objid ("7995fb8d-6ef3-4178-aa0a-68f72c777251")
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
        
        AuthData other = (AuthData) obj;
        
        if (! getSchemeId().equals(other.getSchemeId()))
            return false;
        
        if (getData() == null) {
            if (other.getData() != null) {
                return false;
            }
        } else if (!getData().equals(other.getData())) {
            return false;
        }
        return true;
    }

}
