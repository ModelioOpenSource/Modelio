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

package org.modelio.core.ui.cert;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class to get the best parenting possible for a dialog. If there
 * is a modal shell create it so as to avoid two modal dialogs. If not then
 * return the shell of the active workbench window. If neither can be found
 * return null.
 */
@objid ("d3a1a8bc-cd36-4558-9660-ed6fccd72c78")
public class TopShellGetter implements IShellProvider {
    @objid ("e4d7ea1b-278f-4f39-9f03-76e1353d6eb2")
    private MApplication application;

    /**
     * initialize the service.
     * 
     * @param application the application model.
     */
    @objid ("3a67721c-52d3-4c4c-af4f-c5f8ccacffac")
    public TopShellGetter(MApplication application) {
        this.application = application;
    }

    /**
     * Return the modal shell that is currently open. If there isn't one then
     * return null. If there are stacked modal shells, return the top one.
     * 
     * @param shell A shell to exclude from the search. May be <code>null</code>.
     * @return Shell or <code>null</code>.
     */
    @objid ("ffd7c7f4-9add-4136-821b-793916e3a7b0")
    private static Shell getModalShellExcluding(Shell shell) {
        // If shell is null or disposed, then look through all shells
        if (shell == null || shell.isDisposed()) {
            return getModalChildExcluding(Display.getCurrent().getShells(), shell);
        }
        
        // Start with the shell to exclude and check it's shells
        return getModalChildExcluding(shell.getShells(), shell);
    }

    /**
     * Return the modal shell that is currently open. If there isn't one then
     * return null.
     * 
     * @param toSearch shells to search for modal children
     * @param toExclude shell to ignore
     * @return the most specific modal child, or null if none
     */
    @objid ("85580cfc-dace-4876-8768-75a30e9c12c6")
    private static Shell getModalChildExcluding(Shell[] toSearch, Shell toExclude) {
        int modal = SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL
                | SWT.PRIMARY_MODAL;
        
        // Make sure we don't pick a parent that has a modal child (this can
        // lock the app)
        // If we picked a parent with a modal child, use the modal child instead
        
        for (int i = toSearch.length - 1; i >= 0; i--) {
            Shell shell = toSearch[i];
            if(shell.equals(toExclude)) {
                continue;
            }
        
            // Check if this shell has a modal child
            Shell[] children = shell.getShells();
            Shell modalChild = getModalChildExcluding(children, toExclude);
            if (modalChild != null) {
                return modalChild;
            }
        
            // If not, check if this shell is modal itself
            if (shell.isVisible() && (shell.getStyle() & modal) != 0) {
                return shell;
            }
        }
        return null;
    }

    /**
     * Utility method to get the best parenting possible for a dialog. If there
     * is a modal shell create it so as to avoid two modal dialogs. If not then
     * return the shell of the active workbench window. If neither can be found
     * return null.
     * 
     * @return Shell or <code>null</code>
     */
    @objid ("90d15d96-8d7d-44e5-a2f0-a3bb0f0e349f")
    @Override
    public Shell getShell() {
        Shell modal = getModalShellExcluding(null);
        if (modal != null) {
            return modal;
        }
        return getNonModalShell();
    }

    /**
     * Get the active non modal shell. If there isn't one return null.
     */
    @objid ("aea30513-97f6-4dea-a7cc-5a517e7c9276")
    private Shell getNonModalShell() {
        if (this.application != null) {
            MWindow window = this.application.getSelectedElement();
            if (window != null) {
                Object widget = window.getWidget();
                if (widget instanceof Shell) {
                    return (Shell) widget;
                }
            }
            for (MWindow child : this.application.getChildren()) {
                Object widget = child.getWidget();
                if (widget instanceof Shell) {
                    return (Shell) widget;
                }
            }
        }
        
        // Return first shell found
        for (Shell shell : Display.getCurrent().getShells()) {
            if (! shell.isDisposed() && shell.isVisible()
                    && (shell.getStyle() & (SWT.TOOL )) == 0) {
                return shell;
            }
        }
        return null;
    }

}
