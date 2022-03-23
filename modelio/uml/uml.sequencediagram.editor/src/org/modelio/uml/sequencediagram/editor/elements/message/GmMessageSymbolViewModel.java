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
package org.modelio.uml.sequencediagram.editor.elements.message;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.sequencediagram.editor.plugin.DiagramEditorSequence;

/**
 * Helper class building a {@link ISymbolViewModel} for {@link GmMessage}.
 */
@objid ("9077d1cd-40ce-4dc9-bb4a-d435e894b3dc")
class GmMessageSymbolViewModel {
    @objid ("d146ef0b-5e76-4414-bdd4-2f13c2f6ec6d")
    public static ISymbolViewModel create(IStyle editedStyle, GmMessage input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(input.getRelatedMClass().getName());
        b
        .add(b.createStyleChooserItem()) // Router can't be modified by the user
        .add(b.createLabelItem(DiagramEditorSequence.I18N.getString("symbol.Message.group.pen"))
                .add(b.createStyleItem(GmMessageStyleKeys.LINEWIDTH))
                .add(b.createStyleItem(GmMessageStyleKeys.LINECOLOR))
                .add(b.createStyleItem(GmMessageStyleKeys.TEXTCOLOR))
                .add(b.createStyleItem(GmMessageStyleKeys.FONT))
                .add(b.createLabelItem(DiagramEditorSequence.I18N.getString("symbol.Message.group.label"))
                        .add(b.createStyleItem(GmMessageStyleKeys.SHOWSTEREOTYPES))
                        .add(b.createStyleItem(GmMessageStyleKeys.SHOWTAGS))
                        .add(b.createStyleItem(GmMessageStyleKeys.SHOWSEQUENCE)))
                .add(b.createLabelItem(DiagramEditorSequence.I18N.getString("symbol.Message.group.informationflow"))
                        .add(b.createStyleItem(GmMessageStyleKeys.SHOWFLOWS))
                        .setNextChildrenFilter(b.filterEquals(GmMessageStyleKeys.SHOWFLOWS, true))
                        .add(b.createStyleItem(GmMessageStyleKeys.InfoFlows.FLOWTEXTCOLOR))
                        .add(b.createStyleItem(GmMessageStyleKeys.InfoFlows.FLOWFONT))
                        .add(b.createStyleItem(GmMessageStyleKeys.InfoFlows.FLOWSHOWSTEREOTYPES))
                        .add(b.createStyleItem(GmMessageStyleKeys.InfoFlows.FLOWSHOWTAGS)))
                );
        return b.build(editedStyle, input);
    }

}
