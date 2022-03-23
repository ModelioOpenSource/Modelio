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
package org.modelio.edition.dialogs.dialog.panels.element;

import java.security.InvalidParameterException;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.modelio.edition.dialogs.plugin.EditionDialogs;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.platform.model.ui.MimeServices.MimeType;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.htmleditor.HtmlComposer;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.api.model.IMObjectFilter;
import org.modelio.vcore.session.api.model.IModel;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;

/**
 * The NoteEditPanel class is an implementation of a generic IPanelProvider designed to edit a Modelio 'Note' attached to a
 * 'ModelElement'.<br/>
 * It is generic as the type of note to be edited by the panel can be configured in the constructor by passing a module name and
 * note type name which are resolved when possible into a 'NoteType'.<br/>
 * The input element for this panel must be a ModelElement whose notes are to be edited.<br/>
 * The NoteEditPanel class allows switching the mime type of the note between PLAIN and HTML at the mandatory condition that the
 * NoteType has a HTML mime type. In any other cases, PLAIN is forced.<br/>
 */
@objid ("5a047f78-b02e-4449-ac6b-ee36247bde6c")
public class TypedNotePanel implements IPanelProvider {
    @objid ("6ffa5997-0117-45f3-af86-612d4f004475")
    private NoteEditView view;

    @objid ("488e8377-27c1-4a4e-bb72-c4cbb45c3448")
    private NoteEditController controller;

    /**
     * C'tor.
     * @param moduleName name of the module providing the note type. Might be <code>null</code>.
     * @param noteTypeName a note type name. Might be <code>null</code>.
     */
    @objid ("1c3c0099-444d-4efb-a61a-bc67eaab6dc6")
    public  TypedNotePanel(String moduleName, String noteTypeName) {
        this.controller = new NoteEditController(moduleName, noteTypeName);
    }

    @objid ("e4da3741-b370-4d13-9c8e-fb5573812d5f")
    @Override
    public Object createPanel(Composite parent) {
        this.view = new NoteEditView(parent, this.controller);
        this.controller.setView(this.view);
        return this.view.getContainer();
    }

    @objid ("ef93d72e-aed2-4df3-ba2b-3f61af15e7ad")
    @Override
    public void dispose() {
        this.view.dispose();
        this.view = null;
        this.controller = null;
        
    }

    @objid ("55cc0187-a18f-4eae-a39e-bbe89417ea60")
    @Override
    public String getHelpTopic() {
        // no specific help topic
        return null;
    }

    @objid ("6b85ed3d-1bf6-4eaf-b848-cec720f36e4b")
    @Override
    public Object getInput() {
        return this.controller.getModelElement();
    }

    @objid ("00672583-0eea-4d91-a5e0-ce8e41cc6273")
    @Override
    public Object getPanel() {
        return this.view != null ? this.view.getContainer() : null;
    }

    @objid ("b83f2bd1-d73c-4fb4-ab81-2dfc67ba6f42")
    @Override
    public boolean isRelevantFor(Object obj) {
        return obj instanceof ModelElement;
    }

    /**
     * setInput should receive the annoted ModelElement
     */
    @objid ("3dd52475-a4a9-4cdf-8f71-5e87bc14cba2")
    @Override
    public void setInput(Object input) {
        // Bad input, clear all
        if (input == null || !(input instanceof ModelElement)) {
            this.controller.setModelElement(null);
            return;
        }
        if (!((ModelElement) input).isValid()) {
            this.controller.setModelElement(null);
            return;
        }
        
        // Input is a valid Note
        this.controller.setModelElement((ModelElement) input);
        
    }

    @objid ("61b8e22b-cd0d-467e-8e46-b9e7afbb9b05")
    @SuppressWarnings("synthetic-access")
    private static class NoteEditView {
        @objid ("50f18740-67f7-4e8c-b86c-ffc01590bff6")
        private MimeType mimeMode;

