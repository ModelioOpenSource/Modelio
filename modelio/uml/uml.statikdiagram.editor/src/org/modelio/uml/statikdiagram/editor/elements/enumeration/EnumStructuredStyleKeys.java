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
package org.modelio.uml.statikdiagram.editor.elements.enumeration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierAttributeKeys;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierInnerGroupKeys;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierOperationKeys;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("34d257e7-55b7-11e2-877f-002564c97630")
public final class EnumStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation model.
     */
    @objid ("a65f1868-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("ENUM_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a65f186b-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("ENUM_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a65f186e-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("ENUM_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a65f1871-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("ENUM_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a65f1874-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("ENUM_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a65f1877-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("ENUM_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a6609eeb-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("ENUM_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a6609eee-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("ENUM_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a6609ef1-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ENUM_SHOWSTEREOTYPES",
                                                                      MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a6609ef4-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("ENUM_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display class visibility.
     */
    @objid ("a6609ef7-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = createStyleKey("ENUM_SHOWVISIBILITY",
                                                                     MetaKey.SHOWVISIBILITY);

    /**
     * Display enumeration literals.
     */
    @objid ("a6609f00-55c2-11e2-9337-002564c97630")
    public static final StyleKey LITGROUPVISIBLE = createStyleKey("ENUM_LIT_GROUPVISIBLE", Boolean.class);

    /**
     * Filter on features
     */
    @objid ("a6609f03-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("ENUM_FEATURES_FILTER", MetaKey.VISIBILITYFILTER);

    @objid ("6baeaa5b-364f-41a9-a4e5-5bdae8489265")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("ENUM");

    @objid ("227b9529-f736-400b-a04a-1e04160cad33")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("ENUM");

    @objid ("eca2a769-9fa1-456e-a17e-ec4fd77b91d4")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("ENUM");

    @objid ("1ff2fbfb-6895-42fa-99f1-1cf5ad849e68")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return EnumSymbolViewModel.create(style, null);
    }

    /**
     * Attributes style keys.
     * 
     * @author cmarin
     */
    @objid ("34d56503-55b7-11e2-877f-002564c97630")
    @SuppressWarnings("hiding")
    public static final class Litteral extends StaticAbstractStyleKeyProvider {
        /**
         * Attributes text color.
         */
        @objid ("a6609f09-55c2-11e2-9337-002564c97630")
        public static final StyleKey TEXTCOLOR = createStyleKey("ENUM_LIT_TEXTCOLOR", Color.class);

        /**
         * Attributes font.
         */
        @objid ("a6609f0c-55c2-11e2-9337-002564c97630")
        public static final StyleKey FONT = createStyleKey("ENUM_LIT_FONT", Font.class);

        /**
         * Attributes stereotype display mode.
         */
        @objid ("a6609f0f-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWSTEREOTYPES = createStyleKey("ENUM_LIT_SHOWSTEREOTYPES",
                                                                              MetaKey.SHOWSTEREOTYPES);

        /**
         * Display attributes tagged values.
         */
        @objid ("a6609f12-55c2-11e2-9337-002564c97630")
        public static final StyleKey SHOWTAGS = createStyleKey("ENUM_LIT_SHOWTAGS", MetaKey.SHOWTAGS);

    }

}
