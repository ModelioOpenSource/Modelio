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

package org.modelio.diagram.editor.statik.elements.bpmncollaboration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnCollaboration;

/**
 * This class provides the StyleKey constants for a {@link BpmnCollaboration} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("929dbbde-c9dd-41c2-a12f-f6ac0460a25b")
public class GmBpmnCollaborationUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a06bf620-809f-4bb3-b399-f922ef3909a4")
     static final StyleKey REPMODE = GmBpmnCollaborationStructuredStyleKeys.REPMODE;

    @objid ("e4a5067f-051e-47bc-8417-4efdecb1c181")
     static final StyleKey FONT = GmBpmnCollaborationStructuredStyleKeys.FONT;

    @objid ("1774e65d-7744-4efd-af53-6af98fc3757d")
     static final StyleKey TEXTCOLOR = GmBpmnCollaborationStructuredStyleKeys.TEXTCOLOR;

    @objid ("b39df54f-b4f3-44e4-8bf5-e67c688562b1")
     static final StyleKey SHOWSTEREOTYPES = GmBpmnCollaborationStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("714064ef-7ae5-474b-922f-4bba1813c1b8")
     static final StyleKey SHOWTAGS = GmBpmnCollaborationStructuredStyleKeys.SHOWTAGS;

}
