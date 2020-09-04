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

package org.modelio.app.project.core.modelshield;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.app.project.core.plugin.AppProjectCore;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vaudit.modelshield.IErrorReportListener;
import org.modelio.vaudit.modelshield.ModelShield;
import org.modelio.vaudit.modelshield.engine.CoreProtectionAgent;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IErrorReport;

/**
 * Utility class to setup the model shield that protects
 * the model against inconsistent model modifications.
 */
@objid ("e5070474-47cb-437a-bf42-48d70149f3cf")
public class ModelShieldController {
    /**
     * This method is called during a project opening.
     * 
     * @param openedProject the opened project
     */
    @objid ("82488fee-1dd5-11e2-82de-002564c97630")
    public static void onProjectOpening(final GProject openedProject) {
        ICoreSession coreSession = openedProject.getSession();
        
        // Create the core agent
        ModelShield shield = new ModelShield();
        shield.addAgent(new CoreProtectionAgent(coreSession));
        
        // Register the transaction validator
        coreSession.getTransactionSupport().setTransactionValidator(shield.createTransationValidator());
        
        // Create a default diagnostic listener
        shield.addDiagnosticListener(new IErrorReportListener() {
            @Override
            public void onCommitDiagnostic(final IErrorReport errors) {
                ErrorReportDialog.open(AppProjectCore.I18N.getString("CoreAudit.report.title"), AppProjectCore.I18N.getString("CoreAudit.report.message"), errors);
            }
        });
    }

    /**
     * Called when a project is closed.
     * On session close un-reference the modeling session.
     * 
     * @param closedProject the closed project
     */
    @objid ("82488ff4-1dd5-11e2-82de-002564c97630")
    public static void onProjectClosing(final GProject closedProject) {
        ICoreSession session = closedProject.getSession();
        if (session != null) {
            session.getTransactionSupport().setTransactionValidator(null);
        }
    }

}
