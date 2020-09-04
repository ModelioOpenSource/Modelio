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

package org.modelio.bpmnxml.importer.commands;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("f0c305bf-6b54-471d-aafa-5c804e45b11d")
public class BPMImportModel {
    @objid ("d299b894-59ad-44fb-96ab-4942e31fbef6")
    private String filePath;

    @objid ("3a8f5151-3b65-4155-bd73-dbbffcaa146b")
    private boolean keeyId = false;

    @objid ("9265850c-75b5-41d1-a022-7e1465f72458")
    private boolean update = false;

    @objid ("b9c11978-3a30-4c81-a978-b7c911cd829a")
    public String getFilePath() {
        return this.filePath;
    }

    @objid ("1df4cbbf-f84d-44ac-af87-3c6d3d097423")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @objid ("de4f4913-2bcb-4908-9ae5-1195264a307c")
    public boolean isKeeyId() {
        return this.keeyId;
    }

    @objid ("bcc8cfbe-f729-428f-94df-8c5ca0037f2b")
    public void setKeeyId(boolean keeyId) {
        this.keeyId = keeyId;
    }

    @objid ("4bf429a7-cbde-4aa0-8e53-f1a5bee62f90")
    public boolean isUpdate() {
        return this.update;
    }

    @objid ("d134260d-54bd-4be1-83e5-ac5155d88b7f")
    public void setUpdate(boolean update) {
        this.update = update;
    }

}
