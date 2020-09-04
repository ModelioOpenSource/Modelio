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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.audit.plugin.Audit;

/**
 * Definition of the severity of a failed rule.
 */
@objid ("0a0b3839-3b73-4b21-8e76-9293ffc730ff")
public enum AuditSeverity {
    /**
     * Success
     */
    AuditSuccess (Audit.I18N.getString("Audit.Severity.AuditSuccess"), "icons/idle.png", "none"),
    /**
     * Tip
     */
    AuditAdvice (Audit.I18N.getString("Audit.Severity.AuditAdvices"), "icons/advice.png", "tip"),
    /**
     * Warning
     */
    AuditWarning (Audit.I18N.getString("Audit.Severity.AuditWarnings"), "icons/warning.png", "warning"),
    /**
     * Error.
     */
    AuditError (Audit.I18N.getString("Audit.Severity.AuditErrors"), "icons/error.png", "error");

    @objid ("e0b1e5ac-53fa-4633-8a0e-37cef4a9d2b4")
    private String label;

    @objid ("91ec66b9-2fc2-41c2-94ac-58bdd432f062")
    private String identifier;

    @objid ("6b2f1909-1dfd-4250-bbec-c643f97613e3")
    private Image image;

    @objid ("ad9fdb86-b7ec-4df0-823b-f154bec40c13")
    private AuditSeverity(String label, String image, String identifier) {
        this.image = Audit.getImageDescriptor(image).createImage();
        this.label = label;
        this.identifier = identifier;
    }

    /**
     * @return the icon image
     */
    @objid ("45ae6e86-ccc1-48c5-bfbc-f83669bb47e9")
    public Image getImage() {
        return this.image;
    }

    /**
     * @return the user GUI label.
     */
    @objid ("a3e9ee2c-3a61-47ed-9ea3-7e930a6cb531")
    public String getLabel() {
        return this.label;
    }

    /**
     * Get all severities GUI labels.
     * @return severities GUI labels.
     */
    @objid ("7f116674-7e37-4ec9-ba9a-f53d71d66d81")
    public static String[] getRuntimeValues() {
        return new String[] { AuditSeverity.AuditAdvice.getLabel(), AuditSeverity.AuditWarning.getLabel(), AuditSeverity.AuditError.getLabel() };
    }

    /**
     * Get all severity icons.
     * @return all severity icons.
     */
    @objid ("ac48ee03-e231-4779-9586-ef1179093bc6")
    public static Image[] getRuntimeImages() {
        return new Image[] { AuditSeverity.AuditAdvice.getImage(), AuditSeverity.AuditWarning.getImage(), AuditSeverity.AuditError.getImage() };
    }

    /**
     * Find an AuditSeverity from its GUI label.
     * @param value a translated GUI label.
     * @return the matching severity or <code>null</code>.
     */
    @objid ("1b7791d0-fec6-4fb2-82ca-c24cbb22480a")
    public static AuditSeverity findByLabel(String value) {
        if (AuditSuccess.getLabel().equals(value)) {
            return AuditSuccess;
        } else if (AuditAdvice.getLabel().equals(value)) {
            return AuditAdvice;
        } else if (AuditWarning.getLabel().equals(value)) {
            return AuditWarning;
        } else if (AuditError.getLabel().equals(value)) {
            return AuditError;
        }
        return null;
    }

    /**
     * Find an AuditSeverity from the identifier used in configuration files.
     * @param v the audit identifier.
     * @return the found severity
     * @throws java.lang.IllegalArgumentException if the identifier does not match a severity.
     */
    @objid ("eeec0ede-b5f2-4684-b7a5-4f54a5be4fd8")
    public static AuditSeverity fromIdentifier(String v) throws IllegalArgumentException {
        for (AuditSeverity c : AuditSeverity.values()) {
            if (c.identifier.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Invalid severity:" + v);
    }

    /**
     * Get the identifier to use for persistence.
     * @return the enum identifier.
     */
    @objid ("5106b839-a2c8-40a7-a7d2-9560de63b2a7")
    public String identifier() {
        return this.identifier;
    }

}
