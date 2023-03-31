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
package org.modelio.platform.model.ui.panels.selectelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.panels.search.model.ModelSearchPanel;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.ui.LocalFontRegistry;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.UIThreadRunner;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * <p>
 * The SelectObjectsPanel is an implementation of IPanelProvider that can be used to select several model Element from a list of <em>candidates</em> into a <em>results</em> list.
 * </p>
 * <p>
 * The <em>candidates</em> list is passed as ISearchEngine and ISearchCriteria parameters. The search is launched only when the panel is displayed and it is run in a separate thread in order to keep the UI refreshing.
 * </p>
 * <p>
 * The initial list of <em>results</em> has to be passed using the <em>setInput()</em> method. It must be a non-null, possibly non-empty, modifiable List&lt;MObject&gt;.
 * </p>
 * <p>
 * &nbsp;
 * </p>
 * 
 * @param <T>
 */
@objid ("a0c5061c-a772-432f-8d1a-59a4b46997d9")
public class SelectObjectsPanel<T> implements IPanelProvider {
    @objid ("97950539-bffb-43b3-ab4c-260cfedb3836")
    private PanelView<T> view;

    @objid ("a7f07738-7304-4a2c-84c1-f131f08e9da7")
    private PanelControler<T> controler;

    @objid ("c94a82a3-25b3-4eb1-834b-e3ef158d6a8a")
    private final ILabelProvider labelProvider;

    @objid ("c5e85db7-01d1-435f-872f-8fd52677af89")
    private Class<T> templateType;

    /**
     * C'tor
     * @param templateType the manipulated object type
     * @param candidatesProvider a @link {@link Supplier} for candidates objects
     * @param candidatesLabelProvider a label provider to display the candidates and the results.
     * @param candidatesFilter a 'name contains value' filter for candidates (filter applied on supplier results)
     * @param autoPopulate if true the suppliaer is called during iniitalisation, otherwise calling the supplier is left to the end user who has to click a run button in the gui.
     */
    @objid ("2f8c1556-be5a-4fcd-a1fc-0e8e9d3cb537")
    public  SelectObjectsPanel(Class<T> templateType, Supplier<List<T>> candidatesProvider, ILabelProvider candidatesLabelProvider, String candidatesFilter, boolean autoPopulate) {
        this.labelProvider = candidatesLabelProvider;
        this.templateType = templateType;
        this.controler = new PanelControler<>(candidatesProvider, candidatesFilter, autoPopulate);
        
    }

    /**
     * C'tor, no candidates filter
     */
    @objid ("f3c2198f-4203-45d1-9c09-5706a6037116")
    public  SelectObjectsPanel(Class<T> templateType, Supplier<List<T>> candidatesProvider, ILabelProvider candidatesLabelProvider, boolean autoPopulate) {
        this(templateType, candidatesProvider, candidatesLabelProvider, "", autoPopulate);
    }

    /**
     * C'tor, no candidates filter, autopopulate true
     */
    @objid ("132a6981-8446-4b47-ad99-6981732775b6")
    public  SelectObjectsPanel(Class<T> templateType, Supplier<List<T>> candidatesProvider, ILabelProvider candidatesLabelProvider) {
        this(templateType, candidatesProvider, candidatesLabelProvider, "", true);
    }

    @objid ("996148ab-b88c-4815-8297-f4317148ad29")
    @Override
    public boolean isRelevantFor(Object obj) {
        return (obj instanceof List<?>);
    }

    @objid ("b91c51f2-3864-40a5-a8fc-8aebb6d27fb3")
    @Override
    public Composite createPanel(Composite parent) {
        this.view = new PanelView<>(parent, this.templateType, this.controler);
        this.view.setLabelProvider(this.labelProvider);
        this.controler.setView(this.view);
        return this.view.getContainer();
    }

    @objid ("cd57f1ad-56e5-4178-b142-5151b7e78fbb")
    @Override
    public Composite getPanel() {
        return this.view.getContainer();
    }

