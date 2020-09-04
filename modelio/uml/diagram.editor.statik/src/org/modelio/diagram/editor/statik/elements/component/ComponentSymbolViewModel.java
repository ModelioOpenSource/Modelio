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

package org.modelio.diagram.editor.statik.elements.component;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.editor.statik.plugin.DiagramEditorStatik;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.Inner;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.InternalStructure;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.REPMODE;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.SHOWPORTS;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.statik.elements.component.ComponentStructuredStyleKeys.TEXTCOLOR;

@objid ("cc3ea0d6-7f5f-4343-81c9-ab512c2e8ca7")
class ComponentSymbolViewModel extends ClassifierSymbolModelBuilder {
    @objid ("77e9e452-4005-45ad-97bc-c65b7956fbaf")
    public static ISymbolViewModel create(IStyle editedStyle, GmComponent gmComponent) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.Component.label"));
        
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
        return b.build(editedStyle, gmComponent);
    }

}
