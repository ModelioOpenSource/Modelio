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

package org.modelio.diagram.elements.umlcommon.diagramholder.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNoStyleCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.diagramholder.GmDiagramHolderMigrator;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphical model for a {@link Dependency} with <<related>> stereotype.
 * 
 * @deprecated since Modelio 3.8, this gm is obsolete.
 * @see GmDiagramHolderMigrator
 */
@objid ("813a13cf-1dec-11e2-8cad-001ec947c8cc")
@Deprecated
public class _GmDiagramHolder extends GmNoStyleCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("813a13d4-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("813a13d7-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("813a13d1-1dec-11e2-8cad-001ec947c8cc")
    private Dependency dependency;

    /**
     * Constructor to use only for deserialization.
     */
    @objid ("813a13d9-1dec-11e2-8cad-001ec947c8cc")
    public _GmDiagramHolder() {
    }

    /**
     * Creates a diagram holder.
     * 
     * @param diagram The diagram owning the node
     * @param dependency The represented <<related>> dependency, may be null
     * @param dependencyRef The represented dependency reference, may be null
     */
    @objid ("813a13dc-1dec-11e2-8cad-001ec947c8cc")
    public _GmDiagramHolder(IGmDiagram diagram, final Dependency dependency, final MRef dependencyRef) {
        super(diagram, dependencyRef);
        this.dependency = dependency;
    }

    @objid ("813a13e4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("813a13e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dependency getRepresentedElement() {
        return this.dependency;
    }

    @objid ("813c762a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("813c763c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDiagramHolder.");
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

    @objid ("813c7640-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.dependency != null) {
            MObject target = this.dependency.getDependsOn();
            if (target instanceof AbstractDiagram) {
                if (getChild(new MRef(target)) == null) {
                    getDiagram().unmask(this, target, null);
                }
            }
        }
    }

    @objid ("813c7643-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dependency getRelatedElement() {
        return this.dependency;
    }

//
// @objid ("813c7648-1dec-11e2-8cad-001ec947c8cc")
// @Override
// public void removeEndingLink(final IGmLink gmLink) {
// boolean selfDelete = false;
// if (getRelatedElement() != null && getRelatedElement().equals(gmLink.getRelatedElement())) {
// // the removed link represents the same element (the note) as this gm: delete self as well.
// selfDelete = true;
// }
//
// super.removeEndingLink(gmLink);
//
// if (selfDelete) {
// // the removed link represents the same element (the note) as this gm: delete self as well.
// delete();
// }
// }
    @objid ("813c764d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        if (AbstractDiagram.class.isAssignableFrom(metaclass)) {
            return this;
        }
        return null;
    }

    @objid ("813c7656-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return (AbstractDiagram.class.isAssignableFrom(type));
    }

    @objid ("813c765f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(final MObject el) {
        return el instanceof AbstractDiagram;
    }

    @objid ("813c7666-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementResolved(final MObject el) {
        if (el instanceof Dependency) {
            this.dependency = (Dependency) el;
        }
        
        super.obElementResolved(el);
    }

    @objid ("813c766b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean isValidChild(final GmNodeModel node) {
        final MObject el = node.getRelatedElement();
        
        if (el == null || !isValidElement(el)) {
            return false;
        }
        
        // Cannot unmask if the element is already displayed
        final GmNodeModel sameChild = getChild(node.getRepresentedRef());
        if (sameChild != null && sameChild != node) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the given model element can be and still be displayed here.
     * <p>
     * Check all conditions except the case where it is already unmasked.
     * 
     * @param el The element to unmask
     * @return true if it satisfies all conditions, else false.
     */
    @objid ("813c7672-1dec-11e2-8cad-001ec947c8cc")
    protected boolean isValidElement(final MObject el) {
        // Cannot unmask anything else than an attribute
        if (!(el instanceof AbstractDiagram)) {
            return false;
        }
        
        // Cannot unmask another diagram than the related one
        if (this.dependency != null) {
            final ModelElement dependsOn = this.dependency.getDependsOn();
            return (dependsOn != null && dependsOn.equals(el));
        }
        return false;
    }

    @objid ("813c7679-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDiagramHolder.", _GmDiagramHolder.MINOR_VERSION);
    }

    @objid ("813ed881-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.dependency = (Dependency) resolveRef(getRepresentedRef());
    }

    @objid ("813ed884-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return _GmDiagramHolder.MAJOR_VERSION;
    }

}
