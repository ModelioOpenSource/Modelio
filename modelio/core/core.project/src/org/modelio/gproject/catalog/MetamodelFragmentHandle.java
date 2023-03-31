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
package org.modelio.gproject.catalog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.module.IMetamodelFragmentHandle;
import org.modelio.vbasic.version.Version;

/**
 * Basic implementation of {@link IMetamodelFragmentHandle}.
 */
@objid ("cb21d74a-f9e7-4180-99b3-967271419a52")
class MetamodelFragmentHandle implements IMetamodelFragmentHandle {
    @objid ("e18580d3-b83d-4732-9546-cb6540667c32")
    private String vendor;

    @objid ("9f3db3d2-3137-4208-af62-8ff6e64dc4bf")
    private String vendorVersion;

    @objid ("37c549be-1201-433d-872f-9d01497be53b")
    private String name;

    @objid ("1e926cae-702e-4b99-80ba-b278a8c8fe9c")
    private String mainClassName;

    @objid ("83ca1c63-2277-4956-b8a9-6efb05be94d0")
    private Version version;

    /**
     * @param name the metamodel fragment name
     * @param version the metamodel fragment version for Modelio
     * @param vendor the vendor name this metamodel come from
     * @param vendorVersion the metamodel version from the vendor
     */
    @objid ("d516564e-1333-4234-8f5e-be39400b7a67")
    public  MetamodelFragmentHandle(String name, Version version, String vendor, String vendorVersion, String mainClassName) {
        this.name = name;
        this.version = version;
        this.vendor = vendor;
        this.vendorVersion = vendorVersion;
        this.mainClassName = mainClassName;
        
    }

    /**
     * @return The metamodel version for the Vendor.
     */
    @objid ("681dc5df-15ea-4a49-b227-8cd1d000f9b4")
    @Override
    public String getVendorVersion() {
        return this.vendorVersion;
    }

    /**
     * @param vendorVersion The vendor metamodel version.
     */
    @objid ("5d5ab52b-e098-4b7b-a60a-3ddf2bfe7583")
    public void setVendorVersion(String vendorVersion) {
        this.vendorVersion = vendorVersion;
    }

    /**
     * @return the fragment provider.
     */
    @objid ("37c1a8a2-8477-4927-aad5-e09a5fa10a7c")
    @Override
    public String getVendor() {
        return this.vendor;
    }

    /**
     * @param vendor the fragment provider.
     */
    @objid ("b763b9f0-3686-4fc1-8783-2ae3272e077a")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @objid ("bdee3c92-8086-4889-8120-f72839d33546")
    @Override
    public String getName() {
        return this.name;
    }

    @objid ("4ed3cae1-d711-4e40-8b28-27d9f5e9f84e")
    @Override
    public Version getVersion() {
        return this.version;
    }

    @objid ("5aed07c3-01fc-480a-8d94-5e5b97fea1b0")
    @Override
    public String getClassName() {
        return this.mainClassName;
    }

}
