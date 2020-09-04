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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadLinkObjectEndAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f3d58a44-d387-43bb-90ad-1becc98b66d6")
public class UML2ReadLinkObjectEndAction {
    @objid ("20715e4f-4302-4447-8b42-2ca1a9cf60d7")
    public static final String STEREOTYPE_NAME = "UML2ReadLinkObjectEndAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("b39cef55-c3a5-4066-af36-6a5997cda7fe")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadLinkObjectEndAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("53df22f0-330a-425e-b321-aa7d3435df29")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> then instantiate a {@link UML2ReadLinkObjectEndAction} proxy.
     * 
     * @return a {@link UML2ReadLinkObjectEndAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("1563dd30-8696-4b31-9738-d89548b57bcd")
    public static UML2ReadLinkObjectEndAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadLinkObjectEndAction.STEREOTYPE_NAME);
        return UML2ReadLinkObjectEndAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadLinkObjectEndAction} proxy or <i>null</i>.
     */
    @objid ("6a21a110-e625-4392-8173-aff7b4250176")
    public static UML2ReadLinkObjectEndAction instantiate(OpaqueAction obj) {
        return UML2ReadLinkObjectEndAction.canInstantiate(obj) ? new UML2ReadLinkObjectEndAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadLinkObjectEndAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadLinkObjectEndAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadLinkObjectEndAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9ea9ab20-dbbf-4b24-826d-0a2201e7e007")
    public static UML2ReadLinkObjectEndAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadLinkObjectEndAction.canInstantiate(obj))
        	return new UML2ReadLinkObjectEndAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadLinkObjectEndAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6a7ae4d4-afd4-43e5-a209-0beb8f64d12a")
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
        UML2ReadLinkObjectEndAction other = (UML2ReadLinkObjectEndAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("42e8cd81-c915-4276-a73b-93957acf5baa")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c626f32b-0e51-40b5-8184-1360e6baa97e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d59acb13-11e9-4354-a079-845461de6c5e")
    protected UML2ReadLinkObjectEndAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("b56e0188-35ac-4bc6-abe5-96c4afc8d821")
    public static final class MdaTypes {
        @objid ("a2b4d836-d43a-4183-abdd-50aba67a8475")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("18bc962b-a368-4d39-b2cc-4792897e8db1")
        private static Stereotype MDAASSOCDEP;

        @objid ("570c8102-f285-4210-b4c7-ea4d5f0c1208")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c283b0f8-26ff-443e-a193-16fb28e15148")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f9061fa3-c2fc-11de-8ac8-001302895b2b");
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
