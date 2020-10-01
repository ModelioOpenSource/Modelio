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

package org.modelio.edition.notes.panelprovider.data;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.swt.widgets.Composite;

/**
 * This class provides an enable/disable Eclipse context in order for its sub-classes implementation to provide SWT shortcuts in
 * their edition field. It works by storing and disabling all the active context in its deactivateContext() method and by restoring
 * them in its reactivateContexts() method.F
 * 
 * @author phv
 */
@objid ("d823ff7a-a8f1-475d-aa26-8c6a7b0fc65d")
public abstract class AbstractContentComposite extends Composite implements INoteContent {
    @objid ("cf0c0885-26ac-43e5-8854-90cb559efe4f")
    private ArrayList<String> activeContexts;

    @objid ("1c9201ea-2759-41a7-afd6-fb3c15c71e4f")
    private final EContextService contextService;

    @objid ("e536812a-3ffa-401b-b512-1587628a632d")
    public AbstractContentComposite(Composite parentComposite, int style, EContextService contextService) {
        super(parentComposite, style);
        this.contextService = contextService;
    }

    @objid ("abeed79a-9d66-41c3-969e-f4548664f8c0")
    protected void deactivateContexts() {
        // We must deactivate the active contexts during the edition, to avoid the editor's shortcuts to be triggered when entering
        // an element's name...
        // Store those contexts for further reactivation
        if (this.contextService != null) {
            this.activeContexts = new ArrayList<>(this.contextService.getActiveContextIds());
            for (final String contextId : this.activeContexts) {
                this.contextService.deactivateContext(contextId);
            }
        }
    }

    @objid ("aaa16675-7e18-437c-821d-710190115c07")
    protected void reactivateContexts() {
        if (this.activeContexts != null) {
            // Restore previously deactivated contexts
            for (final String contextId : this.activeContexts) {
                this.contextService.activateContext(contextId);
            }
            this.activeContexts = null;
        }
    }

}
