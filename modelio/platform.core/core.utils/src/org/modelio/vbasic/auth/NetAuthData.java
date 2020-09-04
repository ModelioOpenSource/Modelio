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

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Extends user and password auth data with internet options such as:
 * <ul>
 * <li> proxy host
 * <li> proxy port
 * <li> proxy password
 * <li> client certificate
 * <li> proxy certificate
 * </ul>
 * @author cmarin
 */
@objid ("bca6a876-ea4c-47f3-8ac8-79c62e4374ed")
public class NetAuthData extends UserPasswordAuthData {
    @objid ("5f0ad6bb-30a3-406d-8260-3de6de86872e")
    public static final String PROP_PROXY_HOST = "proxy.host";

    @objid ("5b796fab-f16e-43ee-aff3-070b1f025294")
    public static final String PROP_PROXY_PORT = "proxy.port";

    @objid ("96df26cc-f4a7-4a1a-aeb6-dde3c1331489")
    public static final String PROP_PROXY_PASSWORD = "proxy.password";

    @objid ("6ad489b3-7245-485d-949f-cfe28f173eaf")
    public static final String PROP_CLIENT_CERTIFICATE = "client.certificate";

}
