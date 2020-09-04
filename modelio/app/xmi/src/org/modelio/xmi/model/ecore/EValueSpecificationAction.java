/* 
 * Copyright 2013-2019 Modeliosoft
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
import org.modelio.metamodel.mmextensions.infrastructure.ElementNotUniqueException;
import org.modelio.metamodel.mmextensions.standard.factory.IStandardModelFactory;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
import org.modelio.metamodel.uml.infrastructure.Element;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.IModelerModuleStereotypes;
import org.modelio.module.modelermodule.api.xmi.standard.opaqueaction.UML2ValueSpecificationAction;
import org.modelio.xmi.plugin.Xmi;
import org.modelio.xmi.reverse.ReverseProperties;
import org.modelio.xmi.util.ObjingEAnnotation;

@objid ("a5cef32e-3322-4726-858f-f2c880024b39")
public class EValueSpecificationAction extends EActivityNode {
    @objid ("8228382d-7c4a-4e9f-af14-ccbc73844c3b")
    private org.eclipse.uml2.uml.ValueSpecificationAction ecoreElement = null;

    @objid ("8bf5718c-6644-4045-befb-306669ebe8d5")
    @Override
    public Element createObjingElt() {
        if(ObjingEAnnotation.isDeleted(this.ecoreElement))
            return null;
        return UML2ValueSpecificationAction.create().getElement();
    }

    @objid ("d82583ae-51ac-4c6a-9a02-4f1a83394b69")
    public EValueSpecificationAction(org.eclipse.uml2.uml.ValueSpecificationAction element) {
        super(element);
        this.ecoreElement = element;
    }

    @objid ("61dfd7e4-696d-40ff-b4db-862728d05d35")
    @Override
    public void setProperties(Element objingElt) {
        super.setProperties(objingElt);
        setValue((OpaqueAction) objingElt);
    }

    @objid ("75131771-5310-419a-a5bc-6e3d57a67eeb")
    private void setValue(OpaqueAction objingElt) {
        IStandardModelFactory model = ReverseProperties.getInstance().getMModelServices().getModelFactory().getFactory(IStandardModelFactory.class);
        
        try {
            Stereotype stereo = ReverseProperties.getInstance().getMModelServices().getStereotype(IModelerModulePeerModule.MODULE_NAME, IModelerModuleStereotypes.UML2VALUESPECIFICATIONACTION, objingElt.getMClass());
        
            for (TagType tagType : stereo.getDefinedTagType()){
                if (tagType.getName().equals("Value")){
        
                    TaggedValue taggedValue = model.createTaggedValue();
        
                    taggedValue.setDefinition(tagType);
                    taggedValue.setAnnoted(objingElt);
        
                    TagParameter actual = model.createTagParameter();
                    actual.setValue(this.ecoreElement.getValue().stringValue());
                    taggedValue.getActual().add(actual);
                }
        
            }
        
        
        } catch (ElementNotUniqueException e) {
            Xmi.LOG.warning(e);
        }
    }

}
