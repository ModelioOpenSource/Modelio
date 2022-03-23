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
package org.modelio.semantic.browser.panel;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.inject.Inject;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.modelio.gproject.gproject.GProject;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("b6cb8b83-cd3c-4575-8b24-55536fd47bff")
public class SmBrowserController {
    @objid ("a2a94ccf-8832-42d6-a990-83c306536b0f")
    private SmBrowserUi ui;

    @objid ("3f7fdcc5-f2f4-4ecb-a771-97dbd91ee85e")
    @Inject
    @Optional
    private ESelectionService selectionService;

    @objid ("75d5dbd4-19b6-4824-b1d0-c7b1e4fd1042")
    @Inject
    @Optional
    private EMenuService menuService;

    @objid ("f1183911-4b01-4770-814d-af678c77a1d2")
    public SmBrowserUi createUi(Composite parent) {
        this.ui = new SmBrowserUi(parent, this, this.menuService);
        return this.ui;
    }

    @objid ("4284d48e-3ed3-4974-88da-24cd888480cf")
    public void onProjectOpened(GProject openedProject) {
        this.ui.setInput(openedProject);
    }

    @objid ("c40cfb91-e7fb-4264-abef-61f4536f05ba")
    public void onProjectClosed(GProject project) {
        if (this.ui != null && !this.ui.getComposite().isDisposed()) {
            this.ui.setSelection(new StructuredSelection());
            this.ui.setInput(null);
        }
        
    }

    @objid ("1f6b77ad-5297-4a6e-8429-94987ddb95d0")
    public void select(List<MObject> elements) {
        StructuredSelection selection = new StructuredSelection(elements);
        if (!Objects.equals(this.ui.getSelection(), selection)) {
            this.ui.setSelection(selection);
        }
        
    }

    /**
     * Called when the selection change in the tree viewer => propagates selection to application
     * @param selection
     */
    @objid ("dfd5a7fb-46a5-4e31-b913-8aa8aef4ff58")
    void onSelectionChanged(ISelection selection) {
        if (this.selectionService != null) {
            this.selectionService.setSelection(selection);
        }
        
    }

}
