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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileSystemException;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.util.EntityUtils;
import org.modelio.vbasic.auth.IAuthData;

/**
 * HTTP/HTTPS URI support using Apache HTTP client library.
 */
@objid ("bae410b8-11a7-4719-b527-e7909fe8cb16")
public class ApacheUriConnection extends UriConnection {
    @objid ("6a0187ad-4705-4542-b9a9-62c4905b67aa")
    private boolean dooutput;

    @objid ("a5281e74-0c89-492a-87b7-83a0eb22c299")
    private CloseableHttpResponse res;

    @objid ("d7bd2f5b-6589-4747-8ec6-9cc506dcaca6")
    private int timeout;

    @objid ("6f583fea-55a5-4edd-902d-e43c9aef349f")
    private final Builder configBuilder;

    @objid ("49680186-feda-42ea-b9b9-8327b6a63f8e")
    private HttpClientContext context;

    @objid ("496ff6f7-6030-4369-afc7-c21547ff2f2a")
    private HttpRequestBase req;

    @objid ("dd1f5d11-eb65-4998-8263-9e56adc8ef00")
    private IAuthData auth;

    @objid ("a1511e70-2685-47e5-88d5-2d8064d43fc3")
    private final URI uri;

    /**
     * @param uri the URI to open
     */
    @objid ("bcf11edd-2a78-43ac-bf86-c0b0d059c536")
    public ApacheUriConnection(URI uri) {
        this.uri = uri;
        this.configBuilder = RequestConfig.custom();
    }

    @objid ("366975a5-be39-484e-a69f-c37ffc4a162e")
    @Override
    public int getConnectTimeout() {
        return this.timeout;
    }

    @objid ("23a62344-2666-4e45-bd5f-0ca4d03ccf5a")
    @Override
    public String getContentType() throws IOException {
        Header h = getResponse().getFirstHeader(HttpHeaders.CONTENT_TYPE);
        return h == null ? null : h.getValue();
    }

    @objid ("569f276f-58a6-480b-bc05-ceb79fed4262")
    @Override
    public InputStream getInputStream() throws IOException {
        return getResponse().getEntity().getContent();
    }

    /**
     * Same as {@link java.net.URLConnection#getOutputStream()}.
     * <p>
     * This implementation creates a {@link PipedOutputStream} to the Apache entity input stream.
     * It is strongly advised to <b>write to the returned stream in another thread</b>.
     * @see PipedOutputStream
     * @see PipedInputStream
     * 
     * @return an output stream that writes to this connection.
     * @throws java.io.IOException if an I/O error occurs while creating the output stream.
     */
    @objid ("79282b13-7e13-42d5-9917-892c78a155bd")
    @Override
    public OutputStream getOutputStream() throws IOException {
        if (! this.dooutput) {
            throw new IllegalStateException("This is not an output connection");
        }
        
        if (this.req != null && ! (this.req instanceof HttpPut)) {
            throw new IllegalStateException("This is not an output connection");
        }
        
        PipedOutputStream outPipe = new PipedOutputStream();
        PipedInputStream snk = new PipedInputStream(outPipe);
        outPipe.connect(snk);
        
        BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(snk);
        
        HttpPut pr = (HttpPut) getRequest();
        pr.setEntity(entity);
        return outPipe;
    }

