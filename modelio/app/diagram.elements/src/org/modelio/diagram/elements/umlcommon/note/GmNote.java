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

package org.modelio.diagram.elements.umlcommon.note;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.NoteType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphical model for a {@link Note}.
 * 
 * @author phv
 */
@objid ("81819a59-1dec-11e2-8cad-001ec947c8cc")
public class GmNote extends GmSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("81819a5e-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("81865f0f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("81819a5b-1dec-11e2-8cad-001ec947c8cc")
    private Note note;

    @objid ("81819a5c-1dec-11e2-8cad-001ec947c8cc")
     static final GmNoteStyleKeys KEYS = new GmNoteStyleKeys();

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("81865f11-1dec-11e2-8cad-001ec947c8cc")
    public GmNote() {
    }

    /**
     * Creates a GmNote.
     * 
     * @param diagram The diagram owning the node
     * @param note The represented note element
     * @param ref The represented note reference
     */
    @objid ("81865f14-1dec-11e2-8cad-001ec947c8cc")
    public GmNote(IGmDiagram diagram, Note note, MRef ref) {
        super(diagram, ref);
        this.note = note;
    }

    /**
     * @return the note content.
     */
    @objid ("81865f1a-1dec-11e2-8cad-001ec947c8cc")
    public String getContents() {
        if (this.note != null) {
            return this.note.getContent();
        } else {
            return "?";
        }
    }

    @objid ("81865f1e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        if (this.note == null) {
            return null;
        }
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return GmNote.this.getRepresentedElement().getContent();
                    }
        
                    @Override
                    public void setText(String text) {
                        GmNote.this.getRepresentedElement().setContent(text);
                    }
                };
    }

    @objid ("81865f23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Note getRepresentedElement() {
        return this.note;
    }

    @objid ("81865f28-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("81865f2d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmNote.KEYS.getStyleKey(metakey);
    }

    @objid ("8188c169-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmNote.KEYS.getStyleKeys();
    }

    /**
     * Get the note type label.
     * 
     * @return the note type label.
     */
    @objid ("8188c170-1dec-11e2-8cad-001ec947c8cc")
    public String getType() {
        if (this.note == null) {
            return "?";
        }
        
        String name = this.note.getName();
        
        StringBuilder result = new StringBuilder();
        
        // name
        if (!name.isEmpty() && !name.equals(Note.MNAME)) {
            result.append("'");
            result.append(name);
            result.append("' ");
        }
        
        // use type
        NoteType model = this.note.getModel();
        if (model == null) {
            result.append("<none>");
        } else {
            result.append(ModuleI18NService.getLabel(model));
        }
        
        // Mime type
        String mime = checkNoteMimeType("html") ? " [html]" : checkNoteMimeType("jython") ? " [py]" : "";
        if (!mime.isEmpty()) {
            result.append(mime);
        }
        return result.toString();
    }

    @objid ("8188c175-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNote.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("8188c179-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        if (this.note != null) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, this.note.getContent());
        
            refreshNoteLink();
        }
    }

    @objid ("8188c17c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("8188c181-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeEndingLink(final IGmLink gmLink) {
        boolean selfDelete = false;
        if (getRelatedElement() != null && getRelatedElement().equals(gmLink.getRelatedElement())) {
            // the removed link represents the same element (the note) as this gm: delete self as well.
            selfDelete = true;
        }
        super.removeEndingLink(gmLink);
        if (selfDelete) {
            // the removed link represents the same element (the note) as this gm: delete self as well.
            delete();
        }
    }

    @objid ("8188c186-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNote.", GmNote.MINOR_VERSION);
    }

    @objid ("8188c18a-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.note = (Note) resolveRef(getRepresentedRef());
    }

    @objid ("8188c18d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmNote.MAJOR_VERSION;
    }

    /**
     * @return true if the note text is HTML
     */
    @objid ("ec352994-5589-42d7-88b2-b4e4b973c4e3")
    public boolean isHtml() {
        if (this.note == null) {
            return false;
        }
        
        String mimeType = this.note.getMimeType();
        if (mimeType == null || mimeType.isEmpty()) {
            NoteType model = this.note.getModel();
            mimeType = model != null ? model.getMimeType() : "";
        }
        return mimeType.contains("html");
    }

    /**
     * Refresh the link between this Note and its parent.
     * <p>
     * Invalid links are deleted, and missing links are unmasked.
     * </p>
     */
    @objid ("df4c0ab4-31bb-46bc-bf61-4d65464041dc")
    private void refreshNoteLink() {
        Note relatedElement = getRepresentedElement();
        if (relatedElement == null || !relatedElement.isValid() || getDiagram() == null) {
            return;
        }
        
        ModelElement subject = relatedElement.getSubject();
        if (subject instanceof AbstractDiagram) {
            // Notes on diagrams do not have a link
            return;
        }
        
        boolean found = false;
        // Start by scanning existing links
        for (IGmLink gmLink : new ArrayList<>(getEndingLinks())) {
            if (gmLink instanceof GmNoteLink && gmLink.getFrom() != null) {
                MObject toElement = gmLink.getFrom().getRelatedElement();
                if (Objects.equals(subject, toElement)) {
                    // link to subject found, refresh is done
                    found = true;
                } else {
                    // destroying invalid link
                    super.removeEndingLink(gmLink);
                    gmLink.delete();
                }
            }
        }
        
        if (found) {
            // valid link found
            return;
        }
        
        // Unmask missing link.
        if (subject != null) {
            Collection<GmModel> models = getDiagram().getAllGMRelatedTo(new MRef(subject));
            for (GmModel model : models) {
                if (model instanceof GmLink || ((GmNodeModel) model).isVisible()) {
                    // fire property change to force creation of missing link by edit part.
                    firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, subject);
                    break;
                }
            }
        }
    }

    /**
     * The note mime type is ckecked for containing the 'mime' string.
     */
    @objid ("6a585607-f6fc-450b-863d-a2325a08cac3")
    private boolean checkNoteMimeType(String mime) {
        String noteMimeType = this.note.getMimeType();
        String mimeType = ((noteMimeType != null) && !noteMimeType.isEmpty()) ? noteMimeType : this.note.getModel().getMimeType();
        return mimeType.contains(mime);
    }

}
