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

package org.modelio.diagram.editor.deployment.elements.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.deployment.plugin.DiagramEditorDeployment;
import org.modelio.diagram.editor.statik.elements.classifier.style.ClassifierSymbolModelBuilder;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.utils.i18n.BundledMessages;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.Attribute;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.FEATURES;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.FILLCOLOR;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.FILLMODE;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.FONT;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.Inner;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.InternalStructure;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.LINECOLOR;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.LINEWIDTH;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.Operation;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.REPMODE;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.SHOWNAME;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.SHOWPORTS;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.SHOWTAGS;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.diagram.editor.deployment.elements.node.GmNodeStructuredStyleKeys.TEXTCOLOR;

@objid ("9b28a53b-4d5a-4f88-bd98-1cf504a22bcb")
class NodeSymbolViewModel extends ClassifierSymbolModelBuilder {
    @objid ("2445d963-0b88-457b-9e71-2c10e27167f0")
    public static ISymbolViewModel create(IStyle editedStyle, GmNode gmComponent) {
        BundledMessages i18n = DiagramEditorDeployment.I18N;
        SymbolViewContentBuilder builder = new SymbolViewContentBuilder(i18n.getString("symbol.Node.label"));
        
        builder
                .add(builder.createStyleChooserItem())
                .add(builder.createStyleItem(REPMODE))
                .add(builder.createStyleItem(FEATURES)
                        .withLabel(staticI18n.getString("symbol.Classifier.automaticContent"))
                        .filter(builder.structuredModeFilter)
                        .filter((style, context) -> style.getBoolean(Attribute.ATTGROUPVISIBLE)
                                || style.getBoolean(Operation.OPERATIONGROUPVISIBLE)
                                || style.getProperty(Inner.INNERVIEWMODE) != InternalsViewMode.NONE))
                .add(builder.createPenAndBrushSection(LINEWIDTH,
                        LINECOLOR,
                        FILLMODE,
                        FILLCOLOR,
                        TEXTCOLOR,
                        FONT))
                .add(builder.createLabelItem(staticI18n.getString("symbol.Classifier.group.header"))
                        .add(builder.createStyleItem(SHOWNAME))
                        .add(builder.createStyleItem(SHOWPORTS))
                        .add(builder.createStyleItem(SHOWSTEREOTYPES))
                        .add(builder.createStyleItem(SHOWTAGS))
                        .add(builder.createStyleItem(SHOWVISIBILITY)))
                .add(createAttributesSection(Attribute))
                .add(createOperationsSection(Operation))
                .add(createInnerClassesSection(Inner))
                .add(createInternalStructureSection(InternalStructure))
        
        ;
        return builder.build(editedStyle, gmComponent);
    }

}
