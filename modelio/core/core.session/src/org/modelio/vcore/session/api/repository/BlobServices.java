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

package org.modelio.vcore.session.api.repository;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.blob.BlobInfo;
import org.modelio.vcore.session.api.blob.IBlobInfo;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Blob management services.
 */
@objid ("2895967e-3bc5-4700-a6b2-62c03aab8a49")
public class BlobServices {
    /**
     * Blob serialization format version.
     * <p>
     * History:<ul>
     * <li> 1 : initial version
     * <li> 2 : Modelio 3.7 - 31/03/2017 : <ul>
     * <li> remove label
     * <li> added related element
     * <li> added local id.
     * </ul>
     * 
     * </ul>
     */
    @objid ("91add287-813d-4b85-b6fa-09369fd97713")
    private static final int VERSION = 2;

    @objid ("f8635a30-31b9-4132-af24-f363f3b0852e")
    private BlobServices() {
        // no instance
    }

    /**
     * Read the blob info from an input stream.
     * 
     * @param is an input stream.
     * @return the read blob information.
     * @throws java.io.IOException in case of failure
     */
    @objid ("1cc90816-c7f9-4fef-b9c2-50ed4f0a8f72")
    public static IBlobInfo readBlobInfo(InputStream is) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        int rversion = dis.readInt();
        if (rversion == 1) {
            String key = dis.readUTF();
            @SuppressWarnings("unused")
            String label = dis.readUTF();
            return new BlobInfo(key);
        } else if (rversion == VERSION) {
            String mc = dis.readUTF();
            if (mc.isEmpty()) {
                // global blob
                String key = dis.readUTF();
                return new BlobInfo(key);
            } else {
                // blob related to a model object
                String uuid = dis.readUTF();
                String refname = dis.readUTF();
                String localId = dis.readUTF();
                MRef ref = new MRef(mc, uuid, refname);
        
                return new BlobInfo(ref, localId);
            }
        } else {
            throw new UnsupportedOperationException(rversion + " BlobInfo version not supported");
        }
    }

    /**
     * Read a set of blob informations from a repository.
     * <p>
     * If some blobs cannot be read for any reason, a blob information is created
     * with its key and the error message as label. A warning is also fired
     * to the repository error listeners.
     * 
     * @param blobKeys the blob keys to read
     * @param repo the repository where blobs are stored
     * @return the read blob information.
     */
    @objid ("6c978d96-5584-47a4-88cb-bacd839ccaea")
    public static Collection<IBlobInfo> loadBlobInfos(Collection<String> blobKeys, IRepository repo) {
        Collection<IBlobInfo> infos = new ArrayList<>(blobKeys.size());
        for (String key : blobKeys) {
            try {
                IBlobInfo info = repo.readBlobInfo(key);
                if (info != null) {
                    infos.add(info);
                } else {
                    throw new IllegalArgumentException(key);
                }
            } catch (FileSystemException e) {
                BlobInfo info = new BlobInfo(key, "<"+FileUtils.getLocalizedMessage(e)+">");
                infos.add(info);
                repo.getErrorSupport().fireWarning(e);
                
            } catch (IOException e) {
                BlobInfo info = new BlobInfo(key, "<"+e.getLocalizedMessage()+">");
                infos.add(info);
                repo.getErrorSupport().fireWarning(e);
            }
        }
        return infos;
    }

    /**
     * Serialize a blob information implementing {@link IBlobInfo} in the given output stream.
     * <p>
     * The blob information will be readable as by using {@link #readBlobInfo(InputStream)}.
     * 
     * @param info a blob information
     * @param os an output stream.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("fbf396f3-fc2e-4fae-b936-9647f6c64e79")
    public static void write(IBlobInfo info, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        
        dos.writeInt(VERSION);
        MRef relatedElement = info.getRelatedElement();
        if (relatedElement == null) {
            dos.writeUTF("");
            dos.writeUTF(info.getKey());
        } else {
            dos.writeUTF(relatedElement.mc);
            dos.writeUTF(relatedElement.uuid);
            dos.writeUTF(relatedElement.name);
            dos.writeUTF(info.getLocalName());
        }
    }

}
