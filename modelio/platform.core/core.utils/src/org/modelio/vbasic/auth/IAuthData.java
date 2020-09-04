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

import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Interface for authentication data used to connect to remote locations.
 * <p>
 * Implementations should sub class the abstract {@link AuthData} class.
 */
@objid ("545a7470-263f-40be-98f0-77dcf6dd6ae0")
public interface IAuthData {
    /**
     * Get all authentication data fields
     * @return all authentication data fields.
     */
    @objid ("f25d8277-af83-4d77-aa5e-ca43b546f724")
    Map<String, String> getData();

    /**
     * Returns a Properties containing all data to serialize.
     * @return the datas to serialize.
     */
    @objid ("76636deb-7c4b-4b67-91c9-ec25fb3e43a7")
    Map<String, String> serialize();

    /**
     * Tells whether this authorization data is complete or some fields are missing.
     * @return <code>true</code> if this data is complete, <code>false</code> if fields are missing.
     */
    @objid ("4c47043d-480d-427e-9052-5b735da962d7")
    boolean isComplete();

    /**
     * Get the authentication scheme identifier.
     * @return the authentication scheme identifier.
     */
    @objid ("16d4f86d-96d7-4ab5-a072-35edcf3f60fd")
    String getSchemeId();

    /**
     * equals() must be implemented.
     */
    @objid ("0c1b0b15-11c0-4888-91eb-9cf1fa542bfe")
    @Override
    boolean equals(Object obj);

    /**
     * hashCode() must be implemented.
     */
    @objid ("ca817ec4-18a6-4fb0-8308-56e561ab3995")
    @Override
    int hashCode();

}
