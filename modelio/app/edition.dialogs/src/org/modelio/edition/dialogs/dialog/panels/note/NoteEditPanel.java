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

package org.modelio.edition.dialogs.dialog.panels.note;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.core.ui.MimeServices.MimeType;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.ui.UIColor;
import org.modelio.ui.htmleditor.HtmlComposer;
import org.modelio.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

/**
 * Edition panel for 'Note' objects.
 * 
 * @author phv
 */
@objid ("a3fbbd25-f351-41c6-8446-c35f407f9749")
@Creatable
public class NoteEditPanel implements IPanelProvider {
    @objid ("b901a65f-33b4-43fc-9c58-54f01a7ed92f")
    private NoteEditController controller;

    @objid ("a14ddc80-20d7-4b9f-956e-550badeb5c11")
    private NoteEditView view;

    @objid ("89569a73-5111-450f-8b47-e56b13122da3")
    public NoteEditPanel() {
        super();
    }

    @objid ("b64dc77d-a0a7-46ed-b14f-ff79071599ef")
    @Override
    public Object createPanel(Composite parent) {
        this.controller = new NoteEditController();
        this.view = new NoteEditView(parent, this.controller);
        this.controller.setView(this.view);
        return this.view.getContainer();
    }

    @objid ("553f394d-fe2b-4707-9369-2771f239dd80")
    @Override
    public void dispose() {
        this.view.dispose();
        this.view = null;
        this.controller = null;
    }

    @objid ("cd09dbf7-b279-4828-ae5a-697dd184d849")
    @Override
    public String getHelpTopic() {
        // no specific help topic
        return null;
    }

    @objid ("85aa5542-3ecb-4c4e-9c16-3db3cebd141e")
    @Override
    public Object getInput() {
        return this.controller.getNote();
    }

    @objid ("d9a452ad-6ad2-46f5-a6c2-eb0b73a47bd1")
    @Override
    public Object getPanel() {
        return this.view != null ? this.view.getContainer() : null;
    }

    @objid ("68c7d1c1-43db-4a39-a8ce-5d0a34c2b435")
    @Override
    public boolean isRelevantFor(Object obj) {
        if (obj instanceof ISelection) {
            return SelectionHelper.getFirst((ISelection) obj, Note.class) != null;
        } else {
            return obj instanceof Note;
        }
    }

    @objid ("27023a91-7d40-45ee-b7ba-6e63658abcab")
    @Override
    public void setInput(Object input) {
        Note note = null;
        
        if (input instanceof ISelection) {
            note = SelectionHelper.getFirst((ISelection) input, Note.class);
        } else if (input instanceof Note) {
            note = (Note) input;
        }
        
        // No note, clear all
        if (note != null && note.isValid()) {
            this.controller.setNote(note);
            return;
        } else {
            this.controller.setNote(null);
            return;
        }
    }

    /**
     * @author phv
     */
    @objid ("a398487e-c7b3-4198-a8f1-a2b3ab19ca74")
    private static class NoteEditController {
        /**
         * The Note being edited
         */
        @objid ("23e8fe3b-4816-4184-8098-80c8e8f3d7cb")
        private Note note;

        /**
         * The view driven by this controller
         */
        @objid ("e08e883d-a2c0-468e-9007-99582f5ddfc0")
        private NoteEditView view;

        /**
         * C'tor
         */
        @objid ("b43f3acb-9dc5-44fd-9fe8-7dec1f60e804")
        public NoteEditController() {
        }

        /**
         * Set the view controlled by this controller
         * @param view
         */
        @objid ("983e9fcc-9d76-4d3d-ba94-f06cda488cc6")
        public void setView(NoteEditView view) {
            this.view = view;
        }

        /**
         * Get the Note being edited
         * @return
         */
        @objid ("4eae2fe7-4b98-4ee7-9f21-bf2bd0f15d94")
        public Note getNote() {
            return this.note;
        }

        /**
         * Set the Note being edited. This method also refreshes the view
         * contents.
         * @param note
         */
        @objid ("bbf3026c-0843-4cd7-90e6-b68c9ac4c4d5")
        public void setNote(Note note) {
            this.note = note;
            
            if (this.note != null) {
                switch (getNoteMimeMode(this.note)) {
                case HTML:
                    this.view.setMimeMode(MimeType.HTML);
                    break;
                case PLAIN:
                default:
                    this.view.setMimeMode(MimeType.PLAIN);
                    break;
                }
            
                final NoteType type = this.note.getModel();
            
                final boolean htmlAllowed = getNoteTypeMimeMode(this.note.getModel()) == MimeType.HTML;
                this.view.enableModeSwitcher(htmlAllowed);
            
                this.view.setReadOnly(this.note.isModifiable());
            
                this.view.setText(note.getContent());
            
                this.view.setTitle(ModuleI18NService.getLabel(type));
            
            } else {
                this.view.setText("");
                this.view.setReadOnly(true);
                this.view.setMimeMode(MimeType.PLAIN);
                this.view.enableModeSwitcher(false);
                this.view.setTitle("undefined");
            }
        }

