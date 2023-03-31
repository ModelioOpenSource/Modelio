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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.net.HttpUriException;

/**
 * OpenID Connect authentication data, based on an access token.
 * @author cmarin
 * @since 5.2
 */
@objid ("12d4c334-f560-4d70-9fb3-11ec9ed5dc42")
public class OidcAuthData extends AuthData {
    /**
     * This authentication data class scheme identifier.
     */
    @objid ("ec53348a-00e9-4386-a58b-58663f99773e")
    public static final String SCHEME_ID = "AUTH_OIDC";

    @objid ("ab423816-eb2b-4e49-af06-8a87254c0234")
    private static final String KUID = "kuid";

    @objid ("7ced3c7c-11d3-44f9-8f86-f20e54717186")
    private final OAuthTokenProvider tokenProvider;

    /**
     * C'tor
     * @param tokenProvider a way to get an access token
     * @param kuid uid of the current user
     */
    @objid ("c73e1ef5-2646-421e-8811-ca11de815330")
    public  OidcAuthData(OAuthTokenProvider tokenProvider, String kuid) {
        super();
        this.tokenProvider = tokenProvider;
        getData().put(KUID, kuid);
        
    }

    /**
     * @return the user ID or <code>null</code>
     */
    @objid ("46dcb7e4-ee8b-4f17-bf2d-630d43df520e")
    public String getUserId() {
        return getData().get(KUID);
    }

    @objid ("5457aab2-c5c2-44bd-8f16-958598cac020")
    @Override
    public boolean isComplete() {
        try {
            return this.tokenProvider != null && this.tokenProvider.getToken() != null;
        } catch (IOException e) {
            return false;
        }
        
    }

    @objid ("e1527c6b-3c07-4513-994b-9b9415cc5e6c")
    @Override
    public String getSchemeId() {
        return SCHEME_ID;
    }

    /**
     * @return the access token or <code>null</code>
     * @throws IOException on I/O or HTTP error
     */
    @objid ("2a84030d-3715-46ac-86ac-132f7f9fc9f8")
    public String getToken() throws IOException {
        String token = this.tokenProvider.getToken();
        return token;
    }

    @objid ("1f11ee43-c209-4b60-a1b7-2b2f34729b19")
    @Override
    public Map<String, String> serialize(boolean forceCredentials) {
        Map<String, String> s =  new HashMap<>(getData());
        s.remove(KUID);
        return s;
    }

    /**
     * Lambda that provides the OAuth/OIDC token.
     */
    @objid ("710c11b6-b932-41cf-b50a-477e1598937d")
    @FunctionalInterface
    public interface OAuthTokenProvider {
        /**
         * @return the OAuth/OIDC token.
         * @throws HttpUriException on HTTP error
         * @throws IOException on I/O error
         */
        @objid ("06532f25-5010-4a3d-b4ad-0c7ac8322b22")
        String getToken() throws HttpUriException, IOException;
}
    

}
