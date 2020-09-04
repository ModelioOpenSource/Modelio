/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.core.ui.nattable.parts.data.element.single;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.edit.editor.AbstractCellEditor;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer.MoveDirectionEnum;
import org.eclipse.nebula.widgets.nattable.widget.EditModeEnum;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.core.picking.IModelioPickingService;
import org.modelio.core.ui.swt.textelement.ITextElementSelectionListener;
import org.modelio.core.ui.swt.textelement.TextElement;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Nat table editor for an {@link IElementNatValue}.
 */
@objid ("2b48c6ee-c2da-4720-aa80-07df15f8d4a2")
public class ElementNatValueEditor extends AbstractCellEditor {
    @objid ("6d0dbcb8-3018-40e3-bf6a-b24f91c300d2")
    private IElementNatValue elementData;

    @objid ("212ae658-6322-49cb-a333-af4c653c24a5")
    private final IModelioPickingService pickingService;

    @objid ("631e123f-03a2-4492-8db5-ad0facfe0865")
    private final ICoreSession session;

    @objid ("aff41e78-e6d9-4c55-a5b3-d014361b75e8")
    private Text text;

    @objid ("b159b474-3a7d-4f23-87fe-d3d8a7eb614d")
    private TextElement textElement;

    /**
     * Build a new editor.
     * 
     * @param session a model session, needed to look for elements. CAN BE <code>null</code> in which case no completion will occur in the editor.
     * @param pickingService the picking service, to manually select an element in the model. CAN BE <code>null</code> in which case no picking will occur in the editor.
     */
    @objid ("748fceb2-7bc6-43ce-9d04-e68ba346dbd9")
    public ElementNatValueEditor(ICoreSession session, IModelioPickingService pickingService) {
        this.session = session;
        this.pickingService = pickingService;
    }

    /**
     * Had to override this because of the focusListener which closes the editor when focus is lost. This is not expected when focus is lost because of the result popup being displayed.
     */
    @objid ("f3757d28-9e0a-41c2-b893-8af86a8cbfeb")
    @Override
    public void addEditorControlListeners() {
        final Control editorControl = getEditorControl();
        if (editorControl != null && !editorControl.isDisposed() && this.editMode == EditModeEnum.INLINE) {
            // only add the focus and traverse listeners for inline mode
            // editorControl.addFocusListener(this.focusListener);
            // editorControl.addTraverseListener(this.traverseListener);
        
            editorControl.addKeyListener(new KeyListener() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.keyCode == SWT.ESC) {
                        close();
                    }
                }
        
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.keyCode == SWT.ESC) {
                        close();
                    }
                }
            });
        }
    }

    @objid ("1538d513-68f1-4319-9ea2-d123571692c4")
    @Override
    public void close() {
        // Clean up
        this.textElement = null;
        super.close();
    }

    @objid ("65b5ec19-dd5c-44e7-b779-f18a29aa73ae")
    @Override
    public Text createEditorControl(Composite parentComposite) {
        this.textElement = new TextElement(parentComposite, SWT.NONE, true);
        if (this.session != null) {
            this.textElement.activateCompletion(this.session);
        }
        if (this.pickingService != null) {
            this.textElement.activatePicking(this.pickingService);
        }
        this.textElement.getAcceptedMetaclasses().add(this.session.getMetamodel().getMClass(Element.class));
        return this.textElement.getTextControl();
    }

    @objid ("e2fbe9f9-391c-42d6-94c1-47b05636ba3f")
    @Override
    public Control getEditorControl() {
        return this.text;
    }

    @objid ("c59490e5-89aa-40cb-b3e9-4ad6b2c402ab")
    @Override
    public Object getEditorValue() {
        this.elementData.setValue(this.textElement.getValue());
        return this.elementData.getValue();
    }

    @objid ("1dc88e53-cc19-40bd-9e81-827468725b2e")
    @Override
    public void removeEditorControlListeners() {
        final Control editorControl = getEditorControl();
        if (editorControl != null && !editorControl.isDisposed()) {
            // editorControl.removeFocusListener(this.focusListener);
            // editorControl.removeTraverseListener(this.traverseListener);
        }
    }

    /**
     * Values are {@link IElementNatValue}. The wrapped TextElement control works with Element instances. Therefore, the MRef value is resolved into an Element instance that can be used to initialize the TextElement editor.
     */
    @objid ("b4758b7a-123c-4e79-8030-d3a679f2c45d")
    @Override
    public void setEditorValue(Object value) {
        if (value != null) {
            final MObject obj;
            if (value instanceof IElementNatValue) {
                this.elementData = (IElementNatValue) value;
                obj = this.elementData.getValue();
        
                this.textElement.getAcceptedMetaclasses().clear();
                for (Class<? extends MObject> allowedClass : this.elementData.getAllowedClasses()) {
                    this.textElement.getAcceptedMetaclasses().add(this.session.getMetamodel().getMClass(allowedClass));
                }
                this.textElement.setFilter(this.elementData.getElementFilter());
                this.textElement.setAcceptNullValue(this.elementData.acceptNullValue());
                this.textElement.setValue(obj);
            } else {
                // FIXME error
                return;
            }
        } else {
            this.textElement.setValue(null);
        }
    }

    @objid ("f683b024-1a92-4ad1-9d95-791ad73c0498")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.text = createEditorControl(parentComposite);
        
        this.textElement.addListener(new ITextElementSelectionListener() {
            @Override
            public void selectedElementChanged(MObject oldElement, MObject newElement) {
                commit(MoveDirectionEnum.DOWN, true);
            }
        });
        
        setEditorValue(originalCanonicalValue);
        
        this.textElement.getTextControl().selectAll();
        this.textElement.getTextControl().setFocus();
        return this.text;
    }

}
