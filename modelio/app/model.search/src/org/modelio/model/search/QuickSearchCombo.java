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

package org.modelio.model.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.app.core.navigate.IModelioNavigationService;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.panels.search.model.ModelSearchPanel;
import org.modelio.core.ui.swt.trimbarcomponent.TrimBarComponent;
import org.modelio.gproject.gproject.GProject;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.model.search.dialog.SearchDialog;
import org.modelio.model.search.engine.searchers.model.ModelSearchCriteria;
import org.modelio.model.search.engine.searchers.model.ModelSearchEngine;
import org.modelio.model.search.plugin.ModelSearch;
import org.modelio.ui.UIImages;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Provide a custom toolbar field (based on a combo) to provide the quick search service (search a name space from name pattern)
 */
@objid ("000fd412-c59e-10ab-8258-001ec947cd2a")
@SuppressWarnings ("restriction")
public class QuickSearchCombo extends TrimBarComponent {
    @objid ("765a6949-007f-4fa0-b25c-d8de00d8fdfb")
    private static final String SEARCH_COMMAND_ID = "org.modelio.model.search.command.search";

    @objid ("e67e658f-67b4-41c0-a7b1-719ee82944e1")
    private static String searchText = "";

    @objid ("50f73f1c-03ad-4df0-a21e-b12e4c1780f0")
    @Inject
    private ECommandService commandService;

    @objid ("a805c86c-a41c-46fa-b555-e14d3a2f5494")
    @Inject
    private EHandlerService handlerService;

    @objid ("005a2738-ec63-10ac-8258-001ec947cd2a")
    @Inject
    protected IModelioNavigationService navigationService;

    @objid ("00393dfc-a34e-10ac-8258-001ec947cd2a")
    protected GProject project;

    @objid ("000fdd22-c59e-10ab-8258-001ec947cd2a")
    protected Combo searchCombo;

    @objid ("9b42713c-ef04-4ec1-aba1-0e2763ebfae7")
    private ToolBar searchToolbar;

    @objid ("9c6fb70b-d410-4d0a-ba14-df39a13ab157")
    public QuickSearchCombo() {
        super(ModelSearch.I18N.getString("QuickSearchCombo.SearchZone.label"));
    }

