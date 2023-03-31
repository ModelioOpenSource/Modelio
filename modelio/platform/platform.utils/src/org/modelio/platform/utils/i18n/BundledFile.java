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
package org.modelio.platform.utils.i18n;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.modelio.vbasic.files.FileUtils;
import org.osgi.framework.Bundle;

/**
 * Helper around {@link FileLocator} to get a bundle file the easy way .
 * <p>
 * It has convenient methods to obtain a File, an URL or the string content.
 * <p>
 * Looks for the file in bundle fragments and the following variables may also be used as entries in the provided path:</p>
 * <ul>
 * <li>$nl$ - for language specific information</li>
 * <li>$os$ - for operating system specific information</li>
 * <li>$ws$ - for windowing system specific information</li>
 * </ul>
 * <h2>Usage</h2>
 * <ul>
 * <li>Create a static method in your Plugin singleton:
 * <pre><code>
 * public static BundledFile findFile(String relPath) {
 * return new BundledFile(MyPlugin.getBundle(), relPath);
 * }
 * </code></pre>
 * <li> Call it with :
 * <pre><code>
 * MyPlugin.getFile("/res/toto").toFileOrLinkageError()
 * </ul>
 * </code></pre>
 * 
 * @author cmarin
 */
@objid ("9667b5eb-7412-4b36-a7d0-aedb140bd06f")
public class BundledFile {
    @objid ("23949867-c155-4c3c-8892-60a24ce5a2b3")
    private final String relPath;

    @objid ("72f3416d-4179-4af1-b613-c4306493de17")
    private final Bundle bundle;

    @objid ("e51c0b76-7437-412b-8ebc-73b3db63ec09")
    private URL url;

    /**
     * @param bundle the bundle to look into
     * @param relPath the file path, relative to he bundle root.
     */
    @objid ("054f135b-e6d4-4afd-a6a7-1788d4977ed8")
    public  BundledFile(Bundle bundle, String relPath) {
        this.relPath = relPath;
        this.bundle = bundle;
        
    }

    @objid ("5b90b9eb-d8b2-4006-bddf-84ea976890c3")
    private URL locate() throws NoSuchFileException {
        if (this.url == null) {
            this.url = FileLocator.find(this.bundle, new Path(this.relPath), null);
            if (this.url == null) {
                throw new NoSuchFileException(this.relPath, this.bundle.getLocation(), String.format("path not found in the %s bundle", this.bundle.getSymbolicName()));
            }
        }
        return this.url;
    }

    /**
     * Converts a URL that uses a user-defined protocol into a File.
     * The contents of the URL may be extracted into a cache on the file-system in order to get a file.
     * 
     * If the protocol for the given URL is not recognized by this converter, an IOException is thrown.
     * @return a File
     * @throws IOException on failure
     */
    @objid ("bb57d93d-b09e-4adc-a9f5-bbb60a0cfe7a")
    public File toFile() throws IOException {
        URL fileUrl = FileLocator.toFileURL(locate());
        try {
            return new File(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new IOException(e.getMessage(), e);
        }
        
    }

    /**
     * return a wrapper around this instance with same final methods,
     * that throws an unchecked {@link LinkageError} instead of {@link IOException}.
     * <p>
     * To be used for resources that are not expected to be missing
     * @return an unchecked wrapper
     */
    @objid ("34a5a2f9-7f05-4686-8dbb-421f7ba630d1")
    public UncheckedBundledFile unchecked() {
        return new UncheckedBundledFile(this);
    }

    /**
     * Get the resource content as String.
     * @param charset the charset to use
     * @return the content
     * @throws IOException on failure
     */
    @objid ("a28e7788-1580-4c01-b55e-2f71de810603")
    public String getContent(Charset charset) throws IOException {
        try (InputStream is = toUrl().openStream();) {
            byte[] bytes = is.readAllBytes();
            return new String(bytes, charset);
        }
        
    }

    /**
     * Get an URL that uses a protocol which is native to the Java class library (file, jar, http, etc).
     * 
     * Note however that users of this API should not assume too much about the results of this method.
     * While it may consistently return a file: URL in certain installation configurations, others may result in jar: or http: URLs.
     * 
     * If the protocol is not recognized by this converter, then the original URL is returned as-is.
     * @return an URL directly usable
     * @throws IOException on error
     */
    @objid ("84583bfc-c61a-44a1-a3cb-68da781f248e")
    public URL toUrl() throws IOException {
        return FileLocator.resolve(locate());
    }

    /**
     * Wrapper around {@link BundledFile} with same final methods,
     * but throws an unchecked {@link LinkageError} instead of {@link IOException}.
     * <p>
     * To be used for resources that are not expected to be missing
     */
    @objid ("e5b75134-6fea-437c-85dc-385c8dd45b8d")
    public static class UncheckedBundledFile {
        @objid ("02660832-f7ba-41a8-a381-54128998cb15")
        private final BundledFile wrapped;

        @objid ("6e70d91e-7eb9-43ff-bb23-fa5273532bec")
        public  UncheckedBundledFile(BundledFile wrapped) {
            this.wrapped = wrapped;
        }

        /**
         * Converts a URL that uses a user-defined protocol into a File.
         * The contents of the URL may be extracted into a cache on the file-system in order to get a file.
         * 
         * If the protocol for the given URL is not recognized by this converter, an IOException is thrown.
         * @return a File
         * @throws LinkageError on failure
         */
        @objid ("43515c3a-b290-4108-86c7-e02c06510f25")
        public File toFile() throws LinkageError {
            try {
                return this.wrapped.toFile();
            } catch (IOException e) {
                throw new LinkageError(FileUtils.getLocalizedMessage(e), e);
            }
            
        }

        /**
         * Get an URL that uses a protocol which is native to the Java class library (file, jar, http, etc).
         * 
         * Note however that users of this API should not assume too much about the results of this method.
         * While it may consistently return a file: URL in certain installation configurations, others may result in jar: or http: URLs.
         * 
         * If the protocol is not recognized by this converter, then the original URL is returned as-is.
         * @return an URL directly usable
         * @throws LinkageError on failure
         */
        @objid ("e9dd170c-14c8-4a9b-8470-de6108749d14")
        public URL toUrl() throws LinkageError {
            try {
                return this.wrapped.toUrl();
            } catch (IOException e) {
                throw new LinkageError(FileUtils.getLocalizedMessage(e), e);
            }
            
        }

        /**
         * Get the resource content as String. Failure is not expected.
         * @param charset the charset to use
         * @return the content
         * @throws LinkageError on failure
         */
        @objid ("38aa9e72-90d3-4e76-9533-f052b814f2a6")
        public String getContent(Charset charset) throws LinkageError {
            try {
                return this.wrapped.getContent(charset);
            } catch (IOException e) {
                throw new LinkageError(FileUtils.getLocalizedMessage(e), e);
            }
            
        }

    }

}