    @objid ("ff14e3bc-8288-42d5-b128-e8decee413d4")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("1bb07faa-a73d-41ec-8831-cd93fafb5180")
    @Override
    public List<T> getInput() {
        return this.controler.getResults();
    }

    @objid ("c39e874c-468d-426f-bde8-a0097ea86960")
    @SuppressWarnings ("unchecked")
    @Override
    public void setInput(Object input) {
        this.controler.setInitialResults((List<T>) input);
    }

    @objid ("87382d29-3bb0-475d-bd85-bc702b0ea184")
    @Override
    public void dispose() {
        this.view.dispose();
        this.controler.dispose();
        
    }

    @objid ("4fb208ac-b50f-46e9-80c0-1c9eb82d7f00")
    private static class PanelView<T> {
        @objid ("0abd0b57-9fcc-4f8f-9bc3-c480c7114e4a")
        private final PanelControler<T> controler;

        @objid ("50f726e7-49e7-4d2f-af2a-519aacbb2f84")
        private final Composite container;

        @objid ("14c54f32-e39c-417e-8b92-50953e9000df")
        private TableViewer candidates;

        @objid ("dd8ef403-a9f5-4c86-8d35-dc2a9391a35c")
        private TableViewer results;

        @objid ("65a90448-67a9-46bf-a71f-6062573d0b25")
        private Button addButton;

        @objid ("fba970d3-01f2-4c47-a0f3-f9a4045ae03a")
        private Button removeButton;

        @objid ("ef7cf34c-651b-4ff8-9677-eb4e79cd93b9")
        private Label candidatesStatusLabel;

        @objid ("b89b18e7-46d3-4a8a-ad4b-85a4af014423")
        private Label resultsStatusLabel;

        @objid ("dd0426f5-d716-4f65-85c1-4858656b790e")
        private Button searchButton;

        @objid ("d3c37665-cda2-4647-b4d4-47a233026bad")
        private ModelSearchPanel searchConfigurationPanel;

        @objid ("f393a4c7-d5fb-44aa-8c45-540b9156e430")
        private final LocalFontRegistry fontRegistry;

        @objid ("9bb9cce8-135a-4b4e-b8e0-d1f84cbcdd31")
        private ILabelProvider labelProvider = new LabelProvider();

        @objid ("a8aa7d06-ddda-4cb4-aba4-c78228853823")
        private final Class<T> templateType;

        /**
         * Specific content provider for the candidates sublist that supports 'filtering'
         */
        @objid ("95addcc9-87a3-4bfc-87b0-fe67143777ba")
        private FilteredArrayContentProvider candidatesContentProvider;

        @objid ("c638b725-412f-4dce-8eb4-a1597cfebda7")
        public  PanelView(Composite parent, Class<T> templateType, PanelControler<T> controler) {
            this.controler = controler;
            this.fontRegistry = LocalFontRegistry.create(parent);
            this.container = createGui(parent, controler.autoPopulate);
            this.templateType = templateType;
            
        }

        @objid ("be5573c2-1d9d-46ad-bdd1-cee931c8bd98")
        public void setLabelProvider(ILabelProvider labelProvider) {
            this.labelProvider = labelProvider;
            
            if (this.candidates != null) {
                this.candidates.setLabelProvider(labelProvider);
                this.candidatesContentProvider.setLabelProvider(labelProvider);
            }
            if (this.results != null) {
                this.results.setLabelProvider(labelProvider);
            }
            
        }