    @objid ("00110512-c59e-10ab-8258-001ec947cd2a")
    @Inject
    @Optional
    void onProjectClosed(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSED) final GProject closedProject) {
        if (!QuickSearchCombo.this.searchCombo.isDisposed()) {
            QuickSearchCombo.this.searchCombo.setEnabled(false);
            QuickSearchCombo.this.searchCombo.removeAll();
            SearchDialog.closeInstance();
        }
        QuickSearchCombo.this.project = null;
        setVisible(false);
    }

    @objid ("0010cade-c59e-10ab-8258-001ec947cd2a")
    @Inject
    @Optional
    void onProjectOpened(@UIEventTopic (ModelioEventTopics.PROJECT_OPENED) final GProject openedProject, IProjectService projectService) {
        QuickSearchCombo.this.searchCombo.setEnabled(true);
        QuickSearchCombo.this.searchCombo.removeAll();
        QuickSearchCombo.this.project = openedProject;
        
        // Reload saved search history
        IPreferenceStore statePrefs = projectService.getStatePreferences();
        StatePersistenceHelper.restoreState(statePrefs, this);
        
        setVisible(true);
    }

    @objid ("6d103eda-afcd-477d-afaf-bfbcec1615be")
    @Override
    protected Control createControl(Composite parent) {
        // The control is a toolbar
        this.searchToolbar = new ToolBar(parent, SWT.WRAP | SWT.RIGHT | SWT.FLAT);
        
        // The toolbar has a combo for the search history
        ToolItem comboItem = new ToolItem(this.searchToolbar, SWT.SEPARATOR);
        this.searchCombo = new Combo(this.searchToolbar, SWT.DROP_DOWN | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        this.searchCombo.setToolTipText(ModelSearch.I18N.getString("QuickSearch.tooltip"));
        this.searchCombo.addSelectionListener(new SCWSelectionListener(this));
        this.searchCombo.setEnabled(false);
        this.searchCombo.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                final Combo combo = (Combo) e.getSource();
                if (ModelSearchCriteria.isValidExpression(combo.getText())) {
                    combo.setForeground(combo.getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND));
                } else {
                    combo.setForeground(combo.getDisplay().getSystemColor(SWT.COLOR_RED));
                }
                QuickSearchCombo.searchText = combo.getText();
            }
        });
        
        comboItem.setWidth(120);
        comboItem.setControl(this.searchCombo);
        
        // The toolbar also has a search button
        ToolItem item = new ToolItem(this.searchToolbar, SWT.PUSH);
        item.setToolTipText(ModelSearch.I18N.getString("QuickSearch.execute.tooltip"));
        item.setImage(UIImages.SEARCH);
        // Execute the e4 handler for "create diagram" with the contribution
        Map<String, Object> parameters = new HashMap<>();
        ParameterizedCommand openCommand = this.commandService.createCommand(QuickSearchCombo.SEARCH_COMMAND_ID, parameters);
        
        item.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                QuickSearchCombo.this.handlerService.executeHandler(openCommand);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // Nothing to do
            }
        });
        return this.searchToolbar;
    }

    /**
     * This method is called when the end-user validate (press ENTER) the filter combo text.<br/>
     * It runs a default search using:
     * <ul>
     * <li>metaclass filter = NameSpace</li>
     * <li>includeRamc = true</li>
     * <li>no stereotype </li<
     * </ul>
     * 
     * @param expression the search expression
     */
    @objid ("00102d0e-c59e-10ab-8258-001ec947cd2a")
    protected void runSearch(String expression) {
        if (this.project == null) {
            return; // not expected, but in any case cannot do anything without
            // a project
        }
        
        if (searchForUuid(expression)) {
            return;
        }
        
        final ICoreSession session = this.project.getSession();
        final ModelSearchEngine searchEngine = new ModelSearchEngine();
        final ModelSearchCriteria searchCriteria = new ModelSearchCriteria();
        searchCriteria.setExpression(expression);
        searchCriteria.addMetaclass(NameSpace.class);
        searchCriteria.setIncludeRamc(true);
        searchCriteria.setStereotype("");
        
        final List<Element> found = searchEngine.search(session, searchCriteria);
        switch (found.size()) {
        case 0:
            // No element found
            runExtendedSearch(expression);
            break;
        case 1:
            // One matching element found => navigate
            this.navigationService.fireNavigate(found.get(0));
            break;
        default:
            // Several matching elements => propose for choice.
            final SearchDialog dlg = SearchDialog.getInstance(this.searchCombo.getShell(), session, this.navigationService);
            dlg.setBlockOnOpen(false);
            dlg.open();
            dlg.setDisplayedContent(ModelSearchPanel.class, searchCriteria, found);
            break;
        }
    }

    @objid ("0010400a-c59e-10ab-8258-001ec947cd2a")
    private void runExtendedSearch(String expression) {
        final ICoreSession session = this.project.getSession();
        final ModelSearchEngine searchEngine = new ModelSearchEngine();
        final ModelSearchCriteria searchCriteria = new ModelSearchCriteria();
        
        searchCriteria.addMetaclass(NameSpace.class);
        searchCriteria.setIncludeRamc(true);
        searchCriteria.setStereotype("");
        
        searchCriteria.setExpression((expression.endsWith(".*") == false) ? expression + ".*" : expression);
        
        final List<Element> found = searchEngine.search(session, searchCriteria);
        switch (found.size()) {
        case 1:
            // One matching element found => navigate
            this.navigationService.fireNavigate(found.get(0));
            break;
        case 0:
        default:
            // No Element found or several matching elements => propose for choice.
            final SearchDialog dlg = SearchDialog.getInstance(this.searchCombo.getShell(), session, this.navigationService);
            dlg.setBlockOnOpen(false);
            dlg.open();
            dlg.setDisplayedContent(ModelSearchPanel.class, searchCriteria, found);
            break;
        }
    }

    @objid ("f8221b89-cfbd-479e-99f9-b7a73aaf1730")
    private boolean searchForUuid(String expression) {
        try {
            final ICoreSession session = this.project.getSession();
            MObject found = session.getModel().findById(MObject.class, expression, IModel.NODELETED);
            if (found != null) {
                this.navigationService.fireNavigate(found);
                return true;
            }
        
        } catch (@SuppressWarnings ("unused") IllegalArgumentException e) {
            // ignore and return false
        }
        return false;
    }

    @objid ("00164fd5-f534-498f-8cb3-886c0a4ce24e")
    @Inject
    @Optional
    void onProjectClosing(@UIEventTopic (ModelioEventTopics.PROJECT_CLOSING) final GProject project, IProjectService projectService) {
        // Save search history
        IPreferenceStore statePrefs = projectService.getStatePreferences();
        StatePersistenceHelper.saveState(statePrefs, this);
    }

    @objid ("eeab8a1b-3900-4557-a72c-6d2fcecc831a")
    public static String getSearchText() {
        return QuickSearchCombo.searchText;
    }

    @objid ("00106d8c-c59e-10ab-8258-001ec947cd2a")
    private static final class SCWSelectionListener implements SelectionListener {
        @objid ("001075ca-c59e-10ab-8258-001ec947cd2a")
         QuickSearchCombo scw;

        @objid ("00107ce6-c59e-10ab-8258-001ec947cd2a")
        public SCWSelectionListener(QuickSearchCombo scw) {
            this.scw = scw;
        }

        @objid ("0010900a-c59e-10ab-8258-001ec947cd2a")
        @Override
        public void widgetSelected(SelectionEvent e) {
            final String expression = this.scw.searchCombo.getText();
            this.scw.searchCombo.setText(expression);
            this.scw.searchCombo.setSelection(new Point(expression.length(), expression.length()));
        }

        @objid ("0010ad2e-c59e-10ab-8258-001ec947cd2a")
        @Override
        public void widgetDefaultSelected(SelectionEvent e) {
            final String expression = this.scw.searchCombo.getText();
            this.scw.runSearch(expression);
            try {
                // Avoid duplicated expressions in the combo
                this.scw.searchCombo.remove(expression);
            } catch (@SuppressWarnings ("unused") IllegalArgumentException ex) {
                // expression not in the combo, ignore exception
            }
            this.scw.searchCombo.add(expression, 0);
            this.scw.searchCombo.setText("");
        }

    }

    @objid ("91684e20-4785-468b-ba13-ed4f18c799dd")
    private static class StatePersistenceHelper {
        @objid ("ceba83cd-be21-4af8-8908-48f8c98c8200")
        private static final String SEARCH_HISTORY_CONFIG_KEY = ModelSearch.PLUGIN_ID + ".SearchHistory";

        @objid ("d6c8b16b-9f33-46a9-ba49-a7722952b411")
        private static final int MAX_HISTORY = 8;

        @objid ("2ec7159e-3cea-45cd-ae88-a2c94c64b589")
        public static void restoreState(IPreferenceStore savedPrefs, QuickSearchCombo searchCombo) {
            // Empty combo list
            searchCombo.searchCombo.removeAll();
            
            // Read search history items
            for (int i = 1; i < StatePersistenceHelper.MAX_HISTORY; i++) {
                String key = StatePersistenceHelper.SEARCH_HISTORY_CONFIG_KEY + i;
                String value = savedPrefs.getString(key);
                if (value != null && !value.isEmpty()) {
                    searchCombo.searchCombo.add(value);
                } else {
                    break;
                }
            }
        }

        @objid ("1680d533-9e27-4546-9767-ea8cf6e93d0d")
        public static void saveState(IPreferenceStore prefs, QuickSearchCombo searchCombo) {
            StatePersistenceHelper.clean(prefs);
            
            int i = 1;
            for (String item : searchCombo.searchCombo.getItems()) {
                String key = StatePersistenceHelper.SEARCH_HISTORY_CONFIG_KEY + i;
                prefs.setValue(key, item);
                i++;
                if (i > StatePersistenceHelper.MAX_HISTORY) {
                    break;
                }
            }
        }

        @objid ("7cd35eb1-5549-4f6d-9336-3e1eb7e947ba")
        private static void clean(IPreferenceStore prefs) {
            // Clean previous values
            for (int i = 1; i < StatePersistenceHelper.MAX_HISTORY; i++) {
                String key = StatePersistenceHelper.SEARCH_HISTORY_CONFIG_KEY + i;
                prefs.setToDefault(key);
            }
        }

    }

}
