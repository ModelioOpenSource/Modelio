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
package org.modelio.edition.notes.panelprovider.tree;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.editors.richnote.helper.RichNoteLabelProvider;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.IResourceHandle;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.mda.infra.MdaResources;
import org.modelio.platform.model.ui.swt.images.ElementStyler;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider2;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIFont;

/**
 * Label provider of the annotation tree.
 */
@objid ("e203c493-ddb7-41a7-9e01-2b4bf61691e8")
public class NoteViewTreeLabelProvider extends UniversalLabelProvider2 {
    @objid ("2e32ea7e-999c-432a-bfbe-3e46ce8f24e1")
    protected Styler mimeStyler;

    @objid ("b39f9e9b-e80a-4259-b835-b3addb67e380")
    protected Styler missingResourceStyler;

    @objid ("11414203-df81-4e9b-9ee6-8105651daec5")
    @Override
    public Image getImage(Object element) {
        if (element instanceof Document) {
            Document resource = (Document) element;
            return RichNoteLabelProvider.getIcon(resource);
        } else {
            return super.getImage(element);
        }
        
    }

    @objid ("6fd1fd28-ef88-48ff-8b92-77475605a66b")
    private static Stereotype getFirstSelected(List<Stereotype> stereotypes) {
        for (Stereotype stereotype : stereotypes) {
            ModuleComponent module = stereotype.getOwner().getOwnerModule();
            if (module != null) {
                return stereotype;
            }
        }
        return null;
    }

    @objid ("447e3e09-1be0-4cda-b5da-b063fe02a942")
    @Override
    public StyledString getStyledText(Object element) {
        if (element instanceof Note) {
            return getTextForNote((Note) element);
        } else if (element instanceof Document) {
            return getTextForDocument((Document) element);
        } else if (element instanceof Constraint) {
            return getTextForConstraint((Constraint) element);
        }
        return super.getStyledText(element);
    }

    /**
     * Build the label to be displayed for a Constraint.
     * Naming strategy:
     * <ol>
     * <li>if the constraint name is filled, use it for the constraint label</li>
     * <li>if the constraint name is not filled and if there is at least on
     * stereotype on the constraint, use the first stereotype name as a label.</li>
     * <li>if there is no name and no stereotype, set "Constraint" as a label</li>
     * </ol>
     */
    @objid ("832602ae-7728-4e2f-89ec-5198d2d4e4f6")
    private StyledString getTextForConstraint(Constraint constraint) {
        // try constraint name
        final String name = constraint.getName();
        if ((name != null) && !name.isEmpty()) {
            return new StyledString(name, ElementStyler.getStyler(constraint));
        }
        
        // try first stereotype name
        final List<Stereotype> stereotypes = constraint.getExtension();
        if (stereotypes.size() > 0) {
            final Stereotype stereotype = NoteViewTreeLabelProvider.getFirstSelected(stereotypes);
        
            if (stereotype != null) {
                return new StyledString(MdaResources.getLabel(stereotype), ElementStyler.getStyler(constraint));
            }
        }
        
        // use constant value
        return new StyledString(EditionNotes.I18N.getString("Constraint"), ElementStyler.getStyler(constraint));
    }

    /**
     * Build the label to be displayed for a Note.
     * Naming strategy: Let NAME, MIME, TYPE be respectively the name, the mime
     * type and the type of the note.
     * The build label is: "NAME - TYPE [MIMETYPE]"
     */
    @objid ("199c2308-0b7d-4045-8572-2b97195dfe2c")
    private StyledString getTextForNote(Note note) {
        String name = note.getName();
        String type = (note.getModel() != null) ? MdaResources.getLabel(note.getModel()) : EditionNotes.I18N.getString("Note");
        String mime = checkNoteMimeType(note, "html") ? " [html]" : checkNoteMimeType(note, "jython") ? " [py]" : "";
        
        StyledString result = new StyledString();
        
        Styler standardStyler = ElementStyler.getStyler(note);
        
        // name
        if (!name.isEmpty() && !name.equals(Note.MNAME)) {
            result.append("'");
            result.append(name, standardStyler);
            result.append("' ");
        }
        
        // use type
        result.append(type, standardStyler);
        
        // mime type
        if (!mime.isEmpty()) {
            result.append(mime, this.mimeStyler);
        }
        return result;
    }

    /**
     * The note mime type is ckecked for containing the 'mime' string.
     */
    @objid ("b81cd40b-95a2-4994-a456-c09f3b4d1ae4")
    private boolean checkNoteMimeType(Note note, String mime) {
        String noteMimeType = note.getMimeType();
        String mimeType = ((noteMimeType != null) && !noteMimeType.isEmpty()) ? noteMimeType : note.getModel().getMimeType();
        return mimeType.contains(mime);
    }

    @objid ("cd7477b7-1752-462b-95a6-8b260e091f56")
    public  NoteViewTreeLabelProvider(Viewer viewer) {
        this.mimeStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
                textStyle.font = UIFont.NORMALI;
                textStyle.foreground = UIColor.LABEL_TIP_FG;
            }
        };
        
        this.missingResourceStyler = new Styler() {
            @Override
            public void applyStyles(TextStyle textStyle) {
                textStyle.font = UIFont.NORMALI;
                textStyle.foreground = UIColor.SHELL_ELEMENT_FG;
            }
        };
        
    }

    /**
     * Build the label to be displayed for a Document.
     * Naming strategy:
     * <ol>
     * <li>if the document name is not empty, use 'NAME'</li>
     * <li>if the document name is empty, use the value 'Document'</li>
     * <lI>if the document type exists, add [TYPE] after the name</li>
     * </ol>
     */
    @objid ("486a9aef-898c-4013-8749-b2c7d607ce4e")
    private StyledString getTextForDocument(Document document) {
        StyledString result = new StyledString();
        Styler standardStyler = ElementStyler.getStyler(document);
        
        String name = document.getName();
        if (!name.isEmpty()) {
            result.append(name, standardStyler);
        } else {
            result.append(EditionNotes.I18N.getString("NotesPanel.Document"), standardStyler);
        }
        
        final ResourceType resourceType = document.getType();
        if (resourceType != null) {
            final String type = !MdaResources.getLabel(resourceType).isEmpty() ? MdaResources.getLabel(resourceType) : resourceType.getName();
        
            Styler typeStyler = ElementStyler.getStyler(resourceType);
            result.append(" [", typeStyler);
            result.append(type, typeStyler);
            result.append("]", typeStyler);
        }
        
        IResourceHandle handle = document.getHandle();
        if (handle != null && !document.isEmbedded()) {
            try {
                // Ignore http
                URI storageInfo = handle.getLocation();
                if (!"http".equals(storageInfo.getScheme()) && !"https".equals(storageInfo.getScheme())) {
                    Path path = Paths.get(storageInfo);
                    if (Files.notExists(path)) {
                        result.append(" ");
                        result.append(EditionNotes.I18N.getString("NotesPanel.Document.NotFound"), this.missingResourceStyler);
                    }
                }
            } catch (@SuppressWarnings("unused") Exception e) {
                // Invalid path...
                result.append(EditionNotes.I18N.getString("NotesPanel.Document.Invalid"), this.missingResourceStyler);
            }
        }
        return result;
    }

}
