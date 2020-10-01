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

package org.modelio.diagram.elements.common.genericnode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * Helper class building a {@link ISymbolViewModel} for {@link GmGenericNode}.
 */
@objid ("0e0a86d9-b019-421e-859a-d65c63deba9b")
class GenericNodeSymbolViewModel {
    @objid ("b4b82dc4-401b-4863-96c8-54cf92694039")
    public static ISymbolViewModel create(IStyle editedStyle, GmGenericNode input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(input.getRelatedMClass().getName());
        b
                .add(b.createStyleChooserItem())
                .add(b.createPenAndBrushSection(GmGenericNodeStyleKeys.LINEWIDTH,
                        GmGenericNodeStyleKeys.LINECOLOR,
                        GmGenericNodeStyleKeys.FILLMODE,
                        GmGenericNodeStyleKeys.FILLCOLOR,
                        GmGenericNodeStyleKeys.TEXTCOLOR,
                        GmGenericNodeStyleKeys.FONT))
                .add(b.createLabelItem(DiagramElements.I18N.getString("symbol.GenericNode.group.header"))
                        .add(b.createStyleItem(GmGenericNodeStyleKeys.SHOWSTEREOTYPES))
                        .add(b.createStyleItem(GmGenericNodeStyleKeys.SHOWTAGS)));
        return b.build(editedStyle, input);
    }

}
