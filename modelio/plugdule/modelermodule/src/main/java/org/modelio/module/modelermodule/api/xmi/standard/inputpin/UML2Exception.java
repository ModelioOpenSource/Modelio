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
 * Proxy class to handle a {@link InputPin} with << UML2Exception >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("280f1bea-297f-47f4-b6d6-5ab646b57289")
public class UML2Exception {
    @objid ("cbe15a21-9b63-4f46-b6cd-2c436da2b6ba")
    public static final String STEREOTYPE_NAME = "UML2Exception";

    /**
     * The underlying {@link InputPin} represented by this proxy, never null.
     */
    @objid ("9ae3f2fb-f61a-41d2-b439-73a7a8318d52")
    protected final InputPin elt;

    /**
     * Tells whether a {@link UML2Exception proxy} can be instantiated from a {@link MObject} checking it is a {@link InputPin} stereotyped << UML2Exception >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5b879300-bfb5-41ef-951e-071f39a2ccd3")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InputPin) && ((InputPin) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InputPin} stereotyped << UML2Exception >> then instantiate a {@link UML2Exception} proxy.
     * 
     * @return a {@link UML2Exception} proxy on the created {@link InputPin}.
     */
    @objid ("369a091a-6bd5-44a2-9d58-e85d6887cd51")
    public static UML2Exception create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InputPin");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Exception.STEREOTYPE_NAME);
        return UML2Exception.instantiate((InputPin)e);
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InputPin
     * @return a {@link UML2Exception} proxy or <i>null</i>.
     */
    @objid ("cb717995-b940-4eb1-b564-689fe1eff106")
    public static UML2Exception instantiate(InputPin obj) {
        return UML2Exception.canInstantiate(obj) ? new UML2Exception(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Exception} proxy from a {@link InputPin} stereotyped << UML2Exception >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InputPin}
     * @return a {@link UML2Exception} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("372df9ff-bbec-416f-b34d-1caadf96153e")
    public static UML2Exception safeInstantiate(InputPin obj) throws IllegalArgumentException {
        if (UML2Exception.canInstantiate(obj))
        	return new UML2Exception(obj);
        else
        	throw new IllegalArgumentException("UML2Exception: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5f80f43a-51d4-4b32-bee4-cadd947b2450")
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
        UML2Exception other = (UML2Exception) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InputPin}. 
     * @return the InputPin represented by this proxy, never null.
     */
    @objid ("bc2b109f-17bc-4c90-bbb4-17ba2239b976")
    public InputPin getElement() {
        return this.elt;
    }

    @objid ("f2dd77dd-4e4a-4279-922e-ae6719ed62eb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("bcf1b7e7-d32f-4f63-a09b-9a8b44148032")
    protected UML2Exception(InputPin elt) {
        this.elt = elt;
    }

    @objid ("07543495-a7b1-4345-accd-b02a81db07a5")
    public static final class MdaTypes {
        @objid ("732d6474-3756-42ec-87a6-ddfe556993af")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bfb94736-9c8b-4a4a-9d01-99787370dd3c")
        private static Stereotype MDAASSOCDEP;

        @objid ("e7ef7634-c403-4e62-b624-bdce5f8afc01")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("03fa0e4a-69c0-4f82-afc4-f64767b92f3a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b966e108-5fbe-4990-b7cf-94d258a5c3ff");
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
