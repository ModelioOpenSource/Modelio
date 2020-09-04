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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ClearVariableAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a3faa58b-c1d9-477f-956a-bf64179c20dc")
public class UML2ClearVariableAction {
    @objid ("d09602dd-d881-4a22-b2df-5b0e5207c70e")
    public static final String STEREOTYPE_NAME = "UML2ClearVariableAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("f8e29194-2dc8-4007-8b74-b16a0ab0247c")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ClearVariableAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0b799e2d-d6ef-4860-97ed-0129b80ae798")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> then instantiate a {@link UML2ClearVariableAction} proxy.
     * 
     * @return a {@link UML2ClearVariableAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("a98a41bc-c970-4fcf-8807-ae41071843a7")
    public static UML2ClearVariableAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ClearVariableAction.STEREOTYPE_NAME);
        return UML2ClearVariableAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ClearVariableAction} proxy or <i>null</i>.
     */
    @objid ("2a5537b2-eeea-430c-9db6-4cef11d68449")
    public static UML2ClearVariableAction instantiate(OpaqueAction obj) {
        return UML2ClearVariableAction.canInstantiate(obj) ? new UML2ClearVariableAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ClearVariableAction} proxy from a {@link OpaqueAction} stereotyped << UML2ClearVariableAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ClearVariableAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("deb57ba2-26e8-42a8-bfa1-311629a92af4")
    public static UML2ClearVariableAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ClearVariableAction.canInstantiate(obj))
        	return new UML2ClearVariableAction(obj);
        else
        	throw new IllegalArgumentException("UML2ClearVariableAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("793c9a40-7c71-4c06-86f0-8aa2b2ba390c")
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
        UML2ClearVariableAction other = (UML2ClearVariableAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("37bf72cb-2a7a-4008-8d32-0ab68fec2ef3")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("c11fef83-5bba-4836-be21-e6054ab7d6d3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e85ec07b-6ba0-4ec1-9e8a-16bf40bcb041")
    protected UML2ClearVariableAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("f5cadea9-90dd-4d0b-a894-0d94886a75c3")
    public static final class MdaTypes {
        @objid ("1f1efce3-2bae-4249-8b95-d035878c8d46")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7fcfcb2a-b754-428f-a30e-f226c69e51b8")
        private static Stereotype MDAASSOCDEP;

        @objid ("d117532e-9049-4bb3-94e0-38a76d5e5445")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("22d2a805-4d14-4177-a8bd-429658991464")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "b071b025-c2fc-11de-8ac8-001302895b2b");
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
