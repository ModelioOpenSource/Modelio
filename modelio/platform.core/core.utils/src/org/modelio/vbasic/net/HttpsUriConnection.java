/* 
 * Copyright 2013-2019 Modeliosoft
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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

/**
 * Handles https URIs.
 * 
 * @deprecated Does not handle HTTP DIGEST authentication, use {@link ApacheUriConnection} instead.
 */
@objid ("3fef9ba4-224e-4fb5-8a1f-8736fc344df4")
@Deprecated
class HttpsUriConnection extends UriConnection {
    @objid ("e45a932f-5604-44ff-8216-f5aa591febf0")
     HttpsURLConnection conn;

    @objid ("51dd03ad-e89a-4892-b618-31f3c4147bee")
    private URI uri;

    /**
     * Creates an https URI connection.
     * <p>
     * The URI must be a https URI.
     * 
     * @param uri the URI to connect to.
     * @throws java.io.IOException in case of failure.
     */
    @objid ("a6bd91ec-b360-4308-aac7-1b07f6157923")
    public HttpsUriConnection(URI uri) throws IOException {
        this.uri = uri;
        initConnection();
    }

    @objid ("880bdc5b-f0cb-45c2-975b-669682bcbbc7")
    @Override
    public InputStream getInputStream() throws IOException {
        try {
            return this.conn.getInputStream();
        } catch(SSLHandshakeException e){
            if (SslManager.getInstance().fixUntrustedServer(e, this.uri )) {
                initConnection();
                return this.conn.getInputStream();
            } else {
                throw e;
            }
        }
    }

    @objid ("9885623f-0fbe-42cb-9ea7-414d4d5a7852")
    @Override
    public OutputStream getOutputStream() throws IOException {
        try {
            return this.conn.getOutputStream();
        } catch(SSLHandshakeException e){
            if (SslManager.getInstance().fixUntrustedServer(e, this.uri )) {
                return this.conn.getOutputStream();
            } else {
                throw e;
            }
        }
    }

    @objid ("07b3ebb8-1637-440c-b081-f00136c7ac64")
    @Override
    public void setRequestProperty(String key, String value) {
        this.conn.setRequestProperty(key, value);
    }

    @objid ("6c85bd3a-b417-4c05-a3dc-0ccf010d8535")
    @Override
    public void setDoOutput(boolean dooutput) {
        this.conn.setDoOutput(dooutput);
    }

    @objid ("6e7d3d3c-53a9-4e37-b475-1d6ccbc20673")
    @Override
    public void setDoInput(boolean doinput) {
        this.conn.setDoInput(doinput);
    }

    @objid ("2b46ac64-f344-42d6-bf50-a53d84777813")
    @Override
    public void setConnectTimeout(int timeout) throws IllegalArgumentException {
        this.conn.setConnectTimeout(timeout);
    }

    @objid ("b3bd4d7a-b631-4c6f-a1df-8d1225220e4c")
    @Override
    public int getConnectTimeout() {
        return this.conn.getConnectTimeout();
    }

    @objid ("897519a6-71b1-4312-aa94-a2c38116235e")
    @Override
    public void setIfNotStamp(String stamp) {
        this.conn.setIfModifiedSince(Long.decode(stamp));
    }

    @objid ("0d0f384f-ffdf-493d-b4e9-4c7a3e597a31")
    @Override
    public String getStamp() {
        return Long.toString(this.conn.getIfModifiedSince());
    }

    @objid ("2f363d60-b8b6-4a46-bad0-db22cd29d25c")
    @Override
    public void setAuthenticationData(IAuthData auth) {
        if (auth != null) {
            switch (auth.getSchemeId()) {
            case UserPasswordAuthData.USERPASS_SCHEME_ID:
                UserPasswordAuthData authData = (UserPasswordAuthData) auth;
                String httpAuth = computeHttpAuth(null, authData.getUser(), authData.getPassword());
                this.conn.setRequestProperty("Authorization", httpAuth);
                break;
            case NoneAuthData.AUTH_NONE_SCHEME_ID:
                break;
                
            default:
                throw new UnsupportedOperationException(auth+ " not supported for "+this.conn);
            }
        }
    }

    /**
     * Compute HTTP authentication request property.
     * <p>
     * Look for user and password in the 'user' and 'pass' parameters.
     * If they are not filled, look at the URL itself.
     * 
     * @param url the URL to open
     * @param user the user login, may be null
     * @param pass the password, may be null
     * @return the HTTP authentication request property.
     */
    @objid ("1d617637-6331-41b4-83b6-1ba21520ec20")
    private static String computeHttpAuth(URL url, String user, String pass) {
        if (user != null && !user.isEmpty()) {
            String userInfo = user + ":" + (pass==null ? "" : pass);
            return "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userInfo.getBytes());
        } else if (url != null && url.getUserInfo() != null) {
            return "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(url.getUserInfo().getBytes());
        } else {
            return null;
        }
    }

    @objid ("1a8124d8-79e8-4ecc-994b-5b8463575489")
    private void initConnection() throws IOException, MalformedURLException {
        URL url = this.uri.toURL();
        this.conn = (HttpsURLConnection) url.openConnection();
        this.conn.setSSLSocketFactory(SslManager.getInstance().getSslContext().getSocketFactory());
    }

    @objid ("7d53db93-d99c-4240-91a9-8879db7a91a8")
    @Override
    public String getContentType() throws IOException {
        try {
            this.conn.connect();
            return this.conn.getContentType();
        } catch(SSLHandshakeException e){
            if (SslManager.getInstance().fixUntrustedServer(e, this.uri )) {
                this.conn.connect();
                return this.conn.getContentType();
            } else {
                throw e;
            }
        }
    }

    /**
     * The URI connection factory.
     */
    @objid ("452f6f3b-e1d0-4fda-bac8-37f1976a2134")
    static class Factory implements IUriConnectionFactory {
        @objid ("eb53304d-495c-4e95-993d-3f1134df5ba2")
        @Override
        public boolean supports(URI uri) {
            return "https".equals(uri.getScheme());
        }

        @objid ("778a3be5-9c7a-4ead-a6b0-09ba2b9c1f4a")
        @Override
        public UriConnection createConnection(URI uri) throws IOException {
            return new HttpsUriConnection(uri);
        }

    }

}
