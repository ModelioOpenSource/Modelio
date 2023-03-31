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
import java.util.Arrays;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.modelio.app.ui.statusbar.IStatusBarContribution;
import org.modelio.audit.engine.core.AuditRunnerStatus;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditListener;
import org.modelio.audit.engine.core.IAuditMonitor;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.IAuditService;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIThreadRunner;

/**
 * StatusBar controller.
 * <p>
 * Merges in one class the status bar SWT component and its controller.
 * <p>
 */
@objid ("9d3a73d0-801f-442b-886b-d2a5514483f8")
public class AuditStatusContributor implements IStatusBarContribution, IAuditMonitor, IAuditListener {
    @objid ("751e7a6f-20f7-44dd-9260-275a2f6e13a7")
    private AuditRunnerStatus lastRefreshedstatus = null;

    @objid ("8f3b2e8a-0abd-4fe9-8714-5c7f5dcb4305")
    private int lastRefreshedCount;

    @objid ("74f63a9c-6cdc-4605-adec-5da34b5f22d6")
    private int statusAnimationCounter = 0;

    @objid ("d152591b-bf62-497f-b434-13d8fdcdd2b0")
    private Resources resources;

    @objid ("9a73bff9-f591-4b0d-ad11-47db738f1e94")
    private Composite barContainer;

    @objid ("16749141-ffbc-402e-8cf4-72813ca21446")
    private Label runnerStatusIcon;

    @objid ("131a2e9f-fe0f-4399-9e49-b20beb8dd997")
    private Label errorsNumberLabel;

    @objid ("41b838dd-2936-4651-8f3d-cd522ffb88f2")
    private Label warningsNumberLabel;

    @objid ("9f5e7482-57f4-4c84-9033-a9991794ae12")
    private Label tipsNumberLabel;

    @objid ("184056a3-15af-40a8-941d-b89f14d802a5")
    private ProgressBar2 progressBar;

    @objid ("3ae50a19-7393-4796-a21a-566d3e42810e")
    @Inject
    IEventBroker eventBroker;

    @objid ("cee0e4bf-9f6b-464e-b92c-77494671d168")
    @Inject
    private IAuditService auditService;

    /**
     * Initialize the status bar.
     * @param parent The parent composite.
     */
    @objid ("883b78f2-31c7-4d7e-9472-3c9a591b0ea2")
    @Override
    public Control createControl(Composite parent) {
        this.resources = new Resources();
        this.barContainer = new Composite(parent, SWT.NONE);
        
        final GridLayout layout = new GridLayout(7, false);
        layout.verticalSpacing = 0;
        layout.horizontalSpacing = 2;
        layout.marginHeight = 0;
        this.barContainer.setLayout(layout);
        
        this.barContainer.addDisposeListener((e) -> AuditStatusContributor.this.resources.freeResources());
        
        //
        this.errorsNumberLabel = new Label(this.barContainer, SWT.NONE);
        this.errorsNumberLabel.setText("   ");
        GridData rd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.errorsNumberLabel.setAlignment(SWT.CENTER);
        this.errorsNumberLabel.setLayoutData(rd);
        this.errorsNumberLabel.setForeground(UIColor.RED);
        this.errorsNumberLabel.setToolTipText(Audit.I18N.getMessage("Audit.Status.nbErrors.tooltip"));
        
        new Label(this.barContainer, SWT.SEPARATOR).setLayoutData(new GridData(SWT.DEFAULT, 10));
        
        //
        this.warningsNumberLabel = new Label(this.barContainer, SWT.NONE);
        this.warningsNumberLabel.setText("   ");
        rd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.warningsNumberLabel.setAlignment(SWT.CENTER);
        this.warningsNumberLabel.setLayoutData(rd);
        this.warningsNumberLabel.setForeground(UIColor.ORANGE);
        new Label(this.barContainer, SWT.SEPARATOR).setLayoutData(new GridData(SWT.DEFAULT, 10));
        this.warningsNumberLabel.setToolTipText(Audit.I18N.getMessage("Audit.Status.nbWarnings.tooltip"));
        
        //
        this.tipsNumberLabel = new Label(this.barContainer, SWT.NONE);
        this.tipsNumberLabel.setText("   ");
        rd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.tipsNumberLabel.setAlignment(SWT.CENTER);
        this.tipsNumberLabel.setLayoutData(rd);
        this.tipsNumberLabel.setForeground(UIColor.BLUE);
        this.tipsNumberLabel.setToolTipText(Audit.I18N.getMessage("Audit.Status.nbTips.tooltip"));
        
        // Queue size labeled progress bar
        this.progressBar = new ProgressBar2(this.barContainer, SWT.RIGHT_TO_LEFT) {
            @Override
            public String getSelectionLabel() {
                int value = getSelection();
                if (value > 9999) {
                    return String.format("%dK", value / 1000);
                } else {
                    return String.format("%d", value);
                }
            }
        };
        this.progressBar.setMinimum(0);
        this.progressBar.setMaximum(100000);
        this.progressBar.setSelection(0);
        this.progressBar.setForeground(UIColor.SWT_LIST_SELECTION);
        rd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        rd.heightHint = this.progressBar.getSize().y;
        this.progressBar.setLayoutData(rd);
        this.progressBar.setToolTipText(Audit.I18N.getString("Audit.Status.nbTests.tooltip"));
        
        // Runner status 'LED-like' display
        this.runnerStatusIcon = new Label(this.barContainer, SWT.NONE);
        this.runnerStatusIcon.setImage(this.resources.idleStatusIcon);
        rd = new GridData(SWT.FILL, SWT.CENTER, false, false);
        this.runnerStatusIcon.setLayoutData(rd);
        
        // TODO : This method shouldn't have to be called here:
        // Adding this status bar as listener should fire its status(...) method.
        doRefreshStatus(AuditRunnerStatus.IDLE, 0);
        
        this.auditService.addAuditMonitor(this);
        this.auditService.getAuditEngine().getAuditDiagnostic().addAuditListener(this);
        return this.barContainer;
    }

