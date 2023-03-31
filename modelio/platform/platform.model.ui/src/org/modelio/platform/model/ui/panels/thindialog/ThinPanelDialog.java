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
package org.modelio.platform.model.ui.panels.thindialog;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * A ThinPanelDialog is a dialog composed of:
 * <ul>
 * <li>a shell that is popped on activation of the thin dialog {@link ThinPanelDialog#open()}
 * <li>a panel provided by the caller as a IPanelProvider instance.</li>
 * <li>buttons to close the thin dialog</li>
 * <li>a designated master control whose activation pops up and defines the position of the ThinPanelDialog</li>
 * </ul>
 * The ThinPanelDialog is laid out and sized relatively to its master control.
 * 
 * <p>
 * Usage:
 * <ol>
 * <li>create a ThinPanelDialog passing the master control to the constructor and a IPanelProvider.</li>
 * <li>add the listener of your choice on the master control to call open on the thin dialog.</li>
 * <li>the ok and cancel buttons of the ThinPanelDialog call the {@link ThinPanelDialog#onOk()} and {@link ThinPanelDialog#onCancel()} methods of the dialog. Redefine these methods if required.</li>
 * </ol>
 * </p>
 * Configuration:
 * <p>
 * The buttons can be configured using the 'userValidation' parameters at c'tor
 * <ul>
 * <li>true means that two buttons 'ok' and 'cancel' are proposed to the user who can choose to validate or not the changes he's made in the pane, process by redefining {@link ThinPanelDialog#onOk()} and {@link ThinPanelDialog#onCancel()}</li>
 * <li>false means that a unique button 'close' is proposed to the user, this button does not distinguish between ok or cancel and is processed by redefining {@link ThinPanelDialog#onClose()}</li>
 * </ul>
 * Note: in 'userValidation=true' mode, the {@link ThinPanelDialog#onClose()} method is not called.
 * </p>
 */
@objid ("ece51223-713f-459a-8bf3-b976b6cfb4df")
public class ThinPanelDialog {
    @objid ("3167b615-3fa5-4fdf-ab8c-35f4534093d3")
    private boolean userValidation;

    @objid ("afd21c97-7f24-4908-9063-f3e1a9a7aaac")
    private Control masterControl;

    @objid ("5caef0d7-5ddf-4b58-867d-fe7e40715697")
    private IPanelProvider panelProvider;

    @objid ("30704b93-67cd-48de-89a8-2942468213c3")
    private Shell slaveShell;

    @objid ("687a46c9-f4a3-43d6-b556-b6f68f9f5dc2")
    public  ThinPanelDialog(Control masterControl, IPanelProvider panelProvider) {
        this(masterControl, panelProvider, true);
    }

    @objid ("75e61abe-e208-4093-9130-c5170a63fde7")
    public  ThinPanelDialog(Control masterControl, IPanelProvider panelProvider, boolean userValidation) {
        this.masterControl = masterControl;
        this.panelProvider = panelProvider;
        this.userValidation = userValidation;
        
    }

    @objid ("955ccaa0-2d1c-4301-8a2c-baa058f4ce20")
    public IPanelProvider getPanelProvider() {
        return this.panelProvider;
    }

    @objid ("ec3bb1b7-8670-49e4-8152-ab9d9c7af13c")
    public void onOk() {
        if (this.slaveShell != null) {
            this.slaveShell.close();
            this.slaveShell.dispose();
        }
        
    }

    @objid ("3a44b984-d319-447e-8f1c-2a3e7247306d")
    public void onCancel() {
        if (this.slaveShell != null) {
            this.slaveShell.close();
            this.slaveShell.dispose();
        }
        
    }

    @objid ("2df8aff4-08d9-4456-8df0-c79abfddba77")
    public void onClose() {
        if (this.slaveShell != null) {
            this.slaveShell.close();
            this.slaveShell.dispose();
        }
        
    }

    @objid ("87e86e2c-551b-4005-881a-c8596289dd14")
    public boolean open() {
        final Shell masterShell = this.masterControl.getShell();
        
        this.slaveShell = new Shell(masterShell, SWT.ON_TOP | SWT.TOOL | SWT.PRIMARY_MODAL);
        // this.slaveShell.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
        
        FormLayout layout = new FormLayout();
        layout.marginWidth = 2;
        this.slaveShell.setLayout(layout);
        
        Composite panel = (Composite) this.panelProvider.createPanel(this.slaveShell);
        
        Button ok = new Button(this.slaveShell, SWT.NONE);
        ok.setImage(UIImages.ACCEPT);
        ok.setToolTipText(CoreUi.I18N.getString("ThinPanelDialog.okButton.tooltip"));
        ok.addListener(SWT.Selection, e -> onOk());
        
        Button cancel = new Button(this.slaveShell, SWT.NONE);
        cancel.setImage(UIImages.CANCEL);
        cancel.setToolTipText(CoreUi.I18N.getString("ThinPanelDialog.cancelButton.tooltip"));
        cancel.addListener(SWT.Selection, e -> onCancel());
        
        Button close = new Button(this.slaveShell, SWT.NONE);
        close.setImage(UIImages.ACCEPT);
        close.setToolTipText(CoreUi.I18N.getString("ThinPanelDialog.closeButton.tooltip"));
        close.addListener(SWT.Selection, e -> onClose());
        
        // Form attachments
        FormData fd = new FormData();
        
        // Panel
        fd = new FormData();
        fd.top = new FormAttachment(0);
        fd.left = new FormAttachment(0);
        fd.right = new FormAttachment(100);
        fd.bottom = new FormAttachment(ok, 0, SWT.TOP);
        panel.setLayoutData(fd);
        
        // Button cancel
        fd = new FormData();
        // fd.top = new FormAttachment(0);
        // fd.left = new FormAttachment(label, 0, SWT.RIGHT);
        fd.right = new FormAttachment(100, -2);
        fd.bottom = new FormAttachment(100, -2);
        cancel.setLayoutData(fd);
        
        // Button ok
        fd = new FormData();
        // fd.top = new FormAttachment(0);
        // fd.left = new FormAttachment(label, 0, SWT.RIGHT);
        fd.right = new FormAttachment(cancel, -2, SWT.LEFT);
        fd.bottom = new FormAttachment(100, -2);
        ok.setLayoutData(fd);
        
        // Button close
        fd = new FormData();
        // fd.top = new FormAttachment(0);
        // fd.left = new FormAttachment(label, 0, SWT.RIGHT);
        fd.right = new FormAttachment(ok, -2, SWT.LEFT);
        fd.bottom = new FormAttachment(100, -2);
        close.setLayoutData(fd);
        
        // label.addListener(SWT.MouseExit, labelListener);
        // label.addListener(SWT.MouseDown, labelListener);
        
        ok.setVisible(this.userValidation);
        cancel.setVisible(this.userValidation);
        close.setVisible(!this.userValidation);
        
        syncSlaveBoundsToMaster();
        this.slaveShell.setVisible(true);
        
        masterShell.addControlListener(new ControlAdapter() {
            @Override
            public void controlMoved(ControlEvent e) {
                if (!syncSlaveBoundsToMaster()) {
                    masterShell.removeControlListener(this);
                }
            }
        
            @Override
            public void controlResized(ControlEvent e) {
                if (!syncSlaveBoundsToMaster()) {
                    masterShell.removeControlListener(this);
                }
            }
        
        });
        return true;
    }

    @objid ("5e4948a3-eb39-4dd8-853c-fe0c2854c56d")
    private boolean syncSlaveBoundsToMaster() {
        if (this.slaveShell.isDisposed()) {
            return false;
        }
        
        Rectangle r = this.masterControl.getBounds();
        Point p = this.masterControl.getParent().toDisplay(r.x, r.y + r.height);
        this.slaveShell.setBounds(p.x, p.y, r.width, 300);
        return true;
    }

}
