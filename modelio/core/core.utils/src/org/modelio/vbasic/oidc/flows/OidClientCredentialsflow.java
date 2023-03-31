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
package org.modelio.vbasic.oidc.flows;

import java.io.IOException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import com.nimbusds.openid.connect.sdk.op.ReadOnlyOIDCProviderMetadata;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;

@objid ("c41c5627-aa6a-4665-a930-f179d62683d2")
public class OidClientCredentialsflow implements IOidcAuthenticationFlow {
    @objid ("fdbbd1e1-5175-4397-a094-cfe87391aa0b")
    private final ReadOnlyOIDCProviderMetadata metadata;

    @objid ("d2e9177e-19cf-46f4-bbb3-f664d431c669")
    private final ClientID clientId;

    @objid ("c1b5a780-9e80-443a-8127-95eea49c28fd")
    private final Secret clientSecret;

    @objid ("6c4c522e-8251-48b5-a051-735dfd8cba2c")
    public  OidClientCredentialsflow(ReadOnlyOIDCProviderMetadata metadata, ClientID clientId, Secret clientSecret) {
        super();
        this.metadata = metadata;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        
    }

    @objid ("003ce621-fdf5-4b73-b189-18bf65be5777")
    @Override
    public AuthResponse run() throws IOException {
        return clientCredsTokenRequest();
    }

    @objid ("44f3fc41-68a2-46cf-8d67-a097521ea147")
    private AuthResponse clientCredsTokenRequest() throws IOException {
        TokenRequest tokenReq = new TokenRequest(
                this.metadata.getTokenEndpointURI(),
                new ClientSecretBasic(this.clientId, this.clientSecret),
                new ClientCredentialsGrant(),
                new Scope(OIDCScopeValue.OPENID));
        return NimbusHelper.requestOidcTokens(this.metadata, tokenReq);
    }

}
