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

package org.modelio.platform.ui.swt;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Shell provider that can locate the better parent for modal (or not) shells.
 */
@objid ("356467a3-56d8-4a01-80db-0ba3b2b61f41")
public class DefaultShellProvider implements IShellProvider {
    @objid ("041cd753-642f-4ef2-97b8-aae60c303a09")
    @Override
    public Shell getShell() {
        return getBestParentShell();
    }

    /**
     * Returns the most specific modal child from the given list of Shells.
     * 
     * @param toSearch shells to search for modal children
     * @return the most specific modal child, or null if none
     * 
     * @since 3.1
     */
    @objid ("f2ef9660-220e-4626-8e28-6ee34d3ff8b1")
    private static Shell getModalChild(Shell[] toSearch) {
        int modal = SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL | SWT.PRIMARY_MODAL;
        
        for (int i = toSearch.length - 1; i >= 0; i--) {
            Shell shell = toSearch[i];
        
            // Check if this shell has a modal child
            Shell[] children = shell.getShells();
            Shell modalChild = getModalChild(children);
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
     * Get the best parent shell to open a new dialog at the time of the call.
     * 
     * @return the best parent shell to open a new dialog.
     * @throws org.eclipse.swt.SWTException <ul>
     * <li>ERROR_THREAD_INVALID_ACCESS - if not called from a SWT thread.</li>
     * </ul>
     * @since Modelio 3.7
     */
    @objid ("7e21ef06-1939-4748-a388-65b208833c5f")
    public static Shell getBestParentShell() throws SWTException {
        Display d = Display.getCurrent();
        
        if (d == null) {
            throw new SWTException(SWT.ERROR_THREAD_INVALID_ACCESS);
        }
        
        Shell parent = d.getActiveShell();
        
        // Make sure we don't pick a parent that has a modal child (this can lock the app)
        if (parent == null) {
            // If this is a top-level window, then there must not be any open modal windows.
            parent = getModalChild(Display.getCurrent().getShells());
        } else {
            // If we picked a parent with a modal child, use the modal child instead
            Shell modalChild = getModalChild(parent.getShells());
            if (modalChild != null) {
                parent = modalChild;
            }
        }
        
        if (parent == null) {
            // return the last visible root shell
            Shell[] rootShells = d.getShells();
            for (int i = rootShells.length - 1; i >= 0 ; i--) {
                Shell shell = rootShells[i];
                if (! shell.isDisposed() && shell.isVisible()) {
                    return shell;
                }
            }
        }
        return parent;
    }

}