        @objid ("e00d9446-2971-4f6f-a04d-b54eac58eb2d")
        private final HtmlComposer htmlText;

        @objid ("77c46e73-124d-4de3-b6b6-cfd5e821786a")
        private final NoteEditController controller;

        @objid ("22cc38c5-8cdd-4820-8764-937589312aab")
        private final Composite container;

        @objid ("c47fe658-5ae3-4bf9-bbee-bb4a16fdc642")
        private final Composite stack;

        @objid ("cfb98c55-54b2-4c41-83c0-0fca3165dd8c")
        private final Button htmlCheckBox;

        @objid ("e5a006eb-980b-42d7-b9a7-acb29879cd4a")
        private final StackLayout stackLayout;

        @objid ("8c6636ef-125d-46db-a630-c0c0356198a7")
        private final Label nameLabel;

        @objid ("ee01b89c-f767-47bb-b904-93872cb6060e")
        private final Text text;

        /**
         * Widget structure: container=[label, mime mode selector, stack=[plain text,html text]]
         * @param parent the parent SWT composite
         * @param controller the view controller
         */
        @objid ("1b906def-0ba0-4ff2-8bb9-92e092caf353")
        public  NoteEditView(Composite parent, NoteEditController controller) {
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
            this.htmlCheckBox.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    if (NoteEditView.this.htmlCheckBox.getSelection()) {
                        NoteEditView.this.controller.onSetNoteMimeType(MimeType.HTML);
                    } else {
                        NoteEditView.this.controller.onSetNoteMimeType(MimeType.PLAIN);
                    }
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
                    if (NoteEditView.this.mimeMode != MimeType.HTML) {
                        NoteEditView.this.controller.onSetNoteContent(((Text) e.getSource()).getText());
                    }
                }
            
            });
            this.text.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    final Text atext = (Text) e.getSource();
                    if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == 'a') {
                        // CTRL A
                        atext.selectAll();
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

        @objid ("aa6060ac-be66-4185-819a-1ab94b7219f7")
        public void enableModeSwitcher(boolean onOff) {
            this.htmlCheckBox.setVisible(onOff);
        }

        @objid ("1bd918de-ce5f-40c7-9d3d-25cce028c7c7")
        public void setTitle(String s) {
            this.nameLabel.setText(s);
        }

        @objid ("471ee376-6fcb-41ee-8b65-075f3dab4f56")
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

        @objid ("15306d32-ddd9-4f7a-aad0-fd7bc29dfe2b")
        public Object getContainer() {
            return this.container;
        }

        @objid ("3a395e6a-3d71-4c04-b53b-ce0e4c9bc7b4")
        public void dispose() {
            this.htmlText.dispose();
        }

        @objid ("75550cfd-cc94-4532-8520-f8c95aff1d20")
        public void setMimeType(MimeType mode) {
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

        @objid ("196b4503-48a6-49b9-abf6-9522db903012")
        public void setReadOnly(boolean modifiable) {
            this.text.setEditable(modifiable);
            this.text.setBackground(modifiable ? UIColor.TEXT_WRITABLE_BG : UIColor.TEXT_READONLY_BG);
            this.htmlText.setEditable(modifiable);
            // this.htmlText.setBackground(modifiable ? UIColor.TEXT_WRITABLE_BG : UIColor.TEXT_READONLY_BG);
            
        }

    }

    /**
     * @author phv
     */
    @objid ("57c763ad-39a0-418c-b8d3-e363b4092a74")
    private static class NoteEditController {
        /**
         * The module name of the configured NoteType
         */
        @objid ("636cebe2-73ac-4e5f-919e-b925925bb921")
        private final String moduleName;

        /**
         * The note type name of the configured NoteType
         */
        @objid ("832a6a46-8875-4384-9571-bf6aa811cd31")
        private final String noteTypeName;

        /**
         * The view driven by this controller
         */
        @objid ("3bd09e60-3335-482e-92dd-7fa055eeb843")
        private NoteEditView view;

        /**
         * The ModelElement under edition
         */
        @objid ("1be4a44d-2bc7-41c3-988d-b5bc7fc88330")
        private ModelElement me;

        @objid ("283c2669-f3c2-433b-9442-849df9bdc00a")
        private NoteEditModel noteModel;

        @objid ("332db36d-aeb6-4493-9e9c-9599b0f322eb")
        public  NoteEditController(String moduleName, String noteTypeName) {
            this.moduleName = moduleName;
            this.noteTypeName = noteTypeName;
            
        }

        /**
         * Get the ModelElement whose note is being edited
         * @return the edited element
         */
        @objid ("7de0a6f7-d232-4874-b36b-91bfd40feee8")
        public ModelElement getModelElement() {
            return this.me;
        }

        /**
         * Set the view controlled by this controller
         * @param view the view
         */
        @objid ("69a29631-e94b-44af-9e46-8484834bc204")
        public void setView(NoteEditView view) {
            this.view = view;
        }

        /**
         * Set the ModelElemen whose note is being edited.
         * <p>
         * This method also refreshes the view contents.
         * @param aModelElement the edited element
         */
        @objid ("e4df6d61-dccb-4c12-87bc-026ba7306348")
        @SuppressWarnings("synthetic-access")
        public void setModelElement(ModelElement aModelElement) {
            this.me = aModelElement;
            
            if (this.me == null || !this.me.isValid()) {
                // Do not want to fail here because of an unexpected null or invalid ModelElement however nothing can be done with
                // the panel!
                this.noteModel = null;
                this.view.setTitle("undefined");
                this.view.setReadOnly(true);
                this.view.setMimeType(MimeType.PLAIN);
                this.view.setText("");
                this.view.enableModeSwitcher(false);
                return;
            } else {
                // Compute the effective NoteInstance for the given element
                this.noteModel = new NoteEditModel(this.me, this.moduleName, this.noteTypeName);
            
                if (this.noteModel.hasType() == false) {
                    // No note model, won't even be able to create a new note
                    this.view.setTitle("undefined");
                    this.view.setReadOnly(true);
                    this.view.setMimeType(MimeType.PLAIN);
                    this.view.setText("");
                    this.view.enableModeSwitcher(false);
            
                } else if (this.noteModel.noteExists() == false) {
                    // Got a note model but no existing note, creating a note must be possible
                    this.view.setTitle(ModuleI18NService.getLabel(this.noteModel.noteType));
                    this.view.setReadOnly(this.me.isModifiable());
                    this.view.setMimeType(MimeType.PLAIN);
                    this.view.setText(EditionDialogs.I18N.getString("TypedNotePanel.EnterText"));
            
                    this.view.enableModeSwitcher(this.noteModel.getModelMimeType() == MimeType.HTML);
            
                } else {
                    // Got a note model and an existing note
                    this.view.setTitle(ModuleI18NService.getLabel(this.noteModel.noteType));
                    this.view.setReadOnly(this.me.isModifiable());
                    this.view.setMimeType(this.noteModel.getNoteMimeType());
                    this.view.setText(this.noteModel.note.getContent());
                    this.view.enableModeSwitcher(this.noteModel.getModelMimeType() == MimeType.HTML);
                }
            }
            
        }

        /**
         * Called by the GUI to change the effective mime type of the note
         * @param mode the edition mode to use for the note. Can be either {@link MimeType#HTML} or {@link MimeType#PLAIN}.
         */
        @objid ("24db9c6d-ab16-41f9-b3dd-8b62755895a2")
        public void onSetNoteMimeType(MimeType mode) {
            if (this.noteModel == null) {
                // Nothing useful can be done
                return;
            }
            
            // The user might switch the mode prior to the note creation
            if (this.noteModel.hasType() && !this.noteModel.noteExists()) {
                // got a note type but no note, consider a create note
                this.noteModel.createNote("");
            }
            
            if (mode != this.noteModel.getNoteMimeType()) {
                switch (mode) {
                case HTML:
                    if (this.noteModel.getModelMimeType() == MimeType.HTML) {
                        this.noteModel.setNoteMimeType(MimeType.HTML);
                    } else {
                        // Change to a forbidden mode. Should not happen.
                    }
                    break;
                case PLAIN:
                default:
                    this.noteModel.setNoteMimeType(MimeType.PLAIN);
                    break;
                }
            
                // Refresh the view
                setModelElement(this.me);
            }
            
        }

        @objid ("21a734c5-51d3-45b4-ba5c-361fbd507bd3")
        public void onSetNoteContent(String value) {
            if (this.noteModel != null) {
                if (this.noteModel.noteExists()) {
                    this.noteModel.setNoteContent(value);
                } else {
                    this.noteModel.createNote(value);
                }
            }
            
        }

    }

    @objid ("e7f8e945-763e-4418-b799-0ba795cc14bd")
    private static class NoteEditModel {
        @objid ("0b5162f8-f03f-44c9-850b-8056c0dd17b3")
        private final ModelElement owner;

        @objid ("5feac9ad-732f-42a1-8650-a15ec5655291")
        private NoteType noteType;

        @objid ("7f6d1323-77b5-486a-be5e-1b1c55affaaa")
        private Note note;

        /**
         * C'tor
         * @param owner MUST not be null
         * @param moduleName name of the module providing the note type. Might be <code>null</code>.
         * @param noteTypeName a note type name. Might be <code>null</code>.
         */
        @objid ("5b2931bd-1ed4-4e19-8731-c6911f493113")
        public  NoteEditModel(ModelElement owner, String moduleName, String noteTypeName) {
            if (owner == null) {
                throw new InvalidParameterException();
            }
            
            this.owner = owner;
            init(moduleName, noteTypeName);
            
        }

        @objid ("32ac76fe-4d1d-4b3c-8d57-a1e41b0e529a")
        public boolean hasType() {
            return this.noteType != null;
        }

        @objid ("2e0f4e4c-39ff-4152-82ac-b218b415ddfb")
        public boolean noteExists() {
            return this.note != null;
        }

        @objid ("d771761c-7e98-4405-afd9-c4991ed3aec6")
        public void createNote(String value) {
            if (hasType()) {
                ICoreSession session = CoreSession.getSession(this.owner);
                try (ITransaction t = session.getTransactionSupport().createTransaction("Create note")) {
                    IInfrastructureModelFactory factory = MTools.get(session).getModelFactory(IInfrastructureModelFactory.class);
                    factory.createNote(this.noteType, this.owner, value);
                    t.commit();
                } catch (final Exception e) {
                    EditionDialogs.LOG.error(e);
                }
            }
            
        }

        @objid ("be2925b7-b34c-4ad1-843a-961e13051c98")
        public MimeType getModelMimeType() {
            final String mimeType = this.noteType != null ? this.noteType.getMimeType() : "";
            return mimeType.contains("html") ? MimeType.HTML : MimeType.PLAIN;
        }

        @objid ("b608db5b-07e3-44bd-bb50-6b793df9db14")
        public MimeType getNoteMimeType() {
            if (this.note != null) {
                final String mimeType = this.note.getMimeType() != null && !this.note.getMimeType().isEmpty() ? this.note
                        .getMimeType() : this.note.getModel().getMimeType();
                return mimeType.contains("html") ? MimeType.HTML : MimeType.PLAIN;
            }
            return MimeType.PLAIN;
        }

        @objid ("732c2dde-33b8-41ee-80ae-7df05b605ae6")
        public void setNoteMimeType(MimeType mimeType) {
            try (ITransaction t = CoreSession.getSession(this.owner).getTransactionSupport()
                    .createTransaction("Change note mime type")) {
                this.note.setMimeType(mimeType.toEncodingString());
                t.commit();
            } catch (final Exception e) {
                EditionDialogs.LOG.error(e);
            }
            
        }

        /**
         * Change a Note content in the model within a Transaction. Called by the view.
         * @param value the new content for the edited note.
         */
        @objid ("877f171b-ad60-4e2e-8cbd-80d21efba5cc")
        public void setNoteContent(String value) {
            // If noteType is undefined, nothing can be done.
            if (this.noteType == null) {
                return;
            }
            
            if (noteExists() == false && !value.isEmpty()) {
                createNote(value);
            } else {
                if (!value.equals(this.note.getContent())) {
                    try (ITransaction t = CoreSession.getSession(this.owner).getTransactionSupport()
                            .createTransaction("Set note content")) {
                        if (value.isEmpty()) {
                            // Treat empty content as 'remove' note
                            this.note.delete();
                            this.note = null;
                        } else {
                            this.note.setContent(value);
                        }
                        t.commit();
                    } catch (final Exception e) {
                        EditionDialogs.LOG.error(e);
                    }
                }
            }
            
        }

        @objid ("a87c9da7-462f-4089-85f5-54274050fff0")
        private void init(String moduleName, String noteTypeName) {
            this.noteType = resolveNoteModel(this.owner, moduleName, noteTypeName);
            if (this.noteType != null) {
                this.note = getNote(this.owner, this.noteType);
            }
            
        }

        /**
         * Find the note of given type on modelElement.
         * @param modelElement
         * @param type a note type.
         * @return null if no note could be found
         */
        @objid ("4e22fc84-73bc-498c-bb32-d27ee25ad9bb")
        private Note getNote(ModelElement modelElement, NoteType type) {
            // If noteType is undefined no note can be returned
            if (type != null) {
                for (final Note n : modelElement.getDescriptor()) {
                    final NoteType m = n.getModel();
                    if (m != null && m.equals(type)) {
                        return n;
                    }
                }
            }
            return null;
        }

        /**
         * Find the NoteType matching the current element metaclass, the module provider and the note type name
         * @param me
         * @param moduleName
         * @param noteTypeName
         * @return null if no matching NoteType could be found
         */
        @objid ("53596f0d-cf36-4586-abb6-df75e8156ba7")
        private NoteType resolveNoteModel(final ModelElement me, final String moduleName, final String noteTypeName) {
            final CoreSession session = CoreSession.getSession(me);
            final SmMetamodel mm = session.getMetamodel();
            final IModel m = session.getModel();
            final Collection<NoteType> candidates = m.findByAtt(NoteType.class, "Name", noteTypeName,
                    (IMObjectFilter) o -> ((NoteType) o).getModule() != null && moduleName.equals(((NoteType) o).getModule().getName()));
            
            // First loop: check strict metaclass equality
            for (final NoteType nType : candidates) {
                if (nType.getOwnerReference() != null) {
                    final MClass referenceClass = mm.getMClass(nType.getOwnerReference().getReferencedClassName());
                    if (me.getMClass() == referenceClass) {
                        return nType;
                    }
                } else if (nType.getOwnerStereotype() != null) {
                    final MClass steClass = mm.getMClass(nType.getOwnerStereotype().getBaseClassName());
                    if (me.getMClass() == steClass) {
                        return nType;
                    }
                } else {
                    continue;
                }
            }
            
            // Second loop: if first one did not give any result, check metaclass compatibility
            for (final NoteType nType : candidates) {
                if (nType.getOwnerReference() != null) {
                    final MClass referenceClass = mm.getMClass(nType.getOwnerReference().getReferencedClassName());
                    if (me.getMClass().hasBase(referenceClass)) {
                        return nType;
                    }
                } else if (nType.getOwnerStereotype() != null) {
                    final MClass steClass = mm.getMClass(nType.getOwnerStereotype().getBaseClassName());
                    if (me.getMClass().hasBase(steClass)) {
                        return nType;
                    }
                } else {
                    continue;
                }
            }
            return candidates.isEmpty() ? null : candidates.iterator().next();
        }

    }

}
