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

package org.modelio.audit.service;

import java.io.File;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.AuditEngine;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Audit service interface.
 */
@objid ("1e542651-7add-4b30-b9c4-ae3ead393965")
public interface IAuditService {
    /**
     * Read again the configuration files, compute the audit plan applies it to the engine.
     */
    @objid ("43b57340-a04d-444f-a456-908d85a77193")
    void restart();

    /**
     * Apply the given configuration, saves it in the current configuration file and {@link #restart() restart} the audit service.
     * @param prefModel the audit model to apply
     */
    @objid ("fe7c9451-88b0-4a7f-b6e1-c02c9c808f89")
    void apply(AuditConfigurationModel prefModel);

    /**
     * Audit an element.
     * @param e a model object
     * @param jobId an audit job identifier for reporting
     */
    @objid ("9a8f5fc1-ee1d-4670-b9b3-4df83b06f132")
    void checkElement(MObject e, String jobId);

    /**
     * @return the audit engine.
     */
    @objid ("dd8c293a-2b08-47e8-8776-3c9c51562802")
    AuditEngine getAuditEngine();

    /**
     * Get a copy of the audit configuration model.
     * @return the audit configuration model.
     */
    @objid ("a51a2a06-08fa-4da9-ab01-0c07e78973de")
    AuditConfigurationModel getConfigurationModel();

    /**
     * Get the configuration file used by the audit service.
     * <p>
     * The returned file may be the file defined by {@link #setConfigurationFile(File)},
     * or the default project configuration file.
     * @return the used configuration file.
     */
    @objid ("84a5d1e6-f9d2-4b1a-a410-50125b1ddf2b")
    File getConfigurationFile();

    /**
     * @return the audit configuration factory settings.
     */
    @objid ("45452555-33cc-45c5-8787-9db84f5ff8de")
    AuditConfigurationModel getFactorySettings();

    /**
     * Set the audit configuration file to use.
     * <p>
     * This restart the audit service.
     * @param confFile the new audit configuration file.
     */
    @objid ("b84226bc-922b-4028-8f97-eeb0ee014bd6")
    void setConfigurationFile(File confFile);

    /**
     * Audit an hierarchy of elements.
     * @param selection a model object or a selection of model object
     * @param jobId an audit job identifier for reporting
     */
    @objid ("30ec12a7-aead-4e36-accb-6d726f8e742a")
    void checkElementTree(List<MObject> selection, String jobId);

    /**
     * Register a listener for Audit Status
     * @param monitor Listener interface to add
     */
    @objid ("9fcd5a4b-f765-49c7-90ae-3fd58a747927")
    void addAuditMonitor(IAuditMonitor monitor);

    /**
     * remove a listener for Audit Status
     * @param monitor Listener interface to remove
     */
    @objid ("6ca00ed1-535b-45cc-8d81-8281e9463d0b")
    void removeAuditMonitor(IAuditMonitor monitor);

    /**
     * Interupt an audit
     * @param jobId an audit job identifier for reporting
     */
    @objid ("05bb9b0d-bcc9-4c09-af25-59e404edfef1")
    void interuptCheck(String jobId);

}
