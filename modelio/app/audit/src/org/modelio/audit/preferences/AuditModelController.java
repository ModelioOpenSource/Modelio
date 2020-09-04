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

package org.modelio.audit.preferences;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.modelio.audit.engine.core.IAuditExecutionPlan;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.extension.IAuditConfigurationPlan;
import org.modelio.audit.extension.IAuditExtension;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.preferences.model.AuditConfigurationModel;
import org.modelio.audit.preferences.model.AuditRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.core.rcp.extensionpoint.ExtensionPointContributionManager;

/**
 * Audit model controller.
 */
@objid ("8c2e1d6c-51c4-4a9c-bde8-eaf399b226a1")
public class AuditModelController {
    @objid ("818cfa2a-9227-46a3-8241-3660436d87cf")
    private static final String AUDIT_EXTENSION_ID = "org.modelio.audit.extensions";

    @objid ("31db854e-331d-42db-b57f-085427670dd2")
    private final AuditConfigurationModel model;

    @objid ("1bd668a5-bf4a-44f9-b240-976b16798095")
    private final Properties defaultConf;

    @objid ("36c96b3b-5b41-45ce-a1a4-aa5d05460b79")
    private final List<IAuditExtension> auditExtensions;

    /**
     * Initialize a new controller for a new model.
     */
    @objid ("9845f1b0-12d0-4a05-a341-f4d154f7f65b")
    public AuditModelController() {
        this.auditExtensions = loadAuditExtensions();
        this.model = new AuditConfigurationModel(new AuditMasterConfigurationPlan(getSubConfigurationPlans()));
        this.defaultConf = new Properties();
    }

    /**
     * Creates a new controller that handles an existing model.
     * 
     * @param model the model to handle.
     */
    @objid ("3dc46443-0cf9-4c55-a673-a59a8f2215fc")
    public AuditModelController(final AuditConfigurationModel model) {
        this.auditExtensions = loadAuditExtensions();
        this.model = model;
        this.defaultConf = new Properties();
    }

    /**
     * Get the controlled audit model.
     * 
     * @return the audit model.
     */
    @objid ("d60d9e45-6cbc-45b8-aaad-8120a90adf9b")
    public AuditConfigurationModel getModel() {
        return this.model;
    }

    /**
     * Write the rules configurations in a {@link Properties}.
     * <p>
     * If the given properties default already contain the same configuration for a rule,
     * the given properties are not updated.
     * 
     * @param ret rules configuration.
     */
    @objid ("ff208ef8-0091-4256-bef9-dbfa30a090df")
    private void writeConfiguration(final Properties ret) {
        for (final AuditRule ruleEntry : this.model.getRules()) {
            if (ruleEntry.getImplClass() != null && ruleEntry.getSeverity() != null) {
                final String sstatus = ruleEntry.isEnabled() ? "enabled" : "disabled";
                final String value = sstatus + "," + ruleEntry.getSeverity().identifier();
                if (this.defaultConf == null || !value.equals(this.defaultConf.getProperty(ruleEntry.getId()))) {
                    ret.setProperty(ruleEntry.getId(), value);
                }
            } else {
                Audit.LOG.debug(ruleEntry.getId() + " incomplete: driver=" + ruleEntry.getImplClass() + ", severity=" + ruleEntry.getSeverity() + ", enabled=" + ruleEntry.isEnabled());
            }
        }
    }

