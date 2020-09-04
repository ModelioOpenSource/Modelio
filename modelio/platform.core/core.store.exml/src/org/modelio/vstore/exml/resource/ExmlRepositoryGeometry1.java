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

package org.modelio.vstore.exml.resource;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a local filesystem repository directory structure with format version as 1.
 * <p>
 * This implementation is for the format version 1.
 */
@objid ("9b006f28-1c51-11e2-8eb9-001ec947ccaf")
public class ExmlRepositoryGeometry1 implements IExmlRepositoryGeometry {
    /**
     * EXML files storage directory path relative to the project space path.
     */
    @objid ("d82c530a-1ceb-11e2-8eb9-001ec947ccaf")
     static final String MODEL_STORAGE_PATH = MODEL_DIRNAME;

    /**
     * Get the EXML file local path of an element reference relative to the project space.
     * @param ref an element reference.
     * @return the EXML file path relative to the project space directory.
     */
    @objid ("d715624d-1ceb-11e2-8eb9-001ec947ccaf")
    private static String getPath(final MRef ref, String extension) {
        return MODEL_STORAGE_PATH+ "/" + getSimpleName(ref.mc) + "/" + ref.uuid + extension;
    }

    /**
     * Get the element reference representing the given file.
     * @param exmlFile an EXML file
     * @return the represented element reference.
     */
    @objid ("d7156254-1ceb-11e2-8eb9-001ec947ccaf")
    @Override
    public MRef getObRef(final String exmlFile) {
        // Guess with errors in the metaclass
        String extName = GeometryUtils.getFileName(exmlFile);
        return new MRef(GeometryUtils.getParentFileName(exmlFile), 
                                                        extName.substring(0, extName.lastIndexOf(EXT_EXML)));
    }

    /**
     * Tells whether a file is an EXML file that can be versioned.
     * <p>
     * The answer is based on the file extension.
     * Returns <i>false</i> if it is a {@link IExmlRepositoryGeometry#EXT_LOCAL_EXML ".local.exml"} file.
     * @param relativePath a file
     * @return <i>true</i> if it is an EXML file, else <i>false</i>.
     */
    @objid ("d715625c-1ceb-11e2-8eb9-001ec947ccaf")
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

    @objid ("d7156264-1ceb-11e2-8eb9-001ec947ccaf")
    @Override
    public String getModelPath() {
        return MODEL_DIRNAME;
    }

    @objid ("44c92fd9-d117-4f51-889b-b137d1ce75f8")
    @Override
    public String getBlobPath(String blobKey) {
        return BlobGeometry.getBlobPath(blobKey);
    }

    @objid ("c57b0408-6501-43d6-8c5a-3a023e1c8bae")
    @Override
    public String getBlobKey(String file) {
        return BlobGeometry.getBlobKey(file);
    }

    @objid ("c6835d04-e3df-46db-ab6a-cfc4de3cb5ed")
    @Override
    public boolean isBlobPath(String relativePath) {
        return relativePath.startsWith(getBlobsPath())
                                && relativePath.endsWith(EXT_BLOB);
    }

    @objid ("4665b72a-75ae-4c24-8c70-e267de6d0eba")
    public String getBlobsPath() {
        return BLOBS_DIRNAME;
    }

    /**
     * @param qname a metaclass name, qualified or not
     * @return the simple name of the qualified metaclass name.
     */
    @objid ("666fd4ae-afa3-41ca-a4a6-be2df4e3d8f0")
    private static String getSimpleName(String qname) {
        int i = qname.lastIndexOf('.');
        if (i == -1) {
            return qname;
        } else {
            return qname.substring(i+1);
        }
    }

    /**
     * Get the directory name where the metaclass instances are stored.
     * @param cls a metaclass
     * @return its directory name.
     */
    @objid ("86366fee-50b2-4f74-86d7-7fd98cc14feb")
    public static String getMetaclassDirectoryName(MClass cls) {
        return cls.getName();
    }

    @objid ("6d0a39bf-0d38-4e0d-bb4f-6b9b8aba79d1")
    @Override
    public String getLocalFileRelativePath(MRef ref) {
        return getPath(ref,EXT_LOCAL_EXML);
    }

    @objid ("184c7c4c-c926-4f67-abe0-f39d50cbbeb4")
    @Override
    public String getRelativePath(MRef ref) {
        return getPath(ref,EXT_EXML);
    }

    @objid ("d07c5cc8-aae9-484b-9b1d-75b5ab4b7ddc")
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
                ret.add(MODEL_DIRNAME+"/"+getMetaclassDirectoryName(cmsNodeClass));
            }
        }
        
        // Add "blobs/*" directories
        ret.add(IExmlRepositoryGeometry.BLOBS_DIRNAME);
        
        for (int i=0; i<256; i++) {
            ret.add((String.format("%s/%02x",IExmlRepositoryGeometry.BLOBS_DIRNAME, i)));
        }
        return ret;
    }

    @objid ("a6f50cbc-9c03-4ef0-a95d-ae137b42ad28")
    @Override
    public String getMetamodelDescriptorPath() {
        return MM_DESCRIPTOR_PATH;
    }

}
