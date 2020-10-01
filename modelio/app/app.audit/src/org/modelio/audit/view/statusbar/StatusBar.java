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

package org.modelio.audit.view.statusbar;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.plugin.Audit;
import org.modelio.platform.ui.UIColor;

/**
 * StatusBar controller.
 * <p>
 * Merges in one class the status bar SWT component and its controller.
 * <p>
 * TODO : Images are not disposed with the status bar.
 */
@objid ("a68875a0-fb29-41bb-b23f-a5f67438eba9")
public class StatusBar {
    @objid ("f084b7d8-0df4-417e-9f36-6476c9b0e7f8")
    private String processingStatusTooltip;

    @objid ("4f6daa1f-992b-4868-be37-df91abca00f1")
    private String suspendedStatusTooltip;

    @objid ("3fddfbf1-ca91-4d9b-87b7-53d9a48e6eaa")
    private String idleStatusTooltip;

    @objid ("bdcf8cba-d172-4193-a690-b2ec3481ce9e")
    private AuditRunnerStatus lastRefreshedstatus = null;

    @objid ("ba0a9fb8-9f69-41f0-a235-3b6f9546458a")
    private int lastRefreshedCount;

    @objid ("b2145247-16ea-4328-ab04-f5d6bcca659a")
    private String nErrorsSuffix;

    @objid ("221536af-64c6-4127-9ec7-6f699f8dd704")
    private String nWarningsSuffix;

    @objid ("60711bf5-9dbe-48df-ba6c-5a27e4b96339")
    private String nAdvicesSuffix;

    @objid ("3ba6ad8b-851b-4bad-8b81-466be77108c8")
    private final Label runnerStatusLabel;

    @objid ("eb325501-a568-4dd1-8d2c-7a51d838f975")
    private Image processingStatusIcon;

    @objid ("45b11268-1f1e-433e-b581-24ddf4a115c3")
    private Image suspendedStatusIcon;

    @objid ("95bcaf17-1107-4815-afb4-d56d26204999")
    private Image idleStatusIcon;

    @objid ("ec5304b4-a09d-4840-92dc-348410d87eae")
    private final Composite container;

    @objid ("080b5cbb-9e09-4181-bab1-539e4249f8c2")
    private final Label auditSummaryLabel;

    @objid ("e855ae76-01c1-438f-8fe0-a0a48c4095c1")
    private Color errorColor;

    @objid ("197efcf1-4ee6-40e8-8cc9-821ddc33dd8b")
    private Color warningColor;

    @objid ("2d0d1849-8c85-486b-89fe-394647bdc5f1")
    private Color adviceColor;

    @objid ("a12c5011-19bb-45c5-ab8f-0f39792ed40c")
    private Label scopeLabel;

    @objid ("ecb44454-afba-4d8b-af26-d7c0c6dcaaf7")
    private int statusAnimationCounter = 0;

    @objid ("b28e9011-0c9e-4b82-bae2-2cc7d9177ed6")
    private Image processingStatusIcon2;

    @objid ("64a6515c-ea48-4605-bc9d-df16810e67e4")
    private StringBuilder summaryMessage = new StringBuilder();

    @objid ("4cce02d2-9071-42c9-9073-8ff4ec7ee4a8")
    private ProgressBar2 progressBar;

    /**
     * Initialize the status bar.
     * 
     * @param parent The parent composite.
     * @param style not used.
     */
    @objid ("860b222d-5fd2-4138-bd16-d12002c5d836")
    public StatusBar(Composite parent, int style) {
        allocateResources();
        
        this.container = new Composite(parent, style);
        final GridLayout layout = new GridLayout(5, false);
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 0;
        this.container.setLayout(layout);
        
        this.container.addDisposeListener((e) -> StatusBar.this.freeAllocatedResources());
        
        //
        this.scopeLabel = new Label(this.container, SWT.NONE);
        this.scopeLabel.setText("...");
        GridData rd = new GridData(SWT.FILL, SWT.CENTER, true, true);
        this.scopeLabel.setAlignment(SWT.LEFT);
        this.scopeLabel.setLayoutData(rd);
        
        //
        this.auditSummaryLabel = new Label(this.container, SWT.NONE);
        this.auditSummaryLabel.setText("...");
        rd = new GridData(SWT.FILL, SWT.CENTER, true, true);
        this.auditSummaryLabel.setAlignment(SWT.CENTER);
        this.auditSummaryLabel.setLayoutData(rd);
        
        // Queue size labeled progress bar
        this.progressBar = new ProgressBar2(this.container, SWT.RIGHT_TO_LEFT) {
            @Override
            public String getSelectionLabel() {
                return String.format("%d", getSelection());
            }
        };
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(100000);
        this.progressBar.setSelection(0);
        this.progressBar.setForeground(UIColor.SWT_LIST_SELECTION);
        rd = new GridData(SWT.FILL, SWT.FILL, true, true);
        rd.heightHint = this.progressBar.getSize().y;
        this.progressBar.setLayoutData(rd);
        
        // Runner status 'LED-like' display
        this.runnerStatusLabel = new Label(this.container, SWT.NONE);
        this.runnerStatusLabel.setImage(this.idleStatusIcon);
        rd = new GridData(SWT.FILL, SWT.FILL, false, false);
        this.runnerStatusLabel.setLayoutData(rd);
        
        // TODO : This method shouldn't have to be called here:
        // Adding this status bar as listener should fire its status(...) method.
        doRefreshStatus(AuditRunnerStatus.IDLE, 0);
    }

    /**
     * Get the status bar SWT Composite.
     * <p>
     * The returned Composite is a child of the composite given to the constructor.
     * 
     * @return the status bar composite.
     */
    @objid ("024af934-42cc-49e8-b83a-422f58a36662")
    public Composite getComposite() {
        return this.container;
    }

