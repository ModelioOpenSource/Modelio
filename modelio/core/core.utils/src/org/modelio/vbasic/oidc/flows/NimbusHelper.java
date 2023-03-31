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
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.RemoteJWKSet;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.SerializeException;
import com.nimbusds.oauth2.sdk.TokenErrorResponse;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.nimbusds.openid.connect.sdk.Nonce;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import com.nimbusds.openid.connect.sdk.op.ReadOnlyOIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import com.nimbusds.openid.connect.sdk.validators.IDTokenClaimsVerifier;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.HttpErrorMapper;
import org.modelio.vbasic.net.HttpUriAuthenticationException;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;

/**
 * OAuth / OIDC helper methods.
 */
@objid ("d27ac78e-ca2b-47c3-83d6-6c52fc0568e0")
public class NimbusHelper {
    @objid ("d07b72e6-f1bb-4c45-9834-f6a869f86213")
    private  NimbusHelper() {
        // No instance (except for NimbusDumper.logTrace)
    }

    /**
     * Send an OIDC token request, wait and decode HTTP response.
     * @param metadata OIDC provider metadatas
     * @param tokenReq the token request
     * @return the received OIDC tokens
     * @throws IOException on failure
     */
    @objid ("e5f890d3-4bbe-4740-bbcb-2a478ab5695f")
    public static AuthResponse requestOidcTokens(ReadOnlyOIDCProviderMetadata metadata, TokenRequest tokenReq) throws IOException {
        HTTPResponse tokenHTTPResp = null;
        HTTPRequest httpRequest = tokenReq.toHTTPRequest();
        try {
            httpRequest.setConnectTimeout(10_000); // 10s
            httpRequest.setReadTimeout(20_000); // 20s
            tokenHTTPResp = httpRequest.send();
            Log.trace("NimbusHelper: Received OIDC HTTP %d response.", tokenHTTPResp.getStatusCode());
        } catch (SerializeException e) {
            throw new IOException("Failed serializing token request: "+e.getLocalizedMessage(), e);
        } catch (IOException e) {
            throw new IOException("Failed sending token request: "+FileUtils.getLocalizedMessage(e), e);
        }
        
        // Parse and check response
        TokenResponse tokenResponse = null;
        try {
            //System.out.println("Parsing response "+OidcDumper.dump(tokenHTTPResp));
        
            tokenResponse = OIDCTokenResponseParser.parse(tokenHTTPResp);
        } catch (ParseException e) {
            throw HttpErrorMapper.create(tokenHTTPResp.getStatusCode(),
                    tokenReq.getEndpointURI().toString(),
                    MessageFormat.format("HTTP {0} {1}: non parseable: {2}", tokenHTTPResp.getStatusCode(), tokenHTTPResp.getStatusMessage(), tokenHTTPResp.getContent()),
                    e);
        }
        
        if (tokenResponse instanceof TokenErrorResponse) {
            ErrorObject error = tokenResponse.toErrorResponse().getErrorObject();
            throw HttpErrorMapper.create(tokenHTTPResp.getStatusCode(),
                    tokenReq.getEndpointURI().toString(),
                    MessageFormat.format("HTTP {0}: {1}", error.getHTTPStatusCode(), NimbusDumper.prettyPrint(error.toJSONObject())),
                    null);
        }
        
        OIDCTokenResponse accessTokenResponse = (OIDCTokenResponse) tokenResponse;
        OIDCTokens oidcTokens = accessTokenResponse.getOIDCTokens();
        
        try {
            return createAuthResponse(metadata, oidcTokens);
        } catch (java.text.ParseException | BadJOSEException | JOSEException e) {
            throw new HttpUriAuthenticationException(0, e, tokenReq.getEndpointURI().toString(), e.getLocalizedMessage());
        }
        
    }

