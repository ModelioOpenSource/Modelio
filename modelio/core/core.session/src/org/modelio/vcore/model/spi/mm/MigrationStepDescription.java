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

package org.modelio.vcore.model.spi.mm;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Basic implementation of {@link IMigrationStepDescription}
 * @author cma
 * @since 3.7.1
 */
@objid ("1f23904d-9a3b-4f73-8a02-29958c90675c")
public class MigrationStepDescription implements IMigrationStepDescription {
    @objid ("89fd67d4-3164-4d3e-934b-81f7621f3293")
    private final String desc;

    @objid ("afe66a20-dc86-4583-84a0-d1f8d3bf6b99")
    public MigrationStepDescription(String desc) {
        this.desc = desc;
    }

    @objid ("568c17f5-6531-43d8-9630-be6f87bdfeaa")
    @Override
    public String getStepDescription() {
        return this.desc;
    }

}
