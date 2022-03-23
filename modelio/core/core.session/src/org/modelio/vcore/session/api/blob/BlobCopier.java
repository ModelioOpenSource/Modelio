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
package org.modelio.vcore.session.api.blob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystemException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.repository.IRepository;

/**
 * Helper class to copy blobs.
 */
@objid ("c0edf6e3-6bc5-422a-9efc-dce081833588")
public class BlobCopier {
    /**
     * Copy a blob from a repository to another (or the same).
     * <p>
     * Reports failures to the destination storage error support.
     * @param blobKey the source blob key.
     * @param from the source repository
     * @param to the destination blob identification
     * @param toRepo the destination repository
     * @return <code>true</code> if the copy was successfull, <code>false</code> in case of I/O failure.
     */
    @objid ("2ba32157-a04a-4493-ad8c-e11b338462c8")
    public static boolean copy(String blobKey, IRepository from, IBlobInfo to, IRepository toRepo) {
        try (InputStream in = from.readBlob(blobKey);
                OutputStream out = toRepo.writeBlob(to)) {
            if (in != null) {
            byte[] buffer = new byte[1024 * 4];
            int len = in.read(buffer);
            while (len != -1) {
                out.write(buffer, 0, len);
                len = in.read(buffer);
            }
            return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            // Report failure to the destination storage error support
            String err = getErrorString(e);
            String msg = "Cannot copy '"+blobKey+"' from "+from+" to "+to+" in "+toRepo+": "+err;
            
            toRepo.getErrorSupport().fireWarning(new IOException(msg, e));
            return false;
        }
        
    }

    /**
     * Move a blob from a repository to another.
     * <p>
     * The destination repository must be different from the source one.
     * Reports failures to the involved storage error support.
     * @param blobKey the source blob key.
     * @param from the source repository
     * @param to the destination repository
     * @return <code>true</code> if the blob could at least be copied to the destination repository even if it
     * couldn't be deleted from the source repository.
     * <code>false</code> if copying the blob failed.
     */
    @objid ("e91e5865-d372-4601-aa1e-21f835b573f7")
    public static boolean move(String blobKey, IRepository from, IRepository to) {
        // 1) Get blob info
        IBlobInfo info;
        try {
            info = from.readBlobInfo(blobKey);
        } catch (IOException e) {
            // Report failure to the source storage error support
            String err = getErrorString(e);
            String msg = "Cannot read '"+blobKey+"' blob to move from "+from+" to "+to+": "+err;
            
            from.getErrorSupport().fireWarning(new IOException(msg, e));
            return false;
        }
        
        if (info == null) {
            return false;
        }
        
        // 2) copy blob to destination
        if (! copy(blobKey,from, info, to)) 
            return false;
        
        // 3) delete blob from source
        // From this point consider the operation as success even if deletion fails.
        try {
            from.removeBlob(blobKey);
        } catch (IOException e) {
            // Report failure to the target storage error support
            String err = getErrorString(e);
            String msg = "Cannot delete '"+info+"' blob copied from "+from+" to "+to+": "+err;
        
            to.getErrorSupport().fireWarning(new IOException(msg, e));
        }
        return true;
    }

    /**
     * Get a user friendly error message from the given exception.
     * @param e an exception
     * @return the error message
     */
    @objid ("f66b7f0e-0d44-4c79-8b72-ea7152d77ef3")
    private static String getErrorString(IOException e) {
        String err;
        if (e instanceof FileSystemException)
            err = FileUtils.getLocalizedMessage((FileSystemException)e);
        else
            err = e.getLocalizedMessage();
        return err;
    }

}
