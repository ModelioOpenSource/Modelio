/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.exchange;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.statik.Package;

/**
 * Configuration class for the XMI export service in {@link IExchangeService}.
 * @since 2.2
 */
@objid ("1478fa0e-9516-11e1-a83f-002564c97630")
public class XmiExportConfiguration {
    /**
     * Specify if annotation must be exported.
     */
    @objid ("1479482d-9516-11e1-a83f-002564c97630")
    private boolean exportAnnotations;

    /**
     * Format of the export i.e. EMF 3.0.0, OMG UML2.1.1, OMG UML 2.2, etc.
     */
    @objid ("5d40436c-9516-11e1-a83f-002564c97630")
    private VersionExport versionExport;

    /**
     * Specify the result file of the export.
     */
    @objid ("1479482f-9516-11e1-a83f-002564c97630")
    private File xmiFile;

    /**
     * Root of the export.
     */
    @objid ("e009b1a8-edf0-11e1-82c2-001ec947ccaf")
    private Package entryPoint;

    /**
     * Default constructor, with some default fields initialization:
     * - annotations are not exported.
     * - file is "$ProjectSpace/XMI/rootPackageName.xmi"
     * - version is EMF 3.0.0
     * @param packageToExport the export root.
     */
    @objid ("1479211b-9516-11e1-a83f-002564c97630")
    public XmiExportConfiguration(Package packageToExport) {
        this.entryPoint = packageToExport;
        this.exportAnnotations = false;
        this.versionExport = VersionExport.EMF300;
        this.xmiFile = new File(GProject.getProject(packageToExport).getProjectPath().toFile(), "XMI/" + this.entryPoint.getName() + ".xmi");
    }

    @objid ("1479bd61-9516-11e1-a83f-002564c97630")
    public VersionExport getVersionExport() {
        return this.versionExport;
    }

    @objid ("1479e46e-9516-11e1-a83f-002564c97630")
    public void setVersionExport(final VersionExport versionExport) {
        this.versionExport = versionExport;
    }

    @objid ("147a0b7b-9516-11e1-a83f-002564c97630")
    public File getXmiFile() {
        return this.xmiFile;
    }

    @objid ("147a0b7f-9516-11e1-a83f-002564c97630")
    public void setXmiFile(final File xmiFile) {
        this.xmiFile = xmiFile;
    }

    @objid ("147a328b-9516-11e1-a83f-002564c97630")
    public boolean isExportAnnotations() {
        return this.exportAnnotations;
    }

    @objid ("147a328f-9516-11e1-a83f-002564c97630")
    public void setExportAnotations(final boolean exportAnnotations) {
        this.exportAnnotations = exportAnnotations;
    }

    @objid ("a3df8c0e-0ecc-11e2-96c4-002564c97630")
    public Package getEntryPoint() {
        return this.entryPoint;
    }

    @objid ("a3dfb320-0ecc-11e2-96c4-002564c97630")
    public void setEntryPoint(final Package entryPoint) {
        this.entryPoint = entryPoint;
    }

    @objid ("18386706-a4c7-44eb-bd10-cf461d1063b7")
    public void setXmiFile(final String xmiFilePath) {
        File file = new File(xmiFilePath);
        if (file.exists()){
            this.xmiFile = file;
        }
    }

    /**
     * This enumeration lists the available formats for the export
     */
    @objid ("14796f3e-9516-11e1-a83f-002564c97630")
    public enum VersionExport {
        /**
         * OMG UML2.1.1
         */
        UML211,
        /**
         * OMG UML2.2
         */
        UML22,
        /**
         * OMG UML2.3
         */
        UML23,
        /**
         * OMG UML2.4.1
         */
        UML241,
        /**
         * EMF UML2
         */
        EMF300;
    }

}
