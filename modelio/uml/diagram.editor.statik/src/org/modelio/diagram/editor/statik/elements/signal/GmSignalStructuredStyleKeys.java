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

package org.modelio.diagram.editor.statik.elements.signal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierAttributeKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierInnerGroupKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierInternalStructureKeys;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierOperationKeys;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * This class provides the StyleKey constants for a GmSendSignalAction when its representation mode is
 * RepresentationMode.STRUCTURED
 */
@objid ("369600bf-55b7-11e2-877f-002564c97630")
public class GmSignalStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a53676c9-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("SIGNAL_REPMODE", MetaKey.REPMODE);

    @objid ("a53676cb-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("SIGNAL_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a53676cd-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("SIGNAL_FILLMODE", MetaKey.FILLMODE);

    @objid ("a5369dda-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("SIGNAL_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a5369ddc-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("SIGNAL_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a5369dde-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("SIGNAL_FONT", MetaKey.FONT);

    @objid ("a536c4ea-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("SIGNAL_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Name display mode: none, simple, qualified, ...
     */
    @objid ("a536c4ec-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("SIGNAL_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("a536ebfa-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("SIGNAL_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("a5371309-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("SIGNAL_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Display class visibility.
     */
    @objid ("a537130b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = createStyleKey("SIGNAL_SHOWVISIBILITY", MetaKey.SHOWVISIBILITY);

    /**
     * Filter on features by visibility
     */
    @objid ("a5376129-55c2-11e2-9337-002564c97630")
     static final StyleKey FEATURES = createStyleKey("SIGNAL_FEATURES", MetaKey.VISIBILITYFILTER);

    @objid ("a537883b-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWPORTS = createStyleKey("SIGNAL_SHOWPORTS", MetaKey.AUTOSHOWPORTS);

    @objid ("e0077721-dd0c-46c2-918a-946c83d21e4c")
    public static final ClassifierAttributeKeys Attribute = new ClassifierAttributeKeys("SIGNAL");

    @objid ("509dc393-c2de-4a7e-a927-2122e489669f")
    public static final ClassifierOperationKeys Operation = new ClassifierOperationKeys("SIGNAL");

    @objid ("df660571-cce7-4523-989a-d20914d27e11")
    public static final ClassifierInnerGroupKeys Inner = new ClassifierInnerGroupKeys("SIGNAL");

    @objid ("6ad1e484-1aee-4344-a4b1-3ef9d6673872")
    public static final ClassifierInternalStructureKeys InternalStructure = new ClassifierInternalStructureKeys("SIGNAL");

    @objid ("1e5f7e2e-cd51-4000-a168-445538b04e14")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return SignalSymbolViewModel.create(style, null);
    }

}
