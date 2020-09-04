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

package org.modelio.diagram.editor.activity.elements.clause;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.freezone.GmBodyFreeZone;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityNode;
import org.modelio.metamodel.uml.behavior.activityModel.ActivityParameterNode;
import org.modelio.metamodel.uml.behavior.activityModel.Clause;
import org.modelio.metamodel.uml.behavior.activityModel.Pin;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a Clause.
 */
@objid ("2a045763-55b6-11e2-877f-002564c97630")
public class GmClause extends GmCompositeNode {
    @objid ("2a045767-55b6-11e2-877f-002564c97630")
    private Clause element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a045771-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a045774-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d100674c-55c0-11e2-9337-002564c97630")
    private GmBodyFreeZone innerZone;

    @objid ("d100674d-55c0-11e2-9337-002564c97630")
    private GmElementLabel test;

    @objid ("303b405c-58a2-11e2-9574-002564c97630")
    private static GmClauseStructuredStyleKeys STRUCTKEYS = new GmClauseStructuredStyleKeys();

    /**
     * Empty constructor, needed for serialisation.
     */
    @objid ("2a045776-55b6-11e2-877f-002564c97630")
    public GmClause() {
        // constructor empty for the serialization
    }

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param theClause the represented clause, may be null.
     * @param ref a reference to the represented clause.
     */
    @objid ("2a05ddd9-55b6-11e2-877f-002564c97630")
    public GmClause(IGmDiagram diagram, Clause theClause, MRef ref) {
        super(diagram, ref);
        this.element = theClause;
        this.test = new GmTest(diagram, ref);
        this.innerZone = new GmBodyFreeZone(diagram, ref);
        super.addChild(this.test);
        super.addChild(this.innerZone);
    }

    @objid ("2a05dde5-55b6-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel child) {
        if (child.getRelatedElement() instanceof ActivityNode &&
                ((ActivityNode) child.getRelatedElement()).getCompositionOwner().equals(this.element)) {
            this.innerZone.addChild(child);
        } else {
            super.addChild(child);
        }
    }

    @objid ("2a05ddeb-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ActivityNode.class.isAssignableFrom(type) &&
                        !Pin.class.isAssignableFrom(type) &&
                        !ActivityParameterNode.class.isAssignableFrom(type);
    }

    @objid ("2a05ddf3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (this.element != null && this.element.equals(el.getCompositionOwner()));
    }

    @objid ("2a05ddfb-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return this.innerZone;
    }

    /**
     * Get the inner zone where the clause content is displayed.
     * 
     * @return The inner zone.
     */
    @objid ("2a05de05-55b6-11e2-877f-002564c97630")
    public GmCompositeNode getInnerZone() {
        return this.innerZone;
    }

    @objid ("2a05de0c-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("2a05de13-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("2a076480-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return STRUCTKEYS.getStyleKeys();
    }

    @objid ("2a076489-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmClause.");
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

    @objid ("2a07648f-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing specific to do.
    }

    @objid ("2a076492-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a076499-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2a0764a0-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmClause.", MINOR_VERSION);
    }

    @objid ("2a0764a6-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.test = (GmElementLabel) this.getChildren().get(0);
        this.innerZone = (GmBodyFreeZone) this.getChildren().get(1);
        this.element = (Clause) resolveRef(getRepresentedRef());
    }

    @objid ("2a0764ab-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Clause test label.
     * 
     * @author cmarin
     */
    @objid ("2a0764b0-55b6-11e2-877f-002564c97630")
    @SuppressWarnings ("hiding")
    public static final class GmTest extends GmElementLabel {
        /**
         * Current version of this Gm. Defaults to 0.
         */
        @objid ("2a0764b5-55b6-11e2-877f-002564c97630")
        private static final int MINOR_VERSION = 0;

        @objid ("2a08eb19-55b6-11e2-877f-002564c97630")
        private static final int MAJOR_VERSION = 0;

        /**
         * Constructor for deserialization only.
         */
        @objid ("2a08eb1b-55b6-11e2-877f-002564c97630")
        public GmTest() {
            // for the serialization
        }

        /**
         * Initialize the test label.
         * 
         * @param diagram The diagram
         * @param ref a reference to the represented element.
         */
        @objid ("2a08eb1e-55b6-11e2-877f-002564c97630")
        public GmTest(IGmDiagram diagram, MRef ref) {
            super(diagram, ref);
        }

        @objid ("2a08eb27-55b6-11e2-877f-002564c97630")
        @Override
        public String computeLabel() {
            final String ret = ((Clause) getRelatedElement()).getTest();
            
            if (ret.isEmpty()) {
                return "Test: ...";
            }
            return "Test: " + ret;
        }

        @objid ("2a08eb2c-55b6-11e2-877f-002564c97630")
        @Override
        public IEditableText getEditableText() {
            final Clause aClause = ((Clause) getRelatedElement());
            
            if (aClause == null) {
                return null;
            }
            return new IEditableText() {
            
                            @Override
                            public void setText(String text) {
                                aClause.setTest(text);
                            }
            
                            @Override
                            public String getText() {
                                return aClause.getTest();
                            }
                        };
        }

        @objid ("2a08eb33-55b6-11e2-877f-002564c97630")
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

        @objid ("2a08eb39-55b6-11e2-877f-002564c97630")
        @Override
        public void write(IDiagramWriter out) {
            super.write(out);
            
            // Write version of this Gm if different of 0
            writeMinorVersion(out, "GmTest.", GmClause.MINOR_VERSION);
        }

        @objid ("2a08eb3f-55b6-11e2-877f-002564c97630")
        private void read_0(IDiagramReader in) {
            super.read(in);
        }

        @objid ("2a08eb44-55b6-11e2-877f-002564c97630")
        @Override
        public int getMajorVersion() {
            return MAJOR_VERSION;
        }

    }

}
