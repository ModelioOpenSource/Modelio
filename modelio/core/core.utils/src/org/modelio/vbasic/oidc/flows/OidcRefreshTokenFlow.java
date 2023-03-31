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
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.oauth2.sdk.RefreshTokenGrant;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.op.ReadOnlyOIDCProviderMetadata;
import org.modelio.vbasic.net.UriAuthenticationException;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;

/**
 * OIDC flow that refreshes the tokens when they are to expire.
 * 
 * @author cmarin
 * @since 5.2
 */
@objid ("58b7f6c0-e96b-4d6f-a467-d1ca93cc7d13")
public class OidcRefreshTokenFlow implements IOidcAuthenticationFlow {
    @objid ("cf3a7bf8-dce9-4995-872e-bbb68e503a5d")
    private final ReadOnlyOIDCProviderMetadata metadata;

    @objid ("a70d1333-dfbc-4f57-ae8b-0ad6dddc2199")
    private final ClientID clientId;

    @objid ("fa9e3a11-3784-49de-bb20-b0619b1bd294")
    private final Secret clientSecret;

    @objid ("8e3b0874-3230-4696-9bca-4cd9f6069d3c")
    private AuthResponse cachedResponse;

    @objid ("530c6d20-e367-46fa-a797-747dbb21bbea")
    private final IOidcAuthenticationFlow alternateFlow;

    /**
     * @param clientId the OIDC client Id
     * @param metadata the OIDC provider datas
     * @param alternateFlow the auth flow to use if the refresh token is null, invalid or expired.
     */
    @objid ("15599ccf-0be6-44b0-aeef-0c2dcd9f7507")
    public  OidcRefreshTokenFlow(ClientID clientId, Secret clientSecret, ReadOnlyOIDCProviderMetadata metadata, IOidcAuthenticationFlow alternateFlow) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.metadata = metadata;
        this.alternateFlow = alternateFlow;
        
    }

    /**
     * @param clientId the OIDC client Id
     * @param metadata the OIDC provider datas
     * @param alternateFlow the auth flow to use if the refresh token is null, invalid or expired.
     * @param startTokens the initial access token and refresh token
     */
    @objid ("333e765a-c1a4-4c54-95c8-dbba916c2aa2")
    public  OidcRefreshTokenFlow(ClientID clientId, Secret clientSecret, ReadOnlyOIDCProviderMetadata metadata, IOidcAuthenticationFlow alternateFlow, AuthResponse startTokens) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.metadata = metadata;
        this.alternateFlow = alternateFlow;
        this.cachedResponse = startTokens;
        
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation refreshes the token if it will expire in less than 30 seconds.
     * @see #getTokens(Duration) getTokens(Duration) to define the access token max age
     * @return the OIDC tokens
     * @throws IOException on authentication failure.
     */
    @objid ("02b43597-81bc-42f2-afac-e81a30e4bdf8")
    @Override
    public AuthResponse run() throws IOException {
        return getTokens(Duration.ofSeconds(30));
    }

    /**
     * @deprecated Use {@link #getTokens(Duration)}
     * @param minValidity the access token minimum validity
     * @param unit the access token minimum validity unit
     * @return the authentication response with the token and its expiration date.
     * @throws IOException on failure
     */
    @objid ("18b58629-4471-4a71-85a3-8cccdfdaf13f")
    @Deprecated
    public AuthResponse getTokens(long minValidity, TimeUnit unit) throws IOException {
        return getTokens(Duration.ofMillis(unit.toMillis(minValidity)));
    }

    /**
     * @param minValidity the access token minimum validity
     * @return the authentication response with the token and its expiration date.
     * @throws IOException on failure
     */
    @objid ("5ae7acfb-e887-461e-8d3c-b77960eb5cd4")
    public synchronized AuthResponse getTokens(Duration minValidity) throws IOException {
        if (this.cachedResponse == null
                || this.cachedResponse.tokens.getAccessToken() == null
                || this.cachedResponse.expiration.isBefore(Instant.now().plus(minValidity))) {
            this.cachedResponse = refreshTokens();
        }
        return this.cachedResponse;
    }

    @objid ("c1c3627c-03a4-4394-80a0-d01e7cf462fb")
    private AuthResponse refreshTokens() throws IOException {
        if (this.cachedResponse == null || this.cachedResponse.tokens.getRefreshToken()==null) {
            if (this.alternateFlow == null)
                throw new UriAuthenticationException(this.metadata.getIssuer().getValue(), "Missing authentication data.");
        
            return this.alternateFlow.run();
        }
        
        try {
            TokenRequest tokenReq;
            if (this.clientSecret == null) {
                tokenReq = new TokenRequest(
                        this.metadata.getTokenEndpointURI(),
                        this.clientId,
                        new RefreshTokenGrant(this.cachedResponse.tokens.getRefreshToken()));
            } else {
                tokenReq = new TokenRequest(
                        this.metadata.getTokenEndpointURI(),
                        new ClientSecretBasic(this.clientId, this.clientSecret),
                        new RefreshTokenGrant(this.cachedResponse.tokens.getRefreshToken()));
            }
            return NimbusHelper.requestOidcTokens(this.metadata, tokenReq);
        } catch (IOException e) {
            if (this.alternateFlow == null)
                throw e;
        
            try {
                return this.alternateFlow.run();
            } catch (RuntimeException e2) {
                e2.addSuppressed(e);
                throw e2;
            } catch (IOException e2) {
                e2.addSuppressed(e);
                throw e2;
            }
        }
        
    }

}
