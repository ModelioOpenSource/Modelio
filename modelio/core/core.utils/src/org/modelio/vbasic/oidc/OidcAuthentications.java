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
import java.net.URI;
import java.time.Instant;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.Issuer;
import com.nimbusds.oauth2.sdk.token.BearerAccessToken;
import com.nimbusds.oauth2.sdk.token.RefreshToken;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderConfigurationRequest;
import com.nimbusds.openid.connect.sdk.op.OIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import javax.net.ssl.SSLException;
import net.minidev.json.JSONObject;
import org.modelio.vbasic.auth.OidcAuthData;
import org.modelio.vbasic.net.HttpErrorMapper;
import org.modelio.vbasic.net.HttpUriException;
import org.modelio.vbasic.net.SslManager;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;
import org.modelio.vbasic.oidc.flows.OidClientCredentialsflow;
import org.modelio.vbasic.oidc.flows.OidUserPasswordFlow;
import org.modelio.vbasic.oidc.flows.OidcRefreshTokenFlow;

/**
 * OIDC (Open ID) authentication flows entry point.
 * <p>
 * It works as a factory to obtain an {@link IOidcAuthenticationFlow}.
 * <h2>Usage</h2>
 * <ol>
 * <li>Instantiate with {@link #fromServer(URI)} . Then : <ul>
 * <li>{@link #browserFlow(IOidcWebBrowser)} to create a web browser based flow builder then {@link OidcBrowserFlowBuilder} methods to further configure.
 * <li>{@link #clientCredentialsFlow(String, String)} to create a client id/secret authentication flow
 * <li>{@link #userPasswordFlow(String, String, String, String)} to create a user password based flow.<br>
 * <b>Note :</b> This flow works only for accounts not linked to an identity provider(LDAP, Azure, ...) </li>
 * <li>{@link #tokenFlow(String, String, String)} to create a refresh token or offline token authentication flow.
 * </ul>
 * <li> Call {@link IOidcAuthenticationFlow#createAuthData()} to get a usable {@link org.modelio.vbasic.auth.IAuthData IAuthData}.
 * </ol>
 * 
 * 
 * @author cmarin
 * @since 5.2
 */
@objid ("76b5ff78-e03d-4232-a75f-60235eb71f8e")
public class OidcAuthentications {
    /**
     * The client ID defined in keycloak for the Modelio application.
     */
    @objid ("061bddd7-7be5-4e0f-b573-a6658bd0fc0b")
    public static final String PUBLIC_CLIENT_ID = "com-modelio-wildfly-public";

    @objid ("0fbaeb60-116d-41d0-a7c0-338fae1e6593")
    private OIDCProviderMetadata oidcProviderMetadata;

    @objid ("1ec9012e-f58f-4cf1-98bc-25010f4b053b")
    private URI modelioServerUrl;

    /**
     * @param modelioServerUrl the Modelio server URI
     * @return an OidcAuthentications
     */
    @objid ("f0dfb0b1-77c2-4849-a0cf-3de3bb4b2c04")
    public static OidcAuthentications fromServer(URI modelioServerUrl) {
        return new OidcAuthentications(modelioServerUrl);
    }

    /**
     * @param modelioServerUrl the Modelio server URI
     */
    @objid ("039d3b6d-734a-47a6-b6a2-4e54544ef596")
    public  OidcAuthentications(URI modelioServerUrl) {
        this.modelioServerUrl = modelioServerUrl;
    }

    /**
     * Get or load OIDC provider metadatas from "$server/.well-known/openid-configuration" standard URL.
     * @return Modelio server OIDC provider metadatas
     * @throws HttpUriException on HTTP failure
     * @throws IOException on failure
     */
    @objid ("d055f63c-07a6-4e8c-a7fd-a628f58c6db0")
    OIDCProviderMetadata getOIDCProviderMetadata() throws HttpUriException, IOException {
        if (this.oidcProviderMetadata == null) {
            this.oidcProviderMetadata = reloadOIDCProviderMetadata();
        }
        return this.oidcProviderMetadata;
    }

