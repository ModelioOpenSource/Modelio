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

package org.modelio.model.search.dialog;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.model.search.dialog.results.ResultsPanel;
import org.modelio.model.search.plugin.ModelSearch;
import org.modelio.platform.core.navigate.IModelioNavigationService;
import org.modelio.platform.model.ui.panels.search.ISearchController;
import org.modelio.platform.model.ui.panels.search.ISearchPanel;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.platform.model.ui.swt.images.StandardModelStyleProvider;
import org.modelio.platform.search.engine.ISearchCriteria;
import org.modelio.platform.search.engine.ISearchEngine;
import org.modelio.platform.ui.UIImages;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Search dialog.
 */
@objid ("000a8552-c59e-10ab-8258-001ec947cd2a")
public class SearchDialog extends ModelioDialog {
    @objid ("aaa00fbf-3b49-499e-879a-4883dc93ba83")
    private static final String ENGINE_DATAKEY = "engine";

    @objid ("17516b19-0ad6-435f-9df9-510d00e02614")
    private static final String PANEL_DATAKEY = "panel";

    @objid ("4826c116-7d12-464f-9330-5f4cee1d2f34")
    private static final int BUTTON_WIDTH = 40;

    @objid ("000a8f52-c59e-10ab-8258-001ec947cd2a")
    protected List<Element> results;

    @objid ("0060cb9c-ec63-10ac-8258-001ec947cd2a")
    private final IModelioNavigationService navigationService;

    @objid ("3c199f4b-d600-4332-a4ec-d2b41637c2cd")
    private ICoreSession session;

    @objid ("299ef093-8da5-41a1-9c22-156dc9f382f8")
    private ProgressBar progressBar;

    @objid ("521058e1-b6e2-4eb6-8cb0-bd4782a4ea85")
    private Button searchButton;

    @objid ("6209333b-4031-4811-8fd4-015b10670db7")
    private ISearchController controller;

    @objid ("63f4b569-90da-4c0c-acf8-2d14c9fe9b28")
    private TabFolder tabFolder;

    @objid ("06786cb8-3310-4a10-994f-2f6477a1bfb5")
    private ResultsPanel resultsPanel;

    @objid ("f406bacf-19af-4f8f-a74d-685fe15da136")
    private final SearchModelChangeListener listener;

    @objid ("6e548310-eb9c-484d-b4f1-973a4276111c")
    private static SearchDialog instance = null;

    @objid ("000ac24c-c59e-10ab-8258-001ec947cd2a")
    private SearchDialog(Shell parentShell, ICoreSession session, IModelioNavigationService navigationService) {
        super(parentShell);
        
        this.session = session;
        this.results = null;
        this.navigationService = navigationService;
        
        this.listener = new SearchModelChangeListener(this);
        session.getModelChangeSupport().addModelChangeListener(this.listener);
    }

