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

package org.modelio.diagram.editor.statik.elements.informationflowlink;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.informationconveyed.GmConveyedClassifiersGroup;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link InformationFlow}.
 */
@objid ("3504b25a-55b7-11e2-877f-002564c97630")
public class GmInformationFlowLink extends GmLink {
    @objid ("3504b25e-55b7-11e2-877f-002564c97630")
    private InformationFlow element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3504b263-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3504b266-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("06a2b8f8-9efd-491f-9dae-791ab2e4db05")
    public static final String ROLE_CONVEYED_GROUP = "conveyed_group";

    @objid ("5d1270ed-5bd5-11e2-9e33-00137282c51b")
    private static final InformationFlowLinkStyleKeys STRUCTURED_KEYS = new InformationFlowLinkStyleKeys();

    @objid ("efb521ab-cc49-49a3-ae72-3c879a2d88c8")
    private GmInformationFlowLinkHeader header;

    /**
     * Constructor for deserialization.
     */
    @objid ("3504b268-55b7-11e2-877f-002564c97630")
    public GmInformationFlowLink() {
        // Nothing to do.
    }

    /**
     * Creates a GmElementImport.
     * @param diagram The diagram containing the link.
     * @param role The represented element.
     * @param ref The represented element reference. May not be null.
     */
    @objid ("3504b26b-55b7-11e2-877f-002564c97630")
    public GmInformationFlowLink(IGmDiagram diagram, InformationFlow role, MRef ref) {
        super(diagram, ref);
        
        this.element = role;
        
        if (role != null) {
            // Create extensions
            addExtension(ExtensionLocation.TargetNW, ROLE_MAIN_LABEL, new GmInformationFlowLinkHeader(diagram, ref));
            addExtension(ExtensionLocation.TargetSE, ROLE_CONVEYED_GROUP, new GmConveyedClassifiersGroup(diagram, ref));
        }
    }

    @objid ("3504b277-55b7-11e2-877f-002564c97630")
    @Override
    public UmlModelElement getFromElement() {
        final List<UmlModelElement> sources = this.element.getInformationSource();
        if (sources.isEmpty()) {
            return null;
        } else {
            return sources.get(0);
        }
    }

    @objid ("3504b27d-55b7-11e2-877f-002564c97630")
    @Override
    public InformationFlow getRelatedElement() {
        return this.element;
    }

    @objid ("3504b284-55b7-11e2-877f-002564c97630")
    @Override
    public InformationFlow getRepresentedElement() {
        return this.element;
    }

    @objid ("3504b28b-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTURED_KEYS.getStyleKey(metakey);
    }

    @objid ("350638fb-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTURED_KEYS.getStyleKeys();
    }

    @objid ("35063904-55b7-11e2-877f-002564c97630")
    @Override
    public UmlModelElement getToElement() {
        final List<UmlModelElement> targets = this.element.getInformationTarget();
        if (targets.isEmpty()) {
            return null;
        } else {
            return targets.get(0);
        }
    }

    @objid ("3506390a-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (InformationFlow) resolveRef(this.getRepresentedRef());
    }

    @objid ("35063910-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInformationFlowLink.", GmInformationFlowLink.MINOR_VERSION);
    }

    @objid ("35063916-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("c2d7b5f0-1557-4d2f-85fd-4f2010149241")
    public GmInformationFlowLinkHeader getHeader() {
        if (this.header == null) {
            for (GmNodeModel ext : getExtensions()) {
                if (ext instanceof GmInformationFlowLinkHeader) {
                    this.header = (GmInformationFlowLinkHeader) ext;
                }
            }
        }
        return this.header;
    }

    @objid ("1dc4f876-946c-4e63-969e-ecefe360d320")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmInformationFlowLinkHeader) {
                n.setRoleInComposition(ROLE_MAIN_LABEL);
            } else {
                n.setRoleInComposition(ROLE_CONVEYED_GROUP);
            }
        }
    }

}
