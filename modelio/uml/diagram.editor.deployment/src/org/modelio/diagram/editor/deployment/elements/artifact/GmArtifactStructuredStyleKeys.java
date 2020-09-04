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

package org.modelio.diagram.editor.deployment.elements.artifact;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.deployment.style.DeploymentAbstractStyleKeyProvider;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierAttributeKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierInnerGroupKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierInternalStructureKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierOperationKeys;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * This class provides the StyleKey constants for a GmArtifact when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("971ef1bd-55b6-11e2-877f-002564c97630")
public class GmArtifactStructuredStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("1ce0bb1b-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("ARTIFACT_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("1ce0bb1e-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("ARTIFACT_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("1ce0bb21-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("ARTIFACT_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("1ce0bb24-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("ARTIFACT_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("1ce0bb27-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("ARTIFACT_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("1ce0bb2a-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("ARTIFACT_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1ce0bb2d-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("ARTIFACT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("1ce0bb30-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("ARTIFACT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("1ce0bb33-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("ARTIFACT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("1ce0bb36-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("ARTIFACT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Filter on features
     */
    @objid ("1ce241ac-55c2-11e2-9337-002564c97630")
     static final StyleKey FEATURES = createStyleKey("ARTIFACT_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("1ce241b2-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = createStyleKey("ARTIFACT_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    /**
     * Display visibility.
     */
    @objid ("792c2917-ad2f-4863-bd74-c53df05695b8")
     static final StyleKey SHOWVISIBILITY = createStyleKey("ARTIFACT_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    @objid ("0e7de8a2-6e4a-4bb1-ab64-104a9b69557e")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("ARTIFACT");

    @objid ("768f3c3a-ff8f-43e5-b38e-a99f088fd409")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("ARTIFACT");

    @objid ("ba8e95cf-0c96-4c7f-8f60-cb0aae0fe857")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("ARTIFACT");

    @objid ("c572b808-3e0b-406c-88dc-ef51f68acd1b")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("ARTIFACT");

    @objid ("ca597442-ffbf-427a-8732-814b65305512")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return ArtifactSymbolViewModel.create(style, null);
    }

}
