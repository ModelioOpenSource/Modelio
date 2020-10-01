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

package org.modelio.edition.notes.panelprovider.data;

import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.edition.notes.panelprovider.data.constraints.ConstraintContentComposite;
import org.modelio.edition.notes.panelprovider.data.documents.DocumentContentComposite;
import org.modelio.edition.notes.panelprovider.data.htmlnotes.HtmlNoteContentComposite;
import org.modelio.edition.notes.panelprovider.data.notes.NoteContentComposite;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.platform.core.activation.IActivationService;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vcore.session.api.ICoreSession;

/**
 * This class provide the panel that display the note content, constraint
 * content or document.
 */
@objid ("4c5f1638-62d4-4ce4-b424-1e9ece38c5e0")
public class NoteViewContentPanel implements IPanelProvider {
    @objid ("114224e6-7978-4896-816e-1c4112f54fe8")
    private INoteContent currentContent = null;

    @objid ("cd8ddccd-1a3e-470b-9229-6f437ab1b659")
    private ModelElement element = null;

    @objid ("da0cd2c0-9be1-4405-8a8e-7bcc0b36f3b9")
    private final ICoreSession modelingSession = null;

    @objid ("2cb38a53-7306-4c18-8024-89187d18a679")
    private StackLayout stackLayout = null;

    @objid ("2de5fef0-95fe-4d03-abb0-74d9f04c6830")
    private Composite contentArea = null;

    @objid ("f2fa06f0-770e-481e-9968-4bc672246e6a")
    private static Pattern REGEX_IS_HTML = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);

    @objid ("d075ba50-d71a-484a-8c89-3028a7dede89")
    private final IActivationService activationService;

    @objid ("9a39f62e-adb4-491f-88c8-b9a650670b7a")
    private EContextService contextService;

    @objid ("22ab8685-ac4b-4e06-81f9-30670eee7317")
    private NoteContentComposite noteContentComposite = null;

    @objid ("e88126ad-f064-47d8-8639-4fd2a7625e51")
    private ConstraintContentComposite constraintContentComposite = null;

    @objid ("190f0b35-0e9e-4434-bd21-3a3b13697dda")
    private HtmlNoteContentComposite htmlNoteContentComposite = null;

    @objid ("32d63a01-2046-4f2b-994f-0a8568ac3ef9")
    private DocumentContentComposite documentContentComposite;

    @objid ("05eee4bd-c151-406a-8728-6cdcc4a178c4")
    public NoteViewContentPanel(IActivationService activationService, EContextService contextService) {
        this.activationService = activationService;
        this.contextService = contextService;
    }

    @objid ("cd190d68-a0d3-465d-ac76-3cf301d7c491")
    @Override
    public void setInput(Object input) {
        if (input == null) {
            this.currentContent = null;
            this.stackLayout.topControl = null;
            this.contentArea.layout();
            return;
        }
        
        if (!(input instanceof ModelElement)) {
            return;
        }
        
        final ModelElement elt = (ModelElement) input;
        
        if (elt.isDeleted()) {
            this.currentContent = null;
            this.contentArea.layout();
            return;
        }
        
        if (elt instanceof Note) {
            this.element = elt;
            if (isHtmlNote((Note) elt)) {
                this.currentContent = this.htmlNoteContentComposite;
            } else {
                this.currentContent = this.noteContentComposite;
            }
        } else if (elt instanceof Constraint) {
            this.element = elt;
            this.currentContent = this.constraintContentComposite;
        } else if (elt instanceof Document) {
            this.element = elt;
            this.currentContent = this.documentContentComposite;
        }
        
        if (this.currentContent != null) {
            this.stackLayout.topControl = this.currentContent.getControl();
            this.currentContent.getControl().redraw();
            this.contentArea.layout(true);
        
            if (!this.element.isDeleted()) {
                this.currentContent.setInput(this.element);
            }
        }
    }

    /**
     * Clean up existing note content
     */
    @objid ("e0ed1a96-ea17-4e83-840e-f14f0616e3cf")
    public void cleanContent() {
        if (this.currentContent != null) {
            this.currentContent.setInput(null);
            this.currentContent = null;
            this.stackLayout.topControl = null;
        }
    }

    @objid ("d14c9fd8-b792-4fe5-a913-81632e59ee98")
    protected boolean isHtmlNote(Note note) {
        final String mimeType = note.getMimeType() != null && !note.getMimeType().isEmpty() ? note.getMimeType() : note.getModel().getMimeType();
        return mimeType.contains("html");
    }

    @objid ("4c95da10-12da-48f2-9e4d-0f9e9a5c1736")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("afe19b39-f51d-4823-ad8d-2901c119f282")
    @Override
    public Object createPanel(Composite parent) {
        this.contentArea = new Composite(parent, SWT.NONE);
        this.stackLayout = new StackLayout();
        this.contentArea.setLayout(this.stackLayout);
        this.noteContentComposite = new NoteContentComposite(this.contentArea, SWT.NONE, this.contextService);
        this.htmlNoteContentComposite = new HtmlNoteContentComposite(this.contentArea, SWT.NONE, this.contextService);
        this.constraintContentComposite = new ConstraintContentComposite(this.contentArea, SWT.NONE, this.contextService);
        this.documentContentComposite = new DocumentContentComposite(this.contentArea, SWT.NONE,
                this.activationService);
        return this.contentArea;
    }

    @objid ("a1817f34-1537-454b-b171-3161b05fdce0")
    @Override
    public Object getPanel() {
        return this.contentArea;
    }

    @objid ("d1a324ea-151f-4809-9442-f2dea26d5b5e")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("0f815972-bb6b-4747-84a1-63e7eb11f5ba")
    @Override
    public Object getInput() {
        if (this.currentContent != null) {
            return this.currentContent.getNoteElement();
        } else {
            return null;
        }
    }

    @objid ("40e14112-6e79-43fc-bc9c-380a6c48ce51")
    @Override
    public void dispose() {
        this.constraintContentComposite.dispose();
        this.noteContentComposite.dispose();
    }

}
