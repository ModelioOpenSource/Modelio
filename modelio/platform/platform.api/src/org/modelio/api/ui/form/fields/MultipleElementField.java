/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.form.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.navigation.INavigationService;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.plugin.Api;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.api.ui.labelprovider.ElementLabelProvider;
import org.modelio.api.ui.swt.SelectionHelper;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIFont;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("820c783c-1e2b-403d-90e3-c79f6f5b4ecd")
public class MultipleElementField extends AbstractField {
    @objid ("0ff26b1b-5c04-4419-8e93-29abdf175dd9")
    private boolean editable;

    @objid ("3481aa81-af9c-4831-9d1f-5308dfeb83d4")
    private ILabelProvider labelProvider = new ElementLabelProvider();

    @objid ("ffa8855f-8abd-4562-9e18-31ce099ee431")
    private final INavigationService navigationService;

    @objid ("20b6b936-023b-4dbf-8de1-d84e3d5d1dc7")
    private Text text;

    @objid ("c4721011-0767-4d7b-b63f-9a1bec1ecc2b")
    private List<ModelElement> values = Collections.emptyList();

    @objid ("a37337b3-9ec1-4bed-a9a5-3b44c9d923c2")
    private IModelingSession session;

    @objid ("6b3809d6-a1dd-43be-8255-2936267bf5dc")
    private List<Class<? extends MObject>> allowedMetaclasses = new ArrayList<>();

    @objid ("8c057806-e91e-4b1d-a227-2ba7d3dcdd8e")
    public MultipleElementField(IModuleContext moduleContext, FormToolkit toolkit, Composite parent, IFormFieldData model) {
        super(toolkit, parent, model);
        this.editable = true;
        this.navigationService = moduleContext.getModelioServices().getNavigationService();
        this.session = moduleContext.getModelingSession();
    }

    @objid ("6cf0a95b-ea2c-4f32-9064-724f6266d184")
    @Override
    public void apply() {
        getModel().setValue(this.values);
    }

    @objid ("0246636c-64b8-40bf-a516-5df4b8862adc")
    @Override
    public Control createControl(FormToolkit tk, Composite p) {
        this.text = tk.createText(p, "", SWT.NONE);
        
        // Initialize values
        getLabel().setText(getModel().getName());
        refresh();
        
        // Install Listeners
        // Switch into edition on a simple click
        
        this.text.addListener(SWT.MouseDown, e -> {
            if (this.editable) {
                enterEdition();
            }
        });
        
        this.text.setEditable(false);
        return this.text;
    }

    @objid ("ae8aa456-bbe4-4cb0-b7d8-c437e2178d06")
    @Override
    @SuppressWarnings("unchecked")
    public void refresh() {
        this.values = (List<ModelElement>) getModel().getValue();
        if (this.text != null) {
            this.text.setText(computeStringRepresentation());
        }
    }

    @objid ("5048f559-bd8f-4b5c-9914-936d55a22399")
    @Override
    public void setEditable(boolean onoff) {
        super.setEditable(onoff);
        this.editable = onoff;
    }

    /**
     * Set the label provider used by the field to build its representation.
     */
    @objid ("c0f2bc3d-3342-4e1e-a340-ec24eba0e6a6")
    public void setLabelProvider(ILabelProvider labelProvider) {
        this.labelProvider = labelProvider;
    }

    @objid ("15e552a7-e4be-429c-9be3-7fc4d3b89b2b")
    protected void enterEdition() {
        Set<MObject> candidates = new HashSet<>();
        for (Class<? extends MObject> metaclass : this.allowedMetaclasses) {
            candidates.addAll(this.session.findByClass(metaclass));
        }
        
        SelectElementsPanel panel = new SelectElementsPanel(candidates, this.labelProvider, this.navigationService);
        panel.setInput(this.values);
        
        ThinDialog td = new ThinDialog(getControl(), panel) {
            @SuppressWarnings ("unchecked")
            @Override
            public void onOk() {
                List<ModelElement> oldValue = MultipleElementField.this.values;
                MultipleElementField.this.values = (List<ModelElement>) getPanelProvider().getInput();
        
                fireValueChanged(oldValue, MultipleElementField.this.values);
        
                // Close the dialog
                super.onOk();
        
                MultipleElementField.this.text.setText(computeStringRepresentation());
            }
        
        };
        
        td.open();
    }

