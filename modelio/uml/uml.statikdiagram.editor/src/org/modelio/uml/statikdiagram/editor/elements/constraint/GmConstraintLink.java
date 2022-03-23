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
package org.modelio.uml.statikdiagram.editor.elements.constraint;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Class for the "link" part of a constraint, from its "body" aka central node to the constrained elements.
 * 
 * @author fpoyer
 */
@objid ("811fda00-1dec-11e2-8cad-001ec947c8cc")
public class GmConstraintLink extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("811fda04-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("811fda07-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("811fda02-1dec-11e2-8cad-001ec947c8cc")
    private MRef constrainedElementRef;

    @objid ("811fda03-1dec-11e2-8cad-001ec947c8cc")
    private ModelElement constrainedElement;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("811fda09-1dec-11e2-8cad-001ec947c8cc")
    public  GmConstraintLink() {
        super();
    }

    /**
     * C'tor.
     * @param diagram diagram in which this gm is created.
     * @param relatedRef a reference to the represented constraint. Must not be null.
     * @param constrainedElement the constrained element. may be null.
     * @param constrainedRef a reference to the constrained element. May NOT be null.
     */
    @objid ("811fda0c-1dec-11e2-8cad-001ec947c8cc")
    public  GmConstraintLink(final IGmDiagram diagram, final MRef relatedRef, final ModelElement constrainedElement, final MRef constrainedRef) {
        super(diagram, relatedRef);
        this.constrainedElement = constrainedElement;
        this.constrainedElementRef = constrainedRef;
        
    }

    @objid ("811fda17-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getFromElement() {
        return getRelatedElement();
    }

    @objid ("811fda1c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getToElement() {
        return this.constrainedElement;
    }

    @objid ("811fda21-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return GmConstraintBody.KEYS.getStyleKey(metakey);
    }

    @objid ("811fda28-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmConstraintBody.KEYS.getStyleKeys();
    }

    @objid ("81223c4e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Constraint getRelatedElement() {
        if (getFrom() != null) {
            return (Constraint) getFrom().getRelatedElement();
        } else {
            return null;
        }
        
    }

    @objid ("81223c52-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void readLink(final IDiagramReader in) {
        super.readLink(in);
        this.constrainedElementRef = (MRef) in.readProperty("constrainedElementRef");
        this.constrainedElement = (ModelElement) resolveRef(this.constrainedElementRef);
        if (getFrom() instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) getFrom());
        }
        
    }

    @objid ("81223c57-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        out.writeProperty("constrainedElementRef", this.constrainedElementRef);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConstraintLink.", GmConstraintLink.MINOR_VERSION);
        
    }

    @objid ("81223c5c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setFrom(final IGmLinkable from) {
        super.setFrom(from);
        if (from instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) from);
        }
        
    }

    /**
     * Updates the proxy style to point to the given node style.
     * @param ref the reference node, may be null.
     */
    @objid ("81223c61-1dec-11e2-8cad-001ec947c8cc")
    private void refreshStyle(final GmAbstractObject ref) {
        // Modify the style
        if (ref != null) {
            getPersistedStyle().setCascadedStyle(ref.getPersistedStyle());
        } else {
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
        }
        
    }

    @objid ("81223c66-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("81223c6d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("6ecb608a-3f65-4b35-87ae-989aaafae168")
    @Override
    protected void read_GmLinkV0_roles() {
        // nothing
    }

}
