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
package org.modelio.vbasic.net;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.BasicUserPrincipal;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Lookup;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.NTLMSchemeFactory;
import org.apache.http.impl.auth.RFC2617Scheme;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.OidcAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;

/**
 * Entry point to use Apache HTTP client.
 * <p>
 * Allows to:
 * <ul>
 * <li>Get a default HTTP client with {@link #getDefaultClient()}
 * <li>Get a configured client builder you may customize with {@link #createClientBuilder()}
 * <li>Setup proxy authentication from system preferences set by Eclipse preferences dialog with {@link #configProxyCredentials(Properties, String, CredentialsProvider)}.
 * </ul>
 * The HTTP clients are configured to use {@link SslManager} to validate server certificates that may ask the user for confirmation in case of untrusted certificate.
 * 
 * @author cma
 * @since 3.7.1 : Extracted from {@link ApacheUriConnection} to be reused outside.
 */
@objid ("ea58b1b9-38a6-4b36-96e9-32f37f7016b7")
public class ApacheHttpClients {
    /**
     * Copy of {@link org.apache.http.impl.client.AuthenticationStrategyImpl#DEFAULT_SCHEME_PRIORITY} .
     * <p>
     * Needed for OAuth/token/OIDC authentication.
     * @since 5.2
     */
    @objid ("d9c58319-faaa-407f-9abc-1f03cba3d5c6")
    @SuppressWarnings ("javadoc")
    public static final List<String> DEFAULT_SCHEME_PRIORITY;

    @objid ("4ba63a3b-aab4-47f4-94f6-fcbd5158fd54")
    private static final HttpClient defaultClient;

    /**
     * Default auth scheme registry needed for OAuth/token/OIDC authentication.
     * @since 5.2
     */
    @objid ("c49ad6e4-0455-4bd6-9e4f-6891c13ec2f7")
    public static final Lookup<AuthSchemeProvider> DEFAULT_AUTH_SCHEME_REGISTRY;

    /**
     * Default {@link RequestConfig} needed for OAuth/token/OIDC authentication.
     * <p>
     * It is used to initialize {@link #createClientBuilder()}.
     * @since 5.2
     */
    @objid ("51661a9c-5a10-49a5-947f-89969e292b5b")
    public static final RequestConfig DEFAULT_REQUEST_CONFIG;

    /**
     * Configure HTTP(S) proxy authentication from the given properties .
     * <p>
     * Allows setup HTTP(S) proxy authentication from supplement system properties set by Eclipse.
     * <p>
     * http://docs.oracle.com/javase/7/docs/api/java/net/doc-files/net-properties.html:
     * <p>
     * There are 3 properties you can set to specify the proxy that will be used by the http protocol handler:
     * <ul>
     * <li>http.proxyHost: the host name of the proxy server
     * <li>http.proxyPort: the port number, the default value being 80.
     * <li>http.nonProxyHosts : not used in our case
     * </ul>
     * <p>
     * The Eclipse preference page allows modifying all of these. <code>proxyUser</code> and <code>proxyPassword</code> are properties not used by the JDK but set by Eclipse preference page.
     * @see <a href="http://stackoverflow.com/questions/1626549/authenticated-http-proxy-with-java">stackoverflow: Authenticated HTTP proxy with Java</a>
     * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/net/doc-files/net-properties.html">Java documentation: Networking Properties</a>
     * @see org.eclipse.core.internal.net.ProxyType
     * @param props configuration source
     * @param protocol "http" or "https"
     * @param credsProvider the credential provider to fill
     * @see org.eclipse.core.internal.net.ProxyType 
     */
    @objid ("fbda3db8-0195-4690-81dc-81ecfe95a586")
    private static void configProxyCredentials(Properties props, String protocol, CredentialsProvider credsProvider) {
        /*
         * http://docs.oracle.com/javase/7/docs/api/java/net/doc-files/net-properties.html:
         *
         * There are 3 properties you can set to specify the proxy that will be used by the http protocol handler: http.proxyHost: the host name of the proxy server http.proxyPort: the port number, the default value being 80.
         *
         * proxyUser and proxyPassword are not used by the JDK but are set by Eclipse preference page. see : org.eclipse.core.internal.net.ProxyType
         */
        
        String proxyHostKey = protocol + ".proxyHost";
        String proxyUserKey = protocol + ".proxyUser";
        
        if (props.containsKey(proxyHostKey) &&
                props.containsKey(proxyUserKey)) {
        
            String proxyPortKey = protocol + ".proxyPort";
            String proxyPasswdKey = protocol + ".proxyPassword";
        
            String proxyHost = props.getProperty(proxyHostKey);
            String proxyUser = props.getProperty(proxyUserKey);
            String proxyPwd = props.getProperty(proxyPasswdKey);
            String portStr = props.getProperty(proxyPortKey);
        
            int port = AuthScope.ANY_PORT;
            if (portStr != null) {
                try {
                    port = Integer.parseInt(portStr);
                } catch (RuntimeException e) {
                    // Log and ignore
                    Log.warning(e);
                }
            }
        
            final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(proxyUser, proxyPwd);
        
            final AuthScope authscope = new AuthScope(proxyHost, port);
            credsProvider.setCredentials(
                    authscope,
                    credentials);
        }
        
    }

