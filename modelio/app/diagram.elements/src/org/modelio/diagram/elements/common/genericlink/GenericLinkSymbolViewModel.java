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

package org.modelio.diagram.elements.common.genericlink;

import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;

/**
 * Helper class building a {@link ISymbolViewModel} for {@link GmGenericLink}.
 */
@objid ("f69f025c-5b4d-4709-85ac-4c2600381ad4")
class GenericLinkSymbolViewModel {
    @objid ("62f87c59-e510-4230-8ed4-8195e204278a")
    public static ISymbolViewModel create(IStyle editedStyle, GmGenericLink input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(input.getRelatedMClass().getName());
        b
                .add(b.createStyleChooserItem())
                .add(b.createLabelItem(DiagramElements.I18N.getString("symbol.GenericLink.group.line"))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.CONNECTIONROUTER))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.LINERADIUS)
                                .filter(GmGenericLinkStyleKeys.CONNECTIONROUTER, router -> !Objects.equals(router, ConnectionRouterId.DIRECT)))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.DRAWLINEBRIDGES)))
                .add(b.createLabelItem(DiagramElements.I18N.getString("symbol.GenericLink.group.pen"))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.LINEWIDTH))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.LINECOLOR))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.TEXTCOLOR)
                                .filter(GmGenericLinkStyleKeys.SHOWLABEL, showLabel -> Objects.equals(showLabel, Boolean.TRUE)))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.FONT)))
                .add(b.createLabelItem(DiagramElements.I18N.getString("symbol.GenericLink.group.label"))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.SHOWLABEL))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.SHOWSTEREOTYPES)
                                .filter(GmGenericLinkStyleKeys.SHOWLABEL, showLabel -> Objects.equals(showLabel, Boolean.TRUE)))
                        .add(b.createStyleItem(GmGenericLinkStyleKeys.SHOWTAGS)
                                .filter(GmGenericLinkStyleKeys.SHOWLABEL, showLabel -> Objects.equals(showLabel, Boolean.TRUE))));
        return b.build(editedStyle, input);
    }

}
