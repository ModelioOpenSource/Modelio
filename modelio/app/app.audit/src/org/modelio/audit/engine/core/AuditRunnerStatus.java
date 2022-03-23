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

@objid ("4eb9ee1e-f264-4d24-b73d-b2a17bd241f8")
public enum AuditRunnerStatus {
    @objid ("9c26f1c4-a640-44c0-86bd-28b7e3abd248")
    TERMINATED,
    @objid ("90c9897d-85bd-4366-b424-f39b455a4fe7")
    SUSPENDED,
    @objid ("461609ca-ee39-4762-aef8-e080b9b3e0ba")
    IDLE,
    @objid ("20509f0b-babc-4c48-aafe-34d8d6c9019a")
    PROCESSING;

}
