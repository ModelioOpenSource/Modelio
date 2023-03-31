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
package org.modelio.platform.project.creation;

import java.net.URI;
import java.net.URISyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("74f58fbb-ac14-4b1c-ab59-d96738871cde")
public class ProjectUriHelper {
    /**
     * Create a HTTPS URI with the given path, no query and no fragment.
     * @param source the source URI
     * @param path the wanted path
     * @return the new URI
     * @throws IllegalArgumentException on unexpected {@link URISyntaxException}.
     */
    @objid ("6d096c08-4d4c-4a75-b7b9-46f5e352858d")
    public static URI httpsUriWithPath(URI source, String path) throws IllegalArgumentException {
        try {
            return new URI("https", null, source.getHost(), source.getPort(), path, null, null);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Unable to transform '%s' URL with '%s' path: %s.",source, path, e.getLocalizedMessage()));
        }
        
    }

    /**
     * Remove trailing '/' in the path if present.
     * @param source an URI
     * @return the same or an URI with trailing '/' trimmed.
     * @throws IllegalArgumentException on unexpected {@link URISyntaxException}.
     */
    @objid ("665c9d07-734b-4890-a9db-834e9bb76c67")
    public static URI trimTrailingSlash(URI source) throws IllegalArgumentException {
        String path = source.getPath();
        int lastIndex = path.length() - 1;
        if (lastIndex > 0 && path.lastIndexOf('/') == lastIndex) {
            // Trim last '/'
            path = path.substring(0, lastIndex);
            try {
                return new URI(source.getScheme(), source.getAuthority(), source.getHost(), source.getPort(), path, source.getQuery(), source.getFragment());
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(String.format("Unable to transform '%s' URL with '%s' path: %s.",source, path, e.getLocalizedMessage()));
            }
        }
        return source;
    }

}
