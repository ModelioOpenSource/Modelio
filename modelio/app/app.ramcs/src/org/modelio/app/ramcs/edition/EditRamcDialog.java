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

package org.modelio.app.ramcs.edition;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.modelio.api.ui.dnd.ModelElementTransfer;
import org.modelio.app.ramcs.plugin.AppRamcs;
import org.modelio.gproject.ramc.core.model.ModelComponent;
import org.modelio.gproject.ramc.core.packaging.IModelComponentContributor.ExportedFileEntry;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.ui.UIColor;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Dialog box to edit ramc properties and launch ramc packaging.
 */
@objid ("e798dd82-ce07-4c29-a95c-c81a3dcebd95")
public class EditRamcDialog extends ViewRamcDialog {
    @objid ("a58bf079-c467-4f70-a0e4-cb926e69c339")
    protected Controller controller;

    @objid ("4951ef9d-39d7-4b65-873c-67cc4ed74ce7")
    private Button packageButton;

    /**
     * C'tor.
     * 
     * @param parentShell the parent shell, or <code>null</code> to create a top-level shell.
     * @param dataModel the data model of the ramc to be edited.
     */
    @objid ("0e4f1cd0-1389-4be0-8b2a-b9401232fcd9")
    public EditRamcDialog(Shell parentShell, RamcModel dataModel) {
        super(parentShell, dataModel);
    }

    @objid ("cdfaa60f-c3e2-4361-a253-bed5376309a9")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        // the 'package' button (bound to FINISH_ID)
        this.packageButton = createButton(parent, IDialogConstants.FINISH_ID,
                AppRamcs.I18N.getString("EditRamcDialog.Package"), false);
        this.packageButton.addListener(SWT.Selection, e-> {
            if (this.dataModel.isEditable()) {
                this.controller.onApply();
            }
            setReturnCode(IDialogConstants.FINISH_ID);
            this.close();
        });
        
        // the 'apply changes' button (bound to PROCEED_ID)
        if (this.dataModel.isEditable()) {
            Button apply = createButton(parent, IDialogConstants.PROCEED_ID, AppRamcs.I18N.getString("EditRamcDialog.Modify"), true);
            apply.addListener(SWT.Selection, e-> {
                this.controller.onApply();
                setReturnCode(IDialogConstants.PROCEED_ID);
            });
        }
        
