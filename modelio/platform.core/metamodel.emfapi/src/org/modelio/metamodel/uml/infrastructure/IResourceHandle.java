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

package org.modelio.metamodel.uml.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.auth.IAuthData;

@objid ("4b1195b8-52b5-434f-8a59-74de087d59f0")
public interface IResourceHandle {
    @objid ("4fd05446-cc86-4c29-99c5-f7e076909d80")
    public static final String BLOB_LOCAL_KEY = "modelio.resource";

    /**
     * Open a read stream.
     * <p>
     * The caller has to close the stream, usually with a try-with-resource statement.
     * 
     * @return the read stream.
     * @throws java.io.IOException on I/O failure.
     */
    @objid ("b640fc64-2a28-44f3-8522-aeef1c7d0720")
    InputStream read() throws IOException;

    /**
     * Open a write stream on the resource.
     * <p>
     * The caller has to close the stream, usually with a try-with-resource statement.
     * 
     * @return the writing stream.
     * @throws java.io.IOException on I/O failure.
     */
    @objid ("31663cb8-5d90-4f7f-b927-04ac5121eb59")
    OutputStream write() throws IOException;

    /**
     * Set the authentication data that may be needed to access the resource.
     * 
     * @param auth the authentication data.
     */
    @objid ("7bea46b3-ae84-422f-b0e5-9e7cf4855209")
    void setAuthenticationData(IAuthData auth);

    /**
     * Copy the resource in a file in the given directory.
     * 
     * @param dir the target directory.
     * @return the file containing the resource.
     * @throws java.io.IOException the created file.
     */
    @objid ("17bff375-09b8-4481-85d1-e8a742828799")
    Path extractInto(Path dir) throws IOException;

    /**
     * Copy the attached resource content between 2 model objects.
     * <p>
     * Beware the target resource content will be overwritten, even if it
     * the resource was an external reference.
     * 
     * @param src the source resource handle.
     * @param target the target resource handle.
     * @throws java.io.IOException on I/O failure
     */
    @objid ("f22691ce-a5f2-45a2-b323-9d4ef5016941")
    static void copy(IResourceHandle src, IResourceHandle target) throws IOException {
        try (InputStream is = src.read();
                OutputStream os = target.write();) {
        
            byte[] buf = new byte[4096];
            int read = 0;
            while((read = is.read(buf)) > 0) {
                os.write(buf, 0, read);
            }
        }
    }

    /**
     * Get the location of the resource.
     * <p>
     * Returns null for an embedded resource.
     * 
     * @return the location of the resource.
     */
    @objid ("dbb373fa-e595-41bc-8a98-51897dbe96d7")
    URI getLocation();

}
