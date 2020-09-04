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

package org.modelio.creation.wizard.dialog;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.api.modelio.diagram.ContributorCategory;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.textelement.ITextElementSelectionListener;
import org.modelio.core.ui.swt.textelement.TextElement;
import org.modelio.creation.wizard.dialog.treeview.ContributorCategoryModel;
import org.modelio.creation.wizard.dialog.treeview.ContributorSorter;
import org.modelio.creation.wizard.dialog.treeview.ContributorTreeContentProvider;
import org.modelio.creation.wizard.dialog.treeview.ContributorTreeLabelProvider;
import org.modelio.creation.wizard.plugin.CreationWizard;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.ui.UIColor;
import org.modelio.ui.dialog.ModelioDialog;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;

@objid ("1f9f50ed-0a1e-458a-84a4-34fbd6da6f0c")
public abstract class CreationWizardDialog extends ModelioDialog {
    @objid ("6b1f8db1-ac8b-4f76-9610-cf5c5a30ad64")
    private TextElement contextText;

    @objid ("cfc0d2ef-a01d-4531-8ae2-b27e85853842")
    private IModelioPickingService pickingService;

    @objid ("92bcd34a-bc99-444f-95d6-b057ed2546e8")
    private IProjectService projectService;

    @objid ("5bea84f2-12d4-49a2-a86f-b1f96eb6daec")
     CreationWizardController controller;

    @objid ("2400bdf1-e2f1-42c9-84ca-2849f483456f")
    private IPanelProvider customArea;

    @objid ("22d53c61-bf05-4d22-a5f7-bdcca79f1e0f")
    private Button hideInvalidCheckBox;

    @objid ("ffc08777-c57e-4237-9eb8-644150fbb839")
    private Text nameText;

    @objid ("345bfafc-327c-429d-8ce6-ee50a78daca7")
    private TreeViewer treeViewer;

    @objid ("bada9cc3-6612-49b8-8518-c958126e0b96")
    private Composite composite;

    @objid ("f5635f74-0e3e-4912-93ef-11c1aa6924e5")
    private Composite editionArea;

    @objid ("8840406e-2461-4c50-9417-b4f0f744c05c")
    private Composite typeBrowserArea;

    @objid ("52e2480d-b76d-4a54-9aa6-3b5816804629")
    public CreationWizardDialog(final Shell parentShell, final CreationWizardModel dataModel, IProjectService projectService, IModelioPickingService pickingService) {
        super(parentShell);
        setShellStyle(SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX);
        
        this.projectService = projectService;
        this.pickingService = pickingService;
        
        this.controller = new CreationWizardController(this, dataModel);
    }

    @objid ("825a2d6a-684f-4ef1-806c-6b3a4b630b16")
    @Override
    public void addButtonsInButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @objid ("3ba41043-418f-478a-8245-5253493d105a")
    @Override
    public boolean close() {
        this.contextText.activatePicking(null);
        this.contextText.activateCompletion(null);
        
        if (this.customArea != null) {
            this.customArea.dispose();
            this.customArea = null;
        }
        
        this.composite.dispose();
        return super.close();
    }

    @objid ("3b6f4f10-775b-43dc-a80b-ca0028d58f1e")
    @Override
    public Control createContentArea(final Composite parent) {
        this.composite = new Composite(parent, SWT.BORDER);
        this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.composite.setLayout(new FormLayout());
        
        // Diagram type browser
        this.typeBrowserArea = createTypeBrowserArea(this.composite);
        // Edition area
        this.editionArea = createEditionArea(this.composite);
        
        // Layout the form
        FormData fd1 = new FormData();
        fd1.top = new FormAttachment(0, 0);
        fd1.bottom = new FormAttachment(100, 0);
        fd1.left = new FormAttachment(0, 0);
        fd1.right = new FormAttachment(40, 0);
        this.typeBrowserArea.setLayoutData(fd1);
        
        FormData fd2 = new FormData();
        fd2.top = new FormAttachment(0, 0);
        fd2.left = new FormAttachment(this.typeBrowserArea, 5);
        fd2.right = new FormAttachment(100, -5);
        this.editionArea.setLayoutData(fd2);
        return this.composite;
    }

