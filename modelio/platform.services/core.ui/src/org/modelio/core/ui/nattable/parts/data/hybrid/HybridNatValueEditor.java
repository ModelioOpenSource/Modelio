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

package org.modelio.core.ui.nattable.parts.data.hybrid;

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
import org.modelio.core.ui.swt.hybridtext.HybridTextElement;
import org.modelio.core.ui.swt.hybridtext.IHybridTextElementSelectionListener;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Nat table editor for an {@link IHybridNatValue}.
 */
@objid ("13c0039e-0520-4a1c-8d0b-bc284a3c78ca")
public class HybridNatValueEditor extends AbstractCellEditor {
    @objid ("cc4e6500-c435-4e28-8bcb-20d3812191d1")
    private IHybridNatValue elementData;

    @objid ("166736f5-e3bf-4af6-bf97-50d7e3e0a4ed")
    private final IModelioPickingService pickingService;

    @objid ("cd0cce8c-77fe-4087-b9a5-3e5a1ae9bbd9")
    private final ICoreSession session;

    @objid ("8a492c64-d238-4af8-8a3c-a6a126d00c11")
    private Text text;

    @objid ("d08d3ca5-98cd-4706-83ac-40dec333f5b6")
    private HybridTextElement textElement;

    /**
     * Build a new editor.
     * @param session a model session, needed to look for elements. CAN BE
     * <code>null</code> in which case no completion will occur in
     * the editor.
     * @param pickingService the picking service, to manually select an element in the
     * model. CAN BE <code>null</code> in which case
     * no picking will occur in the editor.
     */
    @objid ("04da068d-8374-49ef-923a-796c8b4a5470")
    public HybridNatValueEditor(ICoreSession session, IModelioPickingService pickingService) {
        this.session = session;
        this.pickingService = pickingService;
    }

    /**
     * Had to override this because of the focusListener which closes the editor when focus is lost. This is not expected when focus
     * is lost because of the result popup being displayed.
     */
    @objid ("e630e5a3-a2d5-408e-8f38-70c4da430872")
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

    @objid ("f9c81574-6284-4420-8d81-4fa36038ed61")
    @Override
    public void close() {
        // Clean up
        this.textElement = null;
        super.close();
    }

    @objid ("73befe93-af16-4d3f-a301-6b10a393cd0f")
    @Override
    public Text createEditorControl(Composite parentComposite) {
        this.textElement = new HybridTextElement(parentComposite, SWT.NONE);
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

    @objid ("5ade6ff6-93e6-49dd-b606-71940061d849")
    @Override
    public Control getEditorControl() {
        return this.text;
    }

    @objid ("92f4136f-8329-48f7-87c3-dab4dc3c45e4")
    @Override
    public Object getEditorValue() {
        this.elementData.setValue(this.textElement.getValue());
        return this.elementData.getValue();
    }

    @objid ("28f15c2b-0058-4a9f-86e4-21996eb1880c")
    @Override
    public void removeEditorControlListeners() {
        final Control editorControl = getEditorControl();
        if (editorControl != null && !editorControl.isDisposed()) {
            // editorControl.removeFocusListener(this.focusListener);
            // editorControl.removeTraverseListener(this.traverseListener);
        }
    }

    /**
     * Values are {@link IHybridNatValue}. The wrapped HybridTextElement control works with Element instances. Therefore, the MRef value is resolved into an
     * Element instance that can be used to initialize the HybridTextElement editor.
     */
    @objid ("2ca93737-fec5-483e-ab10-82f4d9943111")
    @Override
    public void setEditorValue(Object value) {
        if (value != null) {
            final Object obj;
            if (value instanceof IHybridNatValue) {
                this.elementData = (IHybridNatValue)value;
                obj = this.elementData.getValue();
                
                this.textElement.getAcceptedMetaclasses().clear();
                for (Class<? extends MObject> allowedClass : this.elementData.getAllowedClasses()) {
                    this.textElement.getAcceptedMetaclasses().add(this.session.getMetamodel().getMClass(allowedClass));
                    this.textElement.setFilter(this.elementData.getElementFilter());
                }
                this.textElement.setAcceptStringValue(this.elementData.acceptStringValue());
                this.textElement.setValue(obj);
            } else {
            // FIXME error
                return;
            }
        } else {
            this.textElement.setValue(null);
        }
    }

    @objid ("1bc743d1-1993-4562-9aef-48209dc6a67d")
    @Override
    protected Control activateCell(Composite parentComposite, Object originalCanonicalValue) {
        this.text = createEditorControl(parentComposite);
        
        this.textElement.addListener(new IHybridTextElementSelectionListener() {
            @Override
            public void selectedElementChanged(Object oldElement, Object newElement) {
                commit(MoveDirectionEnum.NONE, true);
            }
        });
        
        setEditorValue(originalCanonicalValue);
        
        this.textElement.getTextControl().selectAll();
        this.textElement.getTextControl().setFocus();
        return this.text;
    }

}
