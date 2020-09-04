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

package org.modelio.xmi.model.objing;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.uml2.uml.UMLFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;

@objid ("6d1a66a8-3ffa-4b45-9d2b-39b046579cb2")
public class OCombinedFragment extends OInteractionFragment {
    @objid ("9ab13606-2687-458c-a678-6b418f74bbdc")
    @Override
    public org.eclipse.uml2.uml.Element createEcoreElt() {
        return UMLFactory.eINSTANCE.createCombinedFragment();
    }

    @objid ("e6118095-0945-4849-b9ae-caf4b8020d02")
    public OCombinedFragment(CombinedFragment param) {
        super(param);
    }

    @objid ("647d2071-9566-41ba-ae52-d6b41e234fba")
    @Override
    public void attach(org.eclipse.uml2.uml.Element ecoreElt) {
        super.attach(ecoreElt);
    }

    @objid ("f9b66f87-fc24-4a22-9744-fe1902fee957")
    @Override
    public void setProperties(org.eclipse.uml2.uml.Element ecoreElt) {
        if (ecoreElt instanceof org.eclipse.uml2.uml.CombinedFragment){
            setInteractionOperator( (org.eclipse.uml2.uml.CombinedFragment) ecoreElt);
        }
    }

    @objid ("721d1809-0183-49af-bb62-ed4381fe56cc")
    private void setInteractionOperator(org.eclipse.uml2.uml.CombinedFragment ecoreElt) {
        switch (getObjingElement().getOperator()) {
        case ALTOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.ALT_LITERAL);
            break;
        case ASSERTOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.ASSERT_LITERAL);
            break;
        case BREAKOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.BREAK_LITERAL);
            break;
        case CONSIDEROP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.CONSIDER_LITERAL);
            break;
        case CRITICALOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.CRITICAL_LITERAL);
            break;
        case IGNOREOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.IGNORE_LITERAL);
            break;
        case LOOPOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.LOOP_LITERAL);
            break;
        case NEGOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.NEG_LITERAL);
            break;
        case OPTOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.OPT_LITERAL);
            break;
        case PAROP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.PAR_LITERAL);
            break;
        case SEQOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.SEQ_LITERAL);
            break;
        case STRICTOP:
            ecoreElt
            .setInteractionOperator (org.eclipse.uml2.uml.InteractionOperatorKind.STRICT_LITERAL);
            break;
        default:
            break;
        }
    }

    @objid ("00308287-7148-4208-a4bd-fe33df276c54")
    @Override
    public CombinedFragment getObjingElement() {
        return (CombinedFragment) super.getObjingElement();
    }

}