        /**
         * Called by the view to change the effective mime type of the note
         * @param type
         */
        @objid ("66d3465d-e9aa-48be-8208-7893c6be7193")
        public void onSwitchNoteMimeMode(MimeType type) {
            // For safety
            if (this.note == null) {
                return;
            }
            
            if (type != getNoteMimeMode(this.note)) {
                switch (type) {
                case HTML:
                    if (getNoteTypeMimeMode(this.note.getModel()) == MimeType.HTML) {
                        setNoteMimeType(this.note, MimeType.HTML);
                    } else {
                        // Change to a forbidden mode. Should not happen.
                    }
                    break;
                case PLAIN:
                default:
                    setNoteMimeType(this.note, MimeType.PLAIN);
                    break;
                }
                // Refresh contents
                setNote(this.note);
            }
        }

        /**
         * Change a Note mime type in the model within a Transaction.
         * @param note
         * @param mimeType
         */
        @objid ("135a4789-fac2-4a15-a4cf-845213edfd40")
        private void setNoteMimeType(Note note, MimeType mimeType) {
            try (ITransaction t = CoreSession.getSession(note).getTransactionSupport()
                    .createTransaction("Change note mime type")) {
                note.setMimeType(mimeType.toEncodingString());
                t.commit();
            } catch (final Exception e) {
                EditionDialogs.LOG.error(e);
            }
        }

        /**
         * Returns the current mime type of a Note
         * @param note
         * @return
         */
        @objid ("e98748ba-5c39-4104-8b5e-ad55afab96c3")
        private MimeType getNoteMimeMode(Note aNote) {
            if (aNote != null) {
                final String mimeType = aNote.getMimeType() != null && !aNote.getMimeType().isEmpty()
                        ? aNote.getMimeType() : aNote.getModel().getMimeType();
                return mimeType.contains("html") ? MimeType.HTML : MimeType.PLAIN;
            }
            return MimeType.PLAIN;
        }

        /**
         * Returns the mime type preference of the NoteType
         * 
         * @param model @return
         */
        @objid ("925f2ddd-916f-41d2-b6b5-ed17cfd82ce5")
        private MimeType getNoteTypeMimeMode(NoteType model) {
            final String mimeType = model != null ? model.getMimeType() : "";
            return mimeType.contains("html") ? MimeType.HTML : MimeType.PLAIN;
        }

        /**
         * Change a Note content in the model within a Transaction. Called by
         * the view.
         * @param value
         */
        @objid ("fe7d1dba-92c3-425f-bc7b-28734233a277")
        private void onSetNoteContent(String value) {
            if (this.note == null) {
                return;
            }
            
            if (!value.equals(this.note.getContent())) {
                try (ITransaction t = CoreSession.getSession(this.note).getTransactionSupport()
                        .createTransaction("Set note content")) {
                    this.note.setContent(value);
                    t.commit();
                } catch (final Exception e) {
                    EditionDialogs.LOG.error(e);
                }
            }
        }

    }

    @objid ("fb2a1b7a-1cd6-4a20-b0a6-5111e61975ae")
    private static class NoteEditView {
        /**
         * The current mime mode
         */
        @objid ("0d4f39f5-201c-48ac-9653-7a0312fab94d")
        private MimeType mimeMode;

        /**
         * The view controller
         */
        @objid ("8dd0b7a5-0875-45ec-842e-4c5d42321e42")
        private final NoteEditController controller;

        /**
         * The HTML editor
         */
        @objid ("5e4a0ea7-948f-4461-b93e-7162d79a6ad0")
        private final HtmlComposer htmlText;

        /**
         * Top level container
         */
        @objid ("01886c7e-1315-45df-8f6b-b28f1fd2afdb")
        private final Composite container;

        /**
         * The PLAIN text editor
         */
        @objid ("4389b7cd-9d34-43f4-8524-b545d80c8f6a")
        private final Text text;

        @objid ("debbb3eb-8b48-488d-8465-e020dfbcd54d")
        private final StackLayout stackLayout;

        /**
         * The Stack used to switch the active editor based on mime mode
         */
        @objid ("2e9b2fd0-6b36-4ac7-a8ba-374df6fa8194")
        private final Composite stack;

        @objid ("786d8313-4d0c-4edc-9c1f-bbb23eac7a1a")
        private final Label nameLabel;

        /**
         * The check button that allows to change the mime type of the edited
         * note
         */
        @objid ("73459c60-51c2-4e1e-b591-d190d195ad63")
        private final Button htmlCheckBox;

