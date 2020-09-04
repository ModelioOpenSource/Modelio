/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("de64c82c-4d9f-462d-9cfe-01cd813a917a")
public interface IControl {
    @objid ("c695642e-430a-4b14-aa01-cc749ce4455d")
    int hashId();

    @objid ("6638575f-1e84-4fb6-9beb-fa23955727ac")
    IDiagnosticCollector run(IDiagnosticCollector diagnostic, MObject element);

    @objid ("6bb97fc5-1258-4904-bd77-6bf51f4bdafe")
    String getRuleId();

}