        // The super method will add the cancel button
        super.addButtonsInButtonBar(parent);
    }

    @objid ("c168bbd7-fcf8-4eaf-8158-8ceb445f0cb3")
    @Override
    public Control createContentArea(Composite parent) {
        Control control = super.createContentArea(parent);
        reconfigureforEdition();
        return control;
    }

    @objid ("f1084ce1-979c-4281-8645-284d3eeca026")
    private void reconfigureforEdition() {
        if (this.dataModel.isEditable()) {
            this.controller = new Controller(this, this.dataModel);
        
            // Name text field
            reconfigureNameTextField();
        
            // Ramc version field
            reconfigureVersionTextField();
        
            // Description
            reconfigureDescriptionTextField();
            
            // Provider
            reconfigureProviderTextField();
            
            // Manifestation list field
            reconfigureManifestationsList();
        
            // Dependencies list field
            reconfigureDependenciesList();
        
            // Exported files field
            reconfigureFilesList();
        
            // Reconfigure contributors list
            reconfigureContributorsList();
        }
    }

    @objid ("7fcb2865-d1d0-4b20-8f9a-49800d770442")
    private void reconfigureDescriptionTextField() {
        this.ramcDescriptionText.setEditable(true);
        this.ramcDescriptionText.setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.ramcDescriptionText.setBackground(UIColor.TEXT_WRITABLE_BG);
        this.ramcDescriptionText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                EditRamcDialog.this.controller.onModifyDescription(EditRamcDialog.this.ramcDescriptionText.getHtml());
            }
        });
    }

    @objid ("1b086034-24c8-4baa-9aae-27c5a9db88f8")
    private void reconfigureVersionTextField() {
        this.ramcVersionText.setEditable(true);
        this.ramcVersionText.setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.ramcVersionText.setBackground(UIColor.TEXT_WRITABLE_BG);
        this.ramcVersionText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                EditRamcDialog.this.controller.onModifyVersion(EditRamcDialog.this.ramcVersionText.getText());
            }
        });
    }

    @objid ("f22264bd-2fe3-4887-946d-8368efccbc1b")
    private void reconfigureNameTextField() {
        this.ramcNameText.setEditable(true);
        this.ramcNameText.setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.ramcNameText.setBackground(UIColor.TEXT_WRITABLE_BG);
        this.ramcNameText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                EditRamcDialog.this.controller.onModifyName(EditRamcDialog.this.ramcNameText.getText());
            }
        });
    }

    @objid ("5004bbb8-2947-4aab-a1ff-cd76077abb25")
    private void reconfigureManifestationsList() {
        this.manifestationsTable.getTable().setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.manifestationsTable.getTable().setBackground(UIColor.TEXT_WRITABLE_BG);
        // Drop listener
        ICoreSession session = CoreSession.getSession(this.dataModel.getArtifact());
        
        ManifestationsDropListener dropListener = new ManifestationsDropListener(this.manifestationsTable, this.controller,
                session.getModel());
        this.manifestationsTable.addDropSupport(DND.DROP_MOVE, new Transfer[] { ModelElementTransfer.getInstance() }, dropListener);
        
        // Del key to remove a manifestation
        this.manifestationsTable.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                //
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    Table table = (Table) e.getSource();
                    TableItem[] items = table.getSelection();
                    for (TableItem item : items) {
                        EditRamcDialog.this.controller.onRemoveManifestedElement((Element) item.getData());
                    }
                }
                EditRamcDialog.this.refresh();
            }
        });
    }

    @objid ("34a5e65c-4537-4567-98f0-930b744b842d")
    private void reconfigureDependenciesList() {
        this.dependenciesTable.getTable().setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.dependenciesTable.getTable().setBackground(UIColor.TEXT_WRITABLE_BG);
        
        // Drop listener
        ICoreSession session = CoreSession.getSession(this.dataModel.getArtifact());
        DependenciesDropListener depDropListener = new DependenciesDropListener(this.dependenciesTable, this.controller,
                session.getModel());
        this.dependenciesTable
                .addDropSupport(DND.DROP_MOVE, new Transfer[] { ModelElementTransfer.getInstance() }, depDropListener);
        
        // Del key to remove a dependency
        this.dependenciesTable.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                //
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    Table table = (Table) e.getSource();
                    TableItem[] items = table.getSelection();
                    for (TableItem item : items) {
                        EditRamcDialog.this.controller.onRemoveDependency((ModelComponent) item.getData());
                    }
                }
                EditRamcDialog.this.refresh();
            }
        });
    }

    @objid ("71126fd9-4965-45db-acf8-9e5267b6bc54")
    private void reconfigureFilesList() {
        this.ramcFilesList.getTable().setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.ramcFilesList.getTable().setBackground(UIColor.TEXT_WRITABLE_BG);
        
        final FileDialog filesChooser = new FileDialog(getShell(), SWT.OPEN | SWT.MULTI);
        final Path projectPath = this.dataModel.getProjectPath();
        filesChooser.setFilterPath(projectPath.toString());
        
        this.addFilesButton.setEnabled(true);
        this.addFilesButton.addListener(SWT.Selection, e -> {
            filesChooser.open();
            String filterPath = filesChooser.getFilterPath();
            for (String filename : filesChooser.getFileNames()) {
                Path file = Paths.get(filterPath, filename);
                Path fileRelativeToProj = projectPath.relativize(file);
                String exportPath;
                if (file.startsWith(projectPath)) {
                    // The selected file belongs to the project, deploy
                    // using the same path relative to the project
                    exportPath = fileRelativeToProj.toString();
                } else {
                    // The selected file does not belong to the project,
                    // deploy it directly under the project
                    exportPath = file.getFileName().toString();
                }
                this.dataModel.addExportedFile(new ExportedFileEntry(fileRelativeToProj, exportPath));
            }
        
            refresh();
        });
        
        this.removeFilesButton.setEnabled(true);
        this.removeFilesButton.addListener(SWT.Selection, e -> {
            Table table = this.ramcFilesList.getTable();
            TableItem[] items = table.getSelection();
            for (TableItem item : items) {
                ExportedFileEntry file = (ExportedFileEntry) item.getData();
                this.dataModel.removeExportedFile(file);
            }
            refresh();
        });
        
        this.relativizeFilesButton.setEnabled(true);
        this.relativizeFilesButton.addListener(SWT.Selection, e -> {
            Table table = this.ramcFilesList.getTable();
            TableItem[] items = table.getSelection();
            for (TableItem item : items) {
                ExportedFileEntry file = (ExportedFileEntry) item.getData();
                Path fileToExport = file.getFileToExport();
                if (fileToExport.isAbsolute()) {
                    file.setFileToExport(this.dataModel.getProjectPath().relativize(fileToExport));
                }
            }
            refresh();
        });
        
        this.resolveFilesButton.setEnabled(true);
        this.resolveFilesButton.addListener(SWT.Selection, e -> {
            Table table = this.ramcFilesList.getTable();
            TableItem[] items = table.getSelection();
            for (TableItem item : items) {
                ExportedFileEntry file = (ExportedFileEntry) item.getData();
                Path fileToExport = file.getFileToExport();
                if (!fileToExport.isAbsolute()) {
                    file.setFileToExport(this.dataModel.getProjectPath().resolve(fileToExport).normalize());
                }
            }
            refresh();
        });
    }

    @objid ("a1fcf8fd-40d7-4b4c-b109-253f2924ec1d")
    private void reconfigureContributorsList() {
        this.contributorsTable.getTable().setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.contributorsTable.getTable().setBackground(UIColor.TEXT_WRITABLE_BG);
        
        this.contributorsTable.getTable().setEnabled(true);
        this.contributorsTable.addCheckStateListener(event -> {
            this.controller.onContributorChange(this.contributorsTable.getCheckedElements());
            this.contributorsTable.refresh(true);
        });
    }

    @objid ("6e711dca-8e33-4809-baaa-a7b526e599e8")
    @Override
    public void init() {
        super.init();
        
        // Put the messages in the banner area
        getShell().setText(AppRamcs.I18N.getString("EditRamcDialog.EditRamcDialogTitle"));
        setTitle(AppRamcs.I18N.getString("EditRamcDialog.EditRamcDialogTitle"));
        this.setMessage(AppRamcs.I18N.getString("EditRamcDialog.EditRamcMessage"));
        
        this.ramcDescriptionText.setEditable(true);
        this.ramcDescriptionText.setEnabled(true);
    }

    @objid ("e81c7f87-9184-4923-92c2-8cdf8d4749fa")
    @Override
    protected String getHelpId() {
        return AppRamcs.I18N.getString("EditRamcDialog.HelpId");
    }

    @objid ("8b8f00bc-7eda-4608-b5f2-5c93b1babb9d")
    private void reconfigureProviderTextField() {
        this.ramcProviderText.setEditable(true);
        this.ramcProviderText.setForeground(UIColor.EDITOR_RWTEXT_FG);
        this.ramcProviderText.setBackground(UIColor.TEXT_WRITABLE_BG);
        this.ramcProviderText.addModifyListener(
                e -> this.controller.onModifyProvider(this.ramcProviderText.getText()));
    }

    @objid ("b81c0413-566c-44e8-b446-04c47d3aa097")
    private static class ManifestationsDropListener extends ViewerDropAdapter {
        @objid ("7fe741b4-7721-4673-87bc-28489e8477f9")
        private final Controller controler;

        @objid ("7cd48407-c9ae-46ba-afa1-5538d6161674")
        private final IModel model;

        /**
         * C'tor.
         * 
         * @param viewer - the viewer this drop listener is attached to
         * @param controler - the edition controller.
         * @param model - access to the model in order to be able to find model element from their MRef
         */
        @objid ("39f417f7-0c9e-49ad-bca6-762fc8731436")
        public ManifestationsDropListener(Viewer viewer, Controller controler, IModel model) {
            super(viewer);
            this.controler = controler;
            this.model = model;
        }

        /**
         * Indicates whether or not the currently transfered elements can be dropped on the target.
         * <p>
         * Metamodel rules are checked, as well as manipulation rights for both the target and dropped elements.
         * </p>
         * 
         * @param target the targeted element, must be a MObject.
         * @param operation the d&d operation, must be {@link DND#DROP_COPY} or {@link DND#DROP_MOVE}.
         * @param transferType the contents of data being dropped.
         * @return <code>true</code> if all the drop parameters are valid.
         */
        @objid ("a5e52b3a-c0e3-41d1-a54b-b0475a1b63a4")
        @Override
        public boolean validateDrop(Object target, int operation, TransferData transferType) {
            List<Element> dropedElements = null;
            
            // Convert the transfer data to MRefs.
            ModelElementTransfer elementTransfer = ModelElementTransfer.getInstance();
            MRef[] refs = (MRef[]) elementTransfer.nativeToJava(transferType);
            if (refs != null) {
                dropedElements = new ArrayList<>();
                // Find model elements in the session from their refs
                for (MRef ref : refs) {
                    dropedElements.add((Element) this.model.findByRef(ref));
                }
            } else {
                // On linux, the event data is not filled until the 'drop'. Try
                // getting the selection from LocalSelectionTransfer.
                dropedElements = getLocalDraggedElements();
            }
            
            // Check the elements
            return checkDragged(dropedElements);
        }

        /**
         * After a drop has been stated as valid, copy or move the dragged elements under the target.
         */
        @objid ("2bf16e93-abfa-4759-9dbb-b85951b56edc")
        @Override
        public boolean performDrop(Object data) {
            // Convert the transfer data to MRefs.
            MRef[] refs = (MRef[]) data;
            if (refs != null) {
                // Find model elements in the session from their refs
                List<Element> dropedElements = new ArrayList<>();
                for (MRef ref : refs) {
                    dropedElements.add((Element) this.model.findByRef(ref));
                }
                // Check the elements
                if (checkDragged(dropedElements)) {
                    this.controler.onAddManifestation(dropedElements);
                    getViewer().refresh();
                    return true;
                }
            
            }
            return false;
        }

        /**
         * Check the given elements can be dragged.
         * 
         * @param elements The dragged elements
         * @return true if the given elements can be dragged.
         */
        @objid ("62867094-c70b-40e8-9888-a9d47cebcfa5")
        private boolean checkDragged(List<Element> elements) {
            for (Element element : elements) {
                if (!element.isValid() || element.getStatus().isRamc() || !canBeExported(element)) {
                    return false;
                }
            }
            return true;
        }

        @objid ("0d57462b-3a35-47c5-bd59-087ae61c3ad1")
        private boolean canBeExported(MObject me) {
            return me != null && me.getMClass().isCmsNode();
        }

        @objid ("e2bf87d6-8c4c-4117-b2c1-b5b003288631")
        private List<Element> getLocalDraggedElements() {
            List<Element> selectedElements = new ArrayList<>();
            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            if (selection instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
                    Object o = i.next();
                    if (o instanceof IAdaptable) {
                        IAdaptable adapter = (IAdaptable) o;
                        Element element = adapter.getAdapter(Element.class);
                        if (element != null) {
                            selectedElements.add(element);
                        }
                    } else if (o instanceof Element) {
                        selectedElements.add((Element) o);
                    }
                }
            }
            return selectedElements;
        }

    }

