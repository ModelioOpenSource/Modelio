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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("38d61282-de49-4b5f-9fa5-4c894fb317ea")
public class UML2CreateObjectAction {
    @objid ("27347c03-5913-4634-8db9-39af6819c282")
    public static final String STEREOTYPE_NAME = "UML2CreateObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ed2ec08b-b30a-4e71-8f27-9e0e45459b96")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("f607fb37-3034-4726-ace4-ed499fa99dce")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> then instantiate a {@link UML2CreateObjectAction} proxy.
     * 
     * @return a {@link UML2CreateObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("787608c8-66f4-462e-b71a-ebc7b59b0ebe")
    public static UML2CreateObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateObjectAction.STEREOTYPE_NAME);
        return UML2CreateObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateObjectAction} proxy or <i>null</i>.
     */
    @objid ("e0df5b7b-6d7f-4568-9d94-0ab0ea4357fd")
    public static UML2CreateObjectAction instantiate(OpaqueAction obj) {
        return UML2CreateObjectAction.canInstantiate(obj) ? new UML2CreateObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0515c44c-f71b-4cf5-9f8a-fe16edbe9bc6")
    public static UML2CreateObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateObjectAction.canInstantiate(obj))
        	return new UML2CreateObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("789b898d-2314-46a4-b07d-f937a4376042")
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
        UML2CreateObjectAction other = (UML2CreateObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("268070ad-dce4-436e-accd-c67eef41b7f4")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("47d4fcd7-a7d7-47d4-83eb-fa3009c8ad09")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e0f371f9-cc34-4409-8857-a7c1168652c5")
    protected UML2CreateObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("c60db936-9649-41b7-9b99-afbe48991116")
    public static final class MdaTypes {
        @objid ("fbc1e116-bcca-4c81-bc9c-ea3af69b4a98")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e757c22a-e223-42f0-98f6-62bd0b33c452")
        private static Stereotype MDAASSOCDEP;

        @objid ("f8b7fe28-57f9-4202-991e-cb65941daf88")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cab58d88-f88d-4366-a75e-dc365d70a5eb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5582e283-c2f9-11de-8ac8-001302895b2b");
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
