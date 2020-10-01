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
@objid ("792a0d82-2be1-4c74-9ebf-1795219a437c")
public class GmArtifactUserImageStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("46ef0b4c-a2e2-41d3-886d-ef3ef0c807d6")
    public static final StyleKey REPMODE = createStyleKey("ARTIFACT_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("d8187fbf-6b4e-487a-a0f0-094f96fbd1f6")
    public static final StyleKey FONT = createStyleKey("ARTIFACT_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("7633f810-1585-44a6-b449-1c29890d3a74")
    public static final StyleKey TEXTCOLOR = createStyleKey("ARTIFACT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("3d5bd45e-33ef-48ea-880f-88db1ce41d93")
    public static final StyleKey SHOWNAME = createStyleKey("ARTIFACT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("2adf2422-52a1-4104-920e-e0b2ed6066b7")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ARTIFACT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("f5c106a7-72ca-4412-818c-08f196069452")
    public static final StyleKey SHOWTAGS = createStyleKey("ARTIFACT_SHOWTAGS", MetaKey.SHOWTAGS);

}