        @objid ("1110c8ee-832a-4828-8449-d7c54ffa69b6")
        private Composite createGui(Composite parent, boolean autoPopulate) {
            final Composite composite = new Composite(parent, SWT.NONE);
            composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            composite.setLayout(new FormLayout());
            
            // Candidates group
            Group candidatesGroup = createCandidatesGroup(composite);
            
            // Command buttons group (add / remove)
            Composite commandsGroup = createCommandsGroup(composite);
            
            // Selected objects group
            Group selectionGroup = createSelectionGroup(composite);
            
            // Search group
            Composite searchGroup;
            if (!autoPopulate) {
                searchGroup = createManualSearchGroup(composite);
            
                // Add search button
                this.searchButton = new Button(composite, SWT.ARROW | SWT.PUSH);
                this.searchButton.setImage(UIImages.SEARCH);
            
                FormData formData1 = new FormData();
                formData1.right = new FormAttachment(100, -2);
                formData1.bottom = new FormAttachment(searchGroup, 0, SWT.BOTTOM);
                this.searchButton.setLayoutData(formData1);
            
                FormData formData = new FormData();
                formData.top = new FormAttachment(0, 5);
                formData.left = new FormAttachment(0, 2);
                formData.right = new FormAttachment(this.searchButton, -4, SWT.LEFT);
                // formData.right = new FormAttachment(80, -2);
                formData.bottom = new FormAttachment(30, -2);
                searchGroup.setLayoutData(formData);
            
                this.searchButton.addListener(SWT.Selection, ev -> this.controler.onRunSearch());
            
            } else {
                searchGroup = null;
            }
            
            // candidatesGroup attachments
            FormData formData = new FormData();
            if (searchGroup != null) {
                formData.top = new FormAttachment(searchGroup, 5, SWT.BOTTOM);
            } else {
                formData.top = new FormAttachment(0, 5);
            }
            formData.left = new FormAttachment(0, 2);
            formData.right = new FormAttachment(commandsGroup, 0, SWT.LEFT);
            formData.bottom = new FormAttachment(100, -2);
            formData.width = 200;
            formData.height = 250;
            candidatesGroup.setLayoutData(formData);
            
            // selectionGroup attachments
            formData = new FormData();
            if (searchGroup != null) {
                formData.top = new FormAttachment(candidatesGroup, 0, SWT.TOP);
            } else {
                formData.top = new FormAttachment(0, 5);
            }
            formData.right = new FormAttachment(100, -2);
            formData.left = new FormAttachment(commandsGroup, 0, SWT.RIGHT);
            formData.bottom = new FormAttachment(100, -2);
            selectionGroup.setLayoutData(formData);
            
            // commandsGroup attachments
            formData = new FormData();
            if (searchGroup != null) {
                formData.top = new FormAttachment(candidatesGroup, 100, SWT.TOP);
            } else {
                formData.top = new FormAttachment(0, 5);
            }
            formData.left = new FormAttachment(50, -20);
            // formData.right = new FormAttachment(50, 20);
            formData.bottom = new FormAttachment(100, -5);
            commandsGroup.setLayoutData(formData);
            return composite;
        }

