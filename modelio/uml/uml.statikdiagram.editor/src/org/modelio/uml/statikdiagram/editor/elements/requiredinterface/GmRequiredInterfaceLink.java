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

package org.modelio.uml.statikdiagram.editor.elements.requiredinterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLink;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the link between the port and the provided interface node.
 * <p>
 * The annoted element is the source and the destination is the diagram.
 * 
 * @author cmarin
 */
@objid ("36777c70-55b7-11e2-877f-002564c97630")
public class GmRequiredInterfaceLink extends GmLink {
    @objid ("36777c74-55b7-11e2-877f-002564c97630")
    private RequiredInterface element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36777c79-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36777c7c-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("63a0e86c-5bd5-11e2-9e33-00137282c51b")
     static final RequiredInterfaceStyleKeys SIMPLE_KEYS = new RequiredInterfaceStyleKeys();

    /**
     * Constructor that must be used for deserialization only.
     */
    @objid ("36777c7e-55b7-11e2-877f-002564c97630")
    public GmRequiredInterfaceLink() {
        // Nothing to do.
    }

    /**
     * Creates a new GmNoteLink
     * 
     * @param diagram The diagram containing the link.
     * @param el the represented element, may be null.
     * @param relatedRef a reference to the represented Note.
     */
    @objid ("367902d9-55b7-11e2-877f-002564c97630")
    public GmRequiredInterfaceLink(IGmDiagram diagram, final RequiredInterface el, MRef relatedRef) {
        super(diagram, relatedRef);
        this.element = el;
        
        addExtension(new GmRequiredInterfaceLabel(diagram, el, relatedRef),
                     ROLE_MAIN_LABEL, new GmFractionalConnectionLocator(0.9, 0, -25));
    }

    @objid ("367902e6-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.getRelatedElement().getRequiring();
    }

    @objid ("367902ed-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        Collection<NaryConnector> connectors = getConnections();
        if (connectors.isEmpty()) {
            return this.getDiagram().getRelatedElement();
        } else {
            return connectors.iterator().next();
        }
    }

    @objid ("367902f3-55b7-11e2-877f-002564c97630")
    @Override
    public RequiredInterface getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("367902fa-55b7-11e2-877f-002564c97630")
    @Override
    public RequiredInterface getRepresentedElement() {
        return this.element;
    }

    @objid ("36790301-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return SIMPLE_KEYS.getStyleKey(metakey);
    }

    @objid ("3679030b-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return SIMPLE_KEYS.getStyleKeys();
    }

    /**
     * Get the stereotype image.
     * 
     * @return the stereotype image.
     */
    @objid ("36790314-55b7-11e2-877f-002564c97630")
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    @objid ("367a897b-55b7-11e2-877f-002564c97630")
    @Override
    public void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (RequiredInterface) resolveRef(this.getRepresentedRef());
    }

    /**
     * Get all connections to provided interfaces in the model.
     * 
     * @return all provided interface connections.
     */
    @objid ("367a8981-55b7-11e2-877f-002564c97630")
    public Collection<NaryConnector> getConnections() {
        final Collection<NaryConnector> ret = new ArrayList<>();
        for (NaryLinkEnd l : this.getRelatedElement().getNaryProvider(NaryConnectorEnd.class)) {
            final NaryLink linkNode = l.getNaryLink();
            ret.add((NaryConnector)linkNode);
        }
        return ret;
    }

    /**
     * Get the lollipop connection between this required interface and connected provided interfaces.
     * 
     * @return the lollipop connection
     */
    @objid ("367a898a-55b7-11e2-877f-002564c97630")
    public GmLollipopConnection getLollipopConnection() {
        final IGmLinkable t = getTo();
        if (t instanceof GmLollipopConnection) {
            return (GmLollipopConnection) t;
        } else {
            return null;
        }
    }

    @objid ("367a898e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmRequiredInterfaceLink.", GmRequiredInterfaceLink.MINOR_VERSION);
    }

    @objid ("367a8994-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("295d3521-3a76-474b-b5e0-9f9c7fb54945")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
