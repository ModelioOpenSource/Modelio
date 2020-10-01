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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * The ServerUserPasswordAuthData is basically a UserPasswordAuthData with an additional property: the server this authentication data applies to.
 * 
 * Note that the server part may be null and that server is not required for a isComplete() authentication data.
 */
@objid ("947c5dbc-8b3b-437a-97f6-399670bb73be")
public class ServerUserPassAuthData extends UserPasswordAuthData {
    @objid ("71a56cab-e055-481f-98ae-2cbb44c2bd19")
    private static final String TARGETSERVER = "targetserver";

    @objid ("b9f5ffc3-c55f-4d67-b00e-a89e8981eddd")
    public static final String SERVERUSERPASS_SCHEME_ID = "AUTH_SERVER_USER_PASSWORD";

    /**
     * C'tor
     * 
     * @param server the server part of this authentication data
     * @param user the user part of this authentication data
     * @param pass the password part of this authentication data
     */
    @objid ("41e57eeb-4c6d-4b96-9e65-86587255481a")
    public ServerUserPassAuthData(String server, String user, String pass) {
        super(user, pass, false);
        if (server != null)
            getData().put(ServerUserPassAuthData.TARGETSERVER, server);
    }

    /**
     * C'tor default equivalent to ServerUserPasswordAuthData(null, "", "")
     */
    @objid ("a2d6eecc-130a-44fb-ab69-e417e5c79cdf")
    public ServerUserPassAuthData() {
        super("", "", false);
    }

    /**
     * @return the "server" part of this authentication data. Can be null.
     */
    @objid ("d3b19cdf-ea29-4934-802b-a408fffe935a")
    public String getServer() {
        return getData().getOrDefault(ServerUserPassAuthData.TARGETSERVER, null);
    }

    @objid ("6e09be15-c1cd-4369-98a2-7541eb3821b3")
    @Override
    public String getSchemeId() {
        return SERVERUSERPASS_SCHEME_ID;
    }

    /**
     * Set the server part of this authentication data.
     * 
     * @param server the server. Can be null.
     */
    @objid ("98c71e4a-9390-4387-ae17-1c261be63d40")
    public void setServer(String server) {
        if (server == null) {
            getData().remove(ServerUserPassAuthData.TARGETSERVER);
        } else {
            getData().put(ServerUserPassAuthData.TARGETSERVER, server);
        }
    }

    @objid ("a995e366-ba87-496d-89b7-af823a6f17c8")
    @Override
    public String toString() {
        return "ServerUserPasswordAuthData [getServerUrl()=" + getServer() + ", getUser()=" + getUser() + ", getPassword()=" + getPassword() + "]";
    }

}
