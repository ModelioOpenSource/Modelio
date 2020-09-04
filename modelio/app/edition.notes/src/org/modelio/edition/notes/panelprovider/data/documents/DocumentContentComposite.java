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

package org.modelio.edition.notes.panelprovider.data.documents;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.app.core.activation.IActivationService;
import org.modelio.edition.notes.panelprovider.data.INoteContent;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.ui.UIColor;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Right panel for rich notes.
 */
@objid ("b59b7e18-a5fb-4464-a47e-a5fb60309b7d")
public class DocumentContentComposite extends Composite implements INoteContent {
    @objid ("f8d5bc5a-5859-45eb-9162-8816ad6216a9")
    protected Document xdoc = null;

    @objid ("b6f5e0ee-cbb0-43d3-9d8d-10bb881041ae")
    protected ICoreSession modelingSession = null;

    @objid ("c0084a90-9b64-4807-a145-15f131ddd6ad")
    private DocumentContentModifier noteModifier = null;

    @objid ("bbed1fea-2aec-4c5f-b6b3-50c4b8a6d602")
    private Text text = null;

    @objid ("723bfa38-28cc-432b-b885-a4bf6d94001f")
    private final Button editButton;

    @objid ("7b1ffcc1-21e2-4723-951f-e04a2992c490")
    private IActivationService activationService;

    /**
     * Constructor
     * @param parentComposite the parent composite
     * @param style style bits
     * @param activationService Modelio activation service
     */
    @objid ("b208600d-4f46-446c-bd9e-78a4e7cea5ee")
    public DocumentContentComposite(Composite parentComposite, int style, IActivationService activationService) {
        super(parentComposite, style);
        
        this.activationService = activationService;
        
        GridLayout layout = new GridLayout(1, true);
        setLayout(layout);
        
        this.text = new Text(this, SWT.BORDER | SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
        this.noteModifier = new DocumentContentModifier();
        GridData gdText = new GridData();
        gdText.grabExcessHorizontalSpace = true;
        gdText.horizontalAlignment = SWT.FILL;
        gdText.grabExcessVerticalSpace = true;
        gdText.verticalAlignment = SWT.FILL;
        this.text.setLayoutData(gdText);
        
        this.editButton = new Button(this, SWT.PUSH);
        
        GridData gdButton = new GridData();
        gdButton.grabExcessHorizontalSpace = true;
        gdButton.horizontalAlignment = SWT.FILL;
        this.editButton.setLayoutData(gdButton);
        this.editButton.setText(EditionNotes.I18N.getString("EditDocumentButton.label"));
        this.editButton.setToolTipText(EditionNotes.I18N.getString("EditDocumentButton.tooltip"));
        
        this.editButton.addSelectionListener(new SelectionListener() {
        
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (getActivationService() != null)
                    getActivationService().activateMObject(DocumentContentComposite.this.xdoc);
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do
            }
        });
    }

    @objid ("019bfe33-5cbc-49ba-9e8f-2de8e1b1517a")
    @Override
    public void setInput(final ModelElement aNote) {
        if (aNote != null) {
            this.xdoc = ((Document) aNote);
            this.text.setText(this.xdoc.getAbstract());
            this.text.setData(this.xdoc);
            if (aNote.isModifiable()) {
                this.text.setBackground(UIColor.POSTIT_YELLOW);
            } else {
                this.text.setBackground(UIColor.TEXT_READONLY_BG);
            }
            start(CoreSession.getSession(aNote));
        } else {
            this.xdoc = null;
            this.text.setText("");
            this.text.setData(null);
            this.text.setBackground(UIColor.TEXT_READONLY_BG);
            stop();
        }
    }

    @objid ("4cea44d2-7154-4498-b730-025a04d8ed0b")
    private void start(ICoreSession session) {
        this.modelingSession = session;
        this.text.addFocusListener(this.noteModifier);
        this.text.addKeyListener(this.noteModifier);
    }

    @objid ("e1cd799e-6b35-40ca-9c2c-d1daf705edc4")
    private void stop() {
        this.text.removeFocusListener(this.noteModifier);
        this.text.removeKeyListener(this.noteModifier);
        this.modelingSession = null;
    }

    @objid ("b65c2a0b-c91f-40ce-910f-ec06024ddcbd")
    @Override
    public Control getControl() {
        return this;
    }

    @objid ("a95724ef-c9d0-4459-9bc7-bcb1bdd7294d")
    @Override
    public ModelElement getNoteElement() {
        return this.xdoc;
    }

    @objid ("fd0f18a0-4080-4496-bef1-40e035650c87")
    IActivationService getActivationService() {
        return this.activationService;
    }

    @objid ("b1eb78a9-eb2d-4711-8eb7-05eefcfe8df0")
    private class DocumentContentModifier implements FocusListener, KeyListener {
        @objid ("c7c64601-5696-4f2a-be69-b6f832f2d7b2")
        public DocumentContentModifier() {
        }

        @objid ("f4a5402a-36f8-4e3e-9385-53c77c6ee8a5")
        @Override
        public void focusGained(final FocusEvent event) {
            Text docText = (Text) event.getSource();
            Document doc = (Document) docText.getData();
            
            if (doc != null) {
                if (doc.getStatus().isModifiable()) {
                    docText.setBackground(UIColor.TEXT_WRITABLE_BG);
                } else {
                    docText.getShell().setFocus();
                }
            }
        }

        @objid ("9c14eb75-b647-487c-8239-88bbadda1829")
        @Override
        public void focusLost(final FocusEvent event) {
            validate(event);
        }

        @objid ("db7b682a-f362-4754-8c6e-6708c1cd416a")
        @Override
        public void keyPressed(final KeyEvent event) {
            if ((event.stateMask &= SWT.MOD1) != 0 && event.keyCode == SWT.CR) {
                event.doit = false;
            }
        }

        @objid ("81b40f57-f5bb-4b1b-ad01-94579ef79c14")
        @Override
        public void keyReleased(final KeyEvent event) {
            Text docText = (Text) event.getSource();
            Document note = (Document) docText.getData();
            
            if (event.keyCode == SWT.ESC) {
                // restore content from note
                docText.setText(note.getAbstract());
                // this.notesList.getControl().setFocus();
            } else if ((event.stateMask &= SWT.MOD1) != 0 && event.keyCode == SWT.CR) {
                validate(event);
                docText.getShell().setFocus();
            } else if ((event.stateMask &= SWT.MOD1) != 0 && event.keyCode == 'a') {
                docText.selectAll();
            }
        }

        @objid ("4cfe9e52-4fe6-4382-acee-e04458a1075b")
        private void validate(final TypedEvent event) {
            Text docText = (Text) event.getSource();
            Document note = (Document) docText.getData();
            
            if (note != null && note.getStatus().isModifiable()) {
                String s = docText.getText();
            
                if (!s.equals(note.getAbstract())) {
                    try (ITransaction transaction = DocumentContentComposite.this.modelingSession.getTransactionSupport()
                            .createTransaction(EditionNotes.I18N.getString("UpdateDescriptionNote"))) {
                        s = s.replaceAll("\r\n", "\n");
                        note.setAbstract(s);
                        transaction.commit();
                    }
                }
            
                if (note.isModifiable()) {
                    docText.setBackground(UIColor.POSTIT_YELLOW);
                } else {
                    docText.setBackground(UIColor.TEXT_READONLY_BG);
                }
            }
        }

    }

}
