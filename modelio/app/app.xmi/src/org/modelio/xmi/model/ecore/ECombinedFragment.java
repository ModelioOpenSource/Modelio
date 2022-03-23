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
package org.modelio.xmi.model.ecore;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.xmi.reverse.ReverseProperties;

@objid ("38817e77-e98b-42ec-afec-551b4405b01f")
public class ECombinedFragment extends EInteractionFragment {
    @objid ("e3d6344c-f73e-42eb-a85d-efb79972cbb2")
    @Override
    public Element createObjingElt() {
        return ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class).createCombinedFragment();
    }

    @objid ("1b540561-ca4e-4683-b6ef-d24fc48a7359")
    public  ECombinedFragment(org.eclipse.uml2.uml.CombinedFragment element) {
        super(element);
    }

    @objid ("11605d63-faff-48e1-b558-50b6a9615b8b")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setOperand((CombinedFragment) objingElt);
        
    }

    @objid ("f8b20d02-7bb9-41b3-a4ed-2ac0bcf708e8")
    private void setOperand(CombinedFragment fragment) {
        org.eclipse.uml2.uml.InteractionOperatorKind kindOfOperator = ( (org.eclipse.uml2.uml.CombinedFragment) getEcoreElement())
                .getInteractionOperator();
        
        switch (kindOfOperator.getValue()) {
        case  org.eclipse.uml2.uml.InteractionOperatorKind.ALT:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.ALTOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.ASSERT:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.ASSERTOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.BREAK:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.BREAKOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.CONSIDER:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.CONSIDEROP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.CRITICAL:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.CRITICALOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.IGNORE:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.IGNOREOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.LOOP:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.LOOPOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.NEG:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.NEGOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.OPT:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.OPTOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.PAR:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.PAROP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.SEQ:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.SEQOP);
            break;
        case  org.eclipse.uml2.uml.InteractionOperatorKind.STRICT:
            fragment.setOperator(org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperator.STRICTOP);
            break;
        default :
            break;
        }
        
    }

}
