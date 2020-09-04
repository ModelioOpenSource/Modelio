/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Reference >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("0b1a5642-50de-46f9-bf4a-2fc939d70bf0")
public class Reference {
    @objid ("ae8b5c26-0d38-4460-9480-ea4e0233eef0")
    public static final String STEREOTYPE_NAME = "Reference";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("973c9a26-b47b-4620-8b77-2405082470a0")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Reference proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Reference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6018b9ce-c17a-471c-a7cc-34d1a4c8ee91")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Reference >> then instantiate a {@link Reference} proxy.
     * 
     * @return a {@link Reference} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("0bb01a70-0e0d-48be-92d7-2f8ac888b66a")
    public static Reference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Reference.STEREOTYPE_NAME);
        return Reference.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Reference} proxy from a {@link MethodologicalLink} stereotyped << Reference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Reference} proxy or <i>null</i>.
     */
    @objid ("ec7c3706-37a6-444e-9376-747f46c0a200")
    public static Reference instantiate(MethodologicalLink obj) {
        return Reference.canInstantiate(obj) ? new Reference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Reference} proxy from a {@link MethodologicalLink} stereotyped << Reference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Reference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("bef7e44b-6914-46a7-b2e4-28f24efd07a6")
    public static Reference safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Reference.canInstantiate(obj))
        	return new Reference(obj);
        else
        	throw new IllegalArgumentException("Reference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2e3656a4-2638-4010-b48d-c91fcb045f5e")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("d63c878d-80c3-48e0-be1f-5f7a98a81a88")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("d645d9b3-fb1f-47d6-a3d2-827d410c0d4b")
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
        Reference other = (Reference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("51c1903a-b5bd-4248-b81f-6360c28f97c2")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("4c05c625-960a-4c72-b16a-91a7c16d9cd8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("640c3ae6-e7df-45aa-acb5-227749aca19b")
    protected Reference(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("a27b86e8-95fd-41e3-ac1c-1744e5c17bea")
    public static final class MdaTypes {
        @objid ("108bddbf-b6db-4162-93e4-517a9af00a67")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("012ffc76-b73d-4222-b1c7-4f6603ef4f25")
        private static Stereotype MDAASSOCDEP;

        @objid ("e698ce5e-6439-4859-9f9f-0a1ce4113220")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("25df635c-43c3-4694-a5d2-4c50c70945b6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3b4dc351-ccaa-47b8-af57-8434f8e0e5f5");
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
