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

package org.modelio.diagram.editor.statik.elements.datatype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierAttributeKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierInternalStructureKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierOperationKeys;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * GmClass style keys for the standard structured mode.
 * 
 * @author cmarin
 */
@objid ("34b86750-55b7-11e2-877f-002564c97630")
public final class DataTypeStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Representation model.
     */
    @objid ("a5661249-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = createStyleKey("DATATYPE_REPRES_MODE", MetaKey.REPMODE);

    /**
     * Fill color.
     */
    @objid ("a566395b-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("DATATYPE_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * Fill mode.
     */
    @objid ("a566395e-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = createStyleKey("DATATYPE_FILLMODE", MetaKey.FILLMODE);

    /**
     * Lines color.
     */
    @objid ("a566606b-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("DATATYPE_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Lines width.
     */
    @objid ("a5668779-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("DATATYPE_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Text font.
     */
    @objid ("a566877c-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("DATATYPE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("a566ae89-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("DATATYPE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a566ae8c-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = createStyleKey("DATATYPE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Stereotype display mode.
     */
    @objid ("a566d59b-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("DATATYPE_SHOWSTEREOTYPES",
                                                                  MetaKey.SHOWSTEREOTYPES);

    /**
     * Show tagged values.
     */
    @objid ("a566d59e-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("DATATYPE_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display visibility.
     */
    @objid ("a566fcab-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWVISIBILITY = createStyleKey("DATATYPE_SHOWVISIBILITY",
                                                                 MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features
     */
    @objid ("a5674acb-55c2-11e2-9337-002564c97630")
    public static final StyleKey FEATURES = createStyleKey("DATATYPE_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("d9780aad-5023-4239-9c61-49ff71d68756")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("DATATYPE");

    @objid ("71968f4d-e760-4b8f-a12e-e8e8d4dd19b1")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("DATATYPE");

    @objid ("3a5ad7a1-8927-46bb-ab9d-be339aa9666a")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("DATATYPE");

    @objid ("37f5ce59-ab5f-4518-84d0-6c0ba8f38ac1")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return DataTypeSymbolViewModelProvider.create(style, null);
    }

}