    /**
     * Load OIDC provider metadatas from "$server/.well-known/openid-configuration" standard URL.
     * @return Modelio server OIDC provider metadatas
     * @throws HttpUriException on HTTP failure
     * @throws IOException on failure
     */
    @objid ("d558b256-8305-4639-8374-41d5094016fb")
    private OIDCProviderMetadata reloadOIDCProviderMetadata() throws HttpUriException, IOException {
        // This need to be run once, could be moved to a static initializer
        HTTPRequest.setDefaultSSLSocketFactory(SslManager.getInstance().getSslContext().getSocketFactory());
        
        
        HTTPRequest httpRequest = new OIDCProviderConfigurationRequest(new Issuer(this.modelioServerUrl)).toHTTPRequest();
        HTTPResponse response;
        try {
            response = httpRequest.send();
        } catch (SSLException e) {
            // May be self signed certificate problem, in this case ask the user
            if (! SslManager.getInstance().fixUntrustedServer(e, this.modelioServerUrl)) {
                throw e;
            }
            // Try again
            response = httpRequest.send();
        }
        
        if (! response.indicatesSuccess()) {
            throw HttpErrorMapper.create(response.getStatusCode(), httpRequest.getURL().toString(), response.getContent(), null);
        }
        
        try {
            JSONObject js = response.getContentAsJSONObject();
            OIDCProviderMetadata providerMetadata = OIDCProviderMetadata.parse(js);
        
            return providerMetadata;
        } catch (ParseException e) {
            throw HttpErrorMapper.create(response.getStatusCode(), httpRequest.getURL().toString(), "Response parsing failed: "+e.getLocalizedMessage(), e);
        }
        
    }

    /**
     * Build a browser based authentication flow.
     * <p>
     * @param webBrowser the web browser implementation
     * @return a browser flow builder.
     * @throws HttpUriException on HTTP error calling the authentication server
     * @throws IOException on I/O error calling the authentication server
     */
    @objid ("fd43e930-3c57-480b-bd56-f3b50382f837")
    public OidcBrowserFlowBuilder browserFlow(IOidcWebBrowser webBrowser) throws HttpUriException, IOException {
        Objects.requireNonNull(webBrowser, "Browser not specified");
        
        OIDCProviderMetadata providerMetadata = getOIDCProviderMetadata();
        return new OidcBrowserFlowBuilder(providerMetadata, webBrowser);
    }

    /**
     * Create a client/secret based authentication flow
     * @param clientId the OIDC client id
     * @param clientSecret the client secret, may not be null.
     * @return the built authentication flow
     * @throws IOException on failure calling the authentication server
     */
    @objid ("c302b10c-6de4-4fbe-ad5c-478a5e33823c")
    public IOidcAuthenticationFlow clientCredentialsFlow(String clientId, String clientSecret) throws IOException {
        Objects.requireNonNull(clientId, "Client ID not specified");
        Objects.requireNonNull(clientSecret, "Client secret not specified");
        
        OIDCProviderMetadata providerMetadata = getOIDCProviderMetadata();
        ClientID nimbusClientId = new ClientID(clientId);
        Secret nimbusSecret = makeSecret(clientSecret);
        return refreshable(nimbusClientId, nimbusSecret, providerMetadata , new OidClientCredentialsflow(providerMetadata, nimbusClientId, nimbusSecret));
    }

