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

package org.modelio.gproject.data.project.todo;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.data.plugin.GProjectData;
import org.modelio.gproject.data.project.ModuleDescriptor;

/**
 * Update module action descriptor.
 * @since Modelio 3.4
 */
@objid ("214917d8-42fd-4de9-8e0c-4c5fa9b2f955")
public class UpdateModuleDescriptor extends TodoActionDescriptor {
    @objid ("7bb78d6a-db00-4325-914c-23095d2f5fe1")
    private String oldModuleName;

    @objid ("901b05c8-a3e7-43ca-84ef-3f4990b9c4bc")
    private ModuleDescriptor newDescriptor;

    @objid ("d1184e9d-3e10-4e12-8405-38fddeb3a843")
    @Override
    public String getLocalizedLabel() {
        return GProjectData.I18N.getMessage("TodoDescriptor.update",
                                this.oldModuleName,
                                this.newDescriptor.getName(),
                                this.newDescriptor.getVersion(),
                                this.newDescriptor.getArchiveLocation());
    }

    @objid ("00e3dda7-3dfa-4454-b22b-f7e7689e3b91")
    public ModuleDescriptor getNewModuleDescriptor() {
        return this.newDescriptor;
    }

    @objid ("54fd7622-be89-4687-a6c2-ba597d7ba3fb")
    public String getOldModuleName() {
        return this.oldModuleName;
    }

    @objid ("897c3a41-b8eb-499d-aa86-5e7845ae99d6")
    public void setNewModuleDescriptor(ModuleDescriptor descriptor) {
        this.newDescriptor = descriptor;
    }

    @objid ("e56cfb08-a0ab-473e-8708-290c5b2da848")
    public void setOldModuleName(String moduleName) {
        this.oldModuleName = moduleName;
    }

    @objid ("1c375c7c-65cb-4742-9ad1-146bd8ac9279")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.newDescriptor == null) ? 0 : this.newDescriptor.hashCode());
        result = prime * result + ((this.oldModuleName == null) ? 0 : this.oldModuleName.hashCode());
        return result;
    }

    @objid ("0e16695d-da6a-449a-b9b2-a3726e5f55ed")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UpdateModuleDescriptor other = (UpdateModuleDescriptor) obj;
        if (this.newDescriptor == null) {
            if (other.newDescriptor != null) {
                return false;
            }
        } else if (!this.newDescriptor.equals(other.newDescriptor)) {
            return false;
        }
        if (this.oldModuleName == null) {
            if (other.oldModuleName != null) {
                return false;
            }
        } else if (!this.oldModuleName.equals(other.oldModuleName)) {
            return false;
        }
        return true;
    }

    @objid ("e51aa48d-679f-4dc8-b0b0-02d99abba9f8")
    @Override
    public boolean isValid() {
        return this.oldModuleName != null && (!this.oldModuleName.isEmpty()) && this.newDescriptor!=null && this.newDescriptor.isValid();
    }

    @objid ("931b16e2-4825-43ca-910e-1a7900c42678")
    @Override
    public String toString() {
        return "UpdateModuleDescriptor [update '" + this.oldModuleName + "' , newDescriptor=" + this.newDescriptor + "]";
    }

}
