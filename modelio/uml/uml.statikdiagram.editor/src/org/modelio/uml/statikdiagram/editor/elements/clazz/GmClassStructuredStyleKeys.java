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

package org.modelio.uml.statikdiagram.editor.elements.clazz;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierAttributeKeys;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierInnerGroupKeys;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierInternalStructureKeys;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierOperationKeys;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("3445f63b-55b7-11e2-877f-002564c97630")
public final class GmClassStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation model.
     */
    @objid ("a517f261-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("CLASS_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a517f264-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("CLASS_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a517f267-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("CLASS_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a517f26a-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("CLASS_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a517f26d-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("CLASS_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a517f270-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("CLASS_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a517f273-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("CLASS_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a517f276-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("CLASS_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a51978eb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("CLASS_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a51978ee-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("CLASS_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display class visibility.
     */
    @objid ("a51978f1-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = createStyleKey("CLASS_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features
     */
    @objid ("a51978fa-55c2-11e2-9337-002564c97630")
     static final StyleKey FEATURES = createStyleKey("CLASS_FEATURES", MetaKey.VISIBILITYFILTER);

    /**
     * Auto unmask ports.
     */
    @objid ("a5197900-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = createStyleKey("CLASS_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    @objid ("67e31a53-af09-436d-982d-26f215a38c8b")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("CLASS");

    @objid ("186cda56-2cd4-451d-b80b-136be91ca286")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("CLASS");

    @objid ("71bee772-39c7-42eb-b5ae-2969ba45da0e")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("CLASS");

    @objid ("d8b06719-2a44-4960-b98e-1e8937df8a2c")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("CLASS");

    @objid ("f2ae8756-f6c3-4dee-8e23-ce5bffd91714")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return ClassSymbolViewModelProvider.create(style, null);
    }

}