    /**
     * Create an access token, a refresh token or offline token authentication flow
     * @param clientId the OIDC client id
     * @param clientSecretStr the client secret, may be null.
     * @param token the refresh or offline token, must not be null
     * @return the built authentication flow
     * @throws IOException on failure calling the authentication server
     */
    @objid ("4c9db470-bbbe-4bbc-9e35-415d6d6edac1")
    public IOidcAuthenticationFlow tokenFlow(String clientId, String clientSecretStr, String token) throws IOException {
        Objects.requireNonNull(token, "token not specified");
        
        OIDCProviderMetadata providerMetadata = getOIDCProviderMetadata();
        ClientID nimbusClientId = new ClientID(clientId);
        Secret clientSecret = makeSecret(clientSecretStr);
        
        IOidcAuthenticationFlow accessFlow = createAccessTokenFlow(token);
        if (accessFlow != null) {
            return accessFlow;
        } else {
            OIDCTokens oidctokens = new OIDCTokens(new BearerAccessToken(), new RefreshToken(token));
            return new OidcRefreshTokenFlow(nimbusClientId, clientSecret, providerMetadata, null, new AuthResponse(oidctokens, Instant.MIN));
        }
        
    }

    @objid ("05db7574-9958-443c-8303-7f96421d4408")
    private static IOidcAuthenticationFlow createAccessTokenFlow(String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            Object tokenType = jwt.getJWTClaimsSet().getClaim("typ");
            if (! "Bearer".equals(tokenType))
                return null;
        
            String sub = jwt.getJWTClaimsSet().getSubject();
            return new AccessTokenFlow(token, sub);
        } catch (java.text.ParseException e) {
            return null;
        }
        
    }

    /**
     * @param clientId the OIDC client id
     * @param clientSecret the client secret, may not be null.
     * @param user the user login
     * @param password the user password
     * @return the built authentication flow
     * @throws IOException on failure calling the authentication server
     */
    @objid ("37165ff2-8300-4952-bd8f-26fb97f7e21b")
    public IOidcAuthenticationFlow userPasswordFlow(String clientId, String clientSecret, String user, String password) throws IOException {
        OIDCProviderMetadata providerMetadata = getOIDCProviderMetadata();
        ClientID nimbusClientId = new ClientID(clientId);
        Secret nimbusSecret = makeSecret(clientSecret);
        return refreshable(
                nimbusClientId,
                nimbusSecret,
                providerMetadata ,
                new OidUserPasswordFlow(
                        providerMetadata,
                        nimbusClientId,
                        nimbusSecret,
                        user,
                        makeSecret(password)));
        
    }

    @objid ("06661052-8d42-4db3-a557-7b316794f122")
    static Secret makeSecret(String secretOrNull) {
        if (secretOrNull == null)
            return null;
        return new Secret(secretOrNull);
    }

    @objid ("89fc464b-38ab-470d-87dc-92ef19824e5d")
    static IOidcAuthenticationFlow refreshable(ClientID clientId, Secret clientSecret, OIDCProviderMetadata providerMetadata, IOidcAuthenticationFlow flow) {
        return new OidcRefreshTokenFlow(clientId, clientSecret, providerMetadata, flow);
    }

    /**
     * Authentication flow where we already have an access token with no way to refresh it.
     */
    @objid ("711b43df-dd14-49d9-bbe3-16c2bea58ef4")
    private static class AccessTokenFlow implements IOidcAuthenticationFlow {
        @objid ("a7870612-6a12-417a-9583-6e4a6363dbd6")
        private final AuthResponse resp;

        @objid ("c7c1cb70-b302-4e53-bfdf-9620f85f6180")
        private OidcAuthData oidcAuthData;

        @objid ("91360003-1bd0-472b-9c44-ac5161fcc972")
        public  AccessTokenFlow(String token, String subject) {
            OIDCTokens oidcTokens = new OIDCTokens(new BearerAccessToken(token), null);
            this.resp = new AuthResponse(oidcTokens, Instant.MAX);
            this.oidcAuthData = new OidcAuthData(() -> token, subject);
            
        }

        @objid ("89e76129-5799-4b9a-8034-e225887c1c2e")
        @Override
        public AuthResponse run() throws IOException {
            return this.resp;
        }

        @objid ("e7390d90-115f-4f8a-9ed5-7c3d365a9569")
        @Override
        public OidcAuthData createAuthData() throws IOException {
            // TODO Auto-generated method stub
            return IOidcAuthenticationFlow.super.createAuthData();
        }

    }

}
