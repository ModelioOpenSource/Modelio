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

package org.modelio.vstore.jdbm7;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.BlobServices;

/**
 * Manages blobs as files within a directory.
 */
@objid ("8d5ba6fe-304a-42ad-a469-c973968d0071")
class BlobsRepository {
    @objid ("6d7c04ca-368b-4a1d-9e6f-5ff8da2baa60")
    private static final String EXT_BLOB = ".blob";

    @objid ("990f5a6f-9dd9-49f8-8dec-3f8d656c2cee")
    private Path blobsPath;

    @objid ("ee821009-3964-41c5-85b1-bd36c275078e")
    public BlobsRepository(Path blobsPath) {
        this.blobsPath = blobsPath;
    }

    @objid ("7a2e277b-371a-4b6d-a485-d13d0a7b98e3")
    public OutputStream writeBlob(IBlobInfo info) throws IOException {
        Path blobPath = getBlobPath(info.getKey());
        
        Path blobDir = blobPath.getParent();
        assert (blobDir != null); // to please FindBugs
        
        Files.createDirectories(blobDir);
        
        OutputStream os = Files.newOutputStream(blobPath);
        
        try (CloseOnFail c = new CloseOnFail(os)) {
            BlobServices.write(info, os);
            c.success();
        }
        return os;
    }

    @objid ("136014d8-2a9b-4f77-9741-6adde9c2478f")
    public InputStream readBlob(String key) throws IOException {
        Path blobPath = getBlobPath(key);
        if (Files.isRegularFile(blobPath)) {
            InputStream is = Files.newInputStream(blobPath);
            try (CloseOnFail c = new CloseOnFail(is)) {
                @SuppressWarnings("unused")
                IBlobInfo unused = BlobServices.readBlobInfo(is);
                
                c.success();
            }
            
            return is;
        } else {
            return null;
        }
    }

    @objid ("0878a224-1a3e-4255-8530-03faf951649c")
    public IBlobInfo readBlobInfo(String key) throws IOException {
        Path blobPath = getBlobPath(key);
        if (Files.isRegularFile(blobPath)) {
            try (InputStream is = Files.newInputStream(blobPath)) {
                return BlobServices.readBlobInfo(is);
            }
        } else {
            return null;
        }
    }

    @objid ("4ca3236f-651b-414e-b4c5-7393d44b999f")
    public void removeBlob(String blob) throws IOException {
        Path blobPath = getBlobPath(blob);
        Files.deleteIfExists(blobPath);
    }

    /**
     * Compute the path of a blob file.
     * @param blobKey the blob key
     * @return the blob file path relative to the repository path.
     */
    @objid ("5ec2ac5b-3ec3-415e-b197-54903824f79b")
    public Path getBlobPath(String blobKey) {
        StringBuilder sb = new StringBuilder(200);
        FileUtils.encodeFileName(blobKey, sb);
        sb.append(EXT_BLOB);
        return this.blobsPath.resolve(sb.toString());
    }

    /**
     * Decode the blob key from a blob file path.
     * @param file a blob file path.
     * @return the blob key.
     */
    @objid ("6665f267-9630-4fb4-8dd6-527d690c83f4")
    public static String getBlobKey(Path file) {
        Path fileName = file.getFileName();
        assert (fileName != null); // to please FindBugs
        
        String fname = fileName.toString().replace(EXT_BLOB, "");
        return FileUtils.decodeFileName(fname, new StringBuilder()).toString();
    }

}
