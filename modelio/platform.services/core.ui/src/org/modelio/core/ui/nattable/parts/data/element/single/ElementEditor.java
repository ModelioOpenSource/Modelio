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
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Nat table editor for an {@link MObject}.
 */
@objid ("6dfa6d33-c68c-4ab2-a595-14fd0b8de3c2")
public class ElementEditor extends AbstractCellEditor {
    @objid ("861cca80-73c1-4cb6-9b27-c4dd07d8d02d")
    private final IModelioPickingService pickingService;

    @objid ("8ae77023-7c3f-40c1-bfcd-c050e49b6505")
    private final ICoreSession session;

    @objid ("6cc85d9c-c6db-4e79-993e-7b7ac902c248")
    private Text text;

    @objid ("f970801e-a1fb-4572-8c08-70b0c6eb289a")
    private TextElement textElement;

    /**
     * Build a new editor.
     * 
     * @param session a model session, needed to look for elements.
     * @param pickingService the picking service, to manually select an element in the model.
     */
    @objid ("96c3a426-0870-424c-b392-c4f4c63e04e5")
    public ElementEditor(ICoreSession session, IModelioPickingService pickingService) {
        this.session = session;
        this.pickingService = pickingService;
    }

    /**
     * Had to override this because of the focusListener which closes the editor when focus is lost. This is not expected when focus
     * is lost because of the result popup being displayed.
     */
    @objid ("1e181089-0f8c-40f1-a071-8d4f516d7c33")
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

    @objid ("b01ef03e-6966-4bac-9d7d-f71076782c67")
    @Override
    public void close() {
        // Clean up
        // this.textData = null;
        // if (!this.text.isDisposed()) {
        // this.text.removeKeyListener(this.keyListener);
        // super.close(save);
        // selectNextField();
        // }
        //
        // this.m_Text2 = null;
        //
        // if (this.pickingSession != null) {
        // this.pickingService.stopPicking(this.pickingSession);
        // this.pickingSession = null;
        // }
        this.textElement = null;
        super.close();
    }

    @objid ("9791d923-ddfb-4edf-8b8d-574de2485dfc")
    @Override
    public Text createEditorControl(Composite parentComposite) {
        this.textElement = new TextElement(parentComposite, SWT.NONE, true);
        if (this.session != null) {
            this.textElement.activateCompletion(this.session);
            this.textElement.activateDragAndDrop(this.session);
        }
        if (this.pickingService != null) {
            this.textElement.activatePicking(this.pickingService);
        }
        this.textElement.getAcceptedMetaclasses().add(this.session.getMetamodel().getMClass(Element.class));
        return this.textElement.getTextControl();
    }

    @objid ("29607b62-3cca-46ca-98a0-f525a04a3f41")
    @Override
    public Control getEditorControl() {
        return this.text;
    }

    @objid ("74598533-b82c-407d-8e0a-6ae2fcd5ad03")
    @Override
    public Object getEditorValue() {
        return this.textElement.getValue();
    }

    @objid ("12c87009-3d4b-4d8d-9cb0-f111ecbd4a89")
    @Override
    public void removeEditorControlListeners() {
        final Control editorControl = getEditorControl();
        if (editorControl != null && !editorControl.isDisposed()) {
            // editorControl.removeFocusListener(this.focusListener);
            // editorControl.removeTraverseListener(this.traverseListener);
        }
    }

    /**
     * Values are MRef. The wrapped TextElement control works with MObject instances. Therefore, the MRef value is resolved into an
     * MObject instance that can be used to initialize the TextElement editor.
     */
    @objid ("bd83f590-dbdb-4a5c-b604-f2cdb5fe76a4")
    @Override
    public void setEditorValue(Object value) {
        if (value != null) {
            final MObject obj;
            if (value instanceof MObject) {
                obj = (MObject) value;
            } else if (value instanceof MRef) {
                final MRef mRef = (MRef) value;
                obj = this.session.getModel().findByRef(mRef, IModel.NODELETED);
            } else {
                obj = null;
            }
            this.textElement.setValue(obj);
        } else {
            this.textElement.setValue(null);
        }
    }

    @objid ("a76a742c-9bff-4bd6-9f7a-e302337ac250")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.text = createEditorControl(parentComposite);
        
        this.textElement.addListener(new ITextElementSelectionListener() {
            @Override
            public void selectedElementChanged(MObject oldElement, MObject newElement) {
                commit(MoveDirectionEnum.NONE, true);
            }
        });
        
        setEditorValue(originalCanonicalValue);
        
        this.textElement.getTextControl().selectAll();
        this.textElement.getTextControl().setFocus();
        return this.text;
    }

}
