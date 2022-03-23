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
package org.modelio.uml.statikdiagram.editor.elements.component;

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
 * Component style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("349ffd55-55b7-11e2-877f-002564c97630")
public final class ComponentStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("a644d993-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("COMPONENT_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a644d996-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("COMPONENT_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a644d999-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("COMPONENT_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a644d99c-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("COMPONENT_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a644d99f-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("COMPONENT_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a644d9a2-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("COMPONENT_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a644d9a5-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("COMPONENT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a644d9a8-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWNAME = createStyleKey("COMPONENT_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a644d9ab-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("COMPONENT_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a644d9ae-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("COMPONENT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Filter on features
     */
    @objid ("a644d9b7-55c2-11e2-9337-002564c97630")
    static final StyleKey FEATURES = createStyleKey("COMPONENT_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("a646602b-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWPORTS = createStyleKey("COMPONENT_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    /**
     * Display visibility.
     */
    @objid ("da901d79-9fd2-4b48-91bb-005800360fef")
    static final StyleKey SHOWVISIBILITY = createStyleKey("COMPONENT_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    @objid ("d65b7fa4-b1fa-472a-8d1f-a21731c94ce9")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("COMPONENT");

    @objid ("b77d85a6-8a10-4158-b4e7-8e80af6c9f17")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("COMPONENT");

    @objid ("7d8cc415-5357-4cc0-8635-8808533cd227")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("COMPONENT");

    @objid ("19948345-391a-4847-8ddd-64bc5c02db2f")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("COMPONENT");

    @objid ("6f2265f5-082e-4388-9092-b74839942f69")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return ComponentSymbolViewModel.create(style, null);
    }

}
