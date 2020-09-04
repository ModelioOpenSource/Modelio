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

package org.modelio.editors.texteditors.txt;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.texteditors.IDocumentEditor;
import org.modelio.editors.texteditors.input.IDocumentInput;
import org.modelio.ui.UIColor;

@objid ("7b6ef959-2a77-11e2-9fb9-bc305ba4815c")
public class TXTEditor implements IDocumentEditor {
    @objid ("725e2c94-2fd9-11e2-a79f-bc305ba4815c")
    public static final String EDITOR_ID = "org.modelio.editors.texteditors.txt";

    @objid ("7b6ef95a-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentInput input;

    @objid ("7b6ef95b-2a77-11e2-9fb9-bc305ba4815c")
    private TextViewer viewer;

    @objid ("32b79760-2e73-11e2-ab6d-bc305ba4815c")
    private MPart editor;

    @objid ("7b707ff9-2a77-11e2-9fb9-bc305ba4815c")
    @Inject
    public TXTEditor(Composite parent, IDocumentInput input, MPart editor) {
        this.input = input;
        this.editor = editor;
        parent.setLayout(new FillLayout());
        
        int styles = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION;
        this.viewer = new TextViewer(parent, styles);
        this.viewer.setDocument(input.getDocument(null));
    }

    @objid ("7b707ffe-2a77-11e2-9fb9-bc305ba4815c")
    @Persist
    public void save() {
        this.input.save();
    }

    @objid ("7b708001-2a77-11e2-9fb9-bc305ba4815c")
    @Focus
    public void setFocus() {
        this.viewer.getControl().setFocus();
    }

    @objid ("7b708004-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IDocumentInput getDocumentInput() {
        return this.input;
    }

    @objid ("c1db5832-2e5d-11e2-a8ff-bc305ba4815c")
    @PreDestroy
    private void destroy() {
        this.input.dispose();
    }

    @objid ("c1db5835-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void setReadonlyMode(boolean readOnly) {
        if (readOnly) {
            this.viewer.setEditable(false);
            this.viewer.getTextWidget().setBackground(UIColor.TEXT_READONLY_BG);
            this.editor.setIconURI("platform:/plugin/org.modelio.editors.texteditors/icons/texteditor_ro.png");
        } else {
            this.viewer.setEditable(true);
            this.viewer.getControl().setBackground(UIColor.TEXT_WRITABLE_BG);
            this.editor.setIconURI("platform:/plugin/org.modelio.editors.texteditors/icons/texteditor_rw.png");
        }
    }

    @objid ("74029d59-2e6e-472a-98e1-0db4fda233d8")
    @Override
    public TextViewer getViewer() {
        return this.viewer;
    }

}
