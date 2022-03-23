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
package org.modelio.linkeditor.handlers.leftdepth;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Spinner;
import org.modelio.linkeditor.plugin.LinkEditor;
import org.modelio.linkeditor.view.ILinkEditorView;

@objid ("1b49d867-5e33-11e2-b81d-002564c97630")
public class LeftDepthSpinner {
    @objid ("f062343e-4119-4f0c-a43e-070e45931285")
    private static final int MINIMUM_DEPTH = 0;

    @objid ("c00d2d3e-fb2b-4e57-b39f-0164416a782f")
    private static final int MAXIMUM_DEPTH = 4;

    @objid ("d422be42-f83e-4140-9b7d-bb8b9088222b")
    private Spinner spinner;

    @objid ("1b49d86e-5e33-11e2-b81d-002564c97630")
    @PostConstruct
    protected Control createControl(final Composite parent, MPart part) {
        if (!(part.getObject() instanceof ILinkEditorView)) {
            return null;
        }
        
        final ILinkEditorView linkEditorView = (ILinkEditorView) part.getObject();
        
        this.spinner = new Spinner(parent, SWT.BORDER);
        this.spinner.setMinimum(0);
        
        int selectedValue = linkEditorView.getLinkEditor().getConfigurator().getLeftDepth();
        
        this.spinner.setValues(selectedValue, MINIMUM_DEPTH, MAXIMUM_DEPTH, 0, 1, 1);
        this.spinner.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                linkEditorView.getLinkEditor().getConfigurator().setLeftDepth(getSpinner().getSelection());
                // linkEditorView.refreshFromCurrentSelection();
            }
        });
        this.spinner.setToolTipText(LinkEditor.I18N.getString("LeftDepthSpinner.tooltip"));
        this.spinner.pack();
        return this.spinner;
    }

    @objid ("e18215a3-db3b-4bf3-b81d-cebd6d3c1357")
    Spinner getSpinner() {
        return this.spinner;
    }

    @objid ("b2a4ae9e-6daa-4eb9-a066-89558140c818")
    public void setSpinnerValue(final int depth) {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                Spinner aSpinner = getSpinner();
                if (!aSpinner.isDisposed()) {
                    aSpinner.setValues(depth, MINIMUM_DEPTH, MAXIMUM_DEPTH, 0, 1, 1);
                }
            }
        });
        
    }

    @objid ("eaf641a6-fcc2-4a1c-8ad2-817527a22f8f")
    public int getValue() {
        return this.spinner.getSelection();
    }

}
