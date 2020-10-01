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

package org.modelio.xmi.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.statik.Package;

@objid ("93cf8eba-da3a-4a21-890b-4a2f9a12e6ea")
public class ExportConfiguration {
    @objid ("90f83637-4b48-491e-8fb1-5b016cbc4036")
    private Boolean exportedAnotation;

    @objid ("1d8e0843-8f63-4e93-b970-34bf25a38f54")
    private FormatExport versionExport;

    @objid ("22cd7300-4190-473d-b1a9-008aa30423d6")
    private List<ModelElement> entryPoints = new ArrayList<>();

    @objid ("ca1a6a79-30c6-4cbe-b757-0b8a3666396f")
    private File xmiFile;

    @objid ("74f26438-a9dc-4ace-bbf9-22f436401241")
    public List<ModelElement> getEntryPoint() {
        return this.entryPoints;
    }

    @objid ("0bb3c150-80bb-4ea1-a73f-1de94658fe08")
    public void addEntryPoint(final Package entryPoint) {
        this.entryPoints.add(entryPoint);
    }

    @objid ("d38ee6db-b5ad-4051-8913-d7abe0d3c3f6")
    public File getXmiFile() {
        return this.xmiFile;
    }

    @objid ("2c4a4bd4-2aa0-4d59-a027-b3e235f722a7")
    public void setXmiFile(final File xmiFile) {
        this.xmiFile = xmiFile;
    }

    @objid ("12c3bd26-3c49-4e88-bb23-82644ac3e7af")
    public Boolean exportedAnotation() {
        return this.exportedAnotation;
    }

    @objid ("1bf4a8a3-f40a-4ffb-a7c7-82b68381396b")
    public void setExportedAnotation(final Boolean exportAnotation) {
        this.exportedAnotation = exportAnotation;
    }

    @objid ("ebaf1bc3-7c74-48ed-b695-30a26deb487a")
    public FormatExport getVersionExport() {
        return this.versionExport;
    }

    @objid ("6344f310-763b-4826-9e90-9c8f1142c451")
    public void setVersionExport(final FormatExport versionExport) {
        this.versionExport = versionExport;
    }

    @objid ("0dc5ba31-54c9-4197-a928-2987d16229c8")
    public void setXmiFile(final String xmiFilePath) {
        File file = new File(xmiFilePath);
        if (file.exists()){
            this.xmiFile = file;
        }
    }

    @objid ("254a50f2-b28e-4350-89a1-75e0c72000a1")
    public void addEntryPoint(final Profile entryPoint) {
        this.entryPoints.add(entryPoint);
    }

    @objid ("71510d40-2fc6-411c-9e4c-f20b41d8e437")
    public void setEntryPoint(final Package entryPoint) {
        this.entryPoints = new ArrayList<>();
        this.entryPoints.add(entryPoint);
    }

    @objid ("6ddbfd4e-8eea-4add-9473-92ad19536035")
    public void setEntryPoint(final Profile entryPoint) {
        this.entryPoints = new ArrayList<>();
        this.entryPoints.add(entryPoint);
    }

}
