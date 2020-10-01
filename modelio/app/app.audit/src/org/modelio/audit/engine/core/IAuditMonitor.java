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

package org.modelio.audit.engine.core;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d9ec0e60-5314-4263-8f9f-fdc8f6c8a21d")
public interface IAuditMonitor {
    @objid ("b1daa558-1ec5-49cd-a8a9-55af1b186838")
    void status(AuditRunnerStatus status, int queueSize);

}
