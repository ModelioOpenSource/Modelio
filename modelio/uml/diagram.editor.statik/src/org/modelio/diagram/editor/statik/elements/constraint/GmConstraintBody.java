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

package org.modelio.diagram.editor.statik.elements.constraint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Main class of the central node for a constraint.
 * 
 * @author fpoyer
 */
@objid ("8118b2fd-1dec-11e2-8cad-001ec947c8cc")
public class GmConstraintBody extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("811b153d-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("811b1540-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("8118b2ff-1dec-11e2-8cad-001ec947c8cc")
    private Constraint constraint;

    @objid ("811b153b-1dec-11e2-8cad-001ec947c8cc")
     static final GmConstraintStyleKeys KEYS = new GmConstraintStyleKeys();

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("811b1542-1dec-11e2-8cad-001ec947c8cc")
    public GmConstraintBody() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param constraint the represented constraint. May be null.
     * @param relatedRef a reference to the represented constraint. Must Not be null.
     */
    @objid ("811b1545-1dec-11e2-8cad-001ec947c8cc")
    public GmConstraintBody(final IGmDiagram diagram, final Constraint constraint, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.constraint = constraint;
        GmConstraintBodyLabel header = new GmConstraintBodyLabel(diagram, relatedRef);
        addChild(header);
    }

    @objid ("811b154e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("811b1557-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return false;
    }

    @objid ("811b1560-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(final MObject el) {
        return false;
    }

    @objid ("811d7795-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("811d779a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("811d77a1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("811d77a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.constraint != null ) {
            List<UmlModelElement> constrainedElements = this.constraint.getConstrainedElement();
            // Start by hunting unnecessary links
            List<IGmLink> linksToDelete = new ArrayList<>();
            for (IGmLink link : getStartingLinks()) {
                if (!constrainedElements.contains(link.getToElement())) {
                    linksToDelete.add(link);
                }
            }
            for (IGmLink link : linksToDelete) {
                link.delete();
            }
        
            // Now check if we need to create links to constrained elements ALREADY unmasked in diagram.
            for (UmlModelElement constrained : constrainedElements) {
                boolean linkFound = false;
                for (IGmLink link : getStartingLinks()) {
                    if (constrained.equals(link.getToElement())) {
                        linkFound = true;
                        break;
                    }
                }
                if (!linkFound) {
                    IGmDiagram diagram = getDiagram();
                    if(diagram != null) {
                        Collection<GmModel> models = diagram.getAllGMRelatedTo(new MRef(constrained));
                        for (GmModel model : models) {
                            if (model instanceof GmLink || ((GmNodeModel) model).isVisible()) {
                                // fire property change to force creation of missing link by edit part.
                                firePropertyChange(PROPERTY_LINK_SOURCE, null, constrained);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @objid ("811d77ab-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Constraint getRelatedElement() {
        return this.constraint;
    }

    @objid ("811d77b0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Constraint getRepresentedElement() {
        return this.constraint;
    }

    @objid ("811d77b5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConstraintBody.");
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

    @objid ("811d77ba-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConstraintBody.", GmConstraintBody.MINOR_VERSION);
    }

    @objid ("811d77be-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.constraint = (Constraint) resolveRef(getRepresentedRef());
    }

    @objid ("811d77c2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("6e3c9faa-3d22-4e13-96bb-f2bd66968595")
    @Override
    public void removeStartingLink(final IGmLink gmLink) {
        boolean selfDelete = false;
        if (this.constraint != null && this.constraint.equals(gmLink.getRelatedElement())) {
            // the removed link represents the same element (the constraint) as this gm: delete self as well.
            selfDelete = this.constraint.getConstrainedElement().contains(gmLink.getToElement());
        }
        super.removeStartingLink(gmLink);
        if (selfDelete) {
            // the removed link represents the same element (the constraint) as this gm: delete self as well.
            delete();
        }
    }

}
