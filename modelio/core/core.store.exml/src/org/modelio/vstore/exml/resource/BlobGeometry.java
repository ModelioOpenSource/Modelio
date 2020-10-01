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

package org.modelio.vstore.exml.resource;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;

/**
 * Service class that computes a blob path.
 * <p>
 * The blobs directory has 255 sub directories
 */
@objid ("8e2a2249-b40a-43a7-906f-1ebe767c713b")
public class BlobGeometry {
    /**
     * Compute the path of a blob file.
     * 
     * @param blobKey the blob key
     * @return the blob file path relative to the repository path.
     */
    @objid ("dce35626-eed2-4881-8156-14ffb5bb9b20")
    public static String getBlobPath(String blobKey) {
        StringBuilder sb = new StringBuilder(200);
        sb.append(IExmlRepositoryGeometry.BLOBS_DIRNAME);
        sb.append('/');
        sb.append(String.format("%02x", Math.abs(blobKey.hashCode() % 255))); 
        sb.append('/');
        FileUtils.encodeFileName(blobKey, sb);
        sb.append(IExmlRepositoryGeometry.EXT_BLOB);
        return sb.toString();
    }

    /**
     * Decode the blob key from a blob file path.
     * 
     * @param file a blob file path.
     * @return the blob key.
     */
    @objid ("8dfc9d6b-10d1-4437-b21e-dc4781a9000d")
    public static String getBlobKey(File file) {
        String fname = file.getName().replace(IExmlRepositoryGeometry.EXT_BLOB, "");
        return FileUtils.decodeFileName(fname, new StringBuilder()).toString();
    }

    /**
     * Decode the blob key from a blob file path.
     * 
     * @param file a blob file path.
     * @return the blob key.
     */
    @objid ("28f436a6-70e9-4fbe-977a-f346f5417f6b")
    public static String getBlobKey(String file) {
        String fname = GeometryUtils.getFileName(file);
        fname = fname.replace(IExmlRepositoryGeometry.EXT_BLOB, "");
        return FileUtils.decodeFileName(fname, new StringBuilder()).toString();
    }

}
