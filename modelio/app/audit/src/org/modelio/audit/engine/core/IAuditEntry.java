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

package org.modelio.audit.engine.core;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("72349e77-877d-4bb8-b5f6-232ca3ef2a4b")
public interface IAuditEntry {
    @objid ("2ce3096b-6147-432e-822e-b8fe2c8a9eec")
    String getRuleId();

    @objid ("a44bf57b-a098-4b92-ba7c-248bd3d35a2f")
    AuditSeverity getSeverity();

    @objid ("90f7fa27-7ec5-44bd-b8b9-e9ed48775ae4")
    MObject getElement();

    @objid ("e0859023-36f3-478e-9f69-b8361906090d")
    List<Object> getLinkedObjects();

    @objid ("5d6d1046-31d5-4637-bb4e-240c2bfdefab")
    long getTimestamp();

    @objid ("0ad0ed60-5d83-4619-aea9-c541b91cc798")
    String getJobId();

    @objid ("0c659b34-8157-4f6c-a45d-e8d919dcabc8")
    void setJobId(String jobId);

    @objid ("86e00265-a7a6-4161-b89c-320cbe9ffb15")
    void setSeverity(AuditSeverity severity);

}
