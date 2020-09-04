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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("e4af2c83-13be-47e2-8ac2-04a5585645e4")
public class UML2ReadLinkAction {
    @objid ("d63391f8-0fee-443a-a338-a9e874c43c7a")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("ec93fe4d-b1ea-4168-9f5d-7eb7a1fb2489")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d428118b-c3e9-465b-b761-2375392025e6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> then instantiate a {@link UML2ReadLinkAction} proxy.
     * 
     * @return a {@link UML2ReadLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("4103cdb0-8df8-4cbe-8cc0-a3500217c224")
    public static UML2ReadLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkAction.STEREOTYPE_NAME);
        return UML2ReadLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkAction} proxy or <i>null</i>.
     */
    @objid ("ec2ed2fe-2d09-4cc6-8ac7-6abe8a934f15")
    public static UML2ReadLinkAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkAction.canInstantiate(obj) ? new UML2ReadLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d5574739-acef-4e64-824c-039f0e72d933")
    public static UML2ReadLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkAction.canInstantiate(obj))
        	return new UML2ReadLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("adb7e49e-d07f-4afa-9922-d071535859b2")
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
        UML2ReadLinkAction other = (UML2ReadLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("346b2340-1c16-4250-8e03-2b94db1b5163")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("531e2bc7-f77f-44c9-b98b-fe844a7f1b14")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d42fe718-03df-4e75-bc84-c86098564a8f")
    protected UML2ReadLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f6b43d50-d0f2-4317-8e56-ff05aca49733")
    public static final class MdaTypes {
        @objid ("1a32aa6c-e6f1-47de-8521-07d7130cdcd6")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5a72edb7-4d95-474e-ae86-f4ff6c22ab2b")
        private static Stereotype MDAASSOCDEP;

        @objid ("096aec2e-8c8f-48c9-8e1a-ffe1e53e5d3c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f8872388-9c77-496f-94fe-433fb7f62e07")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "6e2770bf-c2f9-11de-8ac8-001302895b2b");
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