    @objid ("97ec09d2-3491-4fa4-b160-26e6da33ab69")
    private String computeStringRepresentation() {
        // Get the list of individual elements from the content provider
        // Concatenate their labels obtained from the label provider, separated by ", "
        return this.values.stream().map(e -> this.labelProvider.getText(e)).collect(Collectors.joining(", "));
    }

    @objid ("6d82905e-69ef-4074-beb1-10f725702343")
    public MultipleElementField(IModuleContext moduleContext, FormToolkit toolkit, Composite parent, IFormFieldData model, List<Class<? extends MObject>> allowedMetaclasses) {
        this(moduleContext, toolkit, parent, model);
        this.allowedMetaclasses.addAll(allowedMetaclasses);
    }

    /**
     * <p>
     * The SelectElementsPanel is an implementation of IPanelProvider that can be used to select several MObjects from a list of
     * <em>candidates</em> into a <em>results</em> list.
     * </p>
     * <p>
     * The <em>candidates</em> list is passed as a list of MObject.
     * </p>
     * <p>
     * The initial list of <em>results</em> has to be passed using the <em>setInput()</em> method. It must be a non-null, possibly
     * non-empty, modifiable List&lt;MObject&gt;.
     * </p>
     * <p>
     * &nbsp;
     * </p>
     */
    @objid ("f1f199b1-1214-42bc-b955-c08202edc58c")
    private static class SelectElementsPanel implements IPanelProvider {
        @objid ("db227e2b-6e20-4b59-b2b4-740d88835b1e")
        private final Controller controler;

        @objid ("c1ffbd4e-9e2b-462d-a92c-29b65007e01c")
        private ILabelProvider labelProvider;

        /**
         * C'tor
         * 
         * @param candidates the candidates
         * @param labelProvider the label provider for candidates
         * @param navigationService the Modelio navigation service.
         */
        @objid ("62be9c23-2e0a-41e8-be57-3e4120d8bc41")
        public SelectElementsPanel(Collection<MObject> candidates, ILabelProvider labelProvider, INavigationService navigationService) {
            this.controler = new Controller(navigationService, candidates);
            this.labelProvider = labelProvider != null ? labelProvider : new LabelProvider();
        }

        /**
         * C'tor
         */
        @objid ("4c4ba4b2-9656-4883-837d-67bba0c9ee8e")
        public SelectElementsPanel(Collection<MObject> candidates, INavigationService navigationService) {
            this(candidates, null, navigationService);
        }

        @objid ("d7ca2304-ed65-42c2-8b6c-f8cebee32dad")
        @Override
        public boolean isRelevantFor(Object obj) {
            return (obj instanceof List<?>);
        }

        @objid ("c9656dd7-9205-4610-89ec-9ea4c1135414")
        @Override
        public Composite createPanel(Composite parent) {
            View view = new View(parent, this.labelProvider, this.controler);
            this.controler.setView(view);
            return view.getContainer();
        }

        @objid ("d0a0ed34-251f-48df-a767-1f1ae194db6f")
        @Override
        public Composite getPanel() {
            return this.controler.view.getContainer();
        }

        @objid ("e33ca63c-fb50-4edc-b577-a751c88d2ef9")
        @Override
        public String getHelpTopic() {
            return null;
        }

        @objid ("92cf65f9-ce76-4427-8d9a-f82edfb0038a")
        @Override
        public Object getInput() {
            return this.controler.getResults();
        }

        @objid ("94ea43f4-9d56-4844-85e3-99ff024e36a5")
        @Override
        @SuppressWarnings("unchecked")
        public void setInput(Object input) {
            this.controler.setInitialResults((List<MObject>) input);
        }

        @objid ("78ec084d-88bc-46b9-b2ad-34b3f7693e6c")
        @Override
        public void dispose() {
            this.controler.dispose();
        }

        @objid ("adfa5771-2897-4fe0-944d-96b322ce32dd")
        public void setLabelProvider(ILabelProvider labelProvider) {
            this.labelProvider = labelProvider;
        }

