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
 * Descriptor for an install module action
 * @since Modelio 3.4
 */
@objid ("969a6260-eec8-4fd4-b301-182b183d4dc5")
public class InstallModuleDescriptor extends TodoActionDescriptor {
    @objid ("12ff200e-169f-4ea1-a227-11fb568710fb")
    private ModuleDescriptor descriptor;

    @objid ("8e74366a-0eb5-44f1-95c4-bbedb7316e4a")
    @Override
    public String getLocalizedLabel() {
        return GProjectData.I18N.getMessage("TodoDescriptor.install",
                                this.descriptor.getName(),
                                this.descriptor.getVersion(),
                                this.descriptor.getArchiveLocation());
    }

    /**
     * @return the descriptor of the module to install.
     */
    @objid ("11bae2d5-3568-4af9-b305-22fffeb2e606")
    public ModuleDescriptor getModuleDescriptor() {
        return this.descriptor;
    }

    /**
     * @param descriptor the descriptor of the module to install.
     */
    @objid ("d83260ce-8b4c-4444-9321-2a387ef8b899")
    public void setDescriptor(ModuleDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @objid ("68652e09-0953-4b13-b8df-6f26b6c5c5aa")
    @Override
    public String toString() {
        return "install "+this.descriptor.getName()+" module from "+this.descriptor.getArchiveLocation();
    }

    @objid ("76c21488-9e55-4674-83c8-c71da450a8c3")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.descriptor == null) ? 0 : this.descriptor.hashCode());
        return result;
    }

    @objid ("719090e6-f580-4964-8e58-a5989a3e803d")
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
        InstallModuleDescriptor other = (InstallModuleDescriptor) obj;
        if (this.descriptor == null) {
            if (other.descriptor != null) {
                return false;
            }
        } else if (!this.descriptor.equals(other.descriptor)) {
            return false;
        }
        return true;
    }

    @objid ("d703b783-48f4-44b0-990c-d11b1f220141")
    @Override
    public boolean isValid() {
        return this.descriptor != null && this.descriptor.isValid();
    }

}
