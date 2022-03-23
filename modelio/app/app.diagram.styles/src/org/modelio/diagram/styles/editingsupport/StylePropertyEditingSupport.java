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
package org.modelio.diagram.styles.editingsupport;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.editingsupport.color.ColorCellEditor3;
import org.modelio.diagram.styles.editingsupport.combo.EnumComboBoxCellEditor;
import org.modelio.diagram.styles.editingsupport.font.FontDialogEditor;
import org.modelio.diagram.styles.editingsupport.number.IntegerCellEditor;
import org.modelio.diagram.styles.viewer.StyleEditPanelUIData;
import org.modelio.platform.ui.CoreColorRegistry;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * StyleEditingSupport provides EditingSupport implementation for the StyleViewer.
 * <p>
 * It must be able to provide a Label and a CellEditor for all the supported StyleKey value types. It must also be able to get and
 * set values during edition, again dealing with all the possible StyleKey value types.
 */
@objid ("85b2e4b2-1926-11e2-92d2-001ec947c8cc")
public class StylePropertyEditingSupport extends EditingSupport {
    @objid ("0412520a-3421-4167-824e-b165064a6148")
    private final ColumnViewer viewer;

    /**
     * Initialize the StylePropertyEditingSupport.
     * @param treeViewer The style viewer.
     */
    @objid ("85b2e4b6-1926-11e2-92d2-001ec947c8cc")
    public  StylePropertyEditingSupport(ColumnViewer treeViewer) {
        super(treeViewer);
        this.viewer = treeViewer;
        
    }

    @objid ("85b2e4ba-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected boolean canEdit(Object element) {
        StyleEditPanelUIData data = (StyleEditPanelUIData) this.viewer.getInput();
        
        if (data ==  null) {
            return false;
        }
        
        if (! data.isEditable()) {
            return false;
        }
        
        IStyle style = data.getStyleData();
        ISymbolViewItem item = (ISymbolViewItem) element;
        return item.isEditable(style);
    }

    @objid ("85b546fd-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected CellEditor getCellEditor(Object element) {
        if (!(element instanceof ISymbolViewItem)) {
            return null;
        }
        
        final ISymbolViewItem item = (ISymbolViewItem) element;
        final Class<?> stype = item.getType();
        
        final Composite tree = (Composite) this.viewer.getControl();
        
        if (! item.getPossibleValues().isEmpty()) {
            return new EnumComboBoxCellEditor(tree, item.getPossibleValues(), SWT.SINGLE);
        }
        
        if (stype.equals(Boolean.class)) {
            return new org.eclipse.jface.viewers.CheckboxCellEditor();
        }
        if (stype.equals(String.class)) {
            return new TextCellEditor(tree, SWT.SINGLE);
        }
        if (stype.equals(Integer.class)) {
            return new IntegerCellEditor(tree, SWT.SINGLE);
        }
        if (stype.equals(Font.class)) {
            return new FontDialogEditor(tree, SWT.SINGLE);
        }
        if (stype.equals(Color.class)) {
            return new ColorCellEditor3(tree, SWT.SINGLE);
        }
        // if (stype.equals(MRef.class)) {
        // ICoreSession session = this.viewer.getModel().getSession();
        // IModelioPickingService pickingService = this.viewer.getPickingService();
        //
        // return new ElementCellEditor(tree, session, pickingService);
        // }
        return null;
    }

    @objid ("85b54703-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected Object getValue(Object element) {
        if (!(element instanceof ISymbolViewItem)) {
            return null;
        }
        
        final ISymbolViewItem item = (ISymbolViewItem) element;
        final IStyle editedStyle = getEditedStyle();
        final Class<?> stype = item.getType();
        Object value = item.getValue(editedStyle);
        
        if (stype.equals(Color.class)) {
            return ((Color) value).getRGB();
        }
        return value;
    }

    @objid ("85b7a956-1926-11e2-92d2-001ec947c8cc")
    @Override
    protected void setValue(Object element, Object value) {
        if (!(element instanceof ISymbolViewItem)) {
            return;
        }
        
        final ISymbolViewItem item = (ISymbolViewItem) element;
        final IStyle editedStyle = getEditedStyle();
        final Class<?> stype = item.getType();
        
        if (value != null) {
            // Color
            if (stype.equals(Color.class)) {
                item.setValue(editedStyle, CoreColorRegistry.getColor((RGB) value));
            } else {
                item.setValue(editedStyle, value);
            }
        } else {
            // MRef
            if (stype.equals(MRef.class)) {
                item.setValue(editedStyle, value);
            }
        }
        
    }

    @objid ("80ae4efc-d720-4e38-81b1-8d512e1b59e4")
    private IStyle getEditedStyle() {
        return ((StyleEditPanelUIData) this.viewer.getInput()).getStyleData();
    }

}
