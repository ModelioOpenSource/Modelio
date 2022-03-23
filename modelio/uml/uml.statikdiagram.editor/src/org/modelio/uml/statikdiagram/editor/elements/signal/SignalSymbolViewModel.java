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
package org.modelio.uml.statikdiagram.editor.elements.signal;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.uml.statikdiagram.editor.plugin.DiagramEditorStatik;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.Attribute;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.FEATURES;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.FILLCOLOR;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.FILLMODE;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.FONT;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.Inner;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.InternalStructure;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.LINECOLOR;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.LINEWIDTH;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.Operation;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.REPMODE;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.SHOWNAME;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.SHOWPORTS;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.SHOWTAGS;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.uml.statikdiagram.editor.elements.signal.GmSignalStructuredStyleKeys.TEXTCOLOR;

@objid ("335f6356-c65d-4a63-8221-639ac03cefa7")
class SignalSymbolViewModel extends ClassifierSymbolModelBuilder {
    @objid ("b65159f8-2366-4a27-ab8d-37cabfb99217")
    public static ISymbolViewModel create(IStyle editedStyle, GmSignal input) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorStatik.I18N.getString("symbol.Signal.label"));
        
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
        return b.build(editedStyle, input);
    }

}
