/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Configuration class for the XMI import service in {@link IExchangeService}.
 * @since 2.2
 */
@objid ("147a80ae-9516-11e1-a83f-002564c97630")
public class XmiImportConfiguration {
    /**
     * File in XMI format containing the model to import
     */
    @objid ("147acecc-9516-11e1-a83f-002564c97630")
    private File xmiFile;

    /**
     * Element owner of the import result.<br>
     * Must be an IPackage for Model import or an IModule for Profile import.
     */
    @objid ("dff43c8e-edf0-11e1-82c2-001ec947ccaf")
    private MObject owner;

    /**
     * Default constructor.
     * 
     * @param xmiFile File in XMI format containing the model to import.
     * @param packageToImport the default owner for the import.
     */
    @objid ("147aa7bb-9516-11e1-a83f-002564c97630")
    public XmiImportConfiguration(final File xmiFile, Package packageToImport) {
        this.owner = packageToImport;
        this.xmiFile = xmiFile;
    }

    @objid ("147af5dc-9516-11e1-a83f-002564c97630")
    public File getXmiFile() {
        return this.xmiFile;
    }

    @objid ("147b1cee-9516-11e1-a83f-002564c97630")
    public void setXmiFile(final File xmiFile) {
        this.xmiFile = xmiFile;
    }

    @objid ("a4132080-0ecc-11e2-96c4-002564c97630")
    public MObject getOwner() {
        return this.owner;
    }

    @objid ("a4136ea2-0ecc-11e2-96c4-002564c97630")
    public void setOwner(final MObject owner) {
        this.owner = owner;
    }

    @objid ("7f602fcc-e959-4550-9a30-fb517192ba10")
    public void setXmiFile(final String xmiFilePath) {
        File file = new File(xmiFilePath);
        if (file.exists()){
            this.xmiFile = file;
        }
    }

}
