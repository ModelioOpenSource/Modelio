/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartObjectBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("25009208-0fa9-4e7f-90cf-c52ca583397c")
public class UML2StartObjectBehaviorAction {
    @objid ("41f7bc6d-46fd-4815-8ba0-11c08caa4aa2")
    public static final String STEREOTYPE_NAME = "UML2StartObjectBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("72ea767b-0235-458f-9d25-3cd84b5c1d4a")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartObjectBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("aa42bf5f-7b00-4c23-ae70-598679495d86")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> then instantiate a {@link UML2StartObjectBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartObjectBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("7dee8259-9269-4858-9fef-a8bf0ef7d706")
    public static UML2StartObjectBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartObjectBehaviorAction.STEREOTYPE_NAME);
        return UML2StartObjectBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartObjectBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartObjectBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("3ae71c91-a85b-49df-830f-ed4e700075de")
    public static UML2StartObjectBehaviorAction instantiate(OpaqueAction obj) {
        return UML2StartObjectBehaviorAction.canInstantiate(obj) ? new UML2StartObjectBehaviorAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StartObjectBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartObjectBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2StartObjectBehaviorAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("04ff1730-0deb-4483-ad99-5b651530e633")
    public static UML2StartObjectBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartObjectBehaviorAction.canInstantiate(obj))
        	return new UML2StartObjectBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartObjectBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("14a13280-bb7d-47e6-bec6-93e03f6b0e1e")
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
        UML2StartObjectBehaviorAction other = (UML2StartObjectBehaviorAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("17e897ee-ef4d-44b1-ba01-497e395e8540")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("28504a16-f7ee-41ba-9b2c-5e9bc8eeb11f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4d010b77-f6d6-4aeb-8c2e-63c198a240a3")
    protected UML2StartObjectBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("fd5f46e7-53d3-4b11-a35d-6fce01797d11")
    public static final class MdaTypes {
        @objid ("9de77f7f-8f3e-4409-aa17-9d32c79b3c50")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a90ce607-72c7-4d79-adf2-ad1baa25d2c4")
        private static Stereotype MDAASSOCDEP;

        @objid ("93e6fe38-0e86-48ec-b377-d37f36b893ea")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4a8a61cb-4e02-4e7b-95fb-bdbc738efc67")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "d4d4a0b8-fb19-4b78-bc9e-e04ad77087f8");
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
