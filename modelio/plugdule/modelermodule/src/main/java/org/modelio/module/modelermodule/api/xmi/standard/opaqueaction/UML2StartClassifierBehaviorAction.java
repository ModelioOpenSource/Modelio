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
 * Proxy class to handle a {@link OpaqueAction} with << UML2StartClassifierBehaviorAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ac4197cc-0a67-4a64-8eb4-caa91ba5815e")
public class UML2StartClassifierBehaviorAction {
    @objid ("08596f28-053c-46e0-bcd6-ffe33ce3ec7c")
    public static final String STEREOTYPE_NAME = "UML2StartClassifierBehaviorAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("d6dd1ebc-06f2-4d7c-bed6-ae5d3e6ff4b4")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2StartClassifierBehaviorAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("992b7b28-b1b7-45db-9261-89344185178e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> then instantiate a {@link UML2StartClassifierBehaviorAction} proxy.
     * 
     * @return a {@link UML2StartClassifierBehaviorAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("278c82be-ba2a-43a8-8faa-b16ea0aa1f6e")
    public static UML2StartClassifierBehaviorAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StartClassifierBehaviorAction.STEREOTYPE_NAME);
        return UML2StartClassifierBehaviorAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2StartClassifierBehaviorAction} proxy or <i>null</i>.
     */
    @objid ("3ff39d2a-df49-42ad-bf20-bf1fdee09ca6")
    public static UML2StartClassifierBehaviorAction instantiate(OpaqueAction obj) {
        return UML2StartClassifierBehaviorAction.canInstantiate(obj) ? new UML2StartClassifierBehaviorAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StartClassifierBehaviorAction} proxy from a {@link OpaqueAction} stereotyped << UML2StartClassifierBehaviorAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2StartClassifierBehaviorAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("68536735-54d3-4afd-8ffa-741a91181c4a")
    public static UML2StartClassifierBehaviorAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2StartClassifierBehaviorAction.canInstantiate(obj))
        	return new UML2StartClassifierBehaviorAction(obj);
        else
        	throw new IllegalArgumentException("UML2StartClassifierBehaviorAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa91b4d9-6361-4ff6-bcf1-abcc39e5df80")
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
        UML2StartClassifierBehaviorAction other = (UML2StartClassifierBehaviorAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("e7b10555-1901-45da-8b3c-c85fc1c19d64")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("79f762d5-ed7f-4b2d-bea2-4029184f0e76")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7ef162fb-a742-4594-a190-cbfa2227079d")
    protected UML2StartClassifierBehaviorAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("aecc60a1-6721-410a-8867-25ffb2dfa838")
    public static final class MdaTypes {
        @objid ("5ed6c96d-bd64-4710-b1ad-804c377f1478")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c477fc90-0cf2-4eec-a764-59af3c93f69a")
        private static Stereotype MDAASSOCDEP;

        @objid ("b062aadb-5275-49ac-adbb-8de3675b343a")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("aa44e308-8727-4c07-9fbc-c11c60b71a4b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5d357779-c2fd-11de-8ac8-001302895b2b");
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
