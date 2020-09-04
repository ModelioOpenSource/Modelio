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

package org.modelio.app.project.core.services.openproject;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

/**
 * Service that must prompt the user for authentication data.
 * <p>
 * Called when authentication is missing or authentication failed.
 * 
 * @since 4.0
 */
@objid ("28ab38c1-d5b7-4a80-b4ca-7340bd448d2e")
public interface IAuthenticationPrompter {
    /**
     * Empty implementation that aborts authentication.
     */
    @objid ("69751ea9-a8ea-4ad9-88b9-a902a5571660")
    public static final IAuthenticationPrompter NONE = (IAuthData authData, String name, String location, String errorMessage) ->  null;

    /**
     * Prompts the user for authentication .
     * <p>
     * This method may be called from any thread, the implementation
     * must synchronize with the UI thread.
     * 
     * @param authData the authentication to complete
     * @param name the project or fragment name to authenticate.
     * @param location the location of the element to authenticate. Usually an URI. May be null.
     * @param errorMessage the previous authentication error if any. May be null.
     * @return the authentication data or null to abort.
     */
    @objid ("cf52baac-b4b1-4e20-9cd7-c3b78a83a63f")
    IAuthData promptAuthentication(IAuthData authData, String name, String location, String errorMessage);

}
