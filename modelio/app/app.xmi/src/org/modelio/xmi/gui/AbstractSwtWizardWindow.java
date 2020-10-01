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

package org.modelio.xmi.gui;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.xmi.gui.report.ReportManager;
import org.modelio.xmi.gui.report.ReportModel;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.util.BareBonesBrowserLaunch;

/**
 * @author ebrosse
 */
@objid ("b72a3346-d88d-4771-aa39-4fdd4e429e9e")
public abstract class AbstractSwtWizardWindow extends Dialog {
    @objid ("deb1a990-e1d0-4f35-bdb7-088c1d51f7b6")
    protected String title = "";

    @objid ("d5a9b8b0-56e8-4a58-935d-df5e33b03939")
    protected String description = "";

    @objid ("6289e8a3-a442-44f0-a004-14f17f8da131")
    protected String frametitle = "";

    @objid ("9ec1295c-6e82-4e8b-b489-c886671ce664")
    protected String cancelButton = "";

    @objid ("48886e6d-b0c4-477c-b909-07da0b0ac676")
    protected String validateButton = "";

    @objid ("f90eb057-f764-43cf-be9a-969b040c3ed4")
    private static boolean cancelation = false;

    @objid ("a24a7ce1-79fb-4b9f-84a0-b1746a17e04a")
    protected boolean error = false;

    @objid ("e0ec614a-0d71-430c-aae6-7ce7191b437d")
    protected String path = "";

    @objid ("546c2750-8f5d-4073-8aaf-69b51922027e")
    protected ModelElement selectedElt = null;

    @objid ("ab230392-40bc-4c77-abf2-b290c4b84056")
    protected IProgressService progressService = null;

    @objid ("89142edb-bd92-4a7e-b519-a8cec5459c53")
    protected final int minwidth = 655;

    @objid ("0956e81b-7f35-4304-a46b-b878163bf9c1")
    protected IProjectService projectService = null;

    @objid ("d2c3697f-ef49-430b-a5fc-ba75b25f29a6")
    protected FileChooserComposite fileChooserComposite = null;

    @objid ("e3e59f25-e517-452e-9456-14d68ebb39b3")
    protected ValidationBoutonComposite validateComposite = null;

    @objid ("a98d91f5-02fe-40fb-80bc-1b0e594f8e9c")
    protected ProgressBarComposite theProgressBar = null;

    @objid ("14411607-0456-4b1c-8e0b-50e025dc2867")
    protected AbstractXMIThread theThread = null;

    @objid ("24487df6-ce97-4e46-b6c7-85c25ba7dc40")
    protected Shell shell = null;

    /**
     * @return nothing
     */
    @objid ("f2fcf340-15f9-4a76-af7a-f14e34c1ce52")
    public Object open() {
        setCancellation(false);
        createContents();
        Display display = getParent().getDisplay();
        centerOnPrimaryScreen(display);
        this.shell.open();
        this.shell.layout();
        
        while (!this.shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return null;
    }

    @objid ("b6131dac-a270-49fb-8205-2584bc34b535")
    protected abstract void createContents();

    /**
     * set default configuration of the dialog box
     */
    @objid ("f91bd53b-fb9f-442d-a4ee-f110a5f345f4")
    public abstract void setDefaultDialog();

    @objid ("46fe668f-cc7b-43be-8972-983b52e36a5e")
    void cancelAction() {
        if (this.theThread != null && this.theThread.isAlive()) {
            this.theThread.interrupt();
        }
        
        if ((this.shell != null) && (!this.shell.isDisposed())) {
            this.shell.dispose();
        }
    }

    @objid ("0a204520-e1a0-489b-8574-1ed74ef83707")
    public void disableComposites() {
        enableOrDisableCompistes(false);
    }

    /**
     * enable all composites
     */
    @objid ("f48e4482-30a2-4d68-a459-f41c912d192f")
    public void enableComposites() {
        enableOrDisableCompistes(true);
    }

    @objid ("6c05d386-7533-4dee-a389-333dbaf4441a")
    protected abstract void enableOrDisableCompistes(boolean isEnable);

    /**
     * @param cancelButton : the text of the cancel button
     */
    @objid ("43b4eaf1-377f-4d85-a207-b56eed68d8d8")
    public void setCancelButton(final String cancelButton) {
        this.cancelButton = cancelButton;
    }

    /**
     * @param description : the description of the windows
     */
    @objid ("ac22039a-5bd2-4d2d-8781-98ee83febdfa")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param frametitle : the title of the windows frame
     */
    @objid ("ae17c648-dd4c-4256-af0a-8a98e5edda04")
    public void setFrametitle(final String frametitle) {
        this.frametitle = frametitle;
    }

    /**
     * @param title : the title of the windows
     */
    @objid ("abff41e0-d9a6-437d-8f47-240b9018a602")
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @param validateButton : the button of validation
     */
    @objid ("f21fbab8-57bb-42ef-8636-ff7785e55831")
    public void setValidateButton(final String validateButton) {
        this.validateButton = validateButton;
    }

    @objid ("d2071521-df27-4f35-a59a-d9510d6eb1f1")
    protected FileChooserComposite getFileChooserComposite() {
        return this.fileChooserComposite;
    }

    @objid ("454c165d-1184-4388-b977-b8b269d2531a")
    protected ProgressBarComposite getTheProgressBar() {
        return this.theProgressBar;
    }

    /**
     * Warning user that he does not select a file
     */
    @objid ("e76e8285-f3ca-4f40-ab9b-49334c2bdefd")
    public void selectAFile() {
        MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_WARNING);
        messageBox.setMessage(this.description);
        messageBox.open();
    }

