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

package org.modelio.diagram.editor.state.elements.deephistory;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.name.GmNameLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.stateMachineModel.DeepHistoryPseudoState;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialization of the {@link GmPortContainer} class for {@link IDeepHistoryPseudoState}.
 * 
 * @author fpoyer
 */
@objid ("f503cf3f-55b6-11e2-877f-002564c97630")
public class GmDeepHistory extends GmPortContainer {
    @objid ("f503cf43-55b6-11e2-877f-002564c97630")
    private DeepHistoryPseudoState element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f50555bc-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f50555bf-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("f503cf48-55b6-11e2-877f-002564c97630")
    private static final GmDeepHistorySimpleStyleKeys SIMPLE_KEYS = new GmDeepHistorySimpleStyleKeys();

    @objid ("fd2cb2e9-5a5b-11e2-9e33-00137282c51b")
     static final GmDeepHistoryStructuredStyleKeys STRUCTURED_KEYS = new GmDeepHistoryStructuredStyleKeys();

    @objid ("fd2cb2eb-5a5b-11e2-9e33-00137282c51b")
    private static final GmDeepHistoryImageStyleKeys IMAGE_KEYS = new GmDeepHistoryImageStyleKeys();

    @objid ("c7db5c67-5057-42e4-9f18-af34a8679406")
    private static final GmDeepHistoryUserImageStyleKeys USERIMAGE_KEYS = new GmDeepHistoryUserImageStyleKeys();

    /**
     * Constructor.
     * 
     * @param diagram the diagram in which the element is unmasked.
     * @param el the unmasked element, can be <i>null</i>.
     * @param ref the unmasked element reference, must not be <i>null</i>..
     */
    @objid ("f50555c1-55b6-11e2-877f-002564c97630")
    public GmDeepHistory(IGmDiagram diagram, DeepHistoryPseudoState el, MRef ref) {
        super(diagram, ref);
        
        GmDeepHistoryPrimaryNode mainNode = new GmDeepHistoryPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        
        this.element = el;
        
        final GmNameLabel label = new GmNameLabel(diagram, ref);
        label.setRoleInComposition(GmPortContainer.SATELLITE_ROLE);
        label.setLayoutData(Integer.valueOf(PositionConstants.EAST));
        
        this.addChild(mainNode);
        this.addChild(label);
    }

    @objid ("f50555cd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("f50555d5-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("f50555dd-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey styleKey = STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
                case IMAGE:
                    return IMAGE_KEYS.getStyleKey(metakey);
                case USER_IMAGE:
                    return USERIMAGE_KEYS.getStyleKey(metakey);
                case SIMPLE:
                    return SIMPLE_KEYS.getStyleKey(metakey);
                case STRUCTURED:
                    return STRUCTURED_KEYS.getStyleKey(metakey);
            }
        }
        return null;
    }

    @objid ("f50555e7-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        StyleKey styleKey = STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        if (styleKey != null) {
            RepresentationMode mode = getDisplayedStyle().getProperty(styleKey);
            switch (mode) {
                case IMAGE:
                    return IMAGE_KEYS.getStyleKeys();
                case USER_IMAGE:
                    return USERIMAGE_KEYS.getStyleKeys();
                case SIMPLE:
                    return SIMPLE_KEYS.getStyleKeys();
                case STRUCTURED:
                    return STRUCTURED_KEYS.getStyleKeys();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Empty constructor needed for deserialisation.
     */
    @objid ("f50555f0-55b6-11e2-877f-002564c97630")
    public GmDeepHistory() {
        // Nothing specific to do.
    }

    @objid ("f50555f3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDeepHistory.");
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

    @objid ("f50555f9-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("f506dc59-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("f506dc60-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDeepHistory.", GmDeepHistory.MINOR_VERSION);
    }

    @objid ("f506dc66-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (DeepHistoryPseudoState) resolveRef(this.getRepresentedRef());
    }

    @objid ("f506dc6b-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Is this node a Port, which position is defined relatively to the Main Node's bounds.
     * 
     * @param childNode the node to check.
     * @return <code>true</code> if the node is a Port.
     */
    @objid ("f506dc78-55b6-11e2-877f-002564c97630")
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
    @objid ("f506dc82-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("e7f3a5d7-24fe-436f-8cb7-74b9d8625a23")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
