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
package org.modelio.vstore.jdbm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.CloseOnFail;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.session.api.repository.BlobServices;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Manages blobs as files within a directory.
 */
@objid ("d6de25f1-7425-4bf0-8dd3-abc60ef083d8")
class BlobsRepository {
    @objid ("aff14d29-3029-4204-8d46-5da32e29c430")
    private static final String EXT_BLOB = ".blob";

    @objid ("f2fe0ee3-c701-463d-b05a-0149ccbac191")
    private Path blobsPath;

    @objid ("f63a2865-b72d-4921-a291-e3d153989ada")
    public  BlobsRepository(Path blobsPath) {
        this.blobsPath = blobsPath;
    }

    @objid ("48758135-b40e-4957-a7d6-1e824b83bac6")
    public OutputStream writeBlob(IBlobInfo info) throws IOException {
        Path blobPath = getBlobPath(info.getKey());
        
        Path blobDir = blobPath.getParent();
        assert blobDir != null; // to please FindBugs
        
        Files.createDirectories(blobDir);
        
        OutputStream os = Files.newOutputStream(blobPath);
        
        try (CloseOnFail c = new CloseOnFail(os)) {
            BlobServices.write(info, os);
            c.success();
        }
        return os;
    }

    @objid ("79bf2e69-ad55-483d-bd41-05ab4962067c")
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

    @objid ("ae2178c8-554b-431a-a08d-f8eb88939ece")
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

    @objid ("07f1abba-a368-4fd0-ab9e-4dc42456cbd8")
    public void removeBlob(String blob) throws IOException {
        Path blobPath = getBlobPath(blob);
        Files.deleteIfExists(blobPath);
        
    }

    /**
     * Compute the path of a blob file.
     * @param blobKey the blob key
     * @return the blob file path relative to the repository path.
     */
    @objid ("3512f15c-115a-4697-8bd0-17ff3c991fc8")
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
    @objid ("5b6ac50a-498a-47ea-a8ad-87c6e0af9cca")
    public static String getBlobKey(Path file) {
        Path fileName = file.getFileName();
        assert (fileName != null); // to please FindBugs
        
        String fname = fileName
                .toString()
                .replace(EXT_BLOB, "");
        return FileUtils.decodeFileName(fname, new StringBuilder()).toString();
    }

    @objid ("96a69f79-1409-4abd-bbf9-cedec4d16af7")
    public Stream<String> queryBlobs(MRef ref, String localName) throws IOException {
        if (localName == null) {
            String key = ref.uuid;
            Path p = getBlobPath(key);
            String prefix = p.getFileName().toString();
            
            return Files.list(p.getParent())
            .filter(f -> f.getFileName().toString().startsWith(prefix))
            .map(f -> getBlobKey(f));
        } else {
            String k = BlobInfo.computeKey(ref, localName);
            Path blobPath = getBlobPath(k);
            if (Files.isRegularFile(blobPath)) {
                return Stream.of(localName);
            } else {
                return Stream.empty();
            }
        }
        
    }

}
