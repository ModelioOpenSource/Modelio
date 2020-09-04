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

package org.modelio.diagram.elements.umlcommon.dependency;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link Dependency}.
 * 
 * @author sbe
 */
@objid ("81249eb1-1dec-11e2-8cad-001ec947c8cc")
public class GmDependency extends GmLink {
    /**
     * Current version of this Gm.
     */
    @objid ("81249eb6-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 1;

    @objid ("81249eb9-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 1;

    @objid ("81249eb3-1dec-11e2-8cad-001ec947c8cc")
    private Dependency dependency;

    /**
     * Style keys for {@link GmDependency}.
     */
    @objid ("81249eb4-1dec-11e2-8cad-001ec947c8cc")
    public static final GmDependencyStyleKeys styleKeyProvider = new GmDependencyStyleKeys("DEPENDENCY");

    /**
     * Initialize a control flow graphic model.
     * @param diagram The owning diagram
     * @param dependency The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("81249ebb-1dec-11e2-8cad-001ec947c8cc")
    public GmDependency(IGmDiagram diagram, Dependency dependency, MRef ref) {
        super(diagram, ref);
        this.dependency = dependency;
        
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, new GmDefaultModelElementLabel(diagram, ref));
    }

    @objid ("81270101-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmDependency.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("81270107-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmDependency.styleKeyProvider.getStyleKeys();
    }

    /**
     * For deserialization only.
     */
    @objid ("8127010e-1dec-11e2-8cad-001ec947c8cc")
    public GmDependency() {
    }

    @objid ("81270111-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        
        int readVersion = readMinorVersion(in, "GmDependency.");
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

    @objid ("81270115-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getFromElement() {
        // Make sure there is no error when encountering a ghost dependency...
        return this.dependency != null ? this.dependency.getImpacted() : null;
    }

    @objid ("8127011a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getToElement() {
        // Make sure there is no error when encountering a ghost dependency...
        return this.dependency != null ? this.dependency.getDependsOn() : null;
    }

    @objid ("8127011f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRepresentedElement() {
        return this.dependency;
    }

    @objid ("81270124-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("81270129-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        writeMinorVersion(out, "GmDependency.", GmDependency.MINOR_VERSION);
    }

    @objid ("8127012d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmDependency.MAJOR_VERSION;
    }

    @objid ("b3b9cf6c-fcbf-4e8e-a4a1-bdc11bb84824")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

    /**
     * Modelio 3.7.0 -> Modelio 3.7.1 migration.
     * Deal with default router change.
     */
    @objid ("b89bffc6-a524-460a-aeb6-92df2be25d49")
    private void read_0(IDiagramReader in) {
        read_1(in);
        
        IStyle style = getPersistedStyle();
        StyleKey styleKey = GmDependency.styleKeyProvider.getStyleKey(MetaKey.CONNECTIONROUTER);
        if (styleKey != null && !style.isLocal(styleKey)) {
            // Before 3.7.1, default value was "orthogonal router"
            style.setProperty(styleKey, ConnectionRouterId.ORTHOGONAL);
        }
    }

    /**
     * Standard read initializing fields.
     */
    @objid ("43200346-f673-4176-8e8a-529f308ef98b")
    private void read_1(@SuppressWarnings ("unused") IDiagramReader in) {
        this.dependency = (Dependency) resolveRef(getRepresentedRef());
    }

}
