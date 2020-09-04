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

package org.modelio.diagram.elements.common.label.modelelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Header label that shows the edge name, tags and stereotypes.
 * 
 * @deprecated Use or derive from {@link GmDefaultModelElementLabel} instead.
 * To get flat labels redefine {@link #isFlat()} on the model or the edit part.
 */
@objid ("7e94f7f6-1dec-11e2-8cad-001ec947c8cc")
@Deprecated
public final class GmDefaultFlatHeader extends GmDefaultModelElementLabel {
    /**
     * Last major version of this Gm.
     * <li> 0 : previous version
     * <li> 1 : current and last version. No instance with this version should exist.
     */
    @objid ("7e94f7fb-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 1;

    /**
     * Create an header label
     * @param diagram the diagram.
     * @param relatedRef reference to the diagram.
     */
    @objid ("7e94f7fd-1dec-11e2-8cad-001ec947c8cc")
    public GmDefaultFlatHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("7e975a51-1dec-11e2-8cad-001ec947c8cc")
    public GmDefaultFlatHeader() {
        // serialization
    }

    @objid ("7e975a80-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Migrate this instance to a {@link GmDefaultModelElementHeader}.
     * @return a {@link GmDefaultModelElementHeader}.
     */
    @objid ("86ba9f04-41aa-4deb-9901-9f5b969ede86")
    public GmDefaultModelElementHeader migrate() {
        GmDefaultModelElementHeader imageModeHeader = new GmDefaultModelElementHeader(getDiagram(), getRepresentedRef());
        
        imageModeHeader.setRoleInComposition(getRoleInComposition());
        imageModeHeader.setLayoutData(getLayoutData());
        imageModeHeader.setShowLabel(isShowLabel());
        imageModeHeader.setShowMetaclassIcon(isShowMetaclassIcon());
        imageModeHeader.setShowMetaclassKeyword(isShowMetaclassKeyword());
        imageModeHeader.setStackedStereotypes(isDisplayStereotypesAsStack());
        return imageModeHeader;
    }

}
