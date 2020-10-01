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

package org.modelio.uml.statikdiagram.editor.elements.datatype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * GmClass style keys for the simple representation mode.
 * 
 * @author cmarin
 */
@objid ("34b8671d-55b7-11e2-877f-002564c97630")
public class DataTypeSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a655f098-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = DataTypeStructuredStyleKeys.REPMODE;

    @objid ("a655f09a-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = DataTypeStructuredStyleKeys.FILLCOLOR;

    @objid ("a655f09c-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = DataTypeStructuredStyleKeys.FILLMODE;

    @objid ("a655f09e-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = DataTypeStructuredStyleKeys.LINECOLOR;

    @objid ("a655f0a0-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = DataTypeStructuredStyleKeys.LINEWIDTH;

    @objid ("a655f0a2-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = DataTypeStructuredStyleKeys.FONT;

    @objid ("a655f0a4-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = DataTypeStructuredStyleKeys.TEXTCOLOR;

    @objid ("a655f0a6-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = DataTypeStructuredStyleKeys.SHOWNAME;

    @objid ("a655f0a8-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = DataTypeStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a655f0aa-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = DataTypeStructuredStyleKeys.SHOWTAGS;

    @objid ("a657772a-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = DataTypeStructuredStyleKeys.SHOWVISIBILITY;

    /**
     * No shared style edition for this provider, the {@link DataTypeStructuredStyleKeys} symbol view model has everything.
     */
    @objid ("c33ae5e3-dd50-420c-97e4-702e59f6c829")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return null;
    }

}
