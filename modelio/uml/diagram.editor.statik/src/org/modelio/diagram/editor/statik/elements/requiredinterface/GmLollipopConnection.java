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

package org.modelio.diagram.editor.statik.elements.requiredinterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryConnector;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the junction point between required interface and provided interface links.
 */
@objid ("367161d7-55b7-11e2-877f-002564c97630")
public class GmLollipopConnection extends GmSimpleNode {
    @objid ("367161db-55b7-11e2-877f-002564c97630")
    private NaryConnector element;

    /**
     * Current version of this Gm. Defaults to 0.
     * <li> 0 : initial version : element was ConnectorEnd
     * <li> 1 : element is NAryConnector (the link, not the end)
     */
    @objid ("367161de-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("367161e1-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("8cfc6013-c7f6-4740-9a37-e874807085c6")
     static String PROP_REFRESH_BRANCHES = "refresh branches";

    /**
     * Default and deserialization constructor.
     */
    @objid ("367161e3-55b7-11e2-877f-002564c97630")
    public GmLollipopConnection() {
        super();
    }

    @objid ("367161e6-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromObModel() {
        refreshStyleFromModel();
        firePropertyChange(PROP_REFRESH_BRANCHES, null, this);
    }

    /**
     * Initialize a lollipop connection.
     * 
     * @param diagram The diagram.
     * @param element The represented connector, may be null.
     */
    @objid ("367161e9-55b7-11e2-877f-002564c97630")
    public GmLollipopConnection(final IGmDiagram diagram, final NaryConnector element) {
        super(diagram, new MRef(element));
        this.element = element;
        
        addPropertyChangeListener(new LinkListener());
    }

    @objid ("3672e85d-55b7-11e2-877f-002564c97630")
    @Override
    public NaryConnector getRepresentedElement() {
        return this.element;
    }

    @objid ("3672e864-55b7-11e2-877f-002564c97630")
    @Override
    public NaryConnector getRelatedElement() {
        return this.element;
    }

    @objid ("3672e86b-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.SIMPLE;
    }

    @objid ("3672e872-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        final GmLink l = getFirstProvidedInterface();
        if (l != null) {
            return l.getStyleKey(metakey);
        }
        return null;
    }

    @objid ("3672e87d-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        final GmLink l = getFirstProvidedInterface();
        if (l != null) {
            return l.getStyleKeys();
        }
        return Collections.emptyList();
    }

    @objid ("3672e886-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLollipopConnection.");
        switch (readVersion) {
        case 1:
            read_1(in);
            break;
        case 0: 
            read_0(in);
            break;
        
        default: 
            assert (false) : readVersion+ " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        
        }
    }

    @objid ("3672e88d-55b7-11e2-877f-002564c97630")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        return new ProxyStyle();
    }

    @objid ("3672e898-55b7-11e2-877f-002564c97630")
    protected void refreshStyleFromModel() {
        final GmLink link = getFirstProvidedInterface();
        if (link != null) {
            getPersistedStyle().setCascadedStyle(link.getPersistedStyle());
        } else {
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
        }
    }

    @objid ("3672e89a-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementDeleted() {
        // Tell the edit part about deletion in order to reconnect remaining 
        // required/provided interface links.
        for (IGmLink l : new ArrayList<>(getEndingLinks())) {
            if (l instanceof GmLink) {
                ((GmLink) l).obElementsUpdated();
            }
        }
        
        // Call inherited
        super.obElementDeleted();
    }

    /**
     * Get the first provided interface link connected to this node.
     * 
     * @return the first provided interface link.
     */
    @objid ("36746efa-55b7-11e2-877f-002564c97630")
    GmLink getFirstProvidedInterface() {
        for (IGmLink l : getEndingLinks()) {
            if (l instanceof GmProvidedInterfaceLink) {
                return (GmLink) l;
            }
        }
        return null;
    }

    @objid ("36746f01-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm.
        writeMinorVersion(out, "GmLollipopConnection.", Integer.valueOf(MINOR_VERSION));
    }

    @objid ("36746f07-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        
        MObject el = resolveRef(getRepresentedRef());
        this.element = (NaryConnector) el;
    }

    @objid ("36746f0d-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("ed916166-7159-4519-9465-466e3bb48c44")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        MObject el = resolveRef(getRepresentedRef());
        if (el instanceof NaryConnectorEnd) {
            this.element = (NaryConnector) ((NaryConnectorEnd) el).getNaryLink();
        } else  if (el instanceof NaryConnector) {
            this.element = (NaryConnector) el;
        } else  if (el instanceof ConnectorEnd) {
            this.element = null; //(Connector) ((ConnectorEnd) el).getLink();
        } else  if (el instanceof Connector) {
            this.element = null; //(Connector) el;
        } else {
            throw new IllegalArgumentException(el.toString());
        }
    }

    /**
     * Update the proxy style each time a link is connected to this node.
     */
    @objid ("36746f12-55b7-11e2-877f-002564c97630")
    private class LinkListener implements PropertyChangeListener {
        @objid ("36746f14-55b7-11e2-877f-002564c97630")
        public LinkListener() {
            super();
        }

        @objid ("36746f16-55b7-11e2-877f-002564c97630")
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            refreshStyleFromModel();
        }

    }

}