        @objid ("eeb0afd2-ebce-475c-b2cc-5ef6ed5e1570")
        private static class View {
            @objid ("b100fb77-6829-4288-973b-ea128148435d")
            private final Controller controler;

            @objid ("310b5223-6553-4ffe-bc47-0fb2ad7c327b")
            private final Composite container;

            @objid ("2a3c3e5d-8248-4af5-8e42-d194cbdc38fe")
            private TableViewer candidates;

            @objid ("7b664259-0eaf-4609-b6f7-996c0575360c")
            private TableViewer results;

            @objid ("c4c9ef15-f0fc-4673-8c29-664e98b43df5")
            private Button addButton;

            @objid ("39fe75f7-3da0-47ef-9d0e-72b2c1de0490")
            private Button removeButton;

            @objid ("a1aa3371-2b59-4e1b-8499-a8907fb98898")
            private Label candidatesStatusLabel;

            @objid ("391de541-ca54-49c1-a647-fe51258267cd")
            private Label resultsStatusLabel;

            @objid ("53a907a2-b5aa-44ee-a931-f0a5a23836cc")
            private final ILabelProvider labelProvider;

            @objid ("ccd18c4f-7445-42b4-a31b-ee185232c55f")
            private Button upButton;

            @objid ("e882e5ba-df90-4b06-a079-efe9e9ecdf0e")
            private Button downButton;

            @objid ("30b4e4f1-b182-4352-ba89-8c9864dbd3b5")
            public View(Composite parent, ILabelProvider labelProvider, Controller controler) {
                this.controler = controler;
                this.labelProvider = labelProvider;
                this.container = createGui(parent);
            }

            @objid ("686ef68d-93e0-46be-bc63-1d712bf3fe6a")
            private Composite createGui(Composite parent) {
                final Composite composite = new Composite(parent, SWT.NONE);
                composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
                composite.setLayout(new FormLayout());
                
                // Candidates group
                Group candidatesGroup = createCandidatesGroup(composite);
                // Command buttons group
                Composite commandsGroup = createCommandsGroup(composite);
                // Selected Elements group
                Group selectionGroup = createSelectionGroup(composite);
                
                // candidatesGroup attachments
                FormData formData = new FormData();
                formData.top = new FormAttachment(0, 2);
                formData.left = new FormAttachment(0, 2);
                formData.right = new FormAttachment(commandsGroup, 0, SWT.LEFT);
                formData.bottom = new FormAttachment(100, -2);
                candidatesGroup.setLayoutData(formData);
                
                // selectionGroup attachments
                formData = new FormData();
                formData.top = new FormAttachment(0, 2);
                formData.right = new FormAttachment(100, -2);
                formData.left = new FormAttachment(commandsGroup, 0, SWT.RIGHT);
                formData.bottom = new FormAttachment(100, -2);
                selectionGroup.setLayoutData(formData);
                
                // commandsGroup attachments
                formData = new FormData();
                formData.top = new FormAttachment(candidatesGroup, 0, SWT.CENTER);
                formData.left = new FormAttachment(50, -20);
                formData.right = new FormAttachment(50, 20);
                // formData.bottom = new FormAttachment(100, -5);
                commandsGroup.setLayoutData(formData);
                return composite;
            }

