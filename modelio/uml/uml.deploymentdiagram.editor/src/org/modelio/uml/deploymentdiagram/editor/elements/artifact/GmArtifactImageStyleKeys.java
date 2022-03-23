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
package org.modelio.uml.deploymentdiagram.editor.elements.artifact;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.deploymentdiagram.editor.style.DeploymentAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmArtifact when its representation mode is RepresentationMode.IMAGE
 */
@objid ("97175091-55b6-11e2-877f-002564c97630")
public class GmArtifactImageStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("1ccff231-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("ARTIFACT_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("1ccff234-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ARTIFACT_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1ccff237-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ARTIFACT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("1ccff23a-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("ARTIFACT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("1ccff23d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ARTIFACT_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("1ccff240-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ARTIFACT_SHOWTAGS", MetaKey.SHOWTAGS);

}
