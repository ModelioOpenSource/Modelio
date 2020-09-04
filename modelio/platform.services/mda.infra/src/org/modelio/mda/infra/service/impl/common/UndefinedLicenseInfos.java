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

package org.modelio.mda.infra.service.impl.common;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.license.ILicenseInfos;

@objid ("c359952a-6f33-4b47-9986-ac739b63c8ad")
public class UndefinedLicenseInfos implements ILicenseInfos {
    @objid ("8471df5a-819c-42f5-8934-76247d053763")
    @Override
    public String getType() {
        return "UNDEFINED";
    }

    @objid ("018c1210-7723-4cc4-a36b-92f8fa729866")
    @Override
    public Date getDate() {
        return null;
    }

    @objid ("5ee58421-a1a6-49e2-a672-bf5fad85fe4a")
    @Override
    public Status getStatus() {
        return Status.UNDEFINED;
    }

}
