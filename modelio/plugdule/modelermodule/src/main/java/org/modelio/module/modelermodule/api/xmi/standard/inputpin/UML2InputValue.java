/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.inputpin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InputPin;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link InputPin} with << UML2InputValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1c34bb71-2360-4ab4-a7be-dc80c0436183")
public class UML2InputValue {
    @objid ("245d3b5a-70e0-4710-b68a-28da974f2bbd")
    public static final String STEREOTYPE_NAME = "UML2InputValue";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("625c7376-9185-4db9-867a-4c242f1fcb7c")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2InputValue proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2InputValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e968077d-b54e-4fc2-bcda-8e8e03a9ab38")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2InputValue >> then instantiate a {@link UML2InputValue} proxy.
     * 
     * @return a {@link UML2InputValue} proxy on the created {@link InputPin}.
     */
    @objid ("72173d83-5cea-4b9b-8d79-37b95d7a424c")
    public static UML2InputValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InputValue.STEREOTYPE_NAME);
        return UML2InputValue.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2InputValue} proxy or <i>null</i>.
     */
    @objid ("511ac7d2-970c-4851-a415-63541ba283e5")
    public static UML2InputValue instantiate(InputPin obj) {
        return UML2InputValue.canInstantiate(obj) ? new UML2InputValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InputValue} proxy from a {@link InputPin} stereotyped << UML2InputValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2InputValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4a6d8774-cc3f-4c16-abb9-18cf0dfb7b46")
    public static UML2InputValue safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2InputValue.canInstantiate(obj))
        	return new UML2InputValue(obj);
        else
        	throw new IllegalArgumentException("UML2InputValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("cdf0572d-bb1c-45d9-a298-1e604c2d663f")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2InputValue other = (UML2InputValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("0cc64f22-9911-4a42-b3d3-e2f3a5c83725")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("442651f8-01a8-43fc-b4fe-3c324edefb15")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("51f65b01-fd93-49a9-b31f-5f5669db5649")
    protected UML2InputValue(InputPin elt) {
        this.elt = elt;
    }

    @objid ("01baa51d-5b0b-4b35-8446-fe87b63e0a81")
    public static final class MdaTypes {
        @objid ("794e793a-92b8-4dc8-bb42-9d7febded2bc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4d7e02f1-9f25-4eda-b387-14fc86a04f6e")
        private static Stereotype MDAASSOCDEP;

        @objid ("194da00d-1369-4168-a760-6b3760ba1693")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("931cc442-4aa1-4c7c-98f3-025d762b2bbb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "a81a2a04-07b3-4a26-8b1e-5b4ebaa67990");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
