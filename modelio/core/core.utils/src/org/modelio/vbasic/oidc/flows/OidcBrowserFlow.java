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
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.nimbusds.jwt.JWT;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.pkce.CodeChallengeMethod;
import com.nimbusds.oauth2.sdk.pkce.CodeVerifier;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import com.nimbusds.openid.connect.sdk.Nonce;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import com.nimbusds.openid.connect.sdk.op.ReadOnlyOIDCProviderMetadata;
import com.nimbusds.openid.connect.sdk.token.OIDCTokens;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.bootstrap.ServerBootstrap;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.net.HttpErrorMapper;
import org.modelio.vbasic.net.UriAuthenticationException;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow;
import org.modelio.vbasic.oidc.IOidcAuthenticationFlow.AuthResponse;
import org.modelio.vbasic.oidc.IOidcWebBrowser;
import org.modelio.vbasic.oidc.OidcBrowserFlowBuilder;

@objid ("90dff099-af82-4b41-9d27-8af9cf46b7d3")
@SuppressWarnings ("restriction")
public class OidcBrowserFlow implements IOidcAuthenticationFlow {
    @objid ("abcb820d-177f-466d-9df6-ff3f497c87dd")
    private final ReadOnlyOIDCProviderMetadata metadata;

    @objid ("9c5c9694-0732-4d95-ae42-068a8e28e46b")
    private final ClientID clientId;

    @objid ("ca55047a-933c-4d52-a41a-d1ff4959ee5d")
    private final Secret clientSecret;

    @objid ("4ce4f485-2754-418b-997b-58462752cacc")
    private final String loginHint;

    @objid ("854d0079-0e22-4f0b-8ea9-febc5b417089")
    private JWT idTokenHint;

    @objid ("dbc26960-f618-4800-a285-f92a7343f3cf")
    private final IOidcWebBrowser webBrowser;

    /**
     * Used to schedule timeout on login process.
     */
    @objid ("937e9e53-f16b-4919-8109-cdce17e73c56")
    private static final ScheduledExecutorService timerExecutor = createTimerExecutor();

    /**
     * Already running authentication process.
     * <p>
     * All accesses must be synchronized with {@link #runningAuthFlowGuard} .
     */
    @objid ("2b56cb6f-453e-49a1-8e3d-b72884f95ce7")
    private CompletableFuture<AuthResponse> runningAuthFlow;

    /**
     * concurrency lock object for {@link #runningAuthFlow}
     */
    @objid ("d0dd4256-9dd1-47a4-9b75-b1e12976b393")
    private final Object runningAuthFlowGuard = new Object();

    @objid ("c7e2a0dc-78d1-4964-b7a0-8109415c990d")
    private static ScheduledExecutorService createTimerExecutor() {
        AtomicInteger incr = new AtomicInteger(0);
        ThreadFactory threadFactory = (ThreadFactory) r -> new Thread(r, "OIDC login timeout timer -"+incr.incrementAndGet());
        ScheduledThreadPoolExecutor timerService = new ScheduledThreadPoolExecutor(1,  threadFactory);
        timerService.setRemoveOnCancelPolicy(true);
        return timerService;
    }

    @objid ("2f5866c8-ca9e-4978-a58b-1e65a6f491fd")
    public  OidcBrowserFlow(ReadOnlyOIDCProviderMetadata metadata, ClientID clientId, Secret clientSecret, IOidcWebBrowser webBrowser, OIDCTokens startTokens) {
        this.metadata = metadata;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.webBrowser = webBrowser;
        this.idTokenHint = startTokens != null ? startTokens.getIDToken() : null;
        this.loginHint = null;
        
    }

    @objid ("38ae02d0-a423-42ea-96a3-f5709b5f11f0")
    public  OidcBrowserFlow(ReadOnlyOIDCProviderMetadata metadata, ClientID clientId, Secret clientSecret, IOidcWebBrowser webBrowser) {
        this(metadata, clientId, clientSecret, webBrowser, null);
    }

    @objid ("979c845a-9fe1-428b-a893-653efc658cdf")
    public  OidcBrowserFlow(ReadOnlyOIDCProviderMetadata providerMetadata, OidcBrowserFlowBuilder cmd) throws IOException {
        this.metadata =  providerMetadata;
        this.clientId = new ClientID(cmd.getClientId());
        this.clientSecret = cmd.getSecret() == null ? null : new Secret(cmd.getSecret());
        this.webBrowser = cmd.getWebBrowser();
        this.loginHint = cmd.getLoginHint();
        if (cmd.getPreviousAuth() != null) {
            AuthResponse prevtokens = cmd.getPreviousAuth().run();
            this.idTokenHint = prevtokens.tokens.getIDToken();
        } else {
            this.idTokenHint = null;
        }
        
    }

