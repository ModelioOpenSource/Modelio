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
package org.modelio.app.model.imp.impl;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Data model for the model import.
 * <p>
 * Holds several data:
 * <ul>
 * <li>the GProject to import contents from. <code>null</code> until {@link #setImportedProject(Path)} is called.</li>
 * <li>a list of elements to import.</li>
 * <li>whether or not the import should re-identify elements.</li>
 * </ul>
 */
@objid ("b74140da-c13e-4a23-ad67-7b538cd574e9")
public class ModelImportDataModel implements AutoCloseable {
    @objid ("8a016301-daf7-4c15-8e2f-60a239452b0f")
    private List<MObject> elementsToImport = new ArrayList<>();

    @objid ("b8b05bc6-8159-4d88-ae31-7189b9b6ae13")
    private GProject importedProject = null;

    /**
     * Get the actual list of all elements to import.
     * @return all elements to import.
     */
    @objid ("c1c50a4f-91f2-46a8-aa75-0905b4176ecc")
    public List<MObject> getElementsToImport() {
        return this.elementsToImport;
    }

    /**
     * Get the current imported project.
     * @return the imported project. Might be <code>null</code> if {@link #setImportedProject(Path)} has not been called.
     */
    @objid ("329e3d58-6ebc-4bdb-9060-2ee164eeba87")
    public GProject getImportedProject() {
        return this.importedProject;
    }

    /**
     * Sets the imported project.
     * <p>
     * Also clears the element import list.
     * </p>
     */
    @objid ("04d82fce-1fcd-4d1e-947f-c2eabb5f2707")
    public void setImportedProject(GProject importedProject) {
        // Clean up the imported element list, as their project is closed...
        this.elementsToImport.clear();
        
        this.importedProject = importedProject;
        
    }

    @objid ("2eab097b-13d6-4dfd-a427-2ebd9acb0532")
    @Override
    public void close() throws Exception {
        if (this.importedProject != null) {
            this.importedProject.close();
            this.importedProject = null;
        }
        
    }

}
