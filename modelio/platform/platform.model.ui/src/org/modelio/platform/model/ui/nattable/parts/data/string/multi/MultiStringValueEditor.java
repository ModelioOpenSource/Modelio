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
package org.modelio.platform.model.ui.nattable.parts.data.string.multi;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.platform.model.ui.swt.multistring.MultiStringEditionComposite;

/**
 * {@link ICellEditor} implementation for {@link IMultiStringNatValue} edition. Uses a {@link MultiStringEditionComposite} component as editor control.
 */
@objid ("b52c0a4b-93a6-43bb-a31f-e6a3d3992a1c")
public class MultiStringValueEditor extends AbstractCellEditor {
    @objid ("a4f70ed6-1608-426a-9c33-25767aa1ba87")
    private MultiStringEditionComposite cdt;

    @objid ("76b48e3c-4af0-4a71-81b4-566026e881ca")
    @Override
    public void close() {
        super.close();
        this.cdt = null;
        
    }

    @objid ("9da4d783-f273-4777-b612-9070db9cc8d2")
    @Override
    public MultiStringEditionComposite createEditorControl(Composite parentComposite) {
        return new MultiStringEditionComposite(parentComposite, SWT.NONE, -1);
    }

    @objid ("b5b67e14-76a7-4509-b839-8ab0c48614c5")
    @Override
    public Control getEditorControl() {
        return this.cdt;
    }

    @objid ("5745d591-f5bb-4ad4-bbdb-8618461e9770")
    @Override
    public Object getEditorValue() {
        return this.cdt.getContent();
    }

    @objid ("50d6d1b6-1f11-4cc7-9158-e7ef8f4092ed")
    @Override
    public boolean openInline(IConfigRegistry registry, List<String> configLabels) {
        return false;
    }

    @objid ("d5500f37-5b1b-4ec7-aea6-4098aead140b")
    @SuppressWarnings ("unchecked")
    @Override
    public void setEditorValue(Object value) {
        this.cdt.initContent((List<String>) value);
    }

    @objid ("dcc28436-0c8b-4b6d-a683-316787958cca")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.cdt = createEditorControl(parentComposite);
        this.cdt.initContent((originalCanonicalValue != null) ? ((IMultiStringNatValue) originalCanonicalValue).getValue() : new ArrayList<>());
        return this.cdt;
    }

    @objid ("e6dc0d5c-5f62-48f0-9113-1f7d68d33e5a")
    @Override
    public boolean commit(MoveDirectionEnum direction, boolean closeAfterCommit, boolean skipValidation) {
        boolean commit = super.commit(direction, closeAfterCommit, skipValidation);
        
        if (commit) {
            this.layerCell.getLayer().doCommand(new SelectCellCommand(
                    this.layerCell.getLayer(),
                    this.layerCell.getColumnPosition(),
                    this.layerCell.getRowPosition() + 1,
                    false,
                    false));
        }
        return commit;
    }

}
