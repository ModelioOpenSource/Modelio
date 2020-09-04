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

package org.modelio.diagram.editor.activity.elements.exceptionhandler;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link ExceptionHandler}.
 * 
 * @author sbe
 */
@objid ("2a4a87f9-55b6-11e2-877f-002564c97630")
public class GmExceptionHandler extends GmLink {
    @objid ("2a4a87fd-55b6-11e2-877f-002564c97630")
    private ExceptionHandler element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a4a8802-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a4a8805-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3095b559-58a2-11e2-9574-002564c97630")
    private static final GmExceptionHandlerStyleKeys styleKeyProvider = new GmExceptionHandlerStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * @param diagram The owning diagram
     * @param exceptionhandler The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("2a4a8807-55b6-11e2-877f-002564c97630")
    public GmExceptionHandler(IGmDiagram diagram, ExceptionHandler exceptionhandler, MRef ref) {
        super(diagram, ref);
        this.element = exceptionhandler;
        addExtension(ExtensionLocation.MiddleNW, ROLE_MAIN_LABEL, new GmExceptionHandlerHeader(diagram, ref));
    }

    /**
     * For deserialization only.
     */
    @objid ("2a4a8813-55b6-11e2-877f-002564c97630")
    public GmExceptionHandler() {
        // Nothing to do.
    }

    @objid ("2a4a8816-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("2a4a8820-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return styleKeyProvider.getStyleKeys();
    }

    @objid ("2a4a8829-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (ExceptionHandler) resolveRef(this.getRepresentedRef());
    }

    @objid ("2a4a882f-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getProtectedNode();
    }

    @objid ("2a4c0e9d-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getExceptionInput();
    }

    @objid ("2a4c0ea4-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("2a4c0eab-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("2a4c0eb2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExceptionHandler.", GmExceptionHandler.MINOR_VERSION);
    }

    @objid ("2a4c0eb8-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("fa1bb4bf-4d80-4e80-8761-3478ec11795b")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
