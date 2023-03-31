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
package org.modelio.vbasic.oidc;

import java.io.IOException;
import java.time.Instant;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import org.modelio.vbasic.auth.OidcAuthData;
import org.modelio.vbasic.oidc.flows.NimbusHelper;

/**
 * OIDC authentication process.
 * <p>
 * You may create an {@link OidcAuthData} from the instance, it will run the authentication process.
 * @author cmarin
 */
@objid ("c6c57ffa-2086-41b9-9bee-556728d03b3e")
public interface IOidcAuthenticationFlow {
    /**
     * Run the process and get the {@link OIDCTokens OIDC tokens}.
     * <p>
     * This method is synchronous and may take a long time depending on the user.
     * @return the OIDC tokens
     * @throws IOException on authentication failure.
     */
    @objid ("a1c44b6e-6614-4ee9-88b4-09eac0940be3")
    AuthResponse run() throws IOException;

    /**
     * Run the process and get an {@link OidcAuthData}.
     * <p>
     * This method is synchronous and may take a long time depending on the user.
     * @return the OIDC tokens
     * @throws IOException on authentication failure.
     */
    @objid ("bc157192-26a1-4e91-b128-8ed91a580b3e")
    default OidcAuthData createAuthData() throws IOException {
        AuthResponse authResp = run();
        String subject = NimbusHelper.getSubject(authResp.tokens);
        return new OidcAuthData(() -> {
            AuthResponse authResp2 = run();
            return authResp2.tokens.getAccessToken().getValue();
        }, subject);
        
    }

    @objid ("e0f6a63a-6c40-45f0-8c37-65b32e79482f")
    static final class AuthResponse {
        @objid ("ee6da02a-afdc-49c8-b648-ebe25b3441ef")
        public final OIDCTokens tokens;

        @objid ("cd778bfa-7d3d-40a3-a6b8-2cd7599e91e6")
        public final Instant expiration;

        @objid ("94c4fe03-3258-4665-b827-72e8fc6dfffc")
        public  AuthResponse(OIDCTokens tokens, Instant expiration) {
            super();
            this.tokens = tokens;
            this.expiration = expiration;
            
        }

    }
}

