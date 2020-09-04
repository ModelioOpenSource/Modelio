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

package org.modelio.edition.notes.panelprovider.data.htmlnotes;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.modelio.edition.notes.panelprovider.data.AbstractContentComposite;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.ui.UIColor;
import org.modelio.ui.htmleditor.HtmlComposer;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * HTML Note content editor.
 */
@objid ("7819d4fc-e7cb-4a9a-9f13-b46d4b725298")
public class HtmlNoteContentComposite extends AbstractContentComposite {
    @objid ("956b4340-0daf-4b19-a55e-999a9a39d014")
    private final HtmlComposer htmlText;

    @objid ("bda4c5f0-8e1d-4590-8b2d-06456ec225e2")
    private final Controler controler;

    @objid ("d618d5cb-e240-466b-8ebd-e32e144c937d")
    private Note note = null;

    @objid ("781a01be-a389-40f5-a59c-9a9c33325024")
    @Override
    public void setInput(final ModelElement aNote) {
        this.note = (Note) aNote;
        
        if (this.note != null) {
            this.htmlText.setHtml(this.note.getContent());
            this.htmlText.setEditable(this.note.isModifiable());
        } else {
            this.htmlText.setHtml("");
        }
        leaveEdition();
    }

    @objid ("93822920-a36a-4185-91d6-30a0bd2f08e0")
    @Override
    public Control getControl() {
        return this;
    }

    @objid ("d3a2e9f7-c588-48a2-a143-09ada737bd37")
    @Override
    public Note getNoteElement() {
        return this.note;
    }

    @objid ("809354b4-6c0a-4b83-8d8d-f1853c4f833e")
    private void enterEdition() {
        deactivateContexts();
        if (this.note != null && this.note.isModifiable()) {
            this.htmlText.setBackground(UIColor.TEXT_WRITABLE_BG);
        } else {
            this.htmlText.setBackground(UIColor.TEXT_READONLY_BG);
        }
    }

    @objid ("31a5b972-7f39-421b-b58c-b13d586c9f88")
    private void leaveEdition() {
        if (this.note != null && this.note.isModifiable()) {
            this.htmlText.setBackground(UIColor.POSTIT_YELLOW);
        } else {
            this.htmlText.setBackground(UIColor.TEXT_READONLY_BG);
        }
        reactivateContexts();
    }

    @objid ("6cd6a4a8-a8b4-488e-ba13-9a269acf26d7")
    @Override
    public void dispose() {
        this.htmlText.dispose();
        super.dispose();
    }

    @objid ("e9e7aae2-41d1-4770-921b-c4136d2e0db0")
    public HtmlNoteContentComposite(Composite parentComposite, int style, EContextService contextService) {
        super(parentComposite, style, contextService);
        setLayout(new FillLayout());
        this.htmlText = new HtmlComposer(this, SWT.BORDER | SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
        this.controler = new Controler(this, this.htmlText);
        this.htmlText.addFocusListener(this.controler);
    }

    @objid ("9d1541a0-570d-46eb-966b-695e82b19752")
    private static class Controler implements FocusListener {
        @objid ("9fa306f8-8ca5-44a0-8b6c-c5f890ebbed8")
        private final HtmlNoteContentComposite view;

        @objid ("836ad8f9-68c8-43a9-83cc-725afb528c5e")
        private final HtmlComposer htmlComposer;

        @objid ("f00283b2-6678-430b-b79f-c976fba85e07")
        public Controler(HtmlNoteContentComposite htmlNoteContentComposite, HtmlComposer htmlComposer) {
            this.view = htmlNoteContentComposite;
            this.htmlComposer = htmlComposer;
        }

        /**
         * The note text has been modified.<br/>
         * The model change event fired by the transaction is in charge of refreshing the whole GUI.
         */
        @objid ("0bea3b3d-0856-4791-ab08-f3cc1f1c40a4")
        private void changeContent(Note editedNote, String s) {
            if (editedNote != null && !s.equals(editedNote.getContent()) && editedNote.isModifiable()) {
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
        @objid ("3d27ac34-e569-4351-af7e-7384ee636f67")
        @Override
        public void focusGained(FocusEvent e) {
            final Note editedNote = this.view.getNoteElement();
            if (editedNote != null && editedNote.getStatus().isModifiable()) {
                this.view.enterEdition();
            }
        }

        @objid ("bbfc738e-70ef-428c-b774-f6ba86c65032")
        @Override
        public void focusLost(FocusEvent e) {
            final Note editedNote = this.view.getNoteElement();
            changeContent(editedNote, this.htmlComposer.getHtml());
            this.view.leaveEdition();
        }

    }

}
