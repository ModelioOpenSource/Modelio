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
package org.modelio.uml.statikdiagram.editor.elements.interfaze;

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
@objid ("357ec487-55b7-11e2-877f-002564c97630")
public final class InterfaceStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation model.
     */
    @objid ("a5aabc89-55c2-11e2-9337-002564c97630")
    static final StyleKey REPMODE = createStyleKey("INTERFACE_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a5aabc8c-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = createStyleKey("INTERFACE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a5aabc8f-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLMODE = createStyleKey("INTERFACE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a5aabc92-55c2-11e2-9337-002564c97630")
    static final StyleKey LINECOLOR = createStyleKey("INTERFACE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a5aabc95-55c2-11e2-9337-002564c97630")
    static final StyleKey LINEWIDTH = createStyleKey("INTERFACE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a5ac4309-55c2-11e2-9337-002564c97630")
    static final StyleKey FONT = createStyleKey("INTERFACE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a5ac430c-55c2-11e2-9337-002564c97630")
    static final StyleKey TEXTCOLOR = createStyleKey("INTERFACE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a5ac430f-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWNAME = createStyleKey("INTERFACE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a5ac4312-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("INTERFACE_SHOWSTEREOTYPES",
                                                               MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a5ac4315-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWTAGS = createStyleKey("INTERFACE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display visibility.
     */
    @objid ("a5ac4318-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWVISIBILITY = createStyleKey("INTERFACE_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features
     */
    @objid ("a5ac4321-55c2-11e2-9337-002564c97630")
    static final StyleKey FEATURES = createStyleKey("INTERFACE_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("a5ac4327-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOWPORTS = createStyleKey("INTERFACE_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    @objid ("d6c95933-02d5-4c69-a6a6-bdfd0c39a641")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("INTERFACE");

    @objid ("405c4ff3-7221-47f7-b489-99fb25091ec9")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("INTERFACE");

    @objid ("831a56d1-7d65-45f1-9cc1-9d755c534ebb")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("INTERFACE");

    @objid ("f9b757fd-d64a-44fd-8a5b-0295fa856461")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("INTERFACE");

    @objid ("56c37cb0-f9ce-4c4c-906d-0091f3009489")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return InterfaceSymbolViewModelProvider.create(style, null);
    }

}
