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

package org.modelio.uml.deploymentdiagram.editor.elements.artifact;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.uml.deploymentdiagram.editor.plugin.DiagramEditorDeployment;
import org.modelio.uml.statikdiagram.editor.elements.classifier.style.ClassifierSymbolModelBuilder;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.Attribute;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.FEATURES;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.FILLCOLOR;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.FILLMODE;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.FONT;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.Inner;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.InternalStructure;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.LINECOLOR;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.LINEWIDTH;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.Operation;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.REPMODE;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.SHOWNAME;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.SHOWPORTS;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.SHOWSTEREOTYPES;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.SHOWTAGS;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.SHOWVISIBILITY;
import static org.modelio.uml.deploymentdiagram.editor.elements.artifact.GmArtifactStructuredStyleKeys.TEXTCOLOR;

@objid ("2dfb2022-cc69-41d2-9237-7f8fd3ffa453")
class ArtifactSymbolViewModel extends ClassifierSymbolModelBuilder {
    @objid ("3873bc0c-c3aa-4b0c-9dfa-b330282adec1")
    public static ISymbolViewModel create(IStyle editedStyle, GmArtifact gmComponent) {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(DiagramEditorDeployment.I18N.getString("symbol.Artifact.label"));
        
        b
                .add(b.createStyleChooserItem())
                .add(b.createStyleItem(REPMODE))
                .add(b.createStyleItem(FEATURES)
                        .withLabel(staticI18n.getString("symbol.Classifier.automaticContent"))
                        .filter(b.structuredModeFilter)
                        .filter((style, context) -> style.getBoolean(Attribute.ATTGROUPVISIBLE)
                                || style.getBoolean(Operation.OPERATIONGROUPVISIBLE)
                                || style.getProperty(Inner.INNERVIEWMODE) != InternalsViewMode.NONE))
                .add(b.createPenAndBrushSection(LINEWIDTH,
                        LINECOLOR,
                        FILLMODE,
                        FILLCOLOR,
                        TEXTCOLOR,
                        FONT))
                .add(b.createLabelItem(staticI18n.getString("symbol.Classifier.group.header"))
                        .add(b.createStyleItem(SHOWNAME))
                        .add(b.createStyleItem(SHOWPORTS))
                        .add(b.createStyleItem(SHOWSTEREOTYPES))
                        .add(b.createStyleItem(SHOWTAGS))
                        .add(b.createStyleItem(SHOWVISIBILITY)))
                .add(createAttributesSection(Attribute))
                .add(createOperationsSection(Operation))
                .add(createInnerClassesSection(Inner))
                .add(createInternalStructureSection(InternalStructure))
        
        ;
        return b.build(editedStyle, gmComponent);
    }

}
