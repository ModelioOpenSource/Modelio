/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.audit.checker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.audit.plugin.Audit;
import org.modelio.audit.service.IAuditService;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("96eb884f-f002-4509-9546-ecebafdc1802")
public class CheckerView extends ModelioDialog {
    @objid ("41fda318-77e3-4cbb-8aec-2682b8e60b6c")
     volatile boolean refreshPending = false;

    @objid ("e428177c-adbe-46b1-9a18-38eac264991b")
    private static final int SAVE_AS_TEXT_FILE_ID = IDialogConstants.CLIENT_ID + 1;

    @objid ("cb9ff4a4-72ea-4c68-b22c-a11864ceca3a")
    private static final int SAVE_AS_TEXT_CLIP_ID = IDialogConstants.CLIENT_ID + 2;

    @objid ("53ab5afa-64c2-4310-b771-3c28ca173e2d")
    private String lastUsedPath;

    @objid ("c8b57cbd-d554-49cd-b4f2-a9a0d2d4c25f")
    private String lastUsedName;

    @objid ("c876561d-097d-4e0b-95d6-10268bc5eea6")
     Label statusLabel;

    @objid ("04a2cb18-1b8f-4991-9f66-3f6a735b148b")
    private List<MObject> checkedElements;

    @objid ("d903d75e-b4a5-46c7-843c-a628b7f573ea")
    private CheckerPanelProvider checkerPanel;

    @objid ("02060f87-ee74-494d-8c22-b6a55f4f1f30")
    protected CheckerView(Shell parentShell, Object selection, IMModelServices modelService, IModelioNavigationService navigationService, MApplication application, EModelService emService, IAuditService auditService, IProjectService projectService) {
        super(parentShell);
        
        // Used as proposed path for the 'save as file' command.
        this.lastUsedPath = projectService.getOpenedProject().getProjectFileStructure().getProjectPath().toString();
        this.lastUsedName = projectService.getOpenedProject().getName();
        
        this.checkedElements = getSelectedElements(selection);
        
        this.checkerPanel = new CheckerPanelProvider();
        this.checkerPanel.setApplication(application);
        this.checkerPanel.setAuditService(auditService);
        this.checkerPanel.setEmService(emService);
        this.checkerPanel.setModelService(modelService);
        this.checkerPanel.setNavigationService(navigationService);
        this.checkerPanel.setProjectService(projectService);
        
        setBlockOnOpen(false);
    }

