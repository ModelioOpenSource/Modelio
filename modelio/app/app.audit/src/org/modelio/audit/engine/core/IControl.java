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

/**
 * Control that validates a {@link IRule}.
 * <p>
 * All controls must have {@link #equals(Object)} and {@link #hashCode()} implementation that avoid duplicate
 * entries when used as hash map key.
 */
@objid ("de64c82c-4d9f-462d-9cfe-01cd813a917a")
public interface IControl {
    /**
     * Run the control to validate the rule
     * @param diagnostic the diagnostic collector.
     * @param element the element to validate. The passed element is the one passed to {@link IRule#getCreationControl(MObject)}
     * {@link IRule#getDeleteControl(MObject)} or {@link IRule#getUpdateControl(MObject)}.
     * @return the same diagnostic collector.
     */
    @objid ("6638575f-1e84-4fb6-9beb-fa23955727ac")
    IDiagnosticCollector run(IDiagnosticCollector diagnostic, MObject element);

    /**
     * @return the rule identifier
     */
    @objid ("6bb97fc5-1258-4904-bd77-6bf51f4bdafe")
    String getRuleId();

    @objid ("67767f37-76c6-4d6f-9d49-51b6699d32ba")
    @Override
    boolean equals(Object other);

    @objid ("f9bb4031-3dd5-46a8-85e3-7622960fafdc")
    @Override
    int hashCode();
}

