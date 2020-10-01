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
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("578f0691-fee9-4dc1-bd98-8b9bd9752a25")
public abstract class AbstractControl implements IControl {
    @objid ("c7579749-b241-471b-aa63-6cc4afda6f5e")
    protected IRule rule;

    @objid ("710c73e9-3451-42bd-b712-d8b02dc02a61")
    public AbstractControl(IRule rule) {
        this.rule = rule;
    }

    @objid ("0faf5d9d-137f-4c7d-8978-b73ba08ef87b")
    @Override
    public int hashId() {
        return this.getClass().hashCode();
    }

    @objid ("45dbc326-5ed2-4a1d-82ef-7c953c93ba04")
    @Override
    public IDiagnosticCollector run(IDiagnosticCollector diagnostic, MObject element) {
        if (!element.isDeleted()) {
            doRun(diagnostic, element);
            return diagnostic;
        }
        return diagnostic;
    }

    @objid ("702a9252-1be3-4130-9bc3-d628351696b7")
    public abstract IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element);

    @objid ("a1d1b1af-5cf2-4945-a8a0-e2a603f81a66")
    @Override
    public String getRuleId() {
        return this.rule.getRuleId();
    }

}
