/* 
 * Copyright 2013-2018 Modeliosoft
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

/**
 * URI connection for URI that are supported as URL.
 */
@objid ("b6803852-5ff4-4aef-9a52-70adee84a230")
class UrlUriConnection extends UriConnection {
    @objid ("547f5cec-055e-44e2-bf37-a3f32abda87f")
    private boolean connected = false;

    @objid ("9959c067-7043-4c20-8245-564c327272d6")
    private final URLConnection c;

    @objid ("90557c3c-c75f-4486-95e6-a03982b0a6c8")
    public UrlUriConnection(URI uri) throws IOException {
        try {
            URL url = uri.toURL();
            this.c = url.openConnection();
            
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @objid ("34a316fe-4009-43c7-95e7-10d2341714fb")
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream ret = this.c.getInputStream();
        this.connected = true;
        return ret;
    }

    @objid ("0c26e079-cc82-4710-abcf-00569bafa572")
    @Override
    public OutputStream getOutputStream() throws IOException {
        OutputStream ret = this.c.getOutputStream();
        this.connected = true;
        return ret;
    }

    @objid ("b4d330a1-7a57-4d8e-98dd-5ddb2db627eb")
    @Override
    public void setRequestProperty(String key, String value) {
        this.c.setRequestProperty(key, value);
    }

    @objid ("080bdd0b-c9b8-4bc0-a5d4-69e7c4197c1b")
    @Override
    public void setIfNotStamp(String stamp) {
        this.c.setIfModifiedSince(Long.valueOf(stamp));
    }

    @objid ("b1e9af7a-88e0-4966-9ed0-f8cdd5bde7bc")
    @Override
    public String getStamp() {
        return Long.toString(this.c.getLastModified());
    }

    @objid ("2c9484fb-a41d-4b5f-a469-1c9210d1ff6c")
    @Override
    public void setDoInput(boolean doinput) {
        this.c.setDoInput(doinput);
    }

    @objid ("9f4c71ea-10fb-4c80-ac9c-04a1ee5c818a")
    @Override
    public void setDoOutput(boolean dooutput) {
        this.c.setDoOutput(dooutput);
    }

    @objid ("7a8cb8d6-3d45-4086-be79-1c1ad1b9b61a")
    @Override
    public void setAuthenticationData(IAuthData auth) {
        if (auth != null) {
            switch (auth.getSchemeId()) {
            case UserPasswordAuthData.USERPASS_SCHEME_ID:
                if (this.c instanceof HttpURLConnection ) {
                    UserPasswordAuthData authData = (UserPasswordAuthData) auth;
                    String httpAuth = computeHttpAuth(null, authData.getUser(), authData.getPassword());
                    this.c.setRequestProperty("Authorization", httpAuth);
                }
                break;
                
            case NoneAuthData.AUTH_NONE_SCHEME_ID:
                break;
                
            default:
                throw new UnsupportedOperationException(auth+ " not supported for "+this.c);
            }
        }
    }

    /**
     * Compute HTTP authentication request property.
     * <p>
     * Look for user and password in the 'user' and 'pass' parameters.
     * If they are not filled, look at the URL itself.
     * @param url the URL to open
     * @param user the user login, may be null
     * @param pass the password, may be null
     * @return the HTTP authentication request property.
     */
    @objid ("d1b94604-54d2-4fa5-950a-0cf181a63693")
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

    @objid ("1c51622b-e649-4b6e-a62d-65efe6601879")
    @Override
    public void setConnectTimeout(int timeout) throws IllegalArgumentException {
        this.c.setConnectTimeout(timeout);
    }

    @objid ("a57fa360-9337-477f-a91a-b4f77e31634e")
    @Override
    public int getConnectTimeout() {
        return this.c.getConnectTimeout();
    }

    @objid ("081015bc-6ae2-429f-a927-9cb1addace8b")
    @Override
    public String getContentType() throws IOException {
        this.c.connect();
        this.connected = true;
        return this.c.getContentType();
    }

}
