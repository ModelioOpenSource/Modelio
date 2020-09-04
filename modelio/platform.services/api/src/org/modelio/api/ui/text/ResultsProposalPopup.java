/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.text;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class defines the popup of proposed results that appears when user uses the 'Ctrl+Space'
 * shortcut and there are several valid results.
 */
@objid ("5bf100ef-911c-11e0-9de7-002564c97630")
public class ResultsProposalPopup extends PopupDialog {
    @objid ("5bf100f1-911c-11e0-9de7-002564c97630")
    protected boolean loop;

    @objid ("5bf12802-911c-11e0-9de7-002564c97630")
    protected boolean acceptNullValue = false;

    @objid ("d96601f8-5b07-11e2-9c97-002564c97630")
    protected List<MObject> elements;

    @objid ("d96601fb-5b07-11e2-9c97-002564c97630")
    protected MObject selected;

    @objid ("d96601fc-5b07-11e2-9c97-002564c97630")
    protected Composite parent;

    @objid ("d96601fd-5b07-11e2-9c97-002564c97630")
    protected org.eclipse.swt.widgets.List list;

    @objid ("d9662909-5b07-11e2-9c97-002564c97630")
    protected Rectangle listRectangle;

    /**
     * Open a popup to choose elements from.
     * 
     * @return the element chosen in the popup. Might be <code>null</code>.
     */
    @objid ("5bf1eb55-911c-11e0-9de7-002564c97630")
    public Object getChoice() {
        Display display = Display.getDefault();
        
        open();
        setTitleText(Api.I18N.getMessage("ResultsProposalPopup.choose", this.elements.size())); //$NON-NLS-1$
        //getShell().pack();
        if (getShell().getSize().y < 100) {
            getShell().setSize(getShell().getSize().x, 100);
        }
        
        if (this.acceptNullValue) {
            this.list.add(Api.I18N.getMessage("ResultsProposalPopup.None")); //$NON-NLS-1$
        }
        
        for (MObject e : this.elements) {
            if (e instanceof ModelElement) {
                ModelElement me = (ModelElement) e;
                String item = me.getName();
                MObject owner = me.getCompositionOwner();
                if (owner instanceof ModelElement) {
                    item = item + Api.I18N.getMessage("ResultsProposalPopup.From",
                           ((ModelElement) me.getCompositionOwner()).getName())
                           ;
                }
                this.list.add(item);
            }
        }
        
        // Validate on double click
        this.list.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent arg0) {
                int selectedIndex = ResultsProposalPopup.this.list.getSelectionIndex();
                if (ResultsProposalPopup.this.acceptNullValue) {
                    if (selectedIndex < 1) {
                        ResultsProposalPopup.this.selected = null;
                    } else {
                        ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex - 1);
                    }
                } else {
                    ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex);
                }
                ResultsProposalPopup.this.loop = false;
            }
        
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                 // nothing to do here
            }
        });
        
        // Validate on pressing <enter> key
        this.list.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Nothing to do
            }
        
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.character == SWT.CR) {
                    int selectedIndex = ResultsProposalPopup.this.list.getSelectionIndex();
        
                    if (selectedIndex > -1) {
                        if (ResultsProposalPopup.this.acceptNullValue) {
                            if (selectedIndex == 0) {
                                ResultsProposalPopup.this.selected = null;
                            } else {
                                ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex - 1);
                            }
                        } else {
                            ResultsProposalPopup.this.selected = ResultsProposalPopup.this.elements.get(selectedIndex);
                        }
                        ResultsProposalPopup.this.loop = false;
                    }
                } else if (e.character == SWT.DEL) {
                    ResultsProposalPopup.this.loop = false;
                }
            }
        });
        
        // Run a local event loop : the popup is modal.
        this.loop = true;
        while (this.loop) {
            try {
                if (!display.readAndDispatch()) {
                    display.sleep();
                }
            } catch (Exception e) {
                Api.LOG.error(e);
            }
        }
        close();
        return this.selected;
    }

    @objid ("5bf21267-911c-11e0-9de7-002564c97630")
    @Override
    protected void adjustBounds() {
        getShell().setBounds(this.listRectangle);
    }

    /**
     * Constructor initializing a ResultsProposalPopup.
     * 
     * @param control the swt control displaying the popup.
     * @param elements the elements to display in the popup.
     * @param acceptNullValue indicates whether or not the "null" value must be shown in the popup.
     */
    @objid ("d966290a-5b07-11e2-9c97-002564c97630")
    public ResultsProposalPopup(final Control control, final List<MObject> elements, final boolean acceptNullValue) {
        super(control.getShell(), INFOPOPUPRESIZE_SHELLSTYLE,
        /*take focus*/true,
        /*persist size*/false,
        /*persist location*/false,
        /*show dialog menu*/false,
        /*show persist action*/false,
        /* info title*/Api.I18N.getMessage("ResultsProposalPopup.title"), //$NON-NLS-1$
        /* info description */Api.I18N.getMessage("ResultsProposalPopup.description")); //$NON-NLS-1$
        
        this.parent = control.getParent();
        this.elements = elements;
        this.selected = null;
        this.loop = false;
        
        this.acceptNullValue = acceptNullValue;
        
        Rectangle textRect = control.getBounds();
        Rectangle tableRect = this.parent.getBounds();
        
        int posX = textRect.x;
        int posY = textRect.y;
        int width = textRect.width;
        int height = (tableRect.height - textRect.y);
        
        if (width < 100) {
            width = 100;
        }
        if (height < 100) {
            height = 100;
        }
        this.listRectangle = Display.getDefault().map(this.parent, null, new Rectangle(posX, posY, width, height));
    }

    @objid ("d9667729-5b07-11e2-9c97-002564c97630")
    @Override
    protected Point getInitialSize() {
        return new Point(10, 10);
    }

    @objid ("d9669e38-5b07-11e2-9c97-002564c97630")
    @Override
    protected Point getInitialLocation(final Point initialSize) {
        return new Point(this.listRectangle.x, this.listRectangle.y);
    }

    @objid ("d966c549-5b07-11e2-9c97-002564c97630")
    @Override
    protected Control createDialogArea(final Composite area) {
        Composite composite = (Composite) super.createDialogArea(area);
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        this.list = new org.eclipse.swt.widgets.List(composite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
        return composite;
    }

    @objid ("d9671368-5b07-11e2-9c97-002564c97630")
    @Override
    protected void configureShell(final Shell shell) {
        super.configureShell(shell);
    }

}