    @objid ("dfed3213-6b30-48b3-bdf2-e34e2496fbe7")
    public CreationWizardModel getResultModel() {
        return this.controller.getDataModel();
    }

    @objid ("e6154d02-619b-4bdd-b0d5-fbb7e800b7fc")
    @Override
    public void init() {
        getShell().setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.ShellTitle"));
        
        setTitle(CreationWizard.I18N.getMessage("Ui.CreationWizard.Title"));
        setMessage(CreationWizard.I18N.getMessage("Ui.CreationWizard.Message"));
        
        addListeners();
        
        this.controller.onInit();
    }

    @objid ("c8eae388-4fc3-4b1d-9a4c-75fc5ec39fc3")
    void update(CreationWizardModel dataModel) {
        // Edition area
        updateEditionArea(dataModel);
        
        // Diagram type browser
        updateTypeBrowserArea(dataModel);
        
        // Custom area
        updateCustomArea(dataModel);
        
        // Ok button
        updateOkButton(dataModel.isValid());
    }

    @objid ("763f832c-825a-47d3-aca8-2c4ed70d988b")
    @Override
    protected abstract String getHelpId();

    @objid ("c9247f5a-8c13-47bd-939d-c065b6b564c2")
    @Override
    protected void okPressed() {
        this.controller.onOk();
        super.okPressed();
    }

    @objid ("c9db9dd4-a9cf-4e08-b462-28a675e0e7d6")
    private void updateOkButton(boolean enabled) {
        getButton(IDialogConstants.OK_ID).setEnabled(enabled);
    }