// end class ManifestationDropListener
    @objid ("16a40f61-2722-4495-a71e-e9e60e4877a4")
    private static class DependenciesDropListener extends ViewerDropAdapter {
        @objid ("d4ad2f8f-4988-463e-87bd-2ffe5240bdc7")
        private Controller controller;

        @objid ("71c7c977-7ad7-40ef-88c2-794c574ff6ec")
        private IModel model;

        /**
         * C'tor.
         * 
         * @param viewer the viewer this drop listener is attached to
         * @param controller the edition controller.
         * @param model access to the model in order to be able to find model element from their MRef.
         */
        @objid ("4352c32e-f82d-4590-b3ab-d60d9a5ada26")
        public DependenciesDropListener(Viewer viewer, Controller controller, IModel model) {
            super(viewer);
            this.controller = controller;
            this.model = model;
        }

        /**
         * Indicates whether or not the currently transfered elements can be dropped on the target.
         * <p>
         * Metamodel rules are checked, as well as manipulation rights for both the target and dropped elements.
         * </p>
         * 
         * @param target the targeted element, must be a MObject.
         * @param operation the d&d operation, must be {@link DND#DROP_COPY} or {@link DND#DROP_MOVE}.
         * @param transferType the contents of data being dropped.
         * @return <code>true</code> if all the drop parameters are valid.
         */
        @objid ("6f2e958d-4a77-40bf-b0ba-5118145da10e")
        @Override
        public boolean validateDrop(Object target, int operation, TransferData transferType) {
            List<Element> droppedElements = null;
            
            // Convert the transfer data to MRefs.
            ModelElementTransfer elementTransfer = ModelElementTransfer.getInstance();
            MRef[] refs = (MRef[]) elementTransfer.nativeToJava(transferType);
            if (refs != null) {
                droppedElements = new ArrayList<>();
                // Find model elements in the session from their refs
                for (MRef ref : refs) {
                    droppedElements.add((Element) this.model.findByRef(ref));
                }
            } else {
                // On linux, the event data is not filled until the 'drop'. Try
                // getting the selection from LocalSelectionTransfer.
                droppedElements = getLocalDraggedElements();
            }
            
            // Check the elements, this will only check they are RAMC artifact
            // instances but will not care of dependency cycles.
            return checkDragged(droppedElements);
        }

        /**
         * After a drop has been stated as valid, copy or move the dragged elements under the target.
         */
        @objid ("1eb401a2-62c7-44ca-94e6-925227589fbd")
        @Override
        public boolean performDrop(Object data) {
            // Convert the transfer data to MRefs.
            MRef[] refs = (MRef[]) data;
            if (refs != null) {
                // Find model elements in the session from their refs
                List<Element> dropedElements = new ArrayList<>();
                for (MRef ref : refs) {
                    dropedElements.add((Element) this.model.findByRef(ref));
                }
                // Check the elements
                if (checkDragged(dropedElements)) {
                    // The controller will take care of cycles
                    this.controller.onAddDependency(dropedElements);
                    getViewer().refresh();
                    return true;
                }
            
            }
            return false;
        }

        /**
         * Check the given elements can be dragged ie:
         * <ul>
         * <li>Elements must be Artifact, stereotyped 'ModelComponentArchive'</li>
         * <li>Dropped ramcs must not cause a dependency cycle</li>
         * </ul>
         * 
         * @param elements The dragged elements
         * @return true if the given elements can be dragged.
         */
        @objid ("2b319cc4-10ab-48c7-adb8-be91e9bf35ce")
        private boolean checkDragged(List<Element> elements) {
            for (Element e : elements) {
                if (!e.isValid() || !this.controller.isRamcArtifact(e)) {
                    return false;
                }
            }
            return true;
        }

        @objid ("723aa00c-4ff1-42e6-99db-d71f98505c3f")
        private List<Element> getLocalDraggedElements() {
            List<Element> selectedElements = new ArrayList<>();
            ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
            if (selection instanceof IStructuredSelection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
                    Object o = i.next();
                    if (o instanceof IAdaptable) {
                        IAdaptable adapter = (IAdaptable) o;
                        Element element = adapter.getAdapter(Element.class);
                        if (element != null) {
                            selectedElements.add(element);
                        }
                    } else if (o instanceof Element) {
                        selectedElements.add((Element) o);
                    }
                }
            }
            return selectedElements;
        }

    }

}
