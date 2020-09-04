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

import java.net.URI;
import java.net.URISyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Utilities about {@link URI}.
 */
@objid ("1c5e1831-af69-4da0-8774-2dc9b9d0a781")
public class UriUtils {
    /**
     * Ensure the given URI ends with '/'.
     * 
     * @param uri an URI
     * @return the same URI with a trailing '/' if there wasn't one
     */
    @objid ("4e5ad5b7-e0df-45c5-87a4-b69047d87a6f")
    public static URI asDirectoryUri(URI uri) {
        if (!uri.toString().endsWith("/")) {
            String path = uri.getPath() + '/' ;
            try {
                return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), path, uri.getQuery(), uri.getFragment());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return uri;
    }

    /**
     * Same as {@link java.nio.file.Path#getFileName()} for an URI.
     * 
     * @param uri an URI
     * @return a string representing the name of the file or directory or an empty string.
     */
    @objid ("99c29776-778a-4deb-a67f-afb3a98d9365")
    public static String getFileName(URI uri) {
        String p = uri.getPath();
        // if path ends with '/' exclude it
        int endidx = p.endsWith("/") ?  p.length() - 2 : p.length() - 1;
        int idx = p.lastIndexOf('/', endidx); 
        if (idx == -1)
            return p;
        else
            return p.substring(idx+1, endidx+1);
    }

}
