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

package org.modelio.api.impl.audit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.impl.transaction.TransactionWrapper;
import org.modelio.api.modelio.audit.IAuditService;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.vaudit.modelshield.ModelShield;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;

/**
 * Audit services implementation.
 */
@objid ("7b896a97-cbe1-489c-9e2e-216b8f5e97a8")
public class AuditService implements IAuditService {
    @objid ("7bf4e185-8cc5-49bf-b2be-4690edc7910a")
    private ModelShield modelShield;

    @objid ("c0afab6e-71a2-4aaa-b4fb-4adacd53cecf")
    private org.modelio.audit.service.IAuditService auditService;

    @objid ("ceafbb91-661f-4213-8708-48444e52212f")
    @Override
    public boolean check(final MObject element) {
        IErrorReport report = this.modelShield.check(element);
        return report.isFailed();
    }

    @objid ("a4c673fc-eccc-4374-90fb-5d805d3774d2")
    @Override
    public void audit(final MObject element) {
        this.auditService.checkElement(element, null);
    }

    @objid ("732628f3-862b-434a-9b41-2a332dd80e3c")
    public AuditService(ModelShield modelShield, org.modelio.audit.service.IAuditService auditService) {
        this.modelShield = modelShield;
        this.auditService = auditService;
    }

    @objid ("437aa1a9-3057-4cae-a946-758c8aa30e7f")
    @Override
    public boolean check(ITransaction transaction) {
        IErrorReport report = this.modelShield.check((Transaction) ((TransactionWrapper) transaction).getWrapped());
        return report.isFailed();
    }

}
