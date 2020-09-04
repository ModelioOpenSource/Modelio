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

package org.modelio.script.macro.catalogdialog;

import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.modelio.script.macro.catalog.Macro;
import org.modelio.script.view.ScriptView;

/**
 * Button listener that runs the script selected in the given tree viewer.
 */
@objid ("00696554-c497-106a-bf4f-001ec947cd2a")
class RunMacroButtonListener implements SelectionListener {
    @objid ("003abd12-f1bd-106a-bf4f-001ec947cd2a")
    private final TreeViewer treeviewer;

    /**
     * Creates a listener.
     * @param treeviewer The tree viewer where the selected element is to be looked
     * for.
     */
    @objid ("006979c2-c497-106a-bf4f-001ec947cd2a")
    public RunMacroButtonListener(TreeViewer treeviewer) {
        this.treeviewer = treeviewer;
    }

    @objid ("006a0eaa-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        //
    }

    @objid ("006a0f40-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void widgetSelected(SelectionEvent e) {
        ISelection iSelection = this.treeviewer.getSelection();
        Object selection = ((IStructuredSelection) iSelection).getFirstElement();
        if (selection instanceof Macro) {
            Macro macro = (Macro) selection;
        
            final Path path = macro.getScriptPath();
            // Get the script view console and bring it to front
            final ScriptView scriptView = ScriptView.getScriptView(true);
            if (scriptView != null) {
                // Run the macro in the script view
                scriptView.getScriptRunner().runFile(path, scriptView.getSelectionGetter().getSelection(),
                        scriptView.getSelectionGetter().getSelectedElements());
            }
        
        }
    }

}
