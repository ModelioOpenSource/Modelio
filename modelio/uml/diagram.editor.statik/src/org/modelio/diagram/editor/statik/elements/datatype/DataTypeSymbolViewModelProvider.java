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

package org.modelio.diagram.editor.statik.elements.datatype;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.InternalStructure;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.statik.elements.datatype.DataTypeStructuredStyleKeys.TEXTCOLOR;

@objid ("d234745f-c057-4ac1-ad75-6c4ccc845ad0")
class DataTypeSymbolViewModelProvider extends ClassifierSymbolModelBuilder {
    @objid ("fe69d0a0-9cf5-426a-8c40-1533c4714490")
    public static ISymbolViewModel create(IStyle editedStyle, GmDataType input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.DataType.label"));
        
        b        
        .add(b.createStyleChooserItem())
        .add(b.createStyleItem(DataTypeStructuredStyleKeys.REPMODE))
        .add(b.createStyleItem(FEATURES)
                .withLabel(DiagramEditorStatik.I18N.getString("symbol.Classifier.automaticContent"))
                .filter(b.structuredModeFilter)
                .filter((style, context) -> style.getBoolean(Attribute.ATTGROUPVISIBLE) 
                        || style.getBoolean(Operation.OPERATIONGROUPVISIBLE)
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
        .add(createInternalStructureSection(InternalStructure))
        ;
        return b.build(editedStyle, input);
    }

}
