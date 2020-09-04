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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.modelio.core.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.core.ui.panels.search.ISearchController;
import org.modelio.core.ui.panels.search.ISearchPanel;
import org.modelio.model.search.engine.ISearchEngine;
import org.modelio.model.search.plugin.ModelSearch;
import org.modelio.vcore.session.api.ICoreSession;

@objid ("c879a116-1844-4677-b0f0-e903f625a7eb")
class SearchController implements ISearchController {
    @objid ("dedd6c80-6f41-43c4-a7ac-12470500f62e")
    private static final String SEARCHTOOL_EXTENSION_ID = "org.modelio.model.search.searchtool";

    @objid ("635ea4a7-b452-4e26-8432-a7dd4efe73f7")
    private final SearchDialog searchDialog;

    @objid ("73fe70d8-6703-4c4f-8821-0a7d854bb3a6")
    private final ICoreSession session;

    @objid ("6d7dc2e8-ee06-4d4c-b215-d7e0d7f2c08f")
    public SearchController(final ICoreSession session, final SearchDialog searchDialog) {
        this.session = session;
        this.searchDialog = searchDialog;
        
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (final IConfigurationElement e : new ExtensionPointContributionManager(SEARCHTOOL_EXTENSION_ID).getExtensions("searchtool")) {
                    parseSearchTool(searchDialog, e);
                }
            }
        });
    }

    @objid ("500b7fa8-de64-4903-b5bc-051f25f8894b")
    @Override
    public void runSearch() {
        final ISearchPanel panel = this.searchDialog.getActivePanel();
        final ISearchEngine engine = this.searchDialog.getActiveEngine();
        
        if (panel != null && engine != null) {
            doRunSearch(engine, panel);
        }
    }

    @objid ("955a8e12-ab81-4818-8d32-42a580b3ed6b")
    private void doRunSearch(final ISearchEngine engine, final ISearchPanel panel) {
        final ICoreSession curSession = SearchController.this.session;
        final SearchDialog dialog = SearchController.this.searchDialog;
        
        BusyIndicator.showWhile(null, new Runnable() {
            @Override
            public void run() {
                final Display display = dialog.getShell().getDisplay();
        
                // Reset results
                dialog.showResults(panel, null);
        
                // Wait for dialog to be ready
                while (display.readAndDispatch()) {
                    // noop
                }
        
                // Compute and display results
                dialog.showResults(panel, engine.search(curSession, panel.getCriteria()));
            }
        });
    }

    @objid ("0514ffd1-b119-4c78-9958-98fa4b396bff")
    protected void parseSearchTool(final SearchDialog dlg, final IConfigurationElement elt) {
        final String label  = elt.getAttribute("label");
        
        Object panel;
        Object engine;
        try {
            panel = elt.createExecutableExtension("panel");
            engine =  elt.createExecutableExtension("engine");
        
            if (panel instanceof ISearchPanel && engine instanceof ISearchEngine) {
                dlg.registerSearchTool(label, (ISearchPanel)panel, (ISearchEngine)engine);
            }
        
        } catch (final CoreException e) {
            ModelSearch.LOG.error(e);
        }
    }

}