        @objid ("541d9905-940b-43a3-9ef9-66a814ed0e58")
        private Group createCandidatesGroup(Composite composite) {
            final Group candidateGroup = new Group(composite, SWT.NONE);
            
            GridLayout layout = new GridLayout(1, false);
            layout.marginWidth = 2;
            layout.marginHeight = 2;
            candidateGroup.setLayout(layout);
            candidateGroup.setText(CoreUi.I18N.getString("SelectObjectsPanel.CandidatesElements"));
            
            // Candidates list
            this.candidates = new TableViewer(candidateGroup, SWT.BORDER | SWT.MULTI);
            GridData gd = new GridData();
            gd.grabExcessHorizontalSpace = false;
            gd.grabExcessVerticalSpace = true;
            gd.horizontalAlignment = SWT.FILL;
            gd.verticalAlignment = SWT.FILL;
            this.candidates.getTable().setLayoutData(gd);
            
            TableViewerColumn column = new TableViewerColumn(this.candidates, SWT.NONE);
            column.getColumn().setResizable(false);
            
            this.candidates.setComparator(new ViewerComparator());
            this.candidatesContentProvider = new FilteredArrayContentProvider<T>(this.labelProvider);
            this.candidates.setContentProvider(this.candidatesContentProvider);
            this.candidates.setLabelProvider(this.labelProvider);
            
            // Filter
            Text filter = new Text(candidateGroup, SWT.BORDER);
            GridData gd1 = new GridData();
            gd1.grabExcessHorizontalSpace = true;
            gd1.grabExcessVerticalSpace = false;
            gd1.horizontalAlignment = SWT.FILL;
            gd1.verticalAlignment = SWT.FILL;
            filter.setLayoutData(gd1);
            
            filter.setToolTipText(CoreUi.I18N.getString("SelectObjectsPanel.candidatesFilter.tooltip"));
            filter.addModifyListener(new ModifyListener() {
            
                @Override
                public void modifyText(ModifyEvent e) {
                    PanelView.this.controler.onChangeCandidatesFilter(((Text) e.widget).getText());
                    PanelView.this.updateStatusLabel(PanelView.this.candidatesStatusLabel,
                            PanelView.this.candidates);
                }
            
            });
            
            // Status label
            this.candidatesStatusLabel = new Label(candidateGroup, SWT.NONE);
            this.candidatesStatusLabel.setText("...");
            this.candidatesStatusLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
            
            this.candidatesStatusLabel.setFont(this.fontRegistry
                    .builder(this.candidatesStatusLabel.getFont())
                    .addStyle(SWT.ITALIC)
                    .build());
            
            PanelView.this.updateStatusLabel(PanelView.this.candidatesStatusLabel, PanelView.this.candidates);
            
            // Listeners and behavior
            
            // Double click listener: add element
            this.candidates.addDoubleClickListener(new IDoubleClickListener() {
                @Override
                public void doubleClick(DoubleClickEvent event) {
                    PanelView.this.controler.onAdd(SelectionHelper.toList(event.getSelection(), PanelView.this.templateType));
                }
            });
            
            // Selection change
            // - fire controler
            // - update status label
            this.candidates.addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    PanelView.this.controler.onSelectCandidate(SelectionHelper.toList(event.getSelection(), PanelView.this.templateType));
                    PanelView.this.updateStatusLabel(PanelView.this.candidatesStatusLabel,
                            (TableViewer) event.getSource());
                }
            });
            
            // <ctrl>+<alt> + right-click navigates to the selected element
            this.candidates.getTable().addListener(SWT.MouseUp, e -> {
                // <CTRL><ALT> click
                if ((e.button == 1) && (e.stateMask & (SWT.CTRL | SWT.ALT)) != 0) {
                    this.controler.fireNavigate(
                            SelectionHelper.toList(PanelView.this.results.getSelection(), PanelView.this.templateType));
                }
            });
            return candidateGroup;
        }

        @objid ("0f2f69d9-abcb-418a-996e-e3c1d0b3baa7")
        private Group createSelectionGroup(Composite composite) {
            final Group resultsGroup = new Group(composite, SWT.SHADOW_NONE);
            GridLayout layout = new GridLayout(1, true);
            layout.marginHeight = 2;
            layout.marginWidth = 2;
            resultsGroup.setLayout(layout);
            
            resultsGroup.setText(CoreUi.I18N.getString("SelectObjectsPanel.ChoosenElements"));
            
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
            this.resultsStatusLabel
                    .setFont(this.fontRegistry
                            .builder(this.resultsStatusLabel.getFont())
                            .addStyle(SWT.ITALIC)
                            .scale(0.9f)
                            .build());
            
            PanelView.this.updateStatusLabel(PanelView.this.candidatesStatusLabel, PanelView.this.results);
            
            // Double click removes the element
            this.results.addDoubleClickListener(new IDoubleClickListener() {
                @Override
                public void doubleClick(DoubleClickEvent event) {
                    PanelView.this.controler.onRemove(SelectionHelper.toList(event.getSelection(), PanelView.this.templateType));
                }
            });
            
            // Listeners and behavior
            
            // Selection change
            // - fire controller
            // - update status label
            this.results.addSelectionChangedListener(event -> {
                this.controler.onSelectResult(SelectionHelper.toList(event.getSelection(), PanelView.this.templateType));
                this.updateStatusLabel(this.resultsStatusLabel, (TableViewer) event.getSource());
            });
            
            // <ctrl> <alt>+ right click navigates to the selected element
            this.results.getTable().addListener(SWT.MouseUp, (Event e) -> {
                // <CTRL><ALT>Right click
                if ((e.button == 3) && (e.stateMask & (SWT.CTRL | SWT.ALT)) != 0) {
                    this.controler.fireNavigate(SelectionHelper.toList(PanelView.this.results.getSelection(), PanelView.this.templateType));
                }
            });
            return resultsGroup;
        }

        @objid ("6dac8f3c-0f3e-452c-9b1d-3bc666706705")
        private Composite createCommandsGroup(Composite parent) {
            final Composite buttonsGroup = new Composite(parent, SWT.NONE);
            buttonsGroup.setLayout(new GridLayout(1, true));
            
            // Add button
            this.addButton = new Button(buttonsGroup, SWT.ARROW | SWT.RIGHT);
            GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
            this.addButton.setLayoutData(gd);
            this.addButton.setText(CoreUi.I18N.getString("SelectObjectsPanel.AddButton"));
            this.addButton.setToolTipText(CoreUi.I18N.getString("SelectObjectsPanel.AddButton.tooltip"));
            
            // Remove button
            this.removeButton = new Button(buttonsGroup, SWT.ARROW | SWT.LEFT);
            gd = new GridData(SWT.FILL, SWT.FILL, true, false);
            this.removeButton.setLayoutData(gd);
            this.removeButton.setText(CoreUi.I18N.getString("SelectObjectsPanel.RemoveButton"));
            this.removeButton.setToolTipText(CoreUi.I18N.getString("SelectObjectsPanel.RemoveButton.tooltip"));
            
            this.removeButton.addListener(SWT.Selection, (Event e) -> {
                this.controler.onRemove();
            });
            
            this.addButton.addListener(SWT.Selection, (Event e) -> {
                this.controler.onAdd();
            });
            return buttonsGroup;
        }

        /**
         * Ensure that setting the candidates is run in the display thread
         * @param candidatesList the candidate elements
         * @param message a message if no candidates
         */
        @objid ("fbb30b86-a67b-411b-bad6-7b2021a80cc1")
        public void setCandidates(final List<T> candidatesList, String message) {
            if (this.container.isDisposed())
                return;
            
            UIThreadRunner.asynExec(this.container, () -> {
            
                if (candidatesList == null) {
                    this.candidates.setLabelProvider(new LabelProvider());
                    this.candidates.setInput(Arrays.asList(message));
                    this.candidates.getTable().setEnabled(false);
                } else {
                    this.candidates.setInput(candidatesList);
                    this.candidates.setLabelProvider(this.labelProvider);
                    this.candidates.getTable().setEnabled(true);
                }
                for (TableColumn column : this.candidates.getTable().getColumns()) {
                    column.pack();
                }
            
            });
            
        }

        /**
         * Ensure that setting the results is run in the display thread
         * @param selected the results table input
         * @param selection the results table selected elements
         */
        @objid ("2ea65434-bb86-4ecc-b50b-ff477eb594cc")
        public void setResults(List<T> selected, List<T> selection) {
            UIThreadRunner.asynExec(this.container, () -> {
                this.results.setInput(selected);
                if (selection != null) {
                    this.results.setSelection(new StructuredSelection(selection));
                }
            });
            
        }

        @objid ("63735dfe-e263-4a29-b200-d71957cf66b6")
        public void enableAddCommand(boolean onOff) {
            this.addButton.setEnabled(onOff);
        }

        @objid ("de1e9534-b3be-4590-9f86-1b85f538a3a6")
        public void enableRemoveCommand(boolean onOff) {
            this.removeButton.setEnabled(onOff);
        }

        @objid ("0b1e59fd-ec79-4696-b171-d86da2245f69")
        public List<T> getCandidatesSelection() {
            return SelectionHelper.toList(this.candidates.getSelection(), PanelView.this.templateType);
        }

        @objid ("c83dd23e-4434-43f9-b083-655bcdd51ede")
        public List<T> getResultsSelection() {
            return SelectionHelper.toList(this.results.getSelection(), PanelView.this.templateType);
        }

        /**
         * @return the top level container of the view.
         */
        @objid ("e1f9d971-81d0-49e6-8f19-2bb94065a248")
        public Composite getContainer() {
            return this.container;
        }

        /**
         * Dispose the view resources
         */
        @objid ("6afa0387-9ec1-4e0e-9c5c-8da9a913ab6a")
        public void dispose() {
            // Empty
        }

        @objid ("bd15dd67-1db5-4b66-902e-cc94c3e2f458")
        private void updateStatusLabel(Label label, TableViewer v) {
            final int nSelected = v.getTable().getSelectionCount();
            final int nTotal = v.getTable().getItemCount();
            label.setText(CoreUi.I18N.getMessage("SelectObjectsPanel.ElementsStatus", nSelected, nTotal));
            
        }

        @objid ("7160561b-3c90-45a6-8b5d-a44ee941b025")
        private Composite createManualSearchGroup(final Composite composite) {
            final Composite searchGroup = new Composite(composite, SWT.NONE);
            GridLayout l = new GridLayout(1, true);
            l.marginHeight = 0;
            l.marginTop = 10;
            l.marginWidth = 0;
            searchGroup.setLayout(l);
            
            GridData gd = new GridData();
            gd.grabExcessHorizontalSpace = true;
            gd.grabExcessVerticalSpace = true;
            gd.horizontalAlignment = SWT.FILL;
            gd.verticalAlignment = SWT.FILL;
            gd.horizontalSpan = 1;
            searchGroup.setLayoutData(gd);
            return searchGroup;
        }

        @objid ("578392d9-6eb2-499b-9eb5-8f3c2add09c0")
        public void setCandidatesFilter(String candidatesFilter) {
            this.candidatesContentProvider.setFilter(candidatesFilter);
            this.candidates.refresh();
            
        }

    }

    @objid ("f47a4cab-762e-42b2-aab3-09e266eb08e4")
    private static class PanelControler<T> {
        @objid ("f7769180-196e-453e-a803-5ad03f0ed50d")
        private boolean autoPopulate;

        @objid ("5a7c8fc9-d98a-4ea8-bad2-27bbbad447a2")
        private List<T> selected;

        @objid ("e51b95e9-4b70-4a46-bc54-3797c2d66523")
        private String candidatesFilter;

        @objid ("2bd36394-7c83-4bc4-9d33-a02beda0c4ba")
        private Supplier<List<T>> candidatesProvider;

        @objid ("9a6c00b6-ac30-4411-920b-af131ef10331")
        private PanelView<T> view;

        @objid ("b600eeb2-e289-4d7e-96a6-a331d7f475ee")
        private Thread searchThread;

        @objid ("877a622c-3e63-4362-ae41-e9e309ca8374")
        public  PanelControler(Supplier<List<T>> candidatesProvider, String candidatesFilter, boolean autoPopulate) {
            this.autoPopulate = autoPopulate;
            this.candidatesProvider = candidatesProvider;
            this.candidatesFilter = candidatesFilter;
            
        }

        @objid ("a931c75e-0109-4da7-bfdf-0ec315296c2b")
        private void initCandidates(List<T> candidates) {
            if (this.autoPopulate) {
                this.view.setCandidates(candidates, CoreUi.I18N.getString("SelectObjectsPanel.Searching"));
            } else {
                this.view.setCandidates(candidates, CoreUi.I18N.getString("SelectObjectsPanel.ClickForSearching"));
            }
            
        }

        /**
         * Called by the dialog just before the GUI is closed
         */
        @objid ("af1c7ea1-4d72-4eaa-8923-a4cc1566746a")
        public void onClose() {
            endSearchThread();
        }

        /**
         * Called by the dialog when selection changes in the candidates list.
         * @param selectedCandidates the selected candidates
         */
        @objid ("bd2b640d-55c2-48db-ac64-86bacd6f11ed")
        public void onSelectCandidate(List<T> selectedCandidates) {
            // TODO could improve by checking that at least one of the selected
            // candidates is not already in the results
            this.view.enableAddCommand(!selectedCandidates.isEmpty());
            
        }

        @objid ("8cc55b48-d75c-4782-9320-5eaa1bc5a32f")
        public void onChangeCandidatesFilter(String filterText) {
            setCandidatesFilter(filterText);
        }

        /**
         * Called when selection changes in the results list
         * @param selectedResults the selected elements in the result list
         */
        @objid ("1d50602b-8c94-47be-bec1-dbc137ef7884")
        public void onSelectResult(List<T> selectedResults) {
            this.view.enableRemoveCommand(!selectedResults.isEmpty());
        }

        /**
         * Called when the user clicks the "Add" button. The currently selected candidates are added to the selection.
         */
        @objid ("47ca90be-58e3-49e5-99d4-0a21f4046c9e")
        private void onAdd(List<T> selectedCandidates) {
            for (T obj : selectedCandidates) {
                if (!this.selected.contains(obj)) {
                    this.selected.add(obj);
                }
            }
            this.view.setResults(this.selected, selectedCandidates);
            
        }

        /**
         * Called when the user clicks the "Add" button. The currently selected candidates are added to the selection.
         */
        @objid ("3e1c927f-1d1d-45f4-b451-7bbe5e766e49")
        public void onAdd() {
            onAdd(this.view.getCandidatesSelection());
        }

        /**
         * Called when the user clicks the "Remove" button. The currently selected elements in the selection are removed from the selection.
         */
        @objid ("590fc71e-7bb0-4916-bf37-eddf760f0dea")
        private void onRemove(List<T> selectedResults) {
            for (Object obj : selectedResults) {
                this.selected.remove(obj);
            }
            this.view.setResults(this.selected, null);
            
        }

        /**
         * Called when the user clicks the "Remove" button. The currently selected elements in the selection are removed from the selection.
         */
        @objid ("83bcfb54-8c88-4048-8734-ac0379093e63")
        public void onRemove() {
            onRemove(this.view.getResultsSelection());
        }

        @objid ("86617199-c73b-41cd-9dfb-19156e4bd706")
        public void fireNavigate(List<T> selectedElements) {
            if (!selectedElements.isEmpty()) {
                // IModelioNavigationService s = ??? ;
                // s.fireNavigate(selectedElements.get(0));
            }
            
        }

        /**
         * Called by the panel once the view has been instantiated.<br/>
         * Launch the search thread to populate the candidates list in the view
         * @param view the panel view
         */
        @objid ("be048fd4-0f66-43a3-9f82-70ed9035da7e")
        public void setView(PanelView<T> view) {
            this.view = view;
            setCandidatesFilter(this.candidatesFilter);
            setInitialResults(this.selected);
            if (this.autoPopulate) {
                launchSearchThread();
            } else {
                // this.view.searchConfigurationPanel.setCriteria(this.searchCriteria);
                this.view.setCandidates(null, CoreUi.I18N.getString("SelectObjectsPanel.ClickForSearching"));
            }
            
        }

        @objid ("a15d73f0-fd20-4a10-be19-5ee5cb55baa1")
        private void setCandidatesFilter(String candidatesFilter) {
            if (this.view != null) {
                this.view.setCandidatesFilter(candidatesFilter);
            }
            
        }

        @objid ("3dfbb283-358c-43b7-b8eb-b4baf6c274ec")
        private List<T> searchCandidates() {
            if (this.candidatesProvider != null) {
                return this.candidatesProvider.get();
            } else {
                CoreUi.LOG.debug("SelectObjectsPanel.searchCandidates() : searcher is <null> !");
                return Collections.emptyList();
            }
            
        }

        @objid ("1662819c-be0c-42c5-8baa-a049fd3cb291")
        public void setInitialResults(List<T> elements) {
            this.selected = elements;
            if (this.view != null) {
                this.view.setResults(elements, Collections.emptyList());
            }
            
        }

        @objid ("e36a0d45-5ad2-499d-8203-819188ea0dcc")
        public List<T> getResults() {
            return this.selected;
        }

        @objid ("0f28232a-4386-494a-bc2a-d9c5503fc7c3")
        public void dispose() {
            // nothing to do
        }

        /**
         * Called when the user clicks on the "search" button. Note that the "search" button is only available in USER search mode. The method simply launch the searcher to populate the candidates list.
         */
        @objid ("8a68ac58-1bdb-46fb-a147-36919f66a4b3")
        public void onRunSearch() {
            if (!this.autoPopulate) {
            
            }
            launchSearchThread();
            
        }

        @objid ("bca7f2d7-6202-45b5-80bd-735d3aa52ffa")
        private void launchSearchThread() {
            String label = CoreUi.I18N.getString("SelectObjectsPanel.Searching");
            this.view.setCandidates(null, label);
            // run a thread to search the candidates
            this.searchThread = new Thread(() -> {
                List<T> candidates = searchCandidates();
                initCandidates(candidates);
                this.searchThread = null;
            }, label);
            this.searchThread.setDaemon(true);
            this.searchThread.start();
            
        }

        @objid ("a7293c27-12d6-44b9-84fd-d3c88a5c2788")
        private void endSearchThread() {
            if (this.searchThread != null && this.searchThread.isAlive()) {
                this.searchThread.interrupt();
                this.searchThread.stop();
                this.searchThread = null;
            }
            
        }

    }

    @objid ("4e73136b-bba1-4b02-858e-208fcff3aeca")
    private static class FilteredArrayContentProvider<T> extends ArrayContentProvider {
        @objid ("ff778eb0-5545-4d00-a656-ddcdb1f9e86e")
        private String filter;

        @objid ("0c85e940-c81c-406b-a147-07cfe30ccd39")
        private ILabelProvider labelProvider;

        @objid ("8644e0dd-3bbf-4e3c-b8f2-55b0183eebfe")
        public  FilteredArrayContentProvider(ILabelProvider labelProvider) {
            this.labelProvider = labelProvider;
            this.filter = "";
            
        }

        @objid ("44cc2d87-fb65-4738-833c-979db28af350")
        public void setLabelProvider(ILabelProvider labelProvider) {
            this.labelProvider = labelProvider;
        }

        @objid ("3eb80d5c-5a76-4683-b100-672cd85067fa")
        public void setFilter(String filter) {
            this.filter = filter;
        }

        @objid ("d9d0579c-f122-4597-989c-27db6e8f78d2")
        @Override
        public Object[] getElements(Object inputElement) {
            if (this.filter.isBlank()) {
                return super.getElements(inputElement);
            } else {
                List<Object> results = new ArrayList<>();
                for (Object o : super.getElements(inputElement)) {
                    System.out.println("FilteredArrayContentProvider " + this.labelProvider.getText(o));
                    if (this.labelProvider.getText(o).contains(this.filter)) {
                        results.add(o);
                    }
                }
                return results.toArray();
            
            }
            
        }

    }

}
