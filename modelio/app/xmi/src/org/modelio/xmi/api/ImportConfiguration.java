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

package org.modelio.xmi.api;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("0e446a2a-d92a-4891-b7e1-d481f731e0eb")
public class ImportConfiguration {
    @objid ("9508c66e-b172-4fe4-adb9-a035429d75a6")
    private MObject owner;

    @objid ("d32147c6-283e-44e4-881a-081fa1083daa")
    private File xmiFile;

    @objid ("970310fe-3baa-4437-9a06-9a13d471ec7e")
    public File getXmiFile() {
        return this.xmiFile;
    }

    @objid ("20aabe2f-74d3-4382-a5d8-19f3bb42cd65")
    public void setXmiFile(final File xmiFile) {
        this.xmiFile = xmiFile;
    }

    @objid ("39fd521b-127d-4759-9a24-9dc6141792b0")
    public MObject getOwner() {
        return this.owner;
    }

    @objid ("0237f45c-f9e3-442b-aa28-e703bf73b4e2")
    public void setOwner(final MObject owner) {
        this.owner = owner;
    }

    @objid ("5f110c5b-b15c-4abd-9c80-fe8c10b80de2")
    public void setXmiFile(final String xmiFilePath) {
        File file = new File(xmiFilePath);
        if (file.exists()){
            this.xmiFile = file;
        }
    }

}