    @objid ("ab4eb787-e379-4a78-9000-69d3c0dfdd8e")
    private static DefaultJWTProcessor<SecurityContext> createJwtProcessor(ReadOnlyOIDCProviderMetadata metadata) throws MalformedURLException {
        DefaultJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
        RemoteJWKSet<SecurityContext> jwkSet = new RemoteJWKSet<>(metadata.getJWKSetURI().toURL());
        
        jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(new HashSet<>(metadata.getAuthorizationJWSAlgs()), jwkSet));
        jwtProcessor.setJWEKeySelector(null);
        return jwtProcessor;
    }

    @objid ("8c02dc24-921e-4168-ba4c-dac96f2829fa")
    private static void validateIdToken(OIDCTokens tokens, ReadOnlyOIDCProviderMetadata metadata, ClientID clientId, Nonce nonce) throws IOException {
        JWT idToken = tokens.getIDToken();
        if (idToken != null) {
            try {
                int maxClockSkewSeconds = (int) TimeUnit.MINUTES.toSeconds(5);
                IDTokenClaimsVerifier verifier = new IDTokenClaimsVerifier(metadata.getIssuer(), clientId, nonce, maxClockSkewSeconds);
        
                DefaultJWTProcessor<SecurityContext> jwtProcessor = createJwtProcessor(metadata);
                jwtProcessor.setJWTClaimsSetVerifier(verifier);
                jwtProcessor.process(idToken, null);
            } catch (BadJOSEException e) {
                throw new IOException(e.getLocalizedMessage(), e);
            } catch (JOSEException e) {
                throw new IOException(e.getLocalizedMessage(), e);
            }
        }
        
    }

    /**
     * Validate OIDC tokens and find expiration time from access token claims, ID token or access token response.
     * @throws BadJOSEException  If the JWT is rejected.
     * @throws JOSEException  If an internal processing exception is encountered.
     * @param metadata OIDC provider metadatas
     * @param tokens OIDC tokens
     * @return the tokens with the computed
     * @throws java.text.ParseException on failure parsing access token
     * @throws MalformedURLException should not happen, means server URL is not valid
     */
    @objid ("f33937a8-4179-4665-8980-7e1b62bbcfa7")
    private static AuthResponse createAuthResponse(ReadOnlyOIDCProviderMetadata metadata, OIDCTokens tokens) throws java.text.ParseException, MalformedURLException, BadJOSEException, JOSEException {
        Log.trace("NimbusHelper: Validating OIDC tokens...");
        Instant expiration = null;
        DefaultJWTProcessor<SecurityContext> jwtProcessor = createJwtProcessor(metadata);
        
        if (tokens.getIDToken() != null) {
            JWTClaimsSet claimset = jwtProcessor.process(tokens.getIDToken(), null);
            Date expirationTime = claimset.getExpirationTime();
            if (expirationTime != null) {
                Log.trace("NimbusHelper: Expiration found in ID token: %s", expirationTime);
                expiration = expirationTime.toInstant();
            }
        }
        
        AccessToken accessToken = tokens.getAccessToken();
        if (accessToken != null) {
            JWTClaimsSet claimset = jwtProcessor.process(accessToken.getValue(), null);
            Date expirationTime = claimset.getExpirationTime();
            if (expirationTime != null) {
                Log.trace("NimbusHelper: Expiration found in Access token JWT claims: %s", expirationTime);
                expiration = expirationTime.toInstant();
            } else {
                long lifetime = accessToken.getLifetime();
                if (lifetime > 0) {
                    Log.trace("NimbusHelper: Lifetime found in Access token: %s", expirationTime);
                    expiration = Instant.now().plusSeconds(lifetime);
                }
            }
        }
        
        if (expiration == null) {
            Log.warning("NimbusHelper: No expiration information found either in Id token, access token claims or access token response, default to 5 minutes");
            expiration = Instant.now().plus(5, ChronoUnit.MINUTES);
        }
        return new AuthResponse(tokens, expiration);
    }

    /**
     * Look for the subject either in the ID token or in the access token.
     * @param tokens the OIDC tokens
     * @return the tokens subject
     * @throws IOException on failure
     */
    @objid ("9a46a86e-df0c-4a4e-a882-84622dc47e56")
    public static String getSubject(OIDCTokens tokens) throws IOException {
        if (tokens.getIDToken() != null) {
            try {
                return tokens.getIDToken().getJWTClaimsSet().getSubject();
            } catch (java.text.ParseException e) {
                throw new IOException("ID token is not valid JWT: "+e.getLocalizedMessage(), e);
            }
        }
        
        AccessToken accessToken = tokens.getAccessToken();
        
        if (accessToken != null) {
            try {
                JWT jwt = JWTParser.parse(accessToken.getValue());
        
                JWTClaimsSet claimset = jwt.getJWTClaimsSet();
                return claimset.getSubject();
            } catch (java.text.ParseException e) {
                throw new IOException("Access token is not a valid JWT: "+e.getLocalizedMessage(), e);
            }
        }
        
        throw new IOException("No ID token nor access token");
        
    }

    /**
     * Look for the JWT claims in the ID token or in the access token.
     * @param tokens the OIDC tokens
     * @return the JWT claims
     * @throws IOException on failure
     */
    @objid ("3d42cff5-7064-4032-879c-ba99a0083656")
    public static JWTClaimsSet getJWT(OIDCTokens tokens) throws IOException {
        if (tokens.getIDToken() != null) {
            try {
                return tokens.getIDToken().getJWTClaimsSet();
            } catch (java.text.ParseException e) {
                throw new IOException("ID token is not valid JWT: "+e.getLocalizedMessage(), e);
            }
        }
        
        AccessToken accessToken = tokens.getAccessToken();
        
        if (accessToken != null) {
            try {
                return JWTParser.parse(accessToken.getValue()).getJWTClaimsSet();
            } catch (java.text.ParseException e) {
                throw new IOException("Access token is not a valid JWT: "+e.getLocalizedMessage(), e);
            }
        }
        
        throw new IOException("No ID token nor access token");
        
    }

}