    /**
     * Parse the redirect URI HTTP request received by our local server and extract the authentication authorization code.
     * @param requestURL the called URI
     * @param state the authentication flow state
     * @return the authorization code
     * @throws IOException on failure
     */
    @objid ("c4595115-e325-412f-8e78-c0a2b2fdb294")
    private AuthorizationCode parseAuthenticationResponse(String requestURL, State state) throws IOException {
        AuthenticationResponse authResp = null;
        try {
            authResp = AuthenticationResponseParser.parse(new URI(requestURL));
        } catch (ParseException e) {
            throw new UriAuthenticationException(e, requestURL.toString(), e.getLocalizedMessage());
            //throw new IOException("Failed parsing '"+requestURL+"' : "+e.getLocalizedMessage(), e);
        } catch (URISyntaxException e) {
            throw new UriAuthenticationException(e, requestURL.toString(), e.getLocalizedMessage());
            //throw new IOException("Failed parsing '"+requestURL+"' : "+e.getLocalizedMessage(), e);
        }
        
        if (authResp instanceof AuthenticationErrorResponse) {
          ErrorObject error = authResp.toErrorResponse().getErrorObject();
          throw HttpErrorMapper.create(error.getHTTPStatusCode(), this.metadata.getAuthorizationEndpointURI().toString(), error.getDescription(), null);
          //throw new IOException("Server refused: "+error.getCode()+" "+error.getDescription());
        }
        
        AuthenticationSuccessResponse successResponse = authResp.toSuccessResponse();
        
        /* Don't forget to check the state!
         * The state in the received authentication response must match the state
         * specified in the previous outgoing authentication request.
        */
        if (successResponse.getState() != null && ! Objects.equals(successResponse.getState(), state)) {
            throw new UriAuthenticationException("Request state does not match response state, May indicate man in the middle attack!");
        }
        
        AuthorizationCode authCode = successResponse.getAuthorizationCode();
        return authCode;
    }

    @objid ("af8a0063-f4cd-4119-befc-24fb71bd249e")
    private URI computeAuthcodeRequest(URI redirectUri, State state, CodeVerifier codeVerifier, Nonce nonce) throws IOException {
        // Specify scope
        Scope scope = new Scope(OIDCScopeValue.OPENID);
        
        
        CodeChallengeMethod codeChallengeMethod = chooseCodeChallengeMethod();
        
        AuthenticationRequest authReq = new AuthenticationRequest.Builder(ResponseType.CODE, scope, this.clientId, redirectUri)
        .nonce(nonce)
        .state(state)
        .endpointURI(this.metadata.getAuthorizationEndpointURI())
        //.display(Display.getDefault())
        //.prompt(new Prompt(Prompt.Type.LOGIN))
        .loginHint(this.idTokenHint == null ? this.loginHint : null)
        .idTokenHint(this.idTokenHint)
        .responseMode(this.metadata.getResponseModes().get(0))
        .codeChallenge(codeVerifier, codeChallengeMethod)
        .build();
        return authReq.toURI();
    }

    @objid ("ace80690-2d2e-405e-8a4f-c67a205c9060")
    private CodeChallengeMethod chooseCodeChallengeMethod() throws IOException {
        List<CodeChallengeMethod> methods = this.metadata.getCodeChallengeMethods();
        CodeChallengeMethod codeChallengeMethod = methods.get(methods.size()-1);
        
        if (methods.contains(CodeChallengeMethod.S256)) {
            codeChallengeMethod = CodeChallengeMethod.S256;
        } else if (methods.contains(CodeChallengeMethod.PLAIN)) {
            codeChallengeMethod = CodeChallengeMethod.PLAIN;
        } else {
            throw new IOException("The identity server support no known PKCE challenge method");
        }
        return codeChallengeMethod;
    }

