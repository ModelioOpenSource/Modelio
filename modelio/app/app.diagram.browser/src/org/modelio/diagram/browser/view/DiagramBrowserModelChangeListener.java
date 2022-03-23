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
package org.modelio.diagram.browser.view;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.modelio.vcore.session.api.model.change.IModelChangeEvent;
import org.modelio.vcore.session.api.model.change.IModelChangeListener;
import org.modelio.vcore.session.api.model.change.IStatusChangeEvent;
import org.modelio.vcore.session.api.model.change.IStatusChangeListener;

@objid ("000fdd72-0d4f-10c6-842f-001ec947cd2a")
public class DiagramBrowserModelChangeListener implements IModelChangeListener, IStatusChangeListener {
    @objid ("000ff460-0d4f-10c6-842f-001ec947cd2a")
    private final DiagramBrowserPanelProvider view;

    @objid ("000ffd8e-0d4f-10c6-842f-001ec947cd2a")
    public  DiagramBrowserModelChangeListener(DiagramBrowserPanelProvider view) {
        super();
        this.view = view;
        
    }

    @objid ("001011b6-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void modelChanged(IModelChangeEvent event) {
        // First delegate the event to the model of the browser content provider if supported
        if (this.view.getPanel().getContentProvider() instanceof IModelChangeListener) {
            ((IModelChangeListener) this.view.getPanel().getContentProvider()).modelChanged(event);
        }
        
        // Re enter the UI thread
        Display display = Display.getDefault();
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                updateNavigatorView();
            }
        });
        
    }

    @objid ("001055e0-0d4f-10c6-842f-001ec947cd2a")
    @Override
    public void statusChanged(IStatusChangeEvent event) {
        // First delegate the event to the model of the browser content provider is supported
        if (this.view.getPanel().getContentProvider() instanceof IStatusChangeListener) {
            ((IStatusChangeListener) this.view.getPanel().getContentProvider()).statusChanged(event);
        }
        
        // Re enter the UI thread
        Display display = Display.getDefault();
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                updateNavigatorView();
            }
        });
        
    }

    @objid ("00108e16-0d4f-10c6-842f-001ec947cd2a")
    protected void updateNavigatorView() {
        if (this.view.getPanel().getTree().isDisposed()) {
            return;
        }
        this.view.getPanel().setSelection(new StructuredSelection());
        this.view.getPanel().refresh(true);
        
    }

}