    /**
     * Create a configured but still customizable {@link HttpClientBuilder}.
     * <p>
     * Please note the builder has a default :<ul>
     * <li> request config set as {@link #DEFAULT_REQUEST_CONFIG}.
     * <li> Auth scheme registry
     * @return a HttpClientBuilder
     */
    @objid ("bc1f0a0d-8efc-424f-ae30-745413b75e5e")
    public static HttpClientBuilder createClientBuilder() {
        //HostnameVerifier hostnameVerifier = new HostNameVerifier();
        return HttpClientBuilder.create()
                .useSystemProperties()
                .setSSLContext(SslManager.getInstance().getSslContext())
                .setRedirectStrategy(null)
                .setRetryHandler(new RetryHandler())
                .setDefaultRequestConfig(DEFAULT_REQUEST_CONFIG)
                .setDefaultAuthSchemeRegistry(DEFAULT_AUTH_SCHEME_REGISTRY)
                //.setSSLHostnameVerifier(hostnameVerifier)
                ;
        
    }

    @objid ("b0822db6-c4e3-424f-b800-f5dc3761177a")
    private static Registry<AuthSchemeProvider> initAuthSchemeRegistry() {
        return RegistryBuilder.<AuthSchemeProvider>create()
                .register(AuthSchemes.BASIC, new BasicSchemeFactory())
                .register(AuthSchemes.DIGEST, new DigestSchemeFactory())
                .register(AuthSchemes.NTLM, new NTLMSchemeFactory())
                .register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory())
                .register(AuthSchemes.KERBEROS, new KerberosSchemeFactory())
                .register(BearerAuthScheme.SCHEME_NAME, BearerAuthScheme.factory())
                .build();
        
    }

    /**
     * Get the default Apache HTTP client to be used inside Modelio.
     * @return a ready to use {@link CloseableHttpClient}.
     */
    @objid ("65d4178b-287b-4e04-a887-477100021c0b")
    public static HttpClient getDefaultClient() {
        return ApacheHttpClients.defaultClient;
    }

    @objid ("526c188d-d764-4c43-bef9-8b09b0b3c1eb")
    private static HttpClient initDefaultHttpClient() {
        return createClientBuilder().build();
    }

    /**
     * Creates an {@link HttpClientContext} for the given URI and authentication data
     * @param uri an URI to access
     * @param auth authentication for the URI. <i>null</i> if not authentication required.
     * @param configBuilder an optional RequestConfig builder to setup for proxy settings.
     * @return a configured {@link HttpClientContext}
     * @throws UriAuthenticationException if the authentication data is not handled.
     */
    @objid ("d8a251bb-eb0a-410d-8d25-351b0f8e0730")
    public static HttpClientContext createHttpContext(URI uri, IAuthData auth, Builder configBuilder) throws UriAuthenticationException {
        HttpClientContext context = HttpClientContext.create();
        
        CredentialsProvider credsProvider = new SystemDefaultCredentialsProvider();
        context.setCredentialsProvider(credsProvider);
        
        if (auth != null) {
            switch (auth.getSchemeId()) {
            case UserPasswordAuthData.USERPASS_SCHEME_ID: {
                UserPasswordAuthData authData = (UserPasswordAuthData) auth;
        
                if (authData.getUser() == null) {
                    throw new UriAuthenticationException(uri.toString(), "User name may not be null.");
                }
        
                Credentials credentials = new UsernamePasswordCredentials(authData.getUser(), authData.getPassword());
                AuthScope authscope = new AuthScope(uri.getHost(), AuthScope.ANY_PORT);
                credsProvider.setCredentials(authscope, credentials);
            }
            break;
            case OidcAuthData.SCHEME_ID: {
                OidcAuthData authData = (OidcAuthData) auth;
        
                AuthScope authscope = new AuthScope(uri.getHost(), AuthScope.ANY_PORT, AuthScope.ANY_REALM, BearerAuthScheme.SCHEME_NAME);
                credsProvider.setCredentials(authscope, new OidcCredentials(authData));
        
                if (configBuilder != null) {
                    configBuilder.setTargetPreferredAuthSchemes(Arrays.asList(BearerAuthScheme.SCHEME_NAME, AuthSchemes.DIGEST, AuthSchemes.BASIC));
                }
            }
            break;
            case NoneAuthData.AUTH_NONE_SCHEME_ID:
                break;
            default:
                throw new UriAuthenticationException(uri.toString(), auth + " not supported .");
            }
        }
        
        /** support different proxy */
        configProxy(credsProvider, auth, configBuilder);
        return context;
    }

    /**
     * Proxy configuration for a connection.
     * <p>
     * Configure the proxy if specified in the connection and set proxy authentication data from user settings and Eclipse preferences stored in System properties..
     * @param credsProvider the credential provider to fill.
     * @param auth the authentication data for custom proxy settings
     * @param configBuilder an optional RequestConfig.Builder for proxy setup.
     */
    @objid ("e106d3cf-c3a2-4cea-9fcf-f405317e705f")
    private static void configProxy(CredentialsProvider credsProvider, IAuthData auth, Builder configBuilder) {
        // currently not used : allow auth data to specify proxy
        if (auth != null && configBuilder != null) {
            Map<String, String> data = auth.getData();
            if (data != null) {
                if (data.containsKey("http.proxyHost")) {
                    String host = data.get("http.proxyHost");
                    int port = Integer.parseInt(data.getOrDefault("http.proxyPort", "-1"));
        
                    HttpHost proxy = new HttpHost(host, port);
                    configBuilder.setProxy(proxy);
        
                    final Properties props = new Properties();
                    props.putAll(data);
        
                    ApacheHttpClients.configProxyCredentials(props, "http", credsProvider);
                }
            }
        }
        
        // Setup proxy authentication from system properties set by Eclipse
        // see : org.eclipse.core.internal.net.ProxyType
        configProxyCredentials(System.getProperties(), "http", credsProvider);
        configProxyCredentials(System.getProperties(), "https", credsProvider);
        
    }

