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

/**
 * Remove module action descriptor.
 * @since Modelio 3.4
 */
@objid ("4d18358d-eb06-4e95-912a-bffe8e24b514")
public class RemoveModuleDescriptor extends TodoActionDescriptor {
    @objid ("4afc52f7-35b5-4180-83ac-1e01e239714a")
    private String moduleName;

    @objid ("1fdb9d39-59df-418f-ab49-2d2120e10420")
    @Override
    public String getLocalizedLabel() {
        return GProjectData.I18N.getMessage("TodoDescriptor.remove",
                                this.moduleName);
        
    }

    @objid ("388af171-b600-451e-83ee-e4f083a3153e")
    public String getModuleName() {
        return this.moduleName;
    }

    @objid ("a1bfe975-256f-47b2-96bb-00de65588cf6")
    public void setModuleName(String moduleName) {
        assert (moduleName != null && !moduleName.isEmpty());
        
        this.moduleName = moduleName;
        
    }

    @objid ("275ce389-a030-458d-b4c8-8522c35098dc")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.moduleName == null) ? 0 : this.moduleName.hashCode());
        return result;
    }

    @objid ("75166c47-b36c-4c1f-847b-631ec8305c6a")
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
        RemoveModuleDescriptor other = (RemoveModuleDescriptor) obj;
        if (this.moduleName == null) {
            if (other.moduleName != null) {
                return false;
            }
        } else if (!this.moduleName.equals(other.moduleName)) {
            return false;
        }
        return true;
    }

    @objid ("57a15e5f-7185-4640-b89e-f888484a97b3")
    @Override
    public boolean isValid() {
        return this.moduleName != null && !this.moduleName.isEmpty();
    }

    @objid ("f30b18b3-e2cf-48f1-af30-68ed27238097")
    @Override
    public String toString() {
        return "RemoveModuleDescriptor [moduleName=" + this.moduleName + "]";
    }

}
