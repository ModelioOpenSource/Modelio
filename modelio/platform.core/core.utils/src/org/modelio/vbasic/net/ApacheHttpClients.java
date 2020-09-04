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
import java.net.URI;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.apache.http.protocol.HttpContext;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.ServerUserPassAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
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
    @objid ("57105053-0815-4c28-8265-9232b3e53633")
    private static final CloseableHttpClient defaultClient = initDefaultHttpClient();

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
     * 
     * @param props configuration source
     * @param protocol "http" or "https"
     * @param credsProvider the credential provider to fill
     * 
     * 
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
     * 
     * @return a HttpClientBuilder
     */
    @objid ("bc1f0a0d-8efc-424f-ae30-745413b75e5e")
    public static HttpClientBuilder createClientBuilder() {
        HostnameVerifier hostnameVerifier = new HostNameVerifier();
        return HttpClientBuilder.create()
                .useSystemProperties()
                .setSSLContext(SslManager.getInstance().getSslContext())
                .setRedirectStrategy(null)
                .setRetryHandler(new RetryHandler())
                .setSSLHostnameVerifier(hostnameVerifier);
    }

    /**
     * Get the default Apache HTTP client to be used inside Modelio.
     * 
     * @return a ready to use {@link CloseableHttpClient}.
     */
    @objid ("65d4178b-287b-4e04-a887-477100021c0b")
    public static CloseableHttpClient getDefaultClient() {
        return ApacheHttpClients.defaultClient;
    }

    @objid ("526c188d-d764-4c43-bef9-8b09b0b3c1eb")
    private static CloseableHttpClient initDefaultHttpClient() {
        return createClientBuilder().build();
    }

    /**
     * Creates an {@link HttpClientContext} for the given URI and authentication data
     * 
     * @param uri an URI to access
     * @param auth authentication for the URI. <i>null</i> if not authentication required.
     * @param configBuilder an optional RequestConfig builder to setup for proxy settings.
     * @return a configured {@link HttpClientContext}
     * @throws org.modelio.vbasic.net.UriAuthenticationException if the authentication data is not handled.
     */
    @objid ("d8a251bb-eb0a-410d-8d25-351b0f8e0730")
    public static HttpClientContext createHttpContext(URI uri, IAuthData auth, Builder configBuilder) throws UriAuthenticationException {
        HttpClientContext context = HttpClientContext.create();
        
        CredentialsProvider credsProvider = new SystemDefaultCredentialsProvider();
        context.setCredentialsProvider(credsProvider);
        
        if (auth != null) {
            switch (auth.getSchemeId()) {
            case UserPasswordAuthData.USERPASS_SCHEME_ID:
            case ServerUserPassAuthData.SERVERUSERPASS_SCHEME_ID:
                UserPasswordAuthData authData = (UserPasswordAuthData) auth;
        
                if (authData.getUser() == null) {
                    throw new UriAuthenticationException(uri.toString(), "User name may not be null.");
                }
        
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                        authData.getUser(), authData.getPassword());
                AuthScope authscope = new AuthScope(uri.getHost(), AuthScope.ANY_PORT);
                credsProvider.setCredentials(authscope, credentials);
        
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
     * 
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
                    int port = Integer.parseInt(data.get("http.proxyPort"));
        
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

    /**
     * Use our own implementation of Apache {@link X509HostnameVerifier} that delegates to {@link BrowserCompatHostnameVerifier} and intercepts failures.
     * <p>
     * Exceptions thrown by the delegate are augmented by adding a suppressed {@link InvalidCertificateException} that will be found by {@link SslManager#fixUntrustedServer(SSLException, URI)}.
     */
    @objid ("e5a628f4-826f-4727-b3ed-838b86b17879")
    public static class HostNameVerifier implements HostnameVerifier {
        @objid ("5a220cdc-5a1c-453f-ad74-e5fa14cd8a84")
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
         * 
         * @param host the host name
         * @param ex the exception to handle
         * @param session the SSL session
         * @throws javax.net.ssl.SSLPeerUnverifiedException if the SSL session is not in valid state, should not occur.
         * @throws javax.net.ssl.SSLException the augmented <i>exception</i>.
         */
        @objid ("1828252d-3495-46a7-b8c9-c13791fe3e05")
        private void handleSslFailure(String host, SSLException ex, SSLSession session) throws SSLException, SSLPeerUnverifiedException {
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

        @objid ("774f3770-2d3b-4899-8bbd-cad288d11dc7")
        public HostNameVerifier() {
            super();
        }

    }

    /**
     * {@link DefaultHttpRequestRetryHandler} extension that handles {@link SSLException} to allow {@link SslManager#fixUntrustedServer(SSLException, URI)} to fix the error.
     */
    @objid ("9453ebe9-8193-4264-b09f-dfb4ab856a7c")
    public static class RetryHandler extends DefaultHttpRequestRetryHandler {
        @objid ("00f3dd92-08d0-4053-829a-1c52d7ec5267")
        public RetryHandler() {
            super();
        }

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

}
