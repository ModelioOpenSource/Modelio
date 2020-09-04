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

package org.modelio.diagram.editor.deployment.elements.node;

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
 * This class provides the StyleKey constants for a GmNode when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("97420a19-55b6-11e2-877f-002564c97630")
public class GmNodeStructuredStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("1cd60ccb-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("NODE_REPMODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("1cd60cce-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("NODE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("1cd60cd1-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("NODE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Line color.
     */
    @objid ("1cd60cd4-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("NODE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width.
     */
    @objid ("1cd7934b-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("NODE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("1cd7934e-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("NODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("1cd79351-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("NODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode : {@link MetaKey#SHOWNAME}.
     */
    @objid ("1cd79354-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("NODE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("1cd79357-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("NODE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("1cd7935a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("NODE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Filter on features
     */
    @objid ("1cd79363-55c2-11e2-9337-002564c97630")
     static final StyleKey FEATURES = createStyleKey("NODE_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("1cd79369-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = createStyleKey("NODE_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    /**
     * Display visibility : {@link MetaKey#SHOWVISIBILITY}.
     */
    @objid ("3ec9fcf7-6c3b-4564-888b-12fe4caa001a")
     static final StyleKey SHOWVISIBILITY = createStyleKey("NODE_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    @objid ("c83a426b-573c-4c09-bcd3-121686c431bf")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("NODE");

    @objid ("615d06d8-15f6-421e-b7c9-5a9e73dfe078")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("NODE");

    @objid ("43c85f14-c9c9-4713-a05d-f96497915916")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("NODE");

    @objid ("fb4a153d-7ad6-4336-a168-619953bdc037")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("NODE");

    @objid ("69f8b474-b7c3-4a33-b057-6334a5cbc117")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return NodeSymbolViewModel.create(style, null);
    }

}