    @objid ("dc991b38-4891-4671-9c7b-0e30b865ecb7")
    private HttpServer startLocalServer(State state, CompletableFuture<AuthorizationCode> futureAuthCode) throws IOException {
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        
        
        HttpServer server = ServerBootstrap
              .bootstrap()
              .setListenerPort(0)
              .setLocalAddress(loopbackAddress)
              .registerHandler("/", (req, resp, context) -> {
                String uri = req.getRequestLine().getUri();
        
                try {
                    AuthorizationCode authCode = parseAuthenticationResponse(uri, state);
                    NimbusDumper.logTrace(this, "Auth code received by local server. ");
                    futureAuthCode.complete(authCode);
        
                    String content = "<center><h1>Authentication successful</h1><p>Just wait for the browser to close.</p></center> ";
                    StringEntity stringEntity = new StringEntity(content, StandardCharsets.UTF_8.name());
                    stringEntity.setContentType("text/html");
                    resp.setEntity(stringEntity);
                    resp.setStatusCode(200);
                } catch (IOException e) {
                    futureAuthCode.completeExceptionally(e);
        
                    String content = "Failure : "+ FileUtils.getLocalizedMessage(e);
                    resp.setEntity(new StringEntity(content, StandardCharsets.UTF_8.name()));
                    resp.setStatusCode(500);
                    Log.error(e);
                }
              })
              .create();
          server.start();
        return server;
    }

    @objid ("54dd8db8-7632-4148-ad06-465a96f36afb")
    @Override
    public AuthResponse run() throws IOException {
        // Ensure only one authentication is running at once.
        // If one is already running, wait for it to end and return its answer.
        CompletableFuture<AuthResponse> flowEnded = getOrCreateBrowserProcess();
        
        try {
            // Wait for the login process to end
            return flowEnded.get();
        } catch (CancellationException e) {
            // Login cancelled by the user
            throw new UriAuthenticationException(e, this.metadata.getAuthorizationEndpointURI().toString(), e.getLocalizedMessage());
        } catch (InterruptedException e) {
            // Unexpected java interruption
            Thread.currentThread().interrupt();
            throw new UriAuthenticationException(e, this.metadata.getAuthorizationEndpointURI().toString(), e.getLocalizedMessage());
        } catch (ExecutionException e) {
            // The login process failed
            String msg = e.getCause().getLocalizedMessage();
            if (e.getCause() instanceof IOException) {
                msg = FileUtils.getLocalizedMessage((IOException) e.getCause());
            } else if (e.getCause() instanceof TimeoutException) {
                // Our timeout was triggered
                msg = "Authentication time out elapsed.";
            }
        
            throw new UriAuthenticationException(e, this.metadata.getAuthorizationEndpointURI().toString(), msg);
        }
        
    }

    /**
     * Get a browser login process.
     * <p>
     * Ensures only one authentication is running at once.
     * If one is already running, return it directly.
     * @return a future that will be completed when the user login process ends.
     * @throws IOException on failure creating the login process.
     */
    @objid ("8fb26b9e-8b08-4ab4-9206-25e9c8c43a8a")
    private CompletableFuture<AuthResponse> getOrCreateBrowserProcess() throws IOException {
        CompletableFuture<AuthResponse> flowEnded;
        synchronized (this.runningAuthFlowGuard) {
            flowEnded = this.runningAuthFlow;
            if (flowEnded == null) {
                flowEnded = createBrowserProcess();
                this.runningAuthFlow = flowEnded;
        
                flowEnded.whenComplete((r,t) -> {
                    synchronized(this.runningAuthFlowGuard) {
                        this.runningAuthFlow = null;
                    }
                });
            }
        }
        return flowEnded;
    }

