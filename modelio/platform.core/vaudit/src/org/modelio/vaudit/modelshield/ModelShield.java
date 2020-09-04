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

package org.modelio.vaudit.modelshield;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vaudit.modelshield.internal.ErrorReport;
import org.modelio.vcore.session.api.transactions.ITransactionValidator;
import org.modelio.vcore.session.impl.transactions.Transaction;
import org.modelio.vcore.smkernel.IllegalModelManipulationException;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;

/**
 * ModelShield provides the functionalities to check a single {@link MObject
 * model object} or a whole {@link Transaction transaction}. It handles a list
 * of {@link IProtectionAgent agents} and a list of {@link IErrorReportListener
 * error reports listeners}.
 */
@objid ("002807ec-0000-0007-0000-000000000000")
public class ModelShield {
    /**
     * {@link IllegalModelManipulationException} error code when ModelShield
     * find model errors.
     */
    @objid ("3822ff21-4627-4f22-a9df-eb6f89805345")
    public static final int MODELSHIELD_ERROR = -1;

    @objid ("002804f8-0000-0620-0000-000000000000")
    private final List<IErrorReportListener> diagnosticListeners = new ArrayList<>();

    @objid ("6cd94313-3f9c-11de-b55a-001ec947cd2a")
    private final Map<String, IProtectionAgent> agents = new HashMap<>();

    /**
     * Add a protection agent.
     * @param agent a protection agent.
     */
    @objid ("5edd4230-2b5b-11de-b561-001ec947cd2a")
    public void addAgent(final IProtectionAgent agent) {
        this.agents.put(agent.getId(), agent);
    }

    /**
     * Remove a protection agent.
     * @param agent a protection agent.
     */
    @objid ("25146e1e-721e-11de-9c55-0014222a9f79")
    public void removeAgent(final IProtectionAgent agent) {
        this.agents.remove(agent.getId());
    }

    /**
     * Add a diagnostic listener.
     * @param listener a diagnostic listener.
     */
    @objid ("002804f8-0000-064a-0000-000000000000")
    public void addDiagnosticListener(IErrorReportListener listener) {
        this.diagnosticListeners.add(listener);
    }

    /**
     * Remove a diagnostic listener
     * @param listener a diagnostic listener.
     */
    @objid ("002804f8-0000-0647-0000-000000000000")
    public void removeDiagnosticListener(IErrorReportListener listener) {
        this.diagnosticListeners.remove(listener);
    }

    /**
     * Check for model errors on a model object.
     * @param anObject a model object to check.
     * @return the check report.
     */
    @objid ("002807ec-0000-0008-0000-000000000000")
    public IErrorReport check(MObject anObject) {
        IErrorReport diagnostic = new ErrorReport();
        // checking an object consists in
        // - running the registered agents
        // - collect the diagnostics
        
        for (IProtectionAgent agent : this.agents.values()) {
            if (agent.isInScope(anObject)) {
                agent.check(anObject, diagnostic);
            }
        }
        
        fireDiagnostic(diagnostic);
        return diagnostic;
    }

    @objid ("002804f8-0000-06e3-0000-000000000000")
    private void fireDiagnostic(IErrorReport diagnostic) {
        if (diagnostic.getEntries().size() > 0) {
            for (IErrorReportListener listener : this.diagnosticListeners) {
                listener.onCommitDiagnostic(diagnostic);
            }
        }
    }

    /**
     * Check a transaction for model errors.
     * @param transaction the transaction to check.
     * @return the check report.
     */
    @objid ("002807ec-0000-000a-0000-000000000000")
    public IErrorReport check(final Transaction transaction) {
        IErrorReport diagnostic = new ErrorReport();
        // checking a transaction consists in
        // - running the registered agents
        // - collecting the diagnostics
        for (IProtectionAgent agent : this.agents.values()) {
            agent.check(transaction, diagnostic);
        }
        
        fireDiagnostic(diagnostic);
        return diagnostic;
    }

    @objid ("77a7b6d9-cbb1-4c9a-aaba-2eaecbd51072")
    public ITransactionValidator createTransationValidator() {
        return new ModelShieldTransactionValidator(this);
    }

    @objid ("002804f8-0000-0579-0000-000000000000")
    private static class ModelShieldTransactionValidator implements ITransactionValidator {
        @objid ("01f40340-0000-694f-0000-000000000000")
        private final ModelShield modelShield;

        @objid ("01f40340-0000-6958-0000-000000000000")
        public ModelShieldTransactionValidator(final ModelShield modelShield) {
            this.modelShield = modelShield;
        }

        @objid ("005e775c-8ca4-1033-829a-001ec947cd2a")
        @Override
        public void validate(Transaction currentTransaction) {
            IErrorReport report = this.modelShield.check(currentTransaction);
            
            if (report.isFailed())
                throw new IllegalModelManipulationException(MODELSHIELD_ERROR, null, report);
        }

    }

}
