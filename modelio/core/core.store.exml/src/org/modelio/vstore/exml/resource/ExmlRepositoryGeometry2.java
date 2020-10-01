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

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a local file system repository directory structure.
 * <p>
 * This implementation is for the format version 2.
 */
@objid ("ed2ba5ab-b1c3-40c9-a327-4549b56a4c8c")
public class ExmlRepositoryGeometry2 implements IExmlRepositoryGeometry {
    /**
     * EXML files storage directory path relative to the project space path.
     */
    @objid ("c4308f0f-4f53-4fd3-b3e8-c0134cfc42e4")
     static final String MODEL_STORAGE_PATH = MODEL_DIRNAME;

    @objid ("7b98d153-4c83-4cc6-9867-ae77f9b42e1f")
    @Override
    public String getRelativePath(final MRef ref) {
        return MODEL_STORAGE_PATH+ "/" + ref.mc + "/" + ref.uuid + EXT_EXML;
    }

    @objid ("b987c11e-1fdc-438e-9fbc-23472afac5e6")
    @Override
    public MRef getObRef(String relativePath) {
        String extName = GeometryUtils.getFileName(relativePath);
        return new MRef(GeometryUtils.getParentFileName(relativePath), 
                                                        extName.substring(0, extName.lastIndexOf(EXT_EXML)));
    }

    /**
     * Tells whether a file is an EXML file that can be versioned.
     * <p>
     * The answer is based on the file extension.
     * Returns <i>false</i> if it is a {@link IExmlRepositoryGeometry#EXT_LOCAL_EXML ".local.exml"} file.
     * 
     * @param relativePath a file path relative to the repository root.
     * @return <i>true</i> if it is an EXML file, else <i>false</i>.
     */
    @objid ("f4e85ed7-1c8b-4737-8f46-5877487b7156")
    @Override
    public boolean isModelPath(String relativePath) {
        if ((relativePath != null)
                && (relativePath.startsWith(MODEL_STORAGE_PATH))
                && (relativePath.endsWith(EXT_EXML))
                && (!relativePath.endsWith(EXT_LOCAL_EXML))) {
            try {
                getObRef(relativePath);
                return true;
            } catch (RuntimeException e) {
                return false;
            }
        }
        return false;
    }

    @objid ("6d330306-74f5-4cb6-89cf-0c3c13f4e4a5")
    @Override
    public String getModelPath() {
        return MODEL_DIRNAME;
    }

    @objid ("f99ad56f-4205-45c3-a7af-7fd30c082842")
    @Override
    public String getBlobPath(String blobKey) {
        return BlobGeometry.getBlobPath(blobKey);
    }

    @objid ("624d1acf-2641-4fa0-b179-966b22020ad3")
    @Override
    public String getBlobKey(String relativePath) {
        return BlobGeometry.getBlobKey(relativePath);
    }

    @objid ("957f9945-6c9c-4c52-b864-cf8f8cacfcab")
    @Override
    public boolean isBlobPath(String relativePath) {
        return relativePath.startsWith(BLOBS_DIRNAME)
                                && relativePath.endsWith(EXT_BLOB);
    }

    @objid ("e4c40526-da35-4323-a794-8f8454117453")
    @Override
    public String getLocalFileRelativePath(final MRef ref) {
        return MODEL_STORAGE_PATH+ "/" + ref.mc + "/" + ref.uuid + EXT_LOCAL_EXML;
    }

    @objid ("0ecc4a61-36ec-4ae1-b545-428554869e19")
    @Override
    public Collection<String> getInitialDirectories(MMetamodel metamodel) {
        Collection<String> ret = new ArrayList<>(600);
        
        // Administration directory
        ret.add(ADMIN_DIRNAME);
        
        // Add "model" directory
        ret.add(MODEL_DIRNAME);
        
        // Add directory for each CMS node metaclass
        for (final MClass cmsNodeClass : metamodel.getRegisteredMClasses())
        {
            if (cmsNodeClass.isCmsNode() && !cmsNodeClass.isAbstract()) {
                ret.add(MODEL_DIRNAME+"/"+(cmsNodeClass.getQualifiedName()));
            }
        }
        
        // Add "blobs/*" directories
        ret.add(IExmlRepositoryGeometry.BLOBS_DIRNAME);
        
        for (int i=0; i<256; i++) {
            ret.add((String.format("%s/%02x",IExmlRepositoryGeometry.BLOBS_DIRNAME, i)));
        }
        return ret;
    }

    @objid ("c27ae636-0718-4e02-bfe6-d16ce22e29ef")
    @Override
    public String getMetamodelDescriptorPath() {
        return MM_DESCRIPTOR_PATH;
    }

}
