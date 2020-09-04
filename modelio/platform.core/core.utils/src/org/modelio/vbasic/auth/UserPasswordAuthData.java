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

package org.modelio.vbasic.auth;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * User/password authentication data.
 */
@objid ("d15baf6d-715f-4ad2-b531-000b3dd1bac4")
public class UserPasswordAuthData extends AuthData {
    @objid ("ff229f9d-9363-4338-a6c3-452347ca89e5")
    private static final String NAME = "name";

    @objid ("0a6d0a1c-e3c0-45ac-9249-c3451e322ea1")
    private static final String PASS = "pass";

    @objid ("2719a013-f3d8-49bd-89e4-b5d6e11ae148")
    private static final String SAVE = "save";

    /**
     * This authentication data class scheme identifier.
     */
    @objid ("5f9882b3-8e93-4521-b406-efb895c03069")
    public static final String USERPASS_SCHEME_ID = "AUTH_USER_PASSWORD";

    /**
     * Initialize an empty user/password.
     */
    @objid ("b6460a3b-438b-44f0-909d-7d8164e072a2")
    public UserPasswordAuthData() {
        super();
    }

    /**
     * Initialize the authentication data.
     * 
     * @param user login name
     * @param pass password
     * @param store <code>true</code> to serialize the password, <code>false</code> to skip it from serialization.
     */
    @objid ("f8627f53-c4d0-44b4-ada5-a2bfc90a1e89")
    public UserPasswordAuthData(String user, String pass, boolean store) {
        super();
        getData().put(NAME, user);
        getData().put(PASS, pass);
        getData().put(SAVE, String.valueOf(store));
    }

    /**
     * @return the login name or <code>null</code>
     */
    @objid ("7ac789e7-c970-4425-b57f-3c614c72f46d")
    public String getUser() {
        return getData().get(NAME);
    }

    /**
     * @return the password or <code>null</code>.
     */
    @objid ("6e929a48-7ab2-4858-904f-44372bc22892")
    public String getPassword() {
        return getData().get(PASS);
    }

    @objid ("c04d14e3-871b-4430-b2e9-bc70c3cbb96e")
    @Override
    public String getSchemeId() {
        return USERPASS_SCHEME_ID;
    }

    @objid ("447911da-27bc-43c5-a272-5779b9e4f091")
    @Override
    public boolean isComplete() {
        return !getProperty(NAME, "").isEmpty() && !getProperty(PASS, "").isEmpty();
    }

    /**
     * @return <code>true</code> if the password must be stored.
     */
    @objid ("76ce9376-92c6-4aa0-96bb-4fdcdd7162ec")
    public boolean isStored() {
        return Boolean.valueOf(getProperty(SAVE, String.valueOf(false)));
    }

    @objid ("c0cfa59d-8c6b-483b-b524-d691ec0f8ae0")
    @Override
    public Map<String, String> serialize(boolean forceCredentials) {
        Map<String, String> s =  new HashMap<>(getData());
        if (!forceCredentials && !isStored()) {
            s.remove(PASS);
        }
        return s;
    }

    /**
     * Set the password.
     * 
     * @param pass the password.
     */
    @objid ("ddcd95a8-4dde-4910-90c0-7726c1523d96")
    public void setPassword(String pass) {
        getData().put(PASS, pass);
    }

    /**
     * Set whether the password will be serialized.
     * 
     * @param store <code>true</code> to store the password.
     */
    @objid ("f1eae90e-0a4e-471d-b892-01bc9b2212dc")
    public void setStored(boolean store) {
        getData().put(SAVE, String.valueOf(store));
    }

    /**
     * Set the login name.
     * 
     * @param name the login
     */
    @objid ("cb0c7254-56d9-428a-8b44-e67fad629ac2")
    public void setUser(String name) {
        getData().put(NAME, name);
    }

}
