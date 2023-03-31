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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("556e1d8b-2c05-4654-aecc-5cf9bfcebdb0")
public interface IAuditDiagnostic {
    @objid ("0ce45ee3-f8b6-425c-937c-2ca5811ba728")
    List<IAuditEntry> getEntries();

    @objid ("1ad3ca04-04b9-46a0-b0e5-ec42f1ffb580")
    List<IAuditEntry> getEntries(String jobId);

    @objid ("46a9577a-9e84-4795-aaa2-e968e28ead95")
    int getErrorCount();

    @objid ("41dc34f1-2590-41a7-bc95-bd12f4645ec8")
    int getWarningCount();

    @objid ("457ba0d2-924e-4d53-857a-3e77b7c0a8be")
    int getTipCount();

    @objid ("da800349-56b5-4193-a6e0-f40cd373088f")
    void addAuditListener(IAuditListener listener);

    @objid ("bad199a9-0953-483a-807c-4f741b6cc9fd")
    void removeAuditListener(IAuditListener listener);
}