    /**
     * Create a new browser login process.
     * @return a future that will be completed when the user login process ends.
     * @throws IOException on failure creating the login process.
     */
    @objid ("a0cc5d75-999c-4502-b638-8334b9fe1b11")
    private CompletableFuture<AuthResponse> createBrowserProcess() throws IOException {
        // Generate nonce
        Nonce nonce = new Nonce();
        
        // Generate random state string for pairing the response to the request
        State state = new State();
        
        // Start an ephemeral local web server that will receive auth code
        // This future is completed by the ephemeral HTTP server
        CompletableFuture<AuthorizationCode> futureAuthCode = new CompletableFuture<>();
        
        HttpServer server = startLocalServer(state, futureAuthCode);
        URI embeddedServerUri = computeServerUri(server);
        NimbusDumper.logTrace(this, "Server listening on: %s", embeddedServerUri);
        
        CodeVerifier codeVerifier = new CodeVerifier();
        
        // Compute Keycloak request URL
        URI reqUri = this.computeAuthcodeRequest(embeddedServerUri, state, codeVerifier, nonce);
        
        // Setup async process
        
        CompletableFuture<AuthResponse> flowEnded = futureAuthCode
                .thenApply(code -> {
                    NimbusDumper.logTrace(this, "OIDC Authorization code received. ");
                    try {
                        NimbusDumper.logTrace(this, "Requesting OIDC tokens ...");
                        AuthResponse tokens = getTokenFromAuthcode(code, embeddedServerUri, codeVerifier, nonce);
                        NimbusDumper.logTrace(this, "Received %s access token, %s refresh token, %s ID token.",
                                (tokens.tokens.getAccessToken() != null ? "one" : "NO"),
                                (tokens.tokens.getRefreshToken() != null ? "one" : "NO"),
                                (tokens.tokens.getIDToken() != null ? "one" : "NO")
                                );
                        this.idTokenHint = tokens.tokens.getIDToken();
                        return tokens;
                    } catch (IOException e) {
                        throw new CompletionException(e);
                    }
                });
        
        
        addTimeout(flowEnded);
        
        flowEnded.whenCompleteAsync((t,e) -> {
            NimbusDumper.logTrace(this, "Requesting browser close.");
            this.webBrowser.closeBrowser();
        
            NimbusDumper.logTrace(this, "Stopping server: %s", embeddedServerUri);
            server.shutdown(5, TimeUnit.SECONDS);
            NimbusDumper.logTrace(this, "Stopped server: %s", embeddedServerUri);
        });
        
        // Launch login process
        NimbusDumper.logTrace(this, "Open browser toward: %s..." , reqUri.resolve(reqUri.getRawPath())); // Redact URI request parameters in logs
        this.webBrowser.browse(reqUri, () -> {
            if (flowEnded.isDone()) {
                NimbusDumper.logTrace(this, "Browser close detected after login ended, nothing to do.");
            } else {
                NimbusDumper.logTrace(this, "Browser closed during login process, abort login.");
                flowEnded.completeExceptionally(new CancellationException("Login cancelled by the user"));
            }
        });
        return flowEnded;
    }

    /**
     * Add a 10 minutes time out to the given process.
     * <p>
     * This method will be obsolete once Java 11 is enforced everywhere ,
     * {@link CompletableFuture#completeOnTimeout(Object, long, TimeUnit)} will be used instead.
     * @param browserFlow a process to time out
     * @return a future that will complete either when the given process end or when the time out is triggered.
     */
    @objid ("c2ac90ce-4ab7-48bd-8362-f19ef7d5e6f8")
    private CompletableFuture<AuthResponse> addTimeout(CompletableFuture<AuthResponse> browserFlow) {
        // Create a time out
        ScheduledFuture<Boolean> wasTimedOut = timerExecutor.schedule(() -> {
            NimbusDumper.logTrace(this, "Time out waiting for login to end.");
            return browserFlow.completeExceptionally(new TimeoutException());
        }, 10, TimeUnit.MINUTES);
        
        // Cancel the time out if the process ends before
        return browserFlow.whenComplete((res,err) -> wasTimedOut.cancel(true));
    }

    /**
     * @param server the embedded HTTP server
     * @return the embedded HTTP server URI.
     * @throws IOException if unable to compute an URI, should never happen
     */
    @objid ("fa6eee9b-d1df-4926-ab0a-4be340fe407f")
    private static URI computeServerUri(HttpServer server) throws IOException {
        URI redirectUri;
        try {
            redirectUri = new URI("http", null, server.getInetAddress().getHostName(), server.getLocalPort(), null, null, null);
        } catch (URISyntaxException e) {
            throw new IOException(server.getInetAddress().getHostName()+":"+server.getLocalPort()+": "+e.getLocalizedMessage(), e);
        }
        return redirectUri;
    }

    @objid ("d3ba1343-fb24-457a-8b5d-2443d2ba7e13")
    private AuthResponse getTokenFromAuthcode(AuthorizationCode authCode, URI redirectURI, CodeVerifier codeVeririer, Nonce nonce) throws IOException {
        TokenRequest tokenReq;
        if (this.clientSecret != null) {
            tokenReq = new TokenRequest(
                    this.metadata.getTokenEndpointURI(),
                    new ClientSecretBasic(this.clientId, this.clientSecret),
                    new AuthorizationCodeGrant(authCode, redirectURI, codeVeririer));
        } else {
            tokenReq = new TokenRequest(this.metadata.getTokenEndpointURI(),
                    this.clientId,
                    new AuthorizationCodeGrant(authCode, redirectURI, codeVeririer));
        }
        
        
        AuthResponse oidcTokens = NimbusHelper.requestOidcTokens(this.metadata, tokenReq);
        return oidcTokens;
    }

}
