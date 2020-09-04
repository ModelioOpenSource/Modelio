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

package org.modelio.diagram.editor.statik.elements.templatecontainer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.templateparameter.GmTemplateSignature;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Abstract class for elements that can have template parameters.
 */
@objid ("36e0c595-55b7-11e2-877f-002564c97630")
public abstract class GmTemplateContainer extends GmPortContainer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36e0c59a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36e0c59d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5a020b42-5bd5-11e2-9e33-00137282c51b")
    private GmTemplateSignature templateSignature;

    /**
     * Constructor.
     * 
     * @param diagram The diagram in which this port container will be unmasked.
     * @param mainNode The main node that will be decorated with ports. This node is automatically added as child of the port
     * container.
     * @param relatedRef a reference to the element this GmModel is related to.
     * should be accessed in {@link GmPortContainer#getMainNode()}.
     */
    @objid ("36e0c59f-55b7-11e2-877f-002564c97630")
    public GmTemplateContainer(final IGmDiagram diagram, final GmNodeModel mainNode, final MRef relatedRef) {
        super(diagram, relatedRef);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
    }

    /**
     * Empty constructor for deserialization only.
     */
    @objid ("36e24c01-55b7-11e2-877f-002564c97630")
    public GmTemplateContainer() {
        super();
    }

    @objid ("36e24c04-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        refreshTemplateSignature();
    }

    @objid ("36e24c07-55b7-11e2-877f-002564c97630")
    private void refreshTemplateSignature() {
        if (this.templateSignature == null) {
            if (hasTemplateParameters()) {
                this.templateSignature = new GmTemplateSignature(getDiagram(),
                                                                 getRelatedElement(),
                                                                 getRepresentedRef());
                this.templateSignature.setLayoutData(Border.NorthEast);
                this.templateSignature.setRoleInComposition(PORT_ROLE);
                addChild(this.templateSignature);
            }
        } else {
            if (!hasTemplateParameters()) {
                this.templateSignature.delete();
                this.templateSignature = null;
            }
        }
    }

    /**
     * The related element must be a namespace.
     */
    @objid ("36e24c09-55b7-11e2-877f-002564c97630")
    @Override
    public abstract NameSpace getRelatedElement();

    /**
     * Tells whether the related element is a template.
     * 
     * @return true only if the related element has template parameters.
     */
    @objid ("36e24c0f-55b7-11e2-877f-002564c97630")
    private boolean hasTemplateParameters() {
        NameSpace relatedIElement = getRelatedElement();
        if (relatedIElement == null || !relatedIElement.isValid())
            return false;
        return (relatedIElement.getTemplate().size() > 0);
    }

    @objid ("36e24c14-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        if (this.templateSignature != null)
            out.writeProperty("templateSignature", this.templateSignature);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmTemplateContainer.", Integer.valueOf(GmTemplateContainer.MINOR_VERSION));
    }

    @objid ("36e24c1b-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTemplateContainer.");
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

    @objid ("36e24c22-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.templateSignature = (GmTemplateSignature) in.readProperty("templateSignature");
    }

    @objid ("36e24c28-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("36e3d29c-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("36e3d2a3-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    @objid ("36e3d2aa-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(final Class<? extends GmNodeModel> nodeClass) {
        return !GmElementLabel.class.isAssignableFrom(nodeClass);
    }

}
