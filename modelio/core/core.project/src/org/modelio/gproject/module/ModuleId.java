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

package org.modelio.gproject.module;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;

/**
 * Module identifier.
 * <p>
 * A module is identified by its name and its version.
 */
@objid ("54851cec-f1ee-11e1-8543-001ec947ccaf")
public class ModuleId {
    @objid ("2d27b783-f2b0-11e1-8543-001ec947ccaf")
    private String name;

    @objid ("b370f314-f27f-11e1-8543-001ec947ccaf")
    private Version version;

    /**
     * Initialize
     * 
     * @param name module name
     * @param version module version
     */
    @objid ("b370f315-f27f-11e1-8543-001ec947ccaf")
    public ModuleId(String name, Version version) {
        this.name = name;
        this.version = version;
    }

    /**
     * @return the module name
     */
    @objid ("b370f319-f27f-11e1-8543-001ec947ccaf")
    public String getName() {
        return this.name;
    }

    /**
     * @return the module version
     */
    @objid ("b370f31d-f27f-11e1-8543-001ec947ccaf")
    public Version getVersion() {
        return this.version;
    }

    @objid ("b3735569-f27f-11e1-8543-001ec947ccaf")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

    @objid ("b373556e-f27f-11e1-8543-001ec947ccaf")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ModuleId other = (ModuleId) obj;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.version == null) {
            if (other.version != null)
                return false;
        } else if (!this.version.equals(other.version))
            return false;
        return true;
    }

}