    @objid ("1726b583-3997-4662-b8e0-fc673b2c07dd")
    @Override
    public String getStamp() {
        try {
            Header firstHeader = getResponse().getFirstHeader(HttpHeaders.LAST_MODIFIED);
            if (firstHeader == null) {
                return "";
            } else {
                return firstHeader.getValue();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @objid ("d397b034-969b-4d1e-b1ae-d91981eb1887")
    @Override
    public void setAuthenticationData(IAuthData auth) {
        this.auth = auth;
    }

    @objid ("26cc7c2b-7b49-4b3d-b018-0a11f678bc67")
    @Override
    public void setConnectTimeout(int timeout) throws IllegalArgumentException {
        this.configBuilder.setConnectTimeout(timeout);
        this.timeout = timeout;
    }

    @objid ("b96addaa-11c6-4df7-b0f2-4882209696e4")
    @Override
    public void setDoInput(boolean doinput) {
        // ignore
    }

    @objid ("b8217cfb-3879-4a9a-9962-ac6c5a15da88")
    @Override
    public void setDoOutput(boolean dooutput) {
        this.dooutput = dooutput;
    }

    @objid ("735661c6-69c9-4f06-9ef7-0b83001da5a0")
    @Override
    public void setIfNotStamp(String stamp) {
        getRequest().addHeader("If-Modified-Since", stamp);
    }

    @objid ("e3bc0c3f-d049-43a7-afe9-f4d93825d1d0")
    @Override
    public void setRequestProperty(String key, String value) {
        getRequest().addHeader(key, value);
    }

    @objid ("cf3de94a-81a4-4832-921f-7ba243cceeca")
    private HttpRequestBase getRequest() {
        if (this.req == null) {
            if (this.dooutput) {
                this.req = new HttpPut(this.uri);
            } else {
                this.req = new HttpGet(this.uri);
            }
        }
        return this.req;
    }

    @objid ("6d2eabdf-d059-465d-8143-f7d9c6c1eb30")
    private HttpResponse getResponse() throws IOException {
        if (this.res == null) {
            openConnection();
        }
        return this.res;
    }

    /**
     * Builds and throws a {@link FileSystemException} from {@link #res}.
     * <p>
     * Adds as cause another exception whose message is the entity content.
     * This may be the HTML message sent by the server.
     * 
     * @throws java.nio.file.FileSystemException the built exception
     */
    @objid ("4e25ec1d-3711-45cc-b742-0c77edf5e414")
    private void handleConnectionFailure() throws FileSystemException {
        StatusLine statusLine = this.res.getStatusLine();
        String reason = statusLine.getReasonPhrase();
        
        Exception base = null;
        try {
            // Look for a message in the response body
            String s = EntityUtils.toString(this.res.getEntity());
            if (s != null) {
                base = new HttpResponseException(statusLine.getStatusCode(), s);
            }
        } catch (IOException e) {
            base = e;
        }
        
        FileSystemException error;
        
        int statusCode = statusLine.getStatusCode();
        if (statusCode == HttpStatus.SC_FORBIDDEN) {
            error = new AccessDeniedException(this.uri.toString(), null, reason);
        } else if (statusCode == HttpStatus.SC_UNAUTHORIZED || statusCode == HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED) {
            error = new UriAuthenticationException(this.uri.toString(), reason);
        } else if (statusCode == HttpStatus.SC_NOT_FOUND) {
            error = new NoSuchFileException(this.uri.toString(),  null, reason);
        } else {
            error = new FileSystemException(this.uri.toString(),  null, reason);
        }
        
        if (base != null) {
            error.initCause(base);
        }
        
        throw error;
    }

    @objid ("f8e1a3e4-45b3-4065-8838-90de7fe64eaa")
    private void openConnection() throws IOException, IllegalStateException {
        this.context = ApacheHttpClients.createHttpContext(this.uri, this.auth, this.configBuilder);
        
        HttpRequestBase request = getRequest();
        request.setConfig(this.configBuilder.build()); 
        
        this.res = ApacheHttpClients.getDefaultClient().execute(request, this.context);
        int statusCode = this.res.getStatusLine().getStatusCode();
        
        if (statusCode >=200 && statusCode < 300) { 
            // Try to get content now to get an exception on failure immediately
            this.res.getEntity().getContent();
        } else {
            handleConnectionFailure();
        }
    }

    /**
     * URI connection factory.
     */
    @objid ("160e1779-5aa1-46ab-97b1-b9c4e88d3ffb")
    public static class ApacheUriConnectionFactory implements IUriConnectionFactory {
        @objid ("cac25ba2-73d0-4b92-8d68-b3b12ffb8711")
        @Override
        public boolean supports(URI uri) {
            return uri.getScheme().equals("http") || uri.getScheme().equals("https");
        }

        @objid ("25d00bea-798a-4046-99c3-5adaf15e1760")
        @Override
        public UriConnection createConnection(URI uri) throws IOException {
            return new ApacheUriConnection(uri);
        }

    }

}
