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

package org.modelio.diagram.editor.statik.elements.interfaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.Inner;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.InternalStructure;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.SHOWPORTS;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.statik.elements.interfaze.InterfaceStructuredStyleKeys.TEXTCOLOR;

@objid ("566485f3-fed7-472c-a869-f5ce8d195fee")
class InterfaceSymbolViewModelProvider extends ClassifierSymbolModelBuilder {
    @objid ("0d9fdd85-ae81-430e-b67c-adaafe4cc460")
    public static ISymbolViewModel create(IStyle editedStyle, GmInterface input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.Interface.label"));
        
        b        
        .add(b.createStyleChooserItem())
        .add(b.createStyleItem(InterfaceStructuredStyleKeys.REPMODE))
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
                .add(b.createStyleItem(SHOWPORTS))
                .add(b.createStyleItem(SHOWSTEREOTYPES))
                .add(b.createStyleItem(SHOWTAGS))
                .add(b.createStyleItem(SHOWVISIBILITY))
                )
        .add(createAttributesSection(Attribute))
        .add(createOperationsSection(Operation))
        .add(createInnerClassesSection(Inner))
        .add(createInternalStructureSection(InternalStructure))
        ;
        return b.build(editedStyle, input);
    }

}