    @objid ("db126456-06e9-401e-9ad7-2c6940f35530")
    private void addListeners() {
        this.nameText.addModifyListener(new ModifyListener() {
        
            @Override
            public void modifyText(ModifyEvent e) {
                CreationWizardDialog.this.controller.onNameChanged(((Text) e.getSource()).getText());
            }
        });
        
        this.hideInvalidCheckBox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CreationWizardDialog.this.controller.onHideInvalidContributors(((Button) e.getSource()).getSelection());
            }
        });
        
        this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                CreationWizardDialog.this.controller.onContributorSelection(
                        SelectionHelper.getFirst(event.getSelection(), IWizardContributor.class));
        
            }
        });
        this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
        
            @Override
            public void doubleClick(DoubleClickEvent event) {
                // Double click to create the selected contribution if ok button
                // is enabled
                if (getButton(IDialogConstants.OK_ID).isEnabled()) {
                    okPressed();
                }
            }
        });
        
        this.contextText.addListener(new ITextElementSelectionListener() {
        
            @Override
            public void selectedElementChanged(MObject oldElement, MObject newElement) {
                CreationWizardDialog.this.controller.onContextChanged(oldElement, newElement);
        
            }
        });
    }

    @objid ("a1850387-e800-4bd9-9c5c-7dad3b3cc6d3")
    private Composite createEditionArea(Composite parent) {
        Group group = new Group(parent, SWT.BORDER_SOLID);
        group.setLayout(new GridLayout(2, false));
        
        group.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.IdGroup.label"));
        
        Label nameLabel = new Label(group, SWT.NONE);
        nameLabel.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.Name"));
        nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        this.nameText = new Text(group, SWT.BORDER);
        GridDataFactory.fillDefaults().grab(true, false).hint(-1, 20).applyTo(this.nameText);
        
        Label contextLabel = new Label(group, SWT.NONE);
        contextLabel.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.Context"));
        contextLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        
        this.contextText = new TextElement(group, SWT.BORDER, false);
        this.contextText.activatePicking(this.pickingService);
        this.contextText.activateCompletion(this.projectService.getSession());
        this.contextText.setFilter(null);
        this.contextText.activateDragAndDrop(this.projectService.getSession());
        GridDataFactory.fillDefaults().grab(true, false).hint(-1, 20).applyTo(this.contextText.getTextControl());
        return group;
    }

    @objid ("6a797241-16ae-46f8-9186-896ee4f64f22")
    private Composite createTypeBrowserArea(Composite parent) {
        Group group = new Group(parent, SWT.NONE);
        group.setLayout(new GridLayout(1, false));
        group.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.TypeGroup.label"));
        
        // Diagrams list tree viewer
        this.treeViewer = new TreeViewer(group, SWT.V_SCROLL | SWT.BORDER);
        this.treeViewer.setContentProvider(
                new ContributorTreeContentProvider(this.controller.getDataModel().getContext(), false));
        this.treeViewer.setLabelProvider(new ContributorTreeLabelProvider(null));
        this.treeViewer.setComparator(new ContributorSorter(null));
        this.treeViewer.setInput(new ContributorCategoryModel(this.controller.getContributorsMap()));
        this.treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        // Hide invalid checkbox
        this.hideInvalidCheckBox = new Button(group, SWT.CHECK);
        this.hideInvalidCheckBox.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.HideInvalid"));
        this.hideInvalidCheckBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        return group;
    }

    @objid ("e93ce2b3-adae-4cb1-b0f0-340bf006671b")
    @Override
    protected Point getInitialSize() {
        return new Point(900, 800);
    }

    @objid ("7c758e2c-fedc-4225-b922-73a67c5d6e4c")
    void updateTypeBrowserArea(CreationWizardModel dataModel) {
        // Configure the labelProvider
        ContributorTreeLabelProvider lp = (ContributorTreeLabelProvider) this.treeViewer.getLabelProvider();
        lp.setContext(dataModel.getContext());
        
        // Update tree selection
        if (this.treeViewer.getSelection().isEmpty()) {
            IWizardContributor selectedContributor = dataModel.getSelectedContributor();
            if (selectedContributor != null) {
                this.treeViewer.refresh(true);
                this.treeViewer.setSelection(new StructuredSelection(selectedContributor), true);
            }
        }
        
        this.hideInvalidCheckBox.setSelection(!dataModel.isShowInvalid());
    }

    @objid ("db9a2b63-4b40-48e3-bc0c-b4a6830e14e4")
    void updateEditionArea(CreationWizardModel dataModel) {
        IWizardContributor selectedContributor = dataModel.getSelectedContributor();
        
        // Reconfigure the TextElement field
        if (selectedContributor != null) {
            this.editionArea.setVisible(true);
        
            // Update the 'context' TextElement field allowed metaclasses
            this.contextText.getAcceptedMetaclasses().clear();
            for (ElementScope scope : selectedContributor.getScopes()) {
                this.contextText.getAcceptedMetaclasses().add(scope.getMetaclass());
            }
        } else {
            this.editionArea.setVisible(false);
        }
        // Update the 'context' field filter
        IMObjectFilter elementFilter = new IMObjectFilter() {
            @Override
            public boolean accept(MObject element) {
                if (selectedContributor != null) {
                    return selectedContributor.accept(element);
                } else {
                    return false;
                }
            }
        };
        this.contextText.setFilter(elementFilter);
        this.contextText.getTextControl().setForeground(UIColor.RED);
        this.contextText.setAcceptNullValue(false);
        
        this.contextText.setValue(dataModel.getContext());
        if (selectedContributor != null) {
            setMessage(selectedContributor.getInformation());
            if (selectedContributor.accept(dataModel.getContext())) {
                this.contextText.getTextControl().setForeground(UIColor.BLACK);
            }
        } else {
            setMessage(CreationWizard.I18N.getMessage("Ui.CreationWizard.Message"));
        }
        
        if (!this.nameText.getText().equals(dataModel.getName())) {
            this.nameText.setText(dataModel.getName());
        }
    }

    @objid ("0b089dc8-df54-49b9-a2f3-259dd85115d8")
    @Override
    protected void cancelPressed() {
        this.controller.onCancel();
        super.cancelPressed();
    }

    @objid ("0a24a639-991b-4444-b062-32a56ea4accc")
    void updateCustomArea(CreationWizardModel dataModel) {
        IWizardContributor contributor = dataModel.getSelectedContributor();
        if (this.customArea != null) {
            if (this.customArea.getInput() == contributor) {
                return;
            } else {
                this.customArea.dispose();
                this.customArea = null;
            }
        }
        
        if (contributor != null) {
            IPanelProvider customPanel = contributor.getWizardPanel();
            if (customPanel != null) {
                // Use new panel from contributor
                this.customArea = customPanel;
            } else {
                // Create a standard WizardPreviewPanel
                this.customArea = new WizardPreviewPanel();
            }
        
            Control comp = (Control) this.customArea.createPanel(this.composite);
        
            FormData fd = new FormData();
            fd.top = new FormAttachment(this.editionArea, 5);
            fd.bottom = new FormAttachment(100, 0);
            fd.left = new FormAttachment(this.typeBrowserArea, 5);
            fd.right = new FormAttachment(100, -4);
            comp.setLayoutData(fd);
        
            this.customArea.setInput(contributor);
            this.nameText.setText(contributor.getLabel());
        }
        
        this.composite.layout(true);
    }

    /**
     * Creation wizard controller.
     */
    @objid ("d22dc0dc-3e1f-425d-917c-cff5eb52f739")
    private static class CreationWizardController {
        @objid ("4bc28791-2a60-4557-a959-3ff91ca718d8")
        private CreationWizardDialog dwDialog;

        @objid ("2e9c40bf-828d-43d8-bf30-d6147fdb7f64")
        private CreationWizardModel dwData;

        @objid ("813eed21-42ce-4cff-b2d2-2339083c8f8e")
        public void onNameChanged(String s) {
            if (!Objects.equals(this.dwData.getName(), s)) {
                this.dwData.setName(s);
                this.dwDialog.update(this.dwData);
            }
        }

        @objid ("86bba2b1-bc31-47eb-94d8-7522ea4866db")
        public void onContextChanged(MObject oldElement, MObject newElement) {
            if (oldElement != newElement) {
                this.dwData.setContext((ModelElement) newElement);
                this.dwDialog.update(this.dwData);
            }
        }

        @objid ("0e5ed87e-f73d-41d0-91d0-0dbcc04de42b")
        public void onContributorSelection(IWizardContributor selectedContributor) {
            if (!Objects.equals(this.dwData.getSelectedContributor(), selectedContributor)) {
                this.dwData.setSelectedContributor(selectedContributor);
                this.dwDialog.update(this.dwData);
            }
        }

        @objid ("07bd578d-1175-457b-85db-e917da56ecfd")
        public void onHideInvalidContributors(final boolean hideInvalidContributors) {
            // when changing to hid invalid contributors
            // and if the currently selectedContributor is not valid for the
            // current context
            // clear the texts and messages
            if (hideInvalidContributors) {
                IWizardContributor selectedContributor = this.dwData.getSelectedContributor();
                if (selectedContributor != null && !selectedContributor.accept(this.dwData.getContext())) {
                    this.dwData.setSelectedContributor(null);
                }
            }
            
            this.dwData.setShowInvalid(!hideInvalidContributors);
            
            // Configure tree content provider
            ContributorTreeContentProvider cp = (ContributorTreeContentProvider) this.dwDialog.treeViewer
                    .getContentProvider();
            cp.setShowInvalid(!hideInvalidContributors);
            cp.setContext(this.dwData.getContext());
            
            // Refresh tree input
            this.dwDialog.treeViewer.setInput(this.dwDialog.treeViewer.getInput());
            
            this.dwDialog.update(this.dwData);
        }

        @objid ("9454e825-dea3-4ba4-a1d3-121a42caed1a")
        public CreationWizardController(CreationWizardDialog dwDialog, CreationWizardModel dwModel) {
            this.dwData = dwModel;
            this.dwDialog = dwDialog;
        }

        @objid ("147fe5f7-acd4-4b82-8d9f-4e228415f5c2")
        public void onInit() {
            if (this.dwData.getSelectedContributor() == null) {
                ModelElement initialContext = this.dwData.getContext();
                MObject context = initialContext;
            
                List<IWizardContributor> validContributors = getValidContributors(context);
                while (validContributors.size() == 0 && context != null) {
                    context = context.getCompositionOwner();
                    validContributors = getValidContributors(context);
                }
            
                if (validContributors.size() > 0) {
                    IWizardContributor classDiagramContributor = null;
                    IWizardContributor firstUmlDiagramContributor = null;
                    IWizardContributor firstDiagramContributor = null;
                    IWizardContributor firstOtherContributor = null;
            
                    // Take first diagram contribution as default
                    for (IWizardContributor contributor : validContributors) {
                        if (contributor instanceof IDiagramWizardContributor) {
                            if (classDiagramContributor == null && contributor.getClass().getSimpleName()
                                    .equals("ClassDiagramCreationContributor")) {
                                classDiagramContributor = contributor;
                            } else if (firstUmlDiagramContributor == null && contributor.getClass().getPackage()
                                    .getName().equals("org.modelio.diagram.editor.statik.contributor")) {
                                firstUmlDiagramContributor = contributor;
                            } else if (firstDiagramContributor == null) {
                                firstDiagramContributor = contributor;
                            }
                        } else if (firstOtherContributor == null) {
                            firstOtherContributor = contributor;
                        }
                    }
            
                    /*
                     * Select a contributor: - class diagram is always first - first UML diagram is class diagram isn't valid - first diagram when no UML diagram is valid - last chance fallback, use first contributor
                     */
                    if (classDiagramContributor != null) {
                        this.dwData.setSelectedContributor(classDiagramContributor);
                    } else if (firstUmlDiagramContributor != null) {
                        this.dwData.setSelectedContributor(firstUmlDiagramContributor);
                    } else if (firstDiagramContributor != null) {
                        this.dwData.setSelectedContributor(firstDiagramContributor);
                    } else {
                        this.dwData.setSelectedContributor(firstOtherContributor);
                    }
            
                    this.dwData.setContext((ModelElement) context);
                }
            } else {
                // Get the first valid context...
                IWizardContributor contributor = this.dwData.getSelectedContributor();
                ModelElement initialContext = this.dwData.getContext();
                MObject context = initialContext;
            
                while (!contributor.accept(context) && context != null) {
                    context = context.getCompositionOwner();
                }
                this.dwData.setSelectedContributor(contributor);
                this.dwData.setContext((ModelElement) context);
            }
            this.dwDialog.update(this.dwData);
        }

        @objid ("80ddb03c-1892-4fff-956d-3c8610abdfdf")
        private List<IWizardContributor> getValidContributors(final MObject context) {
            List<IWizardContributor> result = new ArrayList<>();
            for (IWizardContributor type : this.dwData.getAllContributors(this.dwData.getTypeFilter())) {
                if (context != null) {
                    if (type.accept(context)) {
                        result.add(type);
                    }
                }
            }
            return result;
        }

        @objid ("d72a3e0f-1648-41d2-a582-0f87d9921d14")
        public void onOk() {
            // nothing to do
        }

        @objid ("9cfe0cb3-e68c-400a-a140-c236087e2e39")
        public void onCancel() {
            this.dwData = null;
        }

        @objid ("6b72360b-b8b5-4204-a32c-2f19854fd366")
        public CreationWizardModel getDataModel() {
            return this.dwData;
        }

        @objid ("4655c1ed-c0ad-46a3-bd8a-7e6b5adf85fc")
        public Map<ContributorCategory, List<IWizardContributor>> getContributorsMap() {
            Class<? extends IWizardContributor> filter = this.dwData.getTypeFilter();
            return this.dwData.getContributorsMap(filter);
        }

    }

    /**
     * Panel provider working with a {@link IWizardContributor}.
     */
    @objid ("879a9d0c-028a-4b1d-9ae0-372e9a31ef81")
    private static class WizardPreviewPanel implements IPanelProvider {
        @objid ("5fced0a3-fc1e-4fd2-a084-4cf2be67dc76")
        private WizardPreviewPanelController controller;

        @objid ("8299ea2e-79c1-43c4-a34e-2fbe8926f84d")
        public WizardPreviewPanel() {
            this.controller = new WizardPreviewPanelController();
        }

        @objid ("be80a002-2d88-4094-941e-c74f691e4bc0")
        @Override
        public boolean isRelevantFor(Object obj) {
            return false;
        }

        @objid ("4824bd53-e1e7-4931-b670-4792d2cfec26")
        @Override
        public Control createPanel(Composite parent) {
            return this.controller.createUi(parent);
        }

        @objid ("3d0eee78-dbee-4d19-810b-5112cbca068e")
        @Override
        public Control getPanel() {
            return this.controller.getUi();
        }

        @objid ("1ef88e3f-b9e9-41bf-a2d6-fbae474f4802")
        @Override
        public IWizardContributor getInput() {
            return this.controller.getData();
        }

        @objid ("755d1fd2-0929-47a3-82b6-bcaad48fddf8")
        @Override
        public void setInput(Object input) {
            if (input instanceof IWizardContributor) {
                this.controller.setData(input);
            } else {
                this.controller.setData(null);
            }
        }

        @objid ("7ab495c6-5e39-43d7-9930-46a7d7138d1e")
        @Override
        public void dispose() {
            this.controller.dispose();
        }

        @objid ("65f1edf2-6bf4-4803-bf4d-4c14b8a64faf")
        @Override
        public String getHelpTopic() {
            return null;
        }

        @objid ("0c14d5e4-6049-486a-817b-7ed2827db29e")
        private static class WizardPreviewPanelUI {
            @objid ("f7106d19-006b-4c44-ade3-adadda811211")
            private static final int PREVIEW_HEIGHT = 300;

            @objid ("636d4717-6b66-4b4b-9b62-142edcaf66e3")
            private static final int PREVIEW_WIDTH = 400;

            @objid ("ff2744cd-a92d-43f4-bb89-605f2cb47fda")
            private WizardPreviewPanelController controller;

            @objid ("db647710-1c20-4186-85e8-c9171035432d")
            private Group previewGroup = null;

            @objid ("74441de9-990a-49ea-afdf-6bdb166cc9da")
            private Label previewImage;

            @objid ("5a6fb4a3-bc27-48cc-a043-9858afbf8f1d")
            private StyledText detailsText;

            @objid ("98081a2b-a72e-486c-ac10-b1007e80a7ae")
            private Link detailsLink;

            @objid ("4e6702fa-7787-433b-b4ee-4fa24c449722")
            public WizardPreviewPanelUI(WizardPreviewPanelController controller) {
                this.controller = controller;
            }

            @objid ("f9bf0179-64da-446d-89f5-a3b7d0ec97a9")
            public Control createUI(Composite parent) {
                this.previewGroup = new Group(parent, SWT.BORDER_SOLID);
                this.previewGroup.setText(CreationWizard.I18N.getMessage("Ui.CreationWizard.PreviewGroup.label"));
                this.previewGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
                this.previewGroup.setLayout(new GridLayout(1, false));
                
                this.previewImage = new Label(this.previewGroup, SWT.BORDER);
                this.previewImage.setSize(WizardPreviewPanelUI.PREVIEW_WIDTH, WizardPreviewPanelUI.PREVIEW_HEIGHT);
                GridData gd = new GridData();
                gd.heightHint = WizardPreviewPanelUI.PREVIEW_HEIGHT;
                gd.widthHint = WizardPreviewPanelUI.PREVIEW_WIDTH;
                gd.horizontalAlignment = SWT.CENTER;
                this.previewImage.setLayoutData(gd);
                
                this.detailsText = new StyledText(this.previewGroup, SWT.MULTI | SWT.WRAP);
                this.detailsText.setForeground(UIColor.LABEL_TIP_FG);
                this.detailsText.setEditable(false);
                this.detailsText.setBackground(this.previewGroup.getBackground());
                
                GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, true);
                this.detailsText.setLayoutData(gd2);
                
                this.detailsLink = new Link(this.previewGroup, SWT.NONE);
                GridData gd3 = new GridData(SWT.FILL, SWT.FILL, true, false);
                this.detailsLink.setLayoutData(gd3);
                
                // Install listeners
                this.detailsLink.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        WizardPreviewPanelUI.this.controller
                                .onDetailsLinkSelected((String) ((Link) e.getSource()).getData("url"));
                    }
                });
                return this.previewGroup;
            }

            @objid ("425f299f-379f-4948-9337-c84aa776ed30")
            public void dispose() {
                if (this.previewImage.getImage() != null) {
                    this.previewImage.getImage().dispose();
                    this.previewImage.setImage(null);
                }
                this.previewGroup.dispose();
            }

            @objid ("401b9fed-28be-453b-ba82-d63b9caba247")
            private void switchPreviewImage(ImageDescriptor imageDescriptor) {
                // Dispose existing preview image
                Image img = this.previewImage.getImage();
                if (img != null) {
                    this.previewImage.setImage(null);
                    img.dispose();
                }
                
                // Create and setup the new preview image
                if (imageDescriptor != null) {
                    this.previewImage.setImage(imageDescriptor.createImage());
                } else {
                    Bundle bundle = CreationWizard.getContext().getBundle();
                    URL imageUrl = FileLocator.find(bundle, new Path("images/noimagepreview400x300.png"), null);
                    this.previewImage.setImage(ImageDescriptor.createFromURL(imageUrl).createImage());
                }
            }

            @objid ("cde581a7-a498-44f9-953f-52e6c72b560d")
            public void update(IWizardContributor selectedContributor) {
                // Update the preview text and image
                if (selectedContributor != null) {
                    // Preview text
                    this.detailsText.setText(selectedContributor.getDetails());
                    // Preview image
                    switchPreviewImage(selectedContributor.getPreviewImage());
                    // Details link
                    String helpUrl = selectedContributor.getHelpUrl();
                    this.detailsLink
                            .setText((helpUrl != null) ? CreationWizard.I18N.getMessage("Ui.CreationWizard.More") : "");
                    this.detailsLink.setData("url", helpUrl);
                } else {
                    this.detailsText.setText("");
                    switchPreviewImage(null);
                    this.detailsLink.setText("");
                    this.detailsLink.setData("url", null);
                }
            }

        }

        @objid ("4b0048ac-c73c-4564-b9c1-0f26e03b8da5")
        private static class WizardPreviewPanelController {
            @objid ("21422e77-b260-48b9-a263-661a6b0ec202")
            private IWizardContributor data;

            @objid ("a887bf2f-aa70-4883-9bbc-f3c6d82c0cac")
            private WizardPreviewPanelUI ui;

            @objid ("4e89f0eb-a232-4a29-bb4e-fe8975c458c7")
            public IWizardContributor getData() {
                return this.data;
            }

            @objid ("1f699372-1f69-4a1e-b6b1-f6790b386556")
            public void setData(Object data) {
                this.data = (IWizardContributor) data;
                if (this.ui != null) {
                    this.ui.update(this.data);
                }
            }

            @objid ("d91b349a-63c4-45ea-886b-bb9457f2f2ba")
            public Control createUi(Composite parent) {
                this.ui = new WizardPreviewPanelUI(this);
                Control control = this.ui.createUI(parent);
                this.ui.update(this.data);
                return control;
            }

            @objid ("8eabe0f4-a59d-489a-b9a2-6e29fd6c97af")
            public Control getUi() {
                return this.ui.previewGroup;
            }

            @objid ("68393e2b-de2c-40a0-ac24-7a5464b46308")
            public void dispose() {
                this.ui.dispose();
                this.ui = null;
            }

            @objid ("9b9d908e-b3fa-4503-b2b3-a5c1a9cb4075")
            public void onDetailsLinkSelected(String helpUrl) {
                if (helpUrl != null) {
                    BrowserDialog dialog = new BrowserDialog(getUi().getShell(), helpUrl);
                    dialog.open();
                }
            }

        }

    }

}
