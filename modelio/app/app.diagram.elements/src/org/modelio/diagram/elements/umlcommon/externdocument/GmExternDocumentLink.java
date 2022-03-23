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
package org.modelio.diagram.elements.umlcommon.externdocument;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Document;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the link between the document and the annoted element.
 * <p>
 * The annoted element is the source and the destination is a {@link GmExternDocument}.
 */
@objid ("81591268-1dec-11e2-8cad-001ec947c8cc")
public class GmExternDocumentLink extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8159126a-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("8159126d-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor that must be used for deserialization only.
     */
    @objid ("8159126f-1dec-11e2-8cad-001ec947c8cc")
    public  GmExternDocumentLink() {
        // Nothing to do.
    }

    /**
     * Creates a new GmExternDocumentLink
     * @param diagram The diagram containing the link.
     * @param relatedRef a reference to the represented {@link Document}.
     */
    @objid ("81591272-1dec-11e2-8cad-001ec947c8cc")
    public  GmExternDocumentLink(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("81591279-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Document getRelatedElement() {
        if (getTo() != null) {
            return (Document) getTo().getRelatedElement();
        } else {
            return null;
        }
        
    }

    @objid ("8159127d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmExternDocument.KEYS.getStyleKey(metakey);
    }

    @objid ("81591284-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmExternDocument.KEYS.getStyleKeys();
    }

    @objid ("8159128b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("81591292-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getFromElement() {
        return getRelatedElement().getSubject();
    }

    @objid ("81591297-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getToElement() {
        return getRelatedElement();
    }

    /**
     * Updates the proxy style to point to the given node style.
     * @param ref the reference node, may be null.
     */
    @objid ("8159129c-1dec-11e2-8cad-001ec947c8cc")
    private void refreshStyle(final GmAbstractObject ref) {
        // Modify the style
        if (ref != null) {
            getPersistedStyle().setCascadedStyle(ref.getPersistedStyle());
        } else {
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
        }
        
    }

    @objid ("815b74bd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTo(final IGmLinkable to) {
        super.setTo(to);
        if (to instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) to);
        }
        
    }

    @objid ("815b74c2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void readLink(final IDiagramReader in) {
        super.readLink(in);
        if (getTo() instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) getTo());
        }
        
    }

    @objid ("815b74c7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmExternDocumentLink.", GmExternDocumentLink.MINOR_VERSION);
        
    }

    @objid ("815b74cb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmExternDocumentLink.MAJOR_VERSION;
    }

    @objid ("247d1bc6-9fb4-4651-ba21-64ef08abdfc9")
    @Override
    protected void read_GmLinkV0_roles() {
        // nothing : no label
    }

}