    @objid ("4e1956b2-07e6-414a-ac21-85717e4fb848")
    private void load(final File f, final Properties ret) throws IOException {
        try (final BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));) {
            ret.load(is);
        }
    }

    /**
     * Writes the audit model configuration in the configuration file.
     * <p>
     * Rules that have the default configuration are not written in the file.
     * 
     * @param file the configuration file.
     * @throws java.io.IOException in case of I/O error.
     */
    @objid ("c811d837-5e37-4f7e-b6a0-aa830c69569f")
    public void writeConfiguration(final File file) throws IOException {
        final Properties ret = new Properties(this.defaultConf);
        
        writeConfiguration(ret);
        
        // Make sure the parent directory exists
        if (!file.getParentFile().isDirectory()) {
            file.getParentFile().mkdirs();
        }
        
        // Write configuration file
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));) {
            ret.store(out, "Audit configuration file.\nFormat: ruleid = enabled|disabled|obsolete,tip|warning|error");
        }
    }

    /**
     * Read an audit configuration from a property table with the following format:
     * 
     * <pre>
     * ruleid = enabled|disabled|obsolete,tip|warning|error
     * </pre>
     * 
     * @param conf the configuration
     */
    @objid ("a356e9ef-97f4-46f4-b600-1eb4d9e39b31")
    private void applyAuditConfiguration(final Properties conf) {
        for (final Entry<Object, Object> entry : conf.entrySet()) {
            final String ruleId = (String) entry.getKey();
            final String[] v = ((String) entry.getValue()).split(",");
        
            if (v.length == 2) {
                AuditRule rule = this.model.get(ruleId);
                if (rule == null) {
                    rule = new AuditRule(ruleId, null, false, null);
                    this.model.add(rule);
                }
        
                try {
                    final String sstate = v[0];
                    final String sseverity = v[1];
                    rule.setEnabled("enabled".equals(sstate));
                    rule.setSeverity(AuditSeverity.fromIdentifier(sseverity));
                } catch (final IllegalArgumentException e) {
                    Audit.LOG.warning("Invalid rule configuration:" + entry.toString());
                }
            } else {
        
                Audit.LOG.warning("Invalid rule configuration:" + entry.toString());
            }
        }
    }

    /**
     * Load the given file as a the default configuration.
     * <p>
     * The default configuration is used on save to avoid writing the default configuration
     * in the target file.
     * 
     * @param defaultConfFile the default configuration file.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("696d0438-bd9e-4aaf-ad78-e71abc9adcd3")
    public void addDefaultConf(final File defaultConfFile) throws IOException {
        load(defaultConfFile, this.defaultConf);
        applyAuditConfiguration(this.defaultConf);
    }

    /**
     * Applies the rule enablement state and the severity of the given configuration
     * to the handled configuration.
     * <p>
     * Rules present in the given configuration and absent in this configuration are added to this configuration.
     * Rules absent in the given configuration are kept as is.
     * 
     * @param auditConfiguration the configuration to apply.
     */
    @objid ("8c9154eb-2c0e-43c8-92e2-7e0e9a7bdff9")
    public void applyAuditConfiguration(final AuditConfigurationModel auditConfiguration) {
        for (final AuditRule r : auditConfiguration.getRules()) {
            final AuditRule target = this.model.get(r.getId());
            if (target == null) {
                this.model.add(new AuditRule(r));
            } else {
                target.setEnabled(r.isEnabled());
                target.setSeverity(r.getSeverity());
            }
        }
    }

    /**
     * Applies the given audit configuration file.
     * 
     * @param file an audit configuration file.
     * @throws java.io.IOException in case of I/O error
     */
    @objid ("b03d9780-932c-452e-b03e-350e2dd09a16")
    public void applyAuditConfiguration(final File file) throws IOException {
        final Properties props = new Properties();
        load(file, props);
        
        applyAuditConfiguration(props);
    }

    @objid ("ed65ce4a-f49c-4ddf-89f4-e636511f42ea")
    public IAuditExecutionPlan createPlan() {
        // Always build a new plan
        final AuditMasterExecutionPlan masterPlan = new AuditMasterExecutionPlan(getSubExecutionPlans());
        
        // Update severity on runtime rules
        // FIXME this is awful...
        for (final IRule rule : masterPlan.getAllRules()) {
            final AuditRule ruleConfiguration = this.model.get(rule.getRuleId());
            if (ruleConfiguration != null && ruleConfiguration.isEnabled()) {
                rule.setSeverity(ruleConfiguration.getSeverity());
            } else {
                masterPlan.disableRule(rule);
            }
        }
        return masterPlan;
    }

    /**
     * Read the audit rules definitions from a property table with the following format:
     * 
     * <pre>
     * ruleid = category,driver.class.name
     * </pre>
     * <p>
     * The description bundle must contain rules description in keys formatted as <code>'ruleid.description'</code>
     * @param rulesFile the configuration
     * @param descBundle the rule description bundle
     * @throws java.io.IOException in case of I/O failure
     */
    @objid ("d210608e-dd31-47e7-aa6c-f6c93ed29ead")
    private List<IAuditExtension> loadAuditExtensions() {
        final List<IAuditExtension> result = new ArrayList<>();
        for (final IConfigurationElement e : new ExtensionPointContributionManager(AuditModelController.AUDIT_EXTENSION_ID).getExtensions("audit")) {
            try {
                result.add((IAuditExtension) e.createExecutableExtension("clazz"));
            } catch (CoreException | InvalidRegistryObjectException ex) {
                Audit.LOG.error("Unable to register audit plan declared by " + e.getContributor().getName());
                Audit.LOG.debug(ex);
            }
        }
        return result;
    }

    @objid ("eea75260-53d0-4781-89fb-beef13b2b45b")
    private List<IAuditExecutionPlan> getSubExecutionPlans() {
        final List<IAuditExecutionPlan> ret = new ArrayList<>();
        for (final IAuditExtension extension : this.auditExtensions) {
            ret.add(extension.getExecutionPlan());
        }
        return ret;
    }

    @objid ("76f22828-a602-4ab5-8e71-fbf8459d41ab")
    private List<IAuditConfigurationPlan> getSubConfigurationPlans() {
        final List<IAuditConfigurationPlan> ret = new ArrayList<>();
        for (final IAuditExtension extension : this.auditExtensions) {
            ret.add(extension.getConfigurationPlan());
        }
        return ret;
    }

}
