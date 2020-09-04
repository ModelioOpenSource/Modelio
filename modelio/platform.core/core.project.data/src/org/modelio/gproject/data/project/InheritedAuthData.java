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

package org.modelio.gproject.data.project;

import java.util.Collections;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.AuthData;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Authentication data inherited from another one.
 * <p>
 * This data is always complete and serialize no properties.
 */
@objid ("d695b1f4-a2f2-484f-a7c2-8b7ecec6de68")
public class InheritedAuthData extends AuthData {
    /**
     * The inherited scheme identifier.
     */
    @objid ("488a528e-4406-46c0-9e42-1927e69f7c11")
    public static final String SCHEME_ID = "AUTH_SCHEME_INHERITED";

    /**
     * Initialize the authentication data.
     */
    @objid ("22153524-abd3-48ec-a8dd-ce218ea8c7c1")
    public InheritedAuthData() {
    }

    @objid ("23c62957-9a34-41f2-9fa0-6864c9ebb2b1")
    @Override
    public Map<String, String> serialize(boolean forceCredentials) {
        return Collections.emptyMap();
    }

    @objid ("0c15eb90-713d-4ab2-92a4-49ad4bc4446a")
    @Override
    public boolean isComplete() {
        return true;
    }

    @objid ("a7641212-6fda-4bd7-b9a4-6134614e705c")
    @Override
    public String getSchemeId() {
        return SCHEME_ID;
    }

    /**
     * Convenience method to test whether an authentication data is inherited.
     * 
     * @param d an authentication data
     * @return <i>true</i> if the data is inherited.
     */
    @objid ("e8e105b1-563e-48a6-86e7-87f47697ea6e")
    public static boolean matches(IAuthData d) {
        return d != null && SCHEME_ID.equals(d.getSchemeId());
    }

}
