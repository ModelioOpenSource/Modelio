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
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("a02279a2-cf77-4143-9bd0-398931cd28d7")
public interface IRule {
    @objid ("b203582c-d600-4a0c-8e19-d77cea198a7a")
    IControl getCreationControl(MObject element);

    @objid ("431fbb66-3c5f-47f1-8cd9-8da8236da8d3")
    IControl getMoveControl(IElementMovedEvent moveEvent);

    @objid ("1be8a60a-4486-4e17-8237-80a4e87a5520")
    String getRuleId();

    @objid ("aed0116c-6438-4f5a-bab9-4eae922c13c0")
    AuditSeverity getSeverity();

    @objid ("bc4dc240-8ef6-45e4-aa50-9f160761f71a")
    IControl getUpdateControl(MObject element);

    @objid ("32b3ee8f-e752-4dd0-9e8e-a6425a1859d9")
    void setSeverity(AuditSeverity severity);

    @objid ("802f950a-8586-4e1e-9d83-e87ba48e5972")
    IControl getDeleteControl(final MObject element);

}
