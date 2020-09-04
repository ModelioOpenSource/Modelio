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

package org.modelio.diagram.editor.activity.elements.loopnode;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.activity.elements.activitynodeheader.GmActivityNodeHeader;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.IImageableNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.LoopNode;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link ILoopNode loop node}.
 */
@objid ("2abcf918-55b6-11e2-877f-002564c97630")
public class GmLoopNodePrimaryNode extends GmNoStyleCompositeNode implements IImageableNode {
    /**
     * Current version of this Gm.
     */
    @objid ("2abe7f85-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("2abe7f88-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Header
     */
    @objid ("2abe7f79-55b6-11e2-877f-002564c97630")
    private GmActivityNodeHeader header;

    /**
     * Inner freezone.
     */
    @objid ("d2305bc9-55c0-11e2-9337-002564c97630")
    private GmBodyFreeZone innerZone;

    @objid ("d2305bcb-55c0-11e2-9337-002564c97630")
    private GmElementLabel setup;

    @objid ("d2305bcc-55c0-11e2-9337-002564c97630")
    private GmElementLabel test;

    /**
     * C'tor. Empty constructor needed for (de-)serialisation.
     */
    @objid ("2abe7f8a-55b6-11e2-877f-002564c97630")
    public GmLoopNodePrimaryNode() {
        // empty constructor for the serialization
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the loopnode this gm is related to.
     */
    @objid ("2abe7f8d-55b6-11e2-877f-002564c97630")
    public GmLoopNodePrimaryNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        
        this.header = new GmActivityNodeHeader(diagram, relatedRef);
        this.header.setShowMetaclassIcon(true);
        this.setup = new GmSetup(diagram, relatedRef);
        this.test = new GmTest(diagram, relatedRef);
        this.innerZone = new GmBodyFreeZone(diagram, relatedRef);
        
        super.addChild(this.header);
        super.addChild(this.setup);
        super.addChild(this.test);
        super.addChild(this.innerZone);
    }

    @objid ("2abe7f96-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        if (child.getRelatedElement() instanceof ActivityNode &&
                ((ActivityNode) child.getRelatedElement()).getCompositionOwner().equals(getRelatedElement())) {
            this.innerZone.addChild(child);
        } else
            super.addChild(child);
    }

    @objid ("2abe7f9c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ActivityNode.class.isAssignableFrom(type) &&
                        !Pin.class.isAssignableFrom(type) &&
                        !ActivityParameterNode.class.isAssignableFrom(type);
    }

    @objid ("2abe7fa4-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        Class<? extends MObject> type = el.getClass();
        return ActivityNode.class.isAssignableFrom(type) &&
                        !Pin.class.isAssignableFrom(type) &&
                        !ActivityParameterNode.class.isAssignableFrom(type) &&
                        getRelatedElement() != null &&
                        getRelatedElement().equals(el.getCompositionOwner());
    }

    @objid ("2abe7fac-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this.innerZone;
    }

    @objid ("2abe7fb6-55b6-11e2-877f-002564c97630")
    @Override
    public Image getImage() {
        return ElementImageService.getImage(getRelatedElement());
    }

    /**
     * @return the innerZone.
     */
    @objid ("2abe7fbb-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.innerZone;
    }

    @objid ("2ac0061e-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        final StyleKey repModeKey = GmLoopNode.STRUCTURED_KEYS.getStyleKey(MetaKey.REPMODE);
        return getDisplayedStyle().getProperty(repModeKey);
    }

    @objid ("2ac00625-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLoopNodePrimaryNode.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("2ac0062b-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        String oldLabel = this.header.getMainLabel();
        this.header.refreshFromObModel();
        firePropertyChange(PROPERTY_LABEL, oldLabel, this.header.getMainLabel());
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("2ac0062e-55b6-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
        List<GmNodeModel> ret;
        switch (this.getRepresentationMode()) {
        case IMAGE: {
            ret = Collections.emptyList();
            break;
        }
        default: {
            ret = super.getVisibleChildren();
            break;
        }
        }
        return ret;
    }

    @objid ("2ac00637-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLoopNodePrimaryNode.", GmLoopNodePrimaryNode.MINOR_VERSION);
    }

    @objid ("2ac0063d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.setup = (GmElementLabel) this.getChildren().get(1);
        this.test = (GmElementLabel) this.getChildren().get(2);
        this.innerZone = (GmBodyFreeZone) this.getChildren().get(3);
        
        GmDefaultModelElementLabel imageModeHeader = (GmDefaultModelElementLabel) this.getChildren().get(4);
        imageModeHeader.delete();
    }