    @objid ("728a4287-e4a8-447c-8f1d-88987e2fa451")
    private void fireStatusBarUpdate(List<Control> controls) {
        if (this.eventBroker != null) {
            this.eventBroker.post("org/modelio/app/ui/statusbar", controls);
        }
        
    }

    /**
     * Audit monitor callback: display audit activity
     */
    @objid ("f615751e-6739-4c79-b762-4c27a4e419b0")
    @Override
    public void status(AuditRunnerStatus status, int queueSize) {
        UIThreadRunner.asynExec(this.barContainer, () -> {
            doRefreshStatus(status, queueSize);
            fireStatusBarUpdate(Arrays.asList(this.runnerStatusIcon));
        });
        
    }

    /**
     * Audit listener callback: displays audit results
     */
    @objid ("7f998904-206e-4202-b581-ec092fe6d0bb")
    @Override
    public void auditModelChanged(IAuditDiagnostic auditDiagnostic) {
        UIThreadRunner.asynExec(this.barContainer, () -> {
            doRefreshDiagnostic(auditDiagnostic);
            fireStatusBarUpdate(Arrays.asList(this.errorsNumberLabel, this.warningsNumberLabel, this.tipsNumberLabel));
        });
        
    }

    /**
     * Get the status bar SWT Composite.
     * <p>
     * The returned Composite is a child of the composite given to the constructor.
     * @return the status bar composite.
     */
    @objid ("fb0b64ca-377f-4bb9-92b5-7b3ad825349e")
    public Composite getComposite() {
        return this.barContainer;
    }

    /**
     * Update the status bar from last audit diagnostic. MUST BE CALLED from the UI Thread
     * @param auditDiagnostic the last audit diagnostic.
     */
    @objid ("332e571f-5112-429a-b853-7fbff79b6a9f")
    private void doRefreshDiagnostic(final IAuditDiagnostic auditDiagnostic) {
        int nErrors = auditDiagnostic.getErrorCount();
        int nWarnings = auditDiagnostic.getWarningCount();
        int nTips = auditDiagnostic.getTipCount();
        
        if ((nErrors + nWarnings + nTips) != this.lastRefreshedCount) {
            this.errorsNumberLabel.setText(String.format("%4d", nErrors));
            this.warningsNumberLabel.setText(String.format("%4d", nWarnings));
            this.tipsNumberLabel.setText(String.format("%4d", nTips));
            this.lastRefreshedCount = nErrors + nWarnings + nTips;
        }
        
    }

