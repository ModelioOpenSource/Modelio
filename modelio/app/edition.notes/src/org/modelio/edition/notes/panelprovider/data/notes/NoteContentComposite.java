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

package org.modelio.edition.notes.panelprovider.data.notes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.edition.notes.panelprovider.data.AbstractContentComposite;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.ui.UIColor;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

@objid ("dc74c63a-cd25-4dc9-97bf-1d117a5b5bcd")
public class NoteContentComposite extends AbstractContentComposite {
    @objid ("a8222f19-4d44-4419-b684-d982c9d2b6f8")
    private Note note = null;

    @objid ("53ecbd06-f30f-4204-aa17-b6297d3f7e32")
    private final Text text;

    @objid ("452b46b8-16ef-4329-98c3-46efba1d8549")
    private final Controller controler;

    @objid ("c34ed728-a126-4a4d-989e-a5a37b41f3f4")
    public NoteContentComposite(Composite parentComposite, int style, EContextService contextService) {
        super(parentComposite, style, contextService);
        
        setLayout(new FillLayout());
        this.text = new Text(this, SWT.BORDER | SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
        this.controler = new Controller(this);
        this.text.addFocusListener(this.controler);
        this.text.addKeyListener(this.controler);
    }

    @objid ("6f0754d2-84e3-432d-8017-8309a6248917")
    @Override
    public void setInput(final ModelElement aNote) {
        this.note = (Note) aNote;
        if (this.note != null) {
            this.text.setText(this.note.getContent());
        } else {
            this.text.setText("");
        }
        leaveEdition();
    }

    @objid ("d7551a0b-e4e2-4d69-acf2-a7017dce32b3")
    @Override
    public Control getControl() {
        return this;
    }

    @objid ("a7421fcb-1d8d-426f-a032-9f2e509769f9")
    @Override
    public Note getNoteElement() {
        return this.note;
    }

    @objid ("be433aa9-39a9-447a-b3e3-06a190ba64a3")
    @Override
    public void dispose() {
        this.text.dispose();
        super.dispose();
    }

    @objid ("18539c95-f1df-424a-a920-adac1ddf07b7")
    private void enterEdition() {
        deactivateContexts();
        if (this.note != null && this.note.isModifiable()) {
            this.text.setBackground(UIColor.TEXT_WRITABLE_BG);
        } else {
            this.text.setBackground(UIColor.TEXT_READONLY_BG);
        }
    }

    @objid ("8f5e6031-bc00-44b6-8a3a-860cf8fcec6e")
    private void leaveEdition() {
        if (this.note != null && this.note.isModifiable()) {
            this.text.setBackground(UIColor.POSTIT_YELLOW);
        } else {
            this.text.setBackground(UIColor.TEXT_READONLY_BG);
        }
        reactivateContexts();
    }

    @objid ("0201b08c-2af5-4767-85e6-c2effd9e98ce")
    private static class Controller implements FocusListener, KeyListener {
        @objid ("f62d9d38-745c-4a51-b066-a1f112dcb7d1")
        private final NoteContentComposite view;

        @objid ("02ad5bad-a71f-4fd0-9777-973edc86f5ab")
        public Controller(NoteContentComposite view) {
            this.view = view;
        }

        /**
         * The note text has been modified.<br/>
         * The model change event fired by the transaction is in charge of refreshing the whole GUI.
         */
        @objid ("bb345c1c-7e4b-4872-979f-374d38d69db8")
        private void changeContent(Note editedNote, String s) {
            if (!s.equals(editedNote.getContent())) {
                try (ITransaction transaction = CoreSession.getSession(editedNote).getTransactionSupport()
                        .createTransaction(EditionNotes.I18N.getString("UpdateNote"))) {
                    editedNote.setContent(s.replaceAll("\r\n", "\n"));
                    transaction.commit();
                }
            }
        }

        /**
         * On focus gained: enter edition mode if the note is modifiable
         */
        @objid ("30a3269d-ef09-4981-aebe-c2e3c9d04b33")
        @Override
        public void focusGained(FocusEvent e) {
            final Text text = (Text) e.getSource();
            final Note editedNote = this.view.getNoteElement();
            if (editedNote != null && editedNote.getStatus().isModifiable()) {
                this.view.enterEdition();
            }
        }

        @objid ("e06f8ee5-f2f2-491f-8d14-bfc55d745242")
        @Override
        public void focusLost(FocusEvent e) {
            final Text text = (Text) e.getSource();
            final Note editedNote = this.view.getNoteElement();
            changeContent(editedNote, text.getText());
            this.view.leaveEdition();
        }

        @objid ("b496bd50-eedc-4754-814a-f6bf934ae1ec")
        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == SWT.CR) {
                e.doit = false;
            }
        }

        @objid ("fe7f2ca2-d0ab-47a6-83d4-0419761929bf")
        @Override
        public void keyReleased(KeyEvent e) {
            final Text text = (Text) e.getSource();
            final Note note = this.view.getNoteElement();
            if (e.keyCode == SWT.ESC) {
                // ESC
                // restore content from note
                text.setText(note.getContent());
                this.view.leaveEdition();
            } else if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == SWT.CR) {
                // CTRL Enter
                changeContent(note, text.getText());
                this.view.leaveEdition();
            } else if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == 'a') {
                // CTRL A
                text.selectAll();
            }
        }

    }

}