static {
                    // Initialize all statics in the right order
                    DEFAULT_SCHEME_PRIORITY = Collections.unmodifiableList(Arrays.asList(
                            BearerAuthScheme.SCHEME_NAME,
                            AuthSchemes.SPNEGO,
                            AuthSchemes.KERBEROS,
                            AuthSchemes.NTLM,
                            AuthSchemes.CREDSSP,
                            AuthSchemes.DIGEST,
                            AuthSchemes.BASIC));
    
                    DEFAULT_AUTH_SCHEME_REGISTRY = initAuthSchemeRegistry();
    
                    DEFAULT_REQUEST_CONFIG = RequestConfig
                            .copy(RequestConfig.DEFAULT)
                            .setTargetPreferredAuthSchemes(DEFAULT_SCHEME_PRIORITY).build();
    
                    // Initialize default client at last because it depends on above initializations.
                    defaultClient = initDefaultHttpClient();
                }
    
    /**
     * Use our own implementation of Apache {@link HostnameVerifier} that delegates to {@link DefaultHostnameVerifier} and intercepts failures.
     * <p>
     * Exceptions thrown by the delegate are augmented by adding a suppressed {@link InvalidCertificateException} that will be found by {@link SslManager#fixUntrustedServer(SSLException, URI)}.
     * @deprecated This class is a security hole, the user must not be able to bypass the verification.
     */
    @objid ("e5a628f4-826f-4727-b3ed-838b86b17879")
    @Deprecated
    private static class HostNameVerifier implements HostnameVerifier {
        @objid ("a2c8298d-7262-4f84-9e45-bbd4afa142d6")
        private final DefaultHostnameVerifier delegate = new DefaultHostnameVerifier();

        @objid ("63e0be86-be74-4bb8-8ab5-bd27ec18d665")
        @Override
        public boolean verify(String hostname, SSLSession session) {
            // Code copied from AbstractVerifier.
            try {
                final Certificate[] certs = session.getPeerCertificates();
                final X509Certificate x509 = (X509Certificate) certs[0];
                this.delegate.verify(hostname, x509);
                return true;
            } catch (final SSLException e) {
                try {
                    handleSslFailure(hostname, e, session);
                    return true;
                } catch (IOException e2) {
                    return false;
                }
            }
            
        }

        /**
         * Asks {@link SslManager#getTrustManager()} to check the certificate is manually trusted by the user. In this case return normally.
         * <p>
         * I the other case augment the passed exception by adding a suppressed {@link InvalidCertificateException} that will be found by {@link SslManager#fixUntrustedServer(SSLException, URI)}.
         * @param host the host name
         * @param ex the exception to handle
         * @param session the SSL session
         * @throws SSLPeerUnverifiedException if the SSL session is not in valid state, should not occur.
         * @throws SSLException the augmented <i>exception</i>.
         */
        @objid ("1828252d-3495-46a7-b8c9-c13791fe3e05")
        private void handleSslFailure(String host, SSLException ex, SSLSession session) throws SSLPeerUnverifiedException, SSLException {
            X509Certificate[] chain = (X509Certificate[]) session.getPeerCertificates();
            
            try {
                // If the server certificate is in the trusted list this call will return normally.
                // In all other cases it should throw an exception
                SslManager.getInstance().getTrustManager().checkServerTrusted(chain, host);
            
                Log.trace("Ignoring SSL exception because user trusts '" + host + "':");
                Log.trace(ex);
                return;
            } catch (CertificateException ex2) {
                // Add InvalidCertificateException with the invalid certificate chain
                // so that it can be found by SslManager.fixUntrustedServer(...)
                InvalidCertificateException ex3 = new InvalidCertificateException(chain, ex2);
                ex.addSuppressed(ex3);
                Log.trace(ex);
                throw ex;
            }
            
        }

    }

    /**
     * {@link DefaultHttpRequestRetryHandler} extension that handles {@link SSLException} to allow {@link SslManager#fixUntrustedServer(SSLException, URI)} to fix the error.
     */
    @objid ("9453ebe9-8193-4264-b09f-dfb4ab856a7c")
    public static class RetryHandler extends DefaultHttpRequestRetryHandler {
        @objid ("fded4899-30d6-4cc6-95ec-3a4a3b7773c4")
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (exception instanceof SSLException) {
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpHost currentHost = clientContext.getTargetHost();
                URI anUri = URI.create(currentHost.toURI());
            
                return SslManager.getInstance().fixUntrustedServer((SSLException) exception, anUri);
            }
            return super.retryRequest(exception, executionCount, context);
        }

    }

    /**
     * "Bearer" authentication scheme for OIDC/OAuth authentication.
     * <p>
     * Expects the access token being stored in the {@link Credentials#getPassword()}.
     * @author cmarin
     * @since 5.2
     */
    @objid ("2ac5eead-7eac-4800-90cf-8837c59baa24")
    public static class BearerAuthScheme extends RFC2617Scheme {
        @objid ("56dfafae-a4a9-496a-a827-071b56b16c57")
        private static final long serialVersionUID = -1931571557597830536L;

        /**
         * Whether the basic authentication process is complete
         */
        @objid ("f291c173-0961-4d25-8f05-0f788c158066")
        private boolean complete;

        /**
         * The scheme identifier
         */
        @objid ("33eb2f7d-cbac-4674-9c33-f57395275124")
        public static String SCHEME_NAME = "Bearer";

        @objid ("31a91c79-86e7-4c7d-a5b9-3848b3e992fd")
        public  BearerAuthScheme() {
            super(Consts.ASCII);
        }

        /**
         * Returns textual designation of the basic authentication scheme.
         * @return {@code basic}
         */
        @objid ("dd936bb3-07be-4ef2-838f-46d585693552")
        @Override
        public String getSchemeName() {
            return SCHEME_NAME;
        }

        /**
         * Processes the Basic challenge.
         * @param header the challenge header
         * @throws MalformedChallengeException is thrown if the authentication challenge
         * is malformed
         */
        @objid ("5ce50f2f-7209-4b2b-99c1-83f93e9b9956")
        @Override
        public void processChallenge(final Header header) throws MalformedChallengeException {
            super.processChallenge(header);
            this.complete = true;
            
        }

        /**
         * Tests if the Basic authentication process has been completed.
         * @return {@code true} if Basic authorization has been processed,
         * {@code false} otherwise.
         */
        @objid ("cc0a68fd-40a2-4eb6-8be6-e7983ebf107b")
        @Override
        public boolean isComplete() {
            return this.complete;
        }

        /**
         * Returns {@code false}. Basic authentication scheme is request based.
         * @return {@code false}.
         */
        @objid ("e57820e1-e249-4360-b60a-826fec8f81ef")
        @Override
        public boolean isConnectionBased() {
            return false;
        }

        /**
         * @deprecated (4.2) Use {@link org.apache.http.auth.ContextAwareAuthScheme#authenticate(
         * Credentials, HttpRequest, org.apache.http.protocol.HttpContext)}
         */
        @objid ("a26fd6e9-2347-4073-861a-d23f88a4baf4")
        @Override
        @Deprecated
        public Header authenticate(final Credentials credentials, final HttpRequest request) throws AuthenticationException {
            return authenticate(credentials, request, new BasicHttpContext());
        }

        /**
         * Produces basic authorization header for the given set of {@link Credentials}.
         * @param credentials The set of credentials to be used for authentication
         * @param request The request being authenticated
         * @return a basic authorization string
         * @throws InvalidCredentialsException if authentication
         * credentials are not valid or not applicable for this authentication scheme
         * @throws AuthenticationException if authorization string cannot
         * be generated due to an authentication failure
         */
        @objid ("a4b721ca-c5c1-49ba-98a9-683199cf07c8")
        @Override
        public Header authenticate(final Credentials credentials, final HttpRequest request, final HttpContext context) throws InvalidCredentialsException, AuthenticationException {
            Args.notNull(credentials, "Credentials");
            Args.notNull(request, "HTTP request");
            
            String accessToken;
            try {
                accessToken = credentials.getPassword();
            } catch (UncheckedIOException e) {
                throw new AuthenticationException(FileUtils.getLocalizedMessage(e.getCause()), e);
            }
            if (accessToken == null)
                throw new InvalidCredentialsException("credentials.getPassword() returned null");
            
            final CharArrayBuffer buffer = new CharArrayBuffer(64);
            if (isProxy()) {
                buffer.append(AUTH.PROXY_AUTH_RESP);
            } else {
                buffer.append(AUTH.WWW_AUTH_RESP);
            }
            buffer.append(": Bearer ");
            buffer.append(accessToken);
            return new BufferedHeader(buffer);
        }

        @objid ("64bf14f1-d602-4810-bede-f458fa295018")
        @Override
        public String toString() {
            return new StringBuilder()
                    .append(BearerAuthScheme.SCHEME_NAME)
                    .append(" [complete=")
                    .append(this.complete)
                    .append("]")
                    .toString();
            
        }

        /**
         * @return a {@link AuthSchemeProvider} that creates {@link BearerAuthScheme}.
         */
        @objid ("a25545a6-44f8-43e5-ac45-32f29b31ae95")
        public static AuthSchemeProvider factory() {
            return c-> new BearerAuthScheme();
        }

    }

    /**
     * Adapter from {@link OidcAuthData} to Apache HTTP {@link Credentials}.
     * @author cmarin
     * @since 5.2
     */
    @objid ("1d4a4b9f-bd0c-478d-b120-6541db315536")
    static class OidcCredentials implements Credentials {
        @objid ("72206676-33c7-4a70-b27b-2d33edad7033")
        private final OidcAuthData authData;

        @objid ("e7141898-cb78-4b38-b8e7-f90ddc8d208a")
        public  OidcCredentials(OidcAuthData authData) {
            this.authData = authData;
        }

        @objid ("2b501a1f-33e7-4926-b5f0-096a1aba426f")
        @Override
        public Principal getUserPrincipal() {
            return new BasicUserPrincipal(this.authData.getUserId());
        }

        @objid ("f6f36c15-e826-4b40-a7e6-63a9618c730f")
        @Override
        public String getPassword() throws UncheckedIOException {
            try {
                return this.authData.getToken();
            } catch (IOException e) {
                throw new UncheckedIOException(FileUtils.getLocalizedMessage(e), e);
            }
            
        }

    }

}