    /**
     * Update the status view from the given parameters. MUST BE CALLED from the UI Thread
     * @param status The audit status
     * @param queueSize The queue size
     */
    @objid ("1de0da44-d82c-43fa-99f3-65a6b45d808d")
    private void doRefreshStatus(final AuditRunnerStatus status, final int queueSize) {
        this.progressBar.setSelection(queueSize);
        if (status != this.lastRefreshedstatus || status == AuditRunnerStatus.PROCESSING) {
            this.statusAnimationCounter++;
            switch (status != null ? status : AuditRunnerStatus.IDLE) {
            case IDLE:
                this.runnerStatusIcon.setImage(this.resources.idleStatusIcon);
                this.runnerStatusIcon.setToolTipText(this.resources.idleStatusTooltip);
                break;
            case PROCESSING:
                this.runnerStatusIcon.setImage((this.statusAnimationCounter % 2 == 0) ? this.resources.processingStatusIcon : this.resources.processingStatusIcon2);
                this.runnerStatusIcon.setToolTipText(this.resources.processingStatusTooltip);
                this.runnerStatusIcon.update();
                break;
            case SUSPENDED:
                this.runnerStatusIcon.setImage(this.resources.suspendedStatusIcon);
                this.runnerStatusIcon.setToolTipText(this.resources.suspendedStatusTooltip);
                break;
            case TERMINATED:
                this.runnerStatusIcon.setImage(null);
                this.runnerStatusIcon.setToolTipText(Audit.I18N.getString("Audit.Status.terminated"));
                break;
            default:
                break;
            }
        
            this.lastRefreshedstatus = status;
        }
        
    }

    @objid ("b02ef402-46d2-4549-a05b-1168e082f4cc")
    private static class Resources {
        @objid ("06256de7-479e-47be-aaba-4492dae6d919")
        private final String idleStatusTooltip = Audit.I18N.getString("Audit.Status.terminated");

        @objid ("27d0bd89-57b2-4a25-9918-360b3c0f45fe")
        private final String suspendedStatusTooltip = Audit.I18N.getString("Audit.Status.suspended");

        @objid ("6ddcc80a-20a4-46f2-b08b-a43d4c8302dd")
        private final String processingStatusTooltip = Audit.I18N.getString("Audit.Status.processing");

        @objid ("7c77776f-825b-49cd-ab23-ec75799773a7")
        private Image processingStatusIcon;

        @objid ("4f486539-4725-476e-bcaf-d9bacff5ab03")
        private Image processingStatusIcon2;

        @objid ("e3fb3c3a-b5db-442b-874f-692c4fbf5e87")
        private Image suspendedStatusIcon;

        @objid ("0695e158-f3bc-44aa-ba79-f15cd691a54a")
        private Image idleStatusIcon;

        @objid ("08f15c7e-0fdd-4608-a6f9-d1d682683011")
        public  Resources() {
            this.processingStatusIcon = createImageDescriptor("icons/processing.png").createImage();
            this.processingStatusIcon2 = createImageDescriptor("icons/processing2.png").createImage();
            this.suspendedStatusIcon = createImageDescriptor("icons/suspended.png").createImage();
            this.idleStatusIcon = createImageDescriptor("icons/idle.png").createImage();
            
        }

        @objid ("615f4570-0637-4a37-bfd9-7a83ef5f2011")
        private void freeResources() {
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

        /**
         * Create an image descriptor from a path relative to the Audit plugin.
         * @param path a relative path.
         * @return the image descriptor.
         */
        @objid ("907e5ce5-83b9-43dd-9ef0-91cc7ab8c2be")
        private ImageDescriptor createImageDescriptor(final String path) {
            URL bitmapUrl = FileLocator.find(Platform.getBundle(Audit.PLUGIN_ID), new Path(path), null);
            return ImageDescriptor.createFromURL(bitmapUrl);
        }

    }

}
