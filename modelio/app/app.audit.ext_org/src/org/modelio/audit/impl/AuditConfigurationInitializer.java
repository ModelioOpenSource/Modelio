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
package org.modelio.audit.impl;

import java.io.File;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.modelio.audit.service.IAuditService;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.core.events.ModelioEventTopics;

/**
 * This class is used an e4 processor and is in charge of initializing the {@link IAuditService}.
 */
@objid ("acb3c125-7bd8-48f7-b91d-5e42d1955bcb")
public class AuditConfigurationInitializer {
    @objid ("2f1aed9b-6c3e-48ee-9062-27846c90cbdb")
    private static final String PROJECT_AUDIT_CONF_PATH = ".config/audit/auditconfiguration.properties";

    @objid ("1f58b411-17ad-4683-a978-1a42e19fd68a")
    private static AuditConfigurationInitializer INSTANCE;

    /**
     * This method is called during project opening.
     * <p>
     * It initializes the audit configuration file.
     * </p>
     */
    @objid ("0eb17856-34bc-4678-bac6-4375b6c81170")
    @Inject
    @Optional
    void onProjectOpening(@UIEventTopic (ModelioEventTopics.PROJECT_OPENING) final GProject openedProject, IAuditService auditService) {
        // Set project configuration file
        final File projFile = openedProject.getProjectFileStructure().getProjectDataPath().resolve(AuditConfigurationInitializer.PROJECT_AUDIT_CONF_PATH).toFile();
        auditService.setConfigurationFile(projFile);
        
    }

    @objid ("1be96549-a76b-473a-94f7-072e1b87b8e5")
    @Execute
    void execute() {
        // Store current instance to avoid garbage collecting
        AuditConfigurationInitializer.INSTANCE = this;
        
    }

    /**
     * This method is called during project closing.
     * <p>
     * It empties the audit configuration file.
     * </p>
     */
    @objid ("4f290d46-5032-494d-846d-3a89fe33ea01")
    @SuppressWarnings ("unused")
    @Inject
    @Optional
    void onProjectClosing(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final GProject closingProject, IAuditService auditService) {
        auditService.setConfigurationFile(null);
    }

}