    @objid ("2ac00642-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("2ac00647-55b6-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmActivityNodeHeader) this.getChildren().get(0);
        this.setup = (GmElementLabel) this.getChildren().get(1);
        this.test = (GmElementLabel) this.getChildren().get(2);
        this.innerZone = (GmBodyFreeZone) this.getChildren().get(3);
    }

    /**
     * Setup zone of the loop node.
     * 
     * @author sbe
     */
    @objid ("2ac0064d-55b6-11e2-877f-002564c97630")
    @SuppressWarnings ("hiding")
    public static final class GmSetup extends GmElementLabel {
        /**
         * Current version of this Gm. Defaults to 0.
         */
        @objid ("2ac00652-55b6-11e2-877f-002564c97630")
        private static final int MINOR_VERSION = 0;

        @objid ("2ac00655-55b6-11e2-877f-002564c97630")
        private static final int MAJOR_VERSION = 0;

        /**
         * For deserialization only.
         */
        @objid ("2ac00657-55b6-11e2-877f-002564c97630")
        public GmSetup() {
            // for the serialization
        }

        /**
         * Creates a label
         * 
         * @param diagram The diagram
         * @param relatedRef a reference to the loopnode this gm is related to.
         */
        @objid ("2ac18cbb-55b6-11e2-877f-002564c97630")
        public GmSetup(IGmDiagram diagram, MRef relatedRef) {
            super(diagram, relatedRef);
        }

        @objid ("2ac18cc4-55b6-11e2-877f-002564c97630")
        @Override
        public String computeLabel() {
            final String ret = ((LoopNode) getRelatedElement()).getSetup();
            
            if (ret.isEmpty())
                return "Setup: ...";
            return "Setup: " + ret;
        }

        @objid ("2ac18cc9-55b6-11e2-877f-002564c97630")
        @Override
        public IEditableText getEditableText() {
            final LoopNode loopnode = (LoopNode) getRelatedElement();
            if (loopnode == null)
                return null;
            return new IEditableText() {
                            @Override
                            public String getText() {
                                return loopnode.getSetup();
                            }
            
                            @Override
                            public void setText(String text) {
                                loopnode.setSetup(text);
                            }
                        };
        }

        @objid ("2ac18cd0-55b6-11e2-877f-002564c97630")
        @Override
        public void read(IDiagramReader in) {
            // Read version, defaults to 0 if not found
            int readVersion = readMinorVersion(in, "GmSetup.");
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

        @objid ("2ac18cd6-55b6-11e2-877f-002564c97630")
        @Override
        public void write(IDiagramWriter out) {
            super.write(out);
            
            // Write version of this Gm if different of 0
            writeMinorVersion(out, "GmSetup.", GmSetup.MINOR_VERSION);
        }

        @objid ("2ac18cdc-55b6-11e2-877f-002564c97630")
        private void read_0(IDiagramReader in) {
            super.read(in);
        }

        @objid ("2ac18ce1-55b6-11e2-877f-002564c97630")
        @Override
        public int getMajorVersion() {
            return GmSetup.MAJOR_VERSION;
        }

    }

    /**
     * Test zone of the loop node.
     * 
     * @author sbe
     */
    @objid ("2ac18ce6-55b6-11e2-877f-002564c97630")
    @SuppressWarnings ("hiding")
    public static final class GmTest extends GmElementLabel {
        /**
         * Current version of this Gm. Defaults to 0.
         */
        @objid ("2ac18ceb-55b6-11e2-877f-002564c97630")
        private static final int MINOR_VERSION = 0;

        @objid ("2ac18cee-55b6-11e2-877f-002564c97630")
        private static final int MAJOR_VERSION = 0;

        /**
         * Constructor for the deserialization only.
         */
        @objid ("2ac18cf0-55b6-11e2-877f-002564c97630")
        public GmTest() {
            // for the serialization
        }

        /**
         * Creates a label
         * 
         * @param diagram The diagram
         * @param relatedRef a reference to the loopnode this gm is related to.
         */
        @objid ("2ac18cf3-55b6-11e2-877f-002564c97630")
        public GmTest(IGmDiagram diagram, MRef relatedRef) {
            super(diagram, relatedRef);
        }

        @objid ("2ac3135f-55b6-11e2-877f-002564c97630")
        @Override
        public String computeLabel() {
            final String ret = ((LoopNode) getRelatedElement()).getTest();
            
            if (ret.isEmpty())
                return "Test: ...";
            return "Test: " + ret;
        }

        @objid ("2ac31364-55b6-11e2-877f-002564c97630")
        @Override
        public IEditableText getEditableText() {
            final LoopNode loopnode = (LoopNode) getRelatedElement();
            if (loopnode == null)
                return null;
            return new IEditableText() {
                            @Override
                            public String getText() {
                                return loopnode.getTest();
                            }
            
                            @Override
                            public void setText(String text) {
                                loopnode.setTest(text);
                            }
                        };
        }

        @objid ("2ac3136b-55b6-11e2-877f-002564c97630")
        @Override
        public void read(IDiagramReader in) {
            // Read version, defaults to 0 if not found
            int readVersion = readMinorVersion(in, "GmTest.");
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

        @objid ("2ac31371-55b6-11e2-877f-002564c97630")
        @Override
        public void write(IDiagramWriter out) {
            super.write(out);
            
            // Write version of this Gm if different of 0
            writeMinorVersion(out, "GmTest.", GmTest.MINOR_VERSION);
        }

        @objid ("2ac31377-55b6-11e2-877f-002564c97630")
        private void read_0(IDiagramReader in) {
            super.read(in);
        }

        @objid ("2ac3137c-55b6-11e2-877f-002564c97630")
        @Override
        public int getMajorVersion() {
            return GmTest.MAJOR_VERSION;
        }

    }

}