    /**
     * Update the status bar from last audit diagnostic. MUST BE CALLED from the UI Thread
     * 
     * @param auditDiagnostic the last audit diagnostic.
     */
    @objid ("8c4a37ba-abe8-4a2f-ac2f-cdd90b385018")
    public void doRefreshDiagnostic(final IAuditDiagnostic auditDiagnostic, String scope) {
        int nErrors = auditDiagnostic.getErrorCount();
        int nWarnings = auditDiagnostic.getWarningCount();
        int nTips = auditDiagnostic.getTipCount();
        
        if ((nErrors + nWarnings + nTips) != this.lastRefreshedCount) {
            this.summaryMessage.setLength(0);
            this.summaryMessage.append(auditDiagnostic.getErrorCount());
            this.summaryMessage.append(this.nErrorsSuffix);
            this.summaryMessage.append(auditDiagnostic.getWarningCount());
            this.summaryMessage.append(this.nWarningsSuffix);
            this.summaryMessage.append(auditDiagnostic.getTipCount());
            this.summaryMessage.append(this.nAdvicesSuffix);
        
            this.auditSummaryLabel.setText(this.summaryMessage.toString());
        
            if (nErrors > 0) {
                this.auditSummaryLabel.setForeground(this.errorColor);
            } else if (nWarnings > 0) {
                this.auditSummaryLabel.setForeground(this.warningColor);
            } else {
                this.auditSummaryLabel.setForeground(this.adviceColor);
            }
            this.lastRefreshedCount = nErrors + nWarnings + nTips;
        }
        
        this.scopeLabel.setText(scope);
    }

    /**
     * Update the status view from the given parameters. MUST BE CALLED from the UI Thread
     * 
     * @param status The audit status
     * @param queueSize The queue size
     */
    @objid ("f3b2425b-7220-431c-9cb0-ad1715864e5d")
    public void doRefreshStatus(final AuditRunnerStatus status, final int queueSize) {
        this.progressBar.setSelection(queueSize);
        
        if (status != this.lastRefreshedstatus || status == AuditRunnerStatus.PROCESSING) {
            statusAnimationCounter++;
            switch (status != null ? status : AuditRunnerStatus.IDLE) {
            case IDLE:
                this.runnerStatusLabel.setImage(this.idleStatusIcon);
                this.runnerStatusLabel.setToolTipText(this.idleStatusTooltip);
                break;
            case PROCESSING:
                this.runnerStatusLabel.setImage((statusAnimationCounter % 2 == 0) ? this.processingStatusIcon : this.processingStatusIcon2);
                this.runnerStatusLabel.setToolTipText(this.processingStatusTooltip);
                this.runnerStatusLabel.update();
                break;
            case SUSPENDED:
                this.runnerStatusLabel.setImage(this.suspendedStatusIcon);
                this.runnerStatusLabel.setToolTipText(this.suspendedStatusTooltip);
                break;
            case TERMINATED:
                this.runnerStatusLabel.setImage(null);
                this.runnerStatusLabel.setToolTipText(Audit.I18N.getString("Audit.Status.terminated"));
                break;
            default:
                break;
            }
        
            this.lastRefreshedstatus = status;
        }
    }

    @objid ("1ed3255c-1647-4be2-9b9c-9749a0d96813")
    private void allocateResources() {
        this.processingStatusIcon = createImageDescriptor("icons/processing.png").createImage();
        this.processingStatusIcon2 = createImageDescriptor("icons/processing2.png").createImage();
        this.processingStatusTooltip = Audit.I18N.getString("Audit.Status.processing");
        this.suspendedStatusIcon = createImageDescriptor("icons/suspended.png").createImage();
        this.suspendedStatusTooltip = Audit.I18N.getString("Audit.Status.suspended");
        this.idleStatusIcon = createImageDescriptor("icons/idle.png").createImage();
        this.idleStatusTooltip = Audit.I18N.getString("Audit.Status.terminated");
        
        this.nErrorsSuffix = " " + Audit.I18N.getMessage("Audit.Status.NbErrorsSuffix") + ", ";
        this.nWarningsSuffix = " " + Audit.I18N.getMessage("Audit.Status.NbWarningsSuffix") + ", ";
        this.nAdvicesSuffix = " " + Audit.I18N.getMessage("Audit.Status.NbAdvicesSuffix");
        
        this.errorColor = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
        this.warningColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
        this.adviceColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
    }

    /**
     * Create an image descriptor from a path relative to the Audit plugin.
     * 
     * @param path a relative path.
     * @return the image descriptor.
     */
    @objid ("7d3f3c6e-5867-45d4-aad1-5ea7ba3047b0")
    private ImageDescriptor createImageDescriptor(final String path) {
        URL bitmapUrl = FileLocator.find(Platform.getBundle(Audit.PLUGIN_ID), new Path(path), null);
        return ImageDescriptor.createFromURL(bitmapUrl);
    }

    @objid ("681cb655-8d59-4a92-bee0-b18be43dd641")
    private void freeAllocatedResources() {
        if (this.processingStatusIcon != null) {
            this.processingStatusIcon.dispose();
            this.processingStatusIcon = null;
        }
        if (this.processingStatusIcon2 != null) {
            this.processingStatusIcon2.dispose();
            this.processingStatusIcon2 = null;
        }
        if (this.suspendedStatusIcon != null) {
            this.suspendedStatusIcon.dispose();
            this.suspendedStatusIcon = null;
        }
        if (this.idleStatusIcon != null) {
            this.idleStatusIcon.dispose();
            this.idleStatusIcon = null;
        }
    }

}