            @objid ("d4b817e4-6d95-4eb8-94fa-561aa26442f4")
            private Group createCandidatesGroup(Composite composite) {
                final Group candidateGroup = new Group(composite, SWT.NONE);
                candidateGroup.setLayout(new GridLayout(1, false));
                candidateGroup.setText(Api.I18N.getString("MultipleElementsField.CandidateElements"));
                
                // Candidates list
                this.candidates = new TableViewer(candidateGroup, SWT.BORDER | SWT.MULTI);
                this.candidates.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
                
                this.candidates.setContentProvider(new ArrayContentProvider());
                this.candidates.setLabelProvider(this.labelProvider);
                
                this.candidates.setComparator(new ViewerComparator());
                
                // Status label
                this.candidatesStatusLabel = new Label(candidateGroup, SWT.NONE);
                this.candidatesStatusLabel.setText("...");
                this.candidatesStatusLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
                
                this.candidatesStatusLabel.setFont(CoreFontRegistry.getModifiedFont(this.candidatesStatusLabel.getFont(),
                        SWT.ITALIC, UIFont.SMALL_SIZE));
                
                // Listeners and behavior
                
                // Double click listener: add element
                this.candidates.addDoubleClickListener(
                        event -> this.controler.onAdd(toObjectsList(event.getSelection())));
                
                // Selection change
                // - fire controler
                // - update status label
                this.candidates.addSelectionChangedListener(event -> {
                    this.controler.onSelectCandidate(toObjectsList(event.getSelection()));
                    updateStatusLabel(View.this.candidatesStatusLabel, (TableViewer) event.getSource());
                });
                
                // <CTRL> + <ALT> + right-click navigates to the selected element
                this.candidates.getTable().addListener(SWT.MouseUp, e -> {
                    // <CTRL><ALT> click
                    if ((e.button == 1) && (e.stateMask & (SWT.CTRL | SWT.ALT)) != 0) {
                        this.controler.fireNavigate(SelectionHelper.toList(this.results.getSelection(), MObject.class));
                    }
                });
                return candidateGroup;
            }

            @objid ("b17326df-b981-424c-bc99-ef45010569ba")
            private Group createSelectionGroup(Composite composite) {
                final Group resultsGroup = new Group(composite, SWT.SHADOW_NONE);
                resultsGroup.setLayout(new GridLayout(1, true));
                
                resultsGroup.setText(Api.I18N.getString("MultipleElementsField.ChoosenElements"));
                
                // New content table
                this.results = new TableViewer(resultsGroup, SWT.BORDER | SWT.MULTI);
                GridData fd_contentTree = new GridData(SWT.FILL, SWT.FILL, true, true);
                this.results.getTable().setLayoutData(fd_contentTree);
                
                this.results.setContentProvider(new ArrayContentProvider());
                this.results.setLabelProvider(this.labelProvider);
                
                // Status label
                this.resultsStatusLabel = new Label(resultsGroup, SWT.NONE);
                this.resultsStatusLabel.setText("...");
                this.resultsStatusLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
                this.resultsStatusLabel.setFont(CoreFontRegistry.getModifiedFont(this.resultsStatusLabel.getFont(), SWT.ITALIC,
                        UIFont.SMALL_SIZE));
                
                // Double click removes the element
                this.results.addDoubleClickListener(
                        event -> this.controler.onRemove(toObjectsList(event.getSelection())));
                
                // Listeners and behavior
                
                // Selection change
                // - fire controller
                // - update status label
                this.results.addSelectionChangedListener(event -> {
                    this.controler.onSelectResult(toObjectsList(event.getSelection()));
                    updateStatusLabel(View.this.resultsStatusLabel, (TableViewer) event.getSource());
                });
                
                // <ctrl> <alt>+ right click navigates to the selected element
                this.results.getTable().addListener(SWT.MouseUp, e -> {
                    // <CTRL><ALT>Right click
                    if ((e.button == 3) && (e.stateMask & (SWT.CTRL | SWT.ALT)) != 0) {
                        this.controler.fireNavigate(SelectionHelper.toList(this.results.getSelection(), MObject.class));
                    }
                });
                return resultsGroup;
            }

