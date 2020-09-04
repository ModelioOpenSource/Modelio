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

package org.modelio.diagram.editor.statik.elements.clazz;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.Inner;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.InternalStructure;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.SHOWPORTS;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.statik.elements.clazz.GmClassStructuredStyleKeys.TEXTCOLOR;

@objid ("ad364e33-cc6a-4bc2-a3ee-484d374fb592")
class ClassSymbolViewModelProvider extends ClassifierSymbolModelBuilder {
    @objid ("cfbe2bc9-ac41-4d00-97f1-a249947d84b1")
    public static ISymbolViewModel create(IStyle editedStyle, GmClass input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.Class.label"));
        
        b        
        .add(b.createStyleChooserItem())
        .add(b.createStyleItem(GmClassStructuredStyleKeys.REPMODE))
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
