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

package org.modelio.diagram.editor.statik.elements.enumeration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.Inner;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.REPMODE;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.statik.elements.enumeration.EnumStructuredStyleKeys.TEXTCOLOR;

@objid ("7beaad5a-c78e-487a-b74a-29fe1657c410")
class EnumSymbolViewModel extends ClassifierSymbolModelBuilder {
    @objid ("8bf00397-57f1-45b7-bf73-013a5b9b508d")
    public static ISymbolViewModel create(IStyle editedStyle, GmEnum input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.Enum.label"));
        
        b        
        .add(b.createStyleChooserItem())
        .add(b.createStyleItem(REPMODE))
        .add(b.createStyleItem(FEATURES)
                .withLabel(DiagramEditorStatik.I18N.getString("symbol.Classifier.automaticContent"))
                .filter(b.structuredModeFilter)
                .filter((style, context) -> style.getBoolean(Attribute.ATTGROUPVISIBLE) 
                        || style.getBoolean(Operation.OPERATIONGROUPVISIBLE)
                        || style.getProperty(Inner.INNERVIEWMODE) != InternalsViewMode.NONE
                        )
                )
        .add(b.createPenAndBrushSection(LINEWIDTH, 
                LINECOLOR, 
                FILLMODE, 
                FILLCOLOR, 
                TEXTCOLOR, 
                FONT))
        .add(b.createLabelItem(DiagramEditorStatik.I18N.getString("symbol.Classifier.group.header")) 
                .add(b.createStyleItem(SHOWNAME))
                .add(b.createStyleItem(SHOWSTEREOTYPES))
                .add(b.createStyleItem(SHOWTAGS))
                .add(b.createStyleItem(SHOWVISIBILITY))
                )
        .add(createAttributesSection(Attribute))
        .add(createOperationsSection(Operation))
        .add(createInnerClassesSection(Inner))
        
        ;
        return b.build(editedStyle, input);
    }

}