            @objid ("74b70e85-d1f8-4a77-8735-20ac80aa8f7f")
            private Composite createCommandsGroup(Composite parent) {
                final Composite buttonsGroup = new Composite(parent, SWT.NONE);
                buttonsGroup.setLayout(new GridLayout(1, true));
                
                // Add button
                this.addButton = new Button(buttonsGroup, SWT.ARROW | SWT.PUSH);
                GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
                this.addButton.setLayoutData(gd);
                this.addButton.setImage(UIImages.ADD);
                this.addButton.setToolTipText(Api.I18N.getString("MultipleElementsField.addButton.tooltip"));
                
                // Remove button
                this.removeButton = new Button(buttonsGroup, SWT.PUSH);
                gd = new GridData(SWT.FILL, SWT.FILL, true, false);
                this.removeButton.setLayoutData(gd);
                this.removeButton.setImage(UIImages.DELETE);
                this.removeButton.setToolTipText(Api.I18N.getString("MultipleElementsField.removeButton.tooltip"));
                
                // Up button
                this.upButton = new Button(buttonsGroup, SWT.PUSH);
                gd = new GridData(SWT.FILL, SWT.FILL, true, false);
                this.upButton.setLayoutData(gd);
                this.upButton.setImage(UIImages.UPARROW);
                this.upButton.setToolTipText(Api.I18N.getString("MultipleElementsField.upButton.tooltip"));
                
                // Down button
                this.downButton = new Button(buttonsGroup, SWT.PUSH);
                gd = new GridData(SWT.FILL, SWT.FILL, true, false);
                this.downButton.setLayoutData(gd);
                this.downButton.setImage(UIImages.DOWNARROW);
                this.downButton.setToolTipText(Api.I18N.getString("MultipleElementsField.downButton.tooltip"));
                
                // Branch listeners
                this.addButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        View.this.controler.onAdd();
                    }
                });
                this.removeButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        View.this.controler.onRemove();
                    }
                });
                this.upButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        View.this.controler.onUp();
                    }
                });
                this.downButton.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        View.this.controler.onDown();
                    }
                });
                return buttonsGroup;
            }

            /**
             * Ensure that setting the candidates is run in the display thread
             */
            @objid ("7b0748c3-e083-4f6e-b8cc-e95d622b7263")
            public void setCandidates(final Collection<MObject> candidates, final List<MObject> selection) {
                this.container.getDisplay().asyncExec(
                        () -> doSetCandidates(candidates, selection));
            }

            /**
             * Guaranteed to run in the display thread
             */
            @objid ("cbd6aae0-b2b6-4f63-9379-0635f044c0a9")
            private void doSetCandidates(Collection<MObject> candidates, List<MObject> selection) {
                if (candidates == null) {
                    this.candidates.setLabelProvider(this.labelProvider);
                    this.candidates.getTable().setEnabled(false);
                } else {
                    this.candidates.setInput(candidates);
                    this.candidates.setLabelProvider(this.labelProvider);
                    this.candidates.getTable().setEnabled(true);
                    if (selection != null) {
                        this.candidates.setSelection(new StructuredSelection(selection));
                    }
                }
            }

            /**
             * Set the results in the display thread
             */
            @objid ("6a0fc155-332b-436a-9d62-da29de2e467e")
            public void setResults(List<MObject> selected, List<MObject> selection) {
                this.container.getDisplay().asyncExec( () -> {
                    this.results.setInput(selected);
                    if (selection != null) {
                        this.results.setSelection(new StructuredSelection(selection));
                    }
                });
            }

            @objid ("a0493ef1-d71a-43b0-8f22-a945c7b146c5")
            public void enableAddCommand(boolean onOff) {
                this.addButton.setEnabled(onOff);
            }

            @objid ("807a15ad-4564-47d0-a149-b02b63d8b638")
            public void enableRemoveCommand(boolean onOff) {
                this.removeButton.setEnabled(onOff);
                this.upButton.setEnabled(onOff);
                this.downButton.setEnabled(onOff);
            }

            @objid ("37fd941c-4834-46f2-82c1-979f167e9bf2")
            public List<MObject> getCandidatesSelection() {
                return toObjectsList(this.candidates.getSelection());
            }

            @objid ("11a1bcfc-ddcb-4938-b5b9-b1b74a9aa1d3")
            public List<MObject> getResultsSelection() {
                return toObjectsList(this.results.getSelection());
            }

            @objid ("924386ca-108d-48d8-bf2b-c43ae6d287b0")
            private List<MObject> toObjectsList(ISelection selection) {
                return SelectionHelper.toList(selection, MObject.class);
            }

            /**
             * @return the top level container of the view.
             */
            @objid ("7173cb61-2262-403c-884e-5cff650805ac")
            public Composite getContainer() {
                return this.container;
            }

            /**
             * Dispose the view resources
             */
            @objid ("6a479288-a0ec-44f6-bfe6-7f5c1919f1e3")
            public void dispose() {
                // Nothing to dispose
            }

            @objid ("a615e12f-c4e3-4a7e-bade-0a8a04a72560")
            private void updateStatusLabel(Label label, TableViewer v) {
                final int nSelected = v.getTable().getSelectionCount();
                final int nTotal = v.getTable().getItemCount();
                label.setText(Api.I18N.getMessage("MultipleElementsField.ElementsStatus", nSelected, nTotal));
            }

        }

        @objid ("bc87e73e-0cfa-4838-8371-46e24bb0b17a")
        static class Controller {
            @objid ("88c7e8c3-e16c-4db4-9e90-14c937b84fad")
            private List<MObject> selected;

            @objid ("b38b6858-3a8c-4ce9-affe-a850d4277787")
            private final Collection<MObject> candidates;

            @objid ("456995f1-f38e-44ab-ace5-e942b4fb2eeb")
            private View view;

            @objid ("03b435cb-daa1-4e22-89f8-63bf5d510341")
            private final INavigationService navigationService;

            @objid ("94fe19e3-f4d0-416a-9721-74bd85d4b2e9")
            public Controller(INavigationService navigationService, Collection<MObject> candidates) {
                this.navigationService = navigationService;
                this.candidates = candidates;
            }

            @objid ("503753e7-5f72-4772-bab0-9cebf61a1de3")
            private void initCandidates(Collection<MObject> candidateList) {
                this.view.setCandidates(candidateList, Collections.emptyList());
            }

            /**
             * Called by the dialog just before the GUI is closed
             */
            @objid ("0de80e8e-39b8-4263-b9c5-bd14a8aa2431")
            public void onClose() {
                // Nothing to do
            }

            /**
             * Called by the dialog when selection changes in the candidates list
             */
            @objid ("36f5deb6-029d-4ea2-9642-8c0bb2d8a0ea")
            public void onSelectCandidate(Collection<MObject> selectedCandidates) {
                this.view.enableAddCommand(!selectedCandidates.isEmpty());
            }

            /**
             * Called by the dialog when selection changes in the results list
             */
            @objid ("0d0c7da3-1a18-4831-b75d-f6689d750c6b")
            public void onSelectResult(Collection<MObject> selectedResults) {
                this.view.enableRemoveCommand(!selectedResults.isEmpty());
            }

            @objid ("ce280552-daea-4ac7-9af7-106aa756db40")
            private void onAdd(List<MObject> selectedCandidates) {
                for (MObject obj : selectedCandidates) {
                    if (!this.selected.contains(obj)) {
                        this.selected.add(obj);
                    }
                }
                this.view.setResults(this.selected, selectedCandidates);
            }

            @objid ("5fda4d54-ae2e-4cd8-955f-8c12f86512f5")
            public void onAdd() {
                onAdd(this.view.getCandidatesSelection());
            }

            @objid ("cad84621-ebbf-4d4e-a7bd-4827c4bde869")
            private void onRemove(Collection<MObject> selectedResults) {
                for (MObject obj : selectedResults) {
                    this.selected.remove(obj);
                }
                this.view.setResults(this.selected, null);
            }

            @objid ("b1089e27-4e20-4421-9ce0-f67296bb4233")
            public void onRemove() {
                onRemove(this.view.getResultsSelection());
            }

            @objid ("d0a04169-8345-4929-8e6e-311d0b17f2f5")
            public void fireNavigate(List<MObject> selectedElements) {
                if (!selectedElements.isEmpty()) {
                    this.navigationService.fireNavigate(selectedElements);
                }
            }

            /**
             * Called by the panel once the view has been instantiated. Launch the search thread to populate the candidates list in
             * the view.
             */
            @objid ("9b769564-d24f-4e16-803c-b2b6da231e6f")
            public void setView(View view) {
                this.view = view;
                
                view.setCandidates(null, Collections.emptyList());
                setInitialResults(this.selected);
                
                // run a thread to search the candidates
                Thread t = new Thread(() -> initCandidates(this.candidates));
                
                t.start();
            }

            @objid ("71feb306-8deb-4f40-af96-90c6103622c8")
            public void setInitialResults(Collection<MObject> elements) {
                this.selected = new ArrayList<>(elements);
                if (this.view != null) {
                    this.view.setResults(this.selected, Collections.emptyList());
                }
            }

            @objid ("54599fd2-6616-4f9c-b4b3-4751e3ece2a0")
            public List<MObject> getResults() {
                return this.selected;
            }

            @objid ("61d05745-4f85-41e3-9924-a11244030c49")
            public void dispose() {
                this.view.dispose();
            }

            @objid ("8ad3a0b2-0fb3-46dd-8c60-8f6aad21b5a4")
            private void onDown(List<MObject> selectedResults) {
                for (MObject o : selectedResults) {
                
                    int i = this.selected.indexOf(o);
                    if (i < 0 || i >= this.selected.size() - 1) {
                        // cannot move
                    } else {
                        Collections.swap(this.selected, i, i + 1);
                    }
                }
                this.view.setResults(this.selected, selectedResults);
            }

            @objid ("4cb5641d-2e96-4dd1-bb31-a81fe8f02bb5")
            public void onDown() {
                onDown(this.view.getResultsSelection());
            }

            @objid ("a7cdab37-80b5-4304-83ab-9b17ff71c957")
            private void onUp(List<MObject> selectedResults) {
                for (MObject o : selectedResults) {
                    int i = this.selected.indexOf(o);
                    if (i < 1 || i > this.selected.size()) {
                        // cannot move
                    } else {
                        Collections.swap(this.selected, i, i - 1);
                    }
                }
                this.view.setResults(this.selected, selectedResults);
            }

            @objid ("91c1443d-2740-418b-bfe4-99a205300a38")
            public void onUp() {
                onUp(this.view.getResultsSelection());
            }

        }

    }

    @objid ("890d44d3-af17-42d0-a11e-e6363782e9cb")
    private static class ThinDialog {
        @objid ("95089ba8-52e2-48f9-963e-31fae5fa4256")
        private Control masterControl;

        @objid ("b2b2e6b7-78bc-4256-aaaf-dfbe5bf72d39")
        private IPanelProvider panelProvider;

        @objid ("8c01da44-acb6-4c02-a825-9f29f3f87a08")
        private Shell slaveShell;

        @objid ("658d49ff-0a0d-4c62-a86a-e95d27ecfe0a")
        public ThinDialog(Control masterControl, IPanelProvider panelProvider) {
            this.masterControl = masterControl;
            this.panelProvider = panelProvider;
        }

        @objid ("edd1cd28-2d60-4013-b111-807c304b7c6f")
        public IPanelProvider getPanelProvider() {
            return this.panelProvider;
        }

        @objid ("8d8deefe-7ad7-4e25-a30c-bfd698067f2f")
        public void onOk() {
            if (this.slaveShell != null) {
                this.slaveShell.close();
                this.slaveShell.dispose();
            }
        }

        @objid ("33f7c299-1443-4bd5-acdb-5c32c4cbaa0b")
        public void onCancel() {
            if (this.slaveShell != null) {
                this.slaveShell.close();
                this.slaveShell.dispose();
            }
        }

        @objid ("66744620-32ab-4dea-a1e1-3cf0621ce721")
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
            ok.setToolTipText(Api.I18N.getString("ThinDialog.okButton.tooltip"));
            ok.addListener(SWT.Selection,
                    e -> onOk());
            
            Button cancel = new Button(this.slaveShell, SWT.NONE);
            cancel.setImage(UIImages.CANCEL);
            cancel.setToolTipText(Api.I18N.getString("ThinDialog.cancelButton.tooltip"));
            cancel.addListener(SWT.Selection,
                    e -> onCancel());
            
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
            
            // label.addListener(SWT.MouseExit, labelListener);
            // label.addListener(SWT.MouseDown, labelListener);
            
            syncSlaveBoundsToMaster();
            this.slaveShell.setVisible(true);
            
            masterShell.addControlListener(new ControlAdapter() {
                @Override
                public void controlMoved(ControlEvent e) {
                    if (! syncSlaveBoundsToMaster()) {
                        masterShell.removeControlListener(this);
                    }
                }
            
                @Override
                public void controlResized(ControlEvent e) {
                    if (! syncSlaveBoundsToMaster()) {
                        masterShell.removeControlListener(this);
                    }
                }
            
            });
            return true;
        }

        @objid ("efc66dab-cdbc-4e24-957c-27ee8d772026")
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

}
