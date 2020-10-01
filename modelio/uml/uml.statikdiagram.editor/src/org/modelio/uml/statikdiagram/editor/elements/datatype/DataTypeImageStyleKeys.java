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
 * GmDatatype style keys for the stereotype image representation mode.
 * 
 * @author cmarin
 */
@objid ("34b6e07a-55b7-11e2-877f-002564c97630")
public class DataTypeImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a655f08a-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = DataTypeStructuredStyleKeys.REPMODE;

    @objid ("a655f08c-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = DataTypeStructuredStyleKeys.FONT;

    @objid ("a655f08e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = DataTypeStructuredStyleKeys.TEXTCOLOR;

    @objid ("a655f090-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = DataTypeStructuredStyleKeys.SHOWNAME;

    @objid ("a655f092-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = DataTypeStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a655f094-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = DataTypeStructuredStyleKeys.SHOWTAGS;

    @objid ("a655f096-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWVISIBILITY = DataTypeStructuredStyleKeys.SHOWVISIBILITY;

    /**
     * No shared style edition for this provider, the {@link DataTypeStructuredStyleKeys} symbol view model has everything.
     */
    @objid ("4cdd1750-5d10-4d06-b498-be1ddc115d45")
    @Override
    public ISymbolViewModel getSymbolViewModel(IStyle style) {
        return null;
    }

}
