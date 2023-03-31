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
package org.modelio.gproject.auth;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Reconfigure an authentication configuration from the other authentication descriptor.
 * 
 * @since 5.3.1 . Was a method of GAuthConf before.
 */
@objid ("8ce4e34e-c035-40f7-90a5-12091d429fbd")
public abstract class AuthReconfigurer {
    /**
     * Compare 2 authentication data.
     * <p>
     * Returns <code>false</code> if the second authentication should replace the first one.
     * Compares the schemes, then test whether all the first authentication properties are contained in the second one.
     * @param old an current authentication data
     * @param newData the new authentication data.
     * @return <code>true</code> if the 2 authentications represents the same login, <code>false</code> if the second authentication should replace the first one.
     */
    @objid ("72d9b850-e850-4ea7-8276-255f8692509f")
    private static boolean isToReplace(IAuthData old, IAuthData newData) {
        if (old == null) {
            return newData != null;
        }
        
        if (newData == null) {
            return true;
        }
        
        Map<String, String> oldProp = old.getData();
        if (!old.getSchemeId().equals(newData.getSchemeId())) {
            return true;
        }
        
        for (Entry<String, String> prop : newData.getData().entrySet()) {
            if (!Objects.equals(oldProp.get(prop.getKey()), prop.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reconfigure an authentication configuration from the other authentication descriptor.
     * <p>
     * If no descriptor is provided, the configuration is left unmodified.
     * @param current The new authentication descriptor
     * @param remote The new authentication descriptor
     * @return <code>true</code> if the authentication changed else <code>false</code>.
     */
    @objid ("d19d87ce-0fd0-4849-a506-478463ec8e72")
    public static boolean reconfigure(AuthDescriptor current, AuthDescriptor remote) {
        if (remote == null) {
            return false;
        }
        
        // Do nothing if empty descriptor, unless the scope changed
        if (!remote.isDefined() && remote.getScope() == current.getScope()) {
            return false;
        }
        
        IAuthData newData = remote.getData();
        IAuthData oldData = current.getData();
        
        if (oldData == null) {
            current.setData(newData);
            current.setScope(remote.getScope());
            return true;
        } else if (isToReplace(oldData, newData)) {
            current.setData(newData);
            current.setScope(remote.getScope());
            return true;
        } else {
            current.setScope(remote.getScope());
        
            return false;
        }
        
    }

}
