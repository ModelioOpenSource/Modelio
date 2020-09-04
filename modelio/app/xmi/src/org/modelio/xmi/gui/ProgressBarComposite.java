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

package org.modelio.xmi.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.AbortProcessException;

/**
 * SWT composite containing a SWT progress bar
 * @author ebrosse
 */
@objid ("1c559c5e-5f0f-4906-adbf-d35d9a1e90e5")
public class ProgressBarComposite extends Composite {
    @objid ("fd1a9ea8-fa1d-4610-bb4f-b40f5339eed4")
    private int selection = 0;

    @objid ("73c5b84c-e16a-4d16-b0e6-e23d4a5b61ac")
    private int total = 0;

    @objid ("74ac39b8-0f62-4d66-8800-6ce51d9a6f3a")
    private int max = 0;

    @objid ("5c0ff1eb-899a-4849-bf77-cc5d17651953")
    private String title = Xmi.I18N.getString("progressBar.title");

    @objid ("31f07f3e-3cdb-4d0d-91bc-970f28b136f4")
    private int numberElement = 0;

    @objid ("8859479e-288b-4083-a682-8e8de69a0a2c")
    private int incrementation = 0;

    @objid ("a237721d-5858-4622-840f-6cc339a483e8")
    private int totalElement = 0;

    @objid ("b710aece-1a27-4f8c-96b0-e7f2eaa464ba")
    private String valuePlural = Xmi.I18N.getString("progressBar.setValuePlural");

    @objid ("d71bc632-7908-49a1-a13a-244f8fed4623")
    private ProgressBar progressBar;

    @objid ("c3b79e24-a039-481e-b08c-259c61aa5c39")
    private Label chargeInProgress;

    @objid ("d44a92ea-fbea-40ad-b68b-97e9cde78ab3")
    private Group group;

    /**
     * @return the progress bar
     */
    @objid ("ec30ef2f-ab26-48d5-af1f-4fbb486f4bc1")
    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    /**
     * @return the progress bar label
     */
    @objid ("d32633c8-428f-41ee-a839-b2d6dbc9a466")
    public Label getLabelProgessBar() {
        return this.chargeInProgress;
    }

    /**
     * add one to the current number of treated element
     */
    @objid ("e832e35e-d93c-4de4-bcb0-b8892325b12d")
    public void addValue() {
        if (AbstractSwtWizardWindow.isCancelation()) {
            abortProcess();
        }
        
        if (Display.getDefault().isDisposed())
            return;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (ProgressBarComposite.this.progressBar.isDisposed())
                    return;
                ProgressBarComposite.this.total++;
                if (ProgressBarComposite.this.incrementation == 0 || ProgressBarComposite.this.total % ProgressBarComposite.this.incrementation == 0) {
                    if (ProgressBarComposite.this.selection < ProgressBarComposite.this.max) {
                        ProgressBarComposite.this.selection++;
                        ProgressBarComposite.this.progressBar.setSelection(ProgressBarComposite.this.selection);
                    }
                }
            }
        });
    }

    /**
     * fullfill the progress bar
     */
    @objid ("10a0779a-edf3-4520-b734-4572d71a5a69")
    public void addFinalValue() {
        if (Display.getDefault().isDisposed())
            return;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (ProgressBarComposite.this.progressBar.isDisposed())
                    return;
                ProgressBarComposite.this.progressBar.setSelection(ProgressBarComposite.this.max);
            }
        });
    }

    /**
     * @param value set the Progress bar label
     */
    @objid ("d5345c0b-d5a6-4b04-bbb1-0416eba542c4")
    public void setLabel(final String value) {
        final String v = value;
        if (Display.getDefault().isDisposed())
            return;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (ProgressBarComposite.this.progressBar.isDisposed())
                    return;
                setTextGroup(v, true);
            }
        });
    }

    /**
     * add one element
     */
    @objid ("9c18a66e-db1b-42fb-a52c-12a41d50c18c")
    public void addElement() {
        if (AbstractSwtWizardWindow.isCancelation()) {
            abortProcess();
        }
        this.totalElement++;
        
        if (Display.getDefault().isDisposed())
            return;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (ProgressBarComposite.this.progressBar.isDisposed())
                    return;
                if (ProgressBarComposite.this.totalElement > 1)
                    setTextGroup(String.valueOf(ProgressBarComposite.this.totalElement) + " " + ProgressBarComposite.this.valuePlural, true);
                else
                    setTextGroup(Xmi.I18N.getMessage("progressBar.setValueSingular", String.valueOf(ProgressBarComposite.this.totalElement)), true);
            }
        });
    }

    /**
     * @return the current number of treated element
     */
    @objid ("da4d456f-98ba-4661-a600-47f9b789e5f2")
    public int getNumberElement() {
        return this.numberElement;
    }

    /**
     * @param numberElement the number of treated element
     */
    @objid ("b6f9ede0-645d-427c-a39b-ae614d75066c")
    public void setNumberElement(int numberElement) {
        this.numberElement = numberElement;
        this.incrementation = (numberElement) / this.max;
    }

    @objid ("e6f03ef8-195c-479f-8b08-b28ab17ac70d")
    private void abortProcess() throws AbortProcessException {
        AbstractSwtWizardWindow.setCancellation(true);
        throw new AbortProcessException();
    }

    @objid ("a19b603f-3cf0-4a3f-841f-836e4389e7a7")
    void setTextGroup(String text, boolean inProgression) {
        if (!inProgression) {
            this.group.setText(" " + text + " ");
        } else {
            this.group.setText(" " + this.title + " : " + text + " ");
        }
    }

    @objid ("4f7fc5e0-6169-4bcf-a047-6302d81fcb34")
    private void setTextGroup(String text) {
        setTextGroup(text, false);
    }

    /**
     * @param parent The parent Composite
     * @param style The SWT style
     */
    @objid ("47f013dd-9e9b-404a-86ab-e3bea85b4cfe")
    public ProgressBarComposite(Composite parent, int style) {
        super(parent, style);
        setLayout(new FormLayout());
        this.group = new Group(this, SWT.NONE);
        this.group.setLayout(new FormLayout());
        final FormData fd_group = new FormData();
        fd_group.bottom = new FormAttachment(100, -5);
        fd_group.top = new FormAttachment(0, 0);
        fd_group.right = new FormAttachment(100, 0);
        fd_group.left = new FormAttachment(0, 0);
        
        this.group.setLayoutData(fd_group);
        setTextGroup(this.title);
        
        this.progressBar = new ProgressBar(this.group, SWT.NONE);
        final FormData fd_progressBar = new FormData();
        fd_progressBar.right = new FormAttachment(100, -5);
        fd_progressBar.left = new FormAttachment(0, 5);
        fd_progressBar.bottom = new FormAttachment(100, -5);
        fd_progressBar.top = new FormAttachment(0, 5);
               
        
        this.progressBar.setLayoutData(fd_progressBar);
        this.max = this.progressBar.getMaximum();
    }

    /**
     * Initialize value
     */
    @objid ("b6e42624-ded4-4fcb-a58f-91ab9f4a0b10")
    public void setInitialValue() {
        if (Display.getDefault().isDisposed())
            return;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (ProgressBarComposite.this.progressBar.isDisposed())
                    return;
                ProgressBarComposite.this.progressBar.setSelection(0);
            }
        });
    }

}