        /**
         * Widget structure: container=[label, mime mode selector, stack=[plain
         * text,html text]]
         */
        @objid ("b35571d4-c31b-4edc-8526-dfd94deb1781")
        public NoteEditView(Composite parent, NoteEditController controller) {
            this.controller = controller;
            
            // The top level container
            this.container = new Composite(parent, SWT.BORDER);
            final GridLayout gl = new GridLayout(2, true);
            gl.horizontalSpacing = 0;
            gl.marginWidth = 0;
            gl.verticalSpacing = 0;
            gl.marginHeight = 0;
            this.container.setLayout(gl);
            
            // The label
            this.nameLabel = new Label(this.container, SWT.NO_REDRAW_RESIZE);
            
            GridData gd = new GridData(SWT.LEFT, SWT.CENTER, false, false);
            this.nameLabel.setLayoutData(gd);
            
            // The mime type selector
            this.htmlCheckBox = new Button(this.container, SWT.CHECK);
            this.htmlCheckBox.setText("HTML");
            this.htmlCheckBox.setVisible(false);
            this.htmlCheckBox.addSelectionListener(new SelectionListener() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    if (NoteEditView.this.htmlCheckBox.getSelection()) {
                        NoteEditView.this.controller.onSwitchNoteMimeMode(MimeType.HTML);
                    } else {
                        NoteEditView.this.controller.onSwitchNoteMimeMode(MimeType.PLAIN);
                    }
                }
            
                @Override
                public void widgetDefaultSelected(SelectionEvent e) {
                    // nothing to do
                }
            });
            
            // The stack composite
            this.stack = new Composite(this.container, SWT.NONE);
            this.stackLayout = new StackLayout();
            this.stack.setLayout(this.stackLayout);
            gd = new GridData(SWT.FILL, SWT.FILL, true, true);
            gd.horizontalSpan = 2;
            
            this.stack.setLayoutData(gd);
            
            // The Plain text editor
            this.text = new Text(this.stack, SWT.MULTI | SWT.WRAP);
            this.text.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (NoteEditView.this.mimeMode == MimeType.PLAIN) {
                        NoteEditView.this.controller.onSetNoteContent(((Text) e.getSource()).getText());
                    }
                }
            
            });
            
            // The HTML text editor
            this.htmlText = new HtmlComposer(this.stack, SWT.NONE);
            this.htmlText.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (NoteEditView.this.mimeMode == MimeType.HTML) {
                        NoteEditView.this.controller.onSetNoteContent(NoteEditView.this.htmlText.getHtml());
                    }
                }
            
            });
        }

        /**
         * Show/hide the HTML mime type checkbox
         * @param onOff
         */
        @objid ("eeb1cb25-4142-452e-a51f-facb1f0344e0")
        public void enableModeSwitcher(boolean onOff) {
            this.htmlCheckBox.setVisible(onOff);
        }

        /**
         * Set the panel title string.
         * @param s
         */
        @objid ("6c7b4317-7bb6-483f-9ffd-5a2b87eb5ecf")
        public void setTitle(String s) {
            this.nameLabel.setText(s);
        }

        /**
         * Set the panel note content string.
         * @param s
         */
        @objid ("9cda9bc0-1db2-41dd-beb8-b59f125c6046")
        public void setText(String s) {
            switch (this.mimeMode) {
            case HTML:
                this.htmlText.setHtml(s);
                break;
            case PLAIN:
            default:
                this.text.setText(s);
                break;
            }
        }

        /**
         * Return the top level container of the view.
         * @return
         */
        @objid ("12aeac0c-1537-41a5-8b93-f7873cc51561")
        public Object getContainer() {
            return this.container;
        }

        /**
         * Dispose the view resources
         */
        @objid ("563ac972-67f9-40cd-8ce4-2c550d26ad03")
        public void dispose() {
            // Make sure HTML note is saved before dispose
            if (NoteEditView.this.mimeMode == MimeType.HTML) {
                NoteEditView.this.controller.onSetNoteContent(NoteEditView.this.htmlText.getHtml());
            }
            this.htmlText.dispose();
        }

        /**
         * Change the view displayed mime type
         * @param mode
         */
        @objid ("30aed129-1719-403a-9fb4-e463ff42d8b1")
        public void setMimeMode(MimeType mode) {
            if (this.mimeMode != mode) {
                this.mimeMode = mode;
                switch (mode) {
                case HTML:
                    this.htmlCheckBox.setSelection(true);
                    this.stackLayout.topControl = this.htmlText.getBrowser();
                    this.stack.layout(true);
                    break;
                case PLAIN:
                default:
                    this.htmlCheckBox.setSelection(false);
                    this.stackLayout.topControl = this.text;
                    this.stack.layout(true);
                    break;
            
                }
            }
        }

        /**
         * Change the read only mode for the view (text editors)
         * @param modifiable
         */
        @objid ("0e9b798d-1c6e-4767-8b07-bdf27f0651bc")
        public void setReadOnly(boolean modifiable) {
            this.text.setEditable(modifiable);
            this.text.setBackground(modifiable ? UIColor.TEXT_WRITABLE_BG : UIColor.TEXT_READONLY_BG);
            this.htmlText.setEditable(modifiable);
            // this.htmlText.setBackground(modifiable ? UIColor.TEXT_WRITABLE_BG
            // : UIColor.TEXT_READONLY_BG);
        }

    }

}
