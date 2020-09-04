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

package org.modelio.diagram.elements.umlcommon.externdocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
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
import org.modelio.editors.richnote.api.RichNoteFormatRegistry;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.ResourceType;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphical model for a {@link Document}.
 */
@objid ("81544dee-1dec-11e2-8cad-001ec947c8cc")
public class GmExternDocument extends GmSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("81544df3-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("81544df6-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("81544df0-1dec-11e2-8cad-001ec947c8cc")
    private Document document;

    @objid ("81544df1-1dec-11e2-8cad-001ec947c8cc")
     static final GmExternDocumentStyleKeys KEYS = new GmExternDocumentStyleKeys();

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("81544df8-1dec-11e2-8cad-001ec947c8cc")
    public GmExternDocument() {
    }

    /**
     * Creates a GmExternDocument.
     * @param diagram The diagram owning the node
     * @param document The represented document element
     * @param ref The represented document reference
     */
    @objid ("8156b005-1dec-11e2-8cad-001ec947c8cc")
    public GmExternDocument(final IGmDiagram diagram, final Document document, final MRef ref) {
        super(diagram, ref);
        this.document = document;
    }

    /**
     * @return the external document name.
     */
    @objid ("8156b00e-1dec-11e2-8cad-001ec947c8cc")
    public String getName() {
        if (this.document != null) {
            return this.document.getName();
        } else {
            return "?";
        }
    }

    /**
     * @return the external document content.
     */
    @objid ("8156b012-1dec-11e2-8cad-001ec947c8cc")
    public String getContents() {
        if (this.document != null) {
            return this.document.getAbstract();
        } else {
            return "?";
        }
    }

    @objid ("8156b016-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        if (this.document == null) {
            return null;
        }
        return new IEditableText() {
                    @Override
                    public String getText() {
                        return GmExternDocument.this.getRepresentedElement().getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        GmExternDocument.this.getRepresentedElement().setName(text);
                    }
                };
    }

    @objid ("8156b01b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Document getRepresentedElement() {
        return this.document;
    }

    @objid ("8156b020-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("8156b025-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmExternDocument.KEYS.getStyleKey(metakey);
    }

    @objid ("8156b02c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmExternDocument.KEYS.getStyleKeys();
    }

    /**
     * Get the extern document type label.
     * @return the document type label.
     */
    @objid ("8156b033-1dec-11e2-8cad-001ec947c8cc")
    public String getType() {
        if (this.document == null) {
            return "?";
        }
        
        final ResourceType resourceType = this.document.getType();
        if (resourceType != null) {
            return !ModuleI18NService.getLabel(resourceType).isEmpty() ? ModuleI18NService.getLabel(resourceType) : resourceType.getName();
        }
        return "";
    }

    /**
     * Get the extern document type label.
     * @return the document type label.
     */
    @objid ("8156b038-1dec-11e2-8cad-001ec947c8cc")
    public Image getMimeType() {
        return RichNoteFormatRegistry.getInstance().getFormat(getRepresentedElement()).getIcon();
    }

    @objid ("8156b03d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmExternDocument.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("8156b042-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        if (this.document != null) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, this.document.getAbstract());
        
            refreshExternDocumentLink();
        }
    }

    @objid ("8156b045-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("8156b04a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removeEndingLink(final IGmLink gmLink) {
        boolean selfDelete = false;
        if (getRelatedElement() != null && getRelatedElement().equals(gmLink.getRelatedElement())) {
            // the removed link represents the same element (the document) as this gm: delete self as well.
            selfDelete = true;
        }
        super.removeEndingLink(gmLink);
        if (selfDelete) {
            // the removed link represents the same element (the document) as this gm: delete self as well.
            delete();
        }
    }

    @objid ("8156b04f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmExternDocument.", GmExternDocument.MINOR_VERSION);
    }

    @objid ("8159125f-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.document = (Document) resolveRef(getRepresentedRef());
    }

    @objid ("81591263-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmExternDocument.MAJOR_VERSION;
    }

    /**
     * Refresh the link between this Document and its parent.
     * <p>
     * Invalid links are deleted, and missing links are unmasked.
     * </p>
     */
    @objid ("35d03db1-e1b7-4a76-8a17-e489c13464ed")
    private void refreshExternDocumentLink() {
        Document relatedElement = getRepresentedElement();
        if (relatedElement == null || !relatedElement.isValid() || getDiagram() == null) {
            return;
        }
        
        ModelElement subject = relatedElement.getSubject();
        if (subject instanceof AbstractDiagram) {
            // Documents on diagrams do not have a link
            return;
        }
        
        boolean found = false;
        // Start by scanning existing links
        for (IGmLink gmLink : new ArrayList<>(getEndingLinks())) {
            if (gmLink instanceof GmExternDocumentLink && gmLink.getFrom() != null) {
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

}