    @objid ("b484dcf7-7e02-40ee-be38-9b221a65a6aa")
    @Override
    public Control createContentArea(Composite parent) {
        parent.setLayout(new GridLayout(1, false));
        
        Composite root = new Composite(parent, SWT.NONE);
        GridData gd = new GridData();
        gd.horizontalAlignment = SWT.FILL;
        gd.verticalAlignment = SWT.FILL;
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        root.setLayoutData(gd);
        root.setLayout(new GridLayout(1, false));
        
        // --------------------------------
        Composite comp = new Composite(root, SWT.BORDER);
        comp.setLayout(new RowLayout());
        
        Label elementLabel = new Label(comp, SWT.NONE);
        elementLabel.setText("Checked element(s): ");
        
        int card = this.checkedElements.size();
        Label elementIcon;
        Label elementText;
        switch (card) {
        
        case 0:
            break;
        case 1:
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(0).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(0).getName());
            break;
        case 2:
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(0).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(0).getName() + ", ");
        
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(1).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(1).getName());
            break;
        case 3:
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(0).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(0).getName() + ", ");
        
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(1).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(1).getName() + ", ");
        
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(2).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(2).getName());
            break;
        default:
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(0).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(0).getName() + ", ");
        
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(1).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(1).getName() + ", ");
        
            elementIcon = new Label(comp, SWT.NONE);
            elementIcon.setImage(MetamodelImageService.getIcon(this.checkedElements.get(2).getMClass()));
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(this.checkedElements.get(2).getName());
        
            elementText = new Label(comp, SWT.NONE);
            elementText.setText(", ... (" + this.checkedElements.size() + " elements)");
            break;
        }
        
        gd = new GridData();
        gd.horizontalSpan = 6;
        gd.horizontalAlignment = SWT.FILL;
        comp.setLayoutData(gd);
        
        this.checkerPanel.createPanel(root);
        this.checkerPanel.setInput(this.checkedElements);
        return this.checkerPanel.getAuditPanel().getPanel();
    }

    @objid ("9ad02847-2a84-465f-ac9f-47e1e2c762e0")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, CheckerView.SAVE_AS_TEXT_FILE_ID, Audit.I18N.getString("Audit.CheckerView.SaveAsFile.label"), false);
        createButton(parent, CheckerView.SAVE_AS_TEXT_CLIP_ID, Audit.I18N.getString("Audit.CheckerView.CopyToClipboard.label"), false);
    }

    @objid ("511c844f-45a0-4750-9d9b-63ec8abed84f")
    @Override
    public void init() {
        getShell().setText(Audit.I18N.getString("Audit.CheckerView.DialogTitle"));
        setTitle(Audit.I18N.getString("Audit.CheckerView.DialogTitle"));
        setMessage(Audit.I18N.getString("Audit.CheckerView.DialogMessage"));
        getShell().setSize(800, 400);
        getShell().setMinimumSize(300, 300);
    }

    @objid ("63a3a266-4063-45a7-b816-3310e63d25b5")
    @Override
    public boolean close() {
        this.checkerPanel.getAuditPanel().dispose();
        return super.close();
    }

    @objid ("9f139001-78db-4aef-967b-23998fd56d4c")
    public void refresh() {
        if (!this.refreshPending && !CheckerView.this.statusLabel.isDisposed()) {
            this.refreshPending = true;
        }
    }

    @objid ("9d183d37-76f3-4dbe-b645-2291bb194daf")
    @Override
    protected String getHelpId() {
        return Audit.I18N.getString("Audit.CheckerView.HelpId");
    }

    @objid ("08af30b1-eb5c-48d3-8951-1b073f8ae369")
    private List<MObject> getSelectedElements(Object selection) {
        ArrayList<MObject> selectedElements = new ArrayList<>();
        
        if (selection instanceof MObject) {
            selectedElements.add((MObject) selection);
        } else if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() >= 1) {
            Object[] elements = ((IStructuredSelection) selection).toArray();
            for (Object element : elements) {
                if (element instanceof MObject) {
                    selectedElements.add((MObject) element);
                } else if (element instanceof IAdaptable) {
                    final MObject adapter = ((IAdaptable) element).getAdapter(MObject.class);
                    if (adapter != null) {
                        selectedElements.add(adapter);
                    }
                }
            }
        }
        return selectedElements;
    }

    @objid ("5aa3ff89-5b8f-42fc-a96b-14c9b14f5e74")
    public void refreshContent() {
        // Nothing to do
    }

    @objid ("d0213c43-d025-4147-8d56-59e9ac747268")
    public Tree getAuditTree() {
        return this.checkerPanel.getAuditPanel().getTreeViewer().getTree();
    }

    @objid ("6714c386-83c2-494c-9282-b3cce105ede3")
    @Override
    protected void buttonPressed(int buttonId) {
        if (buttonId == CheckerView.SAVE_AS_TEXT_FILE_ID) {
            doSaveAsTextFile();
        } else if (buttonId == CheckerView.SAVE_AS_TEXT_CLIP_ID) {
            doCopyToClipboard();
        } else {
            super.buttonPressed(buttonId);
        }
    }

    @objid ("1924bde1-ab29-4e07-91d6-7a825c813346")
    private void doSaveAsTextFile() {
        FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
        fileDialog.setFilterExtensions(new String[] { ".txt" });
        fileDialog.setOverwrite(true);
        
        fileDialog.setFileName(this.lastUsedName);
        fileDialog.setFilterPath(this.lastUsedPath);
        
        String file = fileDialog.open();
        
        if (file != null) {
            PrintStream out;
            try {
                out = new PrintStream(file);
                this.checkerPanel.getAuditPanel().printDiagnostic(out);
                out.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
            this.lastUsedName = new File(file).getName();
            this.lastUsedPath = new File(file).getParentFile().getPath();
        }
    }

    @objid ("dee3a7ed-9a71-4e24-81de-a70a01ea2419")
    private void doCopyToClipboard() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bos);
        this.checkerPanel.getAuditPanel().printDiagnostic(out);
        
        // Cannot copy an empty string in clipboard
        if (bos.size() == 0) {
            return;
        }
        
        Clipboard cb = new Clipboard(getShell().getDisplay());
        TextTransfer transfer = TextTransfer.getInstance();
        
        String s = bos.toString();
        out.close();
        
        Object[] data = new Object[] { s };
        cb.setContents(data, new Transfer[] { transfer });
        cb.dispose();
    }

}
