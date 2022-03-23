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
package org.modelio.platform.model.ui.nattable.parts.data.bool;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ICellEditor;
import org.eclipse.nebula.widgets.nattable.painter.cell.CheckBoxPainter;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.widget.EditModeEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * {@link ICellEditor} implementation for checkbox editors. Compared to a TextCellEditor, this editor will immediately commit and close itself on interaction. This is the same behaviour like a regular for a regular checkbox.
 */
@objid ("09936770-49eb-4c0d-9605-406aeaf61350")
public class CheckBoxCellEditor extends AbstractCellEditor {
    /**
     * The current state of the checkbox stating the corresponding value.
     */
    @objid ("b3896c13-bad6-4c25-a5af-27c84db50ffa")
    private boolean checked;

    /**
     * The editor control which is a Canvas that paints the corresponding checkbox images. To adjust the look & feel for checkbox editors you need to look at {@link CheckBoxPainter}
     */
    @objid ("22644e2b-5a03-4aa6-9b76-6cc12719ae16")
    private Canvas canvas;

    /**
     * As soon as the editor is activated, flip the current data value and commit it. The repaint will pick up the new value and flip the image. This is only done if the mouse click is done within the rectangle of the painted checkbox image.
     */
    @objid ("2dd83090-edb4-4c77-bff0-4a3e160e1772")
    @Override
    protected Control activateCell(Composite parent, Object originalCanonicalValue) {
        // if this editor was activated by clicking a letter or digit key, do
        // nothing
        if (originalCanonicalValue instanceof Character) {
            return null;
        }
        
        setCanonicalValue(originalCanonicalValue);
        
        this.checked = !this.checked;
        
        this.canvas = createEditorControl(parent);
        
        commit(MoveDirectionEnum.DOWN, false);
        
        if (this.editMode == EditModeEnum.INLINE) {
            // Close editor so it will react to subsequent clicks on the cell
            if (this.canvas != null && !this.canvas.isDisposed()) {
                close();
            }
        }
        return this.canvas;
    }

    @objid ("ef6a86a9-4f32-4bba-9a8a-8179ad8113f5")
    @Override
    public Boolean getEditorValue() {
        return Boolean.valueOf(this.checked);
    }

    /**
     * Sets the given value to editor control. As this method is called by {@link AbstractCellEditor#setCanonicalValue(Object)} the given value should be already a converted Boolean value. The only other values accepted in here are <code>null</code> which
     * is interpreted as <code>false</code> and Strings than can be converted to Boolean directly. Every other object will result in setting the editor value to <code>false</code>.
     * @param value The display value to set to the wrapped editor control.
     */
    @objid ("938596e1-1ae5-42a2-976d-23078901ba2e")
    @Override
    public void setEditorValue(Object value) {
        if (value == null) {
            this.checked = false;
        } else {
            if (value instanceof Boolean) {
                this.checked = ((Boolean) value).booleanValue();
            } else if (value instanceof String) {
                this.checked = Boolean.valueOf((String) value).booleanValue();
            } else {
                this.checked = false;
            }
        }
        
    }

    @objid ("6046188d-2cd6-4655-98f0-0b01ac3743d8")
    @Override
    public Canvas getEditorControl() {
        return this.canvas;
    }

    @objid ("8af6f84f-dd0c-462d-822a-e0e441f18cac")
    @Override
    public Canvas createEditorControl(Composite parent) {
        final Canvas canvas = new Canvas(parent, SWT.NONE);
        
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent e) {
                CheckBoxCellEditor.this.checked = !CheckBoxCellEditor.this.checked;
                canvas.redraw();
            }
        });
        return canvas;
    }

    @objid ("def089f1-aa02-4ee3-af98-c635d9a68b57")
    @Override
    public boolean openMultiEditDialog() {
        // as it doesn't make sense to open a subdialog for checkbox multi
        // editing, this is not supported
        return false;
    }

    @objid ("cb19a693-2a84-409c-8f16-b6f17f66c34b")
    @Override
    public boolean activateAtAnyPosition() {
        // as the checkbox should only change its value if the icon that
        // represents the checkbox is clicked, this method needs to return
        // false so the IMouseEventMatcher can react on that.
        // Note that on return false here creates the need to add a special
        // matcher for this editor
        // to be activated.
        return false;
    }

    @objid ("89d4dec9-4519-4584-afc1-a620f9538513")
    @Override
    public boolean activateOnTraversal(IConfigRegistry configRegistry, List<String> configLabels) {
        // the checkbox editor is immediately changing the value and closing
        // the again on activation. on tab traversal it is not intended that the
        // value changes therefore this editor is not activated on traversal
        return false;
    }

}