    /**
     * @return true if the process is cancelled by the user
     */
    @objid ("e5238760-0b56-4c38-9ce1-7bff302ecc70")
    public static boolean isCancelation() {
        return AbstractSwtWizardWindow.cancelation;
    }

    /**
     * @param cancelation : set the cancellation of the process
     */
    @objid ("e0479287-d76d-4290-bfcf-0a11f7e5aa29")
    public static void setCancellation(final boolean cancelation) {
        AbstractSwtWizardWindow.cancelation = cancelation;
    }

    /**
     * the action i.e. import or export
     */
    @objid ("79b6009f-1feb-49db-be47-510f8237760d")
    protected abstract void validationAction();

    /**
     * set button labels
     */
    @objid ("82b11c44-0921-44d6-b8df-74db93e5c7c1")
    protected abstract void setLabels();

    /**
     * initialize file path
     */
    @objid ("bedb1c71-f7b7-4e44-869e-c4b16b874eca")
    protected abstract void setPath();

    /**
     * launch a dialog box for wrong path
     */
    @objid ("f321402b-4356-4feb-8ca5-d5ccfff8370c")
    public void fileDontExist() {
        MessageBox messageBox = new MessageBox(this.shell, SWT.ICON_WARNING);
        messageBox.setMessage(Xmi.I18N.getString("fileChooser.banner.import.dontexit"));
        messageBox.open();
    }

    @objid ("9d8e8f80-a2aa-476c-b1c1-02a48afbf98d")
    void helpPressed() {
        String urlPath = "http://127.0.0.1:1697/help/index.jsp";
        BareBonesBrowserLaunch.openURL(urlPath, this.shell);
    }

    /**
     * @param parent : the shell parent
     * @param style : the swt style
     */
    @objid ("164d1dac-c354-4fe3-815c-089c11e9ab38")
    public AbstractSwtWizardWindow(final Shell parent, final int style) {
        super(parent, style);
    }

    /**
     * @param parent : the shell parent
     */
    @objid ("bd78756e-a049-4d5e-959f-1c5aa3d2512b")
    public AbstractSwtWizardWindow(final Shell parent, IProgressService progressService, IProjectService projectService) {
        this(parent, SWT.NONE);       
        this.progressService = progressService;
        this.projectService = projectService;
    }

    @objid ("2cfa948f-4c41-4910-b2c0-4c8c66056485")
    private void centerOnPrimaryScreen(Display display) {
        Monitor primary = display.getPrimaryMonitor();
        Rectangle bounds = primary.getBounds();
        Rectangle rect = this.shell.getBounds();
        int x = bounds.x + (bounds.width - rect.width) / 2;
        int y = bounds.y + (bounds.height - rect.height) / 2;
        this.shell.setLocation(x, y);
        this.shell.open();
    }

    @objid ("bca27331-7279-463e-997c-4a390ad546d9")
    protected void reportBox(ReportModel reportModel, IModelioNavigationService mns, String logFilePath) {
        if (this.shell != null) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    ReportManager.showGenerationReport(AbstractSwtWizardWindow.this.shell,
                            reportModel, mns);
                    ReportManager.writeReport(reportModel, logFilePath);
                    AbstractSwtWizardWindow.this.shell.dispose();
                }
            });
        }
    }

    @objid ("8c469992-8416-428d-849f-0ea9d7787942")
    void customMessageBox(int icon) {
        MessageBox messageBox = new MessageBox(this.shell, icon);
        messageBox.setMessage(this.description);
        messageBox.setText(this.title);
        messageBox.open();
    }

    @objid ("961cfe93-8b83-472a-8209-8d3dcaea1a58")
    protected void incompleteBox(final String errorMessage) {
        if (this.shell != null) {
            this.title = Xmi.I18N.getString("fileChooser.dialog.errorBox");
        
            this.description = errorMessage;
        
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    customMessageBox(SWT.ICON_ERROR);
                    AbstractSwtWizardWindow.this.shell.dispose();
                }
            });
        }
    }

    @objid ("6bb85ce3-50ed-4094-bb7f-f7801e8dd76b")
    protected void catchException(final Exception e) {
        this.error = true;
        Xmi.LOG.error(e);
        
        final String msgTitle = Xmi.I18N.getString("error.import.uncatchedException");
        final String msg = e.getClass().getCanonicalName();
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (!AbstractSwtWizardWindow.this.shell.isDisposed()) {
                    final MessageBox messageBox = new MessageBox(AbstractSwtWizardWindow.this.shell, SWT.ICON_ERROR);
        
                    if (msg != null) {
                        messageBox.setMessage(msg);
                    }
        
                    messageBox.setText(msgTitle);
                    messageBox.open();
                    AbstractSwtWizardWindow.this.shell.dispose();
                }
            }
        });
    }

    @objid ("10ca822f-154a-4d7b-b1c0-5c17cb6ff800")
    protected void completeBox() {
        this.title = Xmi.I18N.getString("fileChooser.dialog.endBox");
        this.description = Xmi.I18N.getString("fileChooser.dialog.endBox");
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
                AbstractSwtWizardWindow.this.shell.dispose();
            }
        });
    }

    /**
     * @return the name of the selected element i.e. Package or IModule
     */
    @objid ("d34b2ee5-1311-4bb5-a3d2-3e4957a67687")
    public String getSelectedPkgName() {
        return this.selectedElt.getName();
    }

    /**
     * @param selectedElt : set the name of the selected element
     */
    @objid ("9743bb6b-d85e-4574-a74e-b7ee0fc8e7b4")
    public void setSelectedElt(final ModelElement selectedElt) {
        this.selectedElt = selectedElt;
    }

    /**
     * @return the selected element i.e. Package or IModule
     */
    @objid ("0c49b727-511a-4844-bb2a-d4797727b57c")
    public ModelElement getSelectedPkg() {
        return this.selectedElt;
    }

}
