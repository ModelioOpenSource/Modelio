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

package org.modelio.uml.statikdiagram.editor.elements.signal;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.common.portcontainer.PortConstraint.Border;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Signal;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.uml.statikdiagram.editor.elements.templatecontainer.GmTemplateContainer;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the GmPortContainer class for SendSignal.
 * 
 * @author fpoyer
 */
@objid ("3689cbba-55b7-11e2-877f-002564c97630")
public class GmSignal extends GmTemplateContainer {
    @objid ("368b5261-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_LABEL_ROLE = "ImageLabel";

    @objid ("368b525f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3689cbbc-55b7-11e2-877f-002564c97630")
    private Signal element;

    /**
     * Current version of this Gm.
     */
    @objid ("368b525c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 3;

    @objid ("02c672a2-9bbb-4fc3-b834-a380aa70ffe1")
    private static final String BODY_CONTENT_AS_SATELLITE = "body content as satellite";

    @objid ("a797e671-55c2-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider IMAGE_KEYS = new GmSignalImageStyleKeys();

    @objid ("a797e66f-55c2-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider SIMPLE_KEYS = new GmSignalSimpleStyleKeys();

    @objid ("a797e66d-55c2-11e2-9337-002564c97630")
     static final AbstractStyleKeyProvider STRUCTURED_KEYS = new GmSignalStructuredStyleKeys();

    @objid ("4129a3ff-c76a-498a-b41a-c4f9d7097958")
     static final AbstractStyleKeyProvider USERIMAGE_KEYS = new GmSignalUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the sendSignal is unmasked.
     * @param el the unmasked sendSignal.
     * @param ref a reference to the unmasked sendSignal.
     */
    @objid ("368b5263-55b7-11e2-877f-002564c97630")
    public GmSignal(IGmDiagram diagram, Signal el, MRef ref) {
        super(diagram, new GmSignalPrimaryNode(diagram, ref), ref);
        this.element = el;
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(GmSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader);
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("368b5291-55b7-11e2-877f-002564c97630")
    public GmSignal() {
        // Nothing specific to do.
    }

    @objid ("368b526f-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("368b5277-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("368cd91f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmSignal.MAJOR_VERSION;
    }

    @objid ("368cd901-55b7-11e2-877f-002564c97630")
    @Override
    public Signal getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("368cd8fa-55b7-11e2-877f-002564c97630")
    @Override
    public Signal getRepresentedElement() {
        return this.element;
    }

    @objid ("368b527f-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = GmSignal.STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmSignal.SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = GmSignal.IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("368b5289-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
        case IMAGE:
            return GmSignal.IMAGE_KEYS.getStyleKeys();
        case USER_IMAGE:
            return GmSignal.USERIMAGE_KEYS.getStyleKeys();
        case SIMPLE:
            return GmSignal.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmSignal.STRUCTURED_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("368cd92a-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret = super.getVisibleChildren();
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
            case STRUCTURED:
            case SIMPLE: {
                GmNodeModel imageModeHeader = getFirstChild(GmSignal.IMAGE_LABEL_ROLE);
                ret.remove(imageModeHeader);
                break;
            }
            case IMAGE:
            default: {
                break;
            }
        
            }
        }
        return ret;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("368e5fa4-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    /**
     * Is this node a Satellite, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Satellite.
     */
    @objid ("368cd933-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return GmSignal.BODY_CONTENT_AS_SATELLITE.equals(role)
                                                                || GmPortContainer.SATELLITE_ROLE.equals(role)
                                                                || GmSignal.IMAGE_LABEL_ROLE.equals(role);
    }

    @objid ("368b5294-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmSignal.");
        switch (readVersion) {
        case 0:
            read_0(in);
            break;
        case 1:
            read_1(in);
            break;
        case 2:
            read_2(in);
            break;
        case 3:
            read_3(in);
            break;
        default:
            assert (false) : "version number not covered!";
            // reading as last handled version: 2
            read_1(in);
            break;
        
        }
    }

    @objid ("368cd90b-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        refreshPortsFromObModel();
    }

    @objid ("368cd914-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        GmAbstractObject.writeMinorVersion(out, "GmSignal.", Integer.valueOf(GmSignal.MINOR_VERSION));
    }

    /**
     * @return true if ports are to be unmasked automatically.
     */
    @objid ("368cd90e-55b7-11e2-877f-002564c97630")
    protected Boolean arePortsAutoDisplayed() {
        return getDisplayedStyle().getProperty(GmSignalStructuredStyleKeys.SHOWPORTS);
    }

    @objid ("368cd91a-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Signal) resolveRef(getRepresentedRef());
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        super.addChild(imageModeHeader, 1);
    }

    @objid ("368cd924-55b7-11e2-877f-002564c97630")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.element = (Signal) resolveRef(getRepresentedRef());
        
        // Issue 0013010: replace the image label to make it selectable...
        GmModelElementHeader oldImageLabel = (GmModelElementHeader) getFirstChild(GmSignal.IMAGE_LABEL_ROLE);
        Object oldLayoutData = oldImageLabel.getLayoutData();
        oldImageLabel.delete();
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(oldLayoutData);
        super.addChild(imageModeHeader);
    }

    /**
     * Automatically unmask ports if asked.
     */
    @objid ("368cd908-55b7-11e2-877f-002564c97630")
    private void refreshPortsFromObModel() {
        if (arePortsAutoDisplayed()) {
            final Signal node = getRepresentedElement();
            if (node != null && node.isValid()) {
                for (BindableInstance part : node.getInternalStructure()) {
                    if (part instanceof Port && getChild(new MRef(part)) == null) {
                        GmNodeModel gmPort = getDiagram().unmask(this, part, Border.East);
                        gmPort.setRoleInComposition(GmPortContainer.PORT_ROLE);
                    }
                }
            }
        }
    }

    @objid ("72d6c3ef-abe4-48b2-a410-94c404d8bd9b")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(GmSignal.IMAGE_LABEL_ROLE);
    }

    @objid ("160d1cfe-d555-461a-aa35-52d60d3c4f07")
    private void read_3(IDiagramReader in) {
        super.read(in);
        this.element = (Signal) resolveRef(getRepresentedRef());
    }

    @objid ("e4264063-d9ba-4bb2-894e-d4b53406e773")
    private void read_2(IDiagramReader in) {
        super.read(in);
        this.element = (Signal) resolveRef(getRepresentedRef());
        
        // Issue 0013010: replace the image label to make it selectable...
        GmModelElementHeader oldImageLabel = (GmModelElementHeader) getFirstChild(GmSignal.IMAGE_LABEL_ROLE);
        Object oldLayoutData = oldImageLabel.getLayoutData();
        oldImageLabel.delete();
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(getDiagram(), getRepresentedRef());
        imageModeHeader.setRoleInComposition(GmSignal.IMAGE_LABEL_ROLE);
        imageModeHeader.setLayoutData(oldLayoutData);
        super.addChild(imageModeHeader);
    }

    @objid ("0428f47f-9fdf-4a63-8eb7-d02e417a0e91")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return SignalSymbolViewModel.create(getPersistedStyle(), this);
    }

}