    @objid ("000aef60-c59e-10ab-8258-001ec947cd2a")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CLOSE_LABEL, false);
    }

    @objid ("000b0c48-c59e-10ab-8258-001ec947cd2a")
    @Override
    public Control createContentArea(Composite parent) {
        final SashForm sash = new SashForm(parent, SWT.VERTICAL | SWT.SMOOTH);
        sash.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        sash.setSashWidth(12);
        createCriteriaArea(sash);
        createResultsArea(sash);
        // Search controller: drives the whole search process
        this.controller = new SearchController(this.session, this);
        return sash;
    }

    @objid ("000b386c-c59e-10ab-8258-001ec947cd2a")
    @Override
    public void init() {
        getShell().setText(ModelSearch.I18N.getString("SearchDialog.shell.title")); //$NON-NLS-1$
        setTitle(ModelSearch.I18N.getString("SearchDialog.title")); //$NON-NLS-1$
        this.setMessage(ModelSearch.I18N.getString("SearchDialog.description")); //$NON-NLS-1$
    }

    @objid ("48e673ea-cb6e-4a6b-85a5-a855bbdd0644")
    ISearchPanel getActivePanel() {
        final TabItem[] activeTabs = this.tabFolder.getSelection();
        if (activeTabs.length > 0) {
            return (ISearchPanel) activeTabs[0].getData(SearchDialog.PANEL_DATAKEY);
        }
        return null;
    }

    @objid ("b0310ae5-cec3-42d0-9265-33e6d26ea0df")
    ISearchEngine getActiveEngine() {
        final TabItem[] activeTabs = this.tabFolder.getSelection();
        if (activeTabs.length > 0) {
            return (ISearchEngine) activeTabs[0].getData(SearchDialog.ENGINE_DATAKEY);
        }
        return null;
    }

    @objid ("3026918d-5ab3-4cc7-8d47-6402b5804de0")
    void showResults(ISearchPanel panel, List<Element> resultsToShow) {
        setActivePanel(panel);
        this.resultsPanel.showResults(resultsToShow);
    }

    /**
     * Set the informations the dialog will display once ready.
     * 
     * @param panelClass the panel to show
     * @param searchCriteria the search criteria to show
     * @param found the results to show
     */
    @objid ("b13881d8-f408-44e0-9939-cc914dd4bb1a")
    public void setDisplayedContent(final Class<? extends ISearchPanel> panelClass, final ISearchCriteria searchCriteria, final List<Element> found) {
        final TabFolder tabs = SearchDialog.this.tabFolder;
        
        getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                if (!tabs.isDisposed()) {
                    ISearchPanel panel = null;
                    for (final TabItem tabItem : tabs.getItems()) {
                        if (tabItem.getData(SearchDialog.PANEL_DATAKEY).getClass() == panelClass) {
                            panel = ((ISearchPanel) tabItem.getData(SearchDialog.PANEL_DATAKEY));
                            break;
                        }
                    }
                    if (panel != null) {
                        panel.setCriteria(searchCriteria);
                        SearchDialog.this.showResults(panel, found);
                    }
                }
            }
        
        });
    }

    @objid ("5fc98cdb-d759-42de-8e60-663b9093fc72")
    @Override
    protected Point getInitialSize() {
        return new Point(600, 600);
    }

    @objid ("d124b33a-1766-4bec-ae94-29f779d42eea")
    @Override
    public boolean close() {
        if (equals(SearchDialog.instance)) {
            SearchDialog.instance = null;
        }
        
        if (this.listener != null && this.session != null && this.session.getModelChangeSupport() != null) {
            this.session.getModelChangeSupport().removeModelChangeListener(this.listener);
        }
        
        this.session = null;
        this.results = null;
        return super.close();
    }

    @objid ("4bd85eec-78b3-4126-876f-fce07bc713c8")
    private void createCriteriaArea(SashForm parent) {
        // The criteria edition area
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new FormLayout());
        
        // Tab folder
        this.tabFolder = new TabFolder(composite, SWT.NONE);
        
        // Search button
        this.searchButton = new Button(composite, SWT.PUSH);
        this.searchButton.setToolTipText(ModelSearch.I18N.getString("SearchButton.tooltip"));
        this.searchButton.setImage(UIImages.SEARCH);
        
        // Attachment tab
        FormData fd1 = new FormData();
        fd1.top = new FormAttachment(0, 4);
        fd1.right = new FormAttachment(this.searchButton, -4);
        fd1.bottom = new FormAttachment(100, -4);
        fd1.left = new FormAttachment(0, 4);
        this.tabFolder.setLayoutData(fd1);
        
        // Attachment searchButton
        FormData fd2 = new FormData();
        fd2.top = new FormAttachment(0, 34);
        fd2.right = new FormAttachment(100, -4);
        // fd2.bottom = new FormAttachment(100, -4);
        
        this.searchButton.setLayoutData(fd2);
        
        this.searchButton.addSelectionListener(new SelectionAdapter() {
        
            @SuppressWarnings ("synthetic-access")
            @Override
            public void widgetSelected(SelectionEvent e) {
                SearchDialog.this.controller.runSearch();
            }
        });
    }

    @objid ("17937083-7e07-4dfe-8b8d-266a6003eaf4")
    private void createResultsArea(SashForm parent) {
        // Results area
        final Composite composite = new Composite(parent, 0);
        composite.setLayout(new GridLayout());
        composite.setFont(parent.getFont());
        
        this.resultsPanel = new ResultsPanel(composite, this.navigationService);
        final Control top = this.resultsPanel.getControl();
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    /**
     * Register a search tool tab in the tab folder.
     * 
     * @param label the tool label, to display as tab title
     * @param panel the panel
     * @param engine the search engine used by the tool.
     */
    @objid ("727bc3cc-9c73-4141-b7e4-c2df732cffb9")
    public void registerSearchTool(String label, ISearchPanel panel, ISearchEngine engine) {
        if (panel.isActive(this.session)) {
            panel.initialize(this.tabFolder, this.session, this.controller);
        
            final TabItem tabItem = new TabItem(this.tabFolder, SWT.NONE);
            tabItem.setText(label);
            tabItem.setControl(panel.getControl());
            tabItem.setData(SearchDialog.PANEL_DATAKEY, panel);
            tabItem.setData(SearchDialog.ENGINE_DATAKEY, engine);
        }
    }

    @objid ("8eb1a2aa-e3a5-44e8-a61f-50c12a61f005")
    void setActivePanel(ISearchPanel panel) {
        for (final TabItem tabItem : SearchDialog.this.tabFolder.getItems()) {
            if (tabItem.getData(SearchDialog.PANEL_DATAKEY).getClass() == panel.getClass()) {
                this.tabFolder.setSelection(tabItem);
                break;
            }
        }
    }

    @objid ("eef0646b-b60e-43e2-9f73-54c805f67a35")
    @Override
    protected String getHelpId() {
        return ModelSearch.I18N.getString("SearchDialog.HELP_TOPIC");
    }

    /**
     * Get the search dialog.
     * <p>
     * Displays the existing dialog if one already exists, create it in the other case.
     * 
     * @param parentShell a parent SWT shell if a dialog needs to be created
     * @param session the modeling session
     * @param navigationService the navigation service
     * @return the search dialog instance
     */
    @objid ("22bb9fa1-b482-4481-84ff-3d83c28b1b51")
    public static SearchDialog getInstance(final Shell parentShell, final ICoreSession session, final IModelioNavigationService navigationService) {
        if (parentShell == null) {
            return null;
        }
        
        if (SearchDialog.instance != null) {
            assert (SearchDialog.instance.session.equals(session));
            return SearchDialog.instance;
        }
        
        SearchDialog.instance = new SearchDialog(parentShell, session, navigationService);
        return SearchDialog.instance;
    }

    /**
     * Close the search dialog instance.
     */
    @objid ("fad163e4-b15a-4b1f-91b5-1ea0ef128b95")
    public static void closeInstance() {
        if (SearchDialog.instance != null) {
            SearchDialog.instance.close();
            SearchDialog.instance = null;
        }
    }

    @objid ("b0df4761-faa3-468d-869b-709971a4d450")
    void runASyncSearch() {
        getShell().getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                runSearch();
            }
        
        });
    }

    @objid ("1c1f332c-e5b9-4263-8de2-e813feb44045")
    void runSearch() {
        this.controller.runSearch();
    }

    @objid ("691d4e0b-13ab-4b31-90c3-b32868c9a5eb")
    static class MetaclassLabelProvider extends StyledCellLabelProvider {
        @objid ("4b859808-c0bf-4ea7-923c-dcafb0ddac68")
        @Override
        public void update(ViewerCell cell) {
            final MClass mc = (MClass) cell.getElement();
            cell.setText(mc.getName());
            cell.setImage(MetamodelImageService.getIcon(mc));
            cell.setStyleRanges(StandardModelStyleProvider.getStyleRanges(mc, cell.getText()));
        }

    }

    @objid ("1281e30e-6529-4a2e-b0dd-35b7467833ae")
    static class SearchModelChangeListener implements IModelChangeListener, IStatusChangeListener {
        @objid ("acd6f846-d925-4b5a-9872-b5f1d993aece")
        private final SearchDialog searchDialog;

        @objid ("e7511797-08dd-417c-ac0d-db80e7e1d4ff")
        public SearchModelChangeListener(SearchDialog searchDialog) {
            this.searchDialog = searchDialog;
        }

        @objid ("d026ae1b-8147-4056-9800-07d9f444c2d2")
        @Override
        public void statusChanged(IStatusChangeEvent event) {
            this.searchDialog.runASyncSearch();
        }

        @objid ("c5c8888f-edf2-46f1-a4b2-21d401345549")
        @Override
        public void modelChanged(IModelChangeEvent event) {
            // TODO improve by looking up for deletions in the update event
            this.searchDialog.runASyncSearch();
        }

    }

}
