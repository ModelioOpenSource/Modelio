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

package org.modelio.core.ui.nattable.parts.data.string.multi;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.core.ui.swt.multistring.MultiStringEditionComposite;

/**
 * {@link ICellEditor} implementation for multi-string edition.
 * Uses a {@link MultiStringEditionComposite} component as editor control.
 */
@objid ("09430a05-38e5-43dd-8dc8-5656faa9274c")
public class MultiStringEditor extends AbstractCellEditor {
    @objid ("4a169a3c-08ce-4692-91bd-44e832d3c8f6")
    private MultiStringEditionComposite cdt;

    @objid ("7eea24c2-5103-4a0d-a356-6681b0215762")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
    }

    @objid ("b5e5a9fe-3624-460e-8cb2-79807188dfb0")
    @Override
    public MultiStringEditionComposite createEditorControl(Composite parentComposite) {
        return new MultiStringEditionComposite(parentComposite, SWT.NONE, -1);
    }

    @objid ("6fefb6a7-e49a-4563-b9b1-1d371f38cddd")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("572ea556-dea1-401c-840a-36e20349cbe6")
    @Override
    public Object getEditorValue() {
        return this.cdt.getContent();
    }

    @objid ("6e92657b-2fe6-466d-818d-9cbcaec75845")
    @Override
    public boolean openInline(IConfigRegistry registry, List<String> configLabels) {
        return false;
    }

    @objid ("dbf8ed9a-b088-439b-ae39-ea2f2470188d")
    @SuppressWarnings("unchecked")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.initContent((List<String>) value);
    }

    @objid ("73b49408-b743-4ad8-bbae-82072aede1da")
    @SuppressWarnings("unchecked")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.initContent((List<String>) originalCanonicalValue);
        return this.cdt;
    }

}
