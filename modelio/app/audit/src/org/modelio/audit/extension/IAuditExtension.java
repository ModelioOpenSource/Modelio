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

package org.modelio.audit.extension;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.IAuditExecutionPlan;

@objid ("eb77dfe2-f95e-4084-8489-f36d4e862868")
public interface IAuditExtension {
    @objid ("5b594dab-747a-47ee-b73c-ba35cb9ca28e")
    IAuditExecutionPlan getExecutionPlan();

    @objid ("d0063ac9-d75a-43ba-8e7d-acde80a7c40d")
    IAuditConfigurationPlan getConfigurationPlan();

}
