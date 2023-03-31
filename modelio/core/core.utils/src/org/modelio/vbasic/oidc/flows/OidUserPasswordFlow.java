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
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.oauth2.sdk.ResourceOwnerPasswordCredentialsGrant;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;

@objid ("088885de-263d-40a5-a301-e4de91e26d9e")
public class OidUserPasswordFlow implements IOidcAuthenticationFlow {
    @objid ("fab51e98-734b-4413-ba5a-3f667beb41da")
    private final OIDCProviderMetadata metadata;

    @objid ("ccd7b76d-4724-47ac-b352-61aaebd685f2")
    private final ClientID clientId;

    @objid ("5b9c7db0-5c09-4310-a878-1bed5b8c9f55")
    private final Secret clientSecret;

    @objid ("d0804f46-6c21-456d-9ce9-601003c01164")
    private final String userName;

    @objid ("e2de4443-b3e4-44e4-a5a0-d7e89ba7a79d")
    private final Secret userPassword;

    /**
     * @param metadata the OIDC provider metadatas
     * @param clientId the client identifier, not null
     * @param clientSecret the optional client secret.
     * @param userName the user login/email, not null
     * @param userPassword the user password. Should not be null.
     */
    @objid ("68d31419-77fe-4af4-be1e-dd6d9af98d0e")
    public  OidUserPasswordFlow(OIDCProviderMetadata metadata, ClientID clientId, Secret clientSecret, String userName, Secret userPassword) {
        super();
        this.metadata = Objects.requireNonNull(metadata, "metadata");
        this.clientId = Objects.requireNonNull(clientId);
        this.clientSecret = clientSecret;
        this.userName = Objects.requireNonNull(userName);
        this.userPassword = userPassword;
        
    }

    @objid ("fd68d87c-f3ea-4892-9917-46465ec13b3d")
    @Override
    public AuthResponse run() throws IOException {
        return resourceOwnerTokenRequest();
    }

    @objid ("ace18618-9e5f-401c-bfb8-53241014e386")
    private AuthResponse resourceOwnerTokenRequest() throws IOException {
        TokenRequest tokenReq;
        if (this.clientSecret != null) {
            tokenReq = new TokenRequest(
                    this.metadata.getTokenEndpointURI(),
                    new ClientSecretBasic(this.clientId, this.clientSecret),
                    new ResourceOwnerPasswordCredentialsGrant(this.userName, this.userPassword),
                    new Scope(OIDCScopeValue.OPENID));
        } else {
            tokenReq = new TokenRequest(
                    this.metadata.getTokenEndpointURI(),
                    this.clientId,
                    new ResourceOwnerPasswordCredentialsGrant(this.userName, this.userPassword),
                    new Scope(OIDCScopeValue.OPENID));
        }
        return NimbusHelper.requestOidcTokens(this.metadata, tokenReq);
    }

}
